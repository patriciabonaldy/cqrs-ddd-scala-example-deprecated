package tv.codely.mooc.video.application.search

import tv.codely.mooc.video.domain.{Video, VideoId}


class VideoFinder(repository: tv.codely.mooc.video.domain.VideoFinder) {
  def findById(id:VideoId): Option[Video] = repository.findById(id)
}
