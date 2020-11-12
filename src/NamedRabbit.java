import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

public class NamedRabbit extends Rabbit {
	String name;

	public NamedRabbit(int _x, int _y, int _vx, int _vy, int w, int h, int left, int right, int up, String _image,
			String[] videoLen, boolean _superJump, int _number, String _name) throws IOException {
		super(_x, _y, _vx, _vy, w, h, left, right, up, _image, videoLen, _superJump, _number);
		name = _name;
	}

	public void draw(Graphics2D g) {
		super.draw(g);
		g.drawString(name, leftX(), upY());
	}
}
