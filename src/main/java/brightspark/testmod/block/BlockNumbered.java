package brightspark.testmod.block;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

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
    @Override
    public int damageDropped(IBlockState state)
    {
        return (state.getValue(NUMBER)).ordinal();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        for (EnumNumber num : EnumNumber.values())
            items.add(new ItemStack(this, 1, num.ordinal()));
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(NUMBER, EnumNumber.values()[meta]);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(NUMBER)).ordinal();
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, NUMBER);
    }
}
