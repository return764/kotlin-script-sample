package org.example

import java.nio.file.Paths

class DefaultTask(override var name: String) : TaskDsl {

}

fun main() {
    val dir = System.getProperty("user.dir")
    val file = Paths.get(dir, "test.task.kts")

    val task = DefaultTask(name = "before name")

    println("before: ${task.name}")
    val host = TaskScriptHost()
    val result = host.eval(file.toFile(), task)
    println("after: ${task.name}")
    println(result) // error occur
}
