package com.mysticbyte.memecreatorapp.meme_editor.presentation

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class MemeEditorViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(MemeEditorState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MemeEditorState()
        )

    fun onAction(action: MemeEditorAction) {
        when (action) {
            MemeEditorAction.OnAddTextClick -> addText()
            MemeEditorAction.OnConfirmLeaveWithoutSaving  -> TODO()
            MemeEditorAction.OnConfirmLeaveWithoutSaving -> TODO()
            is MemeEditorAction.OnContainerSizeChange -> updateContainerSize(action.size)
            is MemeEditorAction.OnDeleteMemeTextClick -> deleteMemeText(action.id)
            is MemeEditorAction.OnEditMemeText -> editMemeText(action.id)
            MemeEditorAction.OnGoBackClick -> TODO()
            is MemeEditorAction.OnMemeTextChange -> updateMemeText(action.id, action.text)
            is MemeEditorAction.OnMemeTextTransformChange -> transformMemeText(
                id = action.id,
                offset = action.offset,
                rotation = action.rotation,
                scale = action.scale
            )
            is MemeEditorAction.OnSaveMemeClick -> TODO()
            is MemeEditorAction.OnSelectMemeText -> selectMemeText(action.id)
            MemeEditorAction.OnTapOutsideSelectedText -> unselectMemeText()
            else -> 0
        }
    }

    private fun transformMemeText(
        id: String,
        offset: Offset,
        rotation: Float,
        scale: Float
    ) {

        _state.update {
            val (width, height) = it.templateSize
            it.copy(
            memeTexts = it.memeTexts.map { memeText ->

                if (memeText.id == id) {
                    memeText.copy(
                        offsetRatioX = offset.x / width,
                        offsetRatioY = offset.y / height,
                        roation = rotation,
                        scale = scale
                    )
                }
                else memeText

            }
        ) }

    }

    private fun unselectMemeText() {
        _state.update { it.copy(
            textBoxInteractionState = TextBoxInteractionState.None
        ) }
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun addText() {
        val id = Uuid.random().toString()

        val memeText = MemeText(
            id = id,
            text = "TAP TO EDIT",
            offsetRatioX = 0.25f,
            offsetRatioY = 0.25f
        )

        _state.update { it.copy(
            memeTexts = it.memeTexts + memeText,
            textBoxInteractionState = TextBoxInteractionState.Editing(id)
        ) }

    }

    private fun deleteMemeText(id: String) {
        _state.update {
            it.copy(
                memeTexts = it.memeTexts.filter { memeText ->
                    memeText.id != id})
        }
    }

    private fun selectMemeText(id: String) {

        _state.update { it.copy(
            textBoxInteractionState = TextBoxInteractionState.Selected(id)
        ) }

    }

    private fun updateMemeText(id: String, text: String) {
        _state.update {
            it.copy(
                memeTexts = it.memeTexts.map { memeText ->
                    if (memeText.id == id) {
                        memeText.copy(text = text)
                    } else memeText
                }
            )
        }
    }

    private fun editMemeText(id: String) {
        _state.update {it.copy(
            textBoxInteractionState = TextBoxInteractionState.Editing(id)
        )}
    }

    private fun updateContainerSize(size: IntSize){
        _state.update { it.copy(templateSize = size) }
    }

}