package com.icesi.petstay.ui;

import net.serenitybdd.screenplay.targets.Target;

public final class BookingPage {
    private BookingPage() { }
    public static final Target TODAY = Target.the("fecha actual del hotel").locatedBy("[data-testid='fecha-hoy']");
    public static final Target PET = Target.the("mascota para hospedar").locatedBy("[name='mascota']");
    public static final Target PET_OPTION = Target.the("opción de {0}")
            .locatedBy("//select[@name='mascota']/option[starts-with(normalize-space(),'{0} (')]");
    public static final Target ENTRY = Target.the("fecha de entrada").locatedBy("#fecha-entrada");
    public static final Target EXIT = Target.the("fecha de salida").locatedBy("[data-testid='fecha-salida']");
    public static final Target BATH = Target.the("baño y peluquería").locatedBy("#srv-bano");
    public static final Target WALK = Target.the("paseo diario").locatedBy("[name='paseo']");
    public static final Target CALCULATE = Target.the("calcular estadía").locatedBy("#btn-calcular");
    public static final Target SUMMARY = Target.the("resumen de reserva").locatedBy(".panel-resumen");
    public static final Target NIGHTS = Target.the("noches").locatedBy("[data-testid='res-noches']");
    public static final Target LODGING = Target.the("alojamiento").locatedBy("#res-alojamiento");
    public static final Target DISCOUNT = Target.the("descuento").locatedBy("[data-testid='res-descuento']");
    public static final Target BATH_PRICE = Target.the("precio del baño").locatedBy(".res-bano");
    public static final Target WALK_PRICE = Target.the("precio de los paseos").locatedBy("#res-paseo");
    public static final Target TOTAL = Target.the("total").locatedBy("[data-testid='res-total']");
    public static final Target CONFIRM = Target.the("confirmar reserva").locatedBy(".panel-resumen button");
    public static final Target CODE = Target.the("código de reserva").locatedBy("[data-testid='codigo-reserva']");
    public static final Target MESSAGE = Target.the("resultado de reserva").locatedBy("[data-testid='mensaje-reserva']");
    public static final Target FIELD_ERROR = Target.the("error del campo {0}").locatedBy("#form-reserva [data-campo='{0}']");
    public static final Target NO_PETS = Target.the("aviso de registro requerido").locatedBy(".sin-mascotas");
}
