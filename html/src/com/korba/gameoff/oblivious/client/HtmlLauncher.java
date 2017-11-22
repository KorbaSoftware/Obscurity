package com.korba.gameoff.oblivious.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.korba.gameoff.oblivious.ObscurityGame;
import com.korba.gameoff.oblivious.config.LauncherConfig;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(LauncherConfig.WIDTH, LauncherConfig.HEIGHT);
        }

        @Override
        public ApplicationListener createApplicationListener() {
                return new ObscurityGame();
        }
}