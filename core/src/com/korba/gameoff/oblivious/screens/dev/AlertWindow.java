package com.korba.gameoff.oblivious.screens.dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.korba.gameoff.oblivious.tools.AssetUtils;


public class AlertWindow extends Dialog {

    private AlertWindow thisOverlay;

    public AlertWindow(Stage stage) {
        super("", AssetUtils.DEFAULT_SKIN, "dialog");
        thisOverlay = this;
        thisOverlay.text("Are you sure?");
        thisOverlay.button("Yes", "Yes");
        thisOverlay.button("No", "No");
        thisOverlay.show(stage);
    }

    @Override
    protected void result(Object obj) {
        switch (obj.toString()) {
            case "Yes": {
                Gdx.app.exit();
                break;
            }
            case "No": {
                this.remove();
                break;
            }
        }
    }


}
