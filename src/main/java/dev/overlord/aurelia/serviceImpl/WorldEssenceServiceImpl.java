package dev.overlord.aurelia.serviceImpl;

import dev.overlord.aurelia.entity.CurrencyDetailsEntity;
import dev.overlord.aurelia.entity.ShopItemEntity;
import dev.overlord.aurelia.entity.UserDataEntity;
import dev.overlord.aurelia.repository.*;
import dev.overlord.aurelia.service.WorldEssenceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Transactional
public class WorldEssenceServiceImpl implements WorldEssenceService {
    private final ShopItemRepo shopItemRepo;

    private final UserDataRepo userDataRepo;

    private final UserDetailsRepo userDetailsRepo;

    private final UserBalanceRepo userBalanceRepo;

    private final CurrencyTierRepo currencyTierRepo;

    private final CurrencyDetailsRepo currencyDetailsRepo;

    public WorldEssenceServiceImpl(ShopItemRepo shopItemRepo, UserDataRepo userDataRepo,
                                   UserDetailsRepo userDetailsRepo, UserBalanceRepo userBalanceRepo,
                                   CurrencyTierRepo currencyTierRepo, CurrencyDetailsRepo currencyDetailsRepo) {
        this.userDataRepo = userDataRepo;
        this.shopItemRepo = shopItemRepo;
        this.userDetailsRepo = userDetailsRepo;
        this.userBalanceRepo = userBalanceRepo;
        this.currencyDetailsRepo = currencyDetailsRepo;
        this.currencyTierRepo = currencyTierRepo;
    }

    @Override
    public ShopItemEntity buyItems() {
        return shopItemRepo.findById(1).orElse(null);
    }

    @Override
    public int begsMoney(String user) {

        int coins = generateUniqueNumber();
        //Make the coins as copper coins and then update in user balance

        CurrencyDetailsEntity copperCoinsEntity = currencyDetailsRepo.findByCurrencyTier("C");
        int copperId = copperCoinsEntity.getCurrencyId();

        UserDataEntity userDataEntity = userDataRepo.findByUserName(user);
        if (userDataEntity != null) {
            userDataEntity.setBalance(userDataEntity.getBalance() + coins);
            userDataRepo.saveAndFlush(userDataEntity);
        } else {
            UserDataEntity newUser = new UserDataEntity();
            newUser.setUserName(user);
            newUser.setBalance(coins);
            userDataRepo.saveAndFlush(newUser);
        }

        return coins;

    }

    private int generateUniqueNumber() {
        Random random = new Random();

        // Generate a random number between 1 and 100 with a 70% chance
        if (random.nextDouble() < 0.7) {
            // 70% chance to select from 1-100
            return random.nextInt(100) + 1; // Range [1, 100]
        } else {
            // 30% chance to select from 101-200
            return random.nextInt(100) + 101; // Range [101, 200]

        }
    }
}
