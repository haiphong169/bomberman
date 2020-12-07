package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class Special extends Entities {

    protected boolean ready = false;
    protected boolean used = false;

    public Special(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image);
    }

    @Override
    public void update(ArrayList<String> input) {
        int count = 0;
        for(Immobile immobile : Main.immobiles){
            if(immobile instanceof Brick){
                if(immobile.getBoundary().getMinX() == getBoundary().getMinX()
                        && immobile.getBoundary().getMinY() == getBoundary().getMinY()){
                    count++;
                }
            }
        }
        if(count == 0){
            ready = true;
        }
    }

}
