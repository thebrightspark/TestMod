package brightspark.testmod.block;

import brightspark.testmod.TestMod;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class TMBlockContainer extends BlockContainer
{
    protected TMBlockContainer(String name)
    {
        super(Material.ROCK);
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(TestMod.CREATIVE_TAB);
    }
}
