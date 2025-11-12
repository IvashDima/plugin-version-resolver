package com.example.model

import model.PluginVersion

data class Plugin(
    val id: String,
    val name: String,
    val versions: List<PluginVersion> = emptyList()
)