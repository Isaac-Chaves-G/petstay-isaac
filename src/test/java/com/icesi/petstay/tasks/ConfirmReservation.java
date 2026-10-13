package com.icesi.petstay.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static com.icesi.petstay.ui.BookingPage.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public final class ConfirmReservation {
    private ConfirmReservation() { }
    public static Performable now() {
        return Task.where("{0} confirma la reserva", actor -> {
            actor.attemptsTo(Click.on(CONFIRM), WaitUntil.the(CODE, isVisible()).forNoMoreThan(20).seconds());
            actor.remember("reservationCode", Text.of(CODE).answeredBy(actor));
        });
    }
}
