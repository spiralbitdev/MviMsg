package spiral.bit.dev.mvilearn.message.view

import com.arkivanov.mvikotlin.core.view.MviView
import spiral.bit.dev.mvilearn.message.view.MessageView.*

interface MessageView : MviView<Model, Event> {

    data class Model(
        val message: String
    )

    sealed class Event {
        class SendClicked(val msg: String) : Event()
    }
}