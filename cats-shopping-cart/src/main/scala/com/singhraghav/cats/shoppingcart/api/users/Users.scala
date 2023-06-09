package com.singhraghav.cats.shoppingcart.api.users

import com.singraghav.models.Types.{Password, UserId, Username}
import com.singraghav.models.User

trait Users[F[_]] {

  def find(username: Username, password: Password): F[Option[User]]

  def create(username: Username, password: Password): F[UserId]

}
