package org.botCreators.SherpaBot.Sherpa.Utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ManageInventory {
	
	private EmbedCreator ec = null;
	private MessageReceivedEvent event = null;
	private String cmd = null;
	
	
	public ManageInventory(){
		this.ec = new EmbedCreator();
	}
	
	public ManageInventory(MessageReceivedEvent event, String cmd){
		this.event = event;
		this.cmd = cmd;
		this.ec = new EmbedCreator();
	}
	
	public void HandleEvent(){
		
	}
	
	private void createEmptyInventory() {
		if(cmd.equals("create")) { //create inventory stub
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
	}
	
	private void deletePlayerInventory() {
		if(cmd.equals("delete")) { //delete inventory stub
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
	}
}
