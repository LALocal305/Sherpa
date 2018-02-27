package org.botCreators.SherpaBot.Core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.managers.Presence;

public class Sherpa 
{
    public static void main(String[] args)
    {

    	Properties log = new Properties();
    	
    	try (FileReader in = new FileReader("Properties/token.propConfs")) {
    		log.load(in);
    	} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	String token = log.getProperty("token");
    	//String token = System.getenv().get("token");
        try
        {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(token) 
                    .addEventListener(new SherpaListener())  //An instance of a class that will handle events.
                    .buildBlocking();  
            
            Presence presence = jda.getPresence();
            presence.setGame(Game.of("D&D 5e | ?help"));
        }
        catch (LoginException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (RateLimitedException e)
        {
            e.printStackTrace();
        }
    }
    
}
