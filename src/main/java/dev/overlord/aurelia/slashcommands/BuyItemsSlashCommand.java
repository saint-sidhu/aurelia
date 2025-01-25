package dev.overlord.aurelia.slashcommands;

import dev.overlord.aurelia.serviceImpl.WorldEssenceServiceImpl;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
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

    public BuyItemsSlashCommand() {
        config = Dotenv.configure().load();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        String command = event.getName();
        if (command.equals("beg")) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("Ah, Back for Scraps Again?");
            embedBuilder.setColor(Color.pink);
            embedBuilder.setDescription("Didn’t think you’d survive without this, huh? Here’s your daily dose of mediocrity");
            embedBuilder.setThumbnail("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.freepik.com%2Fpremium-ai-image%2Fhand-beggar-with-some-coins-begging-money-povertyhungeranxietybankrupt-concept_314518928.htm&psig=AOvVaw1-NDsWdW7fdgjHH3lBJy14&ust=1737844537276000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCIjD5Mq1j4sDFQAAAAAdAAAAABAE");
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
