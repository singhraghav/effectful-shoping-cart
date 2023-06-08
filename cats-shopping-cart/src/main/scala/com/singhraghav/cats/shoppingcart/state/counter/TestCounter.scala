package com.singhraghav.cats.shoppingcart.state.counter

import cats.effect.Ref
import cats.implicits.toFunctorOps

case class TestCounter[F[_]](ref: Ref[F, Int]) extends Counter[F] {
  override def increment: F[Unit] = ref.update(_ + 1)

  override def get: F[Int] = ref.get
}