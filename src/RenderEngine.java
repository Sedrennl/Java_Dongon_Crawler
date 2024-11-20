/**
 * donne le rendu visuel
 */

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

//récupere toutes les methodes de JPanel pour pouvoir les utiliser
//et celles de Engine mais les redefinir en plus
public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList;

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    //implementation des rendus et nouveaux elements
    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    public void clearRenderList() {
        renderList.clear();
    }

    //implémentation principalement dans l'init
    public void addToRenderList(ArrayList<Displayable> displayable) {
        if (!renderList.contains(displayable)) {
            renderList.addAll(displayable);
        }
    }

    //paint global
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject : renderList) {
            renderObject.draw(g);
        }
    }

    //paint un objet dejà existant, si mvmt
    @Override
    public void update() {
        this.repaint();
    }
}
