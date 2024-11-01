package org.saavatech.project

import androidx.compose.ui.window.ComposeUIViewController
import org.saavatech.project.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }