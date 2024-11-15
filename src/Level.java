/**
 * definition des niveaux
 */

import java.util.ArrayList;
import java.util.List;
import java.awt.*;


public class Level {
    private String numero;  // Numéro du niveau (peut être un chiffre ou une lettre)
    private Playground playground;  // Instance du playground de ce niveau
    private List<Level> niveauxAccessibles;  // Niveaux accessibles à partir de ce niveau
    private String currentText = "";

    // Constructeur
    public Level(String numero, Playground playground) {
        this.numero = numero;
        this.playground = playground;
        this.niveauxAccessibles = new ArrayList<>();
    }

    // Ajoute un niveau accessible
    public void addAccessibleLevel(Level niveau) {
        this.niveauxAccessibles.add(niveau);
    }

    // Retourne le numéro du niveau
    public String getNumero() {
        return numero;
    }

    // Retourne le playground du niveau
    public Playground getPlayground() {
        return playground;
    }

    // Retourne les niveaux accessibles
    public List<Level> getNiveauxAccessibles() {
        return niveauxAccessibles;
    }

    // Méthode statique pour créer tous les niveaux et leurs accès
    public static List<Level> createLevels() throws Exception {
        // Création des playgrounds pour chaque niveau
        Playground playgroundMenu = new Playground("./data/level0.txt");
        Playground playground1 = new Playground("./data/level1.txt");
        Playground playground2 = new Playground("./data/level2.txt");
        Playground playground3 = new Playground("./data/level3.txt");
        Playground playground4 = new Playground("./data/level4.txt");
        Playground playgroundGameOver = new Playground("./data/level100.txt");

        // Création des niveaux
        Level menu = new Level("0", playgroundMenu);
        Level level1 = new Level("1", playground1);
        Level level2 = new Level("2", playground2);
        Level level3 = new Level("3", playground3);
        Level level4 = new Level("4", playground4);
        Level gameOver = new Level("100", playgroundGameOver);

        // Ajout des connexions de niveaux accessibles
        menu.addAccessibleLevel(level1);
        level1.addAccessibleLevel(level2);
        level1.addAccessibleLevel(gameOver);
        level2.addAccessibleLevel(level3);
        level2.addAccessibleLevel(gameOver);
        level3.addAccessibleLevel(level4);
        level3.addAccessibleLevel(gameOver);
        level4.addAccessibleLevel(gameOver);

        // Création de la liste de niveaux et ajout des niveaux
        List<Level> niveaux = new ArrayList<>();
        niveaux.add(menu);
        niveaux.add(level1);
        niveaux.add(level2);
        niveaux.add(level3);
        niveaux.add(level4);
        niveaux.add(gameOver);

        return niveaux;
    }
    // Méthode pour afficher les informations du niveau (facultatif)
    public void afficherInfos() {
        System.out.println("Niveau : " + numero);
        System.out.println("Niveaux accessibles :");
        for (Level niveau : niveauxAccessibles) {
            System.out.println(" - " + niveau.getNumero());
        }
    }

    public void render(Graphics g) {
        // Dessin du fond et des éléments du playground
        for (Displayable displayable : playground.getSpriteList()) {
            displayable.draw(g);
        }

        // Appel à drawText pour afficher le texte
        playground.drawText(g, "Votre texte ici", 50, 50, Color.WHITE, new Font("Arial", Font.BOLD, 24));
    }
}