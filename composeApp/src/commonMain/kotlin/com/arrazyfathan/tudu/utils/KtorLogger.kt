package com.arrazyfathan.tudu.utils

import io.ktor.client.plugins.logging.Logger
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement


val json = Json { prettyPrint = true }

fun prettyJson(jsonString: String): String {
    return try {
        val jsonElement: JsonElement = json.parseToJsonElement(jsonString)
        json.encodeToString(JsonElement.serializer(), jsonElement)
    } catch (e: Exception) {
        jsonString
    }
}

object PrettyLogger : Logger {
    override fun log(message: String) {
        if (message.startsWith("REQUEST:") || message.startsWith("RESPONSE:")) {
            val parts = message.split("\n")
            val bodyStartIndex = parts.indexOfFirst { it.trimStart().startsWith("BODY START") }

            parts.take(bodyStartIndex).forEach { AppLogger.d("KtorLogger", it) }

            if (bodyStartIndex != -1 && bodyStartIndex + 1 < parts.size) {
                val body = parts[bodyStartIndex + 1]
                AppLogger.d("KtorLogger", "BODY START")
                AppLogger.d("KtorLogger", prettyJson(body))
                AppLogger.d("KtorLogger", "BODY END")
            }
        } else {
            AppLogger.d("KtorLogger", message)
        }
    }
}