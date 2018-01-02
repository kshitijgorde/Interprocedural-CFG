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

public class this
{
    private static final int da = 5;
    private int height;
    private int ea;
    private static String V = "\u7cf7\u7ce6\u7cfb\u7cf7";
    private static String W = "\u7ce5\u7ce4\u7cc0\u7cec\u7cef\u7cec\u7cf1";
    private static String _a = "\u7ce1\u7ce4\u7cc0\u7cec\u7cef\u7cec\u7cf1";
    private static String aa = "\u7ceb\u7cec\u7cf5\u7ce6\u7cf1\u7cc1\u7ce4\u7cc0\u7cec\u7cef\u7cec\u7cf1";
    private static String ba = "\u7ce5\u7cec\u7ced\u7cf7";
    private static String fa = "\u7ceb\u7cec\u7cf5\u7ce6\u7cf1\u7cc5\u7cec\u7ced\u7cf7";
    private static String ga = "\u7ccd\u7cf6\u7cef\u7cef\u7ca3\u7cea\u7cee\u7ce2\u7ce4\u7ce6";
    private static String ha = "\u7cf6\u7cf1\u7cef";
    private static String ia = "\u7ceb\u7cec\u7cf5\u7ce6\u7cf1\u7cc5\u7ce4\u7cc0\u7cec\u7cef\u7cec\u7cf1";
    
    public this(final int height, final int ea) {
        this.height = height;
        this.ea = ea;
    }
    
    public public a(final Hashtable hashtable, final Component component) {
        final String s = hashtable.get(this.V);
        if (s == null) {
            return null;
        }
        final Color color = (Color)hashtable.get(this.W);
        final Color color2 = (Color)hashtable.get(this._a);
        final Color color3 = (Color)hashtable.get(this.aa);
        final Font font = (Font)hashtable.get(this.ba);
        final FontMetrics fontMetrics = component.getFontMetrics(font);
        Font font2 = font;
        if (hashtable.get(this.fa) != null) {
            font2 = (Font)hashtable.get(this.fa);
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
        n3 += 10;
        final Image image = component.createImage(n3, this.height);
        if (image == null) {
            System.out.println(this.ga);
            return null;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setColor(color2);
        graphics.fillRect(0, 0, n3, this.height);
        graphics.setFont(font);
        graphics.setColor(color);
        graphics.drawString(s, (n3 - n) / 2, this.ea);
        graphics.dispose();
        String s2 = hashtable.get(this.ha);
        if (s2 != null && s2.length() == 0) {
            s2 = null;
        }
        final Rectangle rectangle = new Rectangle(n3, this.height);
        final Image image2 = component.createImage(n3, this.height);
        final Graphics graphics2 = image2.getGraphics();
        Color color4 = color;
        if (hashtable.get(this.ia) != null) {
            color4 = (Color)hashtable.get(this.ia);
        }
        graphics2.setColor(color3);
        graphics2.fillRect(0, 0, n3, this.height);
        graphics2.setFont(font2);
        graphics2.setColor(color4);
        graphics2.drawString(s, (n3 - n2) / 2, this.ea);
        graphics2.dispose();
        final public public1 = new public(image, image2, rectangle);
        public1.b(s2);
        return public1;
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFE7C83);
        }
        return new String(array);
    }
    
    static {
        this.V = b(this.V);
        this.W = b(this.W);
        this._a = b(this._a);
        this.aa = b(this.aa);
        this.ba = b(this.ba);
        this.fa = b(this.fa);
        this.ga = b(this.ga);
        this.ha = b(this.ha);
        this.ia = b(this.ia);
    }
}
