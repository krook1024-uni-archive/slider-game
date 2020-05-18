package com.krook1024.game.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Acts as a controller class for the game view.
 */
public class GameController extends BaseController {
    private String name;

    @FXML
    Label usernameLabel;
    @FXML
    Label elapsedTime;
    @FXML
    GridPane gameGrid;

    public void setName(String name) {
        this.name = name;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            logger.info("Setting name to {}", name);
            usernameLabel.setText("Hello, " + name);
        });
    }
}
