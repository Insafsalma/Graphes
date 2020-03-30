package graphe;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class Step10 extends JPanel implements MouseListener,
        MouseMotionListener {
    static final int CREATION_SOMMET = 1;
    static final int ETIQUETAGE_SOMMET = 2;
    static final int DEBUT_DEPLACEMENT_SOMMET = 3;
    static final int SUITE_DEPLACEMENT_SOMMET = 4;
    static final int DEBUT_CREATION_ARETE = 5;
    static final int SUITE_CREATION_ARETE = 6;
    static final int DEBUT_MODFICATION_POIDS_ARETE = 7;
    static final int SUITE_MODFICATION_POIDS_ARETE = 8;    
    static final int SET_MODFICATION_POIDS_DEFAUT = 9;
    static final int SET_TYPE_GRAPHE = 10;

    static final int TOUT_PRES = 10;

    private JLabel barreEtat;
    private int etat;
    private Sommet sommetSelectionne;
    private static boolean oriente = true;

    Step10() {
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        barreEtat = new JLabel("Choisir une action");
        barreEtat.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        JFrame cadre = new JFrame("Graphe");
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
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                restauration();
            }
        });
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

        item = new JMenuItem("Mofification du poids-arête");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                etat = DEBUT_MODFICATION_POIDS_ARETE;
                rafraichirBarreEtat();
            }
        });
        
        menu.add(item);

        item = new JMenuItem("Mofification du poids par defaut");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                etat = SET_MODFICATION_POIDS_DEFAUT;
                rafraichirBarreEtat();
            }
        });
        
        menu.add(item);

        item = new JMenuItem("Mofification de type de graphe");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                etat = SET_TYPE_GRAPHE;
                rafraichirBarreEtat();
            }
        });
        
        menu.add(item);
        
        menu = new JMenu("Algorithme");
        barre.add(menu);

        item = new JMenuItem("Prufer");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                restauration();
            }
        });
        menu.add(item);

        item = new JMenuItem("Kruskal");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enregistrement();
            }
        });
        menu.add(item);

       // menu.addSeparator();
      /*  item = new JMenuItem("Quitter");
        menu.add(item);*/
        
        menu.add(item);

        item = new JMenuItem("Tarjan");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enregistrement();
            }
        });
        
        menu.add(item);

        item = new JMenuItem("Dijistra");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enregistrement();
            }
        });
        
        menu.add(item);

        item = new JMenuItem("Ordonnancement");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enregistrement();
            }
        });
        
        menu.add(item);

        item = new JMenuItem("Calcul de distaance");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enregistrement();
            }
        });
        
        menu.add(item);

        item = new JMenuItem("Determination du rang");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                enregistrement();
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
        case DEBUT_MODFICATION_POIDS_ARETE:
        	barreEtat.setText("Modification arête : désigner le premier sommet de l'arête");
        	break;
        case SUITE_MODFICATION_POIDS_ARETE:
        	barreEtat.setText("Modification arête : désigner le second sommet de l'arête");
        	break;
        case SET_MODFICATION_POIDS_DEFAUT:
        	barreEtat.setText("Modification poids par defaut");
        case SET_TYPE_GRAPHE:
        	barreEtat.setText("Modification du type de graphe");
        	int type = JOptionPane.showConfirmDialog(this,
                    "Type graphe courant est:  "+typeGraphe()+"\n Si vous choisissez de changer le type de graphe, le graphe courant sera perdu", 
                    "changer type graphe",
                    JOptionPane.OK_CANCEL_OPTION);
        	if (type == JOptionPane.OK_OPTION) {
        		Arete.clearAretes();
        		Sommet.clearSommets();
        		etat=CREATION_SOMMET;
        		oriente = !oriente;
        		repaint();
        	}
        	

        }
    }

    private String typeGraphe() {
		// TODO Auto-generated method stub
    	if (oriente)
		return "orienté";
    	else return "non-orienté";
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
    Arete getArete(Sommet orig, Sommet extr) {
        Iterator iter = Arete.iterator();
        while (iter.hasNext()) {
        	Arete a = (Arete) iter.next();
            if ((a.getExtr().equals(extr))&&(a.getExtr().equals(extr)))
                return a;
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
        case SUITE_MODFICATION_POIDS_ARETE:
        	sommet = sommetVoisin(evt.getX(), evt.getY());
            if (sommet == null || sommet.equals(sommetSelectionne))
                return;
        	Arete art = getArete(sommetSelectionne, sommet);
        	String txt = JOptionPane.showInputDialog(this, "Entrer poids:(1 par defaut",
                    "Poids", JOptionPane.QUESTION_MESSAGE);
            int poids = Integer.parseInt(txt);
            art.setPoids(poids);
            repaint();
            etat = DEBUT_MODFICATION_POIDS_ARETE;
            rafraichirBarreEtat();
            return;
        case DEBUT_MODFICATION_POIDS_ARETE:
        	sommetSelectionne = sommetVoisin(evt.getX(), evt.getY());
            if (sommetSelectionne == null)
                return;
            etat = SUITE_MODFICATION_POIDS_ARETE;
            rafraichirBarreEtat();
            return;
        case SET_MODFICATION_POIDS_DEFAUT:
        	String t = JOptionPane.showInputDialog(this, "Entrer poids par defaut"+Arete.getPoids_defaut() ,
                    "Poids par defaut", JOptionPane.QUESTION_MESSAGE);
        	 Arete.setPoids_defaut(Integer.parseInt(t));
        case SET_TYPE_GRAPHE:
        	/*int type = JOptionPane.showConfirmDialog(this,
                    "Type graphe courant est:  "+Algorithme.typeGraphe()+"\n Si vous choisissez de changer le type de graphe, le graphe courant sera perdu", 
                    "changer type graphe",
                    JOptionPane.OK_CANCEL_OPTION);
        	if (type == JOptionPane.OK_OPTION) {
        		Arete.clearAretes();
        		Sommet.clearSommets();
        		etat=CREATION_SOMMET;
        		repaint();
        	}*/
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

    StreamTokenizer analyseurLexical;

    String prochainMot() throws IOException {
        int typeUnite = analyseurLexical.nextToken();
        if (typeUnite != StreamTokenizer.TT_WORD && typeUnite != '"')
            throw new IOException("Erreur de syntaxe");
        return analyseurLexical.sval;
    }

    int prochainEntier() throws IOException {
        int typeUnite = analyseurLexical.nextToken();
        if (typeUnite != StreamTokenizer.TT_NUMBER)
            throw new IOException("Erreur de syntaxe");
        return (int) analyseurLexical.nval;
    }

    void restauration() {
        JFileChooser dial = new JFileChooser();
        int result = dial.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION)
            return;
        try {
            analyseurLexical = new StreamTokenizer(new FileReader(dial
                    .getSelectedFile()));
            int nbr = prochainEntier();
            for (int i = 0; i < nbr; i++) {
                int etik = Integer.parseInt(prochainMot());
                int x = prochainEntier();
                int y = prochainEntier();
                new Sommet(etik, x, y);
            }

            nbr = prochainEntier();
            for (int i = 0; i < nbr; i++) {
                int etik1 = Integer.parseInt(prochainMot());
                int etik2 = Integer.parseInt(prochainMot());
                Sommet s1 = Sommet.trouverSommet(etik1);
                if (s1 == null)
                    throw new IOException(etik1 + " sommet inexistant");
                Sommet s2 = Sommet.trouverSommet(etik2);
                if (s2 == null)
                    throw new IOException(etik2 + " sommet inexistant");
                new Arete(s1, s2);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        repaint();
    }

    
    
    public static boolean isOriente() {
		return oriente;
	}

	public void setOriente(boolean oriente) {
		this.oriente = oriente;
	}

	public static void main(String[] args) {
        new Step10();
    }
}
