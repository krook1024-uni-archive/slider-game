package com.krook1024.game.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Acts as a controller for the launcher view.
 */
public class LauncherController {

    @FXML private Button quitGameButton;
    @FXML private Button scoreboardButton;

    /**
     * Called when the user clicks the scoreboard button on the launcher.
     *
     * @param event the click event
     * @throws IOException if the scoreboard layout cannot be found
     */
    @FXML
    private void onScoreboardButtonClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/scoreboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Called when the user click the quit game button on the launcher.
     *
     * @param event the click event
     */
    @FXML
    public void onQuitGameButtonClicked(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
