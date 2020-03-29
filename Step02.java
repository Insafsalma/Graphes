import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/*
 * Création de la barre de menu 
 * (pour commencer les commandes des menus sont sans effet)
 */
public class Step02 extends JPanel {

	JLabel barreEtat;

	Step02() {
		JFrame cadre = new JFrame("Step 2");
		cadre.setBounds(100, 50, 600, 500);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setBackground(Color.WHITE);
		barreEtat = new JLabel("Ici, bientôt, un texte d'aide");
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
		menu.add(item);

		item = new JMenuItem("Etiqueter sommet");
		menu.add(item);

		item = new JMenuItem("Déplacer sommet");
		menu.add(item);

		item = new JMenuItem("Creer arête");
		menu.add(item);

		return barre;
	}

	public static void main(String[] args) {
		new Step02();
	}
}
