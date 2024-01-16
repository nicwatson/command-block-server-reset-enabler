package net.nicwatson.bresetenabler;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandStop;
import net.minecraft.server.MinecraftServer;

// A lower-security version of the /stop command that doesn't prevent command block usage.
// Inherits from vanilla CommandStop
public class CommandUncheckedStop extends CommandStop
{	
	@Override
	public String getName()
	{
		return "breset";
	}
	
	@Override
    public int getRequiredPermissionLevel()
    {
        return 2;	// Instead of 4
    }
	
	// Checks lockout timer before passing the buck to vanilla/superclass
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args)
	{
		long curTime = server.getCurrentTimeMillis();
		long elapsed = curTime - ModBReset.instance.upSince();
		if(elapsed < ModBReset.instance.getLockout())
		{
			System.out.println(sender.getName() + " tried to reset the server but not enough time has passed! (" + (elapsed / 1000) + "s < " + (ModBReset.instance.getLockout() / 1000) + "s)");
		}
		else
		{
			try
			{
				super.execute(sender.getServer(), sender, args);
			} catch (CommandException e)
			{
				System.out.println("Exception in /breset command.");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
