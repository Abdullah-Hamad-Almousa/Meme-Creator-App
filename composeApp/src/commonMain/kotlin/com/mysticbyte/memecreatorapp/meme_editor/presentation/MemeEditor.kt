package com.mysticbyte.memecreatorapp.meme_editor.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mysticbyte.memecreatorapp.core.presentation.MemeTemplate

import com.mysticbyte.memecreatorapp.core.theme.MemeCreatorTheme
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

    Box(

        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        Image(
            painter = painterResource(template.drawable),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        MemeTextBox(
            memeText = MemeText(
                id = "hello",
                text = "TAP TO EDIT",
            ),
            textBoxInteractionState = state.textBoxInteractionState,
            maxWidth = 500.dp,
            maxHeight = 500.dp,
            onClick = {
                onAction(MemeEditorAction.OnSelectMemeText("hello"))
            },
            onDoubleClick = {
                onAction(MemeEditorAction.OnEditMemeText("hello"))
            },
            onTextChange = {
                onAction(MemeEditorAction.OnMemeTextChange("hello", it))
            },
            onDeleteClick = {
                onAction(MemeEditorAction.OnDeleteMemeTextClick("hello"))
            }
        )
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