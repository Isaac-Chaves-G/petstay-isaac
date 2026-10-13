package com.icesi.petstay.ui;

import net.serenitybdd.screenplay.targets.Target;

public final class LoginPage {
    private LoginPage() { }
    public static final Target USERNAME = Target.the("usuario").locatedBy("[name='usuario']");
    public static final Target PASSWORD = Target.the("contraseña").locatedBy("#pass-01");
    public static final Target SUBMIT = Target.the("ingresar").locatedBy("[data-testid='btn-ingresar']");
    public static final Target ERROR = Target.the("error de acceso").locatedBy("#error-login");
}
