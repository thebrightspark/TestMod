package brightspark.testmod.item;

import brightspark.testmod.TestMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemUseExample extends TMItem
{
    public ItemUseExample()
    {
        super("itemuseexample");
        setMaxStackSize(1);
    }

    /**
     * How long it takes to use or consume an item
     * This is set to 72000 ticks, or 1 hour (same as ItemBow)
     */
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

    /**
     * This is called when the item is right clicked
     * We use this to start using the item
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if(!playerIn.isSneaking())
        {
            TestMod.LOG.info("Item Right Clicked! Starting use...");
            playerIn.setActiveHand(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));
        }
        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(hand));
    }

    /**
     * Called each tick while using the item
     * This is where we'll actually handle activating the item
     */
    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count)
    {
        //I'm going to only make this "activate" every second
        if(count % 20 == 0)
        {
            TestMod.LOG.info("Activated!");
        }
    }

    /**
     * Called when finished using the item
     */
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        TestMod.LOG.info("Finished!");
        return stack;
    }
}
