package com.singhraghav.cats.shoppingcart.service.checkout

import cats.{Monad, MonadThrow}
import cats.syntax.all._
import com.singraghav.models.CartTotal
import com.singhraghav.cats.shoppingcart.api.orders.Orders
import com.singhraghav.cats.shoppingcart.api.payment.PaymentClient
import com.singhraghav.cats.shoppingcart.api.shoppingcart.ShoppingCart
import com.singraghav.models.{Card, CartItem, Payment}
import com.singraghav.models.Types.{OrderId, PaymentId, UserId}
import cats.effect.std.Console
import cats.implicits.catsSyntaxSemigroup
import com.singhraghav.cats.shoppingcart.api.background.Background
import com.singraghav.errors.{EmptyCartError, OrderError, PaymentError}
import retry.{RetryDetails, Sleep, retryingOnAllErrors}
import retry.RetryDetails.{GivingUp, WillDelayAndRetry}
import retry.RetryPolicies.{exponentialBackoff, limitRetries}
import squants.market.Money

import scala.concurrent.duration.DurationInt

final class CheckoutService[F[_]: MonadThrow: Console: Sleep: Background](
    paymentClient: PaymentClient[F],
    shoppingCart: ShoppingCart[F],
    orders: Orders[F]
) {

  private val retryPolicy = limitRetries[F](3) |+| exponentialBackoff[F](10.milliseconds)

  private def logError(action: String)(e: Throwable, details: RetryDetails): F[Unit] =
    details match {
      case r: WillDelayAndRetry =>
        Console[F].error(s"Failed on action $action, retried ${r.retriesSoFar} times")
      case g: GivingUp =>
        Console[F].error(s"giving up on action $action, after ${g.totalRetries} retires")
    }

  def checkout(userId: UserId, card: Card): F[OrderId] =
    shoppingCart.get(userId)
      .ensure(EmptyCartError)(_.items.nonEmpty)
      .flatMap {
        case CartTotal(items, total) =>
          for {
            pid <- processPayment(Payment(userId, total, card))
            order <- createOrder(userId, pid, items, total)
            _  <- shoppingCart.delete(userId).attempt.void
          } yield order
      }


  private def processPayment(payment: Payment): F[PaymentId] = {
    val action = retryingOnAllErrors[PaymentId](
      policy = retryPolicy,
      onError = logError("Payments")
    )(paymentClient.process(payment))

    action.adaptError {
      case e => PaymentError(Option(e.getMessage).getOrElse("Unknown"))
    }
  }

  private def createOrder(
                         userId: UserId,
                         paymentId: PaymentId,
                         items: List[CartItem],
                         total: Money
                         ): F[OrderId] = {
    val action = retryingOnAllErrors[OrderId](
      policy = retryPolicy,
      onError = logError("Order")
    )(orders.create(userId, paymentId, items, total))

    def bagAction(fa: F[OrderId]): F[OrderId] =
      fa.adaptError {
        case e => OrderError(e.getMessage)
      }
        .onError {
          case _ =>
            Console[F].error(s"Failed to create order for: ${paymentId}") >> Background[F].schedule(bagAction(fa), 1.hour)
        }

    bagAction(action)
  }

}
