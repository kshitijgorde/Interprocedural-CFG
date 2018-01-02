// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.gui;

import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class DynamicImagePanel extends JPanel
{
    public BufferedImage image;
    Graphics graphics;
    static boolean is_mode_debug;
    
    public static void setUseDebugMode(final boolean is_mode_debug) {
        DynamicImagePanel.is_mode_debug = is_mode_debug;
    }
    
    public DynamicImagePanel(final Image image) {
        this.graphics = null;
        this.setLayout(null);
        this.image = (BufferedImage)image;
        if (DynamicImagePanel.is_mode_debug) {
            System.out.println("Inside constructor of DNImagePanel");
        }
    }
    
    @Override
    public void paint(final Graphics graphics) {
        if (DynamicImagePanel.is_mode_debug) {
            System.out.println(this.getBackground());
            System.out.println("Inside paint() of DNImagePanel");
        }
        graphics.drawImage(this.image, 40, 10, 150, 200, null);
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        DynamicImagePanel.is_mode_debug = false;
    }
}
