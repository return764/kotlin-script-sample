package org.example

import java.io.File
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.SourceCode
import kotlin.script.experimental.api.constructorArgs
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost

class TaskScriptHost {
    private val scriptingHost = BasicJvmScriptingHost()

    fun eval(
        sourceCodeFile: File,
        dsl: TaskDsl
    ) = eval(sourceCodeFile.toScriptSource(), dsl)

    /**
     * Evaluates the given project script [sourceCode] against the
     * given [project].
     */
    fun eval(
        sourceCode: String,
        dsl: TaskDsl
    ) = eval(sourceCode.toScriptSource(), dsl)

    /**
     * Evaluates the given project script [sourceCode] against the
     * given [project].
     */
    fun eval(
        sourceCode: SourceCode,
        dsl: TaskDsl
    ): ResultWithDiagnostics<EvaluationResult> {
        return scriptingHost.evalWithTemplate<TaskScript>(
            sourceCode,
            evaluation = {
                constructorArgs(dsl)
            }
        )
    }

}
