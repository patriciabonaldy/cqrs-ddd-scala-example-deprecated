package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.{Video, VideoId}

import scala.concurrent.Future

final class VideosSearcher(repository:  tv.codely.mooc.video.domain.VideoFinder) {
  def all(): Future[Seq[Video]] = repository.all()

  def findById(id: VideoId): Option[Video] = repository.findById(id)
}
