package brightspark.testmod.capability;

import brightspark.testmod.capability.playerNumber.IPlayerNumber;
import brightspark.testmod.init.TMCapabilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod.EventBusSubscriber
public class CapabilityHandler
{
    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        //Attach the capability to all players without it
        if(event.getObject() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getObject();
            if(!player.hasCapability(TMCapabilities.PLAYER_NUMBER, null))
            {
                IPlayerNumber playerNumber = TMCapabilities.PLAYER_NUMBER.getDefaultInstance();
                event.addCapability(playerNumber.getKey(), playerNumber.getProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        //Send client capability details to the client on login
        if(event.player instanceof EntityPlayerMP)
        {
            IPlayerNumber playerNumber = event.player.getCapability(TMCapabilities.PLAYER_NUMBER, null);
            if(playerNumber != null)
                playerNumber.dataChanged((EntityPlayerMP) event.player);
        }
    }

    @SubscribeEvent
    public static void onClonePlayer(net.minecraftforge.event.entity.player.PlayerEvent.Clone event)
    {
        //Copy capability on player death to new player
        if(event.isWasDeath() && event.getEntityPlayer() instanceof EntityPlayerMP)
        {
            EntityPlayerMP player = (EntityPlayerMP) event.getEntityPlayer();
            IPlayerNumber oldPlayerNumber = event.getOriginal().getCapability(TMCapabilities.PLAYER_NUMBER, null);
            IPlayerNumber newPlayerNumber = player.getCapability(TMCapabilities.PLAYER_NUMBER, null);
            if(oldPlayerNumber == null || newPlayerNumber == null) return;
            newPlayerNumber.deserializeNBT(oldPlayerNumber.serializeNBT());
            newPlayerNumber.dataChanged(player);
        }
    }
}
