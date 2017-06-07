package net.terminal_end

import org.junit.Test
import org.hamcrest.MatcherAssert.*
import org.hamcrest.Matchers.*

/**
 * Created by S-Shimotori on 6/7/17.
 */

class MainTest {
    @Test
    fun testGetArgs() {
        assertThat(getArgs("").size, `is`(0))
        assertThat(getArgs(",").size, `is`(0))
        assertThat(getArgs(", ").size, `is`(0))
        assertThat(getArgs(", , ").size, `is`(0))

        assertThat(getArgs("a"), `is`(listOf("a")))
        assertThat(getArgs("a, b"), `is`(listOf("a", "b")))
        assertThat(getArgs("a, b b"), `is`(listOf("a", "b b")))
    }
}