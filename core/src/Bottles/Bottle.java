
package Bottles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ColorSplash;
import com.badlogic.gdx.math.Rectangle;

public class Bottle extends Sprite {

    private Texture texture;
    private Rectangle hitbox;
    private String color; // Adicionar identificador de cor

    private float scaleX = 0.25f, scaleY = 0.25f;
    private float timeToLive; // Tempo total de vida da garrafa
    public Bottle(int type) {
        switch (type) {
            case 0:
                this.texture = ColorSplash.manager.get("Bottles/BLACK.png", Texture.class);
                this.color = "BLACK";

                break;
            case 1:
                this.texture = ColorSplash.manager.get("Bottles/BLUE.png", Texture.class);
                this.color = "BLUE";
                break;
            case 2:
                this.texture = ColorSplash.manager.get("Bottles/BLUE_LIGHT.png", Texture.class);
                this.color = "BLUE LIGHT";
                break;
            case 3:
                this.texture = ColorSplash.manager.get("Bottles/RED.png", Texture.class);
                this.color = "RED";
                break;
        }
        hitbox = new Rectangle(getX(), getY(), texture.getWidth(), texture.getHeight());
        setBounds(getX(), getY(), texture.getWidth(), texture.getHeight());
        setRegion(texture);
        setScale(scaleX, scaleY); // Aplica a escala

    }
    public void setTimeToLive(float timeToLive) {
        this.timeToLive = timeToLive;
    }
    public String getBottleColor() {
        return color;
    }

    public void update(float delta) {
        timeToLive -= delta;
        hitbox.setPosition(getX(), getY());
        hitbox.setSize(getWidth() * scaleX, getHeight() * scaleY);
    }

    public boolean isTimeUp() {
        return timeToLive <= 0;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }
    public void draw(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y, getWidth() * scaleX, getHeight() * scaleY);
    }
}
