package Utils;

import Bottles.Bottle;
import Enemy.Enemy;
import Player.Player;
import Puddles.Puddle;
import com.badlogic.gdx.utils.Array;
import java.util.List;

public class CollisionHandler {
    public static void checkBottleCollisionsPlayer1(Player player, Array<Bottle> bottles) {
        for (Bottle bottle : bottles) {
            if (player.getHitbox().overlaps(bottle.getHitbox()) && !player.hasBottle()) {
                System.out.println("Colisão detectada com a garrafa do player 1!");
                player.collectBottle(bottle);
            }
        }
    }
    public static void checkBottleCollisionsPlayer2(Enemy player2, Array<Bottle> bottles) {
        for (Bottle bottle : bottles) {
            if (player2.getHitbox().overlaps(bottle.getHitbox()) && !player2.hasBottle()) {
                System.out.println("Colisão detectada com a garrafa do player 2!");
                player2.collectBottle(bottle);
            }
        }
    }
    public static void checkPuddleCollisions(Damageable entity, List<Puddle> allPuddles) {
        for (Puddle puddle : allPuddles) {
            if (entity.getHitbox().overlaps(puddle.getPuddleHitbox())) {
//                System.out.println("Checking collision for entity at x: " + entity.getHitbox().x + " y: " + entity.getHitbox().y);
//
//                System.out.println("Collision detected com puddle da entidade: " + entity); // Log para confirmação
                entity.takeDamage(5);
            }
        }
    }
}
