package Sceneries;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class CodeCheckerScene implements Scenery {
    private Scenery nextScene;
    private Scene scene;
    private String title;

    private BorderPane borderPane;
    private HBox hBox;

    private ArrayList<TextField> checkBoxes;
    private ArrayList<Integer> correctAnswers;

    public CodeCheckerScene(Stage primaryStage){
        this.title = "Worth every hour I've put into this creation.";
        Text title = new Text("Are you OK?");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 100));

        this.hBox = new HBox();
        this.correctAnswers = new ArrayList<>(Arrays.asList(14, 1, 3));
        this.checkBoxes = new ArrayList<>(this.correctAnswers.size());

        for (int i = 1; i <= this.correctAnswers.size(); ++i){
            TextField adding = new TextField("n. " + i);
            adding.setMaxWidth(80);
            adding.setMaxHeight(10);
            adding.setStyle("-fx-font: 10 Verdana;");
            this.checkBoxes.add(adding);
            this.hBox.getChildren().add(adding);
            if (i < this.correctAnswers.size()){
                Label label = new Label("-----");
                label.setStyle("-fx-font: 20 Verdana;");
                label.setMaxWidth(20);
                this.hBox.getChildren().add(label);
            }
        }

        Button checkButton = new Button("Check.");
        checkButton.setStyle("-fx-font: 40 Verdana; -fx-base: #3399ff;");
        checkButton.setOnAction(event -> {
            boolean correct = true;
            for(int each : this.correctAnswers){
                if (each == Integer.parseInt(this.checkBoxes.get(this.correctAnswers.indexOf(each)).getText())){

                } else {
                    correct = false;

                }

            }
            if (correct){
                System.out.println("CORRECT!!!");
            }
        });

        Button indexButton = new Button("Return to Index");
        indexButton.setStyle("-fx-font: 40 Verdana; -fx-base: #3399ff;");
        indexButton.setOnAction(event -> {
            primaryStage.setScene(this.nextScene.getScene());
            primaryStage.setTitle(this.nextScene.getTitle());
        });

        this.borderPane = new BorderPane();
        this.borderPane.setBackground(new Background(new BackgroundFill(Color.color((double)222/256, (double)174/256, (double)84/256), CornerRadii.EMPTY, Insets.EMPTY)));

        this.borderPane.setTop(title);
        this.borderPane.setCenter(this.hBox);
        HBox hboxButtons = new HBox();
        hboxButtons.getChildren().addAll(checkButton, indexButton);
        this.borderPane.setBottom(hboxButtons);
        this.scene = new Scene(this.borderPane);

    }

    public Scene getScene() {
        return this.scene;
    }

    public String getName() {
        return this.title;
    }

    public String getTitle() {
        return title;
    }

    public void setNextScene(Scenery nextScene) {
        this.nextScene = nextScene;
    }
}
