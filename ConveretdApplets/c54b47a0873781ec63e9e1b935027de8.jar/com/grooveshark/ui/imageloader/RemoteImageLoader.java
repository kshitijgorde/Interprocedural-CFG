// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.imageloader;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import java.net.URL;

public class RemoteImageLoader implements ImageLoader
{
    private String imageFolder;
    private URL serverUrl;
    private JApplet applet;
    
    public RemoteImageLoader(final JApplet applet, final URL serverUrl, final String imageFolder) {
        this.applet = applet;
        this.serverUrl = serverUrl;
        this.imageFolder = imageFolder;
    }
    
    public ImageIcon loadImage(final String imageName) {
        try {
            return new ImageIcon(this.applet.getImage(this.serverUrl, this.imageFolder + imageName));
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
