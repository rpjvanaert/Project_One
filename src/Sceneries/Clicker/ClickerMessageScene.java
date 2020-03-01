package Sceneries.Clicker;

import Sceneries.Player;
import Sceneries.Scenery;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class ClickerMessageScene implements Scenery {
    private Scenery nextScene;
    private Scene scene;
    private String songPath = "";

    private Button buttonNext;
    private BorderPane borderPane;

    private String title;

    public ClickerMessageScene(Stage primaryStage, Player player){
        this.title = "Tip: how many squares did you have to click?";

        this.buttonNext = new Button("Next Scene");
        this.buttonNext.setOnAction(event -> {
            primaryStage.setScene(this.nextScene.getScene());
            primaryStage.setTitle(this.nextScene.getTitle());
            player.stop();
            player.setSong(this.nextScene.getSongPath());
        });

        TextFlow textFlow = new TextFlow();

        Text text1 = new Text("¡sueN\n");
        text1.setStyle(
                "-fx-font: 500 Tahoma;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 5;" +
                "-fx-fill: red;");

        Text text2 = new Text();
        text2.setEffect(new InnerShadow());
        text2.setText("That was the first number of the code!");
        text2.setFill(Color.YELLOW);
        text2.setEffect(new InnerShadow());
        text2.setFont(Font.font(null, FontWeight.BOLD, 100));

        textFlow.getChildren().addAll(text1, text2);

        this.borderPane = new BorderPane();
        this.borderPane.setTop(this.buttonNext);
        this.borderPane.setCenter(textFlow);
        this.scene = new Scene(this.borderPane);
    }

    public void setNextScene(Scenery nextScene){
        this.nextScene = nextScene;
    }

    public String getTitle(){ return this.title; }

    public Scene getScene(){
        return this.scene;
    }

    public String getName(){ return "Clicker Message"; }

    public String getSongPath(){ return this.songPath; }
}
