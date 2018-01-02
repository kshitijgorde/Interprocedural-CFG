// 
// Decompiled by Procyon v0.5.30
// 

public class SetPanel$FEATURES
{
    public static final SetFeature NUMBER;
    public static final SetFeature COLOR;
    public static final SetFeature SYMBOL;
    public static final SetFeature SHADING;
    public static final SetFeature[] DEFAULT_FEATURES;
    public static final String[] DEFAULT_FEATURES2;
    
    static {
        NUMBER = new SetFeature("NUMBER", "1", "2", "3");
        COLOR = new SetFeature("COLOR", "red", "purple", "green");
        SYMBOL = new SetFeature("SYMBOL", "squiggles", "diamonds", "ovals");
        SHADING = new SetFeature("SHADING", "solid", "striped", "empty");
        DEFAULT_FEATURES = new SetFeature[] { SetPanel$FEATURES.NUMBER, SetPanel$FEATURES.COLOR, SetPanel$FEATURES.SYMBOL, SetPanel$FEATURES.SHADING };
        DEFAULT_FEATURES2 = new String[] { "Numbers", "Colors", "Symbols", "Shadings" };
    }
}
