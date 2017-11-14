package com.korba.gameoff.oblivious.equipment.slot;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.korba.gameoff.oblivious.equipment.items.*;

public class EqSlotTooltip extends Window {

    private Skin skin;
    private Label description;

    public EqSlotTooltip(Skin skin) {
        super("", skin);
        this.skin = skin;

        description = new Label("", skin);
        this.padLeft(5).padRight(5);
        this.pack();
        this.setVisible(false);
    }

    public void setVisible(EqSlot slot, boolean visible) {
        super.setVisible(visible);

        if(slot == null)
            return;

        if(!slot.hasItem())
            super.setVisible(false);
    }

    public void updateDescription(EqSlot slot) {
        if(slot.hasItem()) {
            Item item = slot.getItem();
            description.setText(item.getDescription());

            this.pack();
        } else {
            description.setText("");
            this.pack();
        }
    }

}
