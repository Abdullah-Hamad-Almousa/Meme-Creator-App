package com.mysticbyte.memecreatorapp.meme_editor.domain

interface SaveToStorageStrategy {

    fun getFilePath(fileName: String): String

}