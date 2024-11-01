package si.um.feri.stopthesquirrels.oop;

import static com.badlogic.gdx.utils.TimeUtils.nanoTime;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

public class Basket extends DynamicGameObject {
    public static Basket basket = new Basket(448, 112, Assets.basketImage.getWidth(), Assets.basketImage.getHeight(), nanoTime());

    public Basket(float x, float y, float width, float height, long t) {
        super(x, y, width, height, 0, 500, t);
    }

    @Override
    public void render() {
        if (Score.score.basketHealth > 0) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.P)) Pause.isPaused = !Pause.isPaused;
            if (!Pause.isPaused) {
                if (Gdx.input.isTouched()) Basket.basket.commandTouched();
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) Basket.basket.commandMoveLeft();
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) Basket.basket.commandMoveRight();
                if (Gdx.input.isKeyPressed(Input.Keys.A)) Basket.basket.commandMoveLeft();
                if (Gdx.input.isKeyPressed(Input.Keys.S)) Basket.basket.commandMoveRight();
            }
        }

        Assets.batch.begin();
        Assets.batch.draw(Assets.basketImage, bounds.x, bounds.y);
        Assets.pEffectBasket.draw(Assets.batch);
        Assets.batch.end();
    }

    public void commandMoveLeft() {
        bounds.x -= speed * Gdx.graphics.getDeltaTime();
        if (bounds.x < 0) commandMoveLeftCorner();
    }

    public void commandMoveRight() {
        bounds.x += speed * Gdx.graphics.getDeltaTime();
        if (bounds.x > Gdx.graphics.getWidth() - Assets.basketImage.getWidth())
            commandMoveRightCorner();
    }

    public void commandTouched() {
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        Assets.camera.unproject(touchPos);
        bounds.x = (int) (touchPos.x - Assets.basketImage.getWidth() / 2f);
        if (bounds.x < 0) commandMoveLeftCorner();
        if (bounds.x > Gdx.graphics.getWidth() - Assets.basketImage.getWidth())
            commandMoveRightCorner();
    }

    public void commandMoveLeftCorner() {
        bounds.x = 0;
    }

    public void commandMoveRightCorner() {
        bounds.x = Gdx.graphics.getWidth() - Assets.basketImage.getWidth();
    }

    public void updateEffect(float delta){
        Assets.pEffectBasket.update(delta);

        if (Assets.pEffectBasket.isComplete())
            Assets.pEffectBasket.reset();

        Assets.pEffectBasket.setPosition(
                bounds.x + Assets.basketImage.getWidth() / 2f, bounds.y
        );
    }
}
