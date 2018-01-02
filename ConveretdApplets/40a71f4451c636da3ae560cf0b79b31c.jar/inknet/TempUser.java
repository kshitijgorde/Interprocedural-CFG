// 
// Decompiled by Procyon v0.5.30
// 

package inknet;

import java.awt.Event;
import java.applet.Applet;

public class TempUser implements User
{
    String \u00cf;
    String \u00d0;
    int \u00d1;
    int \u00d2;
    long \u00d3;
    Applet \u00d4;
    boolean \u00d5;
    
    public void endGame() {
    }
    
    public static User createTempUser(final String s) {
        return new TempUser();
    }
    
    public String getSessionID() {
        return this.\u00d0;
    }
    
    public int getLicenseMode() {
        return this.\u00d2;
    }
    
    public Applet getApplet() {
        return this.\u00d4;
    }
    
    public String runCommand(final String s) {
        if (this.\u00d4 != null && s.equalsIgnoreCase("casino")) {
            this.\u00d4.postEvent(new Event(this, 1001, "casino"));
        }
        return "";
    }
    
    public TempUser() {
        this.\u00cf = null;
        this.\u00d0 = null;
        this.\u00d1 = 500000;
        this.\u00d2 = 0;
        this.\u00d3 = 0L;
        this.\u00d4 = null;
        this.\u00d5 = false;
        this.\u00d3 = System.currentTimeMillis();
    }
    
    public void addCredits(final int n) {
        this.\u00d1 += n;
    }
    
    public void deductCredits(final int n) {
        this.\u00d1 -= n;
    }
    
    public void cashOut(final int n) {
    }
    
    public int getCredits() {
        return this.\u00d1;
    }
    
    public void init(final Applet applet, final String s, final String s2, final String s3, final String s4, final int n) {
        this.init(applet, s, s2, s3, s4, n, 0);
    }
    
    public void init(final Applet \u00f4, final String s, final String \u00f0, final String \u00ef, final String s2, final int n, final int \u00f2) {
        this.\u00d4 = \u00f4;
        this.\u00cf = \u00ef;
        this.\u00d1 = 500000;
        this.\u00d0 = \u00f0;
        this.\u00d3 = System.currentTimeMillis();
        this.\u00d2 = \u00f2;
        if (\u00f4 != null) {
            try {
                final String parameter = \u00f4.getParameter("POINTMODE");
                if (parameter != null && (parameter.equalsIgnoreCase("true") || parameter.equals("1"))) {
                    this.\u00d5 = true;
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean getPointMode() {
        return this.\u00d5;
    }
    
    public String getUserName() {
        return this.\u00cf;
    }
}
