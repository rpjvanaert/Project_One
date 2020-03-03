package Sceneries.reversePacManTest;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface Blob {
    boolean update(ArrayList<Blob> food);
    void draw(FXGraphics2D g);
    AffineTransform getTransform();
    void setTarget(Point2D target);
    double distance(Blob other, Point2D newPosition);
    Point2D getPosition();
}
