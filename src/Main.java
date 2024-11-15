import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame; //taille zone d'affichage

    RenderEngine renderEngine; // rendu graphique
    GameEngine gameEngine;  // logique du jeu
    PhysicEngine physicEngine; //collisions mvmt objets

    public Main() throws Exception{
        //Set up principal window
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //hero creation
        DynamicSprite hero = new DynamicSprite(200,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50);

        //init window, physic, hero logic
        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero, "0" );

        //init timers
        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());

        //start timers
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        //Visu on
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        //fond et toutes les infos du niveau
        Playground level = new Playground("./data/level1.txt");
        //SolidSprite testSprite = new DynamicSprite(100,100,test,0,0);
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        //interaction clavier
        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main (String[] args) throws Exception {

        Main main = new Main();

        //List<Level> niveaux = Level.createLevels();
    }
}
