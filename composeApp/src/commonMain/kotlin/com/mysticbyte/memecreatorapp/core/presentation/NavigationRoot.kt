package com.mysticbyte.memecreatorapp.core.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mysticbyte.memecreatorapp.meme_editor.presentation.MemeEditorRoot
import com.mysticbyte.memecreatorapp.meme_gallery.presentation.MemeGalleryScreen

@Composable
fun NavigatorRoot(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.MemeGallery
    ){
        composable<Route.MemeGallery>{

            MemeGalleryScreen(
                onMemeTemplateSelected = {
                    memeTemplate -> navController.navigate(
                        Route.MemeEditor(
                            memeTemplate.id
                        )
                    )
                }
            )

        }
        composable<Route.MemeEditor>{

            MemeEditorRoot()

        }
    }

}