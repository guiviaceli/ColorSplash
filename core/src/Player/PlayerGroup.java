//package Player;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.Group;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//public class PlayerGroup extends Group {
//    private Player player;
//    private HealthBar healthBar; // HealthBar é um novo ator que representa a barra de vida
//
//    public PlayerGroup(Player player) {
//        this.player = player;
//        this.healthBar = new HealthBar(player.getMaxHealth());
//
//        this.addActor(player);
//        this.addActor(healthBar);
//
//        // Configura a posição inicial do grupo
//        this.setPosition(player.getX(), player.getY());
//    }
//
//    @Override
//    public void act(float delta) {
//        super.act(delta);
//        // Aqui você pode adicionar a lógica para atualizar a barra de vida
//        healthBar.updateHealth(player.getCurrentHealth());
//    }
//}
