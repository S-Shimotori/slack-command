package net.terminal_end

/**
 * Created by S-Shimotori on 6/7/17.
 */

interface Response {
    var statusCode: Int
    var text: String
    var responseType: Type
    fun toMap(): Map<String, Any>

    enum class Type(val string: String) {
        EPHEMERAL("ephemeral"),
        IN_CHANNEL("in_channel")
    }
}
