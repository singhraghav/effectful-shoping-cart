package com.singraghav.models

import eu.timepit.refined.types.string.NonEmptyString
import io.estatico.newtype.macros._

import java.util.UUID
import scala.language.implicitConversions

object Types {

  @newtype case class Password(value: String)

  @newtype case class Username(value: String)

  @newtype case class Email(value: String)

  @newtype case class BrandId(value: UUID)

  @newtype case class BrandName(value: String)

  @newtype case class CategoryId(value: UUID)

  @newtype case class CategoryName(value: NonEmptyString)

  @newtype case class ItemId(value: UUID)

  @newtype case class ItemName(value: NonEmptyString)

  @newtype case class ItemDescription(value: NonEmptyString)


  @newtype case class Model(value: NonEmptyString)

  @newtype case class Description(value: NonEmptyString)

  @newtype case class RedisKey(value: String)

  object RedisKey {
    implicit def toString(key: RedisKey): String = key.value
  }

  @newtype case class UserId(value: UUID)

  @newtype case class Quantity(value: Int)

  @newtype case class CartId(value: UUID)

  @newtype case class OrderId(uuid: UUID)

  @newtype case class PaymentId(uuid: UUID)

  @newtype case class JwtToken(value: String)

}
