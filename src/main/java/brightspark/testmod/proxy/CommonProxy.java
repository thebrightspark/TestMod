package brightspark.testmod.proxy;

import brightspark.testmod.init.TMBlocks;
import brightspark.testmod.init.TMCapabilities;
import brightspark.testmod.init.TMItems;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;

public class CommonProxy
{
    public void preInit()
    {
        TMCapabilities.init();
    }

    public void init()
    {

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
