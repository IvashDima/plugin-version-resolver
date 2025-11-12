package model

import com.example.model.OSVariant

data class PluginVersion(
    val version: String,
    val releaseDate: Long = System.currentTimeMillis(),
    val osVariants: List<OSVariant> = emptyList()
) {
    fun isUniversal(): Boolean = osVariants.isEmpty()
}