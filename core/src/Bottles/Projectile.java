//package Bottles;
//
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.math.Vector2;
//
//public class Projectile extends Sprite {
//    private Vector2 position;
//    private Vector2 velocity;
//    private float gravity;
//
//    public Projectile(Texture texture, Vector2 startPosition, Vector2 startVelocity, float gravity) {
//        super(texture);
//        this.position = new Vector2(startPosition);
//        this.velocity = new Vector2(startVelocity);
//        this.gravity = gravity;
//    }
//
//    public void update(float deltaTime) {
//        // Aplica gravidade
//        velocity.y -= gravity * deltaTime;
//
//        // Atualiza a posição
//        position.add(velocity.x * deltaTime, velocity.y * deltaTime);
//
//        // Atualiza a posição do sprite
//        setPosition(position.x, position.y);
//
//        // Aqui você pode adicionar lógica para remover o projétil se ele atingir o chão
//    }
//}
