package com.singhraghav.cats.shoppingcart.service.checkout

import cats.Monad
import cats.syntax.flatMap._
import cats.syntax.functor._
import com.singhraghav.cats.shoppingcart.api.orders.Orders
import com.singhraghav.cats.shoppingcart.api.payment.PaymentClient
import com.singhraghav.cats.shoppingcart.api.shoppingcart.ShoppingCart
import com.singraghav.models.{Card, Payment}
import com.singraghav.models.Types.{OrderId, UserId}

final class CheckoutService[F[_]: Monad](
    paymentClient: PaymentClient[F],
    shoppingCart: ShoppingCart[F],
    orders: Orders[F]
) {

  def checkout(userId: UserId, card: Card): F[OrderId] =
    for {
      cart <- shoppingCart.get(userId)
      paymentId <- paymentClient.process(Payment(userId, cart.total, card))
      orderId <- orders.create(userId, paymentId, cart.items, cart.total)
    } yield orderId

}
