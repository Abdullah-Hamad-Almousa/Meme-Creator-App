package com.mysticbyte.memecreatorapp.meme_editor.presentation.util

import platform.Foundation.NSURL
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

actual class PlatformShareSheet {
    actual fun shareFile(filePath: String) {

        val fileUrl = NSURL.fileURLWithPath(filePath)

        val itemToShare = listOf(fileUrl)

        val activityViewController = UIActivityViewController(
            activityItems = itemToShare,
            applicationActivities = null
        )

        val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
            ?: throw IllegalStateException("No root view controller found!")

        rootViewController.presentViewController(
            viewControllerToPresent = activityViewController,
            animated = true,
            completion = null
        )

    }


}