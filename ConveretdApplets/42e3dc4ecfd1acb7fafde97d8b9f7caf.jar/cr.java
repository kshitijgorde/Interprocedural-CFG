import java.util.Enumeration;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Properties;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import javax.swing.JScrollBar;
import java.awt.Dimension;
import java.util.Hashtable;
import java.awt.Font;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class cr extends JPanel
{
    public static Font a;
    public static int b;
    public static Hashtable c;
    private Dimension d;
    private JScrollBar e;
    private cs f;
    public static /* synthetic */ Class g;
    
    private static void a(final String s, final String s2) {
        cr.c.put(s2, ImageRes.a(s));
    }
    
    public cr(final cl cl) {
        this.d = new Dimension(200, 200);
        this.setLayout(new BorderLayout());
        this.e = new JScrollBar(1, 5, 5, 0, 10);
        this.add(this.f = new cs(this, this.e, cl), "Center");
        this.add(this.e, "East");
    }
    
    public void a(final String s, final int n) {
        this.f.a(s, true, n);
    }
    
    public String a() {
        return this.f.a();
    }
    
    public String a(final int n, final int n2) {
        return this.f.a(n, n2);
    }
    
    public Color getBackground() {
        if (this.f != null) {
            return this.f.getBackground();
        }
        return super.getBackground();
    }
    
    public Font getFont() {
        if (this.f != null) {
            return this.f.getFont();
        }
        return super.getFont();
    }
    
    public Color getForeground() {
        if (this.f != null) {
            return this.f.getForeground();
        }
        return super.getForeground();
    }
    
    public Dimension getPreferredSize() {
        return this.d;
    }
    
    public void setBackground(final Color color) {
        if (this.f != null) {
            this.f.setBackground(color);
        }
        else {
            super.setBackground(color);
        }
    }
    
    public void a(final int n) {
        this.f.a(n);
    }
    
    public void setFont(final Font font) {
        if (this.f != null) {
            this.f.a(font.getName(), font.getSize());
        }
        else {
            super.setFont(font);
        }
    }
    
    public void setForeground(final Color foreground) {
        if (this.f != null) {
            this.f.setForeground(foreground);
        }
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        cr.a = new Font("Dialog", 0, 12);
        cr.b = 1;
        cr.c = null;
        cr.c = new Hashtable();
        Cloneable cloneable = null;
        try {
            final InputStream resourceAsStream = ((cr.g == null) ? (cr.g = class$("au.com.rocketdog.project.awc.applet.images.ImageRes")) : cr.g).getResourceAsStream("Smileys.properties");
            cloneable = new Properties();
            ((Properties)cloneable).load(resourceAsStream);
        }
        catch (IOException ex) {
            b.a(ex, 3);
        }
        cr.b = 1;
        final Enumeration<String> keys = ((Hashtable<String, V>)cloneable).keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final int index = s.indexOf(".image");
            if (index == s.length() - 6) {
                final String property = ((Properties)cloneable).getProperty(s);
                final StringTokenizer stringTokenizer = new StringTokenizer(((Properties)cloneable).getProperty(s.substring(0, index) + ".emoticons"));
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    a(property, nextToken);
                    if (nextToken.length() > cr.b) {
                        cr.b = nextToken.length();
                    }
                }
            }
        }
    }
}
