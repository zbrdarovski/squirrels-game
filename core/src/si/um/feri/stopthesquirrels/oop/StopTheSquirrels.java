package si.um.feri.stopthesquirrels.oop;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class StopTheSquirrels extends ApplicationAdapter {
    public static boolean safe = false;

    public static void isItGameOver() {
        if (Score.score.getBasketHealth() > 0) {
            Squirrel.isItGameOver();
            Hazelnut.isItGameOver();
            Blanket.isItGameOver();
        } else {
            End.isEnd = true;
        }
    }

    public static void updateEffects(float delta) {
        Basket.basket.updateEffect(delta);
        Background.background.updateEffect(delta);
    }

    @Override
    public void create() {
        Assets.load();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Background.background.render();
        Basket.basket.render();
        Score.score.render();
        Debug.render();

        for (DynamicGameObject dgm : Squirrel.squirrels) {
            dgm.render();
        }

        for (DynamicGameObject dgm : Blanket.blankets) {
            dgm.render();
        }

        for (DynamicGameObject dgm : Hazelnut.hazelnuts) {
            dgm.render();
        }

        Pause.pause.render();
        End.end.render();

        Assets.camera.update();
        Assets.batch.setProjectionMatrix(Assets.camera.combined);
    }

    @Override
    public void dispose() {
        Assets.dispose();
    }
}