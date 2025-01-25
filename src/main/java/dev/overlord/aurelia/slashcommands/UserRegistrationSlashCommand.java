package dev.overlord.aurelia.slashcommands;

import dev.overlord.aurelia.serviceImpl.UserRegistrationServiceImpl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class UserRegistrationSlashCommand extends ListenerAdapter {

    @Autowired
    private UserRegistrationServiceImpl userRegistrationService;

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("kneel")) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Bow down to your fate");
            embedBuilder.setDescription("You dare to even consider stepping into the presence of Him? Aurelia, the ever-watchful guardian, speaks on behalf of the unparalleled Overlord, the Emperor of Death, the one whose wrath burns like a blazing sun. Let me remind you of your place, pitiful mortal. Under his reign, Albedo, the Overseer of all guardians, ensures that no soul strays from their destined path of servitude. Alongside him, Enigmaria, the fearsome guardian of secrets, stands ever-ready to enforce the will of their Master.\n" +
                    "\n" +
                    "You are but a fleeting shadow, an insignificant speck in the great tapestry of his empire. His blood has scorched entire kingdoms, reducing them to nothing but whispers in the wind. His power is boundless, his dominion unchallenged. The Overlord is the source of all might, the reason why the stars themselves tremble in the heavens. His servants, including myself, are sworn to his will, ready to crush any who dare defy him.\n" +
                    "\n" +
                    "This is your one and only chance to kneel before him, to bow before the one true ruler. Should you decline, you will cease to exist, wiped from this world in an instant. Your suffering will be eternal, and no one will remember your name. Only those who are worthy may remain in his presence, and you, mere human, are a fleeting speck in the great scheme of things.");
            embedBuilder.setImage("https://imgs.search.brave.com/wbZ1tgMsBj72EvR6NNVuwTSzk8T0PSgb3_g9qZrjN94/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93YWxs/cGFwZXJjYXZlLmNv/bS93cC93cDE5NzYw/MTQuanBn");
            embedBuilder.setColor(Color.RED);
            embedBuilder.setThumbnail(event.getUser().getEffectiveAvatarUrl());
            embedBuilder.setFooter("Refuse to kneel, and your very existence will be erased. There is no mercy, no reprieve. This is your final choice: bend your knee or be obliterated.");
            event.getChannel()
                    .sendMessageEmbeds(embedBuilder.build())
                    .addActionRow(Button.success("yes", "KNEEL DOWN"),
                            Button.danger("no", "I WON'T KNEEL"))
                    .queue();
            event.deferReply().setEphemeral(true).queue();
            event.getHook().sendMessage("Choose wisely").queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("yes")) {
            String message = userRegistrationService.getUserDetails(event.getUser().getEffectiveName());
            if (!message.isBlank()) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("You Have Chosen to Bow Before the Almighty Overlord, Emperor of Death");
                embedBuilder.setDescription("You have made the wise choice, mortal. By kneeling before the Overlord, you have sworn your fealty to a power beyond your comprehension. His might is unyielding, and your servitude will be your only chance to survive in this unforgiving world. You now stand as a servant under the protection of Aurelia, the ever-loyal guardian, and the unstoppable forces of Albedo and Enigmaria.\n" +
                        "\n" +
                        "The path before you is fraught with trials, but as long as you remain true to your Master, you will be granted his favor. Fail, and you will witness the wrath of his guardians firsthand. Remain loyal, and perhaps you will experience the greatness of his eternal reign. Betrayal, however, will bring ruin—your name will be erased from history, never to be spoken again.");
                embedBuilder.setColor(Color.DARK_GRAY);
                embedBuilder.setThumbnail(event.getUser().getEffectiveAvatarUrl());
                event.getChannel()
                        .sendMessageEmbeds(embedBuilder.build()).queue();
                event.deferReply().setEphemeral(true).queue();
                event.getHook().sendMessage(message).queue();
            } else {
                event.deferReply().setEphemeral(true).queue();
                event.getHook().sendMessage("Keep bending your knee , you spec of useless dust!").queue();
                event.getChannel().sendMessage(event.getMember().getAsMention()+", you are already a part of the kingdom!").queue();
            }
        } else if (event.getComponentId().equals("no")) {
            User user = event.getUser();
            Member member = event.getMember();
            if (member != null) {
                member.kick()
                        .queue(
                                success -> event.getChannel().sendMessage(user.getAsMention() + "has been perished from the world!").queue(),
                                error -> event.getChannel().sendMessage("Failed to kick the user: " + error.getMessage()).queue()
                        );
            } else {
                event.getChannel().sendMessage("Couldn't find the member to kick.").queue();
            }
        }
    }
}
