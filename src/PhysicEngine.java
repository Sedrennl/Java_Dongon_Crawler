import java.util.ArrayList;

public class PhysicEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList<Sprite> environment;

    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    public void addToEnvironmentList(Sprite sprite) {
        if (!environment.contains(sprite)) {
            environment.add(sprite);
        }
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    public void addToMovingSpriteList(DynamicSprite sprite) {
        if (!movingSpriteList.contains(sprite)) {
            movingSpriteList.add(sprite);
        }
    }

    @Override
    public void update() {
        // Gérer le mouvement des DynamicSprite
        for (DynamicSprite dynamicSprite : movingSpriteList) {
            dynamicSprite.moveIfPossible(environment);
        }

        // Gérer les dégâts infligés par les TrapSprite
        for (Sprite sprite : environment) {
            if (sprite instanceof TrapSprite) {
                ((TrapSprite) sprite).applyDamage(movingSpriteList.get(0)); // Vérifie et applique les dégâts
            }
        }
    }
}
