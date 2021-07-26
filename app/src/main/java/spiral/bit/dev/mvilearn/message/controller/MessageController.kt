package spiral.bit.dev.mvilearn.message.controller

import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import spiral.bit.dev.mvilearn.message.eventToIntent
import spiral.bit.dev.mvilearn.message.stateToModel
import spiral.bit.dev.mvilearn.message.store.MessageStoreFactory
import spiral.bit.dev.mvilearn.message.view.MessageView

@ExperimentalCoroutinesApi
class MessageController(viewLifecycle: Lifecycle) {

    private val store = MessageStoreFactory(DefaultStoreFactory).create()

    init {
        viewLifecycle.doOnDestroy(store::dispose)
    }

    fun onViewCreated(view: MessageView, viewLifecycle: Lifecycle) {
        bind(viewLifecycle, BinderLifecycleMode.START_STOP) {
            store.states.map { it.stateToModel() } bindTo view
            view.events.map { it.eventToIntent() } bindTo store
        }
    }
}