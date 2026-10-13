package com.icesi.petstay.ui;

import net.serenitybdd.screenplay.targets.Target;

public final class ReservationsPage {
    private ReservationsPage() { }
    public static final Target ROWS = Target.the("reservas").locatedBy("[data-testid='tabla-reservas'] tbody tr");
    public static final Target ROW = Target.the("reserva {0}")
            .locatedBy("//table[@data-testid='tabla-reservas']/tbody/tr[td[@class='codigo' and normalize-space()='{0}']]");
    public static final Target CELL = Target.the("campo {1} de la reserva {0}")
            .locatedBy("//table[@data-testid='tabla-reservas']/tbody/tr[td[@class='codigo' and normalize-space()='{0}']]/td[{1}]");
    public static final Target CANCEL = Target.the("cancelar reserva {0}").locatedBy(".btn-cancelar[data-codigo='{0}']");
    public static final Target DIALOG = Target.the("confirmación de cancelación").locatedBy("[role='dialog']");
    public static final Target DIALOG_TEXT = Target.the("reembolso estimado").locatedBy(".texto-modal");
    public static final Target CONFIRM_CANCEL = Target.the("aceptar cancelación").locatedBy("#confirmar-cancelacion");
    public static final Target KEEP = Target.the("conservar reserva").locatedBy("[data-accion='volver']");
    public static final Target MESSAGE = Target.the("resultado de cancelación").locatedBy("#mensaje-reservas");
}
