package brightspark.testmod.handler;

import brightspark.testmod.misc.InventoryItemHandler;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler
{
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        InventoryPlayer playerInv = player.inventory;
        BlockPos pos = new BlockPos(x, y, z);
        switch(ID)
        {
            case 0: //Custom chest
                IInventory inventory = getInvFromTileCap(world, pos);
                return inventory != null ? new ContainerChest(playerInv, inventory, player) : null;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        InventoryPlayer playerInv = player.inventory;
        BlockPos pos = new BlockPos(x, y, z);
        switch(ID)
        {
            case 0: //Custom chest
                IInventory inventory = getInvFromTileCap(world, pos);
                return inventory != null ? new GuiChest(playerInv, inventory) : null;
            default:
                return null;
        }
    }

    /**
     * Gets an IInventory from the TileEntity's item handler capability
     */
    private static IInventory getInvFromTileCap(World world, BlockPos pos)
    {
        TileEntity te = world.getTileEntity(pos);
        if(te == null) return null;
        ItemStackHandler handler = (ItemStackHandler) te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        if(handler == null) return null;
        int size = handler.getSlots();
        IInventory inventory = new InventoryItemHandler(te.getBlockType().getTranslationKey(), false, handler);
        for(int i = 0; i < size; i++)
            inventory.setInventorySlotContents(i, handler.getStackInSlot(i));
        return inventory;
    }
}
