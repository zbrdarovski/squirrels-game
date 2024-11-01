package si.um.feri.stopthesquirrels.oop;

import static com.badlogic.gdx.utils.TimeUtils.nanoTime;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import java.util.Iterator;

public class Squirrel extends DynamicGameObject implements Pool.Poolable {
    public static Array<Squirrel> squirrels = new Array<>();
    public static final Pool<Squirrel> poolSquirrels = new Pool<Squirrel>() {
        protected Squirrel newObject() {
            int size = (int) (Math.random() * 2);
            int speed = (int) (Math.random() * 10);
            return new Squirrel(MathUtils.random(Assets.squirrelImage64x64.getWidth(), Gdx.graphics.getWidth() - Assets.squirrelImage64x64.getWidth()), Gdx.graphics.getHeight(), Assets.squirrelImage64x64.getWidth(), Assets.squirrelImage64x64.getHeight(), size, speed, nanoTime());
        }
    };

    public Squirrel(float x, float y, float width, float height, int s, int v, long t) {
        super(x, y, width, height, s, v, t);
    }

    @Override
    public void render() {
        Assets.batch.begin();
        Assets.batch.draw(Assets.squirrelImage64x64, bounds.x, bounds.y);
        Assets.batch.end();
    }

    public void init() {
        int size = (int) (Math.random() * 2);
        int speed = (int) (Math.random() * 10);
        float x = MathUtils.random(Assets.squirrelImage64x64.getWidth(), Gdx.graphics.getWidth() - Assets.squirrelImage64x64.getWidth());
        float y = Gdx.graphics.getHeight();
        float width = Assets.squirrelImage64x64.getWidth();
        float height = Assets.squirrelImage64x64.getHeight();
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
        poolSquirrels.free(this);
    }
    public static Squirrel obtain() {
        return poolSquirrels.obtain();
    }

    public static void isItGameOver(){
        for (Iterator<Squirrel> it = Squirrel.squirrels.iterator(); it.hasNext(); ) {
            Squirrel sq = it.next();
            sq.bounds.y -= 100 * Gdx.graphics.getDeltaTime() + sq.speed;
            if (sq.bounds.y < 0) {
                sq.free();
                it.remove();
            }
            if (sq.bounds.overlaps(Basket.basket.bounds)) {
                if (StopTheSquirrels.safe) {
                    Assets.endSound.play();
                    StopTheSquirrels.safe = false;
                    sq.free();
                    it.remove();
                } else {
                    Assets.endSound.play();
                    Score.score.setBasketHealth(0);
                }
            }
        }
    }
}
