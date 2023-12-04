package Puddles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Puddle {
    private Sprite sprite;
    private Rectangle puddleHitbox;
    private float timeToLive;
    private float elapsedTime;
    private String bottleColor;

    public Puddle(Texture texture, float x, float y, String bottleColor) {
        timeToLive = 10;
        elapsedTime = 0;
        this.bottleColor = bottleColor;
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x, y);
        this.puddleHitbox = new Rectangle(sprite.getX(), sprite.getY() , sprite.getWidth() , sprite.getHeight());
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
}
