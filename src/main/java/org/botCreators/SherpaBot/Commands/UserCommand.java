package org.botCreators.SherpaBot.Commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.botCreators.SherpaBot.Sherpa.Utility.DatabaseManager;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class UserCommand extends Command {

	@Override
	public void onCommand(MessageReceivedEvent event, String[] args) {

		if(args[1].equals("create") || args[1].equals("create") ){
			create();
		} else if(args[1].equals("delete") || args[1].equals("d") ){
			delete();
		} else if(args[1].equals("mod") || args[1].equals("m") ){
			mod();
		}
		
		DatabaseManager dbm = new DatabaseManager();
		
		String sql = "select * from user";
		
		try {
			PreparedStatement ps = dbm.connect().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			try {
				while(rs.next()){
					System.out.println("Something happened.");
				}
				event.getChannel().sendMessage(event.getAuthor().getAsMention()).queue();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ps.close();
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.disconnect(); 
		}
		
	}
	
	private void create(){
		
	}
	
	private void delete(){
		
	}
	
	private void mod(){
		
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
