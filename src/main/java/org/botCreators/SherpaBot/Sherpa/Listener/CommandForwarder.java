package org.botCreators.SherpaBot.Sherpa.Listener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandForwarder {

	public CommandForwarder(){

	}
	
	static public void Forward(MessageReceivedEvent event, String cmd){
		EmbedCreator ec = new EmbedCreator();
		if(cmd.equals("ping")) {

			event.getChannel().sendMessage("pong").queue();
			
		}
		
		if(cmd.equals("one")) {
			event.getChannel().sendMessage(event.getAuthor().getAsMention()).queue(); //@<person>
			event.getChannel().sendMessage(event.getAuthor().getName()).queue(); //accountName
			event.getChannel().sendMessage(event.getAuthor().getId()).queue(); //bunch of digits
			event.getChannel().sendMessage(event.getGuild().getName()).queue();
			event.getChannel().sendMessage(event.getGuild().getId()).queue();
			
			
			String s = event.getAuthor().getAsMention();
			System.out.println(s);
		}
		
		if(cmd.equals("two")) { //create inventory stub
			//file name is BaseDirPath/Server_Id/Author_Id/name
			String server = event.getGuild().getId();
			String name = event.getAuthor().getName();
			String disc = event.getAuthor().getDiscriminator();
			String baseDir = System.getProperty("user.dir");
			String path = baseDir + "/invs/" + server + "/" + name + disc;
			
			
			try {
				Files.createDirectories(Paths.get(path));
				
				event.getChannel().sendMessage(ec.BuildSimpleEmbed(event, "Stub Created", "Inventory stub created for "
						+ event.getMember().getNickname())).queue();
				
				File f = new File(path + "/" + name + disc + ".json");
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(cmd.equals("three")) { //delete inventory stub
			String server = event.getGuild().getId();
			String name = event.getAuthor().getName();
			String disc = event.getAuthor().getDiscriminator();
			String baseDir = System.getProperty("user.dir");
			String path = baseDir + "/invs/" + server + "/" + name + disc;
			
			try {
				File f = new File(path + "/" + name + disc + ".json");
				
				if(f.delete()){
					event.getChannel().sendMessage("Inventory stub removed.").queue();
				} else {
					event.getChannel().sendMessage("You don't have an inventory yet. Use `?create` first.").queue();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 if (msg.equals("?ping"))
    {
        //channel.sendMessage(BuildTestEmbed(event)).queue();
		channel.sendMessage("pong!").queue();
		//channel.deleteMessageById(message.getId()).queueAfter(5, TimeUnit.SECONDS);
    }
    else if (msg.equals("?inv"))
    {	
    	author.openPrivateChannel().queue( 
			pChannel -> {
				pChannel.sendMessage(author.getName()).queue();
				channel.deleteMessageById(message.getId()).queue();
		});
    } 
	 */
}
