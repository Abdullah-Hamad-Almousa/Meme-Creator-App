@file:OptIn(ExperimentalUuidApi::class)

package com.mysticbyte.memecreatorapp.meme_editor.domain

import androidx.compose.ui.unit.IntSize
import com.mysticbyte.memecreatorapp.meme_editor.presentation.MemeText
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface MemeExporter{

    suspend fun exportMeme(

        backgroundImageByte: ByteArray,
        memeTexts: List<MemeText>,
        templateSize: IntSize,
        saveToStorageStrategy: SaveToStorageStrategy,
        fileName: String = "meme_${Uuid.random()}.jpg"

    )

}
