package si.um.feri.stopthesquirrels.oop;

import static com.badlogic.gdx.utils.TimeUtils.nanoTime;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import java.util.Iterator;

public class Blanket extends DynamicGameObject implements Pool.Poolable {
    public static Array<Blanket> blankets = new Array<>();
    public static final Pool<Blanket> poolBlankets = new Pool<Blanket>() {
        @Override
        protected Blanket newObject() {
            int size = (int) (Math.random() * 2);
            int speed = (int) (Math.random() * 10);
            return new Blanket(MathUtils.random(Assets.blanketImage.getWidth(), Gdx.graphics.getWidth()) - Assets.blanketImage.getWidth(), Gdx.graphics.getHeight() - 50, Assets.blanketImage.getWidth(), Assets.blanketImage.getHeight(), size, speed, nanoTime());
        }
    };

    public Blanket(float x, float y, float width, float height, int s, int v, long t) {
        super(x, y, width, height, s, v, t);
    }

    public static Blanket obtain() {
        return poolBlankets.obtain();
    }

    @Override
    public void render() {
        Assets.batch.begin();
        Assets.batch.draw(Assets.blanketImage, bounds.x, bounds.y);
        Assets.batch.end();
    }

    public void init() {
        int size = (int) (Math.random() * 2);
        int speed = (int) (Math.random() * 10);
        float x = MathUtils.random(Assets.blanketImage.getWidth(), Gdx.graphics.getWidth() - Assets.blanketImage.getWidth());
        float y = Gdx.graphics.getHeight() - 50;
        float width = Assets.blanketImage.getWidth();
        float height = Assets.blanketImage.getHeight();
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
        this.size = size;
        this.speed = speed;
        this.createTime = nanoTime();
    }

    @Override
    public void reset() {
        init();
    }

    public void free() {
        poolBlankets.free(this);
    }

    public static void isItGameOver(){
        for (Iterator<Blanket> it = Blanket.blankets.iterator(); it.hasNext(); ) {
            Blanket bl = it.next();
            bl.bounds.y -= 100 * Gdx.graphics.getDeltaTime() + bl.speed;
            if (bl.bounds.y < 0) {
                bl.free();
                it.remove();
            }
            if (bl.bounds.overlaps(Basket.basket.bounds)) {
                Assets.hazelnutSound.play();
                StopTheSquirrels.safe = true;
                bl.free();
                it.remove();
            }
        }
    }
}

