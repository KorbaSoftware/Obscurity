package com.korba.gameoff.oblivious.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.korba.gameoff.oblivious.ObscurityGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new ObscurityGame(), config);
		config.height = 720;
		config.width = 1280;
	}
}
