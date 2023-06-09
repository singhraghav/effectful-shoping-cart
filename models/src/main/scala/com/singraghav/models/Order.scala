package com.singraghav.models

import com.singraghav.models.Types.{ItemId, OrderId, PaymentId, Quantity}
import squants.market.Money

case class Order(id: OrderId, paymentId: PaymentId, items: Map[ItemId, Quantity], total: Money)
