package com.icesi.petstay.stepdefinitions;

import com.icesi.petstay.questions.DisplayedMoney;
import com.icesi.petstay.questions.ListedReservations;
import com.icesi.petstay.tasks.*;
import com.icesi.petstay.ui.BookingPage;
import com.icesi.petstay.ui.ReservationsPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class BookingSteps {
    @Cuando("prepara una estadía para {string}")
    public void prepares(String name) {
        theActorInTheSpotlight().attemptsTo(PlanStay.forPet(name));
    }
    @Cuando("solicita {int} noches con entrada en {int} días, baño {string} y paseo {string}")
    public void plans(int nights, int offset, String bath, String walk) {
        theActorInTheSpotlight().attemptsTo(PlanStay.startingIn(offset, nights, "sí".equals(bath), "sí".equals(walk)));
    }
    @Cuando("solicita entrada {string} y salida {string}")
    public void rawDates(String entry, String exit) {
        theActorInTheSpotlight().attemptsTo(PlanStay.onDates(entry, exit, false, false));
    }
    @Entonces("el resumen muestra {int} noches, alojamiento {int}, descuento {int}, baño {int}, paseo {int} y total {int}")
    public void summary(int nights, int lodging, int discount, int bath, int walk, int total) {
        Actor owner = theActorInTheSpotlight();
        owner.attemptsTo(Ensure.that(BookingPage.SUMMARY).isDisplayed(),
                Ensure.that(BookingPage.NIGHTS).text().isEqualTo(Integer.toString(nights)),
                Ensure.that(DisplayedMoney.at(BookingPage.LODGING)).isEqualTo(lodging));
        checksCharge(BookingPage.DISCOUNT, -discount);
        checksCharge(BookingPage.BATH_PRICE, bath);
        checksCharge(BookingPage.WALK_PRICE, walk);
        owner.attemptsTo(Ensure.that(DisplayedMoney.at(BookingPage.TOTAL)).isEqualTo(total));
    }
    private void checksCharge(Target target, int amount) {
        if (amount == 0 && !target.resolveFor(theActorInTheSpotlight()).isVisible()) {
            theActorInTheSpotlight().attemptsTo(Ensure.that(target).isNotDisplayed());
        } else {
            theActorInTheSpotlight().attemptsTo(Ensure.that(DisplayedMoney.at(target)).isEqualTo(amount));
        }
    }
    @Entonces("el campo de reserva {string} indica {string}")
    public void fieldError(String field, String message) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(BookingPage.FIELD_ERROR.of(field)).text().isEqualTo(message),
                Ensure.that(BookingPage.SUMMARY).isNotDisplayed(), Ensure.that(BookingPage.CONFIRM).isNotDisplayed());
    }
    @Entonces("la reserva es rechazada con {string}")
    public void generalError(String message) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(BookingPage.MESSAGE).text().isEqualTo(message),
                Ensure.that(BookingPage.SUMMARY).isNotDisplayed());
    }
    @Entonces("el paseo diario no está disponible")
    public void walkDisabled() {
        theActorInTheSpotlight().attemptsTo(Ensure.that(BookingPage.WALK).isDisabled());
    }
    @Cuando("consulta la disponibilidad para reservar sin mascotas")
    public void noPets() {
        theActorInTheSpotlight().attemptsTo(OpenPetStay.at("/reservar.html"));
    }
    @Entonces("debe registrar una mascota antes de reservar")
    public void requiresPet() {
        theActorInTheSpotlight().attemptsTo(Ensure.that(BookingPage.NO_PETS).isDisplayed(),
                Ensure.that(BookingPage.PET).isNotDisplayed());
    }
    @Cuando("confirma la estadía")
    public void confirms() {
        theActorInTheSpotlight().attemptsTo(ConfirmReservation.now());
    }
    @Cuando("consulta sus reservas")
    public void listsReservations() {
        theActorInTheSpotlight().attemptsTo(OpenPetStay.at("/reservas.html"));
    }
    @Entonces("su reserva aparece confirmada por {int} pesos con código válido y los datos solicitados")
    public void checksReservation(int total) {
        Actor owner = theActorInTheSpotlight();
        String code = owner.recall("reservationCode");
        String name = owner.recall("petName");
        String entry = owner.recall("entry");
        String exit = owner.recall("exit");
        owner.attemptsTo(Ensure.that(code).matches("PS-[0-9]{4}"),
                Ensure.that(ReservationsPage.CELL.of(code, "2")).text().isEqualTo(name),
                Ensure.that(ReservationsPage.CELL.of(code, "3")).text().isEqualTo(entry),
                Ensure.that(ReservationsPage.CELL.of(code, "4")).text().isEqualTo(exit),
                Ensure.that(DisplayedMoney.at(ReservationsPage.CELL.of(code, "5"))).isEqualTo(total),
                Ensure.that(ReservationsPage.CELL.of(code, "6")).text().isEqualTo("Confirmada"),
                Ensure.that(ReservationsPage.CELL.of(code, "7")).text().isEqualTo("—"));
    }
    @Dado("tiene una reserva de {int} noches para {string} con entrada en {int} días")
    public void existingReservation(int nights, String name, int offset) {
        prepares(name);
        plans(nights, offset, "no", "no");
        confirms();
        listsReservations();
    }
    @Entonces("hay {int} reservas en la lista")
    public void reservationCount(int expected) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(ListedReservations.count()).isEqualTo(expected));
    }
}
