package dev.overlord.aurelia.serviceImpl;

import dev.overlord.aurelia.constants.CommandDetailsEnum;
import dev.overlord.aurelia.constants.LevelsEnum;
import dev.overlord.aurelia.entity.UserDetailsEntity;
import dev.overlord.aurelia.entity.UserProgressionEntity;
import dev.overlord.aurelia.repository.UserDetailsRepo;
import dev.overlord.aurelia.repository.UserProgressionRepo;
import dev.overlord.aurelia.service.UserLevelUpService;
import jakarta.transaction.Transactional;
import net.dv8tion.jda.api.EmbedBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;


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
            userProgressionEntity.setXp(userProgressionEntity.getXp() + CommandDetailsEnum.BEG_COMMAND.getXpValue());
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
            userProgressionEntity.setXp(CommandDetailsEnum.BEG_COMMAND.getXpValue());
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

    @Override
    public EmbedBuilder getProgressionBar(int userId) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        UserProgressionEntity userProgressionEntity = userProgressionRepo.getUserProgressionById(userId);
        int currentXP = userProgressionEntity.getXp();
        System.out.println(currentXP);
        int currentLevelXPThreshold = LevelsEnum.getById(userProgressionEntity.getLevel()).getThresholdXP();
        int nextLevel=  userProgressionEntity.getLevel()+1;
        System.out.println(currentLevelXPThreshold);
        embedBuilder.setTitle("Nice Progress, Worm!");
        embedBuilder.setDescription("I, Aurelia, on behalf of our supreme ruler, present your insignificant efforts. Behold your laughable crawl to relevance:"
                +"\n ——————Progression Bar ———————— \n" + getColouredProgressionBar(currentXP,currentLevelXPThreshold) +"**"+nextLevel
                +"**\n\n Pitiful, isn't it? Use every ounce of your mortal existence to prove you’re more than just a speck of dust in His domain. You won’t make it, but try anyway.");
        embedBuilder.setColor(Color.GREEN);
        embedBuilder.setFooter("Keep climbing, insect. The peak isn’t for your kind.");
        return embedBuilder;
    }

    private String getColouredProgressionBar(int current, int threshold){
        int totalBlocks = 20;
        int filledBlocks = ((totalBlocks * current)/threshold);
        return "**" + ":red_circle:".repeat(Math.max(0, filledBlocks)) +
                ":white_circle:".repeat(Math.max(0, (totalBlocks - filledBlocks))) +"\n\n"
                + (filledBlocks * 100) / totalBlocks + "% Progress achieved!\n Skinbag needs "+(threshold-current)
                +"XP to reach level **";
    }
}
