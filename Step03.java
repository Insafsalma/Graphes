package graphe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Step03 extends JPanel {
    static final int CREATION_SOMMET = 1;
    static final int ETIQUETAGE_SOMMET = 2;
    static final int DEBUT_DEPLACEMENT_SOMMET = 3;
    static final int SUITE_DEPLACEMENT_SOMMET = 4;
    static final int DEBUT_CREATION_ARETE = 5;
    static final int SUITE_CREATION_ARETE = 6;

    JLabel barreEtat;
    int etat;

    Step03() {
        this.setBackground(Color.WHITE);
        barreEtat = new JLabel("Choisir une action");
        barreEtat.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        JFrame cadre = new JFrame("Step 3");
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
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                etat = CREATION_SOMMET;
                rafraichirBarreEtat();
            }
        });
        menu.add(item);

        item = new JMenuItem("Etiqueter sommet");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                etat = ETIQUETAGE_SOMMET;
                rafraichirBarreEtat();
            }
        });
        menu.add(item);

        item = new JMenuItem("Déplacer sommet");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                etat = DEBUT_DEPLACEMENT_SOMMET;
                rafraichirBarreEtat();
            }
        });
        menu.add(item);

        item = new JMenuItem("Creer arête");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                etat = DEBUT_CREATION_ARETE;
                rafraichirBarreEtat();
            }
        });
        menu.add(item);

        return barre;
    }

    void rafraichirBarreEtat() {
        switch (etat) {
        case CREATION_SOMMET:
            barreEtat.setText("Creation sommet : cliquer pour designer l'endroit où le poser");
            break;
        case ETIQUETAGE_SOMMET:
            barreEtat.setText("Etiquetage sommet : désigner le sommet à étiqueter");
            break;
        case DEBUT_DEPLACEMENT_SOMMET:
            barreEtat.setText("Deplacement sommet : traîner le sommet à déplacer");
            break;
        case SUITE_DEPLACEMENT_SOMMET:
            barreEtat.setText("Deplacement sommet : lâcher le sommet à la place voulue");
            break;
        case DEBUT_CREATION_ARETE:
            barreEtat.setText("Création arête : désigner le premier sommet");
            break;
        case SUITE_CREATION_ARETE:
            barreEtat.setText("Création arête : désigner le second sommet");
            break;
        }
    }

    public static void main(String[] args) {
        new Step03();
    }
}