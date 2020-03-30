package graphe;

import java.awt.*;
import javax.swing.*;

public class Etape00 extends JPanel {

	Etape00() {
        this.setBackground(Color.WHITE);

        JFrame cadre = new JFrame("Step 0");
        cadre.setBounds(100, 50, 600, 500);
        cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cadre.add(this);
        cadre.setVisible(true);
    }

    public static void main(String[] args) {
        new Etape00();
    }
}
