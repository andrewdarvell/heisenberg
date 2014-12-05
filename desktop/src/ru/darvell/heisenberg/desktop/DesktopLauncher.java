package ru.darvell.heisenberg.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.darvell.heisenberg.HeisenbergGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
		config.title = "Heisenberg\'s Revenge";
		new LwjglApplication(new HeisenbergGame(), config);
	}
}
