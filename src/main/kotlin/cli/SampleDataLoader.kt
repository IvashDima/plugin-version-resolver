package com.example.cli

import com.example.model.*
import com.example.repository.PluginRepository
import jakarta.annotation.PostConstruct
import model.PluginVersion
import org.springframework.stereotype.Component

@Component
class SampleDataLoader(private val repo: PluginRepository) {

    @PostConstruct
    fun load() {
        val plugin1 = Plugin(
            id = "wsl-support",
            name = "WSL Support",
            versions = listOf(
                PluginVersion("1.0.0", releaseDate = 1000L, osVariants = listOf(OSVariant(OS.WINDOWS, Arch.X64))),
                PluginVersion(
                    "1.1.0",
                    releaseDate = 2000L,
                    osVariants = listOf(OSVariant(OS.WINDOWS, Arch.X64), OSVariant(OS.WINDOWS, Arch.ARM64))
                ),
                PluginVersion("2.0.0", releaseDate = 3000L) // universal
            )
        )

        val plugin2 = Plugin(
            id = "native-tool",
            name = "Native Tool",
            versions = listOf(
                PluginVersion("0.1.0", osVariants = listOf(OSVariant(OS.LINUX, Arch.X64))),
                PluginVersion("0.2.0", osVariants = listOf(OSVariant(OS.MAC, Arch.X64)))
            )
        )

        repo.save(plugin1)
        repo.save(plugin2)
    }
}

// to check - test data:
//curl 'http://localhost:8080/api/plugins/wsl-support/resolve?os=WINDOWS&arch=X64'
//curl 'http://localhost:8080/api/plugins/wsl-support/resolve?os=WINDOWS&arch=X86'
//curl 'http://localhost:8080/api/plugins/wsl-support/resolve?os=LINUX&arch=X64'
//curl 'http://localhost:8080/api/plugins/native-tool/resolve?os=MAC&arch=X64'
//- curl 'http://localhost:8080/api/plugins/native-tool/resolve?os=WINDOWS&arch=X64'
//- curl 'http://localhost:8080/api/plugins/unknown/resolve?os=WINDOWS&arch=X64'
