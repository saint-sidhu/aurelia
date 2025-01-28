package dev.overlord.aurelia.slashcommands;

import dev.overlord.aurelia.common.AureliaCommonUtils;
import dev.overlord.aurelia.constants.CommandDetailsEnum;
import dev.overlord.aurelia.constants.LevelsEnum;
import dev.overlord.aurelia.constants.WorldEssenceEnum;
import dev.overlord.aurelia.serviceImpl.UserLevelUpServiceImpl;
import dev.overlord.aurelia.serviceImpl.WorldEssenceServiceImpl;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.utils.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.Duration;

@Component
public class BegSlashCommand extends ListenerAdapter {

    @Getter
    private final Dotenv config;

    @Autowired
    private WorldEssenceServiceImpl worldEssenceService;

    @Autowired
    private AureliaCommonUtils aureliaCommon;
    @Autowired
    private UserLevelUpServiceImpl userLevelUpService;

    public BegSlashCommand() {
        config = Dotenv.configure().load();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        Pair<Boolean, Integer> kneelFlagAndUserId = aureliaCommon.checkIfUserHasKneeled(event);
        Pair<Boolean, Duration> canBegFlagAndCooldownTimer = aureliaCommon.isEligibleForNextCommand
                (CommandDetailsEnum.BEG_COMMAND.getCooldownInHours(), kneelFlagAndUserId.getRight());
        Duration cooldownDuration = canBegFlagAndCooldownTimer.getRight();
        boolean kneelFlag = kneelFlagAndUserId.getLeft();
        boolean canBegFlag = canBegFlagAndCooldownTimer.getLeft();
        String command = event.getName();
        if (command.equals("beg") && kneelFlag && canBegFlag) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Ah, Back for Scraps Again?");
            embedBuilder.setColor(Color.GREEN);
            embedBuilder.setDescription("Didn’t think you’d survive without this, huh? Here’s your daily dose of mediocrity");
            embedBuilder.setThumbnail("https://imgs.search.brave.com/fjCA8-b02aL1wavoh91tWricMlrp1Eu-wgYT5ZjoPnE/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly90NC5m/dGNkbi5uZXQvanBn/LzA5LzE2Lzc2LzIx/LzM2MF9GXzkxNjc2/MjE5M19zd0dQMzZT/OUlCaHBOSDlRNmg1/b3ZxdFlhd1hQN2ho/SC5qcGc");
            embedBuilder.setFooter("Come back tomorrow for more humiliation. I’ll be here.");
            event.getChannel()
                    .sendMessageEmbeds(embedBuilder.build())
                    .queue();
            event.deferReply().setEphemeral(true).queue();
            String coinsAndTier = worldEssenceService.begsMoney(event.getUser().getEffectiveName());
            String begAmount = coinsAndTier.substring(0, 3);
            WorldEssenceEnum essenceEnum = WorldEssenceEnum.getByTier(coinsAndTier.substring(3));
            event.getHook().sendMessage("Here are **" + begAmount + "** " + "**" + essenceEnum.name().toUpperCase()
                    + "** and " + "**" + CommandDetailsEnum.BEG_COMMAND.getXpValue() + " XP** for licking my shoes clean!\n"
                    + "Now move outta my way " + event.getMember().getAsMention() + "!").queue();
            String levelUp = userLevelUpService.addXP(kneelFlagAndUserId.getRight());
            if (!levelUp.isEmpty()) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setThumbnail(event.getMember().getEffectiveAvatarUrl());
                embed.setTitle("You have ascended, " + event.getMember().getEffectiveName() + "! ");
                embed.setDescription(event.getMember().getAsMention() + ", you are **Level " + levelUp + "** now" + ". You have risen to **" + LevelsEnum.getById(Integer.parseInt(levelUp)).getLevelName().toUpperCase() + "**, marking your unwavering loyalty and growing power." +
                        "Your strength is now undeniable, and your place in my empire is further secured. \n\nContinue to serve, for only those who prove themselves worthy shall climb higher. The path is set, and your future in my dominion is inevitable.");
                embed.setColor(Color.pink);
                embed.setFooter("Bow before the supreme force that guides your fate.");
                event.getChannel().sendMessageEmbeds(embed.build()).queue();
            }
        } else if (command.equals("beg") && !canBegFlag) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Grubby Beggar!");
            embedBuilder.setDescription("Your shameless begging has hit a cooldown. Crawl back when you're worthy of more scraps.");
            embedBuilder.setThumbnail("https://imgs.search.brave.com/fjCA8-b02aL1wavoh91tWricMlrp1Eu-wgYT5ZjoPnE/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly90NC5m/dGNkbi5uZXQvanBn/LzA5LzE2Lzc2LzIx/LzM2MF9GXzkxNjc2/MjE5M19zd0dQMzZT/OUlCaHBOSDlRNmg1/b3ZxdFlhd1hQN2ho/SC5qcGc");
            embedBuilder.setColor(Color.RED);
            embedBuilder.setFooter(canBegFlagAndCooldownTimer.getRight() != Duration.ofHours(0) ?
                    "Try begging again in : "
                            + cooldownDuration.toHoursPart() + " hours " + cooldownDuration.toMinutesPart() + " minutes and "
                            + cooldownDuration.toSecondsPart() + " seconds" : "Ah, a first-time beggar—how quaint. Don’t trip over your desperation.");
            event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
            event.deferReply().setEphemeral(true).queue();
            event.getHook().sendMessage("I wish begging were a crime in this realm...").queue();
        } else if (command.equals("beg")) {
            aureliaCommon.askUserToKneelFirst(event);
        }

    }
}


