// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util.$MC;

import java.io.File;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.StringTokenizer;
import java.applet.Applet;
import jlog.util.zip.$CD;
import java.io.FilenameFilter;

public class $BD extends $YC implements FilenameFilter
{
    $CD $DD;
    boolean $ED;
    
    void $FD(final Applet applet) throws Exception {
        String s = applet.getParameter("archive");
        if (s == null) {
            return;
        }
        if (s.startsWith("null")) {
            s = s.substring(4);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";, +\t");
        while (stringTokenizer.hasMoreElements()) {
            String s2 = "";
            try {
                s2 = (String)stringTokenizer.nextElement();
                this.$HD(applet, s2);
            }
            catch (Exception ex) {
                System.out.print("Warning: Can not load archive \"" + s2 + "\": " + ex.getMessage());
            }
        }
    }
    
    public synchronized void $HD(final Applet applet, final String s) throws Exception {
        InputStream openStream = new URL(applet.getCodeBase(), s).openStream();
        try {
            openStream = new BufferedInputStream(openStream);
            final $CD $dd = new $CD(openStream, this, this.$DD);
            if (this.$DD == null) {
                this.$DD = $dd;
            }
        }
        catch (Exception ex) {
            openStream.close();
            throw ex;
        }
    }
    
    public synchronized void $JD() {
        while (this.$DD != null) {
            try {
                this.$DD.flush();
            }
            catch (IOException ex) {}
            this.$DD = ($CD)this.$DD.getDefaults();
        }
        this.$DD = null;
    }
    
    public Image $PC(String substring) {
        Image image = null;
        try {
            while (substring.startsWith("/")) {
                substring = substring.substring(1);
            }
            final $CD $dd = this.$DD;
            if ($dd != null) {
                try {
                    final byte[] $ld = $dd.$LD(substring);
                    if ($ld != null) {
                        Toolkit toolkit = super.applet.getToolkit();
                        if (toolkit == null) {
                            toolkit = Toolkit.getDefaultToolkit();
                        }
                        image = toolkit.createImage($ld);
                    }
                }
                catch (Exception ex2) {}
            }
            Label_0132: {
                if (image == null) {
                    if (this.$ED) {
                        if ($dd != null) {
                            break Label_0132;
                        }
                    }
                    try {
                        final URL url = new URL(super.applet.getCodeBase(), substring);
                        url.openStream().close();
                        image = super.applet.getImage(url);
                    }
                    catch (Exception ex3) {}
                }
            }
            if (image == null && (!this.$ED || $dd == null)) {
                final URL url2 = new URL(super.applet.getDocumentBase(), substring);
                url2.openStream().close();
                image = super.applet.getImage(url2);
            }
            if (image != null) {
                final MediaTracker mediaTracker = new MediaTracker(super.applet);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForAll();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return image;
    }
    
    public $BD(final Applet applet) {
        super(applet);
        this.$DD = null;
        this.$ED = false;
        try {
            this.$FD(applet);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean accept(final File file, final String s) {
        return s != null && !s.endsWith(".class");
    }
    
    public boolean getArchiveFlag() {
        return this.$ED;
    }
    
    public InputStream getResourceAsStream(String substring) {
        InputStream inputStream = null;
        try {
            while (substring.startsWith("/")) {
                substring = substring.substring(1);
            }
            final $CD $dd = this.$DD;
            if ($dd != null) {
                try {
                    inputStream = $dd.openStream(substring);
                }
                catch (Exception ex2) {}
            }
            Label_0083: {
                if (inputStream == null) {
                    if (this.$ED) {
                        if ($dd != null) {
                            break Label_0083;
                        }
                    }
                    try {
                        inputStream = new URL(super.applet.getCodeBase(), substring).openStream();
                    }
                    catch (Exception ex3) {}
                }
            }
            if (inputStream == null && (!this.$ED || $dd == null)) {
                inputStream = new URL(super.applet.getDocumentBase(), substring).openStream();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return inputStream;
    }
    
    public void setArchiveFlag(final boolean $ed) {
        this.$ED = $ed;
    }
}
