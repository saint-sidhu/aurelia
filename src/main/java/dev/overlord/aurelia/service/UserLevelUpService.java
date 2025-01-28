package dev.overlord.aurelia.service;

import net.dv8tion.jda.api.EmbedBuilder;

public interface UserLevelUpService {

    String addXP(int userId);

    EmbedBuilder getProgressionBar(int userId);
}
