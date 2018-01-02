// 
// Decompiled by Procyon v0.5.30
// 

package inknet;

import java.awt.Event;
import java.applet.Applet;

public class TempUser implements User
{
    Applet m_applet;
    boolean m_bPointMode;
    long m_lStartTime;
    int m_nCredits;
    int m_nValid;
    String m_sessionKey;
    String m_userName;
    
    public TempUser() {
        this.m_userName = null;
        this.m_sessionKey = null;
        this.m_nCredits = 500000;
        this.m_nValid = 0;
        this.m_lStartTime = 0L;
        this.m_applet = null;
        this.m_bPointMode = false;
        this.m_lStartTime = System.currentTimeMillis();
    }
    
    public void addCredits(final int nCredits) {
        this.m_nCredits += nCredits;
    }
    
    public void cashOut(final int nScore) {
    }
    
    public static User createTempUser(final String strServer) {
        return new TempUser();
    }
    
    public void deductCredits(final int nCredits) {
        this.m_nCredits -= nCredits;
    }
    
    public void endGame() {
    }
    
    public Applet getApplet() {
        return this.m_applet;
    }
    
    public int getCredits() {
        return this.m_nCredits;
    }
    
    public int getLicenseMode() {
        return this.m_nValid;
    }
    
    public boolean getPointMode() {
        return this.m_bPointMode;
    }
    
    public String getSessionID() {
        return this.m_sessionKey;
    }
    
    public String getUserName() {
        return this.m_userName;
    }
    
    public void init(final Applet applet, final String strServer, final String strSession, final String strUser, final String strGame, final int nPort, final int nLicenseMode) {
        this.m_applet = applet;
        this.m_userName = strUser;
        this.m_nCredits = 500000;
        this.m_sessionKey = strSession;
        this.m_lStartTime = System.currentTimeMillis();
        this.m_nValid = nLicenseMode;
        if (applet != null) {
            try {
                final String strPointMode = applet.getParameter("POINTMODE");
                if (strPointMode != null && (strPointMode.equalsIgnoreCase("true") || strPointMode.equals("1"))) {
                    this.m_bPointMode = true;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void init(final Applet applet, final String strServer, final String strSession, final String strUser, final String strGame, final int nPort) {
        this.init(applet, strServer, strSession, strUser, strGame, nPort, 0);
    }
    
    public String runCommand(final String str) {
        if (this.m_applet != null && str.equalsIgnoreCase("casino")) {
            this.m_applet.postEvent(new Event(this, 1001, "casino"));
            goto Label_0038;
        }
        return "";
    }
}
