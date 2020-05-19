package com.krook1024.game.controller;

import com.krook1024.game.main.App;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Acts as a controller for the launcher view.
 */
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
                GameController gameController = fxmlLoader.getController();
                gameController.setName(name);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                logger.warn("Something is wrong", e);
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
        logger.info("Quit button clicked, exiting now...");
        Platform.exit();
    }
}
