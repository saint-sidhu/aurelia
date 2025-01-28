package dev.overlord.aurelia.slashcommands;

import dev.overlord.aurelia.common.AureliaCommonUtils;
import dev.overlord.aurelia.serviceImpl.UserLevelUpServiceImpl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.utils.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProgressSlashCommand extends ListenerAdapter {

    @Autowired
    private AureliaCommonUtils aureliaCommonUtils;

    @Autowired
    UserLevelUpServiceImpl userLevelUpService;
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        Pair<Boolean,Integer> kneelFlagAndUserId = aureliaCommonUtils.checkIfUserHasKneeled(event);
        String command = event.getName();
        if (command.equals("progress") && kneelFlagAndUserId.getLeft()){
            EmbedBuilder embedBuilder = userLevelUpService.getProgressionBar(kneelFlagAndUserId.getRight());
            embedBuilder.setThumbnail(Objects.requireNonNull(event.getMember()).getEffectiveAvatarUrl());
            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
            event.deferReply().setEphemeral(true).queue();
            event.getHook().sendMessage("Tsskk, mere mortals!").queue();
        } else if(command.equals("progress")){
            aureliaCommonUtils.askUserToKneelFirst(event);
        }
    }
}
