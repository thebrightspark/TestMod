package brightspark.testmod.command;

import brightspark.testmod.handler.ClientEventHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

/**
 * Created by bright_spark on 04/03/2019.
 */
public class CommandScreenshot extends CommandBase
{
	@Override
	public String getName()
	{
		return "screenshot";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		ClientEventHandler.screenshot = true;
	}
}
