package brightspark.testmod.block;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockNumbered extends TMBlock
{
    public enum EnumNumber implements IStringSerializable
    {
        n1,
        n2,
        n3,
        n4,
        n5,
        n6,
        n7,
        n8,
        n9;

        public static String[] names = new String[values().length];
        static
        {
            for(EnumNumber num : values())
                names[num.ordinal()] = num.toString();
        }

        public String getName()
        {
            return toString();
        }
    }

    public static final PropertyEnum<EnumNumber> NUMBER = PropertyEnum.create("number", EnumNumber.class);

    public BlockNumbered()
    {
        super("blocknumbered");
        setDefaultState(blockState.getBaseState().withProperty(NUMBER, EnumNumber.n1));
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return (state.getValue(NUMBER)).ordinal();
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        for (EnumNumber num : EnumNumber.values())
            list.add(new ItemStack(itemIn, 1, num.ordinal()));
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(NUMBER, EnumNumber.values()[meta]);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(NUMBER)).ordinal();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, NUMBER);
    }
}
