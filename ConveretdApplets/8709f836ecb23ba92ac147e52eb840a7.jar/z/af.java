// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.Component;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JFrame;

public class af extends JFrame
{
    private static af a;
    private static w b;
    private static Dimension c;
    private static Dimension d;
    private static int e;
    private static int f;
    private JLabel g;
    private static /* synthetic */ boolean h;
    
    public static void a(final Dimension c, final w b) {
        if (!af.h && (c.width <= 0 || c.height <= 0)) {
            throw new AssertionError();
        }
        if (!af.h && b == null) {
            throw new AssertionError();
        }
        af.b = b;
        af.c = c;
    }
    
    private af() {
        this.getContentPane().setLayout(new BorderLayout());
        this.setAlwaysOnTop(true);
        this.setLocationByPlatform(true);
        this.g = new JLabel();
        this.addKeyListener(new Q(this, this));
        this.addComponentListener(new S(this, this));
        this.pack();
        af.e = this.getSize().width - this.getContentPane().getSize().width;
        af.f = this.getSize().height - this.getContentPane().getSize().height;
        af.d = new Dimension(af.c.width - af.e, af.c.height - af.f);
    }
    
    public static void a(final String s) {
        if (!af.h && af.b == null) {
            throw new AssertionError();
        }
        if (af.a != null) {
            af.a.setVisible(false);
            af.a = null;
        }
        final af af2;
        final af af = af2 = (z.af.a = new af());
        if (!z.af.h && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        af2.setTitle(au.a(s));
        final ImageIcon a;
        boolean b;
        if ((a = af2.a(s, z.af.d)) == null) {
            b = false;
        }
        else {
            final Dimension preferredSize = new Dimension(a.getIconWidth(), a.getIconHeight());
            (af2.g = new JLabel()).setIcon(a);
            af2.g.setOpaque(true);
            af2.g.setPreferredSize(preferredSize);
            af2.setPreferredSize(new Dimension(preferredSize.width + z.af.e, preferredSize.height + z.af.f));
            b = true;
        }
        if (b) {
            af.setResizable(false);
            af.validate();
            af.pack();
            af.setVisible(true);
        }
    }
    
    private ImageIcon a(String s, final Dimension dimension) {
        String s2;
        try {
            return w.a(s, dimension);
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
            s2 = String.format(G.c("ZOOM_FILE_NOT_FOUND"), s);
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
            s2 = G.c("ZOOM_IO_ERROR");
        }
        catch (am am) {
            am.printStackTrace();
            s2 = G.c("ZOOM_UNSUPPORTED_FORMAT");
        }
        catch (M m) {
            m.printStackTrace();
            s2 = G.c("FILE_TOO_LARGE");
        }
        catch (ah ah) {
            ah.printStackTrace();
            s2 = G.c("FILE_TOO_LARGE");
        }
        final String c = G.c("ZOOM_ERROR_DIALOG_TITLE");
        final af af = this;
        final String s3 = c;
        final String s4 = s2;
        s = s3;
        this = af;
        if (!z.af.h && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        if (!z.af.h && (s4 == null || s4.length() <= 0)) {
            throw new AssertionError();
        }
        JOptionPane.showMessageDialog(this, s4, s, 0);
        return null;
    }
    
    static {
        af.h = !af.class.desiredAssertionStatus();
    }
}
