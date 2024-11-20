import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite {
    public SolidSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }


    public boolean intersect(Rectangle2D.Double hitBox) {
        return this.getHitBox().intersects(hitBox);
    }
}