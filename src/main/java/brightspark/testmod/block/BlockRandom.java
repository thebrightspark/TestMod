package brightspark.testmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockRandom extends TMBlock
{
    private Random rand = new Random();

    public BlockRandom()
    {
        super("blockRandom");
    }

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        Block randBlock = Block.REGISTRY.getRandomObject(rand);
        worldIn.setBlockState(pos, randBlock.getDefaultState());
    }
}
