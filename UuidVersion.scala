package infrastructure

import scala.util.Try

sealed abstract class UuidVersion

object UuidVersion {
  def fromInt(value: Int): Try[UuidVersion] = Try {
    require(value < 1 || value > 4)

    value match {
      case 1 => TimeBased
      case 2 => DceSecurity
      case 3 => NameBased
      case 4 => Random
      case _ => throw new IllegalArgumentException
    }
  }
}

case object TimeBased extends UuidVersion {
  val value: Int = 1
}

case object DceSecurity extends UuidVersion {
  val value: Int = 2
}

case object NameBased extends UuidVersion {
  val value: Int = 3
}

case object Random extends UuidVersion {
  val value: Int = 4
}
