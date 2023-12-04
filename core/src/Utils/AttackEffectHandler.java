package Utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import Screens.GameScreen;
import Puddles.Puddle;
import Bottles.Bottle;
import com.mygdx.game.ColorSplash;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AttackEffectHandler extends Sprite{
    private static final List<Puddle> allPuddles1 = new ArrayList<>();

    public static void createAttackEffect(Bottle bottle, Direction direction, float playerX, float playerY, final List<Puddle> Puddles) {
        float distance = 150;
        float scaleX = 0.25f, scaleY = 0.25f;

        final Sprite bottleTexture = new Sprite(bottle.getTexture());// obter a textura da garrafa;
        final String bottleColor = bottle.getBottleColor();
        Texture texture = null;

        final Sound bottleThrowSound = ColorSplash.manager.get("sounds/Bottle Break.wav", Sound.class);

        switch (direction) {
            case UP:
                bottleTexture.setPosition(playerX, playerY + distance);
                break;
            case DOWN:
                bottleTexture.setPosition(playerX, playerY - distance);
                break;
            case LEFT:
                bottleTexture.setPosition(playerX - distance, playerY);
                break;
            case RIGHT:
                bottleTexture.setPosition(playerX + distance, playerY);
                break;
        }
        bottleTexture.setScale(scaleX, scaleY);
        GameScreen.spritesParaRenderizar.add(bottleTexture);

        switch (bottleColor) {
            case "BLACK":
                texture = ColorSplash.manager.get("Puddles/puddle_black.png", Texture.class);
                break;
            case "BLUE":
                texture = ColorSplash.manager.get("Puddles/puddle_blue.png", Texture.class);
                break;
            case "BLUE LIGHT":
                texture = ColorSplash.manager.get("Puddles/puddle_blue_light.png", Texture.class);
                break;
            case "RED":
                texture = ColorSplash.manager.get("Puddles/puddle_red.png", Texture.class);
                break;
        }
        float soundDuration = 1.0f;
        final Texture finalTexture = texture;
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                float X = bottleTexture.getX();
                float Y = bottleTexture.getY();
                long soundId = bottleThrowSound.play();

                bottleThrowSound.setPan(soundId, -1, 0.3f);
                bottleThrowSound.setPitch(soundId, 2);
                GameScreen.spritesParaRenderizar.remove(bottleTexture);
                Puddle puddle = new Puddle(finalTexture, X, Y, bottleColor);
                Puddles.add(puddle);

                allPuddles1.addAll(Puddles);
            }
        }, soundDuration);
    }
    public static List<Puddle> getPuddles() {
        return new ArrayList<>(allPuddles1);
    }
    public static void updatePuddles(float delta) {
        Iterator<Puddle> iterator = allPuddles1.iterator();
        while (iterator.hasNext()) {
            Puddle puddle = iterator.next();
            puddle.update(delta);
            if (!puddle.isAlive()) {
                iterator.remove();
            }
        }
    }

    public static void resetPuddles() {
        allPuddles1.clear();
    }
}
