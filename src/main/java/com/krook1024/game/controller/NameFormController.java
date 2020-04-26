package com.krook1024.game.controller;

import javafx.event.ActionEvent;

/**
 * Acts as a controller class for the nameform view.
 */
public class NameFormController extends BaseController {
    /**
     * Runs when the user clicks the go back button on the name form view.
     *
     * @param event the click event
     */
    public void onGoBackButtonClicked(ActionEvent event) {
        setSceneRoot(getStageOfEvent(event), launcherSceneRoot);
    }
}
