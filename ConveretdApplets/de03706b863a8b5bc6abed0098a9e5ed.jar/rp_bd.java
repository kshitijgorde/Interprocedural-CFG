import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Window;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_bd extends rp_fG
{
    private String a;
    private String b;
    private String c;
    private String d;
    
    private rp_bd(final String s, final String a) {
        super(s);
        this.c = null;
        this.d = null;
        this.a = a;
    }
    
    public rp_bd(final Window window, final String s) {
        this(s, null);
    }
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.a();
            this.b();
        }
        super.setVisible(visible);
    }
    
    public void a(final JPanel panel) {
        if (this.a != null) {
            panel.add(new JLabel(this.a));
        }
    }
    
    protected final boolean b() {
        this.b = this.d;
        this.dispose();
        return true;
    }
    
    protected final boolean c() {
        this.b = this.c;
        this.dispose();
        return true;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.b = actionEvent.getActionCommand();
        this.dispose();
    }
    
    public static void a(final Window window, final String s, final String s2, final String s3) {
        final rp_bd rp_bd;
        (rp_bd = new rp_bd(s, s2)).a(s3, null);
        rp_bd.setVisible(true);
    }
    
    public static void a(final Window window, final rp_fb rp_fb, String s, final String s2) {
        s = s;
        final String a = rp_fb.a(0, s);
        s = s2;
        final String a2 = rp_fb.a(0, s);
        s = "cl";
        a(window, a, a2, rp_fb.a(0, s));
    }
    
    public static int a(final Window window, final rp_fb rp_fb, final String s, final String s2, final boolean b) {
        final rp_bd rp_bd;
        (rp_bd = new rp_bd(rp_fb.a(0, s), s2)).a(rp_fb.a(0, "y"), "Y");
        rp_bd.a(rp_fb.a(0, "n"), "N");
        rp_bd.d = "Y";
        if (b) {
            rp_bd.c = "C";
            rp_bd.a(rp_fb.a(0, "Cancel"), "C");
        }
        else {
            rp_bd.c = "N";
        }
        rp_bd.setVisible(true);
        final String b2 = rp_bd.b;
        if ("Y".equals(b2)) {
            return 1;
        }
        if ("N".equals(b2)) {
            return 2;
        }
        return 0;
    }
    
    public static int b(final Window window, final rp_fb rp_fb, final String s, final String s2, final boolean b) {
        return a(window, rp_fb, s, rp_fb.a(0, s2), b);
    }
    
    public static int c(final Window window, final rp_fb rp_fb, final String s, final String s2, final boolean b) {
        return a(window, rp_fb, s, rp_fb.a(s2), false);
    }
}
