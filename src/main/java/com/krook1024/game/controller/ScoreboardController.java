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

    private Scene launcherScene;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * Runs when the user clicks the Go Back button on the scoreboard view.
     *
     * @param event the click event
     */
    public void onGoBackButtonClicked(ActionEvent event) {
        if (launcherScene == null) {
            throw new AssertionError("launcherScene is not loaded");
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(launcherScene);
        stage.show();
    }

    public Scene getLauncherScene() {
        return launcherScene;
    }

    public void setLauncherScene(Scene launcherScene) {
        this.launcherScene = launcherScene;
    }
}
