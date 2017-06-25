package brightspark.testmod.capability.playerNumber;

import brightspark.testmod.init.TMCapabilities;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityPlayerNumberProvider implements ICapabilitySerializable<NBTTagCompound>
{
    private IPlayerNumber playerNumber;

    public CapabilityPlayerNumberProvider()
    {
        playerNumber = new CapabilityPlayerNumber();
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == TMCapabilities.PLAYER_NUMBER;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        return hasCapability(capability, facing) ? (T) playerNumber : null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return playerNumber.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        playerNumber.deserializeNBT(nbt);
    }
}
