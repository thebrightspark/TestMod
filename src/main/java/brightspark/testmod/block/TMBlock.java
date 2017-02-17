package brightspark.testmod.block;

import brightspark.testmod.TestMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class TMBlock extends Block
{
    public TMBlock(String name)
    {
        super(Material.ROCK);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(TestMod.CREATIVE_TAB);
    }
}
