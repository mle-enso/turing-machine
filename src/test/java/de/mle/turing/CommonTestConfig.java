package de.mle.turing;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Extend from this class and assign {@code rules} a rule set with start state "q0" as well as a {@code initialTapeAndExpectedResultAndExpectedTape}
 * a two-dimensional object array with the following ordered information:
 * - a {@link Tape} which represents the initial tape,
 * - the expected {@link Result} of the Turing Machine after finished and
 * - the respective final tape as {@link String}.
 */
public abstract class CommonTestConfig {
    protected Rule[] rules;
    protected Object[][] initialTapeAndExpectedResultAndExpectedTape;

    @DataProvider
    public Object[][] provideTape() {
        return initialTapeAndExpectedResultAndExpectedTape;
    }

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
