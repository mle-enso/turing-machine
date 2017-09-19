package de.mle.turing;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

public abstract class CommonTestConfig {
    protected Rule[] rules;

    @Test(dataProvider = "provideTape")
    public void runTMWithInitalTapeAndExpectResultAsWellAsFinalTape(Tape initialTape, Result expectedResult, String finalTape) {
        // given
        finalTape = finalTape != null ? finalTape : initialTape.getSymbols();
        TuringMachine tm = new TuringMachine(initialTape, rules, "q0");

        // when
        Result result = tm.run();

        // then
        assertThat(result).isEqualTo(expectedResult);
        assertThat(tm.getCurrentTape()).isEqualTo(finalTape);
    }
}
