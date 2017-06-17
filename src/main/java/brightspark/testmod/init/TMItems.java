package brightspark.testmod.init;

import brightspark.testmod.item.ItemUseExample;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class TMItems
{
    //Contains all registered items
    public static Map<String, Item> ITEMS = new HashMap<String, Item>();

    private static void regItem(Item item)
    {
        ITEMS.put(item.getRegistryName().getResourcePath().toLowerCase(), item);
    }

    public static void init()
    {
        //Make sure we only register once
        if(!ITEMS.isEmpty()) return;

        regItem(new ItemUseExample());
    }
}
