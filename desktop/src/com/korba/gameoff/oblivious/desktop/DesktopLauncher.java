package com.korba.gameoff.oblivious.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.LauncherConfig;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new ObscurityGame(), config);
		config.title = LauncherConfig.APP_NAME;
		config.height = LauncherConfig.HEIGHT;
		config.width = LauncherConfig.WIDTH;
		config.foregroundFPS = LauncherConfig.FOREGROUND_FPS;
		config.backgroundFPS = LauncherConfig.BACKGROUND_FPS;
		config.vSyncEnabled = LauncherConfig.vSync;
		config.fullscreen = LauncherConfig.FULLSCREEN;
	}
}
