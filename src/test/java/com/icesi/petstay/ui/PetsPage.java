package com.icesi.petstay.ui;

import net.serenitybdd.screenplay.targets.Target;

public final class PetsPage {
    private PetsPage() { }
    public static final Target FORM = Target.the("registro de mascotas").locatedBy("#form-mascota");
    public static final Target NAME = Target.the("nombre").locatedBy("#nombreMascota");
    public static final Target SPECIES = Target.the("especie {0}").locatedBy("[name='especie'][value='{0}']");
    public static final Target WEIGHT = Target.the("peso").locatedBy("[name='peso']");
    public static final Target AGE = Target.the("edad").locatedBy("[data-testid='edad']");
    public static final Target VACCINATED = Target.the("vacunas al día").locatedBy("[name='vacunas']");
    public static final Target SUBMIT = Target.the("registrar mascota").locatedBy("#form-mascota button[type='submit']");
    public static final Target MESSAGE = Target.the("resultado del registro").locatedBy("[data-testid='mensaje-mascotas']");
    public static final Target FIELD_ERROR = Target.the("error del campo {0}").locatedBy("#form-mascota [data-campo='{0}']");
    public static final Target ROWS = Target.the("mascotas registradas").locatedBy("#tabla-mascotas tbody tr");
    public static final Target ROW = Target.the("mascota {0}")
            .locatedBy("//table[@id='tabla-mascotas']/tbody/tr[td[@class='nombre' and normalize-space()='{0}']]");
    public static final Target SIZE = Target.the("tamaño de {0}")
            .locatedBy("//table[@id='tabla-mascotas']/tbody/tr[td[@class='nombre' and normalize-space()='{0}']]//span[@class='etiqueta-tamano']");
}
