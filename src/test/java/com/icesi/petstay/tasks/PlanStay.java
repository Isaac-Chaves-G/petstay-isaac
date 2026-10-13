package com.icesi.petstay.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static com.icesi.petstay.ui.BookingPage.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public final class PlanStay {
    private PlanStay() { }
    public static Performable forPet(String name) {
        return Task.where("{0} prepara una estadía para " + name, actor -> {
            actor.attemptsTo(OpenPetStay.at("/reservar.html"),
                    WaitUntil.the(TODAY, isVisible()).forNoMoreThan(20).seconds());
            String option = Text.of(PET_OPTION.of(name)).answeredBy(actor);
            actor.attemptsTo(SelectFromOptions.byVisibleText(option).from(PET));
            actor.remember("petName", name);
        });
    }
    public static Performable startingIn(int offset, int nights, boolean bath, boolean walk) {
        return Task.where("{0} solicita " + nights + " noches con entrada dentro de " + offset + " días", actor -> {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/uuuu");
            LocalDate today = LocalDate.parse(Text.of(TODAY).answeredBy(actor), format);
            actor.attemptsTo(onDates(today.plusDays(offset).format(format),
                    today.plusDays(offset).plusDays(nights).format(format), bath, walk));
        });
    }
    public static Performable onDates(String entry, String exit, boolean bath, boolean walk) {
        return Task.where("{0} calcula el costo de la estadía", actor -> {
            actor.remember("entry", entry);
            actor.remember("exit", exit);
            actor.attemptsTo(Enter.theValue(entry).into(ENTRY), Enter.theValue(exit).into(EXIT));
            if (BATH.resolveFor(actor).isSelected() != bath) actor.attemptsTo(Click.on(BATH));
            if (WALK.resolveFor(actor).isSelected() != walk) actor.attemptsTo(Click.on(WALK));
            actor.attemptsTo(Click.on(CALCULATE));
        });
    }
}
