package si.um.feri.stopthesquirrels.oop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import si.um.feri.util.debug.DebugCameraController;
import si.um.feri.util.debug.MemoryInfo;

public class Assets {
    public static Texture backgroundImage;
    public static Texture basketImage;
    public static Texture squirrelImage64x64;
    public static Texture hazelnutImage32x32;
    public static Texture blanketImage;
    public static Sound hazelnutSound;
    public static Sound endSound;
    public static BitmapFont font;
    public static SpriteBatch batch;
    public static OrthographicCamera camera;

    public static DebugCameraController debugCameraController;
    public static MemoryInfo memoryInfo;

    public static ShapeRenderer shapeRenderer;
    public static Viewport viewport;

    public static ParticleEffect pEffectBasket;
    public static ParticleEffect pEffectsOtherLeft;
    public static ParticleEffect pEffectsOtherCenterLeft;
    public static ParticleEffect pEffectsOtherCenterRight;
    public static ParticleEffect pEffectsOtherRight;


    public static void load() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        backgroundImage = new Texture(Gdx.files.internal("background.png"));
        basketImage = new Texture(Gdx.files.internal("basket.png"));
        squirrelImage64x64 = new Texture(Gdx.files.internal("squirrel64x64.png"));
        hazelnutImage32x32 = new Texture(Gdx.files.internal("hazelnut32x32.png"));
        blanketImage = new Texture(Gdx.files.internal("blanket.png"));

        hazelnutSound = Gdx.audio.newSound(Gdx.files.internal("hazelnut.wav"));
        endSound = Gdx.audio.newSound(Gdx.files.internal("end.wav"));

        debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        memoryInfo = new MemoryInfo(500);

        shapeRenderer = new ShapeRenderer();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Assets.camera);

        pEffectBasket = new ParticleEffect();
        pEffectBasket.load(Gdx.files.internal("basket.pe"), Gdx.files.internal(("")));

        pEffectsOtherLeft = new ParticleEffect();
        pEffectsOtherLeft.load(Gdx.files.internal("other.pe"), Gdx.files.internal(("")));

        pEffectsOtherCenterLeft = new ParticleEffect();
        pEffectsOtherCenterLeft.load(Gdx.files.internal("other.pe"), Gdx.files.internal(("")));

        pEffectsOtherCenterRight = new ParticleEffect();
        pEffectsOtherCenterRight.load(Gdx.files.internal("other.pe"), Gdx.files.internal(("")));

        pEffectsOtherRight = new ParticleEffect();
        pEffectsOtherRight.load(Gdx.files.internal("other.pe"), Gdx.files.internal(("")));

        font = new BitmapFont();
        font.getData().setScale(2);

        batch = new SpriteBatch();
    }

    public static void dispose() {
        backgroundImage.dispose();
        basketImage.dispose();
        squirrelImage64x64.dispose();
        hazelnutImage32x32.dispose();
        blanketImage.dispose();
        hazelnutSound.dispose();
        endSound.dispose();
        font.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }
}
