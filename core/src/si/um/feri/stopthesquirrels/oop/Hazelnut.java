package si.um.feri.stopthesquirrels.oop;

import static com.badlogic.gdx.utils.TimeUtils.nanoTime;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import java.util.Iterator;

public class Hazelnut extends DynamicGameObject implements Pool.Poolable {
    public static final Pool<Hazelnut> poolHazelnuts = new Pool<Hazelnut>() {
        @Override
        protected Hazelnut newObject() {
            int size = (int) (Math.random() * 2);
            int speed = (int) (Math.random() * 10);
            return new Hazelnut(MathUtils.random(Assets.hazelnutImage32x32.getWidth(), Gdx.graphics.getWidth()) - Assets.hazelnutImage32x32.getWidth(), Gdx.graphics.getHeight() - 50, Assets.hazelnutImage32x32.getWidth(), Assets.hazelnutImage32x32.getHeight(), size, speed, nanoTime());
        }
    };
    public static Array<Hazelnut> hazelnuts = new Array<>();
    public float angle = 360;
    public float rand = (float) (Math.random() * 1);

    public Hazelnut(float x, float y, float width, float height, int s, int v, long t) {
        super(x, y, width, height, s, v, t);
    }

    public static Hazelnut obtain() {
        return poolHazelnuts.obtain();
    }

    public static void isItGameOver() {
        for (
                Iterator<Hazelnut> it = Hazelnut.hazelnuts.iterator(); it.hasNext(); ) {
            Hazelnut ha = it.next();
            ha.bounds.y -= 200 * Gdx.graphics.getDeltaTime() + ha.speed;
            if (ha.bounds.y < 0) {
                ha.free();
                it.remove();
            }
            if (ha.bounds.overlaps(Basket.basket.bounds)) {
                Assets.hazelnutSound.play();
                Score.score.hazelnutsPickedScore++;
                ha.free();
                it.remove();
            }
        }
    }

    @Override
    public void render() {
        Assets.batch.begin();
        Assets.batch.draw(Assets.hazelnutImage32x32, bounds.x, bounds.y, bounds.width / 2, bounds.height / 2, bounds.width, bounds.height, 1, 1, angle * rand, 0, 0, (int) bounds.width, (int) bounds.height, false, false);
        Assets.batch.end();
    }

    public void init() {
        int size = (int) (Math.random() * 2);
        int speed = (int) (Math.random() * 10);
        float x = MathUtils.random(Assets.hazelnutImage32x32.getWidth(), Gdx.graphics.getWidth() - Assets.hazelnutImage32x32.getWidth());
        float y = Gdx.graphics.getHeight() - 50;
        float width = Assets.hazelnutImage32x32.getWidth();
        float height = Assets.hazelnutImage32x32.getHeight();
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
        poolHazelnuts.free(this);
    }
}