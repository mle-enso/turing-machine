package de.mle.turing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "de.mle.turing")
public class TuringMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuringMachineApplication.class, args);
    }
}
