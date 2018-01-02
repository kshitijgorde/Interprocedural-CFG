import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class X_PiePart
{
    String X_Name;
    String X_Message;
    String X_URL;
    String X_TargetFrame;
    int X_Value;
    Color X_Color;
    Color X_Color_Shadow;
    int Angle;
    boolean active;
    int StringWidth;
    
    public X_PiePart(final String s, final int x_Value, final Color x_Color, final Color x_Color_Shadow, final String s2, final String s3, final String s4) {
        this.active = false;
        this.X_Name = s.trim();
        this.X_Value = x_Value;
        this.X_Color = x_Color;
        this.X_Color_Shadow = x_Color_Shadow;
        this.X_Message = s2.trim();
        this.X_URL = s3.trim();
        this.X_TargetFrame = s4.trim();
    }
}
