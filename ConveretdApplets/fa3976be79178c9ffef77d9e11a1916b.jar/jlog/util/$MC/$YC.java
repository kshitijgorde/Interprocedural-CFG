// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.$MC;

import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;
import java.io.InputStream;
import java.io.OutputStream;
import jlog.io.$LB;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.applet.Applet;

public class $YC implements $NC
{
    static final boolean debug = true;
    Applet applet;
    Class $ZC;
    
    String $AD(String s) {
        s = s.replace('\\', '/');
        if (!s.startsWith("/")) {
            s = "/" + s;
        }
        return s;
    }
    
    public byte[] $OC(final String s) {
        byte[] byteArray = null;
        try {
            InputStream resourceAsStream = this.getResourceAsStream(s);
            if (resourceAsStream != null) {
                try {
                    resourceAsStream = new BufferedInputStream(resourceAsStream);
                    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    $LB.copy(resourceAsStream, byteArrayOutputStream);
                    byteArray = byteArrayOutputStream.toByteArray();
                }
                finally {
                    resourceAsStream.close();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return byteArray;
    }
    
    public Image $PC(String $ad) {
        Image image = null;
        try {
            $ad = this.$AD($ad);
            URL resource = null;
            try {
                resource = this.$ZC.getResource($ad);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (resource != null) {
                image = this.applet.getImage(resource);
            }
            else {
                final URL url = new URL(this.applet.getDocumentBase(), $ad.substring(1));
                url.openStream().close();
                image = this.applet.getImage(url);
            }
            if (image != null) {
                final MediaTracker mediaTracker = new MediaTracker(this.applet);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForAll();
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return image;
    }
    
    public $YC(final Applet applet) {
        this.applet = applet;
        this.$ZC = applet.getClass();
    }
    
    public InputStream getResourceAsStream(String $ad) {
        InputStream inputStream = null;
        URL resource = null;
        try {
            $ad = this.$AD($ad);
            try {
                resource = this.$ZC.getResource($ad);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (resource != null) {
                inputStream = resource.openStream();
            }
            else {
                inputStream = new URL(this.applet.getDocumentBase(), $ad.substring(1)).openStream();
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return inputStream;
    }
}
