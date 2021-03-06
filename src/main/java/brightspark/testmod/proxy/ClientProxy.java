package brightspark.testmod.proxy;

import brightspark.testmod.command.CommandScreenshot;
import brightspark.testmod.handler.KeyHandler;
import brightspark.testmod.init.TMBlocks;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit()
    {
        super.preInit();
        TMBlocks.regTESRs();
    }

    @Override
    public void init()
    {
        super.init();
        ClientCommandHandler.instance.registerCommand(new CommandScreenshot());
        ClientRegistry.registerKeyBinding(KeyHandler.keyTest);
    }

    @Override
    public IAnimationStateMachine loadASM(ResourceLocation location, ImmutableMap<String, ITimeValue> parameters)
    {
        return ModelLoaderRegistry.loadASM(location, parameters);
    }
}
