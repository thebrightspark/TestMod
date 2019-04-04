package brightspark.testmod.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ShapedDamageRecipe extends ShapedOreRecipe
{
	public ShapedDamageRecipe(ResourceLocation group, Block result, Object... recipe)
	{
		super(group, result, recipe);
	}

	public ShapedDamageRecipe(ResourceLocation group, Item result, Object... recipe)
	{
		super(group, result, recipe);
	}

	public ShapedDamageRecipe(ResourceLocation group, ItemStack result, Object... recipe)
	{
		super(group, result, recipe);
	}

	public ShapedDamageRecipe(ResourceLocation group, ItemStack result, CraftingHelper.ShapedPrimer primer)
	{
		super(group, result, primer);
	}
}
