package Sceneries;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class EndScene implements Scenery {
    private Scenery nextScene;
    private String title;
    private Scene scene;

    public EndScene(Stage primaryStage){
        this.title = "Can I have a mug with your face on it?  x)";

    }

    public void setNextScene(Scenery nextScene){ this.nextScene = nextScene; }

    public Scene getScene(){ return this.scene; }

    public String getTitle(){ return this.title; }

    public String getName(){ return this.title; }
}
