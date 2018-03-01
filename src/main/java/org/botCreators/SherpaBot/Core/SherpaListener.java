package org.botCreators.SherpaBot.Core;

import java.util.StringTokenizer;

import org.botCreators.SherpaBot.Sherpa.Utility.CommandParser;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class SherpaListener extends ListenerAdapter{
	
	CommandParser parser = new CommandParser();
	
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(!event.getAuthor().isBot())
        {
            String msg = event.getMessage().getContent();
            
        	if (msg.startsWith("?") && msg.length() > 1)
			{ 
        		//String command = msg.substring(1);
        		
        		parser.Forward(event, msg.substring(1));
			}
        }
    }

    
   /*public MessageEmbed BuildTestEmbed(MessageReceivedEvent e){
    	EmbedBuilder embed = new EmbedBuilder();
    	
    	embed.setAuthor(e.getAuthor().getName(), null, e.getAuthor().getEffectiveAvatarUrl());
    	embed.setColor(Color.GREEN);
    	embed.setTitle("ITEM NAME");
    	embed.setDescription("Blah blah blah description of item.");
    	embed.addField("Weight", "1lb", true);
    	embed.setThumbnail("https://i.imgur.com/wkJx2vQ.png");
    	//embed.setImage("https://i.imgur.com/wkJx2vQ.png");
    	//https://i.imgur.com/7p7mtov.png - backpack
    	
    	return embed.build();
    }
    */

}
