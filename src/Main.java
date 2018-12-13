//Polzik Daniel, 2019a
//23.11.2018
//Island


import java.awt.*;
import javax.swing.*;

public class Main {

	static JFrame FRAME = new JFrame("Island");

	public static class MyPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(new Color(181, 243, 255));
			g2d.fillRect(0, 0, 1800, 1000);

			g2d.setColor(new Color(234, 240, 249));
			g2d.fillOval(150, 120, 600, 150);
			g2d.fillOval(250, 90, 150, 100);
			g2d.fillOval(350, 50, 200, 200);
			g2d.fillOval(500, 40, 270, 270);
			g2d.setColor(new Color(181, 243, 255));
			g2d.fillRect(150, 195, 700, 200);

			g2d.setColor(new Color(56, 158, 255));
			g2d.fillRect(0, 600, 1800, 400);

			g2d.setColor(new Color(204, 151, 136));
			g2d.fillRect(700, 400, 150, 150);

			g2d.setColor(new Color(70, 186, 27));
			g2d.fillOval(420 - 5, 520, 750, 200);

			g2d.setColor(new Color(56, 158, 255));
			g2d.fillRect(0, 600, 1800, 400);

			g2d.setColor(new Color(255, 86, 30));
			int[] x = new int[3];
			int[] y = new int[3];
			x[0] = 680;
			y[0] = 400;
			x[1] = 870;
			y[1] = 400;
			x[2] = 775;
			y[2] = 300;
			g2d.fillPolygon(x, y, 3);

			g2d.setColor(new Color(56, 158, 255));
			for (int i = 0; i < 26; i++) {
				g2d.fillOval(70 * i - 5, 590, 40, 40);
			}
			g2d.setColor(new Color(181, 243, 255));
			for (int i = 0; i < 6; i++) {
				g2d.fillOval(30 + 70 * i, 570, 40, 40);
			}
			for (int i = 16; i < 26; i++) {
				g2d.fillOval(30 + 70 * i, 570, 40, 40);
			}

			g2d.setColor(new Color(70, 186, 27));
			for (int i = 6; i < 16; i++) {
				g2d.fillOval(30 + 70 * i, 570, 40, 40);
			}

			g2d.setColor(new Color(231, 255, 124));
			g2d.fillRect(750, 438, 50, 50);

			g2d.setColor(new Color(204, 151, 136));
			g2d.fillRect(750, 460, 50, 6);
			g2d.fillRect(772, 438, 6, 50);

			g2d.setColor(Color.YELLOW);
			for (int i = 0; i < 20; i++) {
				double a = 2.0 * i * Math.PI / 20;
				g2d.drawLine(1375, 175, 1375 + (int) (200 * Math.sin(a)), 175 + (int) (200 * Math.cos(a)));
			}
			g2d.setColor(new Color(181, 243, 255));
			g2d.fillOval(1285, 85, 180, 180);
			g2d.setColor(Color.YELLOW);
			g2d.fillOval(1300, 100, 150, 150);

		}
	}

	public static void main(String[] args) {
		FRAME.setSize(1800, 1000);
		FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		FRAME.setLayout(new BorderLayout());
		MyPanel panel = new MyPanel();
		FRAME.add(panel);
		FRAME.setVisible(true);
	}

}