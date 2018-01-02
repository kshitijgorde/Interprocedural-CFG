// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import jlog.$BI.$M4;
import java.text.MessageFormat;
import jlog.io.$JW;
import java.awt.Image;
import java.applet.AudioClip;
import java.net.URL;
import java.util.Enumeration;
import java.applet.Applet;
import jlog.applet.$KEC;
import java.util.Dictionary;
import jlog.applet.$HEC;
import java.applet.AppletContext;

public class $GEC implements AppletContext, $HEC
{
    public Dictionary $IEC;
    private int $JEC;
    private $KEC showStatus;
    
    public $GEC(final Dictionary $iec, final $KEC showStatus) {
        this.$JEC = 0;
        this.$IEC = $iec;
        this.showStatus = showStatus;
    }
    
    public Applet getApplet(final String s) {
        this.ni("getApplet");
        return null;
    }
    
    public Enumeration getApplets() {
        this.ni("getApplets");
        return null;
    }
    
    public AudioClip getAudioClip(final URL url) {
        this.ni("getAudioClip");
        return null;
    }
    
    public Image getImage(final URL url) {
        this.ni("getImage");
        return null;
    }
    
    void ni(final String s) {
        throw new Error(String.valueOf(s) + " not implemented");
    }
    
    public void showDocument(final URL url) {
        this.showDocument(url, "");
    }
    
    public void showDocument(final URL url, final String s) {
        for (int i = this.$JEC; i < 40; ++i) {
            final String s2 = this.$IEC.get("SHOW_BROWSER_CMD" + ((i == 0) ? "" : String.valueOf(i)));
            if (s2 == null) {
                return;
            }
            try {
                this.$JEC = i;
                String s3;
                if (url.getProtocol().equals("file")) {
                    s3 = $JW.$NEC(url.getFile());
                }
                else {
                    s3 = url.toExternalForm();
                }
                if (Runtime.getRuntime().exec(MessageFormat.format(s2, s3, s)) != null) {
                    return;
                }
            }
            catch (Exception ex) {}
        }
        if (this.$JEC != 1000) {
            this.$JEC = 1000;
            $M4.print("\nHTML-viewer not found.");
        }
    }
    
    public void showStatus(final String s) {
        this.showStatus.showStatus(s);
    }
}
