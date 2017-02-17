package brightspark.testmod.handler;

import brightspark.testmod.init.TMBlocks;
import brightspark.testmod.init.TMItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

@Mod.EventBusSubscriber
public class RegistrationHandler
{
    @SubscribeEvent
    public static void initItems(RegistryEvent.Register<Item> event)
    {
        //Register all items
        TMItems.init();
        IForgeRegistry<Item> registry = event.getRegistry();
        for(Item item : TMItems.ITEMS.values())
            registry.register(item);

        //Register item blocks
        TMBlocks.init();
        for(Item item : TMBlocks.ITEM_BLOCKS.values())
            registry.register(item);
    }

    @SubscribeEvent
    public static void regBlocks(RegistryEvent.Register<Block> event)
    {
        //Register all blocks
        TMBlocks.init();
        IForgeRegistry<Block> registry = event.getRegistry();
        for(Block block : TMBlocks.BLOCKS.values())
            registry.register(block);
    }
}
