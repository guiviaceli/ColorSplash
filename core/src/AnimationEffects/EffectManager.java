//package AnimationEffects;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class EffectManager {
//    private List<AnimationEffect> activeEffects = new ArrayList<>();
//
//    public void startEffect(String effectType, float x, float y, int rows, int cols) {
//        float frameDuration = 0.15f;
//        String spritesheetPath = "AnimationEffects/" + effectType + "Animation.png";
//
//        AnimationEffect effect = new AnimationEffect(spritesheetPath, rows, cols, frameDuration, Animation.PlayMode.NORMAL);
//        effect.setPosition(x, y);
//        activeEffects.add(effect);
//    }
//
//    public void update(float delta) {
//        // Use um iterator para evitar ConcurrentModificationException ao remover itens durante a iteração
//        Iterator<AnimationEffect> iterator = activeEffects.iterator();
//        while (iterator.hasNext()) {
//            AnimationEffect effect = iterator.next();
//            effect.update(delta);
//            if (effect.isFinished()) {
//                iterator.remove(); // Remove o efeito se estiver concluído
//            }
//        }
//    }
//
//
//    public void draw(SpriteBatch batch) {
//        // Em algum lugar do seu loop de jogo/renderização:
//        for (AnimationEffect effect : activeEffects) {
//            //effect.update(Gdx.graphics.getDeltaTime()); // Atualiza o efeito
//            effect.draw(batch); // Desenha o efeito
//        }
//
//    }
//
//}

package AnimationEffects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EffectManager {
    private List<AnimationEffect> activeEffects = new ArrayList<>();

    public void startEffect(String effectType, float x, float y, int rows, int cols) {
        String spritesheetPath = "AnimationEffects/" + effectType + "Animation.png";
        AnimationEffect effect = new AnimationEffect(spritesheetPath, rows, cols, 0.1f, Animation.PlayMode.NORMAL);
        effect.setPosition(x, y);
        activeEffects.add(effect);
    }

    public void update(float delta) {
        Iterator<AnimationEffect> iterator = activeEffects.iterator();
        while (iterator.hasNext()) {
            AnimationEffect effect = iterator.next();
            effect.update(delta);
            if (effect.isFinished()) {
                iterator.remove();
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for (AnimationEffect effect : activeEffects) {
            effect.draw(batch);
        }
    }
}
