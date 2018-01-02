// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.applets;

import java.io.IOException;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.awt.Image;
import java.text.DecimalFormat;
import com.guymcarthur.Debuggable;
import java.applet.Applet;

public class BaseApplet extends Applet implements Debuggable
{
    private static final DecimalFormat MBytes;
    protected boolean debug;
    
    public Image loadImage(final String s) throws IOException {
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(this.getClass().getResourceAsStream(s));
        final byte[] array = new byte[bufferedInputStream.available()];
        bufferedInputStream.read(array);
        bufferedInputStream.close();
        return Toolkit.getDefaultToolkit().createImage(array);
    }
    
    public void message(final String s, final Throwable t) {
        this.message(s);
        if (this.debug) {
            t.printStackTrace(System.out);
        }
    }
    
    public void message(final String s) {
        this.showStatus(s);
        if (this.debug) {
            System.out.println(s);
        }
    }
    
    public boolean isDebug() {
        return this.debug;
    }
    
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
    
    public void printFreeMegs() {
        System.out.println("Free memory is " + BaseApplet.MBytes.format(Runtime.getRuntime().freeMemory() / 1048576.0));
    }
    
    public void printTotalMegs() {
        System.out.println("Total memory is " + BaseApplet.MBytes.format(Runtime.getRuntime().totalMemory() / 1048576.0));
    }
    
    static {
        MBytes = new DecimalFormat("#,###.00 MBytes");
    }
}
