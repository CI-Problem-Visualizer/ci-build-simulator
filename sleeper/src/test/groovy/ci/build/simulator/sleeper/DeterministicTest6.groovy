package ci.build.simulator.sleeper

import org.junit.jupiter.api.Test

class DeterministicTest6 {
    @Test void t() {
        Thread.sleep(688)
        assert 1 == 1
    }
}