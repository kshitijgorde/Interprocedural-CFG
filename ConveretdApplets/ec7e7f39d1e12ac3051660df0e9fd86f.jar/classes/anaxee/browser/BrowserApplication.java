// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.browser;

import java.lang.management.ManagementFactory;
import java.security.AccessController;
import java.awt.Color;
import java.security.PrivilegedAction;
import java.awt.Component;
import classes.anaxee.gui.LogoImagePanel;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.MediaTracker;
import classes.anaxee.gui.GuiComponents;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JApplet;

public class BrowserApplication extends JApplet
{
    boolean is_mode_debug;
    String gui_use_mode;
    String bio_use_mode;
    Image logo_image;
    Graphics g;
    URL url;
    GuiComponents gui_components;
    MediaTracker media_tracker;
    
    public void setUseDebugMode(final boolean b) {
        this.gui_components.setUseDebugMode(this.is_mode_debug);
    }
    
    public void setBiometricMode(final String biometricMode) {
        this.gui_components.setBiometricMode(biometricMode);
    }
    
    public void setUseMode(final String useMode) {
        this.gui_components.setUseMode(useMode);
    }
    
    public BrowserApplication() {
        this.is_mode_debug = false;
        this.gui_use_mode = "verification";
        this.bio_use_mode = "BMP_IMAGE";
        this.logo_image = null;
        this.g = null;
        this.setLayout(new GridLayout(1, 1));
        LogoImagePanel.setAppletUsage(true);
        GuiComponents.setAppletUsage(true);
    }
    
    @Override
    public void init() {
        final int n = 1001;
        final String parameter = this.getParameter("DebugMode");
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("true")) {
                this.is_mode_debug = true;
            }
            if (parameter.equalsIgnoreCase("false")) {
                this.is_mode_debug = false;
            }
        }
        final String parameter2 = this.getParameter("UseMode");
        if (parameter2 != null) {
            this.gui_use_mode = parameter2;
        }
        final String parameter3 = this.getParameter("BioMode");
        if (parameter3 != null) {
            this.bio_use_mode = parameter3;
        }
        this.media_tracker = new MediaTracker(this);
        if (this.is_mode_debug) {
            System.out.println("Inside init()");
        }
        this.url = this.getCodeBase();
        if (this.is_mode_debug) {
            System.out.println("url " + this.url);
        }
        if (this.url != null) {
            this.logo_image = this.getImage(this.url, "CompanyLogo.gif");
        }
        this.media_tracker.addImage(this.logo_image, n);
        LogoImagePanel.setAppletImage(this.logo_image);
        try {
            this.media_tracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
            @Override
            public Object run() {
                try {
                    BrowserApplication.this.gui_components = new GuiComponents();
                    BrowserApplication.this.setUseDebugMode(BrowserApplication.this.is_mode_debug);
                    BrowserApplication.this.setUseMode(BrowserApplication.this.gui_use_mode);
                    BrowserApplication.this.setBiometricMode(BrowserApplication.this.bio_use_mode);
                    BrowserApplication.this.add(BrowserApplication.this.gui_components);
                    BrowserApplication.this.getContentPane().setBackground(new Color(255, 0, 0));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }
        });
    }
    
    @Override
    public void start() {
        if (this.is_mode_debug) {
            System.out.println("INSIDE START ");
        }
        this.resize(500, 210);
    }
    
    public String getData() {
        return this.gui_components.getData();
    }
    
    public void setTimeOut(final int timeOut) {
        this.gui_components.setTimeOut(timeOut);
    }
    
    public boolean isScanned() {
        return this.gui_components.isScanned();
    }
    
    public void refreshGUI() {
        this.gui_components.refreshGUI();
    }
    
    @Override
    public void stop() {
    }
    
    @Override
    public void paint(final Graphics graphics) {
        if (this.is_mode_debug) {
            System.out.println("In side paint of Applet ");
        }
        super.paint(graphics);
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    @Override
    public void destroy() {
        final String name = ManagementFactory.getRuntimeMXBean().getName();
        if (this.is_mode_debug) {
            System.out.println("inside destroy () ");
        }
        final int index = name.indexOf(64);
        final String substring = name.substring(0, index);
        final String substring2 = name.substring(index + 1, name.length());
        if (this.is_mode_debug) {
            System.out.println("  pc name  is  " + substring2 + "    process id is " + substring);
        }
        final String string = "taskkill /f /pid " + substring;
        if (this.is_mode_debug) {
            System.out.println(" command string is  " + string);
        }
        try {
            Runtime.getRuntime().exec(string);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
