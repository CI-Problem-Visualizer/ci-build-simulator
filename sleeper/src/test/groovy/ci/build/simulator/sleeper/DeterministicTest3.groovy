package ci.build.simulator.sleeper

import org.junit.jupiter.api.Test

class DeterministicTest3 {
    @Test void t() {
        Thread.sleep(466)
        assert 1 == 1
    }
}