package Utils;

import Bottles.Bottle;
import Puddles.Puddle;
import com.badlogic.gdx.utils.Array;
import java.util.List;

public class CollisionHandler {
    public static void checkBottleCollisions(Utility entity, Array<Bottle> bottles) {
        for (Bottle bottle : bottles) {
            if (entity.getHitbox().overlaps(bottle.getHitbox()) && !entity.hasBottle()) {
                entity.collectBottle(bottle);
            }
        }
    }

    public static void checkPuddleCollisions(Utility entity, List<Puddle> allPuddles) {
        for (Puddle puddle : allPuddles) {
            if (puddle.isAlive() && entity.getHitbox().overlaps(puddle.getPuddleHitbox()) && !puddle.isAnimationStarted()) {
                if (puddle.getBottleColor().equals("BLUE LIGHT")) {
                    entity.freeze(3.0f);
                    entity.takeDamage(50);
                    puddle.startAnimation();
                }
                if (puddle.getBottleColor().equals("BLUE")) {
                    entity.takeDamage(50);
                    puddle.startAnimation();
                }
                if (puddle.getBottleColor().equals("RED")) {
                    entity.takeDamage(50);
                    puddle.startAnimation();
                }
                if (puddle.getBottleColor().equals("BLACK")) {
                    entity.takeDamage(50);
                    puddle.startAnimation();
                }
            }
        }
    }
}

