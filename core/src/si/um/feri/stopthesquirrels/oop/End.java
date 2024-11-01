package si.um.feri.stopthesquirrels.oop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

public class End extends GameObject {
    public static End end = new End(Gdx.graphics.getHeight() / 2f + 180, Gdx.graphics.getHeight() / 2f, 0, 0);
    public static boolean isEnd = false;

    public End(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) end.commandExitGame();
        if (isEnd) {
            Assets.batch.begin();
            {
                Assets.font.setColor(Color.WHITE);
                Assets.font.draw(Assets.batch, "GAME OVER!", bounds.x, bounds.y);
            }
            Assets.batch.end();
        }
    }

    public void commandExitGame() {
        Gdx.app.exit();
    }
}
