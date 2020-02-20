package infrastructure

import scala.util.Try

sealed abstract class UuidVariant

object UuidVariant {
  def fromInt(value: Int): Try[UuidVariant] = Try {
    require(value == 0 || value == 2 || value == 6 || value == 7)

    value match {
      case 0 => Ncs
      case 2 => Rfc
      case 6 => Microsoft
      case 7 => Reserved
      case _ => throw new IllegalArgumentException
    }
  }
}

case object Ncs extends UuidVariant {
  val value: Int = 0
}

case object Rfc extends UuidVariant {
  val value: Int = 2
}

case object Microsoft extends UuidVariant {
  val value: Int = 6
}

case object Reserved extends UuidVariant {
  val value: Int = 7
}
