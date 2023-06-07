package com.singraghav.errors

import scala.util.control.NoStackTrace

sealed trait BusinessError extends NoStackTrace

case object RandomError extends BusinessError

