package com.icesi.petstay.tasks;

import com.icesi.petstay.models.Pet;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static com.icesi.petstay.ui.PetsPage.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public final class RegisterPet {
    private RegisterPet() { }
    public static Performable withDetails(Pet pet) {
        return Task.where("{0} registra a " + pet.name(), actor -> {
            actor.attemptsTo(Enter.theValue(pet.name()).into(NAME),
                    Click.on(SPECIES.of(pet.species())),
                    Enter.theValue(pet.weight()).into(WEIGHT),
                    Enter.theValue(pet.age()).into(AGE));
            if (VACCINATED.resolveFor(actor).isSelected() != pet.vaccinated()) {
                actor.attemptsTo(Click.on(VACCINATED));
            }
            actor.attemptsTo(Click.on(SUBMIT),
                    WaitUntil.the(MESSAGE, isVisible()).forNoMoreThan(20).seconds());
        });
    }
}
