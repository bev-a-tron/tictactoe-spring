package com.springapp.mvc;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class TicTacToeSteps {
    String animal;
    String sound;

    @Given("that the player is at the website")
    public void givenAnimal(@Named("animal") String thisAnimal) {
        animal = thisAnimal;
    }

    @When("$animal makes sound")
    public void whenAnimalMakesSound(@Named("animal") String thisAnimal) {
        if (thisAnimal.equals("lion")){
            sound = "roar";
        }
        else if (thisAnimal.equals ("cat")){
            sound = "meow";
        }
    }

    @Then("it should $cry")
    public void thenShouldCry(@Named("cry") String cry) {
        if (!sound.equals(cry)) {
            throw new RuntimeException("Sound is " + sound + ", but should be " + cry);

        }
    }
}
