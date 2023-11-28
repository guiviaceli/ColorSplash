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
                System.out.println("Colisão detectada com a garrafa!");
                player.collectBottle(bottle);
            }
        }
    }
    public static void checkBottleCollisionsPlayer2(Enemy player2, Array<Bottle> bottles) {
        for (Bottle bottle : bottles) {
            if (player2.getHitbox().overlaps(bottle.getHitbox()) && !player2.hasBottle()) {
                System.out.println("Colisão detectada com a garrafa!");
                player2.collectBottle(bottle);
            }
        }
    }

    public static void checkPuddleCollisionsPlayer1(Player player) {
        for (Puddle puddle : player.getPuddles()) {
            if (player.getHitbox().overlaps(puddle.getPuddleHitbox())) {
                player.takeDamage(5);
            }
        }
    }

    public static void checkPuddleCollisionsPlayer2(Enemy player2) {
        for (Puddle puddle : player2.getPuddles()) {
            if (player2.getHitbox().overlaps(puddle.getPuddleHitbox())) {
                player2.takeDamage(5);
            }
        }
    }
}
