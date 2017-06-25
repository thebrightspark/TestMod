package brightspark.testmod.capability.playerNumber;

import brightspark.testmod.capability.ICapability;

public interface IPlayerNumber extends ICapability
{
    int getNumber();

    void setNumber(int number);
}
