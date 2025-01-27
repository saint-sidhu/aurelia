package dev.overlord.aurelia.constants;

import lombok.Getter;

@Getter
public enum LevelsEnum {

    FLEDGLING(1, "Fledgling", "New and insignificant, barely stepping into your realm.", 0),
    SERVANT(2, "Servant", "A basic follower, learning to serve under your reign.", 100),
    THRALL(3, "Thrall", "A soul bound by your will, obedient to your power.", 300),
    MINION(4, "Minion", "A trusted subordinate, carrying out minor tasks for your empire.", 600),
    ACOLYTE(5, "Acolyte", "A devotee who has begun to understand your supreme authority.", 1000),
    ENFORCER(6, "Enforcer", "A loyal subject empowered to maintain order in your name.", 1500),
    WARLORD(7, "Warlord", "A fearsome warrior spreading fear and destruction under your banner.", 2100),
    OVERSEER(8, "Overseer", "A commander of lesser beings, enforcing your rule with authority.", 2800),
    HARBINGER(9, "Harbinger", "A bringer of your wrath, an ominous presence for those who dare resist.", 3600),
    DOMINION(10, "Dominion", "A being of significant power, entrusted with vast control in your realm.", 4500),
    SHADOW_SOVEREIGN(11, "Shadow Sovereign", "A near-perfect extension of your will, feared and respected by all.", 5500),
    ECLIPSE(12, "Eclipse", "A force capable of engulfing all light, symbolizing your eternal dominion.", 6600),
    ETERNAL_VANGUARD(13, "Eternal Vanguard", "An immortal defender of your throne, unshakable and resolute.", 7800),
    DREAD_MONARCH(14, "Dread Monarch", "A ruler of terror under your reign, instilling fear in the hearts of mortals.", 9100),
    VOID_ASCENDANT(15, "Void Ascendant", "One who has transcended mortality, merging with the infinite void of your power.", 10500),
    SUPREME_HERALD(16, "Supreme Herald", "The ultimate emissary of your will, second only to you in authority and feared by all.", 12000);

    private final int id;
    private final String levelName;
    private final String levelDescription;
    private final int thresholdXP;

    // Constructor
    LevelsEnum(int id, String levelName, String levelDescription, int thresholdXP) {
        this.id = id;
        this.levelName = levelName;
        this.levelDescription = levelDescription;
        this.thresholdXP = thresholdXP;
    }

    public static LevelsEnum getById(int id){
        for (LevelsEnum level : values()){
            if(level.getId() == id) {
                return level;
            }
        }
        throw new IllegalArgumentException("No such Id : "+ id +" exists");
    }
}

