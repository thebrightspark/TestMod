package brightspark.testmod.init;

import brightspark.testmod.block.BlockMulti;
import brightspark.testmod.tileentity.TileMutli;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
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

    private static void regBlock(Block block)
    {
        BLOCKS.put(block.getRegistryName().getResourcePath().toLowerCase(), block);
        ITEM_BLOCKS.put(block.getRegistryName().getResourcePath().toLowerCase(), (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName()));
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
    }

    public static void initTileEntities()
    {
        regTE(TileMutli.class, blockMulti);
    }
}
