package si.um.feri.stopthesquirrels.oop.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import si.um.feri.stopthesquirrels.oop.StopTheSquirrels;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Stop The Squirrels";
        config.width = 1024;
        config.height = 480;
        config.forceExit = false;
        config.addIcon("icon.png", Files.FileType.Internal);

        config.vSyncEnabled = true;
        config.foregroundFPS = 60;
        config.backgroundFPS = 60;
        new LwjglApplication(new StopTheSquirrels(), config);
    }
}
