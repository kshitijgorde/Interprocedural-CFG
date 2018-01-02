// 
// Decompiled by Procyon v0.5.30
// 

package gamelib;

import java.io.InputStream;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.applet.Applet;
import java.awt.Image;
import java.applet.AudioClip;

public class AVEntry
{
    public static final int IMAGE = 0;
    public static final int AUDIO = 1;
    int type;
    String name;
    private Object data;
    private static AVLoader avm;
    private volatile boolean loaded;
    
    public AVEntry(final String name, final int type) {
        this.name = name;
        this.type = type;
        synchronized (AVEntry.avm) {
            AVEntry.avm.entries.addElement(this);
            AVEntry.avm.notify();
        }
        // monitorexit(AVEntry.avm)
    }
    
    public final AudioClip getClip() {
        this.sync();
        return (AudioClip)this.data;
    }
    
    public final Image getImage() {
        this.sync();
        return (Image)this.data;
    }
    
    public static final void init(final Applet applet) {
        AVEntry.avm = new AVLoader(applet);
    }
    
    void loadAudio() {
        try {
            this.data = AVEntry.avm.applet.getAudioClip(AVEntry.avm.applet.getCodeBase(), this.name);
        }
        catch (Exception ex) {}
        if (this.data == null) {
            try {
                final Applet applet = AVEntry.avm.applet;
                this.data = Applet.newAudioClip(AVEntry.avm.applet.getClass().getResource(this.name));
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            catch (Exception ex2) {}
        }
        if (this.data != null) {
            ((AudioClip)this.data).play();
            ((AudioClip)this.data).stop();
        }
    }
    
    public final void loadData() {
        System.out.println("loading " + this.name);
        if (this.type == 1) {
            this.loadAudio();
        }
        if (this.type == 0) {
            this.loadImage();
        }
        this.loaded = true;
        System.out.println("loading completed");
        this.notifyAll();
    }
    
    private final void loadImage() {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final InputStream resourceAsStream = AVEntry.avm.applet.getClass().getResourceAsStream(this.name);
            try {
                final byte[] array = new byte[4096];
                int read;
                while ((read = resourceAsStream.read(array)) >= 0) {
                    byteArrayOutputStream.write(array, 0, read);
                }
            }
            finally {
                resourceAsStream.close();
            }
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            if (byteArray.length < 10) {
                throw new IOException();
            }
            this.data = Toolkit.getDefaultToolkit().createImage(byteArray);
        }
        catch (Exception ex) {}
        if (this.data == null) {
            try {
                this.data = AVEntry.avm.applet.getImage(AVEntry.avm.applet.getCodeBase(), this.name);
            }
            catch (Exception ex2) {}
        }
        final int index = AVEntry.avm.entries.indexOf(this);
        AVEntry.avm.applet.prepareImage((Image)this.data, AVEntry.avm.applet);
        AVEntry.avm.tracker.addImage((Image)this.data, index);
        try {
            AVEntry.avm.tracker.waitForID(index);
        }
        catch (InterruptedException ex3) {}
        if (((Image)this.data).getWidth(AVEntry.avm.applet) <= 0) {
            this.data = null;
        }
    }
    
    private final synchronized void sync() {
        if (!this.loaded) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
}
