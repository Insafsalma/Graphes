package graphe;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Step01 extends JPanel {

    JLabel barreEtat;

    Step01() {
        this.setBackground(Color.WHITE);
        barreEtat = new JLabel("Ici, bientôt, un texte d'aide");
        barreEtat.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        JFrame cadre = new JFrame("Step 1");
        cadre.setBounds(100, 50, 600, 500);
        cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cadre.add(this, BorderLayout.CENTER);
        cadre.add(barreEtat, BorderLayout.SOUTH);
        cadre.setVisible(true);
    }

    public static void main(String[] args) {
        new Step01();
    }
}