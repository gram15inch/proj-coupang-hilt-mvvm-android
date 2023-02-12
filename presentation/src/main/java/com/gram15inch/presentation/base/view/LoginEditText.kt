package com.gram15inch.presentation.base.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.ViewCoupangEditTextBinding


@BindingAdapter("textLive")
fun bindText(view: EditText, str: String?) {
    if (str != null)
        if (view.text.toString() != str) {
            view.setText(str)
        }
}

@InverseBindingAdapter(attribute = "textLive", event = "android:textAttrChanged")
fun inBindText(view: EditText): String {
    return view.text.toString()
}

class LoginEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    val binding =
        ViewCoupangEditTextBinding.inflate(LayoutInflater.from(context), this, true)

    val editTextValue = MutableLiveData("")
    private lateinit var label: String
    private lateinit var hint: String
    private var endIconType = 0
    private var isPasswordShow = false

    init {

        if (attrs != null && context != null) {

            // attr.xml파일 declare-styleable이 CustomView로 정의된 attr(속성)을 typeArray객체로 받아온다.
            val typedArr = context.obtainStyledAttributes(attrs, R.styleable.LoginEditText)

            // format을 구분하여 속성값 참조
            label = typedArr.getString(R.styleable.LoginEditText_label) ?: ""
            hint = typedArr.getString(R.styleable.LoginEditText_hintText) ?: ""
            endIconType = typedArr.getInt(R.styleable.LoginEditText_endIconType, 0)
        }
        binding.lifecycleOwner = findViewTreeLifecycleOwner()
        binding.viewCpLabel.text = label
        binding.viewCpEdit.hint = hint
        binding.viewCpEdit.textCursorDrawable =
            ContextCompat.getDrawable(context, R.drawable.cl_login_cursor)

        setContainer()
        setEndIcon()
    }

    private fun setContainer() {
        val shape: LayerDrawable =
            ContextCompat.getDrawable(
                context,
                R.drawable.bg_login_out_line
            ) as LayerDrawable
        val gradient: GradientDrawable =
            shape.findDrawableByLayerId(R.id.bg_cp_outline) as (GradientDrawable)
        gradient.setStroke(3, ContextCompat.getColor(context, R.color.grey919FAC))

        //초기화
        binding.viewCpContainer.background = gradient

        binding.viewCpEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                gradient.setStroke(3, ContextCompat.getColor(context, R.color.light_blue))
            } else {
                gradient.setStroke(3, ContextCompat.getColor(context, R.color.grey919FAC))
            }
            binding.viewCpContainer.background = gradient
        }

    }

    private fun setEndIcon() {
        when (endIconType) {
            1 -> {
                setClearText()
            }
            2 -> {
                setPassword()
            }
        }
    }

    private fun setClearText() {
        binding.viewCpEndIc.setImageDrawable(
            resources.getDrawable(R.drawable.ic_clear)
        )

        binding.viewCpEndIc.visibility = View.INVISIBLE
        binding.viewCpEndIc.setOnClickListener {


            if (editTextValue.value?.isNotEmpty() ?: false) {

                editTextValue.value = ""
                binding.viewCpEdit.setText("")
                binding.viewCpEndIc.visibility = View.INVISIBLE
            }
        }

        binding.viewCpEdit.doOnTextChanged { text, start, before, count ->

            editTextValue.postValue(text.toString())
            if (binding.viewCpEndIc.visibility != View.VISIBLE) {
                binding.viewCpEndIc.visibility = View.VISIBLE
            }
        }
    }

    private fun setPassword() {
        binding.viewCpEdit.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        binding.viewCpEndIc.setImageDrawable(
            resources.getDrawable(R.drawable.ic_eye_close)
        )
        //초기화
        binding.viewCpEdit.inputType =
            InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT

        //password toggle
        binding.viewCpEndIc.setOnClickListener {
            isPasswordShow = !isPasswordShow
            when (isPasswordShow) {
                true -> {
                    binding.viewCpEndIc.setImageDrawable(
                        resources.getDrawable(R.drawable.ic_eye_open)
                    )
                    binding.viewCpEdit.inputType=  InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    binding.viewCpEdit.setSelection(binding.viewCpEdit.text.length)
                }
                false -> {
                    binding.viewCpEndIc.setImageDrawable(
                        resources.getDrawable(R.drawable.ic_eye_close)
                    )
                    binding.viewCpEdit.inputType =
                        InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
                    binding.viewCpEdit.setSelection(binding.viewCpEdit.text.length)

                }
            }

        }
        binding.viewCpEdit.doOnTextChanged { text, start, before, count ->
            editTextValue.postValue(text.toString())
        }
    }


}