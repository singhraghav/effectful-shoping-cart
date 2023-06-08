package com.singhraghav.cats.shoppingcart.state.counter

trait Counter[F[_]] {

    def increment: F[Unit]
    def get: F[Int]
}
