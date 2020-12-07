package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;

import java.util.ArrayList;

public abstract class Entities {
    protected double x, y;
    protected double width, height;
    protected Image image;
    public static final int SPRITE_SHEET_COLOR = 0xffff00ff;
    protected static final int SIZE = 16;
    public Sound sound = new Sound();

    public Entities(double x, double y, double width, double height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        getTransparentImage();
    }

    public void setImage(String path){
        image = new Image(path);
        getTransparentImage();
    }

    public abstract void update(ArrayList<String> input);

    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x, y, width, height);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, width, height);
    }

    public void getTransparentImage() {
        PixelReader pixelReader = image.getPixelReader();
        int[][] pixels = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                pixels[i][j] = pixelReader.getArgb(i, j);
            }
        }
        WritableImage writableImage = new WritableImage(SIZE, SIZE);
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(pixels[i][j]== SPRITE_SHEET_COLOR){
                    pixelWriter.setArgb(i,j,0);
                }
                else{
                    pixelWriter.setArgb(i,j,pixels[i][j]);
                }
            }
        }
        image = new ImageView(writableImage).getImage();
    }

    public boolean intersectLeft(Entities entities) {
        return getBoundary().getMinX() == entities.getBoundary().getMaxX()
                && ((entities.getBoundary().getMinY() >= getBoundary().getMinY()
                && entities.getBoundary().getMinY() < getBoundary().getMaxY())
                || (entities.getBoundary().getMaxY() > getBoundary().getMinY()
                && entities.getBoundary().getMaxY() <= getBoundary().getMaxY()));
    }

    public boolean intersectRight(Entities entities) {
        return getBoundary().getMaxX() == entities.getBoundary().getMinX()
                && ((entities.getBoundary().getMinY() >= getBoundary().getMinY()
                && entities.getBoundary().getMinY() < getBoundary().getMaxY())
                || (entities.getBoundary().getMaxY() > getBoundary().getMinY()
                && entities.getBoundary().getMaxY() <= getBoundary().getMaxY()));
    }

    public boolean intersectUp(Entities entities) {
        return getBoundary().getMinY() == entities.getBoundary().getMaxY()
                && ((entities.getBoundary().getMinX() >= getBoundary().getMinX()
                && entities.getBoundary().getMinX() < getBoundary().getMaxX())
                || (entities.getBoundary().getMaxX() > getBoundary().getMinX()
                && entities.getBoundary().getMaxX() <= getBoundary().getMaxX()));
    }

    public boolean intersectDown(Entities entities) {
        return getBoundary().getMaxY() == entities.getBoundary().getMinY()
                && ((entities.getBoundary().getMinX() >= getBoundary().getMinX()
                && entities.getBoundary().getMinX() < getBoundary().getMaxX())
                || (entities.getBoundary().getMaxX() > getBoundary().getMinX()
                && entities.getBoundary().getMaxX() <= getBoundary().getMaxX()));
    }


}
