package ru.sberbank.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/",
        glue = {"ru.sberbank.steps"},
        monochrome = true,
        strict = true
)
public class TestRunner {
}
