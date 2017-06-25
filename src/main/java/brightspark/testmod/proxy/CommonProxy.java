package brightspark.testmod.proxy;

import brightspark.testmod.init.TMBlocks;
import brightspark.testmod.init.TMCapabilities;

public class CommonProxy
{
    public void preInit()
    {
        TMBlocks.initTileEntities();

        TMCapabilities.init();
    }

    public void init()
    {

    }

    public void postInit()
    {

    }
}
