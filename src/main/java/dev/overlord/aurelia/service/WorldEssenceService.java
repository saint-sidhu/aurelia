package dev.overlord.aurelia.service;


import dev.overlord.aurelia.entity.ShopItemEntity;
import net.dv8tion.jda.internal.utils.tuple.Pair;

public interface WorldEssenceService {

    ShopItemEntity buyItems();

    String begsMoney(String user);
}
