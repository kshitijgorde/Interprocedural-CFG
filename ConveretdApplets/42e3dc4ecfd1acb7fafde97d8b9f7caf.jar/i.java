import java.awt.Dimension;
import java.awt.Component;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class i extends JDialog implements WindowListener
{
    private j a;
    private static i b;
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.a();
    }
    
    public static synchronized void a() {
        if (i.b != null) {
            i.b.a.a();
        }
        i.b = null;
    }
    
    public static synchronized i b() {
        if (i.b == null) {
            i.b = new i();
        }
        return i.b;
    }
    
    private i() {
        super(Main.h());
        this.a = new j(this);
        final t a = t.a();
        this.addWindowListener(this);
        this.setBackground(dj.w);
        this.setSize(Integer.parseInt(a.a("chat.frame.size").substring(0, 3)), Integer.parseInt(a.a("chat.frame.size").substring(4, a.a("chat.frame.size").length())));
        this.getContentPane().add(this.a);
        this.setTitle("ANYwebcam Chat");
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setLocation(screenSize.width / 2 - this.getSize().width / 2, screenSize.height / 2 - this.getSize().height / 2);
    }
    
    public ck a(final bj bj) {
        final ck ck = new ck(bj, this.a);
        this.a.a(bj.r(), ck);
        return ck;
    }
    
    public cy a(final cz cz) {
        final cy cy = new cy(cz, this.a);
        this.a.a(cy.getName(), cy);
        return cy;
    }
    
    public boolean a(final String s) {
        final boolean g = this.a.g(s);
        if (!g) {
            return g;
        }
        if (!this.isVisible()) {
            this.setVisible(true);
        }
        return g;
    }
    
    static {
        i.b = null;
    }
}
