package brightspark.testmod.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class InventoryItemHandler extends InventoryBasic
{
    private final ItemStackHandler handler;

    public InventoryItemHandler(String title, boolean customName, ItemStackHandler handler)
    {
        super(title, customName, handler.getSlots());
        this.handler = handler;
    }

    public InventoryItemHandler(ITextComponent title, ItemStackHandler handler)
    {
        super(title, handler.getSlots());
        this.handler = handler;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        //Any changes to the inventory are made in the handler too
        handler.setStackInSlot(index, stack);
        super.setInventorySlotContents(index, stack);
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
        //Sync the stacks just to make sure all is correct
        for(int i = 0; i < getSizeInventory(); i++)
            handler.setStackInSlot(i, getStackInSlot(i));
        super.closeInventory(player);
    }
}
