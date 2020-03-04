package MiniGame_Detective;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GlassMain extends Application {

    public static void main(String[] args) {
        launch(GlassMain.class);
    }

    private Stage stage;
    private MagnifyingGlass mGlass;

    public void start(Stage stage){
        this.stage = stage;
        javafx.scene.canvas.Canvas canvas = new Canvas(1920, 1080);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        canvas.setOnMouseClicked(event -> {
            this.mGlass.setPos((int) event.getX() - this.mGlass.getWidth()/2, (int) event.getY() - this.mGlass.getWidth()/2);
            draw(g2d);
        });

        canvas.setOnMouseDragged(event -> {
            this.mGlass.setPos((int) event.getX() - this.mGlass.getWidth()/2, (int) event.getY() - this.mGlass.getWidth()/2);
            draw(g2d);
        });
        this.mGlass = new MagnifyingGlass();

        draw(g2d);

        this.stage.setScene(new Scene(new Group(canvas)));
//        this.stage.setFullScreen(true);
        this.stage.setTitle("Nachtwacht");
        this.stage.show();
    }

    public void draw(FXGraphics2D g2d){
        g2d.setBackground(Color.white);
        g2d.clearRect(0,0,1920,1080);
        this.mGlass.draw(g2d);
        g2d.setClip(this.mGlass.getShapeGlass());
        //draw stuff
        BufferedImage img = null;
        File imgFile = new File("resource/han_solo.jpeg");
        try {
            img = ImageIO.read(imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.drawImage(img,  null, null);
        g2d.setClip(null);
    }
}
