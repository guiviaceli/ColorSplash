package Player;

import Map.GameMap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ColorSplash;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Sprite {
    private float mapWidth, mapHeight; // As dimensões do seu mapa

    PlayerInputProcessor playerInputProcessor;
    private static final int FRAME_WIDTH = 24;
    private static final int FRAME_HEIGHT = 32;
    private float x, y;
    private float speed = 100;

    private int frameRow; // A linha da spritesheet que será mostrada
    private int frameCol; // A coluna da spritesheet que será mostrada
    private static final int FRAMES_PER_DIRECTION = 3; // Se você tem 3 frames de animação por direção

    private Animation<TextureRegion>[] animations;
    private float stateTime;

    private TextureRegion currentFrame; // O frame atual para desenhar

    public Player(int mapWidth, int mapHeight){
        super(ColorSplash.manager.<Texture>get("Mage.png"));
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        playerInputProcessor = new PlayerInputProcessor();
        ColorSplash.addInputProcessor(playerInputProcessor);
        this.frameRow = 0; // Inicializar com a linha de 'DOWN'
        this.frameCol = 0;
        setRegion(frameCol * FRAME_WIDTH, frameRow * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
        this.x = 0;
        this.y = 0;
        this.setPosition(this.x, this.y);

        animations = new Animation[4]; // Supondo que temos 4 direções
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 0; i < 4; i++) { // Para cada direção
            frames.clear();
            for (int j = 0; j < FRAMES_PER_DIRECTION; j++) { // Para cada frame na direção
                frames.add(new TextureRegion(getTexture(), j * FRAME_WIDTH, i * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT));
            }
            animations[i] = new Animation<TextureRegion>(0.1f, frames, Animation.PlayMode.LOOP); // 0.1f é a duração de cada frame
        }
        stateTime = 0f; // Inicializa o tempo de estado
        currentFrame = getFrame(); // Inicializa o frame atual

    }
    private TextureRegion getFrame() {
        // Obtém o frame inicial baseado na direção inicial
        return animations[frameRow].getKeyFrame(stateTime);
    }
    public void draw(SpriteBatch batch, float delta) {
        this.setPosition(x,y);
        super.draw(batch);
        update(delta);
    }

    public void update(float delta) {
        // Verifica se alguma tecla de movimento está pressionada
        boolean isMoving = playerInputProcessor.UP || playerInputProcessor.DOWN ||
                playerInputProcessor.LEFT || playerInputProcessor.RIGHT;

        if (isMoving) {
            stateTime += delta; // Atualiza o tempo de estado apenas se o jogador estiver se movendo
        } else {
            stateTime = 0; // Resetar o stateTime para evitar avançar a animação
        }

        // Escolhe o frame de animação baseado na direção e no tempo de estado
        Animation<TextureRegion> animation = animations[frameRow];
        currentFrame = animation.getKeyFrame(isMoving ? stateTime : 0, true);

        setRegion(currentFrame);
        this.setPosition(x, y); // Atualiza a posição do Sprite

        // Atualiza a posição baseada na direção
        if (playerInputProcessor.UP) {
            y += speed * delta;
            frameRow = 0; // Ajuste conforme a direção correta para cima
        } else if (playerInputProcessor.DOWN) {
            y -= speed * delta;
            frameRow = 2; // Ajuste conforme a direção correta para baixo
        } else if (playerInputProcessor.LEFT) {
            x -= speed * delta;
            frameRow = 3; // Ajuste conforme a direção correta para esquerda
        } else if (playerInputProcessor.RIGHT) {
            x += speed * delta;
            frameRow = 1; // Ajuste conforme a direção correta para direita
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

//        if(playerInputProcessor.UP){
//            frameRow = 0;
//            y += speed * delta;
//
//        } else if (playerInputProcessor.DOWN) {
//            frameRow = 2;
//            y -= speed * delta;
//
//        } else if (playerInputProcessor.LEFT) {
//            frameRow = 3;
//            x -= speed * delta;
//
//        } else if (playerInputProcessor.RIGHT) {
//            frameRow = 1;
//            x += speed * delta;
//
//        }
//
//        stateTime += delta;
//        Animation<TextureRegion> animation = animations[frameRow];
//        currentFrame = animation.getKeyFrame(stateTime);
//        setRegion(currentFrame);
//
//
//        //setRegion(frameCol * FRAME_WIDTH, frameRow * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
//        this.setPosition(x,y);
    }
    }
