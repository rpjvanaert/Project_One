package Block_Sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.util.ArrayList;

public class Main extends Application{

    private Stage stage;
    private int screenWidth = 1920;
    private int screenHeight = 1080;
    private ArrayList<Block> blockList;

    public void start(Stage primaryStage){
        this.stage = primaryStage;
        Canvas canvas = new Canvas(this.screenWidth, this.screenHeight);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        draw(g2d);
        primaryStage.setScene(new Scene(new Group(canvas)));
        primaryStage.setTitle("Project One");
        primaryStage.show();

        canvas.setOnMouseClicked(event -> {
            clearCanvas(g2d);
            this.blockList = Block.randomBlocks(this.screenWidth, this.screenHeight, 10);
            drawBlocks(g2d, this.blockList);
        });
    }

    public void draw(FXGraphics2D g2d){
        clearCanvas(g2d);
        this.blockList = Block.randomBlocks(this.screenWidth, this.screenHeight, 10);
        drawBlocks(g2d, this.blockList);
    }

    public void clearCanvas(FXGraphics2D g2d){
        g2d.setBackground(Color.lightGray);
        g2d.clearRect(0,0, this.screenWidth, this.screenHeight);
    }

    public void drawBlocks(FXGraphics2D g2d, ArrayList<Block> blocks){
        for (Block block : blocks){
            block.setColor(block.getColor());
            block.draw(g2d);
        }
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}
