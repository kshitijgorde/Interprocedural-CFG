// 
// Decompiled by Procyon v0.5.30
// 

package org.wordsmith.anagram;

import java.awt.Font;
import java.util.Arrays;
import java.awt.GraphicsEnvironment;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.awt.Cursor;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

public class AnagramApplet extends Applet implements ConfigurationGate, Redirector
{
    private AnagramCanvas myCanvas;
    private Configuration myConfiguration;
    private static String[][] ourParameterInfo;
    
    public void start() {
        this.myCanvas = new AnagramCanvas(this.myConfiguration, this.getDocumentBase(), this);
        this.setLayout(new BorderLayout());
        this.add(this.myCanvas, "Center");
        this.doLayout();
        this.myCanvas.initMainImage();
        final Timer timer = new Timer();
        timer.schedule(new LoadStringsTask(this, timer, new ShowBannerTask(this.myCanvas)), 0L, 100L);
    }
    
    public void init() {
        super.init();
        this.myConfiguration = new ConfigurationImpl(this);
        if (!this.isAuthorized()) {
            this.setCursor(new Cursor(12));
        }
    }
    
    public Image loadImage(final String s) {
        try {
            return this.getAppletContext().getImage(new URL(s));
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public String[][] getParameterInfo() {
        return AnagramApplet.ourParameterInfo;
    }
    
    public void redirectToURL(final URL url) {
        if (url != null) {
            this.getAppletContext().showDocument(url, "_parent");
        }
    }
    
    protected static void dumpAllFonts() {
        final Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        System.err.println("All Fonts:" + allFonts.length);
        System.err.println(Arrays.asList(allFonts).toString().replace(',', '\n'));
    }
    
    public boolean isAuthorized() {
        return this.myConfiguration.isSiteAuthorized(this.getDocumentBase());
    }
    
    public Configuration getConfiguration() {
        return this.myConfiguration;
    }
    
    public AnagramCanvas getCanvas() {
        return this.myCanvas;
    }
    
    static {
        AnagramApplet.ourParameterInfo = new String[][] { { "shadow", "true/false", "" }, { "backgroundColor", "#rrggbb", "" }, { "symbolDelayMS", "integer", "" }, { "endless", "true/false", "" }, { "bold", "true/false", "" }, { "italic", "true/false", "" }, { "font", "string", "" }, { "fontSize", "integer", "" }, { "pauseTimeMS", "integer", "" }, { "shadowColor", "#rrggbb", "" }, { "roundedCornersRadius", "true/false", "" }, { "sourceString", "string", "" }, { "targetString", "string", "" }, { "remoteAnagram", "true/false", "" }, { "textColor", "#rrggbb", "" }, { "symbolFrames", "integer", "" }, { "symbolTimeMS", "integer", "" }, { "verticalGap", "float", "" } };
    }
}
