import netscape.security.PrivilegeManager;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends m
{
    public String i;
    public String h;
    public String g;
    public String f;
    public Font e;
    public int d;
    public String b;
    public String a;
    
    public a(final String i, final String h, final String g, final Color cg, final Color cf, final String s, final int n, final String b, final String a) {
        super(100, 20);
        this.b = b;
        this.a = a;
        this.i = i;
        this.h = h;
        this.g = g;
        this.e = new Font(s, 1, n);
        this.d = this.getFontMetrics(this.e).getHeight();
        super.cg = cg;
        super.cf = cf;
        super.setFont(this.e);
        this.resize(100, this.d + 8);
        this.b();
    }
    
    public final void e(final String i) {
        this.i = i;
        this.b();
    }
    
    public final void d(final String h) {
        this.h = h;
        this.b();
    }
    
    public final void c(final String g) {
        this.g = g;
        this.b();
    }
    
    public final void b() {
        this.f = "#" + this.i + ": " + this.h + " " + this.a + ", " + this.b + " " + this.g;
        super.h();
        super.a4(this.f);
        super.repaint();
    }
    
    public static void a() {
        try {
            PrivilegeManager.enablePrivilege("TerminalEmulator");
        }
        catch (Exception ex) {}
    }
}
