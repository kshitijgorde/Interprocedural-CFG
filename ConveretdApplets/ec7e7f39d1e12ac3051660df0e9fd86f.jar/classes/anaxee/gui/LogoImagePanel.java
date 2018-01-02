// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.gui;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.awt.Image;
import javax.swing.JPanel;

public class LogoImagePanel extends JPanel
{
    boolean is_mode_debug;
    static Image image;
    static boolean is_applet;
    
    public static void setAppletUsage(final boolean is_applet) {
        LogoImagePanel.is_applet = is_applet;
    }
    
    public static void setAppletImage(final Image image) {
        LogoImagePanel.image = image;
    }
    
    public void setUseDebugMode(final boolean is_mode_debug) {
        this.is_mode_debug = is_mode_debug;
    }
    
    LogoImagePanel() {
        this.is_mode_debug = false;
        this.setBackground(Color.WHITE);
        if (this.is_mode_debug) {
            System.out.println("is applet " + LogoImagePanel.is_applet);
        }
        try {
            if (!LogoImagePanel.is_applet) {
                LogoImagePanel.image = ImageIO.read(new File("CompanyLogo.gif"));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void paint(final Graphics graphics) {
        if (this.is_mode_debug) {
            System.out.println(" " + LogoImagePanel.image);
        }
        graphics.drawImage(LogoImagePanel.image, 20, 0, 120, 100, null);
    }
    
    static {
        LogoImagePanel.is_applet = false;
    }
}
