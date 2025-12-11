package com.mysticbyte.memecreatorapp

import androidx.compose.runtime.*
import com.mysticbyte.memecreatorapp.core.presentation.NavigationRoot

import com.mysticbyte.memecreatorapp.core.theme.MemeCreatorTheme
import com.mysticbyte.memecreatorapp.meme_gallery.presentation.MemeGalleryScreen

import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MemeCreatorTheme {
        NavigationRoot()
    }
}