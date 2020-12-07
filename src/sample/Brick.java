package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Brick extends Destroyable {

    private int breakTime = 9;

    public Brick(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image);
    }

    @Override
    public void update(ArrayList<String> input) {
        if (isBreaking) {
            if (breakTime > 0) {
                switch (breakTime % 10) {
                    case 9:
                        setImage("brick_exploded.png");
                        break;
                    case 6:
                        setImage("brick_exploded1.png");
                        break;
                    case 3:
                        setImage("brick_exploded2.png");
                        break;
                }
                breakTime--;
            } else {
                removed = true;
            }
        }
    }
}
