package com.arrazyfathan.tudu.utils

import io.github.aakira.napier.Napier
import io.ktor.client.plugins.logging.Logger
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

val json = Json { prettyPrint = true }

fun prettyJson(jsonString: String): String =
    try {
        val jsonElement: JsonElement = json.parseToJsonElement(jsonString)
        json.encodeToString(JsonElement.serializer(), jsonElement)
    } catch (e: Exception) {
        jsonString
    }

object PrettyLogger : Logger {
    override fun log(message: String) {
        if (message.startsWith("REQUEST:") || message.startsWith("RESPONSE:")) {
            val parts = message.split("\n")
            val bodyStartIndex = parts.indexOfFirst { it.trimStart().startsWith("BODY START") }

            parts.take(bodyStartIndex).forEach { Napier.d(tag = "KtorLogger") { it } }

            if (bodyStartIndex != -1 && bodyStartIndex + 1 < parts.size) {
                val body = parts[bodyStartIndex + 1]
                Napier.d(tag = "KtorLogger") { "BODY START" }
                Napier.d(tag = "KtorLogger") { prettyJson(body) }
                Napier.d(tag = "KtorLogger") { "BODY END" }
            }
        } else {
            Napier.d(tag = "KtorLogger") { message }
        }
    }
}
