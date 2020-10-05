package tv.codely.mooc.video.application.update

import tv.codely.mooc.video.domain.{Video, VideoMother, VideoTitle}
import tv.codely.mooc.video.infrastructure.repository.VideoRepositoryMock
import tv.codely.shared.infrastructure.rabbitmq.MessagePublisherMock
import tv.codely.shared.infrastructure.unit.UnitTestCase

class VideoTitleUpdaterShould extends UnitTestCase with VideoRepositoryMock with MessagePublisherMock {

  private val updater = new VideoTitleUpdater(videoFinder, messagePublisher)

  "update existing videos" in {
    val oldVideo        = VideoMother.random
    val title = VideoTitle("nuevo titulo")
    val video = Video(oldVideo.id,title, oldVideo.duration, oldVideo.category, oldVideo.creatorId )

    videoFinderShouldFindById(oldVideo)

    updater.update(oldVideo.id, title).title.value shouldBe video.title.value
  }

}
