package com.mysticbyte.memecreatorapp.meme_editor.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint

import androidx.compose.ui.unit.IntSize
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.withTranslation

import com.mysticbyte.memecreatorapp.meme_editor.domain.MemeExporter
import com.mysticbyte.memecreatorapp.meme_editor.domain.SaveToStorageStrategy
import com.mysticbyte.memecreatorapp.meme_editor.presentation.MemeText
import com.mysticbyte.memecreatorapp.meme_editor.presentation.util.MemeRenderCalculator

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext

import java.io.File
import java.io.FileOutputStream

actual class PlatformMemeExporter(
    private val context: Context
) : MemeExporter {

    private val memeRenderer = MemeRenderCalculator(
        density = context.resources.displayMetrics.density
    )

    actual override suspend fun exportMeme(
        backgroundImageByte: ByteArray,
        memeTexts: List<MemeText>,
        templateSize: IntSize,
        saveToStorageStrategy: SaveToStorageStrategy,
        fileName: String
    ) {

        var bitmap: Bitmap? = null
        var outputBitmap: Bitmap? = null

    }
}