package exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ErrorResponse(val message: String)

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(PluginNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlePluginNotFound(ex: PluginNotFoundException): Map<String, Any> {
        log.info("Handling PluginNotFoundException: ${ex.message}")
        return mapOf(
            "status" to HttpStatus.NOT_FOUND.value(),
            "error" to HttpStatus.NOT_FOUND.reasonPhrase,
            "message" to (ex.message ?: "Plugin not found"),
            "timestamp" to System.currentTimeMillis()
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleOtherErrors(ex: Exception): ResponseEntity<Map<String, Any?>> {
        log.error("Unhandled exception", ex)
        val body: Map<String, Any?> = mapOf(
            "status" to HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "error" to HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            "message" to ex.message,
            "timestamp" to System.currentTimeMillis()
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body)
    }
}

class PluginNotFoundException(message: String) : RuntimeException(message)
