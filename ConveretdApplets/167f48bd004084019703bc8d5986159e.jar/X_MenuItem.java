// 
// Decompiled by Procyon v0.5.30
// 

public class X_MenuItem
{
    String X_Name;
    String X_Ust;
    String X_Message;
    String X_URL;
    String X_TargetFrame;
    boolean X_Active;
    boolean X_Visible;
    int X_Icon;
    int X_Level;
    
    public X_MenuItem(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        this.X_Active = false;
        this.X_Visible = false;
        this.X_Level = 0;
        this.X_Name = s.trim();
        this.X_Ust = s2.trim();
        this.X_Message = s3.trim();
        this.X_URL = s4.trim();
        this.X_TargetFrame = s5.trim();
        this.X_Icon = Integer.parseInt(s6);
    }
}
