package com.icesi.petstay.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

public final class DisplayedMoney {
    private DisplayedMoney() { }
    public static Question<Integer> at(Target target) {
        return Question.about("valor en pesos de " + target.getName()).answeredBy(actor -> {
            String value = Text.of(target).answeredBy(actor).replaceAll("[^0-9-]", "");
            if (value.isEmpty() || value.equals("-")) {
                throw new IllegalStateException("No hay un valor monetario visible en " + target.getName());
            }
            return Integer.parseInt(value);
        });
    }
}
