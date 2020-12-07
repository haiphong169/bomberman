package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Oneal extends Enemy {

    public Oneal(double x, double y, double width, double height, Image image, double velocity) {
        super(x, y, width, height, image, velocity);
    }

    @Override
    public void update(ArrayList<String> input) {
        if (!isDying) {
            step++;
            getCalculatedDirection();
            switch (direction) {
                case LEFT:
                    for (Entities entity : Main.everything) {
                        if (!(entity instanceof Grass) && !(entity instanceof Bomber) && !(entity instanceof Enemy)) {
                            if (entity.intersectRight(this)) {
                                return;
                            }
                        }
                    }
                    x -= velocity;
                    switch (step % 15) {
                        case 0:
                            setImage("oneal_left1.png");
                            break;
                        case 5:
                            setImage("oneal_left2.png");
                            break;
                        case 10:
                            setImage("oneal_left3.png");
                            break;
                    }
                    break;
                case RIGHT:
                    for (Entities entity : Main.everything) {
                        if (!(entity instanceof Grass) && !(entity instanceof Bomber) && !(entity instanceof Enemy)) {
                            if (entity.intersectLeft(this)) {
                                return;
                            }
                        }
                    }
                    x += velocity;
                    switch (step % 15) {
                        case 0:
                            setImage("oneal_right1.png");
                            break;
                        case 5:
                            setImage("oneal_right2.png");
                            break;
                        case 10:
                            setImage("oneal_right3.png");
                            break;
                    }
                    break;
                case UP:
                    for (Entities entity : Main.everything) {
                        if (!(entity instanceof Grass) && !(entity instanceof Bomber) && !(entity instanceof Enemy)) {
                            if (entity.intersectDown(this)) {
                                return;
                            }
                        }
                    }
                    y -= velocity;
                    switch (step % 15) {
                        case 0:
                            setImage("oneal_right1.png");
                            break;
                        case 5:
                            setImage("oneal_right2.png");
                            break;
                        case 10:
                            setImage("oneal_right3.png");
                            break;
                    }
                    break;
                case DOWN:
                    for (Entities entity : Main.everything) {
                        if (!(entity instanceof Grass) && !(entity instanceof Bomber) && !(entity instanceof Enemy)) {
                            if (entity.intersectUp(this)) {
                                return;
                            }
                        }
                    }
                    y += velocity;
                    switch (step % 15) {
                        case 0:
                            setImage("oneal_left1.png");
                            break;
                        case 5:
                            setImage("oneal_left2.png");
                            break;
                        case 10:
                            setImage("oneal_left3.png");
                            break;
                    }
                    break;
            }
        } else {
            if (deadTime > 0) {
                switch (deadTime % 1000) {
                    case 24:
                        sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\dead.mp3");
                        setImage("oneal_dead.png");
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
