package com.mysticbyte.memecreatorapp.di

import com.mysticbyte.memecreatorapp.meme_editor.presentation.MemeEditorViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(
        ::MemeEditorViewModel
    )
}