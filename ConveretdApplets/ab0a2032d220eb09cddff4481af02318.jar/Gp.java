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

public class Gp extends Dialog implements WindowListener, ActionListener
{
    private Jp JDb;
    private Button uDb;
    private Vo wDb;
    private boolean vDb;
    private volatile yDb;
    private Frame tDb;
    private Fp aEb;
    private Fp bEb;
    private Button cEb;
    
    public Gp(final Frame tDb, final String s, final Fp fp, final Fp bEb, final volatile yDb) {
        super(tDb, s, true);
        this.JDb = null;
        this.vDb = false;
        this.aEb = null;
        this.bEb = null;
        this.bEb = bEb;
        this.yDb = yDb;
        this.tDb = tDb;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        this.add(this.JDb = this.a(fp), "Center");
        this.wDb = new Vo(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.uDb = new Button(this.yDb._("btnOK"));
        final Button button = new Button(this.yDb._("btnCancel"));
        this.uDb.addActionListener(this);
        button.addActionListener(this);
        this.wDb.add(this.uDb);
        this.wDb.add(button);
        if (this.bEb != null) {
            (this.cEb = new Button(this.yDb._("btnAdditionalSettings"))).addActionListener(this);
            this.wDb.add(this.cEb);
        }
        this.add(this.wDb, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.uDb) {
            this.aEb = this.JDb.a();
            this.vDb = true;
            this.dispose();
        }
        else if (actionEvent.getSource() == this.cEb) {
            final Gp gp = new Gp(this.tDb, this.yDb._("strAdditionalChartProperties"), this.bEb, null, this.yDb);
            gp.show();
            if (gp.l()) {
                this.bEb = gp.b();
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
    
    private Jp a(final Fp fp) {
        final String[] _ = fp._();
        final Jp jp = new Jp(this, _.length);
        for (int i = 0; i < _.length; ++i) {
            final String s = _[i];
            jp._(s, fp._(s).getColor(), fp._(s).getWidth());
        }
        return jp;
    }
    
    public Fp b() {
        return this.aEb;
    }
    
    public boolean l() {
        return this.vDb;
    }
    
    public Fp _() {
        return this.bEb;
    }
    
    static volatile b(final Gp gp) {
        return gp.yDb;
    }
    
    static Frame b(final Gp gp) {
        return gp.tDb;
    }
}
