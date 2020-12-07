package sample;

import javafx.scene.image.Image;

import java.util.Random;

public abstract class Mobile extends Entities {
    protected double velocity;
    protected int step = 0;

    protected enum DIRECTION {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    protected DIRECTION direction = DIRECTION.DOWN;
    protected boolean isDying = false;
    protected boolean alive = true;
    protected int deadTime = 24;

    public Mobile(double x, double y, double width, double height, Image image, double velocity) {
        super(x, y, width, height, image);
        this.velocity = velocity;
    }

    public void getRandomDirection() {
        long seed = new Random().nextLong();
        int pick = new Random(seed).nextInt(DIRECTION.values().length);
        direction = DIRECTION.values()[pick];
    }

    public DIRECTION getHorizontalDirection() {
        if (Main.player.x < this.x) {
            return DIRECTION.LEFT;
        } else if (Main.player.x > this.x) {
            return DIRECTION.RIGHT;
        }
        return null;
    }

    public DIRECTION getVerticalDirection() {
        if (Main.player.y < this.y) {
            return DIRECTION.UP;
        } else if (Main.player.y > this.y) {
            return DIRECTION.DOWN;
        }
        return null;
    }

    public void getCalculatedDirection() {
        if (!Main.entities.contains(Main.player)) {
            getRandomDirection();
        } else {
            int randomDirection = new Random().nextInt(2);
            if (randomDirection == 0) {
                direction = getVerticalDirection();
                if (direction == null) {
                    direction = getHorizontalDirection();
                }
            } else {
                direction = getHorizontalDirection();
                if (direction == null) {
                    direction = getVerticalDirection();
                }
            }
        }
    }

}
