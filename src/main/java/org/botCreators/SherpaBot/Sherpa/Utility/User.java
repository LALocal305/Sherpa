package org.botCreators.SherpaBot.Sherpa.Utility;

public class User {
	private String nickname;
	private String discordName;
	private String discordDiscriminator;
	
	public User(){}
	
	public User(String nickname, String discordName, String discordDiscriminator) {
		this.nickname = nickname;
		this.discordName = discordName;
		this.discordDiscriminator = discordDiscriminator;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getDiscordName() {
		return discordName;
	}
	
	public void setDiscordName(String discordName) {
		this.discordName = discordName;
	}
	
	public String getDiscordDiscriminator() {
		return discordDiscriminator;
	}
	
	public void setDiscordDiscriminator(String discordDiscriminator) {
		this.discordDiscriminator = discordDiscriminator;
	}
}
