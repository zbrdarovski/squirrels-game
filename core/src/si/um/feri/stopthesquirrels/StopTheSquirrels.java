package si.um.feri.stopthesquirrels;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class StopTheSquirrels extends ApplicationAdapter {
    // all values are set experimental
    private static final int SPEED = 500;    // pixels per second
    private static final int SPEED_HAZELNUT = 200; // pixels per second
    private static final long CREATE_SQUIRREL_TIME = 2000000000;    // ns
    private static final long CREATE_HAZELNUT_TIME = 1000000000;    // ns
    private static int SPEED_SQUIRREL = 100;    // pixels per second
    public BitmapFont font;
    private Texture backgroundImage;
    private Texture basketImage;
    private Texture squirrelImage;
    private Texture hazelnutImage;
    private Sound hazelnutSound;
    private Sound endSound;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Rectangle basket;
    private int basketHealth;
    private int hazelnutsPickedScore;
    private Array<Rectangle> squirrels;
    private Array<Rectangle> hazelnuts;
    private long lastSquirrelTime;
    private long lastHazelnutTime;

    @Override
    public void create() {
        basketHealth = 1;    // 1 life
        hazelnutsPickedScore = 0;
        font = new BitmapFont();
        font.getData().setScale(2);

        // default way to load a texture
        backgroundImage = new Texture(Gdx.files.internal("background.png"));
        basketImage = new Texture(Gdx.files.internal("basket.png"));
        squirrelImage = new Texture(Gdx.files.internal("squirrel64x64.png"));
        hazelnutImage = new Texture(Gdx.files.internal("hazelnut64x64.png"));
        hazelnutSound = Gdx.audio.newSound(Gdx.files.internal("hazelnut.wav"));
        endSound = Gdx.audio.newSound(Gdx.files.internal("end.wav"));

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        // create a Rectangle to logically represent the basket
        basket = new Rectangle();
        basket.x = 448;    // center the basket
        basket.y = 20;    // bottom left corner of the basket is 20 pixels above the bottom screen edge
        basket.width = basketImage.getWidth();
        basket.height = basketImage.getHeight();

        squirrels = new Array<>();
        hazelnuts = new Array<>();

        // add first squirrel and hazelnut
        spawnSquirrel();
        spawnHazelnut();
    }

    // runs every frame
    @Override
    public void render() {
        // clear screen
        Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // process user input
        if (Gdx.input.isTouched()) commandTouched();    // mouse or touch screen
        if (Gdx.input.isKeyPressed(Keys.LEFT)) commandMoveLeft();
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) commandMoveRight();
        if (Gdx.input.isKeyPressed(Keys.A)) commandMoveLeftCorner();
        if (Gdx.input.isKeyPressed(Keys.S)) commandMoveRightCorner();
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) commandExitGame();

        // check if we need to create a new squirrel or hazelnut
        if (TimeUtils.nanoTime() - lastSquirrelTime > CREATE_SQUIRREL_TIME) spawnSquirrel();
        if (TimeUtils.nanoTime() - lastHazelnutTime > CREATE_HAZELNUT_TIME) spawnHazelnut();

        // begin a new batch and draw the background, basket, squirrels, hazelnuts and score
        batch.begin();
        {   // brackets added just for indent
            batch.draw(backgroundImage, 0, 0);
            batch.draw(basketImage, basket.x, basket.y);
            for (Rectangle squirrel : squirrels) {
                batch.draw(squirrelImage, squirrel.x, squirrel.y);
            }
            for (Rectangle hazelnut : hazelnuts) {
                batch.draw(hazelnutImage, hazelnut.x, hazelnut.y);
            }
            font.setColor(Color.BROWN);
            font.draw(batch, "SCORE: " + hazelnutsPickedScore, Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 20);
        }
        batch.end();

        if (basketHealth > 0) {    // game over?
            // move and remove any squirrel or hazelnut element that is beneath the bottom edge of the screen or that hits the basket
            for (Iterator<Rectangle> it = squirrels.iterator(); it.hasNext(); ) {
                Rectangle squirrel = it.next();
                squirrel.y -= SPEED_SQUIRREL * Gdx.graphics.getDeltaTime();
                if (squirrel.y + squirrelImage.getHeight() < 0) it.remove();
                if (squirrel.overlaps(basket)) {
                    endSound.play();
                    basketHealth--;
                }
            }

            for (Iterator<Rectangle> it = hazelnuts.iterator(); it.hasNext(); ) {
                Rectangle hazelnut = it.next();
                hazelnut.y -= SPEED_HAZELNUT * Gdx.graphics.getDeltaTime();
                if (hazelnut.y + hazelnutImage.getHeight() < 0) it.remove();    // from screen
                if (hazelnut.overlaps(basket)) {
                    hazelnutSound.play();
                    hazelnutsPickedScore++;
                    if (hazelnutsPickedScore % 10 == 0)
                        SPEED_SQUIRREL += 50;    // speeds up squirrels
                    it.remove();    // smart Array enables remove from Array
                }
            }
        } else {    // game over (the squirrel sneaked into the basket)
            batch.begin();
            {
                font.setColor(Color.BROWN);
                font.draw(batch, "GAME OVER!", Gdx.graphics.getHeight() / 2f, Gdx.graphics.getHeight() / 2f);
            }
            batch.end();
        }

        // tell the camera to update its matrices
        camera.update();

        // tell the SpriteBatch to render in the coordinate system specified by the camera
        batch.setProjectionMatrix(camera.combined);
    }

    // release all the native resources
    @Override
    public void dispose() {
        backgroundImage.dispose();
        basketImage.dispose();
        squirrelImage.dispose();
        hazelnutImage.dispose();
        hazelnutSound.dispose();
        endSound.dispose();
        batch.dispose();
        font.dispose();
    }

    private void spawnSquirrel() {
        Rectangle squirrel = new Rectangle();
        squirrel.x = MathUtils.random(0, Gdx.graphics.getWidth() - hazelnutImage.getWidth());
        squirrel.y = Gdx.graphics.getHeight();
        squirrel.width = squirrelImage.getWidth();
        squirrel.height = squirrelImage.getHeight();
        squirrels.add(squirrel);
        lastSquirrelTime = TimeUtils.nanoTime();
    }

    private void spawnHazelnut() {
        Rectangle hazelnut = new Rectangle();
        hazelnut.x = MathUtils.random(0, Gdx.graphics.getWidth() - hazelnutImage.getWidth());
        hazelnut.y = Gdx.graphics.getHeight();
        hazelnut.width = hazelnutImage.getWidth();
        hazelnut.height = hazelnutImage.getHeight();
        hazelnuts.add(hazelnut);
        lastHazelnutTime = TimeUtils.nanoTime();
    }

    private void commandMoveLeft() {
        basket.x -= SPEED * Gdx.graphics.getDeltaTime();
        if (basket.x < 0) basket.x = 0;
    }

    private void commandMoveRight() {
        basket.x += SPEED * Gdx.graphics.getDeltaTime();
        if (basket.x > Gdx.graphics.getWidth() - basketImage.getWidth())
            basket.x = Gdx.graphics.getWidth() - basketImage.getWidth();
    }

    private void commandMoveLeftCorner() {
        basket.x = 0;
    }

    private void commandMoveRightCorner() {
        basket.x = Gdx.graphics.getWidth() - basketImage.getWidth();
    }

    private void commandTouched() {
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        basket.x = touchPos.x - basketImage.getWidth() / 2f;
    }

    private void commandExitGame() {
        Gdx.app.exit();
    }
}