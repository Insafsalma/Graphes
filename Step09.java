package graphe;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

public class Step09 extends JPanel implements MouseListener,
        MouseMotionListener {
    static final int CREATION_SOMMET = 1;
    static final int ETIQUETAGE_SOMMET = 2;
    static final int DEBUT_DEPLACEMENT_SOMMET = 3;
    static final int SUITE_DEPLACEMENT_SOMMET = 4;
    static final int DEBUT_CREATION_ARETE = 5;
    static final int SUITE_CREATION_ARETE = 6;

    static final int TOUT_PRES = 10;

    JLabel barreEtat;
    int etat;
    Sommet sommetSelectionne;

    Step09() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        barreEtat = new JLabel("Choisir une action");
        barreEtat.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        JFrame cadre = new JFrame("Step 9");
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
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enregistrement();
            }
        });
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
                    "Définition d'une étiquette", JOptionPane.QUESTION_MESSAGE);
            if (texte != null)
            	 sommet.setEtiquette(Integer.parseInt(texte));
            repaint();
            return;
        case DEBUT_DEPLACEMENT_SOMMET:
            sommetSelectionne = sommetVoisin(evt.getX(), evt.getY());
            if (sommetSelectionne == null)
                return;
            etat = SUITE_DEPLACEMENT_SOMMET;
            rafraichirBarreEtat();
            return;
        case DEBUT_CREATION_ARETE:
            sommetSelectionne = sommetVoisin(evt.getX(), evt.getY());
            if (sommetSelectionne == null)
                return;
            etat = SUITE_CREATION_ARETE;
            rafraichirBarreEtat();
            return;
        case SUITE_CREATION_ARETE:
            sommet = sommetVoisin(evt.getX(), evt.getY());
            if (sommet == null || sommet.equals(sommetSelectionne))
                return;
            new Arete(sommetSelectionne, sommet);
            repaint();
            etat = DEBUT_CREATION_ARETE;
            rafraichirBarreEtat();
            return;
        }
    }

    public void mouseReleased(MouseEvent evt) {
        if (etat == SUITE_DEPLACEMENT_SOMMET)
            etat = DEBUT_DEPLACEMENT_SOMMET;
        rafraichirBarreEtat();
    }

    public void mouseDragged(MouseEvent evt) {
        if (etat == SUITE_DEPLACEMENT_SOMMET) {
            sommetSelectionne.setPosition(evt.getX(), evt.getY());
            repaint();
        }
    }
    public void mouseMoved(MouseEvent evt) {}
    public void mouseClicked(MouseEvent evt) {}
    public void mouseEntered(MouseEvent evt) {}
    public void mouseExited(MouseEvent evt) {}

    public void paint(Graphics g) {
        super.paint(g);
        Color cp = g.getColor();

        g.setColor(Color.black);
        Iterator iterA = Arete.iterator();
        while (iterA.hasNext()) {
            Arete a = (Arete) iterA.next();
            g.drawLine(a.getOrig().getX(), a.getOrig().getY(), a.getExtr()
                    .getX(), a.getExtr().getY());
          /*  String texte = JOptionPane.showInputDialog(this, "Entrer poids:(1 par defaut",
                    "Poids", JOptionPane.QUESTION_MESSAGE);
            int poids = Integer.parseInt(texte);
            a.setPoids(poids);
            */
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

    void enregistrement() {
        JFileChooser dial = new JFileChooser();
        int result = dial.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION)
            return;
        try {
            PrintStream sortie = new PrintStream(dial.getSelectedFile());

            sortie.println(Sommet.nombreSommets());
            Iterator iterS = Sommet.iterator();
            while (iterS.hasNext())
                sortie.println(iterS.next());

            sortie.println(Arete.nombreAretes());
            Iterator iterA = Arete.iterator();
            while (iterA.hasNext())
                sortie.println(iterA.next());
            sortie.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void main(String[] args) {
        new Step09();
    }
}