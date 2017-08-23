package de.mle.turing;

import static de.mle.turing.TuringMachine.TERMINATION_STATE;
import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BusyBeaverTest {
    private Rule[] transition = new Rule[] {
            new Rule("q0", "_", "1", Direction.RIGHT, "q1"),
            new Rule("q0", "1", "1", Direction.LEFT, "q1"),
            new Rule("q1", "_", "1", Direction.LEFT, "q0"),
            new Rule("q1", "1", "1", Direction.NONE, TERMINATION_STATE)
    };

    @DataProvider
    public Object[][] provideTape() {
        return new Object[][] {
                { new Tape("_", "_", 0), Result.ACCEPT, "1111" },
                { new Tape("x", "_", 0), Result.REJECT, "x" }
        };
    }

    @Test(dataProvider = "provideTape")
    public void runBusyBeaverAccepting(Tape initialTape, Result expectedResult, String finalTape) {
        // given
        TuringMachine tm = new TuringMachine(initialTape, transition, "q0");

        // when
        Result result = tm.run();

        // then
        assertThat(result).isEqualTo(expectedResult);
        assertThat(tm.getCurrentTape()).isEqualTo((finalTape));
    }
}
