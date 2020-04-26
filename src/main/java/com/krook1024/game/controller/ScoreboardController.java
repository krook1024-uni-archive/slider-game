package com.krook1024.game.controller;

import com.krook1024.game.main.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Acts as a controller class for the scoreboard view.
 */
public class ScoreboardController {
    /* TODO: implement scoreboardTable functionality
    @FXML private TableView scoreboardTable;
     */

    private Parent launcherSceneRoot;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * Runs when the user clicks the Go Back button on the scoreboard view.
     *
     * @param event the click event
     */
    public void onGoBackButtonClicked(ActionEvent event) {
        if (launcherSceneRoot == null) {
            throw new AssertionError("launcherScene is not loaded");
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(launcherSceneRoot);
        stage.show();
    }

    public Parent getLauncherSceneRoot() {
        return launcherSceneRoot;
    }

    public void setLauncherSceneRoot(Parent launcherSceneRoot) {
        this.launcherSceneRoot = launcherSceneRoot;
    }
}
