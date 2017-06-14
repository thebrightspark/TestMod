package brightspark.testmod.block;

import brightspark.testmod.tileentity.TileMutli;
import brightspark.testmod.util.LogHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockMulti extends TMBlockContainer
{
    public BlockMulti()
    {
        super("blockMulti");
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileMutli();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        TileMutli te = (TileMutli) worldIn.getTileEntity(pos);
        if(te != null && te.formStructure())
        {
            //Play sound when forms
            LogHelper.info("Formed!");
            worldIn.playSound(null, pos, SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.BLOCKS, 1f, 1f);
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
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
