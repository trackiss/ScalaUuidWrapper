package infrastructure

import java.util.UUID

import scala.util.Try

case class Uuid private (private val value: UUID) extends Ordered[Uuid] {
  def leastSignificantBits: Long = value.getLeastSignificantBits
  def mostSignificantBits: Long = value.getMostSignificantBits
  def version: Try[UuidVersion] = UuidVersion.fromInt(value.version)
  def variant: Try[UuidVariant] = UuidVariant.fromInt(value.variant)
  def timestamp: Try[Long] = Try(value.timestamp)
  def clockSequence: Try[Int] = Try(value.clockSequence)
  def node: Try[Long] = Try(value.node)
  override def toString: String = value.toString
  override def hashCode: Int = value.hashCode
  override def equals(obj: Any): Boolean = value.equals(obj)
  override def compare(that: Uuid): Int = value.compareTo(that.value)
}

object Uuid {
  def apply(mostSignificantBits: Long, leastSignificantBits: Long): Uuid =
    new Uuid(new UUID(mostSignificantBits, leastSignificantBits))
  def randomUuid: Uuid = Uuid(UUID.randomUUID)
  def nameUuidFromBytes(name: Seq[Byte]): Uuid =
    Uuid(UUID.nameUUIDFromBytes(name.toArray))
  def fromString(name: String): Try[Uuid] = Try(Uuid(UUID.fromString(name)))
}
