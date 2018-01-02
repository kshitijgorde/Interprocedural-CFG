import java.awt.Image;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryLWMenu
{
    String \u0199;
    String \u0198;
    String \u0197;
    ryLWItem[] \u0196;
    int \u0195;
    int \u0194;
    int \u0193;
    int \u0192;
    int \u0191;
    boolean \u0190;
    Rectangle \u018f;
    boolean \u018e;
    Image \u018d;
    Image \u018c;
    String \u018b;
    
    public ryLWMenu(final String \u0199, final String \u01992, final String \u0268, final String \u018c, final int \u0195, final int \u0263) {
        this.\u018e = false;
        this.\u0199 = \u0199;
        this.\u0198 = \u01992;
        this.\u0197 = \u0268;
        this.\u018b = \u018c;
        this.\u0195 = \u0195;
        this.\u0194 = \u0263;
        this.\u0191 = \u0263 + 2;
        this.\u0196 = new ryLWItem[this.\u0195];
        this.\u0190 = (this.\u0198 != "");
    }
}
