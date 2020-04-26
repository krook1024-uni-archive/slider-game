package com.krook1024.game.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


/**
 * Acts as a controller class for the nameform view.
 */
public class NameFormController extends BaseController {

    @FXML
    protected TextField name;

    /**
     * Runs when the user clicks the go back button on the name form view.
     *
     * @param event the click event
     */
    public void onGoBackButtonClicked(ActionEvent event) {
        setSceneRoot(getStageOfEvent(event), launcherSceneRoot);
    }

    /**
     * Runs when the user clicks the play button on the name form view.
     *
     * @param event the click event
     */
    public void onPlayButtonClicked(ActionEvent event) {
        String nameText = name.getText();
        if (nameText != null) {
            logger.info("Starting game with the username {}", nameText);
            setSceneRoot(getStageOfEvent(event), gameSceneRoot);
        }
    }
}
