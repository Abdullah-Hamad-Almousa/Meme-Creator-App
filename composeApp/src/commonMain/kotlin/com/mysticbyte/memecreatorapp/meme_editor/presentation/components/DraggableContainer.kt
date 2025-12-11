package com.mysticbyte.memecreatorapp.meme_editor.presentation.components

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.mysticbyte.memecreatorapp.meme_editor.presentation.MemeEditorAction
import com.mysticbyte.memecreatorapp.meme_editor.presentation.MemeText
import com.mysticbyte.memecreatorapp.meme_editor.presentation.TextBoxInteractionState

@Composable
fun DraggableContainer(
    children: List<MemeText>,
    textBoxInteractionState: TextBoxInteractionState,
    onChildTransformChanged: (id: String, offset: Offset, rotation: Float, scale: Float) -> Unit,
    onChildClick: (String) -> Unit,
    onChildDoubleClick: (String) -> Unit,
    onChildTextChange: (id: String, text: String) -> Unit,
    onChildDeleteClick: (String) -> Unit,
    modifier: Modifier = Modifier) {

    val density = LocalDensity.current
    BoxWithConstraints {
        val parentWidth = constraints.maxWidth
        val parentHeight = constraints.maxHeight

        children.forEach { child ->
            var childWidth by remember(child.id) {
                mutableStateOf(0)
            }

            var childHeight by remember(child.id) {
                mutableStateOf(0)
            }

            val transformableState = rememberTransformableState {
                scaleChange, panChange, rotationChange ->
            }

            Box(
                modifier = Modifier
                    .onSizeChanged{
                        childWidth = it.width
                        childHeight = it.height
                    }
                    .graphicsLayer{
                        translationX = child.offsetRatioX * parentWidth
                        translationY = child.offsetRatioY * parentHeight
                        rotationZ = child.roation
                        scaleX = child.scale
                        scaleY = child.scale
                    }
                    .transformable(transformableState)
            ) {
                MemeTextBox(
                    memeText = child,
                    textBoxInteractionState = textBoxInteractionState,
                    maxWidth = with(density) {
                        parentWidth.toDp()
                    },
                    maxHeight = with(density) {
                        parentHeight.toDp()
                    },
                    onClick = {
                        onChildClick(child.id)
                    },
                    onDoubleClick = {
                        onChildDoubleClick(child.id)
                    },
                    onTextChange = {
                        onChildTextChange(child.id, it)
                    },
                    onDeleteClick = {
                        onChildDeleteClick(child.id)
                    }
                )
            }

        }

    }
}