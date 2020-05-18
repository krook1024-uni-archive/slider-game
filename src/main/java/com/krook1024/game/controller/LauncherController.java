package com.krook1024.game.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Acts as a controller for the launcher view.
 */
public class LauncherController extends BaseController {
    /**
     * Called when the user clicks the start game button on the launcher.
     *
     * @param event the click event
     */
    @FXML
    private void onStartGameButtonClicked(ActionEvent event) {
        changeSceneTo(getStageOfEvent(event), "/fxml/nameform.fxml");
    }

    /**
     * Called when the user clicks the scoreboard button on the launcher.
     *
     * @param event the click event
     */
    @FXML
    private void onScoreboardButtonClicked(ActionEvent event) {
        changeSceneTo(getStageOfEvent(event), "/fxml/scoreboard.fxml");
    }

    /**
     * Called when the user click the quit game button on the launcher.
     *
     * @param event the click event
     */
    @FXML
    private void onQuitGameButtonClicked(ActionEvent event) {
        logger.info("Quit button clicked, exiting now...");
        Platform.exit();
    }
}
