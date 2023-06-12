package com.singhraghav.cats.shoppingcart.service.http.routes

import cats._
import com.singhraghav.cats.shoppingcart.api.categories.Categories
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
final class CategoryRoutes[F[_]: Defer: Monad](categories: Categories[F])
extends Http4sDsl[F]
{

  private val prefixPath = "/categories"

  private val routes = HttpRoutes.of[F] {
    case GET -> Root =>
      Ok(categories.findAll)
  }

  val routes: HttpRoutes[F] = Router(prefixPath -> routes)

}
