package org.botCreators.SherpaBot.Commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.botCreators.SherpaBot.Sherpa.Utility.DatabaseManager;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class UserCommand extends Command {

	private HashMap<String, String> argMap;
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args) {
		
		argMap = new HashMap<>();
		this.parseArgs(args);

		if(args[1].equals("create") || args[1].equals("c") ){
			create(event, args);
		} else if(args[1].equals("delete") || args[1].equals("d") ){
			delete(event, args);
		} else if(args[1].equals("mod") || args[1].equals("m") ){
			mod(event, args);
		} else {
			event.getChannel().sendMessage("Invalid command received for message: `" + event.getMessage().getContent() +  "`").queue();
		}
		
	}
	
	/**
	 * Method to create a new user in the database. Sends a success or failure
	 * message back to the channel that the command originated from. On success
	 * a record is created in the database.
	 * 
	 * @param event - The event received for the current transaction
	 * @param args - The String array that contains the command and arguments
	 */
	private void create(MessageReceivedEvent event, String[] args){
		if(argMap.containsKey("name")){
			if(userExists(event)){
				//Send Message that user already exists.
				event.getChannel().sendMessage("A character with the name `" + argMap.get("name") + "` already exists.").queue();
			} else {
				//Create a new user in the database.
				
				DatabaseManager dbm = new DatabaseManager();
				String name = event.getAuthor().getName();
				String disc = event.getAuthor().getDiscriminator();
				String nickname = argMap.get("name");
				
				String sql = "INSERT INTO USER (name, discordName, discordDisc) VALUES () " ;
				
				try {
					PreparedStatement ps = dbm.connect().prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					
					try {
						int rowcount = 0;
						if (rs.last()){
							rowcount = rs.getRow();
							rs.beforeFirst();
						}
						
						if (rowcount == 0) {
							foundUser = false;
						} else {
							foundUser = true;
						}
						event.getChannel().sendMessage(event.getAuthor().getAsMention()).queue();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						rs.close();
						ps.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					dbm.disconnect(); 
				}
				
			}
		} else {
			event.getChannel().sendMessage("A name was not provided. Try adding `-name Your Name` after the command.").queue();
		}
	}
	
	private void delete(MessageReceivedEvent event, String[] args){
		
	}
	
	private void mod(MessageReceivedEvent event, String[] args){
		
	}
	
	/**
	 * Method for parsing the arguments passed in with the command. The 
	 * arguments are then put into a HashMap with the key being the parameter
	 * name and the value being the text that applies to the parameter.
	 * 
	 * @param args - The String array that contains the command and arguments
	 */
	private void parseArgs(String[] args){
		for(int i = 0; i < args.length; i++){
			String temp[] = args[i].split(" ", 2);
			
			argMap.put(temp[0], (temp[1].charAt(temp[1].length() -1) == ' ' ? temp[1].substring(0, temp[1].length() -1) : temp[1].substring(0, temp[1].length())));
		}
	}
	
	/**
	 * Method for checking the database for a duplicate nickname for the current user.
	 * 
	 * @param event - The event received for the current transaction
	 * @return - False if the user does not exist; True if the user exists.
	 */
	private boolean userExists(MessageReceivedEvent event){
		boolean foundUser = false;
		
		DatabaseManager dbm = new DatabaseManager();
		String name = event.getAuthor().getName();
		
		String sql = "select * from user where name =" + name + " and nickname = '" + argMap.get("name") +"'" ;
		
		try {
			PreparedStatement ps = dbm.connect().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			try {
				int rowcount = 0;
				if (rs.last()){
					rowcount = rs.getRow();
					rs.beforeFirst();
				}
				
				if (rowcount == 0) {
					foundUser = false;
				} else {
					foundUser = true;
				}
				event.getChannel().sendMessage(event.getAuthor().getAsMention()).queue();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.disconnect(); 
		}
		
		return foundUser;
	}
	
	private String findNickname(String[] args){
		String name = null;
		
		return name;
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
