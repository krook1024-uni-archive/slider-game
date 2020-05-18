package com.krook1024.game.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Acts as a controller class for the nameform view.
 */
public class NameFormController extends BaseController {

    @FXML
    protected TextField name;

    @FXML
    private void initialize() {
        Platform.runLater(() -> name.setText(System.getProperty("user.name")));
    }

    /**
     * Runs when the user clicks the go back button on the name form view.
     *
     * @param event the click event
     */
    @FXML
    public void onGoBackButtonClicked(ActionEvent event) {
        changeSceneTo(getStageOfEvent(event), "/fxml/launcher.fxml");
    }

    /**
     * Runs when the user clicks the play button on the name form view.
     *
     * @throws IOException if it cannot find the game's layout information
     * @param event the click event
     */
    @FXML
    public void onPlayButtonClicked(ActionEvent event) throws IOException {
        String nameText = name.getText();
        if (nameText != null) {
            logger.info("Starting game with the username {}", nameText);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            GameController controller = fxmlLoader.getController();
            controller.setName(name.getText());
            Stage stage = getStageOfEvent(event);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
