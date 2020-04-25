package com.krook1024.game.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Acts as a controller class for the scoreboard view.
 */
public class ScoreboardController {
    /* TODO: implement scoreboardTable functionality
    @FXML private TableView scoreboardTable;
     */
    @FXML private Button goBackButton;

    /**
     * Runs when the user clicks the Go Back button on the scoreboard view.
     *
     * @param event the click event
     * @throws IOException if it cannot load launcher's view
     */
    public void onGoBackButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/launcher.fxml"));
        stage.setScene(new Scene(root));
    }
}
