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

public class default
{
    private static final int A = 5;
    private int height;
    private int B;
    private static String a = "\u7104\u7115\u7108\u7104";
    private static String b = "\u7116\u7117\u7133\u711f\u711c\u711f\u7102";
    private static String c = "\u7112\u7117\u7133\u711f\u711c\u711f\u7102";
    private static String d = "\u7118\u711f\u7106\u7115\u7102\u7132\u7117\u7133\u711f\u711c\u711f\u7102";
    private static String e = "\u7116\u711f\u711e\u7104";
    private static String f = "\u7118\u711f\u7106\u7115\u7102\u7136\u711f\u711e\u7104";
    private static String g = "\u713e\u7105\u711c\u711c\u7150\u7119\u711d\u7111\u7117\u7115";
    private static String h = "\u7105\u7102\u711c";
    private static String i = "\u7118\u711f\u7106\u7115\u7102\u7136\u7117\u7133\u711f\u711c\u711f\u7102";
    
    public default(final int height, final int b) {
        this.height = height;
        this.B = b;
    }
    
    public final a(final Hashtable hashtable, final Component component) {
        final String s = hashtable.get(default.a);
        if (s == null) {
            return null;
        }
        final Color color = (Color)hashtable.get(default.b);
        final Color color2 = (Color)hashtable.get(default.c);
        final Color color3 = (Color)hashtable.get(default.d);
        final Font font = (Font)hashtable.get(default.e);
        final FontMetrics fontMetrics = component.getFontMetrics(font);
        Font font2 = font;
        if (hashtable.get(default.f) != null) {
            font2 = (Font)hashtable.get(default.f);
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
            System.out.println(default.g);
            return null;
        }
        final Graphics graphics = image.getGraphics();
        graphics.setColor(color2);
        graphics.fillRect(0, 0, n3, this.height);
        graphics.setFont(font);
        graphics.setColor(color);
        graphics.drawString(s, (n3 - n) / 2, this.B);
        graphics.dispose();
        String s2 = hashtable.get(default.h);
        if (s2 != null && s2.length() == 0) {
            s2 = null;
        }
        final Rectangle rectangle = new Rectangle(n3, this.height);
        final Image image2 = component.createImage(n3, this.height);
        final Graphics graphics2 = image2.getGraphics();
        Color color4 = color;
        if (hashtable.get(default.i) != null) {
            color4 = (Color)hashtable.get(default.i);
        }
        graphics2.setColor(color3);
        graphics2.fillRect(0, 0, n3, this.height);
        graphics2.setFont(font2);
        graphics2.setColor(color4);
        graphics2.drawString(s, (n3 - n2) / 2, this.B);
        graphics2.dispose();
        final final final1 = new final(image, image2, rectangle);
        final1.a(s2);
        return final1;
    }
    
    private static String c(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF7170);
        }
        return new String(array);
    }
    
    static {
        default.a = c(default.a);
        default.b = c(default.b);
        default.c = c(default.c);
        default.d = c(default.d);
        default.e = c(default.e);
        default.f = c(default.f);
        default.g = c(default.g);
        default.h = c(default.h);
        default.i = c(default.i);
    }
}
