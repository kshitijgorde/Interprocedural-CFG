// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.applet;

import java.applet.Applet;
import netscape.javascript.JSObject;
import javax.swing.JApplet;

public abstract class AppletWithCookieServices extends JApplet implements IHoldCookies
{
    public String getCookieValue(final String s) {
        String s2 = null;
        final String s3 = (String)JSObject.getWindow((Applet)this).call("getCookie", new Object[] { s });
        if (s3 != null && s3.length() > 0) {
            s2 = s3;
        }
        return s2;
    }
    
    public void setCookieValue(final String s, final String s2) {
        JSObject.getWindow((Applet)this).call("setCookieForApplet", new Object[] { s, s2 });
    }
}
