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

public class class
{
    private int height;
    private int t;
    private static String l = "\uc050\uc041\uc05c\uc050";
    private static String m = "\uc042\uc043\uc067\uc04b\uc048\uc04b\uc056";
    private static String o = "\uc046\uc043\uc067\uc04b\uc048\uc04b\uc056";
    private static String p = "\uc042\uc04b\uc04a\uc050";
    private static String s = "\uc04c\uc04b\uc052\uc041\uc056\uc062\uc04b\uc04a\uc050";
    private static String u = "\uc06a\uc051\uc048\uc048\uc004\uc04d\uc049\uc045\uc043\uc041";
    private static String v = "\uc051\uc056\uc048";
    private static String w = "\uc04c\uc04b\uc052\uc041\uc056\uc067\uc04b\uc048\uc04b\uc056";
    
    public class(final int height, final int t) {
        this.height = height;
        this.t = t;
    }
    
    public u a(final Hashtable hashtable, final Component component) {
        final String s = hashtable.get(class.l);
        if (s == null) {
            return null;
        }
        final Color color = (Color)hashtable.get(class.m);
        final Color color2 = (Color)hashtable.get(class.o);
        final Font font = (Font)hashtable.get(class.p);
        final FontMetrics fontMetrics = component.getFontMetrics(font);
        Font font2 = font;
        if (hashtable.get(class.s) != null) {
            font2 = (Font)hashtable.get(class.s);
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
            System.out.println(class.u);
            return null;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setColor(color2);
        graphics.fillRect(0, 0, n3, this.height);
        graphics.setFont(font);
        graphics.setColor(color);
        graphics.drawString(s, (n3 - n) / 2, this.t);
        graphics.dispose();
        Image image2 = null;
        String s2 = hashtable.get(class.v);
        if (s2 != null && s2.length() == 0) {
            s2 = null;
        }
        Rectangle rectangle = null;
        if (s2 != null) {
            rectangle = new Rectangle(n3, this.height);
            image2 = component.createImage(n3, this.height);
            final Graphics graphics2 = image2.getGraphics();
            Color color3 = color;
            if (hashtable.get(class.w) != null) {
                color3 = (Color)hashtable.get(class.w);
            }
            graphics2.setColor(color2);
            graphics2.fillRect(0, 0, n3, this.height);
            graphics2.setFont(font2);
            graphics2.setColor(color3);
            graphics2.drawString(s, (n3 - n2) / 2, this.t);
            graphics2.dispose();
        }
        final u u = new u(image, image2, rectangle);
        u._(s2);
        return u;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\uc024');
        }
        return new String(array);
    }
    
    static {
        class.l = _(class.l);
        class.m = _(class.m);
        class.o = _(class.o);
        class.p = _(class.p);
        class.s = _(class.s);
        class.u = _(class.u);
        class.v = _(class.v);
        class.w = _(class.w);
    }
}
