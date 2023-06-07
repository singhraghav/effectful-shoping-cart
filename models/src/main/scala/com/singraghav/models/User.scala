package com.singraghav.models

import com.singraghav.models.Types.{Password, Username}

import java.util.UUID

sealed trait User {
  def uuid: UUID

  def userName: Username
}

case class Visitor(uuid: UUID, userName: Username, password: Password) extends User

case class Admin(uuid: UUID, userName: Username) extends User