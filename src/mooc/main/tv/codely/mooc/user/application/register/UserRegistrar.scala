package tv.codely.mooc.user.application.register

import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.mooc.user.domain._
import tv.codely.shared.domain.bus.MessagePublisher
import tv.codely.mooc.shared.infrastructure.marshaller.DomainEventsMarshaller.MessageMarshaller

final class UserRegistrar(repository: UserRepository, publishers: Seq[MessagePublisher]) {
  def register(id: UserId, name: UserName): Unit = {
    val user = User(id, name)

    repository.save(user)

    for (publisher <- publishers) {
      publisher.publish(UserRegistered(user))(MessageMarshaller)
    }
  }
}
