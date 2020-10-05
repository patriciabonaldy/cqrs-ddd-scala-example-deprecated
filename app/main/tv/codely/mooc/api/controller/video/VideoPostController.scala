package tv.codely.mooc.api.controller.video

import scala.concurrent.duration.Duration
import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.mooc.video.application.update.VideoTitleUpdater
import tv.codely.mooc.video.application.create.VideoCreator
import tv.codely.mooc.video.domain.{Video, VideoCategory, VideoDuration, VideoId, VideoTitle}

final class VideoPostController(creator: VideoCreator, updater: VideoTitleUpdater) {
  def post(id: String, title: String, duration: Duration, category: String, creatorId: String): StandardRoute = {
    creator.create(VideoId(id), VideoTitle(title), VideoDuration(duration), VideoCategory(category), UserId(creatorId))

    complete(HttpResponse(NoContent))
  }
  def updateTitle(id: String, title: String):StandardRoute ={
    val video: Video = updater.update(VideoId(id), VideoTitle(title))
    complete(HttpResponse(entity=video.toString))
  }
}
