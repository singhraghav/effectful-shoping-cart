package com.singhraghav.cats.shoppingcart.api.background

import cats.effect.implicits._
import cats.effect.kernel.Temporal
import cats.syntax.all._

import scala.concurrent.duration.FiniteDuration

trait Background[F[_]] {

  def schedule[A] (fa: F[A], duration: FiniteDuration): F[Unit]

}

object Background {

  def apply[F[_]](implicit bc: Background[F]): Background[F] = bc

  implicit def concurrentBackground[F[_] : Temporal]: Background[F] =
    new Background[F] {

      def schedule[A](
                       fa: F[A],
                       duration: FiniteDuration
                     ): F[Unit] =
        (Temporal[F].sleep(duration) *> fa).start.void

    }
}
