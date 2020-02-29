import Sceneries.Clicker.ClickerMessageScene;
import Sceneries.Clicker.ClickerScene;
import Sceneries.OpeningScene;
import Sceneries.Scenery;
import Sceneries.ScreenSaver.ScreenSaverScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scenery welcome = new OpeningScene(primaryStage);
        Scenery clicker = new ClickerScene(primaryStage);
        Scenery clickerMSG = new ClickerMessageScene(primaryStage);
        Scenery screenSaver = new ScreenSaverScene(primaryStage);


        welcome.setNextScene(clicker);
        clicker.setNextScene(clickerMSG);
        clickerMSG.setNextScene(screenSaver);
        screenSaver.setNextScene(clicker);

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