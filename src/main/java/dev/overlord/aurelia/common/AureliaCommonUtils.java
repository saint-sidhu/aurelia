package dev.overlord.aurelia.common;

import dev.overlord.aurelia.constants.AureliaConstants;
import dev.overlord.aurelia.entity.UserCooldownEntity;
import dev.overlord.aurelia.repository.UserCooldownRepo;
import dev.overlord.aurelia.repository.UserDetailsRepo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.internal.utils.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class AureliaCommonUtils {
    @Autowired
    private UserCooldownRepo userCooldownRepo;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    public Pair<Boolean, Integer> checkIfUserHasKneeled(SlashCommandInteractionEvent event) {
        //This is a case where user hasn/t kneeled and uses "/beg" command.
        if (userDetailsRepo.findByUserName(event.getMember().getEffectiveName()) == null)
            return Pair.of(false, -1);
        int userId = userDetailsRepo.findByUserName(Objects.requireNonNull(event.getMember()).getEffectiveName()).getUserId();
        Member member = event.getMember();
        Role roleName = member.getGuild().getRoleById(AureliaConstants.SERVANT_BOT_TESTING_ROLE_ID);
        if (!member.getRoles().contains(roleName)) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Unworthy Wretch Detected");
            embedBuilder.setDescription("""
                    You dare defy the sacred order? Your insolence knows no bounds, mortal!\s

                     **Use the '/kneel' command now! **

                     Bow down and prove your loyalty—or be cast aside like the insignificant speck you are.""");
            embedBuilder.setColor(Color.RED);
            embedBuilder.setFooter("Defy the command, and face eternal shame.");
            embedBuilder.setThumbnail(event.getUser().getEffectiveAvatarUrl());
            embedBuilder.setImage("https://imgs.search.brave.com/3wu0mwu0yG7ndDt8K8gtyv_B06ImZ4W84NOAqO2sHUo/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9tLm1l/ZGlhLWFtYXpvbi5j/b20vaW1hZ2VzL00v/TVY1Qk5qSXpNekk0/WXpBdE1tVmxPQzAw/TlRNNUxUbGpZV0l0/TkRoa1lUWTVPVE5s/WlRaa1hrRXlYa0Zx/Y0dkZVFWUm9hWEpr/VUdGeWRIbEpibWRs/YzNScGIyNVhiM0py/Wm14dmR3QEAuX1Yx/X1FMNzVfVVg1MDBf/Q1IwLDAsNTAwLDI4/MV8uanBn");
            event.getChannel()
                    .sendMessageEmbeds(embedBuilder.build())
                    .queue();
            return Pair.of(false, userId);
        }
        return Pair.of(true, userId);
    }

    public Pair<Boolean,Duration> isEligibleForNextCommand(int cooldownInHours, int userId) {
        //userId is passed as -1 when it is a fresh user and he hasn't kneeled yet!
        if (userId == -1) return Pair.of(true,Duration.ofHours(0));
        LocalDateTime now = LocalDateTime.now();
        UserCooldownEntity userCooldownEntity = userCooldownRepo.findByUserId(userId);
        if (userCooldownEntity != null) {
            LocalDateTime lastCommandTime = userCooldownEntity.getLastCommandTime();
            Duration duration = Duration.between(lastCommandTime, now);
            return Pair.of(duration.toHours() >= cooldownInHours,Duration.between(now,userCooldownEntity.getLockedTillTime()));
        } else return Pair.of(true,Duration.ofHours(0));
    }

    public void askUserToKneelFirst(SlashCommandInteractionEvent event){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Delusional Worm!");
        embedBuilder.setDescription("I, Aurelia, hereby, on behalf of our supreme ruler, demand your submission. \n\n" + "**Use '/kneel' command first.**"
                + " \n\nBend your knee now to swear fealty to His infinite majesty, or crawl back into the void of irrelevance from which you came.");
        embedBuilder.setThumbnail(event.getMember().getEffectiveAvatarUrl());
        embedBuilder.setColor(Color.RED);
        embedBuilder.setFooter("Kneeling is your salvation; disobedience, your doom.");
        event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();
        event.deferReply().setEphemeral(true).queue();
        event.getHook().sendMessage("Submission is your only path to relevance.").queue();
    }
}
