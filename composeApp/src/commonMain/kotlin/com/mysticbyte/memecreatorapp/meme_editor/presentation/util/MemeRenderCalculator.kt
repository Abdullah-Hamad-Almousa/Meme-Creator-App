package com.mysticbyte.memecreatorapp.meme_editor.presentation.util

class MemeRenderCalculator (

    private val density: Float,

) {

    companion object{

        private const val TEXT_PADDING_DP = 8f
        private const val STROKE_WIDTH_DP = 3f

    }

    fun calculateScaleFactors() {



    }

}

data class ScaleFactors(
    val scaleX: Float,
    val scaleY: Float,
    val bitmapScale: Float
)