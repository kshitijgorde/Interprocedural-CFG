// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import b.a.d.c;
import java.util.MissingResourceException;
import java.util.Date;
import b.a.c.q;
import b.a.d.g;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.UIManager;
import b.a.d.d;
import java.util.ResourceBundle;
import b.a.e.i;

public class a extends i
{
    protected n i;
    protected ResourceBundle j;
    
    public a() {
        this.b();
    }
    
    private void b() {
        try {
            String property = null;
            final boolean b = b.a.d.d.b("1.4") >= 0;
            final boolean b2 = b.a.d.d.b("1.5") >= 0;
            final boolean b3 = b.a.d.d.a() && b.a.d.d.c("10.5") >= 0;
            try {
                property = System.getProperty("swing.defaultlaf");
            }
            catch (Exception ex2) {}
            if (property == null) {
                if (b.a.d.d.b()) {
                    property = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                }
                else if (b.a.d.d.a()) {
                    property = "apple.laf.AquaLookAndFeel";
                }
                else {
                    property = "javax.swing.plaf.metal.MetalLookAndFeel";
                }
            }
            if (b.a.d.d.a() && (!b || (b3 && b2))) {
                property = "javax.swing.plaf.metal.MetalLookAndFeel";
            }
            final UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
            boolean b4 = false;
            for (int i = 0; i < installedLookAndFeels.length; ++i) {
                if (installedLookAndFeels[i].getClassName().equals(property)) {
                    b4 = true;
                    break;
                }
            }
            if (b4) {
                UIManager.setLookAndFeel(property);
            }
        }
        catch (Exception ex) {
            System.err.println("UI exception: " + ex);
        }
    }
    
    public void init() {
        super.init();
        this.j = ResourceBundle.getBundle("com-astronomy-almanac");
        this.i = new n(this);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add("Center", this.i);
        String s = this.getParameter("LOCATION");
        final String parameter = this.getParameter("HELPURL");
        final String b = b.a.d.g.b(this.getParameter("ATLASQUERYURL"));
        if (b.a.d.d.a(s)) {
            s = this.a("almloc");
        }
        if (!b.a.d.d.a(s)) {
            final q a = q.a(s);
            if (a != null) {
                this.i.a(a);
            }
        }
        if (!b.a.d.d.a(parameter)) {
            this.i.a(parameter);
        }
        if (!b.a.d.d.a(b)) {
            a.a.a.h.a(b);
        }
    }
    
    public void a(final q q) {
        this.a("almloc", q.toString(), new Date(System.currentTimeMillis() + 315360000000L));
    }
    
    public String getAppletInfo() {
        return this.c("APP_INFO");
    }
    
    public void start() {
        this.i.e();
    }
    
    public void stop() {
        this.i.f();
    }
    
    public String b(final String s) {
        String string = null;
        try {
            string = this.j.getString(s);
        }
        catch (MissingResourceException ex) {
            System.err.println("ERROR: Missing resource \"" + s + '\"');
        }
        return string;
    }
    
    public String c(final String s) {
        final String b = this.b(s);
        if (b != null) {
            return b;
        }
        return "[" + s + "]";
    }
    
    public String[] d(final String s) {
        return this.a(s, '\t');
    }
    
    public String[] a(final String s, final char c) {
        final String b = this.b(s);
        if (b != null) {
            return c.a(b, c);
        }
        return new String[] { "[" + s + "]" };
    }
}
