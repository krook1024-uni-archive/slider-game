package com.krook1024.game.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.IOException;

/**
 * The class that controls the application GUI.
 */
public class App extends Application {
    private int appWidth = 640;
    private int appHeight = 480;

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), appWidth, appHeight);
        stage.setScene(scene);
        setAppTitleWithVersion(stage);
        stage.setResizable(false);
        stage.show();
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
     * @throws IOException if an error occurs during reading pom.xml
     * @throws XmlPullParserException if pom.xml cannot be parsed
     */
    private String getProjectVersionFromPom() throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        return model.getVersion();
    }
}