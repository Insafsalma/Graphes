import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/*
 * Détection des clics de la souris pour créer des sommets
 * (pour commencer on se contente de les afficher dans la console)
 */
public class Step04 extends JPanel implements MouseListener {
	static final int CREATION_SOMMET = 1;
	static final int ETIQUETAGE_SOMMET = 2;
	static final int DEBUT_DEPLACEMENT_SOMMET = 3;
	static final int SUITE_DEPLACEMENT_SOMMET = 4;
	static final int DEBUT_CREATION_ARETE = 5;
	static final int SUITE_CREATION_ARETE = 6;

	JLabel barreEtat;
	int etat;

	Step04() {
		JFrame cadre = new JFrame("Step 4");
		cadre.setBounds(100, 50, 600, 500);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		barreEtat = new JLabel("Choisir une action");
		barreEtat.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));

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
			barreEtat
					.setText("Creation sommet : cliquer pour designer l'endroit où le poser");
			break;
		case ETIQUETAGE_SOMMET:
			barreEtat
					.setText("Etiquetage sommet : désigner le sommet à étiqueter");
			break;
		case DEBUT_DEPLACEMENT_SOMMET:
			barreEtat
					.setText("Deplacement sommet : traîner le sommet à déplacer");
			break;
		case SUITE_DEPLACEMENT_SOMMET:
			barreEtat
					.setText("Deplacement sommet : lâcher le sommet à la place voulue");
			break;
		case DEBUT_CREATION_ARETE:
			barreEtat.setText("Création arête : désigner le premier sommet");
			break;
		case SUITE_CREATION_ARETE:
			barreEtat.setText("Création arête : désigner le second sommet");
			break;
		}
	}

	public void mousePressed(MouseEvent evt) {
		switch (etat) {
		case CREATION_SOMMET:
			System.out.println(new Sommet(evt.getX(), evt.getY()));
			return;
		}
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	public static void main(String[] args) {
		new Step04();
	}

}
