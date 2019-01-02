package brightspark.testmod.init;

import brightspark.testmod.TestMod;
import brightspark.testmod.block.BlockCustomChest;
import brightspark.testmod.block.BlockMulti;
import brightspark.testmod.block.BlockNumbered;
import brightspark.testmod.block.BlockRandom;
import brightspark.testmod.tileentity.TileCustomChest;
import brightspark.testmod.tileentity.TileMutli;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
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
    public static List<Item> ITEM_BLOCKS;

    public static final Block multi = null;
    public static final Block random = null;
    public static final Block numbered = null;
    public static final Block chest = null;

    private static void init()
    {
        BLOCKS = new ArrayList<>();
        ITEM_BLOCKS = new ArrayList<>();

        Block temp = new BlockMulti();
        addBlock(temp);
        regTE(TileMutli.class, temp);

        addBlock(new BlockRandom());

        temp = new BlockNumbered();
        addBlock(temp, new ItemMultiTexture(temp, temp, BlockNumbered.EnumNumber.names));

        temp = new BlockCustomChest();
        addBlock(temp);
        regTE(TileCustomChest.class, temp);
    }

    private static void addBlock(Block block)
    {
        addBlock(block, new ItemBlock(block));
    }

    private static void addBlock(Block block, ItemBlock itemBlock)
    {
        BLOCKS.add(block);
        ITEM_BLOCKS.add(itemBlock.setRegistryName(block.getRegistryName()));
    }

    private static void regTE(Class<? extends TileEntity> teClass, Block block)
    {
        GameRegistry.registerTileEntity(teClass, block.getRegistryName());
    }

    private static <T extends TileEntity> void regTESR(Class<T> teClass, TileEntitySpecialRenderer<? super T> tesr)
    {
        ClientRegistry.bindTileEntitySpecialRenderer(teClass, tesr);
    }

    public static void regTESRs() {}

    public static Item[] getItemBlocks()
    {
        if(ITEM_BLOCKS == null) init();
        return ITEM_BLOCKS.toArray(new Item[0]);
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
