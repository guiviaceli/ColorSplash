package AnimationEffects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;

public class SimpleAnimation {
    private Animation<TextureRegion> animation;
    private float stateTime;
    private float x, y; // Posição da animação
    public SimpleAnimation(String spritesheetPath, int rows, int cols, float frameDuration) {
        Texture spritesheet = new Texture(spritesheetPath);
        int frameWidth = spritesheet.getWidth() / cols;
        int frameHeight = spritesheet.getHeight() / rows;
        Array<TextureRegion> frames = new Array<>();

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                frames.add(new TextureRegion(spritesheet, j * frameWidth, i * frameHeight, frameWidth, frameHeight));
            }
        }
        this.animation = new Animation<>(frameDuration, frames, Animation.PlayMode.NORMAL);
        this.stateTime = 0f;
    }

    public void render(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        if (!animation.isAnimationFinished(stateTime)) {
            TextureRegion currentFrame = animation.getKeyFrame(stateTime, false);

            batch.draw(currentFrame, x, y); // Ajuste a posição conforme necessário

        }
    }

    public void dispose() {
        //batch.dispose();
        // Dispose other resources if necessary
    }
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(stateTime);
    }
}
