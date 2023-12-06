package AnimationEffects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;

public class SimpleAnimation {
    private final Animation<TextureRegion> animation;
    private float stateTime;
    private float x, y;

    private SimpleAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
        this.stateTime = 0f;
    }

    public static SimpleAnimation createExplosionAnimation(Texture spritesheet) {
        int frameWidth = spritesheet.getWidth() / 5;
        int frameHeight = spritesheet.getHeight() / 4;
        int rows = 4;
        int cols = 5;
        return getSimpleAnimation(spritesheet, frameWidth, frameHeight, rows, cols);
    }

    public static SimpleAnimation createWaterAnimation(Texture spritesheet) {
        int frameWidth = spritesheet.getWidth() / 7;
        int frameHeight = spritesheet.getHeight() / 2;
        int rows = 2;
        int cols = 7;
        return getSimpleAnimation(spritesheet, frameWidth, frameHeight, rows, cols);
    }
    public static SimpleAnimation createFreezeAnimation(Texture spritesheet) {
        int rows = 3;
        int cols = 5;
        return getSimpleAnimation(spritesheet, 112, 112, rows, cols);
    }
    public static SimpleAnimation createFireAnimation(Texture spritesheet) {
        int frameWidth = spritesheet.getWidth() / 6;
        int frameHeight = spritesheet.getHeight() / 4;
        int rows = 4;
        int cols = 6;
        return getSimpleAnimation(spritesheet, frameWidth, frameHeight, rows, cols);
    }
    private static SimpleAnimation getSimpleAnimation(Texture spritesheet, int frameWidth, int frameHeight, int rows, int cols) {
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                frames.add(new TextureRegion(spritesheet, j * frameWidth, i * frameHeight, frameWidth, frameHeight));
            }
        }
        Animation<TextureRegion> AnimationAll = new Animation<>(0.1f, frames, Animation.PlayMode.NORMAL);
        return new SimpleAnimation(AnimationAll);
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
    }

    public void draw(SpriteBatch batch) {
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, false);
        batch.draw(currentFrame, x, y);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(stateTime);
    }
}
