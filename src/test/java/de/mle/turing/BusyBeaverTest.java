package de.mle.turing;

import static de.mle.turing.TuringMachine.TERMINATION_STATE;
import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public class BusyBeaverTest {
    private Rule[] transition = new Rule[] {
            new Rule("q0", '_', '1', Direction.RIGHT, "q1"),
            new Rule("q0", '1', '1', Direction.LEFT, "q1"),
            new Rule("q1", '_', '1', Direction.LEFT, "q0"),
            new Rule("q1", '1', '1', Direction.NONE, TERMINATION_STATE)
    };

    @Test
    public void runBusyBeaverAccepting() {
        // given
        TuringMachine tm = new TuringMachine(new Tape("_", '_', 0), transition, "q0");

        // when
        Result result = tm.run();

        // then
        assertThat(result).isEqualTo(Result.ACCEPT);
        assertThat(tm.getCurrentTape()).isEqualTo(("1111"));
    }

    @Test
    public void runBusyBeaverRejecting() {
        // given
        TuringMachine tm = new TuringMachine(new Tape("2222", '_', 0), transition, "q0");

        // when
        Result result = tm.run();

        // then
        assertThat(result).isEqualTo(Result.REJECT);
        assertThat(tm.getCurrentTape()).isEqualTo(("2222"));
    }
}
