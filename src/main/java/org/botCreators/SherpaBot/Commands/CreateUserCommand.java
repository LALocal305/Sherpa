package org.botCreators.SherpaBot.Commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CreateUserCommand extends Command {

	@Override
	public void onCommand(MessageReceivedEvent event, String[] args) {
		
		
	}

	@Override
	public String getDescription() {
		return "Command for creating a new user with Sherpa.";
	}

	@Override
	public String getName() {
		return "Create User";
	}

	@Override
	public String getUsage() {
		return "Usage: ";
	}

}
