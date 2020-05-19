package com.krook1024.game.controller;

import com.krook1024.game.main.App;
import com.krook1024.game.results.GameResultDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Acts as a base class for every other controller.
 *
 * @author krook1024
 * @version 1.0
 * @since 1.0
 */
@Slf4j
public class BaseController {
    protected org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    FXMLLoader fxmlLoader;

    @Inject
    GameResultDao gameResultDao;

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
     * Sets the scene on the specified stage.
     *
     * @param stage        the stage to change scene on
     * @param resourceName the path to the new scene
     */
    protected void changeSceneTo(Stage stage, String resourceName) {
        logger.info("Changing scene to: {}", resourceName);
        try {
            fxmlLoader.setLocation(getClass().getResource(resourceName));
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.show();
            logger.debug("Changed scene to {} on stage {}", root, stage);
        } catch (IOException e) {
            logger.warn("Something is wrong", e);
        }
    }
}
