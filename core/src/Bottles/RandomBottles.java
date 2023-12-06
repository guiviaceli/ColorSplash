package Bottles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;
import java.util.Random;

public class RandomBottles {
    private static Array<Bottle> bottles;
    private final Random random;
    private float timeToLive;
    private final float spawnInterval;
    private float spawnTimer;
    private final int mapWidth;
    private final int mapHeight;
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
        for (int i = 0; i < 3; i++) {
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
        bottles.removeValue(bottle, true);
    }
    public void update(float delta) {
        spawnTimer -= delta;
        if (spawnTimer <= 0) {
            spawnBottles();
            spawnTimer = spawnInterval;
        }
        Iterator<Bottle> iterator = bottles.iterator();
        while (iterator.hasNext()) {
            Bottle bottle = iterator.next();
            bottle.update(delta);
            if (bottle.isTimeUp()) {
                iterator.remove();
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
        int type = random.nextInt(4);
        Bottle bottle = new Bottle(type);
        bottle.setPosition(x, y);
        bottle.setTimeToLive(timeToLive);
        bottles.add(bottle);
    }

}

