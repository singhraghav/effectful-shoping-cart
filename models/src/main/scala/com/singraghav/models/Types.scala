package com.singraghav.models

import eu.timepit.refined.types.string.NonEmptyString
import io.estatico.newtype.macros._

object Types {

  @newtype case class Username(value: String)

  @newtype case class Email(value: String)

  @newtype case class Brand(value: NonEmptyString)

  @newtype case class Category(value: NonEmptyString)

}
