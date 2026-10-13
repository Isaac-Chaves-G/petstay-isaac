package com.icesi.petstay.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static com.icesi.petstay.ui.LoginPage.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public final class Login {
    private Login() { }
    public static Performable withCredentials(String user, String password) {
        return Task.where("{0} inicia sesión como " + user,
                WaitUntil.the(USERNAME, isVisible()).forNoMoreThan(60).seconds(),
                Enter.theValue(user).into(USERNAME),
                Enter.theValue(password).into(PASSWORD),
                Click.on(SUBMIT));
    }
}
