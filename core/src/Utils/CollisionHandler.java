package Utils;

import AnimationEffects.EffectManager;
import AnimationEffects.SimpleAnimation;
import Bottles.Bottle;
import Puddles.Puddle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollisionHandler{
    private static EffectManager effectManager;
    //public static List<SimpleAnimation> animations = new ArrayList<>(); // Lista para armazenar animações

    public CollisionHandler(EffectManager effectManager) {
        this.effectManager = effectManager;
    }
    public static void checkBottleCollisions(Utility entity, Array<Bottle> bottles) {
        for (Bottle bottle : bottles) {
            if (entity.getHitbox().overlaps(bottle.getHitbox()) && !entity.hasBottle()) {
                entity.collectBottle(bottle);
            }
        }
    }
    public static String checkPuddleCollisions(Utility entity, List<Puddle> allPuddles) {
        for (Puddle puddle : allPuddles) {
            if (puddle.isAlive() && entity.getHitbox().overlaps(puddle.getPuddleHitbox())) {
                if (puddle.getBottleColor().equals("BLUE LIGHT")) {
                    entity.freeze(3.0f);
                }if (puddle.getBottleColor().equals("BLUE")) {
                    entity.freeze(1.0f);
                }if (puddle.getBottleColor().equals("RED")) {
                    entity.freeze(1.0f);
                }if (puddle.getBottleColor().equals("BLACK")) {
                    entity.freeze(1.0f);
                }
            }
            return puddle.getBottleColor();
        }
        return ""; // Retorna uma string vazia se não houver colisão
    }


}
