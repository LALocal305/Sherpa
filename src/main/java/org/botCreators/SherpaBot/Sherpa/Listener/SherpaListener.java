package org.botCreators.SherpaBot.Sherpa.Listener;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class SherpaListener extends ListenerAdapter{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(!event.getAuthor().isBot())
        {
            String msg = event.getMessage().getContent();
            
        	if (msg.startsWith("?"))
			{ 
        		String command = msg.substring(1);
        		
        		CommandForwarder.Forward(event, command);
			}
        }
    }
    
    public MessageEmbed BuildTestEmbed(MessageReceivedEvent e){
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
    

}
