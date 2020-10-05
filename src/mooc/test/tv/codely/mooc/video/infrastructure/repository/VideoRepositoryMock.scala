package tv.codely.mooc.video.infrastructure.repository

import org.scalamock.scalatest.MockFactory
import tv.codely.mooc.video.domain.{Video, VideoFinder, VideoRepository}

import scala.concurrent.Future
import tv.codely.shared.infrastructure.unit.UnitTestCase

protected[video] trait VideoRepositoryMock extends MockFactory {
  this: UnitTestCase => // Make mandatory to also extend UnitTestCase in order to avoid using mocks in any other kind of test.

  protected val repository: VideoRepository = mock[VideoRepository]
  protected val videoFinder: VideoFinder    = new VideoFinder(repository)

  protected def repositoryShouldSave(video: Video): Unit =
    (repository.save _)
      .expects(video)
      .returning(Future.unit)

  protected def repositoryShouldFind(videos: Seq[Video]): Unit =
    (repository.all _)
      .expects()
      .returning(Future.successful(videos))

  protected def repositoryShouldUpdate(video: Video): Unit =
    (repository.update _)
      .expects(video)
      .returning(())

  protected def videoFinderShouldFindById(video: Video): Unit = {
    val a:Option[Video] = Some(video)
    (repository.findById _)
      .expects(video.id)
      .returning(Future.successful(a))
    (repository.update _)
      .expects(video)
      .returning(())
  }

}
