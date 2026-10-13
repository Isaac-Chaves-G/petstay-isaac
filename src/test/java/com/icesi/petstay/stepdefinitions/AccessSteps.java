package com.icesi.petstay.stepdefinitions;

import com.icesi.petstay.tasks.Login;
import com.icesi.petstay.tasks.OpenPetStay;
import com.icesi.petstay.ui.LoginPage;
import com.icesi.petstay.ui.PetsPage;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AccessSteps {
    @Dado("el dueño está en el acceso de PetStay")
    public void opensLogin() {
        theActorInTheSpotlight().attemptsTo(OpenPetStay.at("/index.html"));
    }
    @Cuando("inicia sesión con usuario {string} y contraseña {string}")
    public void logsIn(String user, String password) {
        theActorInTheSpotlight().attemptsTo(Login.withCredentials(user, password));
    }
    @Entonces("puede gestionar sus mascotas")
    public void seesPets() {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(PetsPage.FORM, isVisible()).forNoMoreThan(20).seconds(),
                Ensure.that(PetsPage.FORM).isDisplayed());
    }
    @Entonces("el acceso es rechazado con {string}")
    public void seesLoginError(String message) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(LoginPage.ERROR, isVisible()).forNoMoreThan(20).seconds(),
                Ensure.that(LoginPage.ERROR).text().isEqualTo(message),
                Ensure.that(PetsPage.FORM).isNotDisplayed());
    }
    @Dado("el dueño ha iniciado sesión en PetStay")
    public void authenticatedOwner() {
        opensLogin();
        logsIn("dueno", "huellitas123");
        seesPets();
    }
}
