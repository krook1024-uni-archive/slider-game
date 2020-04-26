package com.krook1024.game.controller;

import javafx.event.ActionEvent;

/**
 * Acts as a controller class for the scoreboard view.
 */
public class ScoreboardController extends BaseController {
    /* TODO: implement scoreboardTable functionality
    @FXML private TableView scoreboardTable;
     */

    /**
     * Runs when the user clicks the Go Back button on the scoreboard view.
     *
     * @param event the click event
     */
    public void onGoBackButtonClicked(ActionEvent event) {
        setSceneRoot(getStageOfEvent(event), launcherSceneRoot);
    }
}
