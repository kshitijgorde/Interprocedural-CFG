// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.applet;

import com.objectbox.runner.gui.AppletFrame;
import java.io.IOException;
import java.io.FileOutputStream;
import com.objectbox.runner.beans.DownloadView;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.File;
import com.objectbox.runner.gui.JBee;
import java.awt.Image;
import java.applet.AudioClip;
import java.net.URL;
import java.util.Enumeration;
import com.objectbox.runner.util.JBLogger;
import java.applet.Applet;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.Component;
import java.applet.AppletContext;

public class OBContext implements AppletContext
{
    private Component frame;
    private static Hashtable applethash;
    private boolean useImageCache;
    private Vector soundlist;
    
    static {
        OBContext.applethash = new Hashtable();
    }
    
    public OBContext(final Applet applet, final Component frame) {
        this.frame = null;
        this.useImageCache = true;
        this.soundlist = new Vector();
        this.frame = frame;
    }
    
    public Applet getApplet(final String s) {
        JBLogger.log("OBContext getApplet");
        return OBContext.applethash.get(s);
    }
    
    public Enumeration getApplets() {
        return OBContext.applethash.elements();
    }
    
    public AudioClip getAudioClip(final URL url) {
        try {
            final AudioApp audioApp = new AudioApp(url.toString());
            this.soundlist.addElement(audioApp);
            return audioApp;
        }
        catch (Throwable t) {
            JBLogger.log(t.toString());
            return null;
        }
    }
    
    public Image getImage(final URL url) {
        JBLogger.log("AppletContext: getImage " + url);
        Image image = null;
        try {
            final String string = url.toString();
            final String string2 = string.hashCode() + "_" + string.substring(string.lastIndexOf("/") + 1, string.length());
            String s = JBee.getPreference("javabee_home");
            if (!s.endsWith(System.getProperty("file.separator"))) {
                s = String.valueOf(s) + System.getProperty("file.separator");
            }
            final File file = new File(String.valueOf(s) + "cache", string2);
            if (file.exists() && this.useImageCache) {
                JBLogger.log("Using cached image");
                final FileInputStream fileInputStream = new FileInputStream(file);
                final byte[] array = new byte[(int)file.length()];
                fileInputStream.read(array);
                fileInputStream.close();
                image = Toolkit.getDefaultToolkit().createImage(array);
            }
            else {
                JBLogger.log("Downloading image");
                final DataInputStream dataInputStream = new DataInputStream(url.openConnection().getInputStream());
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    Label_0274: {
                        try {
                            byteArrayOutputStream.write(dataInputStream.readByte());
                            DownloadView.addBytes(1L);
                            break Label_0274;
                        }
                        catch (IOException ex2) {
                            final byte[] byteArray = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                            if (this.useImageCache) {
                                final FileOutputStream fileOutputStream = new FileOutputStream(file);
                                fileOutputStream.write(byteArray);
                                fileOutputStream.close();
                            }
                            image = Toolkit.getDefaultToolkit().createImage(byteArray);
                            continue;
                        }
                    }
                    break;
                }
            }
        }
        catch (Exception ex) {
            JBLogger.log("Exception i OBContext getImage " + ex.toString());
        }
        return image;
    }
    
    public boolean getUseImageCache() {
        return this.useImageCache;
    }
    
    public void setContext(final Applet applet, final Component frame) {
        this.frame = frame;
    }
    
    public void setUseImageCache(final boolean useImageCache) {
        this.useImageCache = useImageCache;
    }
    
    public void showDocument(final URL url) {
        JBLogger.log("AppletConext: showDocument");
        JBee.setUrlToShow(url.toString());
    }
    
    public void showDocument(final URL url, final String s) {
        JBLogger.log("AppletConext: showDocument");
        JBee.setUrlToShow(url.toString());
    }
    
    public void showStatus(final String status) {
        if (this.frame instanceof AppletFrame) {
            ((AppletFrame)this.frame).setStatus(status);
        }
    }
    
    public void stopSounds() {
        final Enumeration<AudioApp> elements = this.soundlist.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().stop();
            JBLogger.log("Stopping applet sound");
        }
        this.soundlist.removeAllElements();
    }
}
