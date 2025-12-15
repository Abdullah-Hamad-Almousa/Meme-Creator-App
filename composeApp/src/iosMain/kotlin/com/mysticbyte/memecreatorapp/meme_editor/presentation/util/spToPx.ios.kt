package com.mysticbyte.memecreatorapp.meme_editor.presentation.util

import androidx.compose.ui.unit.TextUnit
import platform.UIKit.UIScreen

actual fun TextUnit.toPx(): Float {
    return this.value * UIScreen.mainScreen.scale.toFloat()
}