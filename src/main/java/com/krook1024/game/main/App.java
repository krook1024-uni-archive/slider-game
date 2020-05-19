package com.krook1024.game.main;

import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.LoggerFactory;
import com.krook1024.game.results.GameResultDao;
import com.krook1024.game.util.guice.PersistenceModule;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * The class that controls the application GUI.
 */
@Slf4j
public class App extends Application {

    private GuiceContext context = new GuiceContext(this, () -> List.of(
            new AbstractModule() {
                @Override
                protected void configure() {
                    install(new PersistenceModule("slidergame"));
                    bind(GameResultDao.class);
                }
            }
    ));

    @Inject
    private FXMLLoader fxmlLoader;

    @Inject
    private GameResultDao gameResultDao;

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
    public void start(Stage stage) {
        logger.info("Starting slider-game...");
        context.init();
        long startTime = System.nanoTime();
        logger.trace("gameResultDao: {}", gameResultDao);

        stage.setTitle("slider-game");
        stage.setWidth(appWidth);
        stage.setHeight(appHeight);
        stage.setResizable(false);

        try {
            fxmlLoader.setLocation(getClass().getResource("/fxml/launcher.fxml"));
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            logger.error("Something is wrong", e);
        }

        long elapsedTime = System.nanoTime() - startTime;
        logger.info("Started application in {} ms", elapsedTime / 1000000);
    }
}