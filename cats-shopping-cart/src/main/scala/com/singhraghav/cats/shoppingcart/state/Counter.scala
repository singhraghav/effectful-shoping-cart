package com.singhraghav.cats.shoppingcart.state

import cats.effect.Ref
import cats.effect.kernel.Sync
import cats.implicits.toFunctorOps

trait Counter[F[_]] {

    def increment: F[Unit]
    def get: F[Int]
}

object Counter {
  def makeLive[F[_]: Sync]: F[Counter[F]] =
    Ref[F].of[Int](0).map { ref =>
      new Counter[F] {
        override def increment: F[Unit] = ref.update(_ + 1)

        override def get: F[Int] = ref.get
      }
    }
}
