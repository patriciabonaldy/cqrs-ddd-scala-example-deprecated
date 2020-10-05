package tv.codely.mooc.video.domain

import tv.codely.shared.domain.bus.Message

object VideoUpdateTitle {
  def apply(id: String, title: String): VideoUpdateTitle = apply(
    VideoId(id),
    VideoTitle(title)
  )

  def apply(video: Video): VideoUpdateTitle = apply(video.id, video.title)
}

final case class VideoUpdateTitle(
    id: VideoId,
    title: VideoTitle
) extends Message {
  override val subType: String = "video_updated"
}
