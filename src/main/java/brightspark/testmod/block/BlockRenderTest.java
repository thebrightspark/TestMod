package brightspark.testmod.block;

import brightspark.testmod.tileentity.TileRenderTest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRenderTest extends TMBlockContainer
{
    public BlockRenderTest()
    {
        super("rendertest");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileRenderTest();
    }
}
