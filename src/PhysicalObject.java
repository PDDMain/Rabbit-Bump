import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PhysicalObject {
	public static ArrayList<PhysicalObject> po;
	double vx, vy;
	int x, y;
	int height, width;
	boolean jumpable;

	public static int MAX_DELTA_Y = 20;
	public static int BLOCKED_NONE = 0;
	public static int BLOCKED_UP = 1;
	public static int BLOCKED_DOWN = 2;
	public static int BLOCKED_LEFT = 3;
	public static int BLOCKED_RIGHT = 4;
	public static int DELTA_SPEED_JUMPABLE = -20;
	public static int MAX_SPEED_JUMPABLE = -20;

	public int leftX() {
		return x - width / 2;
	}

	public int rightX() {
		return x + width / 2;
	}

	public int upY() {
		return y - height / 2;
	}

	public int downY() {
		return y + height / 2;
	}

	public int isCollision(PhysicalObject other) {
		if (!this.equals(other) && rightX() >= other.leftX() && leftX() <= other.rightX() && upY() - 5 <= other.downY()
				&& downY() >= other.upY()) {
			int y1 = downY() - other.upY();
			int y2 = other.downY() - upY();
			int x1 = rightX() - other.leftX();
			int x2 = other.rightX() - leftX();
			if (Math.min(x1, x2) < Math.min(y1, y2)) {
				if (x1 < x2) {
					return BLOCKED_RIGHT;
				} else {
					return BLOCKED_LEFT;
				}
			} else {
				if (y1 < y2) {
					return BLOCKED_DOWN;
				} else {
					return BLOCKED_UP;
				}
			}
		}
		return BLOCKED_NONE;
	}

	public void update() {
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
