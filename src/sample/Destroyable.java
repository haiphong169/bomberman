package sample;

import javafx.scene.image.Image;

public abstract class Destroyable extends Immobile {

    protected boolean removed = false;
    public boolean isBreaking = false;

    public Destroyable(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image);
    }


}
