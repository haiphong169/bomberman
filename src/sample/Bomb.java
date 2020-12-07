package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Bomb extends Destroyable {

    private int timeUntilExplode = 150;
    private int flameTime = 9;
    public static int range = 2;
    public static int limit = 2;

    public Bomb(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image);
    }

    @Override
    public void update(ArrayList<String> input) {
        if (!isBreaking) {
            if (timeUntilExplode > 0) {
                if (timeUntilExplode > 1) {
                    switch (timeUntilExplode % 18) {
                        case 17:
                            setImage("bomb.png");
                            break;
                        case 11:
                            setImage("bomb_1.png");
                            break;
                        case 5:
                            setImage("bomb_2.png");
                            break;
                    }
                } else {
                    sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\explosion.wav");
                }
                timeUntilExplode--;
            } else {
                explode();
            }
        } else {
            explode();
        }
    }

    public void explode() {
        if (flameTime == 9) {
            for (int i = -1; i >= -range; i--) {
                boolean willBreak = false;
                Entities entity = Main.getObjectAt(getBoundary().getMinX(), getBoundary().getMinY() + i * Main.SCALE_CONSTANT);
                if (entity instanceof Wall) {
                    break;
                } else if (entity instanceof Destroyable) {
                    if (entity instanceof Bomb) {
                        sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\explosion.wav");
                    }
                    ((Destroyable) entity).isBreaking = true;
                    break;
                } else {
                    entity = Main.getGrassAt(getBoundary().getMinX(),getBoundary().getMinY() + i * Main.SCALE_CONSTANT);
                    for(Entities entities : Main.entities) {
                        if(entities.getBoundary().intersects(entity.getBoundary())) {
                            ((Mobile)entities).isDying = true;
                            willBreak = true;
                        }
                    }
                    if(willBreak) {
                        break;
                    } else {
                        Entities entity2 = Main.getObjectAt(getBoundary().getMinX(), getBoundary().getMinY() + i * Main.SCALE_CONSTANT - Main.SCALE_CONSTANT);
                        if (entity2 instanceof Destroyable || entity2 instanceof Mobile || entity2 instanceof Wall) {
                            Flame flame = new Flame(getBoundary().getMinX(), getBoundary().getMinY() + i * Main.SCALE_CONSTANT, Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_vertical_top_last.png"), "up");
                            Main.immobiles.add(flame);
                        } else {
                            Flame flame = new Flame(getBoundary().getMinX(), getBoundary().getMinY() + i * Main.SCALE_CONSTANT, Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_vertical.png"), "mid_up");
                            Main.immobiles.add(flame);
                        }
                    }
                }
            }
            for (int i = 0; i < range; i++) {
                boolean willBreak = false;
                Entities entity = Main.getObjectAt(getBoundary().getMinX(), getBoundary().getMaxY() + i * Main.SCALE_CONSTANT);
                if (entity instanceof Wall) {
                    break;
                } else if (entity instanceof Destroyable) {
                    if (entity instanceof Bomb) {
                        sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\explosion.wav");
                    }
                    ((Destroyable) entity).isBreaking = true;
                    break;
                } else {
                    entity = Main.getGrassAt(getBoundary().getMinX(),getBoundary().getMaxY() + i * Main.SCALE_CONSTANT);
                    for(Entities entities : Main.entities) {
                        if(entities.getBoundary().intersects(entity.getBoundary())) {
                            ((Mobile)entities).isDying = true;
                            willBreak = true;
                        }
                    }
                    if(willBreak) {
                        break;
                    } else {
                        Entities entity2 = Main.getObjectAt(getBoundary().getMinX(), getBoundary().getMaxY() + i * Main.SCALE_CONSTANT + Main.SCALE_CONSTANT);
                        if (entity2 instanceof Destroyable || entity2 instanceof Mobile || entity2 instanceof Wall) {
                            Flame flame = new Flame(getBoundary().getMinX(), getBoundary().getMaxY() + i * Main.SCALE_CONSTANT, Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_vertical_down_last.png"), "down");
                            Main.immobiles.add(flame);
                        } else {
                            Flame flame = new Flame(getBoundary().getMinX(), getBoundary().getMaxY() + i * Main.SCALE_CONSTANT, Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_vertical.png"), "mid_down");
                            Main.immobiles.add(flame);
                        }
                    }
                }
            }
            for (int i = -1; i >= -range; i--) {
                boolean willBreak = false;
                Entities entity = Main.getObjectAt(getBoundary().getMinX() + i * Main.SCALE_CONSTANT, getBoundary().getMinY());
                if (entity instanceof Wall) {
                    break;
                } else if (entity instanceof Destroyable) {
                    if (entity instanceof Bomb) {
                        sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\explosion.wav");
                    }
                    ((Destroyable) entity).isBreaking = true;
                    break;
                } else {
                    entity = Main.getGrassAt(getBoundary().getMinX() + i * Main.SCALE_CONSTANT ,getBoundary().getMinY());
                    for(Entities entities : Main.entities) {
                        if(entities.getBoundary().intersects(entity.getBoundary())) {
                            ((Mobile)entities).isDying = true;
                            willBreak = true;
                        }
                    }
                    if(willBreak) {
                        break;
                    } else {
                        Entities entity2 = Main.getObjectAt(getBoundary().getMinX()+ i * Main.SCALE_CONSTANT - Main.SCALE_CONSTANT, getBoundary().getMinY());
                        if (entity2 instanceof Destroyable || entity2 instanceof Mobile || entity2 instanceof Wall) {
                            Flame flame = new Flame(getBoundary().getMinX() + i * Main.SCALE_CONSTANT, getBoundary().getMinY(), Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_horizontal_left_last.png"), "left");
                            Main.immobiles.add(flame);
                        } else {
                            Flame flame = new Flame(getBoundary().getMinX() + i * Main.SCALE_CONSTANT, getBoundary().getMinY(), Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_horizontal.png"), "mid_left");
                            Main.immobiles.add(flame);
                        }
                    }
                }
            }
            for (int i = 0; i < range; i++) {
                boolean willBreak = false;
                Entities entity = Main.getObjectAt(getBoundary().getMaxX() + i * Main.SCALE_CONSTANT, getBoundary().getMinY());
                if (entity instanceof Wall) {
                    break;
                } else if (entity instanceof Destroyable) {
                    if (entity instanceof Bomb) {
                        sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\explosion.wav");
                    }
                    ((Destroyable) entity).isBreaking = true;
                    break;
                } else {
                    entity = Main.getGrassAt(getBoundary().getMaxX() + i * Main.SCALE_CONSTANT ,getBoundary().getMinY());
                    for(Entities entities : Main.entities) {
                        if(entities.getBoundary().intersects(entity.getBoundary())) {
                            ((Mobile)entities).isDying = true;
                            willBreak = true;
                        }
                    }
                    if(willBreak) {
                        break;
                    } else {
                        Entities entity2 = Main.getObjectAt(getBoundary().getMaxX()+ i * Main.SCALE_CONSTANT + Main.SCALE_CONSTANT, getBoundary().getMinY());
                        if (entity2 instanceof Destroyable || entity2 instanceof Mobile || entity2 instanceof Wall) {
                            Flame flame = new Flame(getBoundary().getMaxX() + i * Main.SCALE_CONSTANT, getBoundary().getMinY(), Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_horizontal_right_last.png"), "right");
                            Main.immobiles.add(flame);
                        } else {
                            Flame flame = new Flame(getBoundary().getMaxX() + i * Main.SCALE_CONSTANT, getBoundary().getMinY(), Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_horizontal.png"), "mid_right");
                            Main.immobiles.add(flame);
                        }
                    }
                }
            }
            /*for (int i = -1; i >= -range * Main.SCALE_CONSTANT; i--) {
                Entities entity = Main.getObjectAt(getBoundary().getMinX(), getBoundary().getMinY() + i);
                if (entity instanceof Destroyable) {
                    if (entity instanceof Bomb) {
                        sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\explosion.wav");
                    }
                    ((Destroyable) entity).isBreaking = true;
                    break;
                } else if (entity instanceof Mobile) {
                    ((Mobile) entity).isDying = true;
                    break;
                } else if (entity instanceof Wall) {
                    break;
                } else {
                    if ((getBoundary().getMinY() + i) % Main.SCALE_CONSTANT == 0) {
                        Entities entity2 = Main.getObjectAt(getBoundary().getMinX(), getBoundary().getMinY() + i - Main.SCALE_CONSTANT);
                        if (entity2 instanceof Destroyable || entity2 instanceof Mobile || entity2 instanceof Wall) {
                            Flame flame = new Flame(getBoundary().getMinX(), getBoundary().getMinY() + i, Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_vertical_top_last.png"), "up");
                            Main.immobiles.add(flame);
                        } else {
                            Flame flame = new Flame(getBoundary().getMinX(), getBoundary().getMinY() + i, Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_vertical.png"), "mid_up");
                            Main.immobiles.add(flame);
                        }
                    }
                }
            }*/
            /*for (int i = 0; i <= range * Main.SCALE_CONSTANT - 1; i++) {
                Entities entity = Main.getObjectAt(getBoundary().getMinX(), getBoundary().getMaxY() + i);
                if (entity instanceof Destroyable) {
                    if (entity instanceof Bomb) {
                        sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\explosion.wav");
                    }
                    ((Destroyable) entity).isBreaking = true;
                    break;
                } else if (entity instanceof Wall) {
                    break;
                } else if (entity instanceof Mobile) {
                    ((Mobile) entity).isDying = true;
                    break;
                } else {
                    if ((getBoundary().getMaxY() + i) % Main.SCALE_CONSTANT == 0) {
                        Entities entity2 = Main.getObjectAt(getBoundary().getMinX(), getBoundary().getMaxY() + i + Main.SCALE_CONSTANT);
                        if (entity2 instanceof Destroyable || entity2 instanceof Mobile || entity2 instanceof Wall) {
                            Flame flame = new Flame(getBoundary().getMinX(), getBoundary().getMaxY() + i, Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_vertical_down_last.png"), "down");
                            Main.immobiles.add(flame);
                        } else {
                            Flame flame = new Flame(getBoundary().getMinX(), getBoundary().getMaxY() + i, Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_vertical.png"), "mid_down");
                            Main.immobiles.add(flame);
                        }
                    }
                }
            }*/
            /*for (int i = 0; i <= range * Main.SCALE_CONSTANT - 1; i++) {
                Entities entity = Main.getObjectAt(getBoundary().getMaxX() + i, getBoundary().getMinY());
                if (entity instanceof Destroyable) {
                    if (entity instanceof Bomb) {
                        sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\explosion.wav");
                    }
                    ((Destroyable) entity).isBreaking = true;
                    break;
                } else if (entity instanceof Wall) {
                    break;
                } else if (entity instanceof Mobile) {
                    ((Mobile) entity).isDying = true;
                    break;
                } else {
                    if ((getBoundary().getMaxX() + i) % Main.SCALE_CONSTANT == 0) {
                        Entities entity2 = Main.getObjectAt(getBoundary().getMaxX() + i + Main.SCALE_CONSTANT, getBoundary().getMinY());
                        if (entity2 instanceof Destroyable || entity2 instanceof Mobile || entity2 instanceof Wall) {
                            Flame flame = new Flame(getBoundary().getMaxX() + i, getBoundary().getMinY(), Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_horizontal_right_last.png"), "right");
                            Main.immobiles.add(flame);
                        } else {
                            Flame flame = new Flame(getBoundary().getMaxX() + i, getBoundary().getMinY(), Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_horizontal.png"), "mid_right");
                            Main.immobiles.add(flame);
                        }
                    }
                }
            }*/
           /*for (int i = -1; i >= -range * Main.SCALE_CONSTANT; i--) {
                Entities entity = Main.getObjectAt(getBoundary().getMinX() + i, getBoundary().getMinY());
                if (entity instanceof Destroyable) {
                    if (entity instanceof Bomb) {
                        sound.playSound("C:\\Users\\lapto\\Java\\Bomberman\\src\\explosion.wav");
                    }
                    ((Destroyable) entity).isBreaking = true;
                    break;
                } else if (entity instanceof Wall) {
                    break;
                } else if (entity instanceof Mobile) {
                    ((Mobile) entity).isDying = true;
                    break;
                } else {
                    if ((getBoundary().getMinX() + i) % Main.SCALE_CONSTANT == 0) {
                        Entities entity2 = Main.getObjectAt(getBoundary().getMinX() + i - Main.SCALE_CONSTANT, getBoundary().getMinY());
                        if (entity2 instanceof Destroyable || entity2 instanceof Mobile || entity2 instanceof Wall) {
                            Flame flame = new Flame(getBoundary().getMinX() + i, getBoundary().getMinY(), Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_horizontal_left_last.png"), "left");
                            Main.immobiles.add(flame);
                        } else {
                            Flame flame = new Flame(getBoundary().getMinX() + i, getBoundary().getMinY(), Main.SCALE_CONSTANT, Main.SCALE_CONSTANT
                                    , new Image("explosion_horizontal.png"), "mid_left");
                            Main.immobiles.add(flame);
                        }
                    }
                }
            }*/
        }
        switch (flameTime) {
            case 9:
                setImage("bomb_exploded.png");
                break;
            case 6:
                setImage("bomb_exploded1.png");
                break;
            case 3:
                setImage("bomb_exploded2.png");
                break;
            case 0:
                removed = true;
                break;
        }

        flameTime--;
    }


}
