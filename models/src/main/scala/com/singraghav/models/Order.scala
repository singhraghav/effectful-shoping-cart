package com.singraghav.models

import java.util.UUID

case class Order(uuid: UUID, paymentId: UUID, items: Map[UUID, Int], total: BigDecimal)
