import java.awt.*;


public class TrapSprite extends Sprite {
    private static final int DAMAGE = 10; // Dégâts infligés
    private static final long DAMAGE_INTERVAL = 1000; // 1 secondes
    private long lastDamageTime = 0;

    public TrapSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    // Applique les dégâts à un seul DynamicSprite
    public void applyDamage(DynamicSprite dynamicSprite) {
        // Vérifie si le DynamicSprite intersecte le TrapSprite
        if (this.getHitBox().intersects(dynamicSprite.getHitBox())) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastDamageTime >= DAMAGE_INTERVAL) { // Délai de 3 secondes entre les dégâts
                dynamicSprite.takeDamage(DAMAGE); // Inflige 10 points de dégâts
                lastDamageTime = currentTime; // Met à jour le temps du dernier dégât
            }
        }
    }
}
