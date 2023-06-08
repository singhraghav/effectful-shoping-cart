package com.singraghav.models

import com.singraghav.models.Types.{BrandId, CategoryId, ItemDescription, ItemId, ItemName, Model}
import squants.market.Money

case class Item(
                uuid: ItemId,
                name: ItemName,
                description: ItemDescription,
                price: Money,
                brand: Brand,
                category: Category
  )

case class CreateItem(
                 name: ItemName,
                 description: ItemDescription,
                 price: Money,
                 brand: BrandId,
                 category: CategoryId
               )


case class UpdateItem(
                       uuid: ItemId,
                       price: Money
                     )