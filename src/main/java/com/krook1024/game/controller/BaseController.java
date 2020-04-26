package com.krook1024.game.controller;

import com.krook1024.game.main.App;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

/**
 * Acts as a base class for every other controller.
 *
 * @author krook1024
 * @since 1.0
 * @version 1.0
 */
public class BaseController {
    protected Parent launcherSceneRoot;
    protected Parent scoreboardSceneRoot;
    protected Parent nameFormSceneRoot;

    protected org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Finds the stage of an event.
     *
     * @param event the event to find the stage of
     * @return the stage of {@code event}
     */
    protected static Stage getStageOfEvent(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    /**
     * Sets the scene root on the specified stage.
     *
     * @param stage the stage to change scene root on
     * @param sceneRoot the new scene root
     */
    protected void setSceneRoot(Stage stage, Parent sceneRoot) {
        logger.info("Changing scene root");
        stage.getScene().setRoot(sceneRoot);
        stage.show();
        logger.debug("Changed scene root to {} on stage {}", sceneRoot, stage);
    }

    public Parent getLauncherSceneRoot() {
        return launcherSceneRoot;
    }

    public void setLauncherSceneRoot(Parent launcherSceneRoot) {
        this.launcherSceneRoot = launcherSceneRoot;
    }

    public Parent getScoreboardSceneRoot() {
        return scoreboardSceneRoot;
    }

    public void setScoreboardSceneRoot(Parent scoreboardSceneRoot) {
        this.scoreboardSceneRoot = scoreboardSceneRoot;
    }

    public Parent getNameFormSceneRoot() {
        return nameFormSceneRoot;
    }

    public void setNameFormSceneRoot(Parent nameFormSceneRoot) {
        this.nameFormSceneRoot = nameFormSceneRoot;
    }
}
