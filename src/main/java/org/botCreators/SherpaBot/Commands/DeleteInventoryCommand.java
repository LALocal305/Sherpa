package org.botCreators.SherpaBot.Commands;

import java.io.File;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class DeleteInventoryCommand extends Command {

	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command) {
		String server = event.getGuild().getId();
		String name = event.getAuthor().getName();
		String disc = event.getAuthor().getDiscriminator();
		String baseDir = System.getProperty("user.dir");
		String path = baseDir + "/invs/" + server + "/" + name + disc;
		
		try {
			File f = new File(path + "/" + name + disc + ".json");
			
			if(f.delete()){
				event.getChannel().sendMessage("Inventory system deleted").queue();
			} else {
				event.getChannel().sendMessage("You don't have an inventory yet. Use `?create` first.").queue();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsage() {
		// TODO Auto-generated method stub
		return null;
	}

}
