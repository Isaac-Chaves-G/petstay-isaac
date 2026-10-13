package com.icesi.petstay.hooks;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class ScreenplayHooks {
    @Before
    public void prepareActor() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Dueño");
    }
}
