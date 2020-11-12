import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Rabbit extends PhysicalObject implements Drawable {
	AnimationVideo video;
	int buttonLeft, buttonRight, buttonUp;
	boolean possibleMoveUp, possibleMoveDown, possibleMoveLeft, possibleMoveRight;
	int startX, startY;
	int quantityJump = MAX_QUANT_JUMP;
	boolean superJump;
	boolean isPressedLeft, isPressedRight;
	int number, result;
	Random random = new Random();

	public static int HEIGHT = 95;
	public static int WIDTH = 65;
	public static double ACCELERATION_OF_GRAVITY = 0.325;
	public static int MAX_DELTA_Y = 15;
	public static int SPEED_X = 5;
	public static int SPEED_Y = -15;
	public static int MAX_QUANT_JUMP = 1;
	public static int DECREASE_VX = 10;
	public static int DECREASE_VY = 10;
	public static int MAX_SHOT_DISTANCE = 5;
	public static double DEATH_CONST = 0.65;

	public static boolean isRabbit(Object c) {
		if (c.getClass() == Rabbit.class || c.getClass() == NamedRabbit.class) {
			return true;
		} else {
			return false;
		}
	}

	public Rabbit(int _x, int _y, int _vx, int _vy, int w, int h, int left, int right, int up, String _image,
			String[] videoLen, boolean _superJump, int _number) throws IOException {
		x = _x;
		y = _y;
		startX = _x;
		startY = _y;
		vx = 0;
		vy = 0;
		width = w;
		height = h;
		buttonLeft = left;

		buttonRight = right;
		buttonUp = up;
		video = new AnimationVideo(_image, videoLen);
		jumpable = false;
		superJump = _superJump;
		isPressedLeft = isPressedRight = false;
		number = _number;
		result = 0;
	}

	public void draw(Graphics2D g) {
		g.drawImage(video.getImage(), leftX(), upY(), width, height, null);
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

	public void update() {
		possibleMoveUp = possibleMoveDown = possibleMoveLeft = possibleMoveRight = true;
		vy += ACCELERATION_OF_GRAVITY;
		x += vx;
		y += vy;
		for (PhysicalObject wall : PhysicalObject.po) {
			int block = isCollision(wall);
			if (block == BLOCKED_DOWN) {
				video.videoNum = AnimationVideo.goNot;
				quantityJump = 0;
				if (vy > 0) {
					if (wall.jumpable) {
						quantityJump++;
						if (Math.abs(vy) > Math.abs(PhysicalObject.MAX_SPEED_JUMPABLE)) {
							vy = -vy;
						} else {
							vy = PhysicalObject.MAX_SPEED_JUMPABLE;
						}
					} else {
						vy = 0;
					}
				}
				y = wall.upY() - height / 2;
				possibleMoveDown = false;
			} else if (block == BLOCKED_UP) {
				if (vy < 0) {
					vy = 0;
				}
				y = wall.downY() + height / 2;
				possibleMoveUp = false;
				if (Rabbit.isRabbit(wall) && Math.abs(wall.x - x) < DEATH_CONST * Math.min(wall.width, width)) {
					Rabbit rabbit = (Rabbit) wall;
					rabbit.result++;
					rabbit.quantityJump = Rabbit.MAX_QUANT_JUMP;
					Random r = new Random();
					x = (int) (r.nextDouble() * Main.WIDTH_FRAME);
					y = (int) (-r.nextDouble() / 3 * Main.HEIGHT_FRAME);
					vx = 0;
					vy = 0;
					quantityJump = MAX_QUANT_JUMP;
					// for(int i = 0; i < 5; i++) {
					// Meat meat = null;
					// try {
					// meat = new Meat(x, y);
					// } catch (IOException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					// Game.addListener(meat);
					// }
				}
			} else if (block == BLOCKED_LEFT) {
				if (vx < 0) {
					vx = 0;
					if (Rabbit.isRabbit(wall)) {
						if (wall.vx > 0) {
							if (random.nextBoolean()) {
								wall.x -= MAX_SHOT_DISTANCE;
							}
//							} else {
//								x += MAX_SHOT_DISTANCE;
//							}
						} else {
							wall.x -= MAX_SHOT_DISTANCE;
						}
					}
				}
				x = wall.rightX() + width / 2;
				possibleMoveLeft = false;
			} else if (block == BLOCKED_RIGHT) {
				if (vx > 0) {
 					vx = 0;
					if (Rabbit.isRabbit(wall)) {
						if (wall.vx < 0) {
							if (random.nextBoolean()) {
								wall.x += MAX_SHOT_DISTANCE;
							}
//							} else {
//								x -= MAX_SHOT_DISTANCE;
//							}
						} else {
							wall.x += MAX_SHOT_DISTANCE;
						}
					}
				}
				x = wall.leftX() - width / 2;
				possibleMoveRight = false;
			}

			if (isPressedLeft) {
				video.videoNum = AnimationVideo.goLeft;
				if (possibleMoveLeft) {
					vx = -SPEED_X;
				}
			} else if (isPressedRight) {
				video.videoNum = AnimationVideo.goRight;
				if (possibleMoveRight) {
					vx = SPEED_X;
				}
			}

			if (possibleMoveDown) {
				video.videoNum = AnimationVideo.goUp;
			}
		}

		// if (rightX() >= wall.leftX() && leftX() <= wall.rightX()) {
		// if (downY() - wall.upY() < MAX_DELTA_Y || wall.downY() - upY() < MAX_DELTA_Y)
		// {
		// if (downY() >= wall.upY() && y <= wall.y) { // низ кролика ниже верха стены
		// if (vy > 0) {
		// vy = 0;
		// }
		// y = wall.upY() - height / 2;
		// possibleMoveDown = false;
		// } else if (upY() >= wall.downY() && y > wall.y) { // верх кролика выше низа
		// стены
		// if (vy < 0) {
		// vy = 0;
		// }
		// y = wall.downY() + height / 2;
		// possibleMoveUp = false;
		// }
		// } else {
		// if (rightX() >= wall.leftX() && x <= wall.x) { // правая грань кролика правее
		// левой
		// if (vx > 0) {
		// vx = 0;
		// }
		// x = wall.leftX() - width / 2;
		// possibleMoveRight = true;
		// } else if (leftX() < wall.rightX() && x > wall.x) {
		// if (vx < 0) {
		// vx = 0;
		// }
		// x = wall.leftX() + width / 2;
		// possibleMoveLeft = true;
		// }
		// }
		// }
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == buttonLeft) {
			isPressedLeft = true;
			if (possibleMoveLeft) {
				vx = -SPEED_X;
			}
		} else if (e.getKeyCode() == buttonRight) {
			isPressedRight = true;
			if (possibleMoveRight) {
				vx = SPEED_X;

			}
		} else if (e.getKeyCode() == buttonUp) {
			if (possibleMoveUp && !possibleMoveDown) {
				video.videoNum = AnimationVideo.goUp;
				video.frameNumber = 0;
				vy = SPEED_Y;
				quantityJump++;
			} else if (quantityJump < MAX_QUANT_JUMP) {
				vy = SPEED_Y;
				quantityJump++;
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == buttonLeft) {
			isPressedLeft = false;
			if (vx < 0) {
				vx = 0;
			}
		} else if (e.getKeyCode() == buttonRight) {
			isPressedRight = false;
			if (vx > 0) {
				vx = 0;
			}
		}

	}
}
