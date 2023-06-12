package com.singhraghav.cats.shoppingcart.service.http.routes

import cats._
import com.singhraghav.cats.shoppingcart.api.items.Items
import com.singraghav.models.BrandParam
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router

import com.singhraghav.cats.shoppingcart.service.http.params._
final class ItemRoutes[F[_]: Defer: Monad](items: Items[F])
extends Http4sDsl[F]
{

  private val prefix = "/items"

  private object BrandQueryParam extends OptionalQueryParamDecoderMatcher[BrandParam]("brand")

  private val httpRoutes: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root :? BrandQueryParam(brand) =>
      Ok(brand.fold(items.findAll)(b => items.findBy(b.toDomain)))
  }

  val routes = Router(prefix -> httpRoutes)

}
