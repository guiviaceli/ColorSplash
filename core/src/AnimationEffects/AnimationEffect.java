//package AnimationEffects;
//
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.*;
//import com.badlogic.gdx.utils.Array;
//import com.mygdx.game.ColorSplash;
//
//public class AnimationEffect {
//    private Animation<TextureRegion> animation;
//    private float stateTime;
//    private boolean isFinished;
//    private float x, y; // Posição da animação
//    int frameRow = 0;
//    private TextureRegion currentFrame;
//    private Animation.PlayMode playMode;
//
//    public AnimationEffect(String spritesheetPath, int rows, int cols, float frameDuration, Animation.PlayMode playMode) {
//        Texture spritesheet = ColorSplash.manager.get(spritesheetPath, Texture.class);
//        int frameWidth = spritesheet.getWidth() / cols;
//        this.playMode = playMode;
//        int frameHeight = spritesheet.getHeight() / rows;
//        TextureRegion[][] tempFrames = TextureRegion.split(spritesheet, frameWidth, frameHeight);
//
//        Array<TextureRegion> animationFrames = new Array<>();
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                animationFrames.add(tempFrames[i][j]);
//            }
//        }
//
//        this.animation = new Animation<TextureRegion>(frameDuration, animationFrames, playMode);
//        this.stateTime = 0f;
//        this.isFinished = false;
//    }
//
//    public void update(float delta) {
//        if (!isFinished) {
//            stateTime += delta;
//            System.out.println("State time: "  + stateTime);
//            currentFrame = animation.getKeyFrame(stateTime);
//            System.out.println("Frame atual: "  + currentFrame);
//
//            if (animation.isAnimationFinished(stateTime)) {
//                System.out.println("ENTROU AUQI");
//
//                isFinished = true;
//                System.out.println("Terminou: "  + isFinished);
//
//            }
//        }
//    }
//
//    public void draw(SpriteBatch batch) {
//        if (!isFinished) {
//            batch.draw(currentFrame, x, y);
//        }
//    }
//
//    public boolean isFinished() {
//        return isFinished;
//    }
//
//    public void setPosition(float x, float y) {
//        this.x = x;
//        this.y = y;
//    }
//}
package AnimationEffects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ColorSplash;

public class AnimationEffect {
    private Animation<TextureRegion> animation;
    private float stateTime;
    private boolean isFinished;
    private float x, y; // Posição da animação

    public AnimationEffect(String spritesheetPath, int rows, int cols, float frameDuration, Animation.PlayMode playMode) {
        Texture spritesheet = ColorSplash.manager.get(spritesheetPath, Texture.class);
        int frameWidth = spritesheet.getWidth() / cols;
        int frameHeight = spritesheet.getHeight() / rows;
        Array<TextureRegion> animationFrames = new Array<>();

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                animationFrames.add(new TextureRegion(spritesheet, j * frameWidth, i * frameHeight, frameWidth, frameHeight));
            }
        }

        this.animation = new Animation<TextureRegion>(frameDuration, animationFrames, playMode);
        this.stateTime = 0f;
        this.isFinished = false;
    }

    public void update(float delta) {
        if (!isFinished) {
            stateTime += delta;
            if (animation.isAnimationFinished(stateTime)) {
                isFinished = true;
            }
        }
    }

    public void draw(SpriteBatch batch) {
        if (!isFinished) {
            TextureRegion currentFrame = animation.getKeyFrame(stateTime, false);
            batch.draw(currentFrame, x, y);
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
