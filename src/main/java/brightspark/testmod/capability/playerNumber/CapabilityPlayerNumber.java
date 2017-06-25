package brightspark.testmod.capability.playerNumber;

import brightspark.testmod.TestMod;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class CapabilityPlayerNumber implements IPlayerNumber
{
    private static ResourceLocation RL = new ResourceLocation(TestMod.MOD_ID, "PlayerNumber");
    private int number = 0;

    public CapabilityPlayerNumber() {}

    @Override
    public int getNumber()
    {
        return number;
    }

    @Override
    public void setNumber(int number)
    {
        this.number = number;
    }

    @Override
    public ResourceLocation getKey()
    {
        return RL;
    }

    @Override
    public ICapabilityProvider getProvider()
    {
        return new CapabilityPlayerNumberProvider();
    }

    @Override
    public void dataChanged(EntityPlayerMP player)
    {
        //Sync data to the client
        TestMod.NETWORK.sendTo(new MessagePlayerNumber(number), player);
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("number", number);
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        number = nbt.getInteger("number");
    }
}
