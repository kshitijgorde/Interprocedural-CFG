// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet;

import java.applet.AppletContext;
import java.net.URL;
import java.io.IOException;
import com.chess.util.Util;
import java.io.InputStream;
import java.io.FileInputStream;
import java.awt.Component;
import java.awt.Container;
import java.util.Properties;
import java.applet.AppletStub;
import javax.swing.JFrame;

public class ChessMainFrame extends JFrame implements AppletStub
{
    private ChessApplet applet;
    private Properties properties;
    
    public ChessMainFrame(final Properties properties) {
        super("Chess.com");
        this.setDefaultCloseOperation(3);
        this.properties = properties;
        (this.applet = new ChessApplet()).setStub(this);
        this.setContentPane(this.applet);
        this.applet.init();
        this.applet.start();
        this.setSize(710, 585);
        this.setLocationRelativeTo(null);
    }
    
    public static void main(final String[] args) {
        final Properties properties = new Properties();
        if (args.length > 0) {
            try {
                final FileInputStream fileInputStream = new FileInputStream(args[0]);
                properties.load(fileInputStream);
                fileInputStream.close();
            }
            catch (IOException ex) {
                Util.getLogger((Class)ChessMainFrame.class).warn((Object)"error loading properties file", (Throwable)ex);
            }
        }
        final ChessMainFrame chessMainFrame = new ChessMainFrame(properties);
        chessMainFrame.setVisible(true);
    }
    
    public URL getDocumentBase() {
        return this.applet.getDocumentBase();
    }
    
    public URL getCodeBase() {
        return this.applet.getCodeBase();
    }
    
    public String getParameter(final String name) {
        return (this.properties != null) ? this.properties.getProperty(name) : null;
    }
    
    public AppletContext getAppletContext() {
        return null;
    }
    
    public void appletResize(final int width, final int height) {
        this.applet.setSize(width, height);
    }
}
