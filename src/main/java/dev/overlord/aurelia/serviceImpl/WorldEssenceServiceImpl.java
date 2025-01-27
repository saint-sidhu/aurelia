package dev.overlord.aurelia.serviceImpl;

import dev.overlord.aurelia.constants.CommandDetailsEnum;
import dev.overlord.aurelia.constants.WorldEssenceEnum;
import dev.overlord.aurelia.entity.*;
import dev.overlord.aurelia.repository.*;
import dev.overlord.aurelia.service.WorldEssenceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Transactional
public class WorldEssenceServiceImpl implements WorldEssenceService {
    private final ShopItemRepo shopItemRepo;

    private final UserDetailsRepo userDetailsRepo;

    private final UserBalanceRepo userBalanceRepo;

    private final CurrencyDetailsRepo currencyDetailsRepo;

    private final UserCooldownRepo userCooldownRepo;

    public WorldEssenceServiceImpl(ShopItemRepo shopItemRepo,
                                   UserDetailsRepo userDetailsRepo, UserBalanceRepo userBalanceRepo,
                                   CurrencyDetailsRepo currencyDetailsRepo, UserCooldownRepo userCooldownRepo) {
        this.shopItemRepo = shopItemRepo;
        this.userDetailsRepo = userDetailsRepo;
        this.userBalanceRepo = userBalanceRepo;
        this.currencyDetailsRepo = currencyDetailsRepo;
        this.userCooldownRepo = userCooldownRepo;
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
        int userId = userDetailsEntity.getUserId();
        //userDetailsEntity will never be null because the user has been registered with '/kneel'.
        UserCooldownEntity userCooldownEntity = userCooldownRepo.findByUserId(userId);
        if (userCooldownEntity == null) {
            userCooldownEntity = new UserCooldownEntity();
            userCooldownEntity.setUserDetailsEntity(userDetailsEntity);
            userCooldownEntity.setCommandId(String.valueOf(CommandDetailsEnum.BEG_COMMAND.getId()));
            userCooldownEntity.setLastCommandTime(LocalDateTime.now());
            userCooldownEntity.setLockedTillTime(LocalDateTime.now().plusHours
                    (CommandDetailsEnum.BEG_COMMAND.getCooldownInHours()));
            userCooldownRepo.saveAndFlush(userCooldownEntity);
        }
        UserBalanceEntity userBalanceEntity = userBalanceRepo.findByCurrencyId(currencyDetailsEntity.getCurrencyId());
        if (userBalanceEntity != null) {
            userBalanceEntity.setBalance(userBalanceEntity.getBalance() + coins);
            userCooldownEntity.setLastCommandTime(LocalDateTime.now());
            userCooldownEntity.setLockedTillTime(LocalDateTime.now().plusHours
                    (CommandDetailsEnum.BEG_COMMAND.getCooldownInHours()));
            userBalanceRepo.saveAndFlush(userBalanceEntity);
            userCooldownRepo.saveAndFlush(userCooldownEntity);
        } else {
            userBalanceEntity = new UserBalanceEntity();
            userBalanceEntity.setBalance(coins);
            userBalanceEntity.setUserDetailsEntity(userDetailsEntity);
            userBalanceEntity.setCurrencyDetailsEntity(currencyDetailsEntity);
            userBalanceRepo.save(userBalanceEntity);
        }

        return coins + currencyDetailsEntity.getCurrencyTier();
    }

    private int generateUniqueNumber() {
        Random random = new Random();

        // Generate a random number between 1 and 100 with a 70% chance
        if (random.nextDouble() < 0.60) {
            // 60% chance to select from 1-100
            return random.nextInt(100, 150);
        } else if (random.nextDouble() < 0.90 && random.nextDouble() > 0.5) {
            // 30% chance to select from 101-200
            return random.nextInt(200, 400);
        } else {
            return random.nextInt(400, 500);
        }
    }
}
