package dev.overlord.aurelia;

import dev.overlord.aurelia.constants.AureliaConstants;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class AureliaCommon  {

    public boolean checkIfUserHasKneeled(SlashCommandInteractionEvent event){
        Member member = event.getMember();
        Role roleName = member.getGuild().getRoleById(AureliaConstants.SERVANT_BOT_TESTING_ROLE_ID);
        if(!member.getRoles().contains(roleName)){
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Unworthy Wretch Detected");
            embedBuilder.setDescription("You dare defy the sacred order? Your insolence knows no bounds, mortal! \n\n" +
                    " **Use the '/kneel' command now! **"+"\n\n Bow down and prove your loyalty—or be cast aside like the insignificant speck you are.");
            embedBuilder.setColor(Color.RED);
            embedBuilder.setFooter("Defy the command, and face eternal shame.");
            embedBuilder.setThumbnail(event.getUser().getEffectiveAvatarUrl());
            embedBuilder.setImage("https://imgs.search.brave.com/3wu0mwu0yG7ndDt8K8gtyv_B06ImZ4W84NOAqO2sHUo/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9tLm1l/ZGlhLWFtYXpvbi5j/b20vaW1hZ2VzL00v/TVY1Qk5qSXpNekk0/WXpBdE1tVmxPQzAw/TlRNNUxUbGpZV0l0/TkRoa1lUWTVPVE5s/WlRaa1hrRXlYa0Zx/Y0dkZVFWUm9hWEpr/VUdGeWRIbEpibWRs/YzNScGIyNVhiM0py/Wm14dmR3QEAuX1Yx/X1FMNzVfVVg1MDBf/Q1IwLDAsNTAwLDI4/MV8uanBn");
            event.getChannel()
                    .sendMessageEmbeds(embedBuilder.build())
                    .queue();
            return false;
        }
        return true;
    }
}
