// 
// Decompiled by Procyon v0.5.30
// 

package inknet;

import java.applet.Applet;

public interface User
{
    void endGame();
    
    int getLicenseMode();
    
    Applet getApplet();
    
    String runCommand(final String p0);
    
    void addCredits(final int p0);
    
    void deductCredits(final int p0);
    
    void cashOut(final int p0);
    
    int getCredits();
    
    void init(final Applet p0, final String p1, final String p2, final String p3, final String p4, final int p5);
    
    void init(final Applet p0, final String p1, final String p2, final String p3, final String p4, final int p5, final int p6);
    
    String getUserName();
}
