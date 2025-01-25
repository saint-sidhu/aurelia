package dev.overlord.aurelia.constants;

import lombok.Getter;

@Getter
public enum WorldEssenceEnum {

    // Enum constants with name, description, acquisition, tier, and rarity
    COPPER_CINDERS("Copper Cinders of Mortality",
            "Common and abundant, remnants of mortal struggles.",
            "Earned through daily tasks, casual interactions, and simple bot quests.",
            "C", Rarity.COMMON),

    BRONZE_TEARS("Bronze Tears of the Fallen",
            "Formed from the sorrow of banished angels, crystallized in forgotten heavens.",
            "Gained through event participation, trading Copper Cinders, or achieving milestones.",
            "U", Rarity.UNCOMMON),

    SILVER_SIGILS("Silver Moonlit Sigils",
            "Forged under the moon’s watchful gaze, carrying blessings of celestial guardians.",
            "Awarded for rare challenges, high-level activities, or collaboration.",
            "R", Rarity.RARE),

    GOLD_CROWNS("Gold Hellfire Crowns",
            "Born from infernal forges, infused with the will of the damned.",
            "Granted for excelling in prestigious events or accomplishing rare milestones.",
            "VR", Rarity.VERY_RARE),

    PLATINUM_EMBERS("Platinum Divine Embers",
            "Radiant remnants of celestial beings, glowing with purity.",
            "Earned through monumental achievements, server contributions, or rare quests.",
            "L", Rarity.LEGENDARY),

    MYTHIC_RELICS("Mythic Ethereal Relics",
            "Shards of divine and infernal artifacts, holding unimaginable power.",
            "Obtained through legendary accomplishments or server-defining moments.",
            "M", Rarity.MYTHICAL),

    ECLIPTIC_HEARTSTONE("Ecliptic Heartstone",
            "Forged in the collision of twin suns, radiating pure destruction.",
            "Found during cosmic events or apocalyptic battles.",
            "S", Rarity.COSMIC),

    CATASTROPHIC_VOIDGEM("Cataclysmic Voidgem",
            "Fragment of pure oblivion, erasing existence with precision.",
            "Unearthed from dying dimensions or gifted by Void Lords.",
            "SS", Rarity.CATASTROPHIC),

    PRIMORDIAL_STORMCORE("Primordial Stormcore",
            "Raw elemental chaos from the first storm of creation.",
            "Discovered in elemental plane collisions or ancient ruins of creation.",
            "SSS", Rarity.ELEMENTAL);

    // Member variables for name, description, acquisition, tier, and rarity
    private final String name;
    private final String description;
    private final String acquisition;
    private final String tier;
    private final Rarity rarity;

    // Enum constructor
    WorldEssenceEnum(String name, String description, String acquisition, String tier, Rarity rarity) {
        this.name = name;
        this.description = description;
        this.acquisition = acquisition;
        this.tier = tier;
        this.rarity = rarity;
    }


    // Static method to get Enum by tier
    public static WorldEssenceEnum getByTier(String tier) {
        for (WorldEssenceEnum essence : values()) {
            if (essence.getTier().equals(tier)) {
                return essence;
            }
        }
        throw new IllegalArgumentException("No World Essence found for tier: " + tier);
    }

    // Static method to get Enum by rarity
    public static WorldEssenceEnum getByRarity(Rarity rarity) {
        for (WorldEssenceEnum essence : values()) {
            if (essence.getRarity() == rarity) {
                return essence;
            }
        }
        throw new IllegalArgumentException("No World Essence found for rarity: " + rarity);
    }

    // Enum for Rarity levels
    public enum Rarity {
        COMMON,
        UNCOMMON,
        RARE,
        VERY_RARE,
        LEGENDARY,
        MYTHICAL,
        COSMIC,
        CATASTROPHIC,
        ELEMENTAL
    }
}

