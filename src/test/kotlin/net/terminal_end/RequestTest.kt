package net.terminal_end

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import org.junit.Test
import org.hamcrest.MatcherAssert.*
import org.hamcrest.Matchers.*

/**
 * Created by S-Shimotori on 6/7/17.
 */

class RequestTest {
    val parser = Parser()
    fun request(fileName: String): Request? =
            Request.from(parser.parse(ClassLoader.getSystemResourceAsStream(fileName)) as JsonObject)

    @Test
    fun testRequestFrom() {
        val all = request("all.json")!!
        assertThat(all.body, `is`(mapOf<String, Any>("text" to "hoge_text", "command" to "/hoge")))
        assertThat(all.headers, `is`(mapOf("Accept" to "application/json,*/*")))
        assertThat(all.httpMethod, `is`(Request.Method.POST))
        assertThat(all.isBase64Encoded, `is`(false))
        assertThat(all.path, `is`("/hoge_path"))
        assertThat(all.resource, `is`("/hoge_resource"))

        val textEmpty = request("text_empty.json")!!
        assertThat((textEmpty.body["text"] as String).isEmpty(), `is`(true))

        val textNone = request("text_none.json")!!
        assertThat(textNone.body.containsKey("text"), `is`(false))
    }
}