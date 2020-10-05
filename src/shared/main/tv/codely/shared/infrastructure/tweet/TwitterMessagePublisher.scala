package tv.codely.shared.infrastructure.tweet

import spray.json.RootJsonFormat
import tv.codely.shared.domain.bus.{Message, MessagePublisher}
import tv.codely.shared.infrastructure.logger.ScalaLoggingLogger

class TwitterMessagePublisher extends MessagePublisher {
  private val logger:ScalaLoggingLogger = new ScalaLoggingLogger

  override def publish[T <: Message](message: T)(implicit marshaller: RootJsonFormat[Message]): Unit = {
    logger.info("Publicando en twitter")

  }
}
