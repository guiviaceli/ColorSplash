package Puddles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Puddle {
    private Sprite sprite;
    private Rectangle puddleHitbox;

    public Puddle(Texture texture, float x, float y) {
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x, y);
        this.puddleHitbox = new Rectangle(sprite.getX(), sprite.getY() , sprite.getWidth() , sprite.getHeight());
    }

    public void update() {
        puddleHitbox.setX(sprite.getX() );
        puddleHitbox.setY(sprite.getY());
        puddleHitbox.setWidth(sprite.getWidth());
        puddleHitbox.setHeight(sprite.getHeight());
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Rectangle getPuddleHitbox() {
        return puddleHitbox;
    }
}
