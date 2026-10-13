package com.icesi.petstay.questions;

import com.icesi.petstay.ui.PetsPage;
import net.serenitybdd.screenplay.Question;

public final class RegisteredPets {
    private RegisteredPets() { }
    public static Question<Integer> count() {
        return Question.about("cantidad de mascotas registradas")
                .answeredBy(actor -> PetsPage.ROWS.resolveAllFor(actor).size());
    }
}
