package com.singhraghav.cats.shoppingcart.api.categories

import com.singraghav.models.Category
import com.singraghav.models.Types.CategoryName

trait Categories[F[_]] {
    def findAll: F[List[Category]]

    def create(name: CategoryName): F[Unit]
}
