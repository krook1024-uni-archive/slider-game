package com.krook1024.game.controller;

import com.krook1024.game.state.Direction;
import com.krook1024.game.state.SliderState;
import com.krook1024.game.state.Tile;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.List;

/**
 * Acts as a controller class for the game view.
 */
public class GameController extends BaseController {
    private String name;
    private Duration time = Duration.ZERO;
    private IntegerProperty steps = new SimpleIntegerProperty(0);
    private SliderState sliderState;
    private Timeline clock;
    private List<Image> images;
    private int activeTileIndex = -1;

    @FXML
    Label usernameLabel;
    @FXML
    Label elapsedTimeLabel;
    @FXML
    Label stepsLabel;
    @FXML
    GridPane gameGrid;

    public void setName(String name) {
        this.name = name;
    }

    @FXML
    private void initialize() {
        images = List.of(
                new Image(getClass().getResource("/rectangle/1.png").toExternalForm()),
                new Image(getClass().getResource("/rectangle/2.png").toExternalForm()),
                new Image(getClass().getResource("/rectangle/3.png").toExternalForm()),
                new Image(getClass().getResource("/rectangle/4.png").toExternalForm()),
                new Image(getClass().getResource("/rectangle/5.png").toExternalForm())
        );
        stepsLabel.textProperty().bind(steps.asString());
        resetGame();
    }

    private void resetGame() {
        Platform.runLater(() -> {
            logger.info("Setting name to {}", name);
            usernameLabel.setText("Hello, " + name);
        });
        sliderState = new SliderState();
        steps.set(0);
        time = Duration.ZERO;
        clock = getClockTimeline();
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        draw();
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

    public void draw() {
        gameGrid.getChildren().clear();
        for (int i = 0; i < sliderState.getTiles().size(); i++) {
            Tile t = sliderState.getTiles().get(i);
            ImageView imageView = new ImageView();

            GridPane.setRowIndex(imageView, Math.min(t.getTopLeft().getY(), t.getBotLeft().getY()));
            GridPane.setColumnIndex(imageView, Math.min(t.getTopLeft().getX(), t.getBotLeft().getX()));
            GridPane.setRowSpan(imageView, 2);
            GridPane.setColumnSpan(imageView, 2);

            imageView.setImage(images.get(t.getType().getValue() - 1));
            imageView.setOnMouseClicked(this::handleGameGridClick);
            if (i == activeTileIndex) {
                imageView.setStyle("-fx-opacity: 0.85");
            }
            gameGrid.getChildren().add(imageView);
        }
    }

    private void handleGameGridClick(Event e) {
        Node source = (Node) e.getSource();

        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        Integer colSpan = GridPane.getColumnSpan(source);
        Integer rowSpan = GridPane.getRowSpan(source);

        logger.info("Clicked on Tile [{}, {}]", colIndex, rowIndex);

        gameGrid.getChildren()
                .filtered((Node elem) -> elem != source)
                .forEach((Node elem) -> elem.setStyle("-fx-opacity: 1;"));
        source.setStyle("-fx-opacity: 0.85");

        activeTileIndex = sliderState.findTileIndexByTopLeftAtPoint(colIndex, rowIndex);
        logger.info("The corresponding index for the clicked tile is {}", activeTileIndex);
    }

    @FXML
    public void stepLeft(ActionEvent event) {
        sliderState.stepTileWithIndexAcrossX(activeTileIndex, Direction.LEFT);
        steps.set(steps.get() + 1);
        draw();
    }

    @FXML
    public void stepRight(ActionEvent event) {
        sliderState.stepTileWithIndexAcrossX(activeTileIndex, Direction.RIGHT);
        steps.set(steps.get() + 1);
        draw();
    }

    @FXML
    public void stepUp(ActionEvent event) {
        sliderState.stepTileWithIndexAcrossY(activeTileIndex, Direction.UP);
        steps.set(steps.get() + 1);
        draw();
    }

    @FXML
    public void stepDown(ActionEvent event) {
        sliderState.stepTileWithIndexAcrossY(activeTileIndex, Direction.DOWN);
        steps.set(steps.get() + 1);
        draw();
    }
}
