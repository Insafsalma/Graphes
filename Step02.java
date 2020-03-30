package graphe;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Step02 extends JPanel {

    JLabel barreEtat;

    Step02() {
        this.setBackground(Color.WHITE);
        barreEtat = new JLabel("Ici, bient�t, un texte d'aide");
        barreEtat.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        JFrame cadre = new JFrame("Step 2");
        cadre.setBounds(100, 50, 600, 500);
        cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cadre.add(this, BorderLayout.CENTER);
        cadre.add(barreEtat, BorderLayout.SOUTH);
        cadre.setJMenuBar(creerBarreMenus());
        cadre.setVisible(true);
    }

    JMenuBar creerBarreMenus() {
        JMenuBar barre = new JMenuBar();
        JMenu menu = new JMenu("Fichier");
        barre.add(menu);

        JMenuItem item = new JMenuItem("Ouvrir...");
        menu.add(item);

        item = new JMenuItem("Enregistrer...");
        menu.add(item);

        menu.addSeparator();
        item = new JMenuItem("Quitter");
        menu.add(item);

        menu = new JMenu("Actions");
        barre.add(menu);

        item = new JMenuItem("Creer sommet");
        menu.add(item);

        item = new JMenuItem("Etiqueter sommet");
        menu.add(item);

        item = new JMenuItem("D�placer sommet");
        menu.add(item);

        item = new JMenuItem("Creer ar�te");
        menu.add(item);

        return barre;
    }

    public static void main(String[] args) {
        new Step02();
    }
}
