package com.korba.gameoff.oblivious.equipment;

import com.korba.gameoff.oblivious.equipment.items.*;

import java.util.*;

public class EquipmentInside implements Equipment {

    private Set<Item> items;

    public EquipmentInside() {
        items = new HashSet<Item>();
    }

    public void putItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void combine(Item item1, Item item2, Item item3) {
        boolean combined = false;
        //TODO combine
    
        Item combinedItem = null;

        if(combined) {
            removeItem(item1);
            removeItem(item2);
            removeItem(item3);
            putItem(combinedItem);
        }
    }

    @Override
    public void dispose() {

    }
}
