package AnimationEffects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class ExplosionAnimation {
    private Animation<TextureRegion> animation;
    private float stateTime;
    private float x, y; // Posição para renderizar a animação

    public ExplosionAnimation() {
        Texture spritesheet = new Texture("AnimationEffects/ExplosionAnimation.png");
        int frameWidth = spritesheet.getWidth() / 5;
        int frameHeight = spritesheet.getHeight() / 4;

        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                frames.add(new TextureRegion(spritesheet, j * frameWidth, i * frameHeight, frameWidth, frameHeight));
            }
        }

        this.animation = new Animation<>(0.1f, frames, Animation.PlayMode.NORMAL);
        this.stateTime = 0f;
    }

    public void update(float delta) {
        if (!animation.isAnimationFinished(stateTime)) {
            stateTime += delta;
        }
    }

    public void render(SpriteBatch batch) {
        if (!animation.isAnimationFinished(stateTime)) {
            TextureRegion currentFrame = animation.getKeyFrame(stateTime);
            System.out.println("Renderizando animação em x: " + x + ", y: " + y);
            batch.draw(currentFrame, x, y);
        }
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(stateTime);
    }
}