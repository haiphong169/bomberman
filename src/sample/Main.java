package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main extends Application {

    public static final double SCALE_CONSTANT = 32;
    public static ArrayList<Entities> entities = new ArrayList<>();
    public static ArrayList<Immobile> immobiles = new ArrayList<>();
    public static ArrayList<Grass> grasses = new ArrayList<>();
    public static ArrayList<Special> specials = new ArrayList<>();
    public static ArrayList<Entities> everything = new ArrayList<>();
    public static ArrayList<Bomb> bombs = new ArrayList<>();
    public ArrayList<String> input = new ArrayList<>();
    public Canvas canvas;
    public static Bomber player;
    public GraphicsContext graphicsContext;
    public static boolean over = false;
    public static boolean next_level = false;
    public int timeToRestart = 100;
    public int level = 5;
    public int level_cap = 5;

    public static Entities getObjectAt(double x, double y) {
        for (Entities entity : everything) {
            if (entity.getBoundary().getMinX() == x && entity.getBoundary().getMinY() == y) {
                if (!(entity instanceof Grass)) {
                    return entity;
                }
            }
        }
        return null;
    }

    public static Grass getGrassAt(double x, double y) {
        for(Grass grass : grasses) {
            if(grass.getBoundary().getMinX() == x && grass.getBoundary().getMinY() == y) {
                return grass;
            }
        }
        return null;
    }

    public void createMap() throws IOException {
        List<String> readFromFile = Files.readAllLines(Paths.get("C:\\Users\\lapto\\Desktop\\level"+ level + ".txt"), StandardCharsets.UTF_8);
        ArrayList<Integer> mapInfo = new ArrayList<>();
        String s = readFromFile.get(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                if (s.charAt(i + 1) == ' ') {
                    mapInfo.add(s.charAt(i) - '0');
                } else {
                    int sum = (s.charAt(i) - '0') * 10;
                    i++;
                    sum += s.charAt(i) - '0';
                    mapInfo.add(sum);
                }
            }
        }
        readFromFile.remove(0);
        canvas = new Canvas(mapInfo.get(2) * SCALE_CONSTANT, mapInfo.get(1) * SCALE_CONSTANT);
        for (int i = 0; i < mapInfo.get(1); i++) {
            for (int j = 0; j < readFromFile.get(i).length(); j++) {
                Grass grass;
                Immobile immobile;
                Special special;
                Enemy enemy;
                switch (readFromFile.get(i).charAt(j)) {
                    case '#':
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        immobile = new Wall(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("wall.png"));
                        immobiles.add(immobile);
                        everything.add(immobile);
                        break;
                    case 'p':
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        player = new Bomber(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("player_down_0.png"), 2);
                        immobile = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        immobiles.add(immobile);
                        entities.add(player);
                        everything.add(immobile);
                        everything.add(player);
                        break;
                    case '*':
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        immobile = new Brick(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("brick.png"));
                        immobiles.add(immobile);
                        everything.add(immobile);
                        break;
                    case 'f':
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        special = new FireBuff(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("powerup_flames.png"));
                        specials.add(special);
                        immobile = new Brick(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("brick.png"));
                        immobiles.add(immobile);
                        everything.add(immobile);
                        everything.add(special);
                        break;
                    case 'b':
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        special = new BombBuff(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("powerup_bombs.png"));
                        specials.add(special);
                        immobile = new Brick(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("brick.png"));
                        immobiles.add(immobile);
                        everything.add(immobile);
                        everything.add(special);
                        break;
                    case 'x':
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        special = new Portal(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("portal.png"));
                        specials.add(special);
                        immobile = new Brick(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("brick.png"));
                        immobiles.add(immobile);
                        everything.add(immobile);
                        everything.add(special);
                        break;
                    case 's':
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        special = new SpeedBuff(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("powerup_speed.png"));
                        specials.add(special);
                        immobile = new Brick(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("brick.png"));
                        immobiles.add(immobile);
                        everything.add(immobile);
                        everything.add(special);
                        break;
                    case '1':
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        enemy = new Balloom(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("balloom_left1.png"), 1);
                        entities.add(enemy);
                        everything.add(enemy);
                        break;
                    case '2':
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        enemy = new Oneal(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("oneal_left1.png"), 1);
                        entities.add(enemy);
                        everything.add(enemy);
                        break;
                    case '3':
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        enemy = new Doll(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("doll_left1.png"), 2);
                        entities.add(enemy);
                        everything.add(enemy);
                        break;
                    case '4':
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        enemy = new Minvo(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("minvo_left1.png"), 2);
                        entities.add(enemy);
                        everything.add(enemy);
                        break;
                    case '5':
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        enemy = new Kondoria(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("kondoria_left1.png"), 2);
                        entities.add(enemy);
                        everything.add(enemy);
                        break;
                    default:
                        grass = new Grass(j * SCALE_CONSTANT, i * SCALE_CONSTANT, SCALE_CONSTANT, SCALE_CONSTANT, new Image("grass.png"));
                        grasses.add(grass);
                        everything.add(grass);
                        break;
                }
            }
        }
    }

    public void remove() {
        if (!bombs.isEmpty()) {
            for (int i = 0; i < 1; i++) {
                if (bombs.get(i).removed) {
                    bombs.remove(i);
                }
            }
        }
        for (int i = 0; i < immobiles.size(); i++) {
            if (immobiles.get(i) instanceof Destroyable) {
                if (((Destroyable) immobiles.get(i)).removed) {
                    immobiles.remove(i);
                }
            }
        }
        for (int i = 0; i < everything.size(); i++) {
            if (everything.get(i) instanceof Destroyable) {
                if (((Destroyable) everything.get(i)).removed) {
                    everything.remove(i);
                }
            } else if (everything.get(i) instanceof Mobile) {
                if (!((Mobile) everything.get(i)).alive) {
                    everything.remove(i);
                }
            } else if (everything.get(i) instanceof Special) {
                if (((Special) everything.get(i)).used) {
                    everything.remove(i);
                }
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            if (!((Mobile) entities.get(i)).alive) {
                entities.remove(i);
            }
        }
        for (int i = 0; i < specials.size(); i++) {
            if (specials.get(i).used) {
                specials.remove(i);
            }
        }
    }

    public ButtonType alert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Game over");
        alert.setHeaderText("Wanna play again?");
        Optional<ButtonType> res = alert.showAndWait();

        return res.orElse(ButtonType.CANCEL);
    }


    public void update() {
        for (Bomb bomb : bombs) {
            bomb.update(input);
        }
        for (Special special : specials) {
            special.update(input);
        }
        for (Immobile immobile : immobiles) {
            immobile.update(input);
        }
        for (Entities entity : entities) {
            entity.update(input);
        }
    }

    public void render() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Grass grass : grasses) {
            grass.render(graphicsContext);
        }
        for (Special special : specials) {
            special.render(graphicsContext);
        }
        for (Immobile immobile : immobiles) {
            immobile.render(graphicsContext);
        }
        for (Entities entity : entities) {
            entity.render(graphicsContext);
        }
        for (Bomb bomb : bombs) {
            bomb.render(graphicsContext);
        }
    }

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            over = true;
        }
    };

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bomberman");

        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        createMap();

        root.getChildren().add(canvas);

        graphicsContext = canvas.getGraphicsContext2D();


        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        if (!input.contains(code)) {
                            input.add(code);
                        }
                    }
                }
        );

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        input.remove(code);
                        player.resetStep();
                    }
                }
        );

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (over) {
                    entities.clear();
                    everything.clear();
                    bombs.clear();
                    grasses.clear();
                    immobiles.clear();
                    specials.clear();
                    if(next_level) {
                        if (level < level_cap) {
                            level++;
                            next_level = false;
                        } else {
                            level = 1;
                        }
                    }
                    try {
                        createMap();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    over = false;
                    timeToRestart = 100;
                    Bomb.range = 2;
                    Bomb.limit = 2;
                    player.velocity = 2;
                }
                update();
                render();
                remove();
                if (!entities.contains(player)) {
                    if (timeToRestart == 0) {
                        over = true;
                    }
                    timeToRestart--;
                }
            }
        }.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
