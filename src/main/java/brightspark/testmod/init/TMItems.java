package brightspark.testmod.init;

import brightspark.testmod.item.ItemUseExample;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class TMItems
{
    //Contains all registered items
    public static List<Item> ITEMS;

    public static void init()
    {
        ITEMS = new ArrayList<>();

        addItem(new ItemUseExample());
    }

    private static void addItem(Item item)
    {
        ITEMS.add(item);
    }

    public static Item[] getItems()
    {
        if(ITEMS == null) init();
        return ITEMS.toArray(new Item[ITEMS.size()]);
    }

    public static void voidLists()
    {
        ITEMS = null;
    }
}
