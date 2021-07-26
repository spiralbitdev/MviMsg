package spiral.bit.dev.mvilearn.message.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import spiral.bit.dev.mvilearn.message.store.MessageStore.Intent
import spiral.bit.dev.mvilearn.message.store.MessageStore.State

internal class MessageStoreFactory(private val storeFactory: StoreFactory) {

    private sealed class Action {
        class InitialValue(val msg: String) : Action()
    }

    private sealed class Result {
        class BtnClicked(val value: String) : Result()
    }

    fun create(): MessageStore =
        object : MessageStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = "MessageStore",
            initialState = State(""),
            executorFactory = ::ExecutorImpl,
            bootstrapper = BootstrapperImpl,
            reducer = ReducerImpl
        ) {
        }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.BtnClicked -> copy(message = result.value)
            }
    }

    private class ExecutorImpl : SuspendExecutor<Intent, Action, State, Result, Nothing>() {
        override suspend fun executeAction(action: Action, getState: () -> State) =
            when (action) {
                is Action.InitialValue -> dispatch(Result.BtnClicked(action.msg))
            }

        override suspend fun executeIntent(intent: Intent, getState: () -> State) =
            when (intent) {
                is Intent.SendClicked -> dispatch(Result.BtnClicked(intent.text))
            }
    }

    private object BootstrapperImpl : SuspendBootstrapper<Action>() {
        override suspend fun bootstrap() {
            dispatch(Action.InitialValue("Initial Stroke, store created"))
        }
    }
}