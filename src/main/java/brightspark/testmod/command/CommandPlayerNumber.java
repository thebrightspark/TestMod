package brightspark.testmod.command;

import brightspark.testmod.capability.playerNumber.IPlayerNumber;
import brightspark.testmod.init.TMCapabilities;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandPlayerNumber extends CommandBase
{

    @Override
    public String getName()
    {
        return "playerNumber";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "Usage:" +
                "\nGet Player Number: playerNumber" +
                "\nSet Player Number: playerNumber <integer>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(sender.getEntityWorld().isRemote || !(sender instanceof EntityPlayerMP))
            return;

        EntityPlayerMP player = (EntityPlayerMP) sender;
        IPlayerNumber cap = player.getCapability(TMCapabilities.PLAYER_NUMBER, null);
        if(cap == null)
            throw new CommandException("Couldn't get capability for player!");

        if(args.length == 0)
        {
            //Get number
            player.sendMessage(new TextComponentString("Number: " + cap.getNumber()));
        }
        else if(args.length == 1)
        {
            //Set number
            try
            {
                int number = Integer.valueOf(args[0]);
                cap.setNumber(number);
                player.sendMessage(new TextComponentString("Set number to: " + number));
            }
            catch(NumberFormatException e)
            {
                throw new CommandException("Argument '" + args[0] + "' is not an integer!");
            }
        }
        else
        {
            throw new WrongUsageException(getUsage(sender));
        }
    }
}
