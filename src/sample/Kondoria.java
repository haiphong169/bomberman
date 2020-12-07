package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Kondoria extends Enemy{

    public Kondoria(double x, double y, double width, double height, Image image, double velocity) {
        super(x, y, width, height, image, velocity);
    }

    @Override
    public void update(ArrayList<String> input) {
        if (!isDying) {
            step++;
            switch (direction) {
                case LEFT:
                    for (Entities entity : Main.everything) {
                        if (entity instanceof Wall && entity.x == 0) {
                            if (entity.intersectRight(this)) {
                                getRandomDirection();
                                return;
                            }
                        }
                    }
                    x -= velocity;
                    switch (step % 15) {
                        case 0:
                            setImage("kondoria_left1.png");
                            break;
                        case 5:
                            setImage("kondoria_left2.png");
                            break;
                        case 10:
                            setImage("kondoria_left3.png");
                            break;
                    }
                    break;
                case RIGHT:
                    for (Entities entity : Main.everything) {
                        if (entity instanceof Wall && entity.x == 30 * Main.SCALE_CONSTANT) {
                            if (entity.intersectLeft(this)) {
                                getRandomDirection();
                                return;
                            }
                        }
                    }
                    x += velocity;
                    switch (step % 15) {
                        case 0:
                            setImage("kondoria_right1.png");
                            break;
                        case 5:
                            setImage("kondoria_right2.png");
                            break;
                        case 10:
                            setImage("kondoria_right3.png");
                            break;
                    }
                    break;
                case UP:
                    for (Entities entity : Main.everything) {
                        if (entity instanceof Wall && entity.y == 0) {
                            if (entity.intersectDown(this)) {
                                getRandomDirection();
                                return;
                            }
                        }
                    }
                    y -= velocity;
                    switch (step % 15) {
                        case 0:
                            setImage("kondoria_right1.png");
                            break;
                        case 5:
                            setImage("kondoria_right2.png");
                            break;
                        case 10:
                            setImage("kondoria_right3.png");
                            break;
                    }
                    break;
                case DOWN:
                    for (Entities entity : Main.everything) {
                        if (entity instanceof Wall && entity.y == 12 * Main.SCALE_CONSTANT) {
                            if (entity.intersectUp(this)) {
                                getRandomDirection();
                                return;
                            }
                        }
                    }
                    y += velocity;
                    switch (step % 15) {
                        case 0:
                            setImage("kondoria_left1.png");
                            break;
                        case 5:
                            setImage("kondoria_left2.png");
                            break;
                        case 10:
                            setImage("kondoria_left3.png");
                            break;
                    }
                    break;
            }
        } else {
            if (deadTime > 0) {
                switch (deadTime % 1000) {
                    case 24:
                        sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\dead.mp3");
                        setImage("kondoria_dead.png");
                        break;
                    case 18:
                        setImage("mob_dead1.png");
                        break;
                    case 12:
                        setImage("mob_dead2.png");
                        break;
                    case 6:
                        setImage("mob_dead3.png");
                        break;
                }
                deadTime--;
            } else {
                alive = false;
            }
        }
    }
}
