package dev.overlord.aurelia.config;

import dev.overlord.aurelia.events.MessageEvent;
import dev.overlord.aurelia.registrar.SlashCommandsRegistrar;
import dev.overlord.aurelia.slashcommands.BuyItemsSlashCommand;
import dev.overlord.aurelia.slashcommands.UserRegistrationSlashCommand;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
@Getter
@Component
public class AureliaConfig {

    private final ShardManager shardManager;

    private final Dotenv config;

    public AureliaConfig(ApplicationContext applicationContext) {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setActivity(Activity.playing("with your mom!"));
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.enableIntents(
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.DIRECT_MESSAGE_REACTIONS,
                GatewayIntent.GUILD_PRESENCES,
                GatewayIntent.GUILD_VOICE_STATES);
        shardManager = builder.build();

        //Register Listeners
        shardManager.addEventListener(new MessageEvent());
        shardManager.addEventListener(applicationContext.getBean(SlashCommandsRegistrar.class));
        shardManager.addEventListener(applicationContext.getBean(BuyItemsSlashCommand.class));
        shardManager.addEventListener(applicationContext.getBean(UserRegistrationSlashCommand.class));
    }
}
