@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.mysticbyte.memecreatorapp.meme_editor.data

import androidx.compose.ui.unit.IntSize
import com.mysticbyte.memecreatorapp.meme_editor.domain.MemeExporter
import com.mysticbyte.memecreatorapp.meme_editor.domain.SaveToStorageStrategy
import com.mysticbyte.memecreatorapp.meme_editor.presentation.MemeText

expect class PlatformMemeExporter: MemeExporter {

    override suspend fun exportMeme(
        backgroundImageByte: ByteArray,
        memeTexts: List<MemeText>,
        templateSize: IntSize,
        saveToStorageStrategy: SaveToStorageStrategy,
        fileName: String
    )



}