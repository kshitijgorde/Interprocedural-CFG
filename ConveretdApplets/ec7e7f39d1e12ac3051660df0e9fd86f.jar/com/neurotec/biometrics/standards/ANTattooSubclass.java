// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANTattooSubclass
{
    MISC_HUMAN(ANTattooClass.HUMAN.getValue() * 256 + 0), 
    MALE_FACE(ANTattooClass.HUMAN.getValue() * 256 + 1), 
    FEMALE_FACE(ANTattooClass.HUMAN.getValue() * 256 + 2), 
    ABSTRACT_FACE(ANTattooClass.HUMAN.getValue() * 256 + 3), 
    MALE_BODY(ANTattooClass.HUMAN.getValue() * 256 + 4), 
    FEMALE_BODY(ANTattooClass.HUMAN.getValue() * 256 + 5), 
    ABSTRACT_BODY(ANTattooClass.HUMAN.getValue() * 256 + 6), 
    ROLE(ANTattooClass.HUMAN.getValue() * 256 + 7), 
    SPORT_FIGURE(ANTattooClass.HUMAN.getValue() * 256 + 8), 
    MALE_BODY_PART(ANTattooClass.HUMAN.getValue() * 256 + 9), 
    FEMALE_BODY_PART(ANTattooClass.HUMAN.getValue() * 256 + 10), 
    ABSTRACT_BODY_PART(ANTattooClass.HUMAN.getValue() * 256 + 11), 
    SKULL(ANTattooClass.HUMAN.getValue() * 256 + 12), 
    MISC_ANIMAL(ANTattooClass.ANIMAL.getValue() * 256 + 0), 
    CAT(ANTattooClass.ANIMAL.getValue() * 256 + 1), 
    DOG(ANTattooClass.ANIMAL.getValue() * 256 + 2), 
    DOMESTIC(ANTattooClass.ANIMAL.getValue() * 256 + 3), 
    VICIOUS(ANTattooClass.ANIMAL.getValue() * 256 + 4), 
    HORSE(ANTattooClass.ANIMAL.getValue() * 256 + 5), 
    WILD(ANTattooClass.ANIMAL.getValue() * 256 + 6), 
    SNAKE(ANTattooClass.ANIMAL.getValue() * 256 + 7), 
    DRAGON(ANTattooClass.ANIMAL.getValue() * 256 + 8), 
    BIRD(ANTattooClass.ANIMAL.getValue() * 256 + 9), 
    INSECT(ANTattooClass.ANIMAL.getValue() * 256 + 10), 
    ABSTRACT_ANIMAL(ANTattooClass.ANIMAL.getValue() * 256 + 11), 
    ANIMAL_PART(ANTattooClass.ANIMAL.getValue() * 256 + 12), 
    MISC_PLANT(ANTattooClass.PLANT.getValue() * 256 + 0), 
    NARCOTIC(ANTattooClass.PLANT.getValue() * 256 + 1), 
    RED_FLOWER(ANTattooClass.PLANT.getValue() * 256 + 2), 
    BLUE_FLOWER(ANTattooClass.PLANT.getValue() * 256 + 3), 
    YELLOW_FLOWER(ANTattooClass.PLANT.getValue() * 256 + 4), 
    DRAWING(ANTattooClass.PLANT.getValue() * 256 + 5), 
    ROSE(ANTattooClass.PLANT.getValue() * 256 + 6), 
    TULIP(ANTattooClass.PLANT.getValue() * 256 + 7), 
    LILY(ANTattooClass.PLANT.getValue() * 256 + 8), 
    MISC_FLAG(ANTattooClass.FLAG.getValue() * 256 + 0), 
    USA(ANTattooClass.FLAG.getValue() * 256 + 1), 
    STATE(ANTattooClass.FLAG.getValue() * 256 + 2), 
    NAZI(ANTattooClass.FLAG.getValue() * 256 + 3), 
    CONFEDERATE(ANTattooClass.FLAG.getValue() * 256 + 4), 
    BRITISH(ANTattooClass.FLAG.getValue() * 256 + 5), 
    MISC_OBJECTObject(ANTattooClass.OBJECT.getValue() * 256 + 0), 
    FIRE(ANTattooClass.OBJECT.getValue() * 256 + 1), 
    WEAPON(ANTattooClass.OBJECT.getValue() * 256 + 2), 
    AIRPLANE(ANTattooClass.OBJECT.getValue() * 256 + 3), 
    VESSEL(ANTattooClass.OBJECT.getValue() * 256 + 4), 
    TRAIN(ANTattooClass.OBJECT.getValue() * 256 + 5), 
    VEHICLE(ANTattooClass.OBJECT.getValue() * 256 + 6), 
    MYTHICAL(ANTattooClass.OBJECT.getValue() * 256 + 7), 
    SPORTING(ANTattooClass.OBJECT.getValue() * 256 + 8), 
    NATURE(ANTattooClass.OBJECT.getValue() * 256 + 9), 
    MISC_ABSTRACT(ANTattooClass.ABSTRACT.getValue() * 256 + 0), 
    FIGURE(ANTattooClass.ABSTRACT.getValue() * 256 + 1), 
    SLEEVE(ANTattooClass.ABSTRACT.getValue() * 256 + 2), 
    BRACELET(ANTattooClass.ABSTRACT.getValue() * 256 + 3), 
    ANKLET(ANTattooClass.ABSTRACT.getValue() * 256 + 4), 
    NECKLACE(ANTattooClass.ABSTRACT.getValue() * 256 + 5), 
    SHIRT(ANTattooClass.ABSTRACT.getValue() * 256 + 6), 
    BODY_BAND(ANTattooClass.ABSTRACT.getValue() * 256 + 7), 
    HEAD_BAND(ANTattooClass.ABSTRACT.getValue() * 256 + 8), 
    MISC_SYMBOL(ANTattooClass.SYMBOL.getValue() * 256 + 0), 
    NATIONAL(ANTattooClass.SYMBOL.getValue() * 256 + 1), 
    POLITICAL(ANTattooClass.SYMBOL.getValue() * 256 + 2), 
    MILITARY(ANTattooClass.SYMBOL.getValue() * 256 + 3), 
    FRATERNAL(ANTattooClass.SYMBOL.getValue() * 256 + 4), 
    PROFESSIONAL(ANTattooClass.SYMBOL.getValue() * 256 + 5), 
    GANG(ANTattooClass.SYMBOL.getValue() * 256 + 6), 
    MISC(ANTattooClass.OTHER.getValue() * 256 + 0), 
    WORDING(ANTattooClass.OTHER.getValue() * 256 + 1), 
    FREEFORM(ANTattooClass.OTHER.getValue() * 256 + 2);
    
    private int value;
    private static final Map<Integer, ANTattooSubclass> lookup;
    
    private ANTattooSubclass(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANTattooSubclass get(final int value) {
        return ANTattooSubclass.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANTattooSubclass>();
        for (final ANTattooSubclass s : EnumSet.allOf(ANTattooSubclass.class)) {
            ANTattooSubclass.lookup.put(s.getValue(), s);
        }
    }
}
