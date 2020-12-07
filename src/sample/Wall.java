package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Wall extends Immobile {

    public Wall(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image);
    }

    @Override
    public void update(ArrayList<String> input) {

    }
}
