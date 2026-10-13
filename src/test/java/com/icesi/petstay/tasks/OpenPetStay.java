package com.icesi.petstay.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public final class OpenPetStay {
    private OpenPetStay() { }
    public static Performable at(String path) {
        String base = System.getProperty("webdriver.base.url", "https://petstay-aphn.onrender.com");
        return Task.where("{0} visita PetStay " + path, Open.url(base.replaceAll("/$", "") + path));
    }
}
