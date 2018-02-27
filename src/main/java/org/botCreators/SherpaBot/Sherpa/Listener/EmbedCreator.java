package org.botCreators.SherpaBot.Sherpa.Listener;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EmbedCreator extends ListenerAdapter {
	
    public MessageEmbed BuildSimpleEmbed(MessageReceivedEvent e, String title, String desc){
    	EmbedBuilder embed = new EmbedBuilder();
    	
    	embed.setAuthor(e.getMember().getNickname(), null, e.getAuthor().getEffectiveAvatarUrl());
    	embed.setColor(Color.GREEN);
    	embed.setTitle(title);
    	embed.setDescription(desc);
    	embed.setThumbnail("https://i.imgur.com/7p7mtov.png");
    	//embed.setImage("https://i.imgur.com/wkJx2vQ.png");
    	//https://i.imgur.com/7p7mtov.png - backpack
    	
    	return embed.build();
    }
}
