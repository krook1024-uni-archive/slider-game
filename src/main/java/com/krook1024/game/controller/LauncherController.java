package com.krook1024.game.controller;

import com.krook1024.game.main.App;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

/**
 * Acts as a controller for the launcher view.
 */
public class LauncherController {

    private Parent scoreboardSceneRoot;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * Called when the user clicks the scoreboard button on the launcher.
     *
     * @param event the click event
     */
    @FXML
    private void onScoreboardButtonClicked(ActionEvent event) {
        if (scoreboardSceneRoot == null) {
            throw new AssertionError("scoreboardSceneLoader is not loaded");
        }
        logger.info("Scoreboard button clicked, changing scene...");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(scoreboardSceneRoot);
        stage.show();
        logger.debug("Changed scene root to {} on stage {}", scoreboardSceneRoot, stage);
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

    public Parent getScoreboardSceneRoot() {
        return scoreboardSceneRoot;
    }

    public void setScoreboardSceneRoot(Parent scoreboardSceneRoot) {
        this.scoreboardSceneRoot = scoreboardSceneRoot;
    }
}
