package com.mysticbyte.memecreatorapp.meme_editor.presentation.util

import androidx.compose.ui.unit.TextUnit

import android.content.res.Resources
import android.util.TypedValue

actual fun TextUnit.toPx(): Float {

    val metrics = Resources.getSystem().displayMetrics

    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.value,
        metrics
    )

}