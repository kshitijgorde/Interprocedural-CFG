import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetColors
{
    public static Color controlColor;
    public static Color controlColorFG;
    public static Color controlColorLighter;
    public static Color controlColorDarker;
    
    static {
        IRCQNetColors.controlColor = Color.lightGray;
        IRCQNetColors.controlColorFG = Color.black;
        IRCQNetColors.controlColorLighter = IRCQNetColors.controlColorFG.brighter().brighter();
        IRCQNetColors.controlColorDarker = IRCQNetColors.controlColorFG.darker();
    }
}
