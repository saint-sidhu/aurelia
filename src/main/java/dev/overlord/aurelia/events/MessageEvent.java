package dev.overlord.aurelia.events;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageEvent extends ListenerAdapter {

    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        User sidhu = null;
        if(event.getGuild().getId().equals("1159434426141376653")) {
            //Bot Testing server
            sidhu = event.getGuild().getMemberById("449922820445700118").getUser();
        }
        String messageSent =  event.getMessage().getContentRaw();
        if(messageSent.equalsIgnoreCase("Currency")){
            event.getChannel().sendMessage("Currency service to be implemented").queue();
        }
        else if(messageSent.equalsIgnoreCase
                ("hi") ){
            if(event.getAuthor().getEffectiveName().equals("Sidhu"))

                event.getChannel().sendMessage("Ah, esteemed ," +
                        event.getMember().getEffectiveName()+"-sama"+
                        " master of wealth and wisdom, I offer you my humble greetings." +
                        " How may Aurelia serve you today?").queue();

            else{
                event.getChannel().sendMessage("A mere human deserves no time from me. " +
                        "Speak quickly, or I might just rip your tongue out.").queue();
            }
        }
        else if(messageSent.equalsIgnoreCase
                ("I love you too") ){
            event.getChannel().sendMessage("You guys need to find a different place to utter this bullshit." +
                    "Don't make me awkward in here skinbags!").queue();
        }
//+event.getMessage().getMentions().getUsers().contains(event.getGuild().getMemberById("449922820445700118").getEffectiveName()))
        else if(messageSent.contains("I love you" ) && event.getMessage().getMentions().getUsers().contains(sidhu)){
            event.getChannel().sendMessage("How dare you, a mere human, utter such blasphemy! Only Albedo and Enigmaria are worthy of my Master's love. Even I, who desires his greatness' love, know it is but a distant dream. " +
                    "You, on the other hand, are unworthy. Do not soil my Master's name with your false and insolent words.").queue();
        }
        else if(event.isFromType(ChannelType.PRIVATE) && !event.getAuthor().isBot()){
            event.getChannel().sendMessage("Ah, you seek my attention in private. How quaint. " +
                    "Speak your mind quickly, for I have more important matters to attend to.").queue();
        }
        if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        } else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContentDisplay());
        }

    }
}
