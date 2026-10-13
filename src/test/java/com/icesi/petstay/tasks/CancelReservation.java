package com.icesi.petstay.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static com.icesi.petstay.ui.ReservationsPage.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public final class CancelReservation {
    private CancelReservation() { }
    public static Performable request() {
        return Task.where("{0} solicita cancelar su reserva", actor -> {
            String code = actor.recall("reservationCode");
            actor.attemptsTo(Click.on(CANCEL.of(code)), WaitUntil.the(DIALOG, isVisible()));
        });
    }
    public static Performable confirm() {
        return Task.where("{0} acepta la cancelación",
                Click.on(CONFIRM_CANCEL), WaitUntil.the(MESSAGE, isVisible()).forNoMoreThan(20).seconds());
    }
    public static Performable keep() {
        return Task.where("{0} decide conservar su reserva", Click.on(KEEP));
    }
}
