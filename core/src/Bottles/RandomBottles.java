package Bottles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;
import java.util.Random;

public class RandomBottles {
    private static Array<Bottle> bottles;
    private Random random;
    private float timeToLive;
    private float spawnInterval;
    private float spawnTimer;
    private int mapWidth;
    private int mapHeight;
    private static final int MARGIN = 50;

    public RandomBottles(int mapWidth, int mapHeight, float timeToLive, float spawnInterval) {
        this.bottles = new Array<>();
        this.random = new Random();
        this.timeToLive = timeToLive;
        this.spawnInterval = spawnInterval;
        this.spawnTimer = spawnInterval;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }
    private void spawnBottles() {
        for (int i = 0; i < 2; i++) { // Gera 4 garrafas
            spawnBottleAtRandomLocation();
        }
    }


    public void reset() {
        bottles.clear();
        spawnTimer = spawnInterval;
    }

    public Array<Bottle> getBottles() {
        return bottles;
    }

    public static void removeBottle(Bottle bottle) {
        boolean removed = bottles.removeValue(bottle, true);
        System.out.println("Garrafa removida: " + removed);
    }
    public void update(float delta) {
        spawnTimer -= delta;
        if (spawnTimer <= 0) {
            spawnBottles(); // Alterado para chamar spawnBottles
            spawnTimer = spawnInterval;
        }
        Iterator<Bottle> iterator = bottles.iterator();
        while (iterator.hasNext()) {
            Bottle bottle = iterator.next();
            bottle.update(delta);
            if (bottle.isTimeUp()) {
                iterator.remove(); // Remove a garrafa se o tempo acabou
            }
        }
    }
    private void spawnBottleAtRandomLocation() {
        float x = MARGIN + random.nextFloat() * (mapWidth - 2 * MARGIN);
        float y = MARGIN + random.nextFloat() * (mapHeight - 2 * MARGIN);
        spawnBottle(x, y);
    }

    public void render(SpriteBatch batch) {
        for (Bottle bottle : bottles) {
            bottle.draw(batch, bottle.getX(), bottle.getY());
        }
    }

    public void spawnBottle(float x, float y) {
        int type = random.nextInt(4); // Gera um tipo aleatório (0 a 3)
        System.out.println("Type is " + (type));
        Bottle bottle = new Bottle(type);
        bottle.setPosition(x, y);
        bottle.setTimeToLive(timeToLive);
        bottles.add(bottle);
    }
    public void setTimeToLive(float timeToLive) {
        this.timeToLive = timeToLive;
    }

}

