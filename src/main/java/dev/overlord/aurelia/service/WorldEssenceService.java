package dev.overlord.aurelia.service;


import dev.overlord.aurelia.entity.ShopItemEntity;

public interface WorldEssenceService {

    ShopItemEntity buyItems();

    int begsMoney(String user);
}
