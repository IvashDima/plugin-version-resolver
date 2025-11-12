import com.example.Application
import com.example.model.Arch
import com.example.model.OS
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.http.HttpStatus
import org.assertj.core.api.Assertions.assertThat


@SpringBootTest(
    classes = [Application::class],
    webEnvironment = WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
class PluginControllerIT(@Autowired val rest: TestRestTemplate) {

    @Test
    fun `resolve endpoint returns 200`() {
        // sample data loader creates plugin "wsl-support" in app
        val url = "/api/plugins/wsl-support/resolve?os=${OS.WINDOWS}&arch=${Arch.X64}"
        val entity = rest.getForEntity(url, String::class.java)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("1.1.0") // depending on sample data
    }
}
