package si.um.feri.stopthesquirrels.oop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import si.um.feri.util.ViewportUtils;

public class Debug {
    public static boolean debug = false;

    public static void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) debug = !debug;

        if (debug) {
            Assets.debugCameraController.handleDebugInput(Gdx.graphics.getDeltaTime());
            Assets.memoryInfo.update();

            Assets.debugCameraController.applyTo(Assets.camera);
            Assets.batch.begin();
            {
                Assets.memoryInfo.render(Assets.batch, Assets.font);
            }
            Assets.batch.end();

            Assets.batch.totalRenderCalls = 0;
            ViewportUtils.drawGrid(Assets.viewport, Assets.shapeRenderer, 50);

            Assets.shapeRenderer.setProjectionMatrix(Assets.camera.combined);
            Assets.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            {
                Assets.shapeRenderer.setColor(1, 1, 0, 1);
                for (Squirrel sq : Squirrel.squirrels) {
                    Assets.shapeRenderer.rect(sq.bounds.x, sq.bounds.y, Assets.squirrelImage64x64.getWidth(), Assets.squirrelImage64x64.getHeight());
                }
                for (Hazelnut ha : Hazelnut.hazelnuts) {
                    Assets.shapeRenderer.rect(ha.bounds.x, ha.bounds.y, Assets.hazelnutImage32x32.getWidth(), Assets.hazelnutImage32x32.getHeight());
                }
                for (Blanket bl : Blanket.blankets) {
                    Assets.shapeRenderer.rect(bl.bounds.x, bl.bounds.y, Assets.blanketImage.getWidth(), Assets.blanketImage.getHeight());
                }
                Assets.shapeRenderer.rect(Basket.basket.bounds.x, Basket.basket.bounds.y, Assets.basketImage.getWidth(), Assets.basketImage.getHeight());
            }
            Assets.shapeRenderer.end();
        }
    }
}
