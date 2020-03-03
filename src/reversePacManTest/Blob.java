package reversePacManTest;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface Blob {
    void update(ArrayList<Blob> food);
    void draw(Graphics2D g2d);
    AffineTransform getTransform();
    void setTarget(Point2D target);
    double distance(Blob other, Point2D newPosition);
    Point2D getPosition();
}
