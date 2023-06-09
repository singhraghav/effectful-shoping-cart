package com.singhraghav.cats.shoppingcart.api.shoppingcart

import com.singraghav.models.{Cart, CartTotal}
import com.singraghav.models.Types.{ItemId, Quantity, UserId}

trait ShoppingCart[F[_]] {

  def add(userId: UserId, itemId: ItemId, quantity: Quantity): F[Unit]

  def delete(userId: UserId): F[Unit]

  def get(userId: UserId): F[CartTotal]

  def removeItem(userId: UserId, itemId: ItemId): F[Unit]

  def update(userId: UserId, cart: Cart): F[Unit]

}
