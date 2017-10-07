package org.botCreators.SherpaBot.Sherpa.Listener;

import java.util.ArrayList;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandForwarder {

	public CommandForwarder(){
	}
	
	static public void Forward(MessageReceivedEvent event, String cmd){
		
			if(cmd.equals("ping"))
				event.getChannel().sendMessage("pong").queue();
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
