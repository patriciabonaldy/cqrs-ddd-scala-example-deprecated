package tv.codely.mooc.video.domain

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


class VideoFinder(val repository: VideoRepository) {

  def all(): Future[Seq[Video]] = {
    repository.all()
  }

  def findById(id:VideoId): Option[Video] = {
    val video = Await.result(repository.findById(id), Duration.Inf)

    ensureVideoExists(video)
    video
  }
  private def ensureVideoExists(video: Option[Video]) : Any = {
    video match {
      case Some(value) => value
      case None => throw new Exception("\"Video no existe")
    }
  }
}
