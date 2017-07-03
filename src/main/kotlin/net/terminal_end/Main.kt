package net.terminal_end

import com.amazonaws.services.lambda.runtime.Context
import java.util.Random
import org.apache.log4j.Logger

/**
 * Created by S-Shimotori on 6/7/17.
 */

private val logger = Logger.getLogger("MainKt")

fun handler(input: Map<String, Any>, context: Context): Map<String, Any> {
    val request = Request.from(input) ?: return InternalServerError().toMap()

    val resource = request.resource
    val text = request.body["text"] as? String
    logger.info("resource: $resource, text: $text")
    return when (resource) {
        "/elect" -> {
            val result = elect(text)
            if (result != null) {
                OK("🎉 $result 👏").toMap()
            } else {
                BadRequest("usage: `/elect 働いて欲しいやつ1, 働いて欲しいやつ2, ...`").toMap()
            }
        }
        else -> InternalServerError().toMap()
    }
}

internal fun getArgs(text: String): List<String> = text.split(",")
        .map { it.trim() }
        .filter { it.isNotEmpty() }

private fun elect(text: String?): String? {
    if (text == null) {
        return null
    }
    val args = getArgs(text)
    logger.info("args: $args")
    if (args.isEmpty()) {
        return null
    }
    val random = Random()
    random.nextInt()
    val result = args[random.nextInt(args.size)]
    logger.info("result: $result")
    return result
}
