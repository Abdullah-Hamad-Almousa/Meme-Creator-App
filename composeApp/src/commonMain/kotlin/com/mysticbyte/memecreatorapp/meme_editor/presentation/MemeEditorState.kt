package com.mysticbyte.memecreatorapp.meme_editor.presentation

import androidx.compose.ui.unit.IntSize

data class MemeEditorState(
    val templateSize: IntSize = IntSize.Zero,
    val isLeavingWithoutSaving: Boolean = false,
    val textBoxInteractionState: TextBoxInteractionState = TextBoxInteractionState.None,
    val memeTexts: List<MemeText> = listOf(),
    val hasLeftEditor: Boolean = false
    )


sealed interface TextBoxInteractionState {
    data object None : TextBoxInteractionState
    data class Selected(val textBoxId: String) : TextBoxInteractionState
    data class Editing(val textBoxId: String) : TextBoxInteractionState
}
