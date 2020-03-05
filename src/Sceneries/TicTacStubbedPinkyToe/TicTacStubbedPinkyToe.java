package Sceneries.TicTacStubbedPinkyToe;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;

//@TODO Random pictures instead of X and O signs
//@TODO Tie screen

public class TicTacStubbedPinkyToe extends Application {
    private Canvas canvas;
    private int width = 1920; private int height = 880;
    private int amountTiles = 11;
    private FXGraphics2D g2d;
    private VBox vBox;
    private HBox hBox;
    private Button buttonNewGame;
    private Button buttonToggleImages;
    private javafx.scene.control.Label labelToggle;

    private BufferedImage milaIMG;
    private BufferedImage ralfIMG;
    private BufferedImage currentPlayerIMG;

    private Shape shapeX;
    private Shape shapeO;
    private Shape shapeW;
    private Shape shapeT;
    private boolean won = false;
    private boolean tie = false;

    private ToeLogic toeLogic;
    private PictureHandler pictureHandler;
    private int imagesShuffled = 2;
    private int player;
    private int winner = 0;
    private int[][] imgMila = new int[amountTiles][amountTiles];
    private int[][] imgRalf = new int[amountTiles][amountTiles];
    private int[][] imgAll = new int[amountTiles][amountTiles];


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.vBox = new VBox();
        this.hBox = new HBox();
        this.canvas = new Canvas(width,height);
        this.g2d = new FXGraphics2D(this.canvas.getGraphicsContext2D());
        this.toeLogic = new ToeLogic(amountTiles, amountTiles);
        this.pictureHandler = new PictureHandler();


        Font font = new Font("Verdana", Font.PLAIN, 110);
        this.shapeX = font.createGlyphVector(this.g2d.getFontRenderContext(), "X").getOutline();
        this.shapeO = font.createGlyphVector(this.g2d.getFontRenderContext(), "O").getOutline();
        this.shapeW = font.createGlyphVector(this.g2d.getFontRenderContext(), "YOU WON").getOutline();
        this.shapeT = font.createGlyphVector(this.g2d.getFontRenderContext(), "It's a tie...").getOutline();

        File milaFile = new File("resource/StubbedPinkyToe/Mila/annabethChase.jfif");
        this.milaIMG = ImageIO.read(milaFile);
        File ralfFile = new File("resource/StubbedPinkyToe/Ralf/percyJackson.jfif");
        this.ralfIMG = ImageIO.read(ralfFile);
        currentPlayerIMG = milaIMG;

        this.player = 1;
        this.buttonNewGame = new Button("Start new Game");
        this.buttonNewGame.setOnAction(event -> {
            this.toeLogic= new ToeLogic(amountTiles, amountTiles);
            this.currentPlayerIMG = this.milaIMG;
            this.player = 1;
            this.won = false;
            this.tie = false;
            this.winner = 0;
        });

        this.buttonToggleImages = new Button("Toggle signs");
        this.buttonToggleImages.setOnAction(event -> {
            this.toggleSign();
            String space = "  ";
            if (this.imagesShuffled == 0){
                this.labelToggle.setText(space + "Images sorted per person" +  space);
            } else if (this.imagesShuffled == 1){
                this.labelToggle.setText(space + "Images shuffled over 2 persons" + space);
            } else if (this.imagesShuffled == 2){
                this.labelToggle.setText(space + "XO XO" +  space);
            }
        });

        this.labelToggle = new Label("  XO XO  ");


        this.hBox.getChildren().addAll(this.buttonNewGame, this.buttonToggleImages, this.labelToggle);
        this.vBox.getChildren().addAll(this.hBox, this.canvas);
        primaryStage.setScene(new Scene(this.vBox));
        primaryStage.setTitle("Tic Tac Stub Toe");
        primaryStage.show();

        this.draw();

        this.canvas.setOnMouseClicked(event -> {
            Point2D p2d = this.getPlace((int)event.getX(), (int)event.getY());
            if (p2d != null && !this.won){
                if (this.toeLogic.placePos(p2d, this.player)){
                    if (this.player == 1){
                        this.imgMila[(int)p2d.getX()][(int)p2d.getY()] = this.pictureHandler.getMilaInt();
                        this.imgAll[(int)p2d.getX()][(int)p2d.getY()] = this.pictureHandler.getInt();
                        this.player = 2;
                        currentPlayerIMG = this.ralfIMG;
                    } else {
                        this.player = 1;
                        this.imgRalf[(int)p2d.getX()][(int)p2d.getY()] = this.pictureHandler.getRalfInt();
                        this.imgAll[(int)p2d.getX()][(int)p2d.getY()] = this.pictureHandler.getInt();
                        currentPlayerIMG = this.milaIMG;
                    }
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
        if (this.won){
            this.drawWon();
        } else if (this.tie){
            this.drawTie();
        }
    }

    public void update(){
        if (this.toeLogic.getToes() != null && !this.won) {
            int winValue = this.toeLogic.checkForWin();
            if (winValue != 0) {
                this.won = true;
                this.winner = winValue;
            } else if (this.toeLogic.isTie()){
                this.tie = true;
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

        if (this.winner == 1){
            this.showPlayer(this.milaIMG);
        } else if (this.winner == 2){
            this.showPlayer(this.ralfIMG);
        } else {
            this.showPlayer(this.currentPlayerIMG);
        }


    }

    public void drawToeLogic(){
        int[][] values = this.toeLogic.getToes();
        for (int x = 0; x < amountTiles; ++x){
            for (int y = 0; y < amountTiles; ++y){
                int value = values[x][y];
                if (value == 1){
                    if (this.imagesShuffled == 0){
                        BufferedImage chosen = this.pictureHandler.getMilaIMG(this.imgMila[x][y]);
                        drawChosenIMG(x, y, chosen);
                    } else if (this.imagesShuffled == 1){
                        BufferedImage chosen = this.pictureHandler.getIMG(this.imgAll[x][y]);
                        drawChosenIMG(x, y, chosen);
                    } else if (this.imagesShuffled == 2){
                        this.g2d.fill(AffineTransform.getTranslateInstance(x * height/amountTiles, (y + 1) * height/amountTiles).createTransformedShape(this.shapeX));
                    }

                } else if (value == 2){
                    if (this.imagesShuffled == 0){
                        BufferedImage chosen = this.pictureHandler.getRalfIMG(this.imgRalf[x][y]);
                        drawChosenIMG(x, y, chosen);
                    } else if (this.imagesShuffled == 1){
                        BufferedImage chosen = this.pictureHandler.getIMG(this.imgAll[x][y]);
                        drawChosenIMG(x, y, chosen);
                    } else if (this.imagesShuffled == 2){
                        this.g2d.fill(AffineTransform.getTranslateInstance(x * height/amountTiles, (y + 1) * height/amountTiles).createTransformedShape(this.shapeO));
                    }
                }
            }
        }
    }

    private void drawChosenIMG(int x, int y, BufferedImage chosen) {
        AffineTransform at = new AffineTransform();
        double scale = getScale(chosen, height/amountTiles, height/amountTiles);
        at.scale(scale, scale);
        at.translate((x * height/amountTiles) / scale, ((y) * height/amountTiles) / scale);
        this.g2d.drawImage(chosen, at, null);
    }

    public double getScale(BufferedImage img, int widthM, int heightM){
        double scaleWidth = (double)widthM / (double)img.getWidth();
        double scaleHeight = (double)heightM / (double)img.getHeight();
        if (scaleHeight < scaleWidth){
            return scaleHeight;
        } else {
            return scaleWidth;
        }
    }

    public void toggleSign(){
        this.imagesShuffled++;
        if (this.imagesShuffled > 2){
            this.imagesShuffled = 0;
        }
    }

    public void drawWon(){
        this.g2d.setPaint(Color.GREEN);
        this.g2d.fill(AffineTransform.getTranslateInstance(height/2, height/2).createTransformedShape(this.shapeW));
        this.g2d.setPaint(Color.WHITE);
    }

    public void drawTie(){
        this.g2d.setPaint(Color.YELLOW);
        this.g2d.fill(AffineTransform.getTranslateInstance(height/2, height/2).createTransformedShape(this.shapeT));
        this.g2d.setPaint(Color.WHITE);
    }

    public void showPlayer(BufferedImage img){
        AffineTransform aT = new AffineTransform();
        double scaleWidth = (width - height)/ (double)img.getWidth();
        double scaleHeight = (height) / (double)img.getHeight();
        double scale;
        if (scaleHeight > scaleWidth){
            scale = scaleWidth;
        } else {
            scale = scaleHeight;
        }
        aT.scale(scale, scale);
        aT.translate(height/scale, 0);
        this.g2d.drawImage(img, aT, null);
    }

    public Point2D getPlace(int x, int y){
        if (x > height || y > height || x < 0 || y < 0){
            return null;
        } else {
            return new Point2D.Double((int)(x / (height/amountTiles)), (int)(y / (height/amountTiles)));
        }
    }
}
