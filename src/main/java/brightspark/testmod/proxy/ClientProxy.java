package brightspark.testmod.proxy;

import brightspark.testmod.init.TMBlocks;

public class ClientProxy extends CommonProxy
{
    public void preInit()
    {
        super.preInit();
        TMBlocks.initTESRs();
    }
}
