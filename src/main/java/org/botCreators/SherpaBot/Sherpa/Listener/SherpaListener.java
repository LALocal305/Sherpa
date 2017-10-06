package org.botCreators.SherpaBot.Sherpa.Listener;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.RestAction;

public class SherpaListener extends ListenerAdapter{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        event.getJDA();

        User author = event.getAuthor();                

        Message message = event.getMessage();           

        MessageChannel channel = event.getChannel();    


        String msg = message.getContent();

        boolean bot = author.isBot();   
        
        if(!bot)
        {
	        if (msg.equals("?ping"))
		    {
		        //channel.sendMessage(BuildTestEmbed(event)).queue();
	        	channel.sendMessage("pong!").queue();
	        	//channel.deleteMessageById(message.getId()).queueAfter(5, TimeUnit.SECONDS));
		    }
	        else if (msg.equals("?inv"))
	        {	
	        	author.openPrivateChannel().queue( 
    				pChannel -> {
    					pChannel.sendMessage(author.getName()).queue();
    					channel.deleteMessageById(message.getId()).queue();
    			});
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
    	embed.setImage("https://i.imgur.com/wkJx2vQ.png");
    	
    	return embed.build();
    }
}
