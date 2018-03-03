package org.botCreators.SherpaBot.Sherpa.Utility;

import java.util.StringTokenizer;

import org.botCreators.SherpaBot.Commands.UserCommand;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandParser {
	
	private UserCommand uc;
	private String[] parsed;
	
	public CommandParser(){
		
		uc = new UserCommand();
	}
	
	public void Forward(MessageReceivedEvent event, String args){
		
		parseCommand(args);
		
		String command = "";

		if(parsed[0].contains(" ")) {
			command = parsed[0].substring(0, parsed[0].indexOf(" ")+1).trim(); 
		}
		
		System.out.println("Command: " + command);
		
		if(command.equals("user") || command.equals("u")){
			uc.onCommand(event, parsed, command);
		}
		
		if(command.equals("ping")) {

			event.getChannel().sendMessage("pong").queue();
			
		}
		
		if(command.equals("help")) {
			User author = event.getAuthor();
			Message message = event.getMessage();
			MessageChannel channel = event.getChannel();
			
			author.openPrivateChannel().queue( 
					pChannel -> {
						pChannel.sendMessage(author.getName() + ", this is obviously not working.").queue();
						channel.deleteMessageById(message.getId()).queue();
				});
			
		}

	}
    
    private String[] parseCommand(String str){
    	
    	if(str.contains("-")){
			StringTokenizer st = new StringTokenizer(str, "-"); //use split instead
			parsed = new String[st.countTokens()];
			int i = 0;
			while(st.hasMoreTokens()){
				parsed[i++] = st.nextToken();
			}

			parsed[0] = parsed[0].trim();
			
    	} else {
    		parsed = str.split("\\s+");
    	}

		return parsed;
    }
	
	
	/*if(cmd.equals("test")) {
		event.getChannel().sendMessage(event.getAuthor().getAsMention()).queue(); //@<person>
		event.getChannel().sendMessage(event.getAuthor().getName()).queue(); //accountName
		event.getChannel().sendMessage(event.getAuthor().getId()).queue(); //bunch of digits
		event.getChannel().sendMessage(event.getGuild().getName()).queue();
		event.getChannel().sendMessage(event.getGuild().getId()).queue();
		
		
		String s = event.getAuthor().getAsMention();
		System.out.println(s);
	}*/

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
