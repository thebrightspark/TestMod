package brightspark.testmod.init;

import brightspark.testmod.TestMod;
import brightspark.testmod.block.BlockMulti;
import brightspark.testmod.block.BlockNumbered;
import brightspark.testmod.block.BlockRandom;
import brightspark.testmod.tileentity.TileMutli;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

@GameRegistry.ObjectHolder(TestMod.MOD_ID)
public class TMBlocks
{
    //Contains all registered blocks
    public static List<Block> BLOCKS;
    public static List<ItemBlock> ITEM_BLOCKS;

    public static final Block blockmulti = null;
    public static final Block blockrandom = null;
    public static final Block blocknumbered = null;

    private static void init()
    {
        BLOCKS = new ArrayList<>();
        ITEM_BLOCKS = new ArrayList<>();

        addBlock(new BlockMulti());
        addBlock(new BlockRandom());
        Block temp = new BlockNumbered();
        addBlock(temp, new ItemMultiTexture(temp, temp, BlockNumbered.EnumNumber.names));
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
        GameRegistry.registerTileEntity(teClass, block.getRegistryName());
    }

    private static <T extends TileEntity> void regTESR(Class<T> teClass, TileEntitySpecialRenderer<? super T> tesr)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(teClass, tesr);
    }

    public static void regTileEntities()
    {
        regTE(TileMutli.class, blockmulti);
    }

    public static void regTESRs() {}

    public static ItemBlock[] getItemBlocks()
    {
        if(ITEM_BLOCKS == null) init();
        return ITEM_BLOCKS.toArray(new ItemBlock[0]);
    }

    public static Block[] getBlocks()
    {
        if(BLOCKS == null) init();
        return BLOCKS.toArray(new Block[0]);
    }

    public static void voidLists()
    {
        BLOCKS = null;
        ITEM_BLOCKS = null;
    }
}
