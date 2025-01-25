package dev.overlord.aurelia.registrar;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlashCommandsRegistrar extends ListenerAdapter {

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        //Guild level local command for when bot is already running and we add
        //it to a new guild
        //event.getGuild().updateCommands().addCommands(commandsDataList()).queue();
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        //Guild level local command for when
        // the bot is already present in guild and running as well
        event.getGuild().updateCommands().addCommands(new ArrayList<>()).queue();

    }

    @Override
    public void onReady(ReadyEvent event) {
        //Global commands
        event.getJDA().updateCommands().addCommands(commandsDataList()).queue();
    }

    public List<CommandData> commandsDataList (){
        List<CommandData> commandDataList = new ArrayList<>();
        commandDataList.add(Commands.slash("beg","Don’t expect me to care how you use them."));
        return commandDataList;
    }
}
