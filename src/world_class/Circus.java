package world_class;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import strategy_difficulty.Strategy;
import to_come.ImageObject;

import java.util.LinkedList;
import java.util.List;

public class Circus implements World {

	private static int MAX_TIME = 1 * 60 * 1000; // 1 minute
	private static int RIGHT_ROCKET = 123456;
	private static int LEFT_ROCKET = 654321;
	private int score = 0;
	private boolean fireRocket = false;
	private long startTime = System.currentTimeMillis();
	private final int width;
	private final int height;
	private Strategy difficulty;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private final List<GameObject> moving = new LinkedList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();

	public Circus(int width, int height, Strategy difficulty) {
		this.width = width;
		this.height = height;
		this.difficulty = difficulty;
	}

	private boolean intersect(GameObject o1, GameObject o2) {
		return (Math.abs((o1.getX() + o1.getWidth() / 2) - (o2.getX() + o2.getWidth() / 2)) <= o1.getWidth())
				&& (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
	}

	@Override
	public List<GameObject> getConstantObjects() {
		return constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		return moving;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return control;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public boolean refresh() {
		boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over

		// get Clown from here
		ImageObject fighter = (ImageObject) control.get(0);

		// handle moving plates here
		for (GameObject o : moving.toArray(new GameObject[moving.size()])) {
			o.setY((o.getY() + 1));
			if (o.getY() >= getHeight()) {
				// reuse the plate in another position
				o.setY(-1 * (int) (Math.random() * getHeight()));
				o.setX((int) (Math.random() * getWidth()));
			}
			o.setX(o.getX() + (Math.random() > 0.5 ? 2 : -2));
			if (!timeout & o.isVisible() && intersect(o, fighter)) {
				
				// clown caught a plate here
			}
		}

		return !timeout;
	}

	@Override
	public String getStatus() {
		return "Score=" + score + "   |   Time="
				+ Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000); // update status
	}

	@Override
	public int getSpeed() {
		return difficulty.speed();
	}

	@Override
	public int getControlSpeed() {
		return 20;
	}
}
