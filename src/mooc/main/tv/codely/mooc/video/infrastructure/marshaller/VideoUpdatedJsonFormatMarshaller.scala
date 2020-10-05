package tv.codely.mooc.video.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat, _}
import tv.codely.mooc.video.domain._
import tv.codely.mooc.video.infrastructure.marshaller.VideoAttributesJsonFormatMarshaller._

object VideoUpdatedJsonFormatMarshaller extends DefaultJsonProtocol {

  implicit object VideoUpdatedJsonFormat extends RootJsonFormat[VideoUpdateTitle] {
    override def write(c: VideoUpdateTitle): JsValue = JsObject(
      "type"                -> JsString(c.`type`),
      "id"                  -> c.id.toJson,
      "title"               -> c.title.toJson
    )

    override def read(value: JsValue): VideoUpdateTitle =
      value.asJsObject.getFields("id", "title") match {
        case Seq(JsString(id), JsString(title)) =>
          VideoUpdateTitle(id, title)
        case unknown =>
          throw DeserializationException(
            s"Error reading VideoUpdateTitle JSON <$unknown>"
          )
      }
  }

}
