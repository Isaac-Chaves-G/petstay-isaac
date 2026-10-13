package com.icesi.petstay.stepdefinitions;

import com.icesi.petstay.models.Pet;
import com.icesi.petstay.questions.RegisteredPets;
import com.icesi.petstay.tasks.RegisterPet;
import com.icesi.petstay.ui.PetsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import java.util.Map;
import net.serenitybdd.screenplay.ensure.Ensure;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PetSteps {
    @Cuando("registra una mascota con estos datos:")
    public void registersPet(DataTable data) {
        Map<String,String> row = data.asMaps().get(0);
        String name = row.getOrDefault("nombre", "");
        if (name == null) name = "";
        Pet pet = new Pet(name, row.get("especie"), row.get("peso"), row.get("edad"), "sí".equals(row.get("vacunas")));
        theActorInTheSpotlight().attemptsTo(RegisterPet.withDetails(pet));
    }
    @Dado("tiene registrada a {string} de especie {string} y peso {string}")
    public void registeredPet(String name, String species, String weight) {
        theActorInTheSpotlight().attemptsTo(RegisterPet.withDetails(new Pet(name, species, weight, "3", true)),
                Ensure.that(PetsPage.ROW.of(name)).isDisplayed());
    }
    @Entonces("la mascota {string} aparece como {string} con peso {string}, edad {string} y tamaño {string}")
    public void checksPet(String name, String species, String weight, String age, String size) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(PetsPage.ROW.of(name)).text().contains(name, species, weight + " kg", age + " años"),
                Ensure.that(PetsPage.SIZE.of(name)).text().isEqualTo(size));
    }
    @Entonces("el registro es rechazado con {string}")
    public void registrationRejected(String message) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(PetsPage.MESSAGE).text().isEqualTo(message));
    }
    @Entonces("el campo de mascota {string} indica {string}")
    public void fieldError(String field, String message) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(PetsPage.FIELD_ERROR.of(field)).text().isEqualTo(message));
    }
    @Entonces("hay {int} mascotas registradas")
    public void petCount(int expected) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(RegisteredPets.count()).isEqualTo(expected));
    }
}
