package com.singhraghav.cats.shoppingcart.api.auth

import com.singraghav.models.Types.{JwtToken, Password, Username}
import com.singraghav.models.User

trait Auth[F[_]] {

  def findUser(token: JwtToken): F[Option[User]]

  def newUser(username: Username, password: Password): F[JwtToken]

  def login(username: Username, password: Password): F[JwtToken]

  def logout(toke: JwtToken, username: Username): F[Unit]
}
