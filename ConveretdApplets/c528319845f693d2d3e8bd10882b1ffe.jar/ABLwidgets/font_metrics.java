// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.util.Hashtable;
import java.awt.FontMetrics;
import java.awt.Canvas;

public class font_metrics extends Canvas
{
    private FontMetrics a;
    private char b;
    private int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    private static String j;
    private static Hashtable k;
    private static String[] l;
    private static String[] m;
    private static Frame n;
    
    protected font_metrics(final FontMetrics a, final char b) {
        this.a = a;
        this.b = b;
        try {
            this.b();
            this.a();
            font_metrics.k.put(new Key(a, b), this);
        }
        catch (Throwable t) {
            abljem.b("font_metrics details failed " + t.toString());
        }
    }
    
    public Font getFont() {
        return this.a.getFont();
    }
    
    public int a(final char c) {
        return this.a.charWidth(c);
    }
    
    public int a(final String s) {
        return this.a.stringWidth(s);
    }
    
    private void a() {
        this.d = this.a.getLeading();
        this.e = this.a.getAscent();
        this.f = this.a.getDescent();
        this.g = this.a.getHeight();
        this.h = this.a.getMaxAscent();
        this.i = this.a.getMaxDescent();
        switch (this.b) {
            default: {
                this.d();
                this.c();
            }
            case '0': {}
            case '1': {
                this.d();
                this.a(this.c);
                this.c();
            }
            case '2': {
                this.d();
                this.b(this.c);
                this.c();
            }
        }
    }
    
    private void b() {
        final String family = this.a.getFont().getFamily();
        for (int i = 0; i < font_metrics.l.length; ++i) {
            if (family.equalsIgnoreCase(font_metrics.l[i])) {
                this.c = 1;
                return;
            }
        }
        for (int j = 0; j < font_metrics.m.length; ++j) {
            if (family.equalsIgnoreCase(font_metrics.m[j])) {
                this.c = 2;
                return;
            }
        }
        this.c = 0;
    }
    
    private void a(final int n) {
        this.b(n);
        Label_0251: {
            switch (n) {
                case 1: {
                    switch (this.a.getFont().getSize()) {
                        case 9: {
                            ++this.f;
                            break Label_0251;
                        }
                        case 14:
                        case 25:
                        case 37: {
                            ++this.e;
                            break Label_0251;
                        }
                        case 31: {
                            --this.e;
                            break Label_0251;
                        }
                    }
                    break;
                }
                case 2: {
                    switch (this.a.getFont().getSize()) {
                        case 7:
                        case 18: {
                            ++this.e;
                            break Label_0251;
                        }
                        case 8: {
                            this.e += 2;
                            break Label_0251;
                        }
                        case 9:
                        case 10:
                        case 11:
                        case 32:
                        case 36: {
                            ++this.f;
                            break Label_0251;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    private void b(final int n) {
        switch (n) {
            case 1: {
                final int size = this.a.getFont().getSize();
                if (size >= 10) {
                    ++this.e;
                }
                if (size >= 42) {
                    ++this.e;
                    break;
                }
                break;
            }
        }
    }
    
    private void c() {
        this.g = this.d + this.e + this.f;
        this.h = this.e;
        this.i = this.f;
    }
    
    private void d() {
        final Font font = this.a.getFont();
        final int stringWidth = this.a.stringWidth(font_metrics.j);
        final int ascent = this.a.getAscent();
        final int descent = this.a.getDescent();
        int e = ascent;
        int f = descent;
        final int n = ascent + descent;
        final Image a = this.a(stringWidth, n);
        final Graphics graphics = a.getGraphics();
        graphics.setFont(font);
        final int n2 = ascent * stringWidth;
        final int n3 = n * stringWidth;
        final int[] array = new int[n3];
        graphics.setColor(Color.white);
        graphics.clearRect(0, 0, stringWidth, n);
        graphics.fillRect(0, 0, stringWidth, n);
        graphics.setColor(Color.black);
        graphics.drawString(font_metrics.j, 0, ascent);
        final PixelGrabber pixelGrabber = new PixelGrabber(a, 0, 0, stringWidth, n, array, 0, stringWidth);
        try {
            pixelGrabber.grabPixels(0L);
            int i;
            for (i = 0; i < n2; ++i) {
                if ((array[i] & 0xFFFFFF) == 0x0) {
                    e = ascent - i / stringWidth;
                    break;
                }
            }
            if (i == n2) {
                e = ascent;
            }
            int j;
            for (j = n3 - 1; j >= n2; --j) {
                if ((array[j] & 0xFFFFFF) == 0x0) {
                    f = j / stringWidth - ascent + 1;
                    break;
                }
            }
            if (j == n2 - 1) {
                f = descent;
            }
        }
        catch (Throwable t) {
            abljem.b("Falling back to standard ascent=" + ascent + " descent=" + descent);
            t.printStackTrace();
            e = ascent;
            f = descent;
        }
        finally {
            graphics.dispose();
        }
        this.e = e;
        this.f = f;
    }
    
    private Image a(final int n, final int n2) {
        Image image;
        try {
            image = font_metrics.n.createImage(n, n2);
        }
        catch (Throwable t) {
            abljem.b("font_metrics createImage(" + n + "," + n2 + ") failed " + t.toString());
            return null;
        }
        if (image == null) {
            abljem.b("font_metrics null image");
        }
        return image;
    }
    
    public static void a(final Frame n) {
        (font_metrics.n = n).addNotify();
    }
    
    public static font_metrics a(final FontMetrics fontMetrics, final char c) {
        font_metrics font_metrics = ABLwidgets.font_metrics.k.get(new Key(fontMetrics, c));
        if (font_metrics == null) {
            font_metrics = new font_metrics(fontMetrics, c);
        }
        return font_metrics;
    }
    
    static {
        font_metrics.l = new String[] { "Monospaced", "Courier", "Courier New" };
        font_metrics.m = new String[] { "SansSerif", "Helvetica", "Arial" };
        font_metrics.j = "\u00c9!@$^&()QW{}|\"ZM?`0gqty[]\\fjkl;'b/_";
        font_metrics.k = new Hashtable();
    }
    
    private static class Key
    {
        protected Font a;
        protected char b;
        
        Key(final FontMetrics fontMetrics, final char b) {
            this.a = fontMetrics.getFont();
            this.b = b;
        }
        
        public int hashCode() {
            return this.a.hashCode() + this.b;
        }
        
        public boolean equals(final Object o) {
            if (o != null && o instanceof Key) {
                final Key key = (Key)o;
                if (key.a.equals(this.a) && key.b == this.b) {
                    return true;
                }
            }
            return false;
        }
    }
}
