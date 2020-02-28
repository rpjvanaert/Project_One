package Sceneries.Clicker;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class pictureRotator {
    private BufferedImage image;

    public pictureRotator(){
        try {
            image = ImageIO.read(getClass().getResource("/clicker/JasonWaterfalls.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(){ return this.image; }
}
