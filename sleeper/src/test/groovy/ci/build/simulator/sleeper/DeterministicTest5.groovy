package ci.build.simulator.sleeper

import org.junit.jupiter.api.Test

class DeterministicTest5 {
    @Test void t() {
        Thread.sleep(263)
        assert 1 == 1
    }
}