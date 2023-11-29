package Utils;

import Puddles.Puddle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.List;

public interface Damageable {
    List<Puddle> getPuddles();
    Rectangle getHitbox();
    Rectangle getHealthBox();
    void takeDamage(int damage);
    void updateHealthBar();
}