package com.singraghav.models

import eu.timepit.refined.types.string.NonEmptyString
import io.estatico.newtype.macros._

object Types {

  @newtype case class Password(value: String)

  @newtype case class Username(value: String)

  @newtype case class Email(value: String)

  @newtype case class Brand(value: NonEmptyString)

  @newtype case class Category(value: NonEmptyString)

  @newtype case class Model(value: NonEmptyString)

  @newtype case class Description(value: NonEmptyString)

}
