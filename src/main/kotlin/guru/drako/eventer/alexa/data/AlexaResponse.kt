package guru.drako.eventer.alexa.data

import guru.drako.eventer.alexa.data.AlexaResponse.Response.Card
import guru.drako.eventer.alexa.data.AlexaResponse.Response.OutputSpeech
import guru.drako.eventer.alexa.data.AlexaResponse.Response.OutputSpeech.Type

data class AlexaResponse(
  val version: String = "1.0",
  val response: Response = Response()
) {
  companion object {
    fun plainText(text: String, shouldEndSession: Boolean = true) = AlexaResponse(
      response = Response(
        shouldEndSession = shouldEndSession,
        outputSpeech = OutputSpeech(text = text),
        card = if (shouldEndSession) Card(content = text) else null
      )
    )

    fun speak(cardText: String, ssml: String, shouldEndSession: Boolean = true) = AlexaResponse(
      response = Response(
        shouldEndSession = shouldEndSession,
        outputSpeech = OutputSpeech(type = Type.SSML, text = null, ssml = "<speak>$ssml</speak>"),
        card = if (shouldEndSession) Card(content = cardText) else null
      )
    )
  }

  data class Response(
    val shouldEndSession: Boolean = true,
    val outputSpeech: OutputSpeech? = OutputSpeech(),
    val card: Card? = Card()
  ) {
    data class OutputSpeech(
      val type: Type = Type.PlainText,
      val text: String? = "",
      val ssml: String? = null
    ) {
      enum class Type {
        PlainText,
        SSML
      }
    }

    data class Card(
      val type: Type = Type.Simple,
      val title: String? = null,
      val content: String? = null
    ) {
      enum class Type {
        Simple
      }
    }
  }
}
