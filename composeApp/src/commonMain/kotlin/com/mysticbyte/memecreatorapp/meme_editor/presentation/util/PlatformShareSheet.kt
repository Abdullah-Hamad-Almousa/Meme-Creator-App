package com.mysticbyte.memecreatorapp.meme_editor.presentation.util

expect class PlatformShareSheet{
    suspend fun shareFile(filePath: String)
}