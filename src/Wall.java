import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends PhysicalObject implements Drawable {
	int x, y;
	int height, width;
	BufferedImage image;

	public Wall(int _x, int _y, int _width, int _height, String _image, boolean _j) throws IOException {
		x = _x;
		y = _y;
		width = _width;
		height = _height;
		image = ImageIO.read(new File(_image));
		jumpable = _j;
	}

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

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawRect(leftX(), upY(), width, height);
		g2d.drawImage(image, leftX(), upY(), width, height, null);
	}
}
