package com.mysticbyte.memecreatorapp.meme_editor.presentation

data class MemeText(
    val id: String,
    val text: String,
    val fontSize: Float = 36f,
    val offsetRatioX: Float = 0f,
    val offsetRatioY: Float = 0f,
    val roation: Float = 0f,
    val scale: Float = 1f
)
