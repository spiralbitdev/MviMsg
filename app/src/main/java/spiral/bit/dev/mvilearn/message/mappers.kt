package spiral.bit.dev.mvilearn.message

import spiral.bit.dev.mvilearn.message.store.MessageStore
import spiral.bit.dev.mvilearn.message.view.MessageView

internal fun MessageStore.State.stateToModel() = MessageView.Model(message)

internal fun MessageView.Event.eventToIntent(): MessageStore.Intent {
    return when (this) {
        is MessageView.Event.SendClicked -> MessageStore.Intent.SendClicked(msg)
    }
}