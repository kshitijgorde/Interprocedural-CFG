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

public class Op extends Dialog implements WindowListener, ActionListener
{
    private Pp JDb;
    private Button uDb;
    private Vo wDb;
    private boolean vDb;
    private volatile yDb;
    private Frame tDb;
    private int KDb;
    private int LDb;
    
    public Op(final Frame tDb, final String s, final int kDb, final int lDb, final volatile yDb) {
        super(tDb, s, true);
        this.JDb = null;
        this.vDb = false;
        this.yDb = yDb;
        this.tDb = tDb;
        this.KDb = kDb;
        this.LDb = lDb;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        final String _ = this.yDb._("strTimeframeInfo");
        final d d = new d("\n");
        d.e(_);
        final Vo vo = new Vo(new GridLayout(d.z(), 1, 0, 0), 1, new Insets(5, 5, 5, 5));
        for (int i = 0; i < d.z(); ++i) {
            vo.add(new Label(d._(i)));
        }
        this.add(vo, "North");
        this.add(this.JDb = this.a(kDb, lDb), "Center");
        this.wDb = new Vo(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.uDb = new Button(this.yDb._("btnOK"));
        final Button button = new Button(this.yDb._("btnCancel"));
        this.uDb.addActionListener(this);
        button.addActionListener(this);
        this.wDb.add(this.uDb);
        this.wDb.add(button);
        this.add(this.wDb, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.uDb) {
            this.KDb = this.JDb.E();
            this.LDb = this.JDb.F();
            this.vDb = true;
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
    
    private Pp a(final int n, final int n2) {
        final Pp pp = new Pp(this, 2);
        pp._("strIntradayTimeframe", n, 250);
        pp._("strTimeframe", n2, 10);
        return pp;
    }
    
    public int F() {
        return this.LDb;
    }
    
    public int E() {
        return this.KDb;
    }
    
    public boolean l() {
        return this.vDb;
    }
    
    static volatile _(final Op op) {
        return op.yDb;
    }
}
