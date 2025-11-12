package api

import com.example.model.Arch
import com.example.model.OS
import service.PluginResolverService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/plugins")
class PluginController(private val resolver: PluginResolverService) {

    @GetMapping("/{pluginId}/resolve")
    fun resolve(
        @PathVariable pluginId: String,
        @RequestParam os: OS,
        @RequestParam arch: Arch
    ): ResponseEntity<Any> {
        val result = resolver.resolve(pluginId, os, arch)
        return if (result != null) ResponseEntity.ok(result) else ResponseEntity.notFound().build()
    }

}
