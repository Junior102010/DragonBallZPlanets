package com.edu.ucne.dragonballzplanets.Presentation.Navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class TopLevelRoute<T : Any>(
    val nombre: String,
    val ruta: T,
    val icono: ImageVector
)
