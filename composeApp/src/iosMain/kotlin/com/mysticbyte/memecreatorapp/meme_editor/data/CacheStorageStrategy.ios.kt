package com.mysticbyte.memecreatorapp.meme_editor.data

import com.mysticbyte.memecreatorapp.meme_editor.domain.SaveToStorageStrategy

actual class CacheStorageStrategy :
    SaveToStorageStrategy {
    actual override fun getFilePath(fileName: String): String {
        TODO("Not yet implemented")
    }
}