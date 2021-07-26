package spiral.bit.dev.mvilearn.message.view

import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.arkivanov.mvikotlin.core.view.BaseMviView
import spiral.bit.dev.mvilearn.R
import spiral.bit.dev.mvilearn.message.view.MessageView.Event
import spiral.bit.dev.mvilearn.message.view.MessageView.Model

@RequiresApi(Build.VERSION_CODES.P)
class MessageViewImpl(root: View) : BaseMviView<Model, Event>(), MessageView {

    private val editText = root.requireViewById<EditText>(R.id.edit_text_enter_msg)

    init {
        root.requireViewById<Button>(R.id.btn_send_message).setOnClickListener {
            dispatch(Event.SendClicked(editText.text.toString()))
        }
    }
}