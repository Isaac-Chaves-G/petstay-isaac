package com.icesi.petstay.questions;

import com.icesi.petstay.ui.ReservationsPage;
import net.serenitybdd.screenplay.Question;

public final class ListedReservations {
    private ListedReservations() { }
    public static Question<Integer> count() {
        return Question.about("cantidad de reservas")
                .answeredBy(actor -> ReservationsPage.ROWS.resolveAllFor(actor).size());
    }
}
