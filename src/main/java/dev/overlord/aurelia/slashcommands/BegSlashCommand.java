package dev.overlord.aurelia.slashcommands;

import dev.overlord.aurelia.common.AureliaCommonUtils;
import dev.overlord.aurelia.constants.WorldEssenceEnum;
import dev.overlord.aurelia.serviceImpl.WorldEssenceServiceImpl;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class BegSlashCommand extends ListenerAdapter {

    @Getter
    private final Dotenv config;

    @Autowired
    private WorldEssenceServiceImpl worldEssenceService;

    @Autowired
    private AureliaCommonUtils aureliaCommon;

    public BegSlashCommand() {
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
            String begAmountAndRarity = worldEssenceService.begsMoney(event.getUser().getEffectiveName());
            String begAmount = begAmountAndRarity.substring(0, 3);
            WorldEssenceEnum essenceEnum = WorldEssenceEnum.getByTier(begAmountAndRarity.substring(3));
            event.getHook().sendMessage("Here are " + begAmount + " " + essenceEnum.getName()
                    + " for licking my shoes clean!\n" + "Now move outta my way , pest!").queue();
        }

    }
}


