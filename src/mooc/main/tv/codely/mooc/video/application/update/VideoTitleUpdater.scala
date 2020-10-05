package tv.codely.mooc.video.application.update
import tv.codely.mooc.shared.infrastructure.marshaller.DomainEventsMarshaller.MessageMarshaller
import tv.codely.mooc.video.domain.{Video, VideoFinder, VideoId, VideoTitle, VideoUpdateTitle}
import tv.codely.shared.domain.bus.MessagePublisher

class VideoTitleUpdater(finder: VideoFinder , messagePublisher: MessagePublisher) {


  def update(id: VideoId, title: VideoTitle): Video = {
    val oldVideo = finder.findById(id).get
    val newVideo = Video(id,title, oldVideo.duration, oldVideo.category, oldVideo.creatorId )
    finder.repository.update(newVideo)
    messagePublisher.publish(VideoUpdateTitle(newVideo))(MessageMarshaller)
    newVideo
  }


}
