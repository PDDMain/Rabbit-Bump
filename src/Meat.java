import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;

public class Meat extends PhysicalObject implements Drawable {

	public static double ACCELERATION_OF_GRAVITY = 0.335;

	public Meat(int _x, int _y) throws IOException {
		Random r = new Random();
		x = _x;
		y = _y;
		vx = 5 * r.nextDouble();
		vy = 5 * r.nextDouble();
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawRect(leftX(), upY(), width, height);
	}

	public void update() {
		vy += ACCELERATION_OF_GRAVITY;
		x += vx;
		y += vy;
		for (PhysicalObject wall : PhysicalObject.po) {
			int block = isCollision(wall);
			if (block == BLOCKED_DOWN) {
				vy = 0;
			} else if (block == BLOCKED_UP) {
				if (vy < 0) {
					vy = 0;
				}
			} else if (block == BLOCKED_LEFT) {
				if (vx < 0) {
					vx = 0;
				}
				x = wall.rightX() + width / 2;
			} else if (block == BLOCKED_RIGHT) {
				if (vx > 0) {
					vx = 0;
				}
				x = wall.leftX() - width / 2;
			}

		}
	}
}
