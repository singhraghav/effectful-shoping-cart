package com.singhraghav.cats.shoppingcart.api.orders

import com.singraghav.models.{CartItem, Order}
import com.singraghav.models.Types.{OrderId, PaymentId, UserId}
import squants.market.Money

trait Orders[F[_]] {

  def get(userId: UserId, orderId: OrderId): F[Option[Order]]

  def findBy(userId: UserId): F[List[Order]]

  def create(userId: UserId, paymentId: PaymentId, items: List[CartItem], total: Money): F[OrderId]

}
