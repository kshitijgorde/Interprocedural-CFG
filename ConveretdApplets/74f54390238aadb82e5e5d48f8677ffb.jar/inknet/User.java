// 
// Decompiled by Procyon v0.5.30
// 

package inknet;

import java.applet.Applet;

public interface User
{
    void addCredits(final int p0);
    
    void cashOut(final int p0);
    
    void deductCredits(final int p0);
    
    void endGame();
    
    Applet getApplet();
    
    int getCredits();
    
    int getLicenseMode();
    
    String getUserName();
    
    void init(final Applet p0, final String p1, final String p2, final String p3, final String p4, final int p5, final int p6);
    
    void init(final Applet p0, final String p1, final String p2, final String p3, final String p4, final int p5);
    
    String runCommand(final String p0);
}
