package com.krook1024.game.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Acts as a controller for the launcher view.
 */
@Slf4j
public class LauncherController extends BaseController {

    @FXML
    private TextField nameField;

    /**
     * Called when the user clicks the start game button on the launcher.
     *
     * @param event the click event
     */
    @FXML
    private void onStartGameButtonClicked(ActionEvent event) {
        String name = nameField.getText();
        if (name != null && name.length() > 0) {
            try {
                fxmlLoader.setLocation(getClass().getResource("/fxml/game.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = getStageOfEvent(event);
                Scene current = stage.getScene();
                GameController gameController = fxmlLoader.getController();
                gameController.setPlayerName(name);
                current.setRoot(root);
            } catch (IOException e) {
                log.warn("Something is wrong", e);
            }
        } else {
            nameField.setStyle("-fx-background-color: salmon; -fx-border-color: firebrick;");
            nameField.requestFocus();
        }
    }

    /**
     * Called when the user clicks the scoreboard button on the launcher.
     *
     * @param event the click event
     */
    @FXML
    private void onScoreboardButtonClicked(ActionEvent event) {
        changeSceneTo(getStageOfEvent(event), "/fxml/highscores.fxml");
    }

    /**
     * Called when the user click the quit game button on the launcher.
     *
     * @param event the click event
     */
    @FXML
    private void onQuitGameButtonClicked(ActionEvent event) {
        log.info("Quit button clicked, exiting now...");
        Platform.exit();
    }
}
