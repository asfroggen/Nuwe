package com.esaudev.nuwe.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.content.res.ResourcesCompat
import com.esaudev.nuwe.R
import com.google.android.material.button.MaterialButton

class NuweFlatButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val NO_RESOURCE_SELECTED = 0
    }

    // LAYOUT COMPONENTS
    private var button: MaterialButton
    private var loader: ProgressBar

    // COMPONENTS RESOURCE VARIABLES
    private var buttonText: Int = NO_RESOURCE_SELECTED
    private var buttonIcon: Int = NO_RESOURCE_SELECTED

    private var buttonEnabled: Boolean = true
    private var isLoading: Boolean = true

    init {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.nuwe_button_flat, this, true)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.nuwe_button,
            0,0
        ).apply {
            try {
                buttonText = getResourceId(R.styleable.nuwe_button_button_text, NO_RESOURCE_SELECTED)
                buttonIcon = getResourceId(R.styleable.nuwe_button_button_icon, NO_RESOURCE_SELECTED)

                buttonEnabled = getBoolean(R.styleable.nuwe_button_button_enabled, false)
                isLoading = getBoolean(R.styleable.nuwe_button_is_loading, false)
            } finally {
                recycle()
            }
        }

        button = view.findViewById(R.id.bButton)
        loader = view.findViewById(R.id.pbButton)


        handleButtonPainting()

    }

    private fun handleButtonPainting() {

        button.text = if (buttonText == NO_RESOURCE_SELECTED) "" else resources.getText(buttonText)

        button.isEnabled = buttonEnabled

        if (buttonIcon != NO_RESOURCE_SELECTED) {
            button.icon = ResourcesCompat.getDrawable(resources, buttonIcon, null)
        }

        if (isLoading) {
            button.text = ""
            button.isEnabled = false
            button.icon = null
            loader.visibility = View.VISIBLE
        } else {
            loader.visibility = View.GONE
        }
    }


}