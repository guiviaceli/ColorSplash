//package Player;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.Group;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//
//import static Screens.GameScreen.player;
//
//public class HealthBar extends Actor {
//    private float maxHealth;
//    private float currentHealth;
//    private ShapeRenderer shapeRenderer;
//    public HealthBar(float maxHealth) {
//        this.maxHealth = maxHealth;
//        this.currentHealth = maxHealth;
//        this.shapeRenderer = new ShapeRenderer();
//
//    }
//
//    public void updateHealth(float health) {
//        this.currentHealth = health;
//    }
//
//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        // Desenhar a barra de vida
//        // Por exemplo, um retângulo simples
//        float healthBarWidth = 100;
//        float healthBarHeight = 10;
//        float healthPercent = currentHealth / maxHealth;
//
//        // Configura a posição da barra de vida em relação ao grupo
//        float barX = getX();;
//        float barY = getY() + player.getHeight() + 20; // Ajuste este valor conforme necessário
//
//        batch.end(); // Encerra o batch para usar o ShapeRenderer
//
//        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
//        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
//        shapeRenderer.translate(getX(), getY(), 0);
//
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.rect(barX, barY, healthBarWidth * healthPercent, healthBarHeight);
//        shapeRenderer.end();
//
//        batch.begin(); // Começa o batch novamente após desenhar com o ShapeRenderer
//    }
//
//    public void act(float delta) {
//        super.act(delta);
//        // Aqui você pode adicionar lógica adicional para atualizar, se necessário
//    }
//
//    public void dispose() {
//        shapeRenderer.dispose(); // Não esqueça de liberar o recurso
//    }
//}
