package Puddles;

import AnimationEffects.SimpleAnimation;
import Screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import static com.mygdx.game.ColorSplash.manager;

public class Puddle extends Sprite{
    private final Sprite sprite;
    private final Rectangle puddleHitbox;
    private final float timeToLive;
    private float elapsedTime;
    private final String bottleColor;
    private boolean animationStarted = false;
    private SimpleAnimation puddleAnimation;

    Texture explosionTexture;
    Texture waterTexture;
    Texture fireTexture;
    Texture freezeTexture;

    float x1, y1;


    public Puddle(Texture texture, float x, float y, String bottleColor) {
        explosionTexture = manager.get("AnimationEffects/ExplosionAnimation.png", Texture.class);
        waterTexture = manager.get("AnimationEffects/water2.png", Texture.class);
        fireTexture = manager.get("AnimationEffects/FireAnimation.png", Texture.class);
        freezeTexture = manager.get("AnimationEffects/FreezeAnimation.png", Texture.class);
        timeToLive = 5;
        elapsedTime = 0;
        this.bottleColor = bottleColor;
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x, y);
        x1 =x;
        y1 = y;
        this.puddleHitbox = new Rectangle(sprite.getX(), sprite.getY() , sprite.getWidth() , sprite.getHeight());
    }

    public void startAnimation() {
        if (!animationStarted) {
            switch (bottleColor) {
                case "BLUE":
                    puddleAnimation = SimpleAnimation.createWaterAnimation(waterTexture);
                    break;
                case "RED":
                    puddleAnimation = SimpleAnimation.createFireAnimation(fireTexture);
                    break;
                case "BLUE LIGHT":
                    puddleAnimation = SimpleAnimation.createFreezeAnimation(freezeTexture);
                    break;
                case "BLACK":
                    puddleAnimation = SimpleAnimation.createExplosionAnimation(explosionTexture);
                    break;
            }
            puddleAnimation.setPosition(sprite.getX() + 500,sprite.getY() + 50);
            animationStarted = true;
            GameScreen.activeAnimations.add(puddleAnimation);
        }
    }

    public void update(float delta) {
        elapsedTime += delta;
        if (!isAlive()) {
            puddleHitbox.setWidth(0);
            puddleHitbox.setHeight(0);
        }else {
            puddleHitbox.setX(sprite.getX());
            puddleHitbox.setY(sprite.getY());
            puddleHitbox.setWidth(sprite.getWidth());
            puddleHitbox.setHeight(sprite.getHeight());
        }
    }
    public boolean isAlive() {
        return elapsedTime < timeToLive;
    }
    public void draw(SpriteBatch batch) {
        if (isAlive()) {
            sprite.draw(batch);
        }
    }

    public String getBottleColor() {
        return bottleColor;
    }

    public Rectangle getPuddleHitbox() {
        return puddleHitbox;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public boolean isAnimationStarted() {
        return animationStarted;
    }
}
