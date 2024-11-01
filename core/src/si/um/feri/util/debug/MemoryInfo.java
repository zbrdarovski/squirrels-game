package si.um.feri.util.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MemoryInfo {
    public static final long MB = 1024 * 1024;
    private final Runtime instance;
    private final long updateIntervalMs;
    private long lastUpdate;
    private long totalMemory;   // total amount of memory in the Java virtual machine
    private long freeMemory;    // amount of free memory in the Java Virtual Machine
    private long usedMemory;
    private long maxMemory;     // maximum amount of memory that the Java virtual machine will attempt to use
    private long javaHeap;      // Java heap memory use in bytes
    private long nativeHeap;    // native heap memory use in bytes

    public MemoryInfo(long updateIntervalMs) {
        instance = Runtime.getRuntime();
        this.updateIntervalMs = updateIntervalMs;
        lastUpdate = 0;
        update();
    }

    public void update() {
        if ((lastUpdate + updateIntervalMs) < System.currentTimeMillis()) {
            totalMemory = instance.totalMemory() / MB;
            freeMemory = instance.freeMemory() / MB;
            usedMemory = instance.totalMemory() / MB - freeMemory;
            maxMemory = instance.maxMemory() / MB;
            javaHeap = Gdx.app.getJavaHeap() / MB;
            nativeHeap = Gdx.app.getNativeHeap() / MB;
            lastUpdate = System.currentTimeMillis();
        }
    }

    public void render(SpriteBatch batch, BitmapFont font) {
        float positionY = 100 + font.getCapHeight();
        font.setColor(Color.WHITE);
        font.draw(batch, "totalMemory:" + totalMemory + " mb", 20, positionY);
        positionY += font.getCapHeight() + 10;
        font.draw(batch, "freeMemory :" + freeMemory + " mb", 20, positionY);
        positionY += font.getCapHeight() + 10;
        font.draw(batch, "usedMemory :" + usedMemory + " mb", 20, positionY);
        positionY += font.getCapHeight() + 10;
        font.draw(batch, "maxMemory  :" + maxMemory + " mb", 20, positionY);
        positionY += font.getCapHeight() + 10;
        font.draw(batch, "javaHeap   :" + javaHeap + " mb", 20, positionY);
        positionY += font.getCapHeight() + 10;
        font.draw(batch, "nativeHeap :" + nativeHeap + " mb", 20, positionY);
        positionY += font.getCapHeight() + 10;
    }
}
