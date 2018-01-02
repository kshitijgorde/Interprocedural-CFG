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

public class Iea extends Dialog implements WindowListener, ActionListener
{
    private Rea va;
    private Button pa;
    private continue ra;
    private boolean wa;
    private switch xa;
    private int Ga;
    private int Ha;
    
    public Iea(final Frame frame, final String s, final int ga, final int ha, final switch xa) {
        super(frame, s, true);
        this.va = null;
        this.wa = false;
        this.xa = xa;
        this.Ga = ga;
        this.Ha = ha;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        final String a = this.xa.a("strTimeframeInfo");
        final try try1 = new try("\n");
        try1.l(a);
        final continue continue1 = new continue(new GridLayout(try1.g(), 1, 0, 0), 1, new Insets(5, 5, 5, 5));
        for (int i = 0; i < try1.g(); ++i) {
            continue1.add(new Label(try1.a(i)));
        }
        this.add(continue1, "North");
        this.add(this.va = this.a(ga, ha), "Center");
        this.ra = new continue(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.pa = new Button(this.xa.a("btnOK"));
        final Button button = new Button(this.xa.a("btnCancel"));
        this.pa.addActionListener(this);
        button.addActionListener(this);
        this.ra.add(this.pa);
        this.ra.add(button);
        this.add(this.ra, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.pa) {
            this.Ga = this.va.e();
            this.Ha = this.va.f();
            this.wa = true;
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
    
    private Rea a(final int n, final int n2) {
        final Rea rea = new Rea(this, 2);
        rea._("strIntradayTimeframe", n, 250);
        rea._("strTimeframe", n2, 10);
        return rea;
    }
    
    public int f() {
        return this.Ha;
    }
    
    public int e() {
        return this.Ga;
    }
    
    public boolean _() {
        return this.wa;
    }
    
    static switch b(final Iea iea) {
        return iea.xa;
    }
}
