import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class AnimationVideo {
	ArrayList<ArrayList<BufferedImage>> video;
	int length = 4;
	int frameNumber = 0;
	int frameTime = 120;
	int videoNum = 0;

	public static int goNot = 0;
	public static int goLeft = 2;
	public static int goRight = 1;
	public static int goUp = 3;

	public AnimationVideo(String input, String[] videoLen) throws IOException {
		video = new ArrayList<ArrayList<BufferedImage>>();
		for (int i = 0; i < length; i++) {
			video.add(new ArrayList<BufferedImage>());
			for (int j = 0; j < Integer.parseInt(videoLen[i]); j++) {
				video.get(i).add(ImageIO.read(new File(input + "-" + Integer.toString(i) + "-" + Integer.toString(j) + ".png")));
			}
		}

		Timer timer = new Timer(frameTime, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frameNumber = (frameNumber + 1) % length;
			}
		});
		
		timer.start();
	}

	public AnimationVideo(String input, int len, String[] videoLen, int time) throws IOException {
		frameTime = time;
		video = new ArrayList<ArrayList<BufferedImage>>();
		for (int i = 0; i < length; i++) {
			video.add(new ArrayList<BufferedImage>());
			for (int j = 0; j < Integer.parseInt(videoLen[i]); j++) {
				video.get(i).add(ImageIO.read(new File(input + "-" + Integer.toString(i))));
			}
		}

		Timer timer = new Timer(frameTime, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frameNumber = (frameNumber + 1) % length;
			}
		});
	}

	public BufferedImage getImage() {
		return video.get(videoNum).get(frameNumber);
	}
}
