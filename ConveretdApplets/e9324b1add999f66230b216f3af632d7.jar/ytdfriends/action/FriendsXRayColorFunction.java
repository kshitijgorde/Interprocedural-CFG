// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import ytdfriends.FriendsLib;
import edu.berkeley.guir.prefuse.EdgeItem;
import ytdfriends.DecoratorItem;
import edu.berkeley.guir.prefuse.AggregateItem;
import edu.berkeley.guir.prefuse.VisualItem;
import edu.berkeley.guir.prefuse.util.ColorLib;
import java.awt.Paint;
import edu.berkeley.guir.prefuse.util.ColorMap;
import java.awt.Color;
import edu.berkeley.guir.prefuse.action.assignment.ColorFunction;

public class FriendsXRayColorFunction extends ColorFunction
{
    private static final int NFRIENDS = 0;
    private static final int GENDER = 1;
    private static final int NETWORK = 2;
    public static final String[] ATTRIBUTES;
    public static final String[] COLOR_MAPS;
    private static final String[] attrs;
    private static final int[] types;
    public static final String[] STATS;
    public static final String[] GENDERS;
    private static final int ONE_YEAR = 1471228928;
    private static final int MAX_AGE = 100;
    private static final int MAX_FRIENDS = 300;
    private Color femaleColor;
    private Color maleColor;
    private Color unknownColor;
    private ColorMap genderMap;
    private ColorMap networkMap;
    private Paint[] array;
    private ColorMap nfriendsMap;
    private ColorMap hotMap;
    private ColorMap coolMap;
    private ColorMap grayMap;
    private ColorMap[] maps;
    private ColorMap curMap;
    private int curAttr;
    private Color invisible;
    private Color auraColor;
    private Color fadedAura;
    private Color nullColor;
    private Color darkEdge;
    
    static {
        ATTRIBUTES = new String[] { "Number of Friends", "Gender", "Relationship Status", "Age", "Location", "Hometown", "Member Since", "Last Login", "Last Update" };
        COLOR_MAPS = new String[] { "Hot Map", "Cool Map", "Grayscale Map" };
        attrs = new String[] { "nfriends", "gender", "network" };
        types = new int[] { 0, 1, 2 };
        STATS = new String[] { "Unkown", "Facebook", "Friendster", "Bebo", "Linkedin", "Twitter", "Yatedo", "Plaxo" };
        GENDERS = new String[] { "Female", "Male", "Unknown" };
    }
    
    public FriendsXRayColorFunction() {
        this.femaleColor = ColorLib.getColor(255, 125, 125);
        this.maleColor = ColorLib.getColor(125, 125, 255);
        this.unknownColor = Color.GRAY;
        this.genderMap = new ColorMap((Paint[])new Color[] { this.femaleColor, this.maleColor, this.unknownColor }, 0.0, 2.0);
        this.networkMap = new ColorMap(this.getNetworkColorArray(), 0.0, 7.0);
        this.array = trimColorArray(ColorMap.getCoolMap(), 0.2, true);
        this.nfriendsMap = new ColorMap(this.array, 0.0, 300.0);
        this.hotMap = new ColorMap(ColorMap.getHotMap(), -0.4, 1.4);
        this.coolMap = new ColorMap(ColorMap.getCoolMap(), -0.4, 1.4);
        this.grayMap = new ColorMap(ColorMap.getGrayscaleMap(), -0.4, 1.4);
        this.maps = new ColorMap[] { this.grayMap, this.hotMap, this.coolMap };
        this.curMap = this.coolMap;
        this.curAttr = 6;
        this.invisible = ColorLib.getColor(0, 0, 0, 0);
        this.auraColor = ColorLib.getColor(127, 137, 164, 125);
        this.fadedAura = ColorLib.getColor(177, 187, 214, 125);
        this.nullColor = Color.BLACK;
        this.darkEdge = Color.DARK_GRAY.darker();
    }
    
    public void setCurrentAttribute(final int attr) {
        if (attr < 0 || attr > FriendsXRayColorFunction.ATTRIBUTES.length) {
            throw new IllegalArgumentException();
        }
        this.curAttr = attr;
    }
    
    public void setColorMap(final int map) {
        if (map < 0 || map > FriendsXRayColorFunction.COLOR_MAPS.length) {
            throw new IllegalArgumentException();
        }
        this.curMap = this.maps[map];
    }
    
    public Paint getColor(final VisualItem item) {
        if (item instanceof AggregateItem) {
            return Color.LIGHT_GRAY;
        }
        if (item instanceof DecoratorItem) {
            return this.auraColor;
        }
        if (item instanceof EdgeItem) {
            final double v = Double.valueOf(String.valueOf(item.getAttribute("network")) + ".0");
            return this.networkMap.getColor(v);
        }
        final int hvalue = FriendsLib.getHighlightValue(item);
        if (hvalue < 0) {
            return Color.GRAY;
        }
        return Color.WHITE;
    }
    
    public Paint getFillColor(final VisualItem item) {
        if (item instanceof AggregateItem) {
            return this.invisible;
        }
        if (item instanceof DecoratorItem) {
            return this.fadedAura;
        }
        final int hvalue = FriendsLib.getHighlightValue(item);
        if (hvalue < 0) {
            return Color.BLACK;
        }
        return this.getAttributeColor(item);
    }
    
    protected Paint getAttributeColor(final VisualItem item) {
        final String val = item.getAttribute(FriendsXRayColorFunction.attrs[this.curAttr]);
        if (val == null) {
            return this.nullColor;
        }
        switch (FriendsXRayColorFunction.types[this.curAttr]) {
            case 0: {
                final int nfriends = Integer.parseInt(val);
                return this.nfriendsMap.getColor((double)nfriends);
            }
            case 1: {
                final double v = this.getGenderValue(val);
                return this.genderMap.getColor(v);
            }
            case 2: {
                final double v = this.getNetworkValue(val);
                return this.networkMap.getColor(v);
            }
            default: {
                return this.nullColor;
            }
        }
    }
    
    private double getNetworkValue(final String network) {
        for (int i = 0; i < FriendsXRayColorFunction.STATS.length; ++i) {
            if (FriendsXRayColorFunction.STATS[i].equals(network)) {
                return i;
            }
        }
        return -1.0;
    }
    
    private double getGenderValue(final String gender) {
        for (int i = 0; i < FriendsXRayColorFunction.GENDERS.length; ++i) {
            if (FriendsXRayColorFunction.GENDERS[i].equals(gender)) {
                return i;
            }
        }
        return -1.0;
    }
    
    public static int getAttributeIndex(final String string) {
        for (int i = 0; i < FriendsXRayColorFunction.attrs.length; ++i) {
            if (FriendsXRayColorFunction.attrs[i].equals(string)) {
                return i;
            }
        }
        return -1;
    }
    
    public String[] getLabels() {
        switch (FriendsXRayColorFunction.types[this.curAttr]) {
            case 1: {
                return FriendsXRayColorFunction.GENDERS;
            }
            case 2: {
                return FriendsXRayColorFunction.STATS;
            }
            default: {
                return null;
            }
        }
    }
    
    public ColorMap getColorMap() {
        switch (FriendsXRayColorFunction.types[this.curAttr]) {
            case 1: {
                return this.genderMap;
            }
            case 2: {
                return this.networkMap;
            }
            case 0: {
                return this.nfriendsMap;
            }
            default: {
                return this.curMap;
            }
        }
    }
    
    private static Paint[] trimColorArray(final Paint[] map, final double trimfrac, final boolean bothEnds) {
        final int trimlen = (int)(trimfrac * map.length);
        final int scalar = bothEnds ? 2 : 1;
        final Paint[] array = new Paint[map.length - scalar * trimlen];
        System.arraycopy(map, bothEnds ? trimlen : 0, array, 0, array.length);
        return array;
    }
    
    private Paint[] getNetworkColorArray() {
        final Paint[] p = ColorMap.getHSBMap(8, 0.5f, 0.8f);
        return p;
    }
}
