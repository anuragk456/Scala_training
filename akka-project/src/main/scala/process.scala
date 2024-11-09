import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._


case class messageClass(message: String, messageKey: String)

object JsonFormats {
  implicit val processMessageFormat: RootJsonFormat[messageClass] = jsonFormat2(messageClass)

}