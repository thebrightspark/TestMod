package brightspark.testmod.handler;

import brightspark.testmod.TestMod;
import brightspark.testmod.init.TMBlocks;
import brightspark.testmod.init.TMItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID)
public class RegistrationHandler
{
    @SubscribeEvent
    public static void regItems(RegistryEvent.Register<Item> event)
    {
        //Register all items
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(TMItems.getItems());
        registry.registerAll(TMBlocks.getItemBlocks());
    }

    @SubscribeEvent
    public static void regBlocks(RegistryEvent.Register<Block> event)
    {
        //Register all blocks
        event.getRegistry().registerAll(TMBlocks.getBlocks());
    }

    //@SubscribeEvent
    public static void regRecipes(RegistryEvent.Register<IRecipe> event)
    {

    }
}
