package brightspark.testmod.init;

import brightspark.testmod.block.BlockAnimated;
import brightspark.testmod.block.BlockMulti;
import brightspark.testmod.block.BlockNumbered;
import brightspark.testmod.block.BlockRandom;
import brightspark.testmod.tileentity.TileAnimated;
import brightspark.testmod.tileentity.TileMutli;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.animation.AnimationTESR;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class TMBlocks
{
    //Contains all registered blocks
    public static List<Block> BLOCKS;
    public static List<ItemBlock> ITEM_BLOCKS;

    public static Block blockMulti;
    public static Block blockRandom;
    public static Block blockNumbered;
    public static Block blockAnimated;

    private static void init()
    {
        BLOCKS = new ArrayList<>();
        ITEM_BLOCKS = new ArrayList<>();

        addBlock(blockMulti = new BlockMulti());
        addBlock(blockRandom = new BlockRandom());
        addBlock(blockNumbered = new BlockNumbered(), new ItemMultiTexture(blockNumbered, blockNumbered, BlockNumbered.EnumNumber.names));
        addBlock(blockAnimated = new BlockAnimated());
    }

    private static void addBlock(Block block)
    {
        addBlock(block, (ItemBlock) new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    private static void addBlock(Block block, ItemBlock itemBlock)
    {
        BLOCKS.add(block);
        ITEM_BLOCKS.add(itemBlock);
    }

    private static void regTE(Class<? extends TileEntity> teClass, Block block)
    {
        GameRegistry.registerTileEntity(teClass, block.getRegistryName().getResourcePath());
    }

    private static <T extends TileEntity> void regTESR(Class<T> teClass, TileEntitySpecialRenderer<? super T> tesr)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(teClass, tesr);
    }

    public static void regTileEntities()
    {
        regTE(TileMutli.class, blockMulti);
        regTE(TileAnimated.class, blockAnimated);
    }

    public static void regTESRs()
    {
        regTESR(TileAnimated.class, new AnimationTESR<TileAnimated>() {});
    }

    public static ItemBlock[] getItemBlocks()
    {
        if(ITEM_BLOCKS == null) init();
        return ITEM_BLOCKS.toArray(new ItemBlock[ITEM_BLOCKS.size()]);
    }

    public static Block[] getBlocks()
    {
        if(BLOCKS == null) init();
        return BLOCKS.toArray(new Block[BLOCKS.size()]);
    }

    public static void voidLists()
    {
        BLOCKS = null;
        ITEM_BLOCKS = null;
    }
}
