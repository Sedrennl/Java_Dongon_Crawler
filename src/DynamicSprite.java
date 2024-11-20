/**
 * DynamicSprite
 * define and draw a sprite
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;


public class DynamicSprite extends SolidSprite {
    private Direction direction = Direction.EAST;
    private double speed = 5;
    private double timeBetweenFrame = 250;
    private boolean isWalking = true;
    private final int spriteSheetNumberOfColumn = 10;
    private int health = 100;  // La vie commence à 100%
    private long lastDamageTime = 0; // Temps du dernier dégât infligé

    /**
     * define where, and the size of an image => a character / a sprite
     *
     * @param x      image position x
     * @param y      image position y
     * @param image  as the nam say it
     * @param width  size on x
     * @param height size on y
     */
    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);  // Réduit la vie sans descendre en dessous de 0
    }

    public void heal(int amount) {
        health = Math.min(100, health + amount);  // Augmente la vie sans dépasser 100
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch (direction) {
            case EAST:
                moved.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:
                moved.setRect(super.getHitBox().getX() - speed, super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() - speed,
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() + speed,
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        for (Sprite s : environment) {
            if ((s instanceof SolidSprite) && (s != this)) {
                if (((SolidSprite) s).intersect(moved)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * return the current position of this sprite
     *
     * @param direction NSEW
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //define the move ( direction and speed)
    private void move() {
        switch (direction) {
            case NORTH -> {
                this.y -= speed;
            }
            case SOUTH -> {
                this.y += speed;
            }
            case EAST -> {
                this.x += speed;
            }
            case WEST -> {
                this.x -= speed;
            }
        }
    }


    /**
     * the character will be blocked if he encounters an obstacle
     *
     * @param environment may be background or obstacle
     */
    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move();
        }
        //checkAndApplyDamage(environment, imageTrap);  // Vérifie les TRAPs et applique les dégâts
    }


    /**
     * draw everything
     *
     * @param g graphic
     */
    @Override
    public void draw(Graphics g) {
        //affichage sprite
        int index = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);
        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                (int) (index * this.width), (int) (direction.getFrameLineNumber() * height),
                (int) ((index + 1) * this.width), (int) ((direction.getFrameLineNumber() + 1) * this.height), null);

        // Affichage de la barre de vie en haut à gauche
        int barWidth = 100;  // Largeur de la barre de vie en pixels
        int barHeight = 10;  // Hauteur de la barre de vie en pixels
        int xPosition = 10;  // Position en X de la barre de vie
        int yPosition = 10;  // Position en Y de la barre de vie

        // Dessine le fond noir de la barre de vie
        g.setColor(Color.BLACK);
        g.fillRect(xPosition, yPosition, barWidth, barHeight);

        // Dessine la barre rouge pour représenter la vie actuelle
        int redBarWidth = (int) (barWidth * (health / 100.0));
        g.setColor(Color.RED);
        g.fillRect(xPosition, yPosition, redBarWidth, barHeight);

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setImageForMainMenu(Image image) {
        this.image = image; // Change l'image du héros pour l'écran d'accueil
    }

}
