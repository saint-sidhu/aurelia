package dev.overlord.aurelia.constants;

import lombok.Getter;

@Getter
public enum XPRewardForEventsEnum {

    BEG_COMMAND("beg",150);

    private final String eventName;
    private final int xpValue;

    XPRewardForEventsEnum(String eventName , int xpValue){
        this.eventName = eventName;
        this.xpValue = xpValue;
    }
}
