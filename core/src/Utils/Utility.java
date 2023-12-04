package Utils;

import Bottles.Bottle;
import Puddles.Puddle;
import com.badlogic.gdx.math.Rectangle;
import java.util.List;

public interface Utility {
    List<Puddle> getPuddles();
    Rectangle getHitbox();
    Rectangle getHealthBox();
    void takeDamage(int damage);
    void updateHealthBar();
    boolean hasBottle();
    void collectBottle(Bottle bottle);
    public int getMaxHealth();
    public int getCurrentHealth();
    Bottle getCollectibleBottle();
    void freeze(float duration);
    public float getX();
    public float getY();
}