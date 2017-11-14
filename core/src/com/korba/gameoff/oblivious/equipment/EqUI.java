package com.korba.gameoff.oblivious.equipment;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.*;
import com.korba.gameoff.oblivious.equipment.items.*;
import com.korba.gameoff.oblivious.equipment.slot.*;
import com.korba.gameoff.oblivious.tools.*;

public class EqUI extends Window {

    private EqSlotTooltip eqSlotTooltip;
    private DragAndDrop dragAndDrop;
    private Array<Actor> eqActors;

    private Table eqTable;
    private Table eqSlotsTable;
    private Table combineItemsTable;

    private final int numberOfSlots = 36;
    private final int slotsInRow = 6;

    private final int slotWidth = 64;
    private final int slotHeight = 64;

    public EqUI(Skin skin) {
       super("Equipment", skin);
       dragAndDrop = new DragAndDrop();
       eqActors = new Array<Actor>();

       eqSlotsTable = new Table();
       eqSlotsTable.setName("Eq_slots_table");

       eqSlotTooltip = new EqSlotTooltip(AssetUtils.DEFAULT_SKIN);

       AssetUtils.loadTexture(AssetUtils.ITEM_PLACEHOLDER);

       for(int i = 1; i <= numberOfSlots; i++) {
            EqSlot eqSlot = new EqSlot(new Item(AssetUtils.ITEM_PLACEHOLDER, Item.ItemID.PLACEHOLDER, false, "Empty slot", null));
            eqSlot.addListener(new EqSlotTooltipListener(eqSlotTooltip));
            dragAndDrop.addTarget(new EqSlotTarget(eqSlot));
            eqSlotsTable.add(eqSlot).size(slotWidth, slotHeight);

            if(i % slotsInRow == 0)
                eqSlotsTable.row();
       }

       eqActors.add(eqSlotTooltip);

       AssetUtils.loadTexture(AssetUtils.ITEM_RADIO);
       Array<Cell> sourceCells = eqSlotsTable.getCells();
       for(int i = 0; i < sourceCells.size; i++) {
           EqSlot slot = ((EqSlot) sourceCells.get(i).getActor());
           if(slot == null) continue;
           if(i % 3 == 0) {
               //.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture("90.png")));
              Item item = new Item(AssetUtils.ITEM_RADIO, Item.ItemID.RADIO, false, "Radio. Not working", null);
              slot.addItem(item);
              dragAndDrop.addSource(new EqSlotSource(slot, dragAndDrop));
          }
       }

       //fill eq

       this.row();
       this.add(eqSlotsTable).colspan(2);
       this.row();
       this.pack();
    }

    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }

    public Table getEqTable() {
        return eqTable;
    }

    public Table getEqSlotsTable() {
        return eqSlotsTable;
    }

    public Table getCombineItemsTable() {
        return combineItemsTable;
    }

    public Array<Actor> getEqActors() {
        return eqActors;
    }
}
