import java.awt.Graphics2D;
import java.util.ArrayList;

public interface Drawable {
	public void draw(Graphics2D g2d);

	public ArrayList<Drawable> drawables = new ArrayList<Drawable>();

	public static void drawAll(Graphics2D g2d) {
		for (Drawable dr : drawables) {
			dr.draw(g2d);
		}
	}
}
