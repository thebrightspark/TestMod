package brightspark.testmod.init;

import brightspark.testmod.capability.Storage;
import brightspark.testmod.capability.playerNumber.CapabilityPlayerNumber;
import brightspark.testmod.capability.playerNumber.IPlayerNumber;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import java.util.concurrent.Callable;

public class TMCapabilities
{
    @CapabilityInject(IPlayerNumber.class)
    public static Capability<IPlayerNumber> PLAYER_NUMBER = null;

    public static void init()
    {
        CapabilityManager.INSTANCE.register(IPlayerNumber.class, new Storage<IPlayerNumber>(), new Callable<IPlayerNumber>()
        {
            @Override
            public IPlayerNumber call() throws Exception
            {
                return new CapabilityPlayerNumber();
            }
        });
    }
}
