import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class testPlayer extends Application {

    private MediaPlayer mediaPlayer;
    private ArrayList<Media> mediaList;
    private int index;
    private boolean playing;

    public static void main(String[] args) {
        launch(testPlayer.class);
    }

    @Override
    public void start(Stage primaryStage) {

        this.mediaList = new ArrayList<>();
        File musicFolder = new File("resource/Music");
        for (File each : musicFolder.listFiles()){
            if (each.toString().contains(".mp3")){
                Media adding = new Media(each.toURI().toString());
                this.mediaList.add(adding);
            }

        }

        this.index = 0;
        this.mediaPlayer = new MediaPlayer(this.mediaList.get(this.index));
        mediaPlayer.play();
        this.playing = true;

        mediaPlayer.setOnEndOfMedia(() -> {
            shiftIndex(1);
        });

        HBox hBox = new HBox();
        Button play = new Button("Resume/Pause");
        Button next = new Button("Next");
        Button previous = new Button("Previous");

        play.setOnAction(event -> {
            if (this.playing){
                this.mediaPlayer.pause();
                this.playing = false;
            } else {
                this.mediaPlayer.play();
                this.playing = true;
            }
        });

        next.setOnAction(event -> {
            this.shiftIndex(1);
        });

        previous.setOnAction(event -> {
            this.shiftIndex(-1);
        });

        hBox.getChildren().addAll(previous, play, next);

        primaryStage.setScene(new Scene(hBox));
        primaryStage.show();
    }

    private void shiftIndex(int amount){
        if (this.playing){
            this.mediaPlayer.stop();
        }
        this.index += amount;
        if (amount > 0){
            if (this.index >= this.mediaList.size()){
                this.index = 0;
            }
        } else if (amount < 0){

            if (this.index < 0){
                this.index = this.mediaList.size() - 1;
            }
        }
        this.mediaPlayer = new MediaPlayer(this.mediaList.get(this.index));

        mediaPlayer.setOnEndOfMedia(() -> {
            shiftIndex(1);
        });

        this.mediaPlayer.play();
    }
}
