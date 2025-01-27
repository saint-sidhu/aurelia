package dev.overlord.aurelia.serviceImpl;

import dev.overlord.aurelia.constants.LevelsEnum;
import dev.overlord.aurelia.constants.XPRewardForEventsEnum;
import dev.overlord.aurelia.entity.UserDetailsEntity;
import dev.overlord.aurelia.entity.UserProgressionEntity;
import dev.overlord.aurelia.repository.UserDetailsRepo;
import dev.overlord.aurelia.repository.UserProgressionRepo;
import dev.overlord.aurelia.service.UserLevelUpService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserLevelUpServiceImpl implements UserLevelUpService {

    @Autowired
    private UserProgressionRepo userProgressionRepo;

    @Autowired
    private UserDetailsRepo userDetailsRepo;

    @Override
    @Transactional
    public String addXP(int userId) {
        UserProgressionEntity userProgressionEntity = userProgressionRepo.getUserProgressionById(userId);
        UserDetailsEntity userDetailsEntity = userDetailsRepo.findById(userId).orElse(null);
        boolean levelUpFlag = false;
        if (userProgressionEntity != null) {
            userProgressionEntity.setXp(userProgressionEntity.getXp() + XPRewardForEventsEnum.BEG_COMMAND.getXpValue());
            userProgressionEntity.setUserDetailsEntity(userDetailsEntity);
            while (userProgressionEntity.getXp() >= LevelsEnum.values()[userProgressionEntity.getLevel() - 1].getThresholdXP()) {
                // Check current level threshold against XP
                LevelsEnum currentLevel = LevelsEnum.values()[userProgressionEntity.getLevel() - 1];
                if (userProgressionEntity.getXp() >= currentLevel.getThresholdXP()) {
                    userProgressionEntity.setLevel(userProgressionEntity.getLevel() + 1);
                    levelUpFlag = true;
                } else {
                    break;  // Break out of loop if no more level-ups are possible
                }
            }
            userProgressionRepo.saveAndFlush(userProgressionEntity);
        } else {
            userProgressionEntity = new UserProgressionEntity();
            userProgressionEntity.setUserDetailsEntity(userDetailsEntity);
            userProgressionEntity.setXp(XPRewardForEventsEnum.BEG_COMMAND.getXpValue());
            userProgressionEntity.setLevel(1);
            while (userProgressionEntity.getXp() >= LevelsEnum.values()[userProgressionEntity.getLevel() - 1].getThresholdXP()) {
                // Check current level threshold against XP
                LevelsEnum currentLevel = LevelsEnum.values()[userProgressionEntity.getLevel() - 1];
                if (userProgressionEntity.getXp() >= currentLevel.getThresholdXP()) {
                    userProgressionEntity.setLevel(userProgressionEntity.getLevel() + 1);
                    levelUpFlag = true;
                } else {
                    break;  // Break out of loop if no more level-ups are possible
                }
            }
            System.out.println(userProgressionEntity);
            userProgressionRepo.saveAndFlush(userProgressionEntity);
        }
        return levelUpFlag ? String.valueOf(userProgressionEntity.getLevel()) : "";
    }
}
