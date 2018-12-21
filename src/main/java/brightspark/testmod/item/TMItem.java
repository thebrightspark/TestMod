package brightspark.testmod.item;

import brightspark.testmod.TestMod;
import net.minecraft.item.Item;

public class TMItem extends Item
{
    public TMItem(String name)
    {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(TestMod.CREATIVE_TAB);
    }
}
