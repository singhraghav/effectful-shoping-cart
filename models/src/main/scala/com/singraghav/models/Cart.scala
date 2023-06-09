package com.singraghav.models

import com.singraghav.models.Types.{CartId, ItemId, Quantity}
import squants.market.Money

case class Cart(id: CartId, items: Map[ItemId, Item])
case class CartItem(item: Item, quantity: Quantity)

case class CartTotal(items: List[CartItem], total: Money)
