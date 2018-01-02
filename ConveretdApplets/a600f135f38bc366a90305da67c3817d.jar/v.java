import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class v
{
    private Image D;
    private Image E;
    private int width;
    private int height;
    private static String l = "\u21b0\u21fa";
    private static String m = "\u21e7\u21af\u21fa";
    
    public v(final Image d, final int width, final int height) {
        this.D = d;
        this.width = width;
        this.height = height;
        if (d != null) {
            if (width <= 0) {
                this.width = d.getWidth(null);
            }
            if (height <= 0) {
                this.height = d.getHeight(null);
            }
        }
        else if (width <= 0) {
            this.width = 5;
        }
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void a(final Component component, final Graphics graphics, final int n) {
        if (this.E == null) {
            this.a(component);
        }
        graphics.drawImage(this.E, n, 0, component);
    }
    
    private void a(final Component component) {
        try {
            this.E = component.createImage(this.width, this.height);
        }
        catch (Exception ex) {
            System.out.println(v.l + this.width + v.m + this.height);
            return;
        }
        final Graphics graphics = this.E.getGraphics();
        if (this.D == null || this.width > this.D.getWidth(null) || this.height > this.D.getHeight(null)) {
            final Color color = graphics.getColor();
            graphics.setColor(component.getBackground());
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setColor(color);
        }
        if (this.D != null) {
            graphics.drawImage(this.D, (this.width - this.D.getWidth(null)) / 2, (this.height - this.D.getHeight(null)) / 2, component);
        }
    }
    
    private static String h(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u21c7');
        }
        return new String(array);
    }
    
    static {
        v.l = h(v.l);
        v.m = h(v.m);
    }
}
