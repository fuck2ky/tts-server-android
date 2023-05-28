package com.github.jing332.tts_server_android.model.rhino.core.type.ui

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.widget.addTextChangedListener
import com.github.jing332.tts_server_android.ui.view.MaterialTextInput

@SuppressLint("ViewConstructor")
class JTextInput(context: Context, hint: String? = null) : MaterialTextInput(context) {
    init {
        super.setHint(hint)

        editText!!.addTextChangedListener {
            mOnTextChangedListeners?.onChanged(it.toString())
        }
    }

    interface OnTextChangedListener {
        fun onChanged(text: CharSequence)
    }

    fun addTextChangedListener(listener: OnTextChangedListener) {
        editText!!.addTextChangedListener {
            listener.onChanged(it.toString())
        }
    }

    private var mOnTextChangedListeners: OnTextChangedListener? = null

    fun setOnTextChangedListener(listener: OnTextChangedListener) {
        mOnTextChangedListeners = listener
    }

}