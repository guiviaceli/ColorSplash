package Puddles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Puddle {
    private Sprite sprite;
    private Rectangle puddleHitbox;

    public Puddle(Texture texture, float x, float y, float scaleX, float scaleY) {
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x, y);
        //this.sprite.setScale(scaleX, scaleY);
        this.puddleHitbox = new Rectangle(sprite.getX(),
                sprite.getY() ,
                sprite.getWidth() ,
                sprite.getHeight());
    }

    public void update() {
        puddleHitbox.setX(sprite.getX() );// - sprite.getWidth() * sprite.getScaleX() / 2);
        puddleHitbox.setY(sprite.getY());// - sprite.getHeight() * sprite.getScaleY() / 2);
        puddleHitbox.setWidth(sprite.getWidth());// * sprite.getScaleX());
        puddleHitbox.setHeight(sprite.getHeight());// * sprite.getScaleY());
        // Atualizar o hitbox para coincidir com a posição e tamanho da sprite
//        puddleHitbox.setPosition(sprite.getX(), sprite.getY());
//        puddleHitbox.setSize(sprite.getWidth() * sprite.getScaleX(), sprite.getHeight() * sprite.getScaleY());
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Rectangle getPuddleHitbox() {
        return puddleHitbox;
    }
}
