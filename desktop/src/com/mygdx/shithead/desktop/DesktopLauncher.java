package com.mygdx.shithead.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.shithead.shitheadmain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "ShitHead";
		config.width = 1920;
		config.height = 1080;
		new LwjglApplication(new shitheadmain(), config);
	}
}
