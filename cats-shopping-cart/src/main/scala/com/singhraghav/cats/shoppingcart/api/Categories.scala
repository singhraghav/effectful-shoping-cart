package com.singhraghav.cats.shoppingcart.api

import cats.MonadError
import cats.effect.std.Random
import cats.implicits.{catsSyntaxApplicativeId, catsSyntaxIfM}
import com.singraghav.errors.{BusinessError, RandomError}
import com.singraghav.models.Types.Category

trait Categories[F[_]] {
    def findAll: F[List[Category]]
}

case class LiveCategories[F[_]: MonadError[*[_], Throwable]: Random]() extends Categories[F] {

  def findAll: F[List[Category]] =
    Random[F].nextBoolean.ifM(
      List.empty[Category].pure[F],
      MonadError[F, Throwable].raiseError(RandomError)
    )
}
