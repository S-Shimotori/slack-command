package net.terminal_end

import com.amazonaws.services.lambda.runtime.Context
import com.beust.klaxon.json

/**
 * Created by S-Shimotori on 6/7/17.
 */

fun elect(input: Map<String, Any>, context: Context): Map<String, Any> {
    return mapOf(
            "statusCode" to 200,
            "headers" to mapOf<String, Any>(),
            "body" to json { obj(
                    "text" to "ok"
            )}.toJsonString()
    )
}