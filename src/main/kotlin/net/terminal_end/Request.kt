package net.terminal_end

import java.net.URLDecoder

/**
 * Created by S-Shimotori on 6/7/17.
 */

class Request private constructor (
        val resource: String,
        val path: String,
        val httpMethod: Method,
        val headers: Map<String, String>,
        val body: Map<String, Any>,
        val isBase64Encoded: Boolean
) {
    enum class Method {
        GET,
        POST
    }

    companion object {
        fun from(map: Map<String, Any?>): Request? {
            return try {
                val resource = map["resource"] as String
                val path = map["path"] as String
                val httpMethod = Method.valueOf(map["httpMethod"] as String)
                val headers = mutableMapOf<String, String>()
                (map["headers"] as Map<String, Any>).forEach {
                    headers[it.key] = it.value as String
                }
                val body = mutableMapOf<String, Any>()
                (map["body"] as String).split("&").map { it.split("=") }.filter { it.size == 2 }.forEach {
                    body[it[0]] = if (it[1] is String) {
                        URLDecoder.decode(it[1], "UTF-8")
                    } else {
                        it[1]
                    }
                }
                val isBase64Encoded = map["isBase64Encoded"]!! as Boolean

                Request(resource, path, httpMethod, headers, body, isBase64Encoded)
            } catch(e: Exception) {
                null
            }
        }
    }
}