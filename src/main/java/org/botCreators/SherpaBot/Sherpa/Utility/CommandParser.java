package org.botCreators.SherpaBot.Sherpa.Utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandParser {

	
	public CommandParser(){

	}
	
	static public void Forward(MessageReceivedEvent event, String cmd){
		//EmbedCreator ec = new EmbedCreator();
		if(cmd.equals("ping")) {

			event.getChannel().sendMessage("pong").queue();
			
		}
		
		if(cmd.equals("help")) {
			User author = event.getAuthor();
			Message message = event.getMessage();
			MessageChannel channel = event.getChannel();
			
			author.openPrivateChannel().queue( 
					pChannel -> {
						pChannel.sendMessage(author.getName()).queue();
						channel.deleteMessageById(message.getId()).queue();
				});
			
		}
		
		if(cmd.equals("test")) {
			event.getChannel().sendMessage(event.getAuthor().getAsMention()).queue(); //@<person>
			event.getChannel().sendMessage(event.getAuthor().getName()).queue(); //accountName
			event.getChannel().sendMessage(event.getAuthor().getId()).queue(); //bunch of digits
			event.getChannel().sendMessage(event.getGuild().getName()).queue();
			event.getChannel().sendMessage(event.getGuild().getId()).queue();
			
			
			String s = event.getAuthor().getAsMention();
			System.out.println(s);
		}
		
		if(cmd.equals("inv")){
			DatabaseManager dbm = new DatabaseManager();
			
			String sql = "select * from user";
			
			try {
				PreparedStatement ps = dbm.connect().prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					System.out.println("Something happened.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbm.disconnect(); 
			}
		}
		
		/*if(cmd.equals("create")) { //create inventory stub
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
		}*/
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
