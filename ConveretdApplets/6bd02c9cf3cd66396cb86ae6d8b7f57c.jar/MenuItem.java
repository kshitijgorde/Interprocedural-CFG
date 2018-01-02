// 
// Decompiled by Procyon v0.5.30
// 

public class MenuItem
{
    String X_Message;
    String X_StatusMessage;
    String X_URL;
    String X_TargetFrame;
    boolean mActive;
    int mTransitionLevel;
    
    public MenuItem(final String s, final String s2, final String s3, final String s4) {
        this.mActive = false;
        this.mTransitionLevel = 0;
        this.X_Message = s.trim();
        this.X_StatusMessage = s2.trim();
        this.X_URL = s3.trim();
        this.X_TargetFrame = s4.trim();
    }
}
