import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;

public class Main {

	public static JFrame FRAME = new JFrame("Rabbit Game");
	public static int WIDTH_FRAME = 1800;
	public static int HEIGHT_FRAME = 1000;
	public static int WIDTH_TEXT = 180;
	public static Level level;
	public static BufferedImage image;

	public static class MyPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(image, 0, 0, WIDTH_FRAME, HEIGHT_FRAME, null);
			Drawable.drawAll(g2d);

		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println(KeyEvent.VK_G + " " + KeyEvent.VK_V + " " + KeyEvent.VK_N);
		FRAME.setSize(WIDTH_FRAME + WIDTH_TEXT, HEIGHT_FRAME);
		FRAME.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		FRAME.setLayout(new BorderLayout());
		MyPanel panel = new MyPanel();
		FRAME.add(panel, BorderLayout.CENTER);
		FRAME.setVisible(true);
		image = ImageIO.read(new File("background.jpg"));
		level = new Level();
		FRAME.setFocusable(true);
		level.load();
		FRAME.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				level.keyTyped(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				level.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				level.keyPressed(e);

			}
		});
		Timer timer = new Timer(5, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				level.update();
			}
		});
		timer.start();
		Timer drawTimer = new Timer(20, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			FRAME.repaint();
			}
		});
		drawTimer.start();
		JPanel texts = new JPanel();
		texts.setMinimumSize(new Dimension(WIDTH_TEXT, HEIGHT_FRAME));
		texts.setPreferredSize(new Dimension(WIDTH_TEXT, HEIGHT_FRAME));
		texts.setMaximumSize(new Dimension(WIDTH_TEXT, HEIGHT_FRAME));
		texts.add(Box.createVerticalStrut(30));
		texts.setLayout(new BoxLayout(texts, BoxLayout.Y_AXIS));
		JLabel[] text = new JLabel[Level.sizeRabbit];
		for (int i = 0; i < text.length; i++) {
			text[i] = new JLabel("");
			text[i].setFont(new Font("", 30, 20));
			texts.add(text[i]);
		}
		Timer timerResult = new Timer(30, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int ind = 0;
				for (PhysicalObject po : PhysicalObject.po) {
					if (Rabbit.isRabbit(po)) {
						Rabbit rabbit = (Rabbit) po;
						String name = Integer.toString(rabbit.number + 1);
						if(po.getClass() == NamedRabbit.class){
							name = ((NamedRabbit) po).name;
						}
						text[ind].setText("  " + name + "  -  "
								+ Integer.toString(rabbit.result) + " x kill");
						ind++;
					}
				}
			}

		});
		timerResult.start();
		FRAME.add(texts, BorderLayout.EAST);
	}

}