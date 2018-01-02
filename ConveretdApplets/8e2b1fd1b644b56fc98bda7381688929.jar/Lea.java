import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Component;
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

public class Lea extends Dialog implements WindowListener, ActionListener
{
    private Oea va;
    private Button pa;
    private continue ra;
    private boolean wa;
    private switch xa;
    private Frame ya;
    private Kea za;
    private Kea Aa;
    private Button Ba;
    
    public Lea(final Frame ya, final String s, final Kea kea, final Kea aa, final switch xa) {
        super(ya, s, true);
        this.va = null;
        this.wa = false;
        this.za = null;
        this.Aa = null;
        this.Aa = aa;
        this.xa = xa;
        this.ya = ya;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        this.add(this.va = this._(kea), "Center");
        this.ra = new continue(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.pa = new Button(this.xa.a("btnOK"));
        final Button button = new Button(this.xa.a("btnCancel"));
        this.pa.addActionListener(this);
        button.addActionListener(this);
        this.ra.add(this.pa);
        this.ra.add(button);
        if (this.Aa != null) {
            (this.Ba = new Button(this.xa.a("btnAdditionalSettings"))).addActionListener(this);
            this.ra.add(this.Ba);
        }
        this.add(this.ra, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.pa) {
            this.za = this.va._();
            this.wa = true;
            this.dispose();
        }
        else if (actionEvent.getSource() == this.Ba) {
            final Lea lea = new Lea(this.ya, this.xa.a("strAdditionalChartProperties"), this.Aa, null, this.xa);
            lea.show();
            if (lea._()) {
                this.Aa = lea.a();
            }
        }
        else {
            this.dispose();
        }
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
    
    private Oea _(final Kea kea) {
        final String[] a = kea.a();
        final Oea oea = new Oea(this, a.length);
        for (int i = 0; i < a.length; ++i) {
            final String s = a[i];
            oea._(s, kea._(s).getColor(), kea._(s).getWidth());
        }
        return oea;
    }
    
    public Kea a() {
        return this.za;
    }
    
    public boolean _() {
        return this.wa;
    }
    
    public Kea b() {
        return this.Aa;
    }
    
    static switch _(final Lea lea) {
        return lea.xa;
    }
    
    static Frame _(final Lea lea) {
        return lea.ya;
    }
}
