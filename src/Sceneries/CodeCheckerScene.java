package Sceneries;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        Text title = new Text("Are you OK?");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 100));

        this.hBox = new HBox();
        this.correctAnswers = new ArrayList<>(Arrays.asList(14, 1, 3));
        this.checkBoxes = new ArrayList<>(this.correctAnswers.size());

        for (int i = 1; i <= this.correctAnswers.size(); ++i){
            TextField adding = new TextField("Number " + i);
            adding.setPrefWidth(100);
            adding.setPrefHeight(40);
            this.checkBoxes.add(adding);
            this.hBox.getChildren().add(adding);
            if (i < this.correctAnswers.size()){
                Label label = new Label("-");
                label.setPrefWidth(75);
                this.hBox.getChildren().add(label);
            }
        }

        Button checkButton = new Button("Check.");
        checkButton.setStyle("-fx-font: 40 Verdana; -fx-base: blue;");

        this.borderPane = new BorderPane();

        this.borderPane.setTop(title);
        this.borderPane.setCenter(this.hBox);
        this.borderPane.setBottom(checkButton);
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

    public Scenery getNextScene() {
        return nextScene;
    }

    public void setNextScene(Scenery nextScene) {
        this.nextScene = nextScene;
    }
}
