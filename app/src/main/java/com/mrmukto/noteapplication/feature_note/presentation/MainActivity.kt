package com.mrmukto.noteapplication.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mrmukto.noteapplication.ui.theme.SmartNoteappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartNoteappTheme {

                
            }
        }
    }
}
