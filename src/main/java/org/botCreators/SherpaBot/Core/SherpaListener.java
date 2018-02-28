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
            
        	if (msg.startsWith("?"))
			{ 
        		//String command = msg.substring(1);
        		
        		parser.Forward(event, parseCommand(msg.substring(1)));
			}
        }
    }
    
    private String[] parseCommand(String str){
		String[] parsed;
		
		StringTokenizer st = new StringTokenizer(str, "-");
		parsed = new String[st.countTokens()];
		
		int i = 0;
		while(st.hasMoreTokens()){
			parsed[i++] = st.nextToken();
		}
    	
		return parsed;
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
