package si.um.feri.stopthesquirrels.oop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.TimeUtils;

public class Pause extends GameObject {
    public static Pause pause = new Pause(Gdx.graphics.getHeight() / 2f + 160, Gdx.graphics.getHeight() / 2f, 0, 0);
    public static boolean isPaused = false;

    public Pause(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void render() {
        if(End.isEnd == false) {
            if (Gdx.input.isKeyPressed(Input.Keys.P)) pause.commandPauseGame();
            if (isPaused) {
                Assets.batch.begin();
                {
                    Assets.font.setColor(Color.WHITE);
                    Assets.font.draw(Assets.batch, "GAME PAUSED!", bounds.x, bounds.y);
                }
                Assets.batch.end();
            }

            if (!isPaused) {
                if (Blanket.blankets.size == 0) {
                    Blanket.blankets.add(Blanket.obtain());
                } else if (TimeUtils.nanoTime() - Blanket.blankets.get(Blanket.blankets.size - 1).createTime > 2000000000) {
                    Blanket.blankets.get(Blanket.blankets.size - 1);
                    if (!End.isEnd)
                        Blanket.blankets.add(Blanket.obtain());
                }
                if (Squirrel.squirrels.size == 0) {
                    Squirrel.squirrels.add(Squirrel.obtain());
                } else if (TimeUtils.nanoTime() - Squirrel.squirrels.get(Squirrel.squirrels.size - 1).createTime > 1000000000) {
                    Squirrel.squirrels.get(Squirrel.squirrels.size - 1);
                    if (!End.isEnd)
                        Squirrel.squirrels.add(Squirrel.obtain());
                }
                if (Hazelnut.hazelnuts.size == 0) {
                    Hazelnut.hazelnuts.add(Hazelnut.obtain());
                } else if (TimeUtils.nanoTime() - Hazelnut.hazelnuts.get(Hazelnut.hazelnuts.size - 1).createTime > 500000000) {
                    Hazelnut.hazelnuts.get(Hazelnut.hazelnuts.size - 1);
                    if (!End.isEnd)
                        Hazelnut.hazelnuts.add(Hazelnut.obtain());
                }
                StopTheSquirrels.isItGameOver();
                StopTheSquirrels.updateEffects(Gdx.graphics.getDeltaTime());
            }
        }
    }

    public static void commandPauseGame () {
        isPaused = !isPaused;
    }
}
