package Sceneries;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class EndScene implements Scenery {
    private Scenery nextScene;
    private String title;
    private Scene scene;
    private Canvas canvas;
    private String songPath = "resource/Music/FlowersOnTheGrave.mp3";

    public EndScene(Stage primaryStage, Player player){
        this.title = "Can I have a mug with your face on it?  x)";
        this.canvas = new Canvas(1920, 980);
        this.scene = new Scene(new Group(this.canvas));

    }

    public void setNextScene(Scenery nextScene){ this.nextScene = nextScene; }

    public Scene getScene(){ return this.scene; }

    public String getTitle(){ return this.title; }

    public String getName(){ return this.title; }

    public String getSongPath(){ return this.songPath; }
}
