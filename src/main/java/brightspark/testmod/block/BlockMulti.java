package brightspark.testmod.block;

import brightspark.testmod.TestMod;
import brightspark.testmod.tileentity.TileMutli;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockMulti extends TMBlockContainer
{
    public BlockMulti()
    {
        super("blockmulti");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileMutli();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        TileMutli te = (TileMutli) worldIn.getTileEntity(pos);
        if(te != null && te.formStructure())
        {
            //Play sound when forms
            TestMod.LOG.info("Formed!");
            worldIn.playSound(null, pos, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS, 1f, 1f);
        }
        return true;
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        TileMutli te = (TileMutli) worldIn.getTileEntity(pos);
        if(te != null && te.isFormed())
        {
            //Spawn some particles
            for(int i = 0; i < rand.nextInt(20) + 10; i++)
                worldIn.spawnParticle(EnumParticleTypes.FLAME,
                        pos.getX() - 1 + (rand.nextDouble() * 3),
                        pos.getY() + 3.125,
                        pos.getZ() - 1 + (rand.nextDouble() * 3),
                        0, 0, 0);
        }
    }
}
