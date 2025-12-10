package com.mysticbyte.memecreatorapp.meme_editor.presentation

data class MemeEditorState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)