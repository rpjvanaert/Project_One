import Sceneries.*;
import Sceneries.Clicker.ClickerMessageScene;
import Sceneries.Clicker.ClickerScene;
import Sceneries.Experimental.ExperimentalScene;
import Sceneries.ScreenSaver.ScreenSaverScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class MainStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Player player =  new Player();
        Scenery welcome = new OpeningScene(primaryStage, player);
        Scenery clicker = new ClickerScene(primaryStage, player);
        Scenery clickerMSG = new ClickerMessageScene(primaryStage, player);
        Scenery screenSaver = new ScreenSaverScene(primaryStage, player);
        Scenery experimental = new ExperimentalScene(primaryStage, player);
        Scenery index = new IndexScene(primaryStage, new ArrayList<>(Arrays.asList(welcome, clicker, screenSaver, experimental)), player);
        Scenery endScene = new EndScene(primaryStage, player);
        Scenery codeChecker = new CodeCheckerScene(primaryStage, endScene, player);




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
