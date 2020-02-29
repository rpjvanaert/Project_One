import Sceneries.Clicker.ClickerMessageScene;
import Sceneries.Clicker.ClickerScene;
import Sceneries.CodeCheckerScene;
import Sceneries.Experimental.ExperimentalScene;
import Sceneries.IndexScene;
import Sceneries.OpeningScene;
import Sceneries.Scenery;
import Sceneries.ScreenSaver.ScreenSaverScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class MainStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scenery welcome = new OpeningScene(primaryStage);
        Scenery clicker = new ClickerScene(primaryStage);
        Scenery clickerMSG = new ClickerMessageScene(primaryStage);
        Scenery screenSaver = new ScreenSaverScene(primaryStage);
        Scenery experimental = new ExperimentalScene(primaryStage);
        Scenery index = new IndexScene(primaryStage, new ArrayList<>(Arrays.asList(welcome, clicker, screenSaver, experimental)));
        Scenery codeChecker = new CodeCheckerScene(primaryStage);


        welcome.setNextScene(clicker);
        clicker.setNextScene(clickerMSG);
        clickerMSG.setNextScene(screenSaver);
        screenSaver.setNextScene(experimental);
        experimental.setNextScene(index);
        index.setNextScene(codeChecker);
        codeChecker.setNextScene(index);

        Image icon = new Image(getClass().getResourceAsStream("/tomConfused.jpeg"));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(welcome.getScene());
        primaryStage.setTitle(welcome.getTitle());
        primaryStage.setHeight(980);
        primaryStage.setWidth(1920);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(MainStage.class);
    }
}
