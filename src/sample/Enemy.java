package sample;

import javafx.scene.image.Image;

public abstract class Enemy extends Mobile {

    public Enemy(double x, double y, double width, double height, Image image, double velocity) {
        super(x, y, width, height, image, velocity);
    }
}
