package brightspark.testmod.init;

import brightspark.testmod.block.BlockMulti;
import brightspark.testmod.block.BlockNumbered;
import brightspark.testmod.block.BlockRandom;
import brightspark.testmod.block.BlockRenderTest;
import brightspark.testmod.tileentity.TESRRenderTest;
import brightspark.testmod.tileentity.TileMutli;
import brightspark.testmod.tileentity.TileRenderTest;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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
    public static Block blockRenderTest;

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

    private static <T extends TileEntity> void regTESR(Class<T> teClass, TileEntitySpecialRenderer<? super T> tesr)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(teClass, tesr);
    }

    public static void init()
    {
        //Make sure we only register once
        if(!BLOCKS.isEmpty()) return;

        regBlock(blockMulti = new BlockMulti());
        regBlock(blockRandom = new BlockRandom());
        regBlock(blockNumbered = new BlockNumbered(), new ItemMultiTexture(blockNumbered, blockNumbered, BlockNumbered.EnumNumber.names));
        regBlock(blockRenderTest = new BlockRenderTest());
    }

    public static void initTileEntities()
    {
        regTE(TileMutli.class, blockMulti);
        regTE(TileRenderTest.class, blockRenderTest);
    }

    public static void initTESRs()
    {
        regTESR(TileRenderTest.class, new TESRRenderTest());
    }
}
