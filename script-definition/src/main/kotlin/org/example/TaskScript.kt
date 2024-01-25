package org.example

import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.ScriptAcceptedLocation
import kotlin.script.experimental.api.ScriptCompilationConfiguration
import kotlin.script.experimental.api.ScriptEvaluationConfiguration
import kotlin.script.experimental.api.acceptedLocations
import kotlin.script.experimental.api.defaultImports
import kotlin.script.experimental.api.ide
import kotlin.script.experimental.api.refineConfiguration
import kotlin.script.experimental.api.scriptsInstancesSharing
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm

@KotlinScript(
    // File extension for the script type
    fileExtension = "task.kts",
    // Compilation configuration for the script type
    compilationConfiguration = TaskScriptCompilationConfiguration::class,
    evaluationConfiguration = TaskScriptEvaluationConfiguration::class,
)
abstract class TaskScript(taskDsl: TaskDsl): TaskDsl by taskDsl

interface TaskDsl {
    var name: String
}

class TaskScriptCompilationConfiguration: ScriptCompilationConfiguration({
    // Implicit imports for all scripts of this type
    jvm {
        // Extract the whole classpath from context classloader and use it as dependencies
        dependenciesFromCurrentContext(
            wholeClasspath = true
        )
    }
    ide {
        acceptedLocations(ScriptAcceptedLocation.Everywhere)
    }
    // Callbacks
    refineConfiguration {
    }
})

class TaskScriptEvaluationConfiguration: ScriptEvaluationConfiguration({
    scriptsInstancesSharing(true)
})
