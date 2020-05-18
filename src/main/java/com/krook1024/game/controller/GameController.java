package com.krook1024.game.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.time.LocalTime;

/**
 * Acts as a controller class for the game view.
 */
public class GameController extends BaseController {
    private String name;
    private Duration time = Duration.ZERO;

    @FXML
    Label usernameLabel;
    @FXML
    Label elapsedTimeLabel;
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

        Timeline clock = getClockTimeline();
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    /**
     * Creates a timeline that runs the clock (counts elapsed time).
     *
     * @return the timeline for the clock
     */
    private Timeline getClockTimeline() {
        return new Timeline(
                new KeyFrame(Duration.millis(100),
                        t -> {
                            Duration duration = ((KeyFrame) t.getSource()).getTime();
                            time = time.add(duration);
                            elapsedTimeLabel.setText(formatElapsedTime((int) time.toSeconds()));
                        })
        );
    }

    /**
     * Formats seconds as H:MM:SS
     *
     * @param seconds the seconds to format
     * @return the formatted string
     */
    private String formatElapsedTime(int seconds) {
        return String.format("%d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, (seconds % 60));
    }
}
