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

public class Jj extends Dialog implements WindowListener, ActionListener
{
    private Mj ya;
    private Button za;
    private a Aa;
    private boolean Ba;
    private o Ca;
    private Frame Da;
    private Ij Ea;
    private Ij Fa;
    private Button Ga;
    
    public Jj(final Frame da, final String s, final Ij ij, final Ij fa, final o ca) {
        super(da, s, true);
        this.ya = null;
        this.Ba = false;
        this.Ea = null;
        this.Fa = null;
        this.Fa = fa;
        this.Ca = ca;
        this.Da = da;
        this.addWindowListener(this);
        this.setLayout(new BorderLayout());
        this.add(this.ya = this._(ij), "Center");
        this.Aa = new a(new FlowLayout(1), 1, new Insets(0, 0, 0, 0));
        this.za = new Button(this.Ca.b("btnOK"));
        final Button button = new Button(this.Ca.b("btnCancel"));
        this.za.addActionListener(this);
        button.addActionListener(this);
        this.Aa.add(this.za);
        this.Aa.add(button);
        if (this.Fa != null) {
            (this.Ga = new Button(this.Ca.b("btnAdditionalSettings"))).addActionListener(this);
            this.Aa.add(this.Ga);
        }
        this.add(this.Aa, "South");
        this.pack();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2, size.width, size.height);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.za) {
            this.Ea = this.ya._();
            this.Ba = true;
            this.dispose();
        }
        else if (actionEvent.getSource() == this.Ga) {
            final Jj jj = new Jj(this.Da, this.Ca.b("strAdditionalChartProperties"), this.Fa, null, this.Ca);
            jj.show();
            if (jj.a()) {
                this.Fa = jj.a();
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
    
    private Mj _(final Ij ij) {
        final String[] b = ij.b();
        final Mj mj = new Mj(this, b.length);
        for (int i = 0; i < b.length; ++i) {
            final String s = b[i];
            mj._(s, ij._(s).getColor(), ij._(s).getWidth());
        }
        return mj;
    }
    
    public Ij a() {
        return this.Ea;
    }
    
    public boolean a() {
        return this.Ba;
    }
    
    public Ij b() {
        return this.Fa;
    }
    
    static o a(final Jj jj) {
        return jj.Ca;
    }
    
    static Frame b(final Jj jj) {
        return jj.Da;
    }
}
