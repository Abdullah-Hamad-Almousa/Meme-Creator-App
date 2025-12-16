@file:OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)

package com.mysticbyte.memecreatorapp.meme_editor.data

import androidx.compose.ui.unit.IntSize

import com.mysticbyte.memecreatorapp.meme_editor.presentation.util.ScaledMemeText
import com.mysticbyte.memecreatorapp.meme_editor.presentation.util.MemeRenderCalculator
import com.mysticbyte.memecreatorapp.meme_editor.presentation.MemeText
import com.mysticbyte.memecreatorapp.meme_editor.domain.SaveToStorageStrategy
import com.mysticbyte.memecreatorapp.meme_editor.domain.MemeExporter

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.useContents
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import platform.CoreGraphics.CGContextRef
import platform.CoreGraphics.CGContextRestoreGState
import platform.CoreGraphics.CGContextRotateCTM
import platform.CoreGraphics.CGContextSaveGState
import platform.CoreGraphics.CGContextScaleCTM
import platform.CoreGraphics.CGContextTranslateCTM
import platform.CoreGraphics.CGFloat
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSData
import platform.Foundation.NSNumber
import platform.Foundation.NSString
import platform.Foundation.create
import platform.Foundation.writeToFile
import platform.UIKit.NSFontAttributeName
import platform.UIKit.NSForegroundColorAttributeName
import platform.UIKit.NSLineBreakByWordWrapping
import platform.UIKit.NSMutableParagraphStyle
import platform.UIKit.NSParagraphStyleAttributeName
import platform.UIKit.NSStrokeColorAttributeName
import platform.UIKit.NSStrokeWidthAttributeName
import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIColor
import platform.UIKit.UIFont
import platform.UIKit.UIGraphicsBeginImageContextWithOptions
import platform.UIKit.UIGraphicsEndImageContext
import platform.UIKit.UIGraphicsGetCurrentContext
import platform.UIKit.UIGraphicsGetImageFromCurrentImageContext
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import platform.UIKit.UIScreen
import platform.UIKit.boundingRectWithSize
import platform.UIKit.drawWithRect
import kotlin.math.PI

actual class PlatformMemeExporter : MemeExporter {
    actual override suspend fun exportMeme(
        backgroundImageByte: ByteArray,
        memeTexts: List<MemeText>,
        templateSize: IntSize,
        saveToStorageStrategy: SaveToStorageStrategy,
        fileName: String
    ): Result<String> = withContext(Dispatchers.IO){
        try {
            val backgroundImageByte = createBackgroundImage(
                imageBytes = backgroundImageByte
            )

        }
        catch (e: Exception){
            coroutineContext.ensureActive()
            Result.failure(e)
        }
    }

    private fun createBackgroundImage(imageBytes: ByteArray): UIImage? {
        val imageData = imageBytes.usePinned { pinned ->

            NSData.Companion.create(
                bytes = pinned.addressOf(0),
                length = imageBytes.size.toULong()
            )

        }

        return UIImage.imageWithData(imageData)
    }

    private fun renderMeme(
        backgroundImage: UIImage,
        memeTexts: List<MemeText>,
        templateSize: IntSize
    ): UIImage? {
        val imageSize = IntSize(
            width = backgroundImage.size.useContents { width.toInt() },
            height = backgroundImage.size.useContents { height.toInt() }
        )

        UIGraphicsBeginImageContextWithOptions(
            CGSizeMake(imageSize.width.toDouble(), imageSize.height.toDouble()),
            false,
            0.0
        )

        val context = UIGraphicsGetCurrentContext()
        if (context == null) {
            UIGraphicsEndImageContext()
            return null
        }

        backgroundImage.drawInRect(
            CGRectMake(
                x = 0.0,
                y = 0.0,
                width = imageSize.width.toDouble(),
                height = imageSize.height.toDouble()
            )
        )
    }

}