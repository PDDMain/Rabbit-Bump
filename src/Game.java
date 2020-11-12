import java.util.ArrayList;

public class Game {
	static ArrayList<PhysicalObject> added;
	static ArrayList<PhysicalObject> deleted;
	
	public static void addListener(PhysicalObject po){
		added.add(po);
	}
	
	public static void deleteListener(PhysicalObject po){
		deleted.add(po);
	}
	
	public void updateListener() {
		for(PhysicalObject po : added) {
			PhysicalObject.po.add(po);
			Drawable.drawables.add((Drawable) po);
		}
		for(PhysicalObject po : deleted) {
			PhysicalObject.po.remove(po);
			Drawable.drawables.remove(po);
		}
	}
}
