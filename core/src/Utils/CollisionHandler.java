package Utils;

import Bottles.Bottle;
import Puddles.Puddle;
import com.badlogic.gdx.utils.Array;
import java.util.List;

public class CollisionHandler{
    public static void checkBottleCollisions(Utility entity, Array<Bottle> bottles) {
        for (Bottle bottle : bottles) {
            if (entity.getHitbox().overlaps(bottle.getHitbox()) && !entity.hasBottle()) {
                //System.out.println("Colisão detectada por !" + entity);
                entity.collectBottle(bottle);
            }
        }
    }
    public static void checkPuddleCollisions(Utility entity, List<Puddle> allPuddles) {
        for (Puddle puddle : allPuddles) {
            if (entity.getHitbox().overlaps(puddle.getPuddleHitbox())) {
//                System.out.println("Checking collision for entity at x: " + entity.getHitbox().x + " y: " + entity.getHitbox().y);
//                System.out.println("Collision detected com puddle da entidade: " + entity); // Log para confirmação
                entity.takeDamage(5);
            }
        }
    }
}
