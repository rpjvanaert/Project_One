package MainLogic;

import Sceneries.Clicker.ClickerScene;
import Sceneries.Scenery;
import Sceneries.ScreenSaver.ScreenSaverScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scenery clicker = new ClickerScene(primaryStage);
        Scenery screenSaver = new ScreenSaverScene(primaryStage);

        clicker.setNextScene(screenSaver);
        screenSaver.setNextScene(screenSaver);

        primaryStage.setScene(clicker.getScene());
        primaryStage.setTitle(clicker.getTitle());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(MainStage.class);
    }
}
