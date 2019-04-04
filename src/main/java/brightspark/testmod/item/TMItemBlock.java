package brightspark.testmod.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.util.Locale;

public class TMItemBlock extends ItemBlock
{
	private String[] subNames;

	public TMItemBlock(Block block, Class<? extends Enum> subTypesEnum)
	{
		super(block);
		setHasSubtypes(true);
		Enum[] subTypes = subTypesEnum.getEnumConstants();
		subNames = new String[subTypes.length];
		for(int i = 0; i < subTypes.length; i++)
			subNames[i] = subTypes[i].name().toLowerCase(Locale.ROOT);
	}

	public String[] getSubNames()
	{
		return subNames;
	}

	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}

	@Override
	public String getTranslationKey(ItemStack stack)
	{
		return super.getTranslationKey() + "." + subNames[MathHelper.clamp(stack.getMetadata(), 0, subNames.length)];
	}
}
