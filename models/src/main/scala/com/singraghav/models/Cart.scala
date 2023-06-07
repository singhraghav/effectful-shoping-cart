package com.singraghav.models

import java.util.UUID

case class Cart(uuid: UUID, items: Map[UUID, Item])
