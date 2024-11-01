package si.um.feri.stopthesquirrels.oop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Score extends GameObject {
    public static Score score = new Score(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 20, 0, 0);
    public int hazelnutsPickedScore = 0;
    public int basketHealth = 1;

    public Score(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void render() {
        Assets.batch.begin();
        Assets.font.setColor(Color.WHITE);
        Assets.font.draw(Assets.batch, "SCORE: " + hazelnutsPickedScore, bounds.x, bounds.y);
        Assets.batch.end();
    }

    public int getBasketHealth() {
        return basketHealth;
    }

    public void setBasketHealth(int basketHealth) {
        this.basketHealth = basketHealth;
    }
}

