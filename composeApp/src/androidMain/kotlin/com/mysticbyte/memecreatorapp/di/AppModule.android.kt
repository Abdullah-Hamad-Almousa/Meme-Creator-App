package com.mysticbyte.memecreatorapp.di

import com.mysticbyte.memecreatorapp.meme_editor.data.CacheStorageStrategy
import com.mysticbyte.memecreatorapp.meme_editor.data.PlatformMemeExporter
import com.mysticbyte.memecreatorapp.meme_editor.domain.MemeExporter
import com.mysticbyte.memecreatorapp.meme_editor.domain.SaveToStorageStrategy
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformAppModule = module {
    factoryOf(::PlatformMemeExporter) bind MemeExporter::class
    factoryOf(::CacheStorageStrategy) bind SaveToStorageStrategy::class
}