package com.korba.gameoff.oblivious.inventory.items;

import com.korba.gameoff.oblivious.tools.*;

import java.util.*;

public class CombineMachine {

    public static Item combineItems(Item... items) {
        List<Item> itemsList = new ArrayList<>();

        for(int i = 0; i < items.length; i++) {
            if(items[i] == null)
                continue;

            if(!items[i].isCombinable())
                return null;

            itemsList.add(items[i]);
        }

        return getItem(itemsList);
    }

    private static Item getItem(List<Item> items) {
        if(items.isEmpty())
            return null;

        int howManyItems = items.size();

        switch(howManyItems) {
            case 2: {
                int combineItemId = items.get(0).getId() + items.get(1).getId();
                switch(combineItemId) {
                    case ItemsIds.MECHA_PINDOL: {
                        return new Item(AssetUtils.getTexture(AssetUtils.ITEM_MECHA_PINDOL), "Mecha pindol", false, ItemsIds.MECHA_PINDOL);
                    }
                    default:
                        return null;
                }
            }
            case 3: {
                return null;
            }
            default: {
                return null;
            }
        }
    }

}
