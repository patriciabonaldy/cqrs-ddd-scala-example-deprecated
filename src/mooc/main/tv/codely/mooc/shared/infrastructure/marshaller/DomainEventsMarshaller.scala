package tv.codely.mooc.shared.infrastructure.marshaller

import spray.json.{DeserializationException, JsString, JsValue, RootJsonFormat, SerializationException, _}
import tv.codely.mooc.user.domain.UserRegistered
import tv.codely.mooc.user.infrastructure.marshaller.UserRegisteredJsonFormatMarshaller._
import tv.codely.mooc.video.domain.{VideoCreated, VideoUpdateTitle}
import tv.codely.mooc.video.infrastructure.marshaller.VideoCreatedJsonFormatMarshaller._
import tv.codely.mooc.video.infrastructure.marshaller.VideoUpdatedJsonFormatMarshaller._
import tv.codely.shared.domain.bus.Message

object DomainEventsMarshaller {
  implicit object MessageMarshaller extends RootJsonFormat[Message] {
    override def write(m: Message): JsValue = m match {
      case vc:  VideoCreated      => vc.toJson
      case vup: VideoUpdateTitle  => vup.toJson
      case ur:  UserRegistered    => ur.toJson
      case unknown                => throw new SerializationException(s"Unknown message type to write <${unknown.getClass}>")
    }

    override def read(jv: JsValue): Message = jv.asJsObject.getFields("type") match {
      case Seq(JsString("cqrs_ddd_scala_example.video_created"))   => jv.convertTo[VideoCreated]
      case Seq(JsString("cqrs_ddd_scala_example.video_updated"))   => jv.convertTo[VideoUpdateTitle]
      case Seq(JsString("cqrs_ddd_scala_example.user_registered")) => jv.convertTo[UserRegistered]
      case Seq(JsString(unknown)) =>
        throw DeserializationException(s"Unknown message type to read <$unknown>")
    }
  }
}
