package com.singhraghav.cats.shoppingcart.state.counter

import cats.Functor
import cats.implicits.toFunctorOps
import com.singraghav.models.Types.RedisKey
import dev.profunktor.redis4cats.RedisCommands
case class LiveCounter[F[_] : Functor] private(key: RedisKey, cmd: RedisCommands[F, String, Int]) extends Counter[F] {
  override def increment: F[Unit] = cmd.incr(key).void

  override def get: F[Int] = cmd.get(key).map(_.getOrElse(0))
}

