package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Flame extends Destroyable {

    public String direction;
    private int flameTime = 9;

    public Flame(double x, double y, double width, double height, Image image, String direction) {
        super(x, y, width, height, image);
        this.direction = direction;
    }

    @Override
    public void update(ArrayList<String> input) {
        switch (direction) {
            case "up":
                switch (flameTime) {
                    case 9:
                        setImage("explosion_vertical_top_last.png");
                        break;
                    case 6:
                        setImage("explosion_vertical_top_last1.png");
                        break;
                    case 3:
                        setImage("explosion_vertical_top_last2.png");
                        break;
                    case 0:
                        removed = true;
                }
                break;
            case "mid_up":
                switch (flameTime) {
                    case 9:
                        setImage("explosion_vertical.png");
                        break;
                    case 6:
                        setImage("explosion_vertical1.png");
                        break;
                    case 3:
                        setImage("explosion_vertical2.png");
                        break;
                    case 0:
                        removed = true;
                        break;
                }
                break;
            case "down":
                switch (flameTime) {
                    case 9:
                        setImage("explosion_vertical_down_last.png");
                        break;
                    case 6:
                        setImage("explosion_vertical_down_last1.png");
                        break;
                    case 3:
                        setImage("explosion_vertical_down_last2.png");
                        break;
                    case 0:
                        removed = true;
                        break;
                }
                break;
            case "mid_down":
                switch (flameTime) {
                    case 9:
                        setImage("explosion_vertical.png");
                        break;
                    case 6:
                        setImage("explosion_vertical1.png");
                        break;
                    case 3:
                        setImage("explosion_vertical2.png");
                        break;
                    case 0:
                        removed = true;
                        break;
                }
                break;
            case "right":
                switch (flameTime) {
                    case 9:
                        setImage("explosion_horizontal_right_last.png");
                        break;
                    case 6:
                        setImage("explosion_horizontal_right_last1.png");
                        break;
                    case 3:
                        setImage("explosion_horizontal_right_last2.png");
                        break;
                    case 0:
                        removed = true;
                        break;
                }
                break;
            case "mid_right":
                switch (flameTime) {
                    case 9:
                        setImage("explosion_horizontal.png");
                        break;
                    case 6:
                        setImage("explosion_horizontal1.png");
                        break;
                    case 3:
                        setImage("explosion_horizontal2.png");
                        break;
                    case 0:
                        removed = true;
                        break;
                }
                break;
            case "left":
                switch (flameTime) {
                    case 9:
                        setImage("explosion_horizontal_left_last.png");
                        break;
                    case 6:
                        setImage("explosion_horizontal_left_last1.png");
                        break;
                    case 3:
                        setImage("explosion_horizontal_left_last2.png");
                        break;
                    case 0:
                        removed = true;
                        break;
                }
                break;
            case "mid_left":
                switch (flameTime) {
                    case 9:
                        setImage("explosion_horizontal.png");
                        break;
                    case 6:
                        setImage("explosion_horizontal1.png");
                        break;
                    case 3:
                        setImage("explosion_horizontal2.png");
                        break;
                    case 0:
                        removed = true;
                        break;
                }
                break;
        }
        flameTime--;
    }
}
