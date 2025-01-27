package dev.overlord.aurelia.constants;

import lombok.Getter;

@Getter
public enum CommandDetailsEnum {

    BEG_COMMAND(1,"beg",150 ,8);

    private final int id;
    private final String eventName;
    private final int xpValue;
    private final int cooldownInHours;

    CommandDetailsEnum(int id,String eventName , int xpValue , int cooldownInHours){
        this.eventName = eventName;
        this.xpValue = xpValue;
        this.cooldownInHours = cooldownInHours;
        this.id = id;
    }
}
