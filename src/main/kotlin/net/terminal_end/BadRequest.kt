package net.terminal_end

import com.beust.klaxon.json

/**
 * Created by S-Shimotori on 6/7/17.
 */

class BadRequest(
        override var text: String,
        override var statusCode: Int = 200,   // to show Slack message
        override var responseType: Response.Type = Response.Type.EPHEMERAL
): Response {
    override fun toMap(): Map<String, Any> = mapOf(
            "statusCode" to statusCode,
            "headers" to mapOf<String, Any>(),
            "body" to json { obj(
                    "response_type" to responseType.string,
                    "text" to text
            )}.toJsonString()
    )
}