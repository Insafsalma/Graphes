import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Le tout début: juste un cadre avec un panneau (blanc) dedans
 */
public class Step00 extends JPanel {

	Step00() {
		JFrame cadre = new JFrame("Step 0");
		cadre.setBounds(100, 50, 600, 500);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setBackground(Color.WHITE);

		cadre.add(this);
		cadre.setVisible(true);
	}

	public static void main(String[] args) {
		new Step00();
	}
}
