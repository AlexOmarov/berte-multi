class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    fun grep(lines: List<String>, pattern: String, action: (String) -> Unit) {
        val regex = pattern.toRegex()
        lines.filter(regex::containsMatchIn)
            .forEach(action)
    }
}
