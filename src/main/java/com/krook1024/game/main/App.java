package com.krook1024.game.main;

import com.krook1024.game.controller.LauncherController;
import com.krook1024.game.controller.NameFormController;
import com.krook1024.game.controller.ScoreboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;

/**
 * The class that controls the application GUI.
 */
public class App extends Application {

    /**
     * Specifies the width of the app window.
     */
    private static final int appWidth = 640;

    /**
     * Specifies the height of the app window.
     */
    private static final int appHeight = 480;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(App.class);

    @Override
    public void start(Stage stage)  {
        logger.info("Starting slider-game...");
        long startTime = System.nanoTime();

        setAppTitleWithVersion(stage);
        stage.setWidth(appWidth);
        stage.setHeight(appHeight);
        stage.setResizable(false);

        try {
            Parent launcherSceneRoot = preloadFxmlResources();

            stage.setScene(new Scene(launcherSceneRoot));
            stage.show();
        } catch (IOException e) {
            logger.error("Something is wrong", e);
        }

        long elapsedTime = System.nanoTime() - startTime;
        logger.info("Started application in {} ms", elapsedTime / 1000000);
    }

    /**
     * Preloads FXML resources for the corresponding controllers.
     * This improves performance when switching scenes and fixes a bug that
     * occurs in tiling window managers that keeps the scenes from being loaded.
     * @return the launcherSceneRoot instance
     * @throws IOException when an fxml resource cannot be loaded
     */
    private Parent preloadFxmlResources() throws IOException {
        FXMLLoader launcherSceneLoader = getFxmlLoader("/fxml/launcher.fxml");
        Parent launcherSceneRoot = launcherSceneLoader.load();

        FXMLLoader scoreboardSceneLoader = getFxmlLoader("/fxml/scoreboard.fxml");
        Parent scoreboardSceneRoot = scoreboardSceneLoader.load();

        FXMLLoader nameFormSceneLoader = getFxmlLoader("/fxml/nameform.fxml");
        Parent nameFormSceneRoot = nameFormSceneLoader.load();

        FXMLLoader gameSceneLoader = getFxmlLoader("/fxml/game.fxml");
        Parent gameSceneRoot = gameSceneLoader.load();

        // Pass the preloaded fxml files to the scoreboard controller so that it can load it much quicker
        LauncherController launcherController = launcherSceneLoader.getController();
        launcherController.setScoreboardSceneRoot(scoreboardSceneRoot);
        launcherController.setNameFormSceneRoot(nameFormSceneRoot);

        // Pass the preloaded fxml files to the launcher controller
        ScoreboardController scoreboardController = scoreboardSceneLoader.getController();
        scoreboardController.setLauncherSceneRoot(launcherSceneRoot);

        // Pass the preloaded fxml files to the game controller
        NameFormController nameFormController = nameFormSceneLoader.getController();
        nameFormController.setLauncherSceneRoot(launcherSceneRoot);
        nameFormController.setGameSceneRoot(gameSceneRoot);
        return launcherSceneRoot;
    }

    /**
     * Gets an {@code FXMLLoader} instance to the specified resource.
     *
     * @param resourceName the name of the resource that has to be loaded
     * @return the loader instance
     */
    private FXMLLoader getFxmlLoader(String resourceName) {
        logger.info("Loading resource: {}", resourceName);
        return new FXMLLoader(getClass().getResource(resourceName));
    }

    /**
     * Sets the stage's title according to the project version in pom.xml.
     *
     * @param stage the stage whose title has to be set
     */
    private void setAppTitleWithVersion(Stage stage) {
        try {
            stage.setTitle("slider-game " + getProjectVersionFromPom());
        } catch (Exception e) {
            stage.setTitle("slider-game");
        }
    }

    /**
     * Find the the project version in pom.xml and returns it.
     *
     * @return the project version
     */
    private String getProjectVersionFromPom() {
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader("pom.xml"));
            return model.getVersion();
        } catch (IOException | XmlPullParserException e) {
            logger.error("Something is wrong", e);
        }
        return null;
    }
}