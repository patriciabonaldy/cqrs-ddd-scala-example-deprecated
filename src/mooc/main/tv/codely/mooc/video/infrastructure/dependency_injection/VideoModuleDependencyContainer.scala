package tv.codely.mooc.video.infrastructure.dependency_injection

import tv.codely.shared.infrastructure.doobie.DoobieDbConnection
import tv.codely.mooc.video.application.create.VideoCreator
import tv.codely.mooc.video.application.search.VideosSearcher
import tv.codely.mooc.video.application.update.VideoTitleUpdater
import tv.codely.mooc.video.domain.{VideoFinder, VideoRepository}
import tv.codely.mooc.video.infrastructure.repository.DoobieMySqlVideoRepository

import scala.concurrent.ExecutionContext
import tv.codely.shared.domain.bus.MessagePublisher

final class VideoModuleDependencyContainer(
    doobieDbConnection: DoobieDbConnection,
    messagePublisher: Seq[MessagePublisher]
)(implicit executionContext: ExecutionContext) {
  val repository: VideoRepository = new DoobieMySqlVideoRepository(doobieDbConnection)
  val videoFinder: VideoFinder = new VideoFinder(repository)
  val videoUpdate: VideoTitleUpdater = new VideoTitleUpdater(videoFinder, messagePublisher.iterator.next())

  val videosSearcher: VideosSearcher = new VideosSearcher(videoFinder)
  val videoCreator: VideoCreator     = new VideoCreator(repository, messagePublisher)
}
