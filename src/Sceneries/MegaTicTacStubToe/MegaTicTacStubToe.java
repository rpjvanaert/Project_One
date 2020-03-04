package Sceneries.MegaTicTacStubToe;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;


public class MegaTicTacStubToe extends Application {
    private Canvas canvas;
    private int width = 1920; private int height = 880;
    private int amountTiles = 11;
    private FXGraphics2D g2d;
    private VBox vBox;
    private HBox hBox;
    private Button buttonNewGame;

    private Shape shapeX;
    private Shape shapeO;

    private ToeLogic toeLogic;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.vBox = new VBox();
        this.hBox = new HBox();
        this.canvas = new Canvas(width,height);
        this.g2d = new FXGraphics2D(this.canvas.getGraphicsContext2D());
        this.toeLogic = new ToeLogic(amountTiles, amountTiles);
        Font font = new Font("Verdana", Font.PLAIN, 110);
        this.shapeX = font.createGlyphVector(this.g2d.getFontRenderContext(), "X").getOutline();
        this.shapeO = font.createGlyphVector(this.g2d.getFontRenderContext(), "O").getOutline();

        this.buttonNewGame = new Button("Start new Game");
        this.buttonNewGame.setOnAction(event -> {
            this.toeLogic= new ToeLogic(amountTiles, amountTiles);
        });

        this.hBox.getChildren().addAll(this.buttonNewGame);
        this.vBox.getChildren().addAll(this.hBox, this.canvas);
        primaryStage.setScene(new Scene(this.vBox));
        primaryStage.setTitle("Tic Tac Stub Toe");
        primaryStage.show();

        this.draw();

        this.canvas.setOnMouseClicked(event -> {
            Point2D p2d = this.getPlace((int)event.getX(), (int)event.getY());
            if (p2d != null){
                if (this.toeLogic.placePos(p2d)){
                    this.toeLogic.botStep();
                }
            }
        });

        new AnimationTimer(){
            public void handle(long now){
                update();
                draw();
            }
        }.start();
    }

    public void draw(){
        this.g2d.setBackground(Color.BLACK);
        this.g2d.clearRect(0,0, width, height);
        this.g2d.setPaint(Color.WHITE);
        this.drawGrid();
        this.drawToeLogic();
    }

    public void update(){
        if (this.toeLogic.getToes() != null) {
            int winValue = this.toeLogic.checkForWin();
            if (winValue != 0) {
                System.out.println(winValue + " WON GAME");
                this.toeLogic = new ToeLogic(amountTiles, amountTiles);
            }
        }
    }

    public void drawGrid(){
        for(int x = 0; x <= height; x += height/amountTiles){
            this.g2d.drawLine(x, 0, x, height);
        }

        for (int y = 0; y <= height; y += height/amountTiles){
            this.g2d.drawLine(0, y, height, y);
        }
    }

    public void drawToeLogic(){
//        System.out.println(height/amountTiles);
        int[][] values = this.toeLogic.getToes();
        for (int x = 0; x < amountTiles; ++x){
            for (int y = 0; y < amountTiles; ++y){
                int value = values[x][y];
                if (value == 1){
                    int px = x * height;
                    int py = (y + 1) * height;
                    this.g2d.fill(AffineTransform.getTranslateInstance(px, py).createTransformedShape(this.shapeX));
                } else if (value == 2){
                    this.g2d.fill(AffineTransform.getTranslateInstance(x * height/amountTiles, (y + 1) * height/amountTiles).createTransformedShape(this.shapeO));
                }
            }
        }
    }

    public Point2D getPlace(int x, int y){
        if (x > height || y > height || x < 0 || y < 0){
            return null;
        } else {
            return new Point2D.Double((int)(x / (height/amountTiles)), (int)(y / (height/amountTiles)));
        }
    }
}
