package brightspark.testmod;

import brightspark.testmod.init.TMBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = TestMod.MOD_ID, name = TestMod.MOD_NAME, version = TestMod.VERSION)
public class TestMod
{
    public static final String MOD_ID = "testmod";
    public static final String MOD_NAME = "Test Mod";
    public static final String VERSION = "1.10.2-1.0";

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MOD_ID)
    {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Items.GOLDEN_APPLE;
        }
    };

    @Mod.Instance(MOD_ID)
    public static TestMod instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //Initialize item, blocks, textures/models and configs here

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        //Initialize GUIs, tile entities, recipies, event handlers here
        TMBlocks.initTileEntities();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        //Run stuff after mods have initialized here

    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        //Register commands

    }
}
