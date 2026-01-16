package com.example.core.designsystem.components.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


/**
 * Composable App Button that can be customized with different styles and colors.
 * @param onClick Lambda function to be invoked when the button is clicked.
 * @param text Text to be displayed on the button.
 * @param buttonStyle Style of the button defining padding, radius, text style, etc.
 * @param buttonColorStyle Color style of the button defining container and content colors.
 * @param enable Boolean flag to enable or disable the button. Default is true.
 * @param leadingIcon Optional composable for the leading icon.
 * @param trailingIcon Optional composable for the trailing icon.
 */
@Composable
fun AppButton(
    onClick: () -> Unit,
    text: String,
    buttonStyle: ButtonStyle,
    buttonColorStyle: ButtonColorStyle,
    enable: Boolean = true,
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
) {
    val interactionSource = remember {  MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()

    Button(
        onClick = onClick,
        enabled = enable,
        shape = RoundedCornerShape(buttonStyle.radius),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColorStyle.containerColor(isPressed.value),
            contentColor = buttonColorStyle.contentColor(),
            disabledContentColor = buttonColorStyle.disableContainerColor(),
            disabledContainerColor = buttonColorStyle.disableContainerColor()
        ),
        border = buttonColorStyle.borderStroke(),
        contentPadding = buttonStyle.paddingValues
    ) {
        if(leadingIcon != null) {
            Box(
                modifier = Modifier.size(buttonStyle.iconSize),
                contentAlignment = Alignment.Center
            ) {
                leadingIcon()
            }
        }
        if(leadingIcon != null && text.isNotEmpty()) {
            Spacer(modifier = Modifier.width(buttonStyle.iconSpacing))
        }
        Text(
            text = text,
            style = buttonStyle.textStyle.copy(
                color = if(enable) buttonColorStyle.contentColor()
                else buttonColorStyle.disableContainerColor()
            )
        )
        if(trailingIcon != null && text.isNotEmpty()) {
            Spacer(modifier = Modifier.width(buttonStyle.iconSpacing))
        }
        if(trailingIcon != null) {
            Box(
                modifier = Modifier.size(buttonStyle.iconSize),
                contentAlignment = Alignment.Center
            ) {
                trailingIcon()
            }
        }
    }

}