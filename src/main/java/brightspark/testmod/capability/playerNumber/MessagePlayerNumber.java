package brightspark.testmod.capability.playerNumber;

import brightspark.testmod.init.TMCapabilities;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePlayerNumber implements IMessage
{
    private int number;

    public MessagePlayerNumber() {}

    public MessagePlayerNumber(int number)
    {
        this.number = number;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        number = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(number);
    }

    public static class Handler implements IMessageHandler<MessagePlayerNumber, IMessage>
    {
        @Override
        public IMessage onMessage(final MessagePlayerNumber message, MessageContext ctx)
        {
            IThreadListener mainThread = Minecraft.getMinecraft();
            mainThread.addScheduledTask(new Runnable()
            {
                @Override
                public void run()
                {
                    Minecraft mc = Minecraft.getMinecraft();
                    EntityPlayerSP player = mc.player;
                    IPlayerNumber capability = player.getCapability(TMCapabilities.PLAYER_NUMBER, null);
                    if(capability != null)
                        capability.setNumber(message.number);
                }
            });
            return null;
        }
    }
}
