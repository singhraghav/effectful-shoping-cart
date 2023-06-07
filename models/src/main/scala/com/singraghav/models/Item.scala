package com.singraghav.models

import com.singraghav.models.Types.{Brand, Category, Description, Model}

import java.util.UUID

case class Item(uuid: UUID, model: Model, brand: Brand, category: Category, description: Description, price: BigDecimal)
