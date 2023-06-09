package com.singhraghav.cats.shoppingcart.api.payment

import com.singraghav.models.Payment
import com.singraghav.models.Types.PaymentId

trait PaymentClient[F[_]] {

  def process(payment: Payment): F[PaymentId]

}
