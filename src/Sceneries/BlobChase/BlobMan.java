package Sceneries.BlobChase;

import org.jfree.fx.FXGraphics2D;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BlobMan implements  Blob{
    private Point2D position;
    private double angle;
    private double speed;
    private BufferedImage sprite;

    private Point2D target;
    private double rotationSpeed;
    private boolean collided;


    public BlobMan(Point2D position, BufferedImage sprite) {
        this.position = position;
        this.sprite = sprite;
        this.angle = 0;
        this.speed = 10;
        this.target = new Point2D.Double(200,200);
        this.rotationSpeed = 0.5;
    }

    public boolean update(ArrayList<Blob> food)
    {
        double targetAngle = Math.atan2(this.target.getY() - this.position.getY(),
                                        this.target.getX() - this.position.getX());

        double angleDifference = this.angle - targetAngle;
        while(angleDifference < -Math.PI)
            angleDifference += 2 * Math.PI;
        while(angleDifference > Math.PI)
            angleDifference -= 2 * Math.PI;


        if(Math.abs(angleDifference) < this.rotationSpeed)
            this.angle = targetAngle;
        else if(angleDifference < 0)
            this.angle += this.rotationSpeed;
        else
            this.angle -= this.rotationSpeed;

        Point2D newPosition = new Point2D.Double(this.position.getX() + this.speed * Math.cos(this.angle),
                this.position.getY() + this.speed * Math.sin(this.angle));

        this.collided = false;

        for(Blob other : food) {
            if(other != this && this.distance(other, newPosition) < other.getRadius() + 32) {
                collided = true;
            }
        }


        if(!collided) {
            this.position = newPosition;
            return false;
        } else {
            this.angle -= this.rotationSpeed*2;
            return true;
        }
    }

    public void draw(FXGraphics2D g)
    {
        g.drawImage(sprite, getTransform(), null);
    }

    public AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX() - this.sprite.getWidth()/2, position.getY() - this.sprite.getHeight()/2);
        tx.rotate(this.angle + 0.5 * Math.PI, this.sprite.getWidth()/2, this.sprite.getHeight()/2);
        return tx;
    }

    public void setTarget(Point2D target) {
        this.target = target;
    }

    public double distance(Blob other, Point2D newPosition){
        return other.getPosition().distance(newPosition);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setSpeed(int speed){
    }

    public int getRadius(){
        return 32;
    }
}
