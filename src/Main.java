import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame; // Fenêtre principale
    RenderEngine renderEngine; // Rendu graphique
    GameEngine gameEngine; // Logique du jeu
    PhysicEngine physicEngine; // Collisions et mouvements
    boolean isGameStarted = false; // Suivi de l'état du jeu
    DynamicSprite hero; // Le héros du jeu

    public Main() throws Exception {
        // Set up principal window
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Gestion de l'écran d'accueil
        renderEngine = new RenderEngine(displayZoneFrame);

        // Ajouter le KeyListener pour détecter le démarrage
        displayZoneFrame.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_S && !isGameStarted) {
                    try {
                        startGameLogic();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Afficher l'écran d'accueil
        Playground levelMenu = new Playground("./data/level0.txt");
        renderEngine.addToRenderList(levelMenu.getSpriteList());
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

// Utiliser Graphics pour dessiner le texte
        Graphics g = renderEngine.getGraphics();
        if (g != null) {
            Font font = new Font("Arial", Font.BOLD, 24);
            levelMenu.drawText(g, "Press S to Start", 150, 300, Color.WHITE, font);
            g.dispose(); // Libérer le contexte graphique
        }
        }

    private void startGameLogic() throws Exception {
        isGameStarted = true;

        // Nettoyer l'écran d'accueil
        renderEngine.clearRenderList();

        // Initialiser le premier niveau et le héros
        Playground gamePlayground = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(gamePlayground.getSpriteList());

        DynamicSprite hero = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);
        renderEngine.addToRenderList(hero);

        // Initialiser les moteurs
        physicEngine = new PhysicEngine();
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(gamePlayground.getSolidSpriteList());

        gameEngine = new GameEngine(hero, "1");

        // Remplacer le KeyListener
        displayZoneFrame.removeKeyListener(displayZoneFrame.getKeyListeners()[0]);
        displayZoneFrame.addKeyListener(gameEngine);

        // Lancer les minuteries
        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();
    }


    public static void main(String[] args) throws Exception {
        new Main();
    }
}
