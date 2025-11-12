import com.example.model.*
import com.example.repository.PluginRepository
import com.example.repository.InMemoryPluginRepository
import model.PluginVersion
import service.PluginResolverService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PluginResolverServiceTest {

    private val repo: PluginRepository = InMemoryPluginRepository()
    private val service = PluginResolverService(repo)

    @Test
    fun `should pick exact variant when exists`() {
        val plugin = Plugin(
            id = "p1",
            name = "p1",
            versions = listOf(
                PluginVersion("1.0.0", osVariants = listOf(OSVariant(OS.WINDOWS, Arch.X64))),
                PluginVersion("2.0.0", osVariants = listOf(OSVariant(OS.LINUX, Arch.X64)))
            )
        )
        repo.save(plugin)
        val found = service.resolve("p1", OS.WINDOWS, Arch.X64)
        assertNotNull(found)
        assertEquals("1.0.0", found!!.version)
    }

    @Test
    fun `should fallback to universal if no exact`() {
        val plugin = Plugin(
            id = "p2",
            name = "p2",
            versions = listOf(
                PluginVersion("1.0.0"),
                PluginVersion("1.1.0", osVariants = listOf(OSVariant(OS.LINUX, Arch.X64)))
            )
        )
        repo.save(plugin)
        val found = service.resolve("p2", OS.MAC, Arch.X64)
        assertNotNull(found)
        assertEquals("1.0.0", found!!.version)
    }

    @Test
    fun `returns null when nothing matches`() {
        val plugin = Plugin(
            id = "p3",
            name = "p3",
            versions = listOf(
                PluginVersion("0.1.0", osVariants = listOf(OSVariant(OS.LINUX, Arch.X64)))
            )
        )
        repo.save(plugin)
        val found = service.resolve("p3", OS.WINDOWS, Arch.X64)
        assertNull(found)
    }
}
