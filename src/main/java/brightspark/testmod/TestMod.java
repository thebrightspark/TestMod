package brightspark.testmod;

import brightspark.testmod.capability.playerNumber.MessagePlayerNumber;
import brightspark.testmod.command.CommandPlayerNumber;
import brightspark.testmod.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

@Mod(modid = TestMod.MOD_ID, name = TestMod.MOD_NAME, version = TestMod.VERSION)
public class TestMod
{
    public static final String MOD_ID = "testmod";
    public static final String MOD_NAME = "Test Mod";
    public static final String VERSION = "1.10.2-1.0";

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MOD_ID)
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(Items.GOLDEN_APPLE);
        }
    };

    @Mod.Instance(MOD_ID)
    public static TestMod instance;

    @SidedProxy(modId = MOD_ID, clientSide = "brightspark.testmod.proxy.ClientProxy", serverSide = "brightspark.testmod.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static SimpleNetworkWrapper NETWORK;
    public static Logger LOG;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOG = event.getModLog();
        proxy.preInit();

        NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
        //Packet used to sync the PlayerNumber capability from the server to the client
        NETWORK.registerMessage(MessagePlayerNumber.Handler.class, MessagePlayerNumber.class, 0, Side.CLIENT);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        //Run stuff after mods have initialized here
        proxy.postInit();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        //Register commands
        event.registerServerCommand(new CommandPlayerNumber());
    }
}
