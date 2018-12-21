package brightspark.testmod.init;

import brightspark.testmod.TestMod;
import brightspark.testmod.item.ItemUseExample;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

@GameRegistry.ObjectHolder(TestMod.MOD_ID)
public class TMItems
{
    //Contains all registered items
    public static List<Item> ITEMS;

    public static final Item itemuseexample = null;

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
        return ITEMS.toArray(new Item[0]);
    }

    public static void voidLists()
    {
        ITEMS = null;
    }
}
