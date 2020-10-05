package tv.codely.mooc.video.domain

import scala.concurrent.Future

trait VideoRepository {
  def update(newVideo: Video): Unit

  def all(): Future[Seq[Video]]

  def save(video: Video): Future[Unit]

  def findById(id: VideoId): Future[Option[Video]]
}
