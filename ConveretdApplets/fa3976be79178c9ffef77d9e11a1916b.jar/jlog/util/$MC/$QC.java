// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.$MC;

import java.awt.image.ImageObserver;
import java.net.URL;
import java.io.FileInputStream;
import java.io.File;
import java.awt.Toolkit;
import java.awt.Image;
import java.io.InputStream;
import java.io.OutputStream;
import jlog.io.$LB;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;

public class $QC implements $NC
{
    static final boolean debug = false;
    
    public byte[] $OC(String $rc) {
        try {
            $rc = this.$RC($rc);
            InputStream resourceAsStream = this.getResourceAsStream($rc);
            if (resourceAsStream == null) {
                return null;
            }
            try {
                resourceAsStream = new BufferedInputStream(resourceAsStream);
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                $LB.copy(resourceAsStream, byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
            finally {
                resourceAsStream.close();
            }
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public Image $PC(final String s) {
        Image image = null;
        try {
            final byte[] $oc = this.$OC(s);
            if ($oc != null) {
                image = Toolkit.getDefaultToolkit().createImage($oc);
                if (image != null) {
                    new $UC(image);
                }
            }
        }
        catch (Exception ex) {}
        return image;
    }
    
    String $RC(String substring) {
        while (substring.startsWith("/")) {
            substring = substring.substring(1);
        }
        return substring;
    }
    
    public InputStream getResourceAsStream(String $rc) {
        InputStream openStream = null;
        try {
            $rc = this.$RC($rc);
            final URL systemResource = ClassLoader.getSystemResource($rc);
            if (systemResource != null) {
                openStream = systemResource.openStream();
            }
        }
        catch (Exception ex) {}
        if (openStream == null) {
            try {
                openStream = new FileInputStream($rc.replace('/', File.separatorChar));
            }
            catch (Exception ex2) {}
        }
        return openStream;
    }
    
    class $UC extends Thread implements ImageObserver
    {
        int $WC;
        
        $UC(final Image image) {
            this.$WC = 224;
            try {
                synchronized (this) {
                    if (!Toolkit.getDefaultToolkit().prepareImage(image, -1, -1, this)) {
                        this.wait();
                    }
                }
            }
            catch (Exception ex) {}
        }
        
        public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
            if ((n & this.$WC) != 0x0) {
                synchronized (this) {
                    this.notifyAll();
                }
                return false;
            }
            return true;
        }
    }
}
