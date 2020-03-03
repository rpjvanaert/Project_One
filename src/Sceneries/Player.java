package Sceneries;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

public class Player {
    private MediaPlayer mediaPlayer;
    private ArrayList<Media> mediaList;
    private int indexPlaying;
    private boolean playing;
    private Media songPlaying;

    public Player(){
        this.mediaList = new ArrayList<>();
        File musicFolder = new File("resource/Music");
        for (File each : musicFolder.listFiles()){
            if (each.toString().contains(".mp3")){
                Media adding = new Media(each.toURI().toString());
                this.mediaList.add(adding);
            }
        }
        this.playing = false;

    }

    public void play(){
        this.mediaPlayer.play();
    }

    public void stop(){
        this.mediaPlayer.stop();
    }

    public void pause(){
        this.mediaPlayer.pause();
    }

    public void shiftIndex(int amount){
        if (this.playing){
            this.mediaPlayer.stop();
        }
        this.indexPlaying += amount;
        if (amount > 0){
            if (this.indexPlaying >= this.mediaList.size()){
                this.indexPlaying = 0;
            }
        } else if (amount < 0){

            if (this.indexPlaying < 0){
                this.indexPlaying = this.mediaList.size() - 1;
            }
        }
        this.mediaPlayer = new MediaPlayer(this.mediaList.get(this.indexPlaying));

        mediaPlayer.setOnEndOfMedia(() -> {
            shiftIndex(1);
        });

        this.mediaPlayer.play();
    }

    public void setSong(String pathSong){
        File song = new File(pathSong);
        Media songMedia = new Media(song.toURI().toString());
        if (playing){
            this.mediaPlayer.stop();
        }
        this.songPlaying = songMedia;

        this.mediaPlayer = new MediaPlayer(songMedia);
        this.mediaPlayer.play();
        this.mediaPlayer.setOnEndOfMedia(()->{
            this.mediaPlayer = new MediaPlayer(this.songPlaying);
        });
    }
}
