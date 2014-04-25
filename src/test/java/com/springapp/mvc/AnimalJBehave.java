package com.springapp.mvc;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(JUnitReportingRunner.class)
public class AnimalJBehave extends JUnitStories {

    public AnimalJBehave() {
        super();
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new AnimalSteps());
    }

    @Override
    public List<String> storyPaths() {
        return Arrays.asList("tictactoe/Animals.story");
    }

}