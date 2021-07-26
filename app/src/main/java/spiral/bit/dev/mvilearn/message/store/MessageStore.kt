package spiral.bit.dev.mvilearn.message.store

import com.arkivanov.mvikotlin.core.store.Store
import spiral.bit.dev.mvilearn.message.store.MessageStore.*

interface MessageStore : Store<Intent, State, Nothing> {

    data class State(
        val message: String
    )

    sealed class Intent {
        class SendClicked(val text: String) : Intent()
    }
}