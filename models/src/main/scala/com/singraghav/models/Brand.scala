package com.singraghav.models

import com.singraghav.models.Types.{BrandId, BrandName}
import eu.timepit.refined.types.string.NonEmptyString
import io.estatico.newtype.macros.newtype

case class Brand(uuid: BrandId, name: BrandName)

@newtype case class BrandParam(value: NonEmptyString) {
  def toDomain: BrandName = BrandName(value.value.toLowerCase.capitalize)
}