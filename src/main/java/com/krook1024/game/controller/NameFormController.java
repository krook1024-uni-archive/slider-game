package com.krook1024.game.controller;

import javafx.event.ActionEvent;

public class NameFormController extends BaseController {
    public void onGoBackButtonClicked(ActionEvent event) {
        setSceneRoot(getStageOfEvent(event), launcherSceneRoot);
    }
}
