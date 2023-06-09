package com.singraghav.models

import com.singraghav.models.Types.UserId
import squants.market.Money

case class Payment(id: UserId, total: Money, card: Card)
