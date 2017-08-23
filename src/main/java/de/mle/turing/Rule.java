package de.mle.turing;

import lombok.Value;

@Value
public class Rule {
    String fromState;
    String read;
    String write;
    Direction move;
    String toState;
}