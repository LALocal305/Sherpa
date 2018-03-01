package org.botCreators.SherpaBot.Commands;


import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public abstract class Command {
	
	public abstract void onCommand(MessageReceivedEvent event, String[] args, String command);
	public abstract String getDescription();
	public abstract String getName();
	public abstract String getUsage();
	
}
