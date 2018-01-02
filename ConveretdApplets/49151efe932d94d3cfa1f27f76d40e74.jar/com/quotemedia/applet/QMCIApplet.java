// 
// Decompiled by Procyon v0.5.30
// 

package com.quotemedia.applet;

import java.net.URL;
import java.awt.Dimension;
import netscape.javascript.JSObject;
import java.applet.Applet;

public abstract class QMCIApplet extends Applet
{
    public void openPopup(final String url, final int width, final int height, final boolean toolbars) {
        final Dimension sSize = this.getToolkit().getScreenSize();
        final String tleft = String.valueOf((sSize.width - width) / 2);
        final String mtop = String.valueOf((sSize.height - height) / 2);
        final String tmpurl = "http://" + this.getCodeBase().getHost() + url + "&";
        String property = "width=" + width + ",height=" + height + ",left=" + tleft + ",top=" + mtop + ",resizable=1";
        if (!toolbars) {
            property += ",menubar=no,toolbar=no,location=no,scrollbars=yes,statusbar=no,status=no";
        }
        final String s = "window.open('" + tmpurl + "','','" + property + " ')";
        JSObject win = null;
        try {
            win = JSObject.getWindow((Applet)this);
            final Object obj = win.eval(s);
            if (obj == null) {
                throw new Exception("JSO failed");
            }
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            this.showDocument(tmpurl, "_blank");
        }
    }
    
    public void showDocument(final String url, final String frame) {
        try {
            this.getAppletContext().showDocument(new URL(url), frame);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public String getServer() {
        return this.getCodeBase().getHost();
    }
}
