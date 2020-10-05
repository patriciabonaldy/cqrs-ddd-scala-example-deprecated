package tv.codely.mooc.video.application.create

import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.mooc.shared.infrastructure.marshaller.DomainEventsMarshaller.MessageMarshaller
import tv.codely.mooc.video.domain._
import tv.codely.shared.domain.bus.MessagePublisher
import tv.codely.shared.domain.logger.Logger
import tv.codely.shared.infrastructure.logger.ScalaLoggingLogger

final class VideoCreator(repository: VideoRepository, messagePublisher: Seq[MessagePublisher]) {
  def create(
      id: VideoId,
      title: VideoTitle,
      duration: VideoDuration,
      category: VideoCategory,
      creatorId: UserId
  ): Unit = {
    val logger: Logger = new ScalaLoggingLogger
    val video = Video(id, title, duration, category, creatorId)

    repository.save(video)
    for (publisher <- messagePublisher) {
      publisher.publish(VideoCreated(video))(MessageMarshaller)
    }
    logger.info("video Creado")
  }
}
