// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.gui.hand;

import java.util.Arrays;
import java.util.ArrayList;
import com.neurotec.biometrics.NFPosition;
import java.util.List;

public class Scenario
{
    public static final List<NFPosition> FINGERS;
    public static final List<NFPosition> SLAP_AND_TWO_THUMBS;
    public static final List<NFPosition> SLAP_AND_SEPARATE_THUMBS;
    public static final List<NFPosition> PALMS;
    public static final Scenario PLAIN_FINGER;
    public static final Scenario ROLLED_FINGER;
    public static final Scenario ALL_PLAIN_FINGERS;
    public static final Scenario ALL_ROLLED_FINGERS;
    public static final Scenario SLAP_AND_THUMB;
    public static final Scenario SLAPS_2_THUMBS;
    public static final Scenario ALL_PALMS;
    public static final Scenario NONE;
    private String message;
    private HandSegmentSelector.SelectionMode selectionMode;
    private List<NFPosition> allowedPositions;
    private boolean isRolled;
    
    public Scenario(final String s, final HandSegmentSelector.SelectionMode selectionMode) {
        this(s, selectionMode, null, false);
    }
    
    public Scenario(final String s, final HandSegmentSelector.SelectionMode selectionMode, final boolean b) {
        this(s, selectionMode, null, b);
    }
    
    public Scenario(final String s, final HandSegmentSelector.SelectionMode selectionMode, final List<NFPosition> list) {
        this(s, selectionMode, list, false);
    }
    
    public Scenario(final String message, final HandSegmentSelector.SelectionMode selectionMode, final List<NFPosition> allowedPositions, final boolean isRolled) {
        this.allowedPositions = new ArrayList<NFPosition>();
        this.isRolled = false;
        this.message = message;
        this.selectionMode = selectionMode;
        this.allowedPositions = allowedPositions;
        this.isRolled = isRolled;
    }
    
    public static Scenario[] getAllFingerScenarios() {
        return new Scenario[] { Scenario.PLAIN_FINGER, Scenario.ROLLED_FINGER, Scenario.ALL_PLAIN_FINGERS, Scenario.ALL_ROLLED_FINGERS, Scenario.SLAP_AND_THUMB, Scenario.SLAPS_2_THUMBS };
    }
    
    public static boolean isSelectAll(final Scenario scenario) {
        return scenario.equals(Scenario.ALL_PLAIN_FINGERS) || scenario.equals(Scenario.ALL_ROLLED_FINGERS);
    }
    
    public static boolean isSlapsAndThumbs(final Scenario scenario) {
        return scenario.equals(Scenario.SLAP_AND_THUMB) || scenario.equals(Scenario.SLAPS_2_THUMBS);
    }
    
    public boolean isRolled() {
        return this.isRolled;
    }
    
    public final List<NFPosition> getAllowedPositions() {
        return this.allowedPositions;
    }
    
    public void setAllowedPositions(final List<NFPosition> allowedPositions) {
        this.allowedPositions = allowedPositions;
    }
    
    public final HandSegmentSelector.SelectionMode getSelectionMode() {
        return this.selectionMode;
    }
    
    public boolean isSelectionNeeded() {
        return !this.selectionMode.equals(HandSegmentSelector.SelectionMode.NONE) && this.allowedPositions != null;
    }
    
    @Override
    public String toString() {
        return this.message;
    }
    
    static {
        FINGERS = Arrays.asList(NFPosition.LEFT_LITTLE_FINGER, NFPosition.LEFT_RING_FINGER, NFPosition.LEFT_MIDDLE_FINGER, NFPosition.LEFT_INDEX_FINGER, NFPosition.LEFT_THUMB, NFPosition.RIGHT_THUMB, NFPosition.RIGHT_INDEX_FINGER, NFPosition.RIGHT_MIDDLE_FINGER, NFPosition.RIGHT_RING_FINGER, NFPosition.RIGHT_LITTLE_FINGER);
        SLAP_AND_TWO_THUMBS = Arrays.asList(NFPosition.PLAIN_LEFT_FOUR_FINGERS, NFPosition.PLAIN_RIGHT_FOUR_FINGERS, NFPosition.PLAIN_THUMBS);
        SLAP_AND_SEPARATE_THUMBS = Arrays.asList(NFPosition.PLAIN_LEFT_FOUR_FINGERS, NFPosition.PLAIN_RIGHT_FOUR_FINGERS, NFPosition.PLAIN_LEFT_THUMB, NFPosition.PLAIN_RIGHT_THUMB);
        PALMS = Arrays.asList(NFPosition.LEFT_FULL_PALM, NFPosition.LEFT_HYPOTHENAR, NFPosition.LEFT_INTERDIGITAL, NFPosition.LEFT_LOWER_PALM, NFPosition.LEFT_THENAR, NFPosition.LEFT_UPPER_PALM, NFPosition.LEFT_WRITERS_PALM, NFPosition.RIGHT_FULL_PALM, NFPosition.RIGHT_HYPOTHENAR, NFPosition.RIGHT_INTERDIGITAL, NFPosition.RIGHT_LOWER_PALM, NFPosition.RIGHT_THENAR, NFPosition.RIGHT_UPPER_PALM, NFPosition.RIGHT_WRITERS_PALM);
        PLAIN_FINGER = new Scenario("Single plain finger", HandSegmentSelector.SelectionMode.NONE);
        ROLLED_FINGER = new Scenario("Single rolled finger", HandSegmentSelector.SelectionMode.NONE, true);
        ALL_PLAIN_FINGERS = new Scenario("All plain fingers", HandSegmentSelector.SelectionMode.FINGER, Scenario.FINGERS);
        ALL_ROLLED_FINGERS = new Scenario("All Rolled fingers", HandSegmentSelector.SelectionMode.FINGER, Scenario.FINGERS, true);
        SLAP_AND_THUMB = new Scenario("4-4-2", HandSegmentSelector.SelectionMode.SLAPS_AND_TWO_THUMBS, Scenario.SLAP_AND_TWO_THUMBS);
        SLAPS_2_THUMBS = new Scenario("4-4-1-1", HandSegmentSelector.SelectionMode.SLAPS_AND_SEPARATE_THUMBS, Scenario.SLAP_AND_SEPARATE_THUMBS);
        ALL_PALMS = new Scenario("All palms", HandSegmentSelector.SelectionMode.PALMS, Scenario.PALMS);
        NONE = new Scenario("None", HandSegmentSelector.SelectionMode.NONE);
    }
}
