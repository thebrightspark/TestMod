package brightspark.testmod.proxy;

import brightspark.testmod.TestMod;
import brightspark.testmod.handler.GuiHandler;
import brightspark.testmod.init.TMBlocks;
import brightspark.testmod.init.TMCapabilities;
import brightspark.testmod.init.TMItems;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy
{
    public void preInit()
    {
        TMCapabilities.init();
    }

    public void init()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(TestMod.instance, new GuiHandler());
    }

    public void postInit()
    {
        TMItems.voidLists();
        TMBlocks.voidLists();
    }

    public IAnimationStateMachine loadASM(ResourceLocation location, ImmutableMap<String, ITimeValue> parameters)
    {
        return null;
    }
}
