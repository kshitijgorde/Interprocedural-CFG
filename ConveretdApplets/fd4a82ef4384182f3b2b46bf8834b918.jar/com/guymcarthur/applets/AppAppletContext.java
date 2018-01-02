// 
// Decompiled by Procyon v0.5.30
// 

package com.guymcarthur.applets;

import java.awt.Toolkit;
import java.awt.Image;
import sun.applet.AppletAudioClip;
import java.applet.AudioClip;
import java.net.URL;
import java.util.Enumeration;
import java.applet.Applet;
import java.applet.AppletContext;

public class AppAppletContext implements AppletContext
{
    public void addApplet(final Applet applet, final String s) {
    }
    
    public Applet getApplet(final String s) {
        return null;
    }
    
    public Enumeration getApplets() {
        return null;
    }
    
    public AudioClip getAudioClip(final URL url) {
        return new AppletAudioClip(url);
    }
    
    public Image getImage(final URL url) {
        return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    public void showDocument(final URL url) {
    }
    
    public void showDocument(final URL url, final String s) {
    }
    
    public void showStatus(final String s) {
        System.out.println(s);
    }
}
