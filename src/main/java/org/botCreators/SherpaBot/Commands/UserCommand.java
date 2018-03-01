package org.botCreators.SherpaBot.Commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.botCreators.SherpaBot.Sherpa.Utility.DatabaseManager;
import org.botCreators.SherpaBot.Sherpa.Utility.TableCols;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class UserCommand extends Command {

	private HashMap<String, String> argMap;
	
	@Override
	public void onCommand(MessageReceivedEvent event, String[] args, String command) {
		
		argMap = new HashMap<>();
		this.parseArgs(args);

		printArray(args);
		
		if(argMap.containsKey(command)){
			if(argMap.get(command).equals("create") || argMap.get(command).equals("c") ){
				create(event, args);
			} else if(argMap.get(command).equals("delete") || argMap.get(command).equals("d") ){
				delete(event, args);
			} else if(argMap.get(command).equals("mod") || argMap.get(command).equals("m") ){
				mod(event, args);
			} else {
				event.getChannel().sendMessage("Invalid command received for message: `" + event.getMessage().getContent() +  "`. Are you forgetting an argument?").queue();
			}
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
		System.out.print("create: ");printArray(args);
		if(argMap.containsKey("n")){
			if(userExists(event)){
				//Send Message that user already exists.
				event.getChannel().sendMessage("A character with the name `" + argMap.get("n") + "` already exists.").queue();
			} else {
				//Create a new user in the database.
				
				DatabaseManager dbm = new DatabaseManager();
				String name = event.getAuthor().getName();
				String disc = event.getAuthor().getDiscriminator();
				String nn = argMap.get("n");
				
				String sql = "INSERT INTO USER (" + TableCols.USER_NN + "," + TableCols.USER_DN + "," + TableCols.USER_DD + 
						") VALUES (?,?,?) " ;
				
				try (PreparedStatement ps = dbm.connect().prepareStatement(sql);){
					
					ps.setString(1, nn);
					ps.setString(2,  name);
					ps.setString(3, disc);
					
					ps.executeUpdate();
					
					event.getChannel().sendMessage("User created successfully.").queue();
					
				} catch (SQLException e) {
					e.printStackTrace();
					event.getChannel().sendMessage("User unable to be created.").queue();
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
		if(args.length > 1) {
			for(int i = 0; i < args.length; i++){
				String temp[] = args[i].split(" ", 2);
				
				if(temp.length > 1){
					argMap.put(temp[0], (temp[1].charAt(temp[1].length() -1) == ' ' ? temp[1].substring(0, temp[1].length() -1) : temp[1].substring(0, temp[1].length())));
				}
			}
		}
	}
	
	private void printArray(String[] arr) {
		for (int i = 0; i < arr.length; i++) 
			System.out.println(arr[i]);
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
		
		String sql = "select * from user where "+ TableCols.USER_DN + " = '" + name + "' and "+ TableCols.USER_NN + " = '" + argMap.get("n") +"'" ;
		
		try (PreparedStatement ps = dbm.connect().prepareStatement(sql);
				ResultSet rs = ps.executeQuery();){

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
