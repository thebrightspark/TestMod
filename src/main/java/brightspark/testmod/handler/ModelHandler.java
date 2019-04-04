package brightspark.testmod.handler;

import brightspark.testmod.TestMod;
import brightspark.testmod.init.TMBlocks;
import brightspark.testmod.init.TMItems;
import brightspark.testmod.item.TMItemBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, value = Side.CLIENT)
public class ModelHandler
{
    @SubscribeEvent
    public static void regModels(ModelRegistryEvent event)
    {
        //Register all item models
        TMItems.ITEMS.forEach(ModelHandler::regModel);
        //Register block models
        TMBlocks.BLOCKS.forEach(ModelHandler::regModel);
    }

    private static void regModel(Block block)
    {
        regModel(Item.getItemFromBlock(block));
    }

    private static void regModel(Item item)
    {
        if(item instanceof TMItemBlock)
        {
            String[] subNames = ((TMItemBlock) item).getSubNames();
            for(int i = 0; i < subNames.length; i++)
                ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(item.getRegistryName() + "/" + subNames[i], "inventory"));
            //Could use something this to get it to use just a single blockstate file:
            //ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "variant=" + meta)
        }
        else
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
