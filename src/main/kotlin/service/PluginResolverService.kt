package service

import com.example.model.Arch
import com.example.model.OS
import com.example.model.OSVariant
import model.PluginVersion
import com.example.repository.PluginRepository
import exception.PluginNotFoundException
import org.springframework.stereotype.Service

@Service
class PluginResolverService(private val repo: PluginRepository) {

    /**
     * Search the best PluginVersion for pluginId & client OS/Arch.
     * Rules:
     * 1) exact coincidence OS+Arch (take the last of releaseDate/version)
     * 2) if no — take any universal (osVariants.isEmpty())
     * 3) else — return null
     */
    fun resolve(pluginId: String, os: OS, arch: Arch): PluginVersion? {
        val plugin = repo.findById(pluginId) ?: throw PluginNotFoundException("Plugin $pluginId not found") //return null
        println("1. Resolving plugin $pluginId for $os/$arch")

        // 1) Try to find exact coincidence
        val exact = plugin.versions
            .filter { it.osVariants.contains(OSVariant(os, arch)) }
            .maxByOrNull { it.releaseDate } // или by version compare
        if (exact != null) return exact
        println("2. Resolving plugin $pluginId for $os/$arch")

        // 2) Fallback on universal version (legacy)
        val universal = plugin.versions
            .filter { it.isUniversal() }
            .maxByOrNull { it.releaseDate }
        if (universal != null) return universal
        println("3. Resolving plugin $pluginId for $os/$arch")

        // 3) no matching versions
        return null
    }
}
