package graphe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class Step06 extends JPanel implements MouseListener {
    static final int CREATION_SOMMET = 1;
    static final int ETIQUETAGE_SOMMET = 2;
    static final int DEBUT_DEPLACEMENT_SOMMET = 3;
    static final int SUITE_DEPLACEMENT_SOMMET = 4;
    static final int DEBUT_CREATION_ARETE = 5;
    static final int SUITE_CREATION_ARETE = 6;

    static final int TOUT_PRES = 10;

    JLabel barreEtat;
    int etat;

    Step06() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        barreEtat = new JLabel("Choisir une action");
        barreEtat.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        JFrame cadre = new JFrame("Step 6");
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

        item = new JMenuItem("D�placer sommet");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                etat = DEBUT_DEPLACEMENT_SOMMET;
                rafraichirBarreEtat();
            }
        });
        menu.add(item);

        item = new JMenuItem("Creer ar�te");
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
            barreEtat.setText("Creation sommet : cliquer pour designer l'endroit o� le poser");
            break;
        case ETIQUETAGE_SOMMET:
            barreEtat.setText("Etiquetage sommet : d�signer le sommet � �tiqueter");
            break;
        case DEBUT_DEPLACEMENT_SOMMET:
            barreEtat.setText("Deplacement sommet : tra�ner le sommet � d�placer");
            break;
        case SUITE_DEPLACEMENT_SOMMET:
            barreEtat.setText("Deplacement sommet : l�cher le sommet � la place voulue");
            break;
        case DEBUT_CREATION_ARETE:
            barreEtat.setText("Cr�ation ar�te : d�signer le premier sommet");
            break;
        case SUITE_CREATION_ARETE:
            barreEtat.setText("Cr�ation ar�te : d�signer le second sommet");
            break;
        }
    }

    Sommet sommetVoisin(int x, int y) {
        Iterator iter = Sommet.iterator();
        while (iter.hasNext()) {
            Sommet s = (Sommet) iter.next();
            if (Math.abs(x - s.getX()) + Math.abs(y - s.getY()) < TOUT_PRES)
                return s;
        }
        return null;
    }

    public void mousePressed(MouseEvent evt) {
        Sommet sommet;

        switch (etat) {
        case CREATION_SOMMET:
            new Sommet(evt.getX(), evt.getY());
            repaint();
            return;
        case ETIQUETAGE_SOMMET:
            sommet = sommetVoisin(evt.getX(), evt.getY());
            if (sommet == null)
                return;
            String texte = JOptionPane.showInputDialog(this, "Nouveau texte:",
                    "D�finition d'une �tiquette", JOptionPane.QUESTION_MESSAGE);
            if (texte != null)
                sommet.setEtiquette(Integer.parseInt(texte));
            repaint();
            return;
        }
    }
    public void mouseClicked(MouseEvent evt) {}
    public void mouseEntered(MouseEvent evt) {}
    public void mouseExited(MouseEvent evt) {}
    public void mouseReleased(MouseEvent evt) {}

    public void paint(Graphics g) {
        super.paint(g);
        Color cp = g.getColor();

        g.setColor(Color.black);
        Iterator iterA = Arete.iterator();
        while (iterA.hasNext()) {
            Arete a = (Arete) iterA.next();
            g.drawLine(a.getOrig().getX(), a.getOrig().getY(), a.getExtr()
                    .getX(), a.getExtr().getY());
            g.drawString(a.getPoidstoString(), (a.getOrig().getX()+a.getExtr().getX())/2, (a.getOrig().getY()+a.getExtr().getY())/2 );
         }

        Iterator iterS = Sommet.iterator();
        while (iterS.hasNext()) {
            Sommet s = (Sommet) iterS.next();
            g.setColor(Color.red);
            g.fillOval(s.getX() - 5, s.getY() - 5, 10, 10);
            if (s.getEtiquette() != 0) {
                g.setColor(Color.blue);
                g.drawString(s.getEtiquette()+"", s.getX() + 5, s.getY() - 5);
            }
        }
        g.setColor(cp);
    }
    public static void main(String[] args) {
        new Step06();
    }
}