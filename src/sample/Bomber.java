package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Bomber extends Mobile {

    public Bomber(double x, double y, double width, double height, Image image, double velocity) {
        super(x, y, width, height, image, velocity);
    }

    public void resetStep() {
        step = 0;
    }

    @Override
    public void update(ArrayList<String> input) {
        if (!isDying) {
            for (Entities entity : Main.everything) {
                if (intersectRight(entity) || intersectLeft(entity) || intersectUp(entity) || intersectDown(entity)) {
                    if (entity instanceof Special && ((Special) entity).ready) {
                        if (entity instanceof FireBuff) {
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\buff.wav");
                            Bomb.range++;
                            ((FireBuff) entity).used = true;
                        }
                        if(entity instanceof BombBuff) {
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\buff.wav");
                            Bomb.limit++;
                            ((BombBuff) entity).used = true;
                        }
                        if(entity instanceof SpeedBuff) {
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\buff.wav");
                            Main.player.velocity *= 2;
                            ((SpeedBuff) entity).used = true;
                        }
                        if (entity instanceof Portal && Main.entities.size()==1){
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\portal.wav");
                            Main.over = true;
                            Main.next_level = true;
                        }
                    } else if (entity instanceof Enemy) {
                        if (intersectRight(entity)) {
                            if (getBoundary().getMaxX()-entity.getBoundary().getMinX()<=10) {
                                isDying = true;
                            }
                        } else {
                            isDying = true;
                        }
                    }
                }
            }
            if (!input.isEmpty()) {
                int allowedDistance = 5;
                if (input.contains("LEFT")) {
                    for (Entities entity : Main.everything) {
                        if (!(entity instanceof Grass)) {
                            if (entity.intersectRight(this)) {
                                if(entity.getBoundary().getMaxY() - getBoundary().getMinY() <= allowedDistance){
                                    y+= entity.getBoundary().getMaxY() - getBoundary().getMinY();
                                }
                                if(getBoundary().getMaxY() - entity.getBoundary().getMinY() <= allowedDistance){
                                    y-= getBoundary().getMaxY() - entity.getBoundary().getMinY();
                                }
                                return;
                            }
                        }
                    }
                    direction = DIRECTION.LEFT;
                    x -= velocity;
                    step++;
                    switch (step % 15) {
                        case 0:
                            setImage("player_left_0.png");
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\footstep1.mp3");
                            break;
                        case 5:
                            setImage("player_left_1.png");
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\footstep2.mp3");
                            break;
                        case 10:
                            setImage("player_left_2.png");
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\footstep1.mp3");
                            break;
                    }
                }
                if (input.contains("RIGHT")) {
                    for (Entities entity : Main.everything) {
                        if (!(entity instanceof Grass)) {
                            if (entity.intersectLeft(this)) {
                                if(entity.getBoundary().getMaxY() - getBoundary().getMinY() <= allowedDistance){
                                    y+= entity.getBoundary().getMaxY() - getBoundary().getMinY();
                                }
                                if(getBoundary().getMaxY() - entity.getBoundary().getMinY() <= allowedDistance){
                                    y-= getBoundary().getMaxY() - entity.getBoundary().getMinY();
                                }
                                return;
                            }
                        }
                    }
                    direction = DIRECTION.RIGHT;
                    x += velocity;
                    step++;
                    switch (step % 15) {
                        case 0:
                            setImage("player_right_0.png");
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\footstep1.mp3");
                            break;
                        case 5:
                            setImage("player_right_1.png");
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\footstep2.mp3");
                            break;
                        case 10:
                            setImage("player_right_2.png");
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\footstep1.mp3");
                            break;
                    }
                }
                if (input.contains("DOWN")) {
                    for (Entities entity : Main.everything) {
                        if (!(entity instanceof Grass)) {
                            if (entity.intersectUp(this)) {
                                if(entity.getBoundary().getMaxX() - getBoundary().getMinX() <= allowedDistance){
                                    x+=entity.getBoundary().getMaxX()-getBoundary().getMinX();
                                }
                                if(getBoundary().getMaxX() -  entity.getBoundary().getMinX() <= allowedDistance + 5){
                                    x-=getBoundary().getMaxX() - entity.getBoundary().getMinX();
                                }
                                return;
                            }
                        }
                    }
                    direction = DIRECTION.DOWN;
                    y += velocity;
                    step++;
                    switch (step % 15) {
                        case 0:
                            setImage("player_down_0.png");
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\footstep1.mp3");
                            break;
                        case 5:
                            setImage("player_down_1.png");
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\footstep2.mp3");
                            break;
                        case 10:
                            setImage("player_down_2.png");
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\footstep1.mp3");
                            break;
                    }
                }
                if (input.contains("UP")) {
                    for (Entities entity : Main.everything) {
                        if (!(entity instanceof Grass)) {
                            if (entity.intersectDown(this)) {
                                if(entity.getBoundary().getMaxX() - getBoundary().getMinX() <= allowedDistance){
                                    x+=entity.getBoundary().getMaxX()-getBoundary().getMinX();
                                }
                                if(getBoundary().getMaxX() -  entity.getBoundary().getMinX() <= allowedDistance + 5){
                                    x-=getBoundary().getMaxX() - entity.getBoundary().getMinX();
                                }
                                return;
                            }
                        }
                    }
                    direction = DIRECTION.UP;
                    y -= velocity;
                    step++;
                    switch (step % 15) {
                        case 0:
                            setImage("player_up_0.png");
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\footstep1.mp3");
                            break;
                        case 5:
                            setImage("player_up_1.png");
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\footstep2.mp3");
                            break;
                        case 10:
                            setImage("player_up_2.png");
                            sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\footstep1.mp3");
                            break;
                    }
                }
                if (input.contains("SPACE")) {
                    if (Main.bombs.size() < Bomb.limit) {
                        double bombX = x;
                        double bombY = y;
                        while (bombX % Main.SCALE_CONSTANT != 0) {
                            if(direction.equals(DIRECTION.LEFT)){
                                bombX--;
                            }
                            if(direction.equals(DIRECTION.RIGHT)){
                                bombX++;
                            }
                        }
                        while (bombY % Main.SCALE_CONSTANT != 0) {
                            if(direction.equals(DIRECTION.UP)){
                                bombY--;
                            }
                            if(direction.equals(DIRECTION.DOWN)){
                                bombY++;
                            }
                        }
                        Bomb bomb = new Bomb(bombX, bombY, Main.SCALE_CONSTANT, Main.SCALE_CONSTANT, new Image("bomb.png"));
                        Main.bombs.add(bomb);
                        Main.everything.add(bomb);
                        input.remove("SPACE");
                    }
                }
            } else {
                switch (direction) {
                    case LEFT:
                        setImage("player_left_0.png");
                        break;
                    case RIGHT:
                        setImage("player_right_0.png");
                        break;
                    case UP:
                        setImage("player_up_0.png");
                        break;
                    case DOWN:
                        setImage("player_down_0.png");
                        break;
                }
            }
        } else {
            switch (deadTime) {
                case 24:
                    sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\dead.mp3");
                    setImage("player_dead1.png");
                    break;
                case 16:
                    setImage("player_dead2.png");
                    break;
                case 8:
                    setImage("player_dead3.png");
                    break;
                case 0:
                    alive = false;
                    break;
            }
            deadTime--;
        }
    }
}
