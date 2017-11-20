package com.korba.gameoff.oblivious.inventory;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.*;
import com.korba.gameoff.oblivious.inventory.items.*;
import com.korba.gameoff.oblivious.tools.*;

public class InventorySlot extends Stack {

    private Stack stack;
    private Image customBackground;

    public InventorySlot() {
        stack = new Stack();
        customBackground = new Image();
        if(AssetUtils.isAssetLoaded(AssetUtils.ITEM_PLACEHOLDER))
            customBackground = new Image(AssetUtils.getTexture(AssetUtils.ITEM_PLACEHOLDER));

        stack.add(customBackground);
        this.add(stack);
    }

    public InventorySlot(Image  customBackground) {
        this();
        this.customBackground = customBackground;
        stack.add(customBackground);
    }

    public static void swapSlots(InventorySlot slotSource, InventorySlot slotTarget, Item dragItem) {
        slotSource.add(slotTarget.getItem());
        slotTarget.add(dragItem);
    }

    public Item getItem() {
        Array<Item> items = new Array<>();
        if(hasItem()) {
            SnapshotArray<Actor> arrayChildren = this.getChildren();
            items.add((Item) arrayChildren.pop());
        }

        return items.get(0);
    }

    public boolean hasItem() {
        if(hasChildren()) {
            SnapshotArray<Actor> items = this.getChildren();
            if(items.size > 1)
                return true;
        }
        return false;
    }

    public String getItemDescription() {
        if(hasChildren()) {
            SnapshotArray<Actor> items = this.getChildren();
            if(items.size > 1) {
                Item item = (Item) items.pop();
                return item.getDescription();
            }
        }
        return "";
    }

}
