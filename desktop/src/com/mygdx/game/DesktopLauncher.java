package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(1920, 960);
		config.useVsync(false);
		config.setTitle("Color Splash");
		config.setWindowIcon("Icon.png");
		new Lwjgl3Application(new ColorSplash(), config);
	}
}
