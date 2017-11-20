package com.korba.gameoff.oblivious.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.korba.gameoff.oblivious.tools.*;

public class InventorySlotTooltip extends Window {

    private Label description;
    private Skin skin = AssetUtils.DEFAULT_SKIN;

    public InventorySlotTooltip() {
        super("", AssetUtils.DEFAULT_SKIN);

        description = new Label("", skin);

        this.add(description);
        this.padLeft(5).padRight(5);
        this.pack();
        this.setVisible(false);
    }

    public void setVisible(InventorySlot slot, boolean visible) {
        super.setVisible(visible);

        if(slot == null)
            return;

        if(!slot.hasItem())
            super.setVisible(false);
    }

    public void updateDescription(InventorySlot slot) {
        description.setText(slot.getItemDescription());
        this.pack();
    }

}
