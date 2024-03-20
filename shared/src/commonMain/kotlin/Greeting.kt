import co.touchlab.kermit.Logger
import co.touchlab.kermit.loggerConfigInit
import co.touchlab.kermit.platformLogWriter

class Greeting {
    private val log = Logger(loggerConfigInit(platformLogWriter()), "Greeting")
    private val platform = getPlatform()

    fun greet(): String {
        log.i { "This is logging in shared module!!!" }
        return "Hello, ${platform.name}!"
    }

    fun grep(lines: List<String>, pattern: String, action: (String) -> Unit) {
        val regex = pattern.toRegex()
        lines.filter(regex::containsMatchIn)
            .forEach(action)
    }
}
