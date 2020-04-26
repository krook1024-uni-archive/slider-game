package com.krook1024.game.controller;

import com.krook1024.game.main.App;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Acts as a controller for the launcher view.
 */
public class LauncherController {

    private Scene scoreboardScene;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * Called when the user clicks the scoreboard button on the launcher.
     *
     * @param event the click event
     */
    @FXML
    private void onScoreboardButtonClicked(ActionEvent event) {
        if (scoreboardScene == null) {
            throw new AssertionError("scoreboardScene is not loaded");
        }
        logger.info("Scoreboard button clicked, changing scene...");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scoreboardScene);
        stage.show();
        logger.debug("Changed scene to {} on stage {}", scoreboardScene, stage);
    }

    /**
     * Called when the user click the quit game button on the launcher.
     *
     * @param event the click event
     */
    @FXML
    public void onQuitGameButtonClicked(ActionEvent event) {
        logger.info("Quit button clicked, exiting now...");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public Scene getScoreboardScene() {
        return scoreboardScene;
    }

    public void setScoreboardScene(Scene scoreboardScene) {
        this.scoreboardScene = scoreboardScene;
    }
}
