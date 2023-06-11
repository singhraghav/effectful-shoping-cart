package com.singraghav.errors

import scala.util.control.NoStackTrace

sealed trait BusinessError extends NoStackTrace

case object RandomError extends BusinessError

case object EmptyCartError extends NoStackTrace

case class OrderError(cause: String) extends NoStackTrace

case class PaymentError(cause: String) extends NoStackTrace

