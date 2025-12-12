package com.mysticbyte.memecreatorapp.meme_editor.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mysticbyte.memecreatorapp.core.presentation.MemeTemplate

import com.mysticbyte.memecreatorapp.core.theme.MemeCreatorTheme
import com.mysticbyte.memecreatorapp.meme_editor.presentation.components.BottomBar
import com.mysticbyte.memecreatorapp.meme_editor.presentation.components.DraggableContainer
import com.mysticbyte.memecreatorapp.meme_editor.presentation.components.MemeTextBox
import memecreatorapp.composeapp.generated.resources.Res
import memecreatorapp.composeapp.generated.resources.meme_template_01
import org.jetbrains.compose.resources.painterResource

import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MemeEditorRoot(
    template: MemeTemplate,
    viewModel: MemeEditorViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MemeEditorScreen(
        template = template,
        state = state,
        onAction = viewModel::onAction
    )


}

@Composable
fun MemeEditorScreen(
    template: MemeTemplate,
    state: MemeEditorState,
    onAction: (MemeEditorAction) -> Unit,
) {

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    onAction(MemeEditorAction.OnTapOutsideSlectedText)
                }
            },
        bottomBar = {

            BottomBar(
                onAddTextClick = {
                    onAction(MemeEditorAction.OnAddTextClick)
                },
                onSaveTextClick = {
                    onAction(MemeEditorAction.OnSaveMemeClick(template))
                }
            )

        }

    ) { innerPadding ->

        Box(

            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ){
            Box {


                Image(
                    painter = painterResource(template.drawable),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                        .onSizeChanged{
                            onAction(MemeEditorAction.OnContainerSizeChange(it))
                        },
                    contentScale = ContentScale.FillWidth
                )
                DraggableContainer(
                    children = state.memeTexts,
                    textBoxInteractionState = state.textBoxInteractionState,
                    onChildTransformChanged = { id, offset, rotation, scale ->
                        onAction(MemeEditorAction.OnMemeTextTransformChange(
                            id = id,
                            offset = offset,
                            rotation = rotation,
                            scale = scale
                        ))
                    },
                    onChildClick = {
                        onAction(MemeEditorAction.OnSelectMemeText(it))
                    },
                    onChildDoubleClick = {
                        onAction(MemeEditorAction.OnEditMemeText(it))
                    },
                    onChildTextChange = { id, text ->
                        onAction(MemeEditorAction.OnMemeTextChange(id, text))
                    },
                    onChildDeleteClick = {
                        onAction(MemeEditorAction.OnDeleteMemeTextClick(it))
                    },
                    modifier = Modifier.matchParentSize()
                )
            }
        }
    }

}

@Preview
@Composable
private fun Preview() {
    MemeCreatorTheme {
        MemeEditorScreen(
            template = MemeTemplate(
                id = "meme_template_01",
                drawable = Res.drawable.meme_template_01
            ),
            state = MemeEditorState(),
            onAction = {}
        )
    }
}