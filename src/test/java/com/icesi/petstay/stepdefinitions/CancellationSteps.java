package com.icesi.petstay.stepdefinitions;

import com.icesi.petstay.questions.DisplayedMoney;
import com.icesi.petstay.tasks.CancelReservation;
import com.icesi.petstay.ui.ReservationsPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CancellationSteps {
    @Cuando("solicita cancelar su reserva")
    public void requestsCancellation() {
        theActorInTheSpotlight().attemptsTo(CancelReservation.request());
    }
    @Entonces("se le pide confirmar con un reembolso del {int} por ciento")
    public void checksDialog(int percent) {
        Actor owner = theActorInTheSpotlight();
        String code = owner.recall("reservationCode");
        owner.attemptsTo(Ensure.that(ReservationsPage.DIALOG).isDisplayed(),
                Ensure.that(ReservationsPage.DIALOG_TEXT).text().contains(code, "(" + percent + "%)"),
                Ensure.that(ReservationsPage.CELL.of(code, "6")).text().isEqualTo("Confirmada"));
    }
    @Cuando("acepta la cancelación")
    public void confirmsCancellation() {
        theActorInTheSpotlight().attemptsTo(CancelReservation.confirm());
    }
    @Cuando("decide conservar la reserva")
    public void declinesCancellation() {
        theActorInTheSpotlight().attemptsTo(CancelReservation.keep());
    }
    @Entonces("la reserva queda cancelada con reembolso de {int} pesos y no admite otra cancelación")
    public void cancelled(int refund) {
        Actor owner = theActorInTheSpotlight();
        String code = owner.recall("reservationCode");
        owner.attemptsTo(Ensure.that(ReservationsPage.CELL.of(code, "6")).text().isEqualTo("Cancelada"),
                Ensure.that(DisplayedMoney.at(ReservationsPage.CELL.of(code, "7"))).isEqualTo(refund),
                Ensure.that(ReservationsPage.CANCEL.of(code)).isNotDisplayed(),
                Ensure.that(ReservationsPage.MESSAGE).text().contains(code, "fue cancelada"));
    }
}
