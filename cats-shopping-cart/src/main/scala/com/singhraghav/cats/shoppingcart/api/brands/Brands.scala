package com.singhraghav.cats.shoppingcart.api.brands

import com.singraghav.models.Types.{Brand, BrandName}

trait Brands[F[_]] {
  def findAll: F[List[Brand]]

  def create(name: BrandName): F[Unit]
}
