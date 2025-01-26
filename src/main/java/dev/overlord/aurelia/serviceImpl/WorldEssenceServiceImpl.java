package dev.overlord.aurelia.serviceImpl;

import dev.overlord.aurelia.constants.WorldEssenceEnum;
import dev.overlord.aurelia.entity.CurrencyDetailsEntity;
import dev.overlord.aurelia.entity.ShopItemEntity;
import dev.overlord.aurelia.entity.UserBalanceEntity;
import dev.overlord.aurelia.entity.UserDetailsEntity;
import dev.overlord.aurelia.repository.CurrencyDetailsRepo;
import dev.overlord.aurelia.repository.ShopItemRepo;
import dev.overlord.aurelia.repository.UserBalanceRepo;
import dev.overlord.aurelia.repository.UserDetailsRepo;
import dev.overlord.aurelia.service.WorldEssenceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Transactional
public class WorldEssenceServiceImpl implements WorldEssenceService {
    private final ShopItemRepo shopItemRepo;

    private final UserDetailsRepo userDetailsRepo;

    private final UserBalanceRepo userBalanceRepo;

    private final CurrencyDetailsRepo currencyDetailsRepo;

    public WorldEssenceServiceImpl(ShopItemRepo shopItemRepo,
                                   UserDetailsRepo userDetailsRepo, UserBalanceRepo userBalanceRepo,
                                   CurrencyDetailsRepo currencyDetailsRepo) {
        this.shopItemRepo = shopItemRepo;
        this.userDetailsRepo = userDetailsRepo;
        this.userBalanceRepo = userBalanceRepo;
        this.currencyDetailsRepo = currencyDetailsRepo;
    }

    @Override
    public ShopItemEntity buyItems() {
        return shopItemRepo.findById(1).orElse(null);
    }

    @Override
    public String begsMoney(String user) {

        int coins = generateUniqueNumber();
        //Make the coins as copper coins and then update in user balance

        CurrencyDetailsEntity currencyDetailsEntity = currencyDetailsRepo.findByCurrencyTier
                (WorldEssenceEnum.COPPER_CINDERS.getTier());

        UserDetailsEntity userDetailsEntity = userDetailsRepo.findByUserName(user);
        //userDetailsEntity will never be null because the user has been registered with '/kneel'.

        UserBalanceEntity userBalanceEntity = userBalanceRepo.findByCurrencyId(currencyDetailsEntity.getCurrencyId());
        if (userBalanceEntity != null) {
            userBalanceEntity.setBalance(userBalanceEntity.getBalance() + coins);
            userBalanceRepo.saveAndFlush(userBalanceEntity);
        } else {
            userBalanceEntity = new UserBalanceEntity();
            userBalanceEntity.setBalance(coins);
            userBalanceEntity.setUserDetailsEntity(userDetailsEntity);
            userBalanceEntity.setCurrencyDetailsEntity(currencyDetailsEntity);
            userBalanceRepo.save(userBalanceEntity);
        }
        return coins + "C";
    }

    private int generateUniqueNumber() {
        Random random = new Random();

        // Generate a random number between 1 and 100 with a 70% chance
        if (random.nextDouble() < 0.85) {
            // 70% chance to select from 1-100
            return random.nextInt(100, 200);
        } else {
            // 30% chance to select from 101-200
            return random.nextInt(200, 500);

        }
    }
}
