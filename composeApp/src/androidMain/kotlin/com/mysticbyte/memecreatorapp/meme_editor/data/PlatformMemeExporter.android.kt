package com.mysticbyte.memecreatorapp.meme_editor.data

import android.content.Context
import androidx.compose.ui.unit.IntSize
import com.mysticbyte.memecreatorapp.meme_editor.domain.MemeExporter
import com.mysticbyte.memecreatorapp.meme_editor.domain.SaveToStorageStrategy
import com.mysticbyte.memecreatorapp.meme_editor.presentation.MemeText

actual class PlatformMemeExporter(
    private val context: Context
) : MemeExporter {
    actual override suspend fun exportMeme(
        backgroundImageByte: ByteArray,
        memeTexts: List<MemeText>,
        templateSize: IntSize,
        saveToStorageStrategy: SaveToStorageStrategy,
        fileName: String
    ) {
    }
}