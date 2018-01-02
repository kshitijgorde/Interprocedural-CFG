import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class private
{
    private int height;
    private int ra;
    private static String ja = "\uf835\uf824\uf839\uf835";
    private static String na = "\uf827\uf826\uf802\uf82e\uf82d\uf82e\uf833";
    private static String oa = "\uf823\uf826\uf802\uf82e\uf82d\uf82e\uf833";
    private static String pa = "\uf829\uf82e\uf837\uf824\uf833\uf803\uf826\uf802\uf82e\uf82d\uf82e\uf833";
    private static String sa = "\uf827\uf82e\uf82f\uf835";
    private static String ta = "\uf829\uf82e\uf837\uf824\uf833\uf807\uf82e\uf82f\uf835";
    private static String ua = "\uf80f\uf834\uf82d\uf82d\uf861\uf828\uf82c\uf820\uf826\uf824";
    private static String va = "\uf834\uf833\uf82d";
    private static String wa = "\uf829\uf82e\uf837\uf824\uf833\uf802\uf82e\uf82d\uf82e\uf833";
    
    public private(final int height, final int ra) {
        this.height = height;
        this.ra = ra;
    }
    
    public instanceof _(final Hashtable hashtable, final Component component) {
        final String s = hashtable.get(private.ja);
        if (s == null) {
            return null;
        }
        final Color color = (Color)hashtable.get(private.na);
        final Color color2 = (Color)hashtable.get(private.oa);
        final Color color3 = (Color)hashtable.get(private.pa);
        final Font font = (Font)hashtable.get(private.sa);
        final FontMetrics fontMetrics = component.getFontMetrics(font);
        Font font2 = font;
        if (hashtable.get(private.ta) != null) {
            font2 = (Font)hashtable.get(private.ta);
        }
        int n2;
        final int n = n2 = fontMetrics.stringWidth(s);
        int n3;
        if (font2 != font) {
            n2 = component.getFontMetrics(font2).stringWidth(s);
            n3 = Math.max(n, n2);
        }
        else {
            n3 = fontMetrics.stringWidth(s);
        }
        n3 += 2;
        final Image image = component.createImage(n3, this.height);
        if (image == null) {
            System.out.println(private.ua);
            return null;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setColor(color2);
        graphics.fillRect(0, 0, n3, this.height);
        graphics.setFont(font);
        graphics.setColor(color);
        graphics.drawString(s, (n3 - n) / 2, this.ra);
        graphics.dispose();
        Image image2 = null;
        String s2 = hashtable.get(private.va);
        if (s2 != null && s2.length() == 0) {
            s2 = null;
        }
        Rectangle rectangle = null;
        if (s2 != null) {
            rectangle = new Rectangle(n3, this.height);
            image2 = component.createImage(n3, this.height);
            final Graphics graphics2 = image2.getGraphics();
            Color color4 = color;
            if (hashtable.get(private.wa) != null) {
                color4 = (Color)hashtable.get(private.wa);
            }
            graphics2.setColor(color3);
            graphics2.fillRect(0, 0, n3, this.height);
            graphics2.setFont(font2);
            graphics2.setColor(color4);
            graphics2.drawString(s, (n3 - n2) / 2, this.ra);
            graphics2.dispose();
        }
        final instanceof instanceof1 = new instanceof(image, image2, rectangle);
        instanceof1._(s2);
        return instanceof1;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFF841);
        }
        return new String(array);
    }
    
    static {
        private.ja = a(private.ja);
        private.na = a(private.na);
        private.oa = a(private.oa);
        private.pa = a(private.pa);
        private.sa = a(private.sa);
        private.ta = a(private.ta);
        private.ua = a(private.ua);
        private.va = a(private.va);
        private.wa = a(private.wa);
    }
}
