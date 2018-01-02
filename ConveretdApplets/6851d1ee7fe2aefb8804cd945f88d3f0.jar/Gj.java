import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class Gj extends Dialog implements WindowListener, ActionListener
{
    private Nj ya;
    private Button za;
    private a Aa;
    private boolean Ba;
    private o Ca;
    private int La;
    private int Ma;
    
    public Gj(final Frame frame, final String s, final int la, final int ma, final o ca) {
        super(frame, s, true);
        this.ya = null;
        this.Ba = false;
        this.Ca = ca;
        this.La = la;
        this.Ma = ma;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        final String b = this.Ca.b("strTimeframeInfo");
        final u u = new u("\n");
        u.m(b);
        final a a = new a(new GridLayout(u.a(), 1, 0, 0), 1, new Insets(5, 5, 5, 5));
        for (int i = 0; i < u.a(); ++i) {
            a.add(new Label(u.b(i)));
        }
        this.add(a, "North");
        this.add(this.ya = this.b(la, ma), "Center");
        this.Aa = new a(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.za = new Button(this.Ca.b("btnOK"));
        final Button button = new Button(this.Ca.b("btnCancel"));
        this.za.addActionListener(this);
        button.addActionListener(this);
        this.Aa.add(this.za);
        this.Aa.add(button);
        this.add(this.Aa, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.za) {
            this.La = this.ya.b();
            this.Ma = this.ya._();
            this.Ba = true;
        }
        this.dispose();
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.dispose();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    private Nj b(final int n, final int n2) {
        final Nj nj = new Nj(this, 2);
        nj._("strIntradayTimeframe", n, 250);
        nj._("strTimeframe", n2, 10);
        return nj;
    }
    
    public int _() {
        return this.Ma;
    }
    
    public int b() {
        return this.La;
    }
    
    public boolean a() {
        return this.Ba;
    }
    
    static o a(final Gj gj) {
        return gj.Ca;
    }
}
