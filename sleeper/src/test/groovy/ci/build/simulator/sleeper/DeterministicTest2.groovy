package ci.build.simulator.sleeper

import org.junit.jupiter.api.Test

class DeterministicTest2 {
    @Test void t() {
        Thread.sleep(440)
        assert 1 == 1
    }
}