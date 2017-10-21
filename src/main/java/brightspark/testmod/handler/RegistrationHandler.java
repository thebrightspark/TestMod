package brightspark.testmod.handler;

import brightspark.testmod.init.TMBlocks;
import brightspark.testmod.init.TMItems;
import brightspark.testmod.item.TMItem;
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
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(TMItems.getItems());
    }

    @SubscribeEvent
    public static void regBlocks(RegistryEvent.Register<Block> event)
    {
        //Register all blocks
        event.getRegistry().registerAll(TMBlocks.getBlocks());
        TMBlocks.regTileEntities();
    }
}
