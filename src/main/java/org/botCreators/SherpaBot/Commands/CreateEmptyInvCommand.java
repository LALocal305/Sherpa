package org.botCreators.SherpaBot.Commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.botCreators.SherpaBot.Sherpa.Utility.EmbedCreator;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CreateEmptyInvCommand extends Command {
	
	EmbedCreator ec = new EmbedCreator();

	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command) {
		//file name is BaseDirPath/Server_Id/Author_Id/name
		String server = event.getGuild().getId();
		String name = event.getAuthor().getName();
		String disc = event.getAuthor().getDiscriminator();
		String baseDir = System.getProperty("user.dir");
		String path = baseDir + "/invs/" + server + "/" + name + disc;
		
		
		try {
			Files.createDirectories(Paths.get(path));
			
			event.getChannel().sendMessage(ec.BuildSimpleEmbed(event, "Empty Inventory Created", 
					"Now tracking inventory for " + event.getMember().getNickname())).queue();
			
			File f = new File(path + "/" + name + disc + ".json");
			f.createNewFile();
		} catch (IOException e) {
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
