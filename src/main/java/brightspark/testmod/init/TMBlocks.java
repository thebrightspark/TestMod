package brightspark.testmod.init;

import brightspark.testmod.block.BlockMulti;
import brightspark.testmod.block.BlockNumbered;
import brightspark.testmod.block.BlockRandom;
import brightspark.testmod.tileentity.TileMutli;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

public class TMBlocks
{
    //Contains all registered blocks
    public static Map<String, Block> BLOCKS = new HashMap<String, Block>();
    public static Map<String, ItemBlock> ITEM_BLOCKS = new HashMap<String, ItemBlock>();

    public static Block blockMulti;
    public static Block blockRandom;
    public static Block blockNumbered;

    private static void regBlock(Block block)
    {
        regBlock(block, new ItemBlock(block));
    }

    private static void regBlock(Block block, ItemBlock itemBlock)
    {
        BLOCKS.put(block.getRegistryName().getResourcePath().toLowerCase(), block);
        ITEM_BLOCKS.put(block.getRegistryName().getResourcePath().toLowerCase(), (ItemBlock) itemBlock.setRegistryName(block.getRegistryName()));
    }

    private static void regTE(Class<? extends TileEntity> teClass, Block block)
    {
        GameRegistry.registerTileEntity(teClass, block.getRegistryName().getResourcePath());
    }

    public static void init()
    {
        //Make sure we only register once
        if(!BLOCKS.isEmpty()) return;

        regBlock(blockMulti = new BlockMulti());
        regBlock(blockRandom = new BlockRandom());
        regBlock(blockNumbered = new BlockNumbered(), new ItemMultiTexture(blockNumbered, blockNumbered, BlockNumbered.EnumNumber.names));
    }

    public static void initTileEntities()
    {
        regTE(TileMutli.class, blockMulti);
    }
}
