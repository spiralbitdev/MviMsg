package spiral.bit.dev.mvilearn.message.view

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import spiral.bit.dev.mvilearn.R
import spiral.bit.dev.mvilearn.message.controller.MessageController

@ExperimentalCoroutinesApi
class MessageInputFragment : Fragment(R.layout.fragment_message_input) {

    private val mviLifecycle by lazy { lifecycle.asMviLifecycle() }
    private val controller = MessageController(mviLifecycle)

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.requireViewById<ConstraintLayout>(R.id.root).rootView.also {
            with(MessageViewImpl(it)) {
                controller.onViewCreated(this, mviLifecycle)
            }
        }
    }
}