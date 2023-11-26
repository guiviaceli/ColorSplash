//package Bottles;
//
//import java.util.List;
//
//public class BottleController {
//    private List<Bottle> bottles;
//    private List<Player> players;
//
//    public BottleController(List<Bottle> bottles, List<Player> players) {
//        this.bottles = bottles;
//        this.players = players;
//    }
//
//    public void update(float deltaTime) {
//        for (Player player : players) {
//            for (Bottle bottle : bottles) {
//                if (playerCollidesWithBottle(player, bottle) && !player.hasBottle()) {
//                    player.collectBottle(bottle);
//                    bottle.setCollected(true);
//                }
//            }
//        }
//    }
//
//    private boolean playerCollidesWithBottle(Player player, Bottle bottle) {
//        // Implementação da detecção de colisão
//        // Pode ser uma verificação de retângulo de delimitação ou algo mais sofisticado
//        return player.getBounds().overlaps(bottle.getBounds());
//    }
//
//    // Outros métodos relacionados às garrafas podem ser adicionados aqui
//}
//
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Vector2;
//
//import java.util.concurrent.ConcurrentLinkedQueue;
////
////public class BottleController {
////    private static ConcurrentLinkedQueue<Bottle> activeBottles;
////    private static ConcurrentLinkedQueue<Bottle> inactiveBottles;
////    private static Texture bottleTexture; // Texture for creating bottles
////
////    public static void init() {
////        activeBottles = new ConcurrentLinkedQueue<>();
////        inactiveBottles = new ConcurrentLinkedQueue<>();
////    }
////
////    public static void throwBottle(Vector2 startPosition, Vector2 targetPosition, Bottle.Type type) {
////        Bottle bottle;
////        if (!inactiveBottles.isEmpty()) {
////            bottle = inactiveBottles.remove();
////            // Optionally reset the bottle's state here if needed
////        } else {
////            bottle = Bottle.createBottle(type, bottleTexture); // Create a new bottle
////        }
////        //bottle.setPosition(x, y); // Set the bottle's position
////        bottle.throwBottle(startPosition, targetPosition); // Iniciar o arremesso
////
////        activeBottles.add(bottle);
////    }
////
////    public static void updateAndDraw(SpriteBatch batch, float delta) {
////        // Assuming the bottles themselves handle their update logic
////        for (Bottle bottle : activeBottles) {
////            bottle.draw(batch);
////            // Additional logic for checking bottle state (e.g., if it's broken)
////            // and moving it to the inactive queue
////        }
////    }
////
////}
