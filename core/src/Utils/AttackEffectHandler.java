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
import java.util.List;

public class AttackEffectHandler extends Sprite{
    //private List<Puddle> puddles = new ArrayList<>();

    public static void createAttackEffect(Bottle bottle, Direction direction, float playerX, float playerY, final List<Puddle> puddles) {
        float distance = 150;
        float scaleX = 0.25f, scaleY = 0.25f;
        final float puddleScaleX = 0.5f, puddleScaleY = 0.5f;

        final Sprite bottleTexture = new Sprite(bottle.getTexture());// obter a textura da garrafa;
        String bottleColor = bottle.getBottleColor();
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
        float soundDuration = 1.0f; // Obter a duração do som
        final Texture finalTexture = texture;
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                float X = bottleTexture.getX();
                float Y = bottleTexture.getY();
                // Remover a garrafa arremessada
                long soundId = bottleThrowSound.play();

                bottleThrowSound.setPan(soundId, -1, 0.3f); // Panorama à esquerda com volume reduzido
                bottleThrowSound.setPitch(soundId, 2); // Aumenta o tom do som
                GameScreen.spritesParaRenderizar.remove(bottleTexture);

                Puddle puddle = new Puddle(finalTexture, X, Y, puddleScaleX, puddleScaleY);
                puddles.add(puddle);
            }
        }, soundDuration);
    }

    public static void createAttackEffect1(Bottle bottle, Direction1 direction, float playerX, float playerY, final List<Puddle> puddles) {
        float distance = 150;
        float scaleX = 0.25f, scaleY = 0.25f;
        final float puddleScaleX = 0.5f, puddleScaleY = 0.5f;

        final Sprite bottleTexture = new Sprite(bottle.getTexture());// obter a textura da garrafa;
        String bottleColor = bottle.getBottleColor();
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
                texture = ColorSplash.manager.get("puddle_black.png", Texture.class);
                break;
            case "BLUE":
                texture = ColorSplash.manager.get("puddle_blue.png", Texture.class);
                break;
            case "BLUE LIGHT":
                texture = ColorSplash.manager.get("puddle_blue_light.png", Texture.class);
                break;
            case "RED":
                texture = ColorSplash.manager.get("puddle_red.png", Texture.class);
                break;
        }
        float soundDuration = 1.0f; // Obter a duração do som
        final Texture finalTexture = texture;
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                float X = bottleTexture.getX();
                float Y = bottleTexture.getY();
                // Remover a garrafa arremessada
                long soundId = bottleThrowSound.play();

                bottleThrowSound.setPan(soundId, -1, 0.3f); // Panorama à esquerda com volume reduzido
                bottleThrowSound.setPitch(soundId, 2); // Aumenta o tom do som
                GameScreen.spritesParaRenderizar.remove(bottleTexture);

                Puddle puddle = new Puddle(finalTexture, X, Y, puddleScaleX, puddleScaleY);
                puddles.add(puddle);
            }
        }, soundDuration);
    }
}