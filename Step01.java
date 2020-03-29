import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/*
 * Deux choses danss le cadre au lieu d'une: 
 * le panneau blanc et une barre d'état pour aider l'utilisateur
 */
public class Step01 extends JPanel {

	JLabel barreEtat;

	Step01() {
		JFrame cadre = new JFrame("Step 1");
		cadre.setBounds(100, 50, 600, 500);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setBackground(Color.WHITE);
		barreEtat = new JLabel("Ici, bientôt, un texte d'aide");
		barreEtat.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));

		cadre.add(this, BorderLayout.CENTER);
		cadre.add(barreEtat, BorderLayout.SOUTH);
		cadre.setVisible(true);
	}

	public static void main(String[] args) {
		new Step01();
	}
}
