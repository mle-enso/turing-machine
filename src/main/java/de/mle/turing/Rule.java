package de.mle.turing;

import lombok.Value;

@Value
public class Rule {
    String fromState;
    char read;
    char write;
    Direction move;
    String toState;
}