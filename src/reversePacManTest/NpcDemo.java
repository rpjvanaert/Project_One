package reversePacManTest;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NpcDemo extends Application {

    private ResizableCanvas canvas;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if(last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane, 1500, 800));
        stage.setTitle("NPCs");
        stage.show();
        draw(g2d);

        canvas.setOnMouseMoved(e ->
        {
            for(Blob blob : blobs) {
                blob.setTarget(new Point2D.Double(e.getX(), e.getY()));
            }
        });
    }


    ArrayList<Blob> blobs;


    public void init() {
        this.blobs = new ArrayList<>();

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("resource/ReversePacMan/food.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Random rng = new Random();
//        for(int i = 0; i < 50; i++) {
//            this.blobs.add(new Food(new Point2D.Double((rng.nextInt(1920/65) * 65), (rng.nextInt(1080/65) * 65)), image));
//        }

        for (int x = 0; x < 1920; x += 65 * 3){
            for (int y = 0; y > -200; y -= 65 * 2){
                this.blobs.add(new Food(new Point2D.Double(x, y), image));
            }
        }
    }


    public void draw(FXGraphics2D g2)
    {
        g2.setTransform(new AffineTransform());
        g2.setBackground(new Color(100,75,75));
        g2.clearRect(0,0,(int)canvas.getWidth(), (int)canvas.getHeight());


        for(Blob blob : blobs) {
            blob.draw(g2);
        }



    }

    public void update(double frameTime) {
        for(Blob blob : blobs) {
            blob.update(blobs);
        }
    }
}