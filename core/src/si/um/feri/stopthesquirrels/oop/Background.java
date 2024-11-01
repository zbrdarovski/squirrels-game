package si.um.feri.stopthesquirrels.oop;


import com.badlogic.gdx.Gdx;

public class Background extends GameObject {
    public static Background background = new Background(0, 0, Assets.backgroundImage.getWidth(), Assets.backgroundImage.getHeight());

    public Background(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void render() {
        Assets.batch.begin();
        Assets.batch.draw(Assets.backgroundImage, 0, 0);
        Assets.pEffectsOtherLeft.draw(Assets.batch);
        Assets.pEffectsOtherCenterLeft.draw(Assets.batch);
        Assets.pEffectsOtherCenterRight.draw(Assets.batch);
        Assets.pEffectsOtherRight.draw(Assets.batch);
        Assets.batch.end();
    }

    public void updateEffect(float delta) {
        Assets.pEffectsOtherLeft.update(delta);

        if (Assets.pEffectsOtherLeft.isComplete())
            Assets.pEffectsOtherLeft.reset();

        Assets.pEffectsOtherLeft.setPosition(
                40, 0
        );


        Assets.pEffectsOtherCenterLeft.update(delta);

        if (Assets.pEffectsOtherCenterLeft.isComplete())
            Assets.pEffectsOtherCenterLeft.reset();
        int x1 = Gdx.graphics.getWidth() / 3;
        Assets.pEffectsOtherCenterLeft.setPosition(
                x1, 0
        );


        Assets.pEffectsOtherCenterRight.update(delta);

        if (Assets.pEffectsOtherCenterRight.isComplete())
            Assets.pEffectsOtherCenterRight.reset();

        int x2 = Gdx.graphics.getWidth() / 3 * 2;
        Assets.pEffectsOtherCenterRight.setPosition(
                x2, 0
        );


        Assets.pEffectsOtherRight.update(delta);

        if (Assets.pEffectsOtherRight.isComplete())
            Assets.pEffectsOtherRight.reset();

        Assets.pEffectsOtherRight.setPosition(
                Gdx.graphics.getWidth() - 40, 0
        );
    }
}
