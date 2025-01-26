package dev.overlord.aurelia.slashcommands;

import dev.overlord.aurelia.AureliaCommon;
import dev.overlord.aurelia.serviceImpl.WorldEssenceServiceImpl;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class BuyItemsSlashCommand extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuyItemsSlashCommand.class);

    @Getter
    private final Dotenv config;

    @Autowired
    private WorldEssenceServiceImpl worldEssenceService;

    @Autowired
    private AureliaCommon aureliaCommon;

    public BuyItemsSlashCommand() {
        config = Dotenv.configure().load();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        boolean kneelFlag = aureliaCommon.checkIfUserHasKneeled(event);
        String command = event.getName();
        if (command.equals("beg") && kneelFlag) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Ah, Back for Scraps Again?");
            embedBuilder.setColor(Color.pink);
            embedBuilder.setDescription("Didn’t think you’d survive without this, huh? Here’s your daily dose of mediocrity");
            embedBuilder.setThumbnail("https://imgs.search.brave.com/fjCA8-b02aL1wavoh91tWricMlrp1Eu-wgYT5ZjoPnE/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly90NC5m/dGNkbi5uZXQvanBn/LzA5LzE2Lzc2LzIx/LzM2MF9GXzkxNjc2/MjE5M19zd0dQMzZT/OUlCaHBOSDlRNmg1/b3ZxdFlhd1hQN2ho/SC5qcGc");
            embedBuilder.setFooter("Come back tomorrow for more humiliation. I’ll be here.");
            event.getChannel()
                    .sendMessageEmbeds(embedBuilder.build())
                    .queue();
            event.deferReply().setEphemeral(true).queue();
            int begAmount = worldEssenceService.begsMoney(event.getUser().getEffectiveName());
            event.getHook().sendMessage("Here are " + begAmount + " copper cinders for licking my shoes clean!\n"
                    + "Now move outta my way , pest!").queue();
        }

    }
}


