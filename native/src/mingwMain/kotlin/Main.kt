fun main() {
    println("Hello World!")
    val name = readlnOrNull()
    println("Your name contains ${name?.replace(" ", "")?.length} letters")

    val graph = Array(5) {
        IntArray(5)
    }
    graph[0][0] = 1
    println(Greeting().greet())
}
