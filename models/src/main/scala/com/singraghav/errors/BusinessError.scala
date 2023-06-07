package com.singraghav.errors

import scala.util.control.NoStackTrace

sealed trait BusinessError

case object RandomError extends BusinessError

