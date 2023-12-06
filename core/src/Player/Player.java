package Player;

import Bottles.Bottle;
import Bottles.RandomBottles;
import Puddles.Puddle;
import Utils.Utility;
import Utils.Direction;
import Utils.AttackEffectHandler;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ColorSplash;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player extends Sprite implements Utility {
    private final float mapWidth;
    private final float mapHeight;
    private final PlayerInputProcessor playerInputProcessor;
    private static final int FRAME_WIDTH = 24;
    private static final int FRAME_HEIGHT = 32;
    private float x, y;
    private int frameRow;
    private static final int FRAMES_PER_DIRECTION = 3;
    private final Animation<TextureRegion>[] animations;
    private float stateTime;
    private TextureRegion currentFrame;
    private boolean hasBottle;
    private final Rectangle playerHitbox;
    private final Rectangle playerHealth;
    private Bottle collectibleBottle;
    private Direction currentDirection;
    private final int maxHealth = 100;
    private int currentHealth = maxHealth;
    private final List<Puddle> puddles = new ArrayList<>();
    private float invulnerabilityTime = 0;
    private boolean isFrozen;
    private float freezeTime;
    private final List<DamageNotification> damageNotifications = new ArrayList<>();
    public Player(int mapWidth, int mapHeight, int characterChoice, PlayerInputProcessor InputProcessor){
        super(ColorSplash.manager.<Texture>get("Characters/Character" + characterChoice + ".png"));
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.frameRow = 0;
        setRegion(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        this.x = 0;
        this.y = 0;
        this.setPosition(this.x, this.y);
        this.playerInputProcessor = InputProcessor;

        playerHitbox = new Rectangle(x, y, getWidth() - 30, getHeight() - 10);
        playerHealth = new Rectangle(x, y, getWidth()- 28, getHeight() -150);

        animations = new Animation[4];

        Array<TextureRegion> frames = new Array<>();

        for (int i = 0; i < 4; i++) {
            frames.clear();
            for (int j = 0; j < FRAMES_PER_DIRECTION; j++) {
                frames.add(new TextureRegion(getTexture(), j * FRAME_WIDTH, i * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT));
            }
            animations[i] = new Animation<>(0.1f, frames, Animation.PlayMode.LOOP);
        }
        stateTime = 0f;
        currentFrame = getFrame();

        this.hasBottle = false;
    }
    private TextureRegion getFrame() {
        return animations[frameRow].getKeyFrame(stateTime);
    }
    public void draw(SpriteBatch batch, float delta) {
        this.setPosition(x,y);
        super.draw(batch);
        update(delta);
    }
    public void update(float delta) {
        if (isFrozen) {
            freezeTime -= delta;
            if (freezeTime <= 0) {
                isFrozen = false;
            }
        } else {
            boolean isMoving = playerInputProcessor.UP || playerInputProcessor.DOWN || playerInputProcessor.LEFT || playerInputProcessor.RIGHT;

            if (isMoving) {
                stateTime += delta;
            } else {
                stateTime = 0;
            }

            Animation<TextureRegion> animation = animations[frameRow];
            currentFrame = animation.getKeyFrame(isMoving ? stateTime : 0, true);

            setRegion(currentFrame);
            this.setPosition(x, y);
            playerHitbox.setPosition(x, y);
            playerHealth.setPosition(x, y + 140);

            float speed = 300;
            if (playerInputProcessor.UP) {
                y += speed * delta;
                frameRow = 0;
                currentDirection = Direction.UP;
            } else if (playerInputProcessor.DOWN) {
                y -= speed * delta;
                frameRow = 2;
                currentDirection = Direction.DOWN;
            } else if (playerInputProcessor.LEFT) {
                x -= speed * delta;
                frameRow = 3;
                currentDirection = Direction.LEFT;
            } else if (playerInputProcessor.RIGHT) {
                x += speed * delta;
                frameRow = 1;
                currentDirection = Direction.RIGHT;
            } else if (playerInputProcessor.ATTACK && hasBottle) {
                attack();
            }

            if (x < 0) {
                x = 0;
            } else if (x + FRAME_WIDTH > mapWidth) {
                x = mapWidth - FRAME_WIDTH;
            }

            if (y < 0) {
                y = 0;
            } else if (y + FRAME_HEIGHT > mapHeight) {
                y = mapHeight - FRAME_HEIGHT;
            }

            if (invulnerabilityTime > 0) {
                invulnerabilityTime -= delta;
            }

        }
        Iterator<DamageNotification> iterator = damageNotifications.iterator();
        while (iterator.hasNext()) {
            DamageNotification notification = iterator.next();
            notification.update(delta);
            if (!notification.isAlive()) {
                iterator.remove();
            }
        }
    }

    public void attack() {
        Bottle collectedBottle = getCollectibleBottle();
        AttackEffectHandler.createAttackEffect(collectedBottle,currentDirection, this.getX(), this.getY(), puddles);
        useOrDiscardBottle();
    }
    public void reset() {
        this.x = 0;
        this.y = 0;
        this.setPosition(x, y);

        this.currentHealth = maxHealth;
        this.invulnerabilityTime = 0;

        this.hasBottle = false;
        this.collectibleBottle = null;

        this.puddles.clear();

        this.stateTime = 0;
        this.frameRow = 0;
        this.currentDirection = Direction.DOWN;
    }
    @Override
    public Rectangle getHitbox() {
        return playerHitbox;
    }
    @Override
    public Rectangle getHealthBox() {
        return playerHealth;
    }
    @Override
    public List<Puddle> getPuddles() {
        return puddles;
    }
    @Override
    public void takeDamage(int damage) {
        if (invulnerabilityTime <= 0) {
            currentHealth -= damage;
            if (currentHealth < 0) {
                currentHealth = 0;
            }
            invulnerabilityTime = 1.0f;
            updateHealthBar();
            damageNotifications.add(new DamageNotification(x + 60, y + 96, damage, 0.2f));

        }
    }
    public void freeze(float duration) {
        isFrozen = true;
        freezeTime = duration;
    }
    private static class DamageNotification {
        float x, y;
        int damage;
        float timeRemaining;

        public DamageNotification(float x, float y, int damage, float duration) {
            this.x = x;
            this.y = y;
            this.damage = damage;
            this.timeRemaining = duration;
        }

        public void update(float delta) {
            timeRemaining -= delta;
        }

        public boolean isAlive() {
            return timeRemaining > 0;
        }
    }
    @Override
    public void updateHealthBar() {
        float healthPercent = (float) currentHealth / maxHealth;
        playerHealth.setWidth((getWidth() - 28) * healthPercent);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
    public void collectBottle(Bottle bottle) {
        if (bottle != null && !this.hasBottle){
            setCollectibleBottle(bottle);
            RandomBottles.removeBottle(bottle);
            setHasBottle(true);
        }
    }

    public boolean hasBottle() {
        return hasBottle;
    }

    public void setHasBottle(boolean hasBottle) {
        this.hasBottle = hasBottle;
    }

    public void useOrDiscardBottle() {
        this.hasBottle = false;
    }

    public void setCollectibleBottle(Bottle bottle) {
        this.collectibleBottle = bottle;
    }

    public Bottle getCollectibleBottle() {
        return collectibleBottle;
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
