import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
	public int[] result;
	public static Level level;
	// public static ArrayList<Rabbit> rabbits;
	// public static ArrayList<Wall> walls;

	public static int sizeRabbit;
	public static int sizeWall;

	public static Scanner sc;

	public void load() throws NumberFormatException, IOException {
		sc = new Scanner(new File("input.txt"));

		sizeRabbit = Integer.parseInt(sc.nextLine());
		// rabbits = new ArrayList<Rabbit>();
		PhysicalObject.po = new ArrayList<PhysicalObject>();
		for (int i = 0; i < sizeRabbit; i++) {
			String[] input = sc.nextLine().split(" ");
			NamedRabbit rabbit = new NamedRabbit(Integer.parseInt(input[0]), Integer.parseInt(input[1]),
					Integer.parseInt(input[2]), Integer.parseInt(input[3]), Rabbit.WIDTH, Rabbit.HEIGHT,
					Integer.parseInt(input[4]), Integer.parseInt(input[5]), Integer.parseInt(input[6]), input[7], input[8].split("-"),true, i, input[9]);
			// rabbits.add(rabbit);
			Drawable.drawables.add(rabbit);
			PhysicalObject.po.add(rabbit);
		}

		sizeWall = Integer.parseInt(sc.nextLine());
		// walls = new ArrayList<Wall>();
		for (int i = 0; i < sizeWall; i++) {
			String[] input = sc.nextLine().split(" ");
			Wall wall = new Wall(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]),
					Integer.parseInt(input[3]), input[4], false);
			// walls.add(wall);
			Drawable.drawables.add(wall);
			PhysicalObject.po.add(wall);
		}
		Wall downWall = new Wall(Main.WIDTH_FRAME / 2, Main.HEIGHT_FRAME - 60 + 250, 2 * Main.WIDTH_FRAME, 30 + 500, "wall.png", true);
		PhysicalObject.po.add(downWall);
		Wall leftWall = new Wall(0, Main.HEIGHT_FRAME/2, 10, 4*Main.HEIGHT_FRAME, "wall.png", false);
		PhysicalObject.po.add(leftWall);
		Wall rightWall = new Wall(Main.WIDTH_FRAME - 30, Main.HEIGHT_FRAME/2, 10, 4
				*Main.HEIGHT_FRAME, "wall.png", false);
		PhysicalObject.po.add(rightWall);
	}

	public void update() {
		for (PhysicalObject rabbit : PhysicalObject.po) {
			if (Rabbit.isRabbit(rabbit)) {
				rabbit.update();
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		for (PhysicalObject rabbit : PhysicalObject.po) {
			if (Rabbit.isRabbit(rabbit)) {
				rabbit.keyPressed(e);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		for (PhysicalObject rabbit : PhysicalObject.po) {
			if (Rabbit.isRabbit(rabbit)) {
				rabbit.keyReleased(e);
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		for (PhysicalObject rabbit : PhysicalObject.po) {
			if (Rabbit.isRabbit(rabbit)) {
				rabbit.keyReleased(e);
			}
		}		
	}
}
