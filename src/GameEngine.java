/**
 * logique du jeux et du héros
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    DynamicSprite hero;
    private String currentLevelId;

    public GameEngine(DynamicSprite hero, String currentLevelId) {
        this.hero = hero;
        this.currentLevelId = currentLevelId;
    }


    @Override
    public void update() {

    }

    // Setter pour modifier l'ID du niveau actuel
    public void setCurrentLevelId (String currentLevelId){
        this.currentLevelId = currentLevelId;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //controle du perso
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;
            case KeyEvent.VK_S:
                if ("0".equals(currentLevelId)) {
                    setCurrentLevelId("1");
                    break;
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


}
