// 
// Decompiled by Procyon v0.5.30
// 

package com.aviva.framework.applet;

import com.aviva.framework.security.CryptoException;
import com.aviva.framework.security.CryptoUtil;
import com.aviva.framework.security.SecurityUtil;
import com.aviva.framework.security.DigestTool;
import java.applet.Applet;

public class AuthenticationApplet extends Applet
{
    private String salt;
    private String sessionId;
    private int counter;
    
    public void init() {
        if (this.getParameter("salt") != null) {
            this.salt = this.getParameter("salt");
        }
        if (this.getParameter("counter") != null) {
            this.counter = Integer.parseInt(this.getParameter("counter"));
        }
        if (this.getParameter("sessionId") != null) {
            this.sessionId = this.getParameter("sessionId");
        }
    }
    
    public String getEncryptedPwd(final String rawKey, final String newPwd) {
        try {
            if (rawKey != null && !this.salt.equals("") && this.counter != 0) {
                final DigestTool dt = DigestTool.getInstance();
                final String hashKey = dt.digestSHA2(rawKey);
                final String hashNewPwd = dt.digestSHA2(newPwd);
                final String key = SecurityUtil.getDesKey(hashKey, this.salt, this.counter);
                final CryptoUtil cu = new CryptoUtil(key);
                final String encPassword = cu.encrypt(hashNewPwd);
                return encPassword;
            }
            return null;
        }
        catch (CryptoException ibse) {
            ibse.printStackTrace();
            return null;
        }
    }
    
    public String checkJavaVersion() {
        return System.getProperty("java.version");
    }
}
