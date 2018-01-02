import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_al extends rp_bW implements ActionListener
{
    private rp_df a;
    public rp_dg a;
    
    public rp_al(final rp_au rp_au, final rp_dW rp_dW) {
        super(rp_au.a("RW_Page1"));
        this.a = null;
        this.a();
        final rp_aW rp_aW2;
        final rp_aW rp_aW = rp_aW2 = new rp_aW(this, this);
        final String a = rp_au.a("Prev");
        final String a2 = rp_au.a("Next");
        final String a3 = rp_au.a("Fin");
        final String a4 = rp_au.a("Cancel");
        final String c = a3;
        final String b = a2;
        final String a5 = a;
        final rp_aW rp_aW3 = rp_aW;
        rp_aW.a = a5;
        rp_aW3.b = b;
        rp_aW3.c = c;
        rp_aW3.d = a4;
        rp_aW2.a = rp_aJ.d;
        this.a = new rp_dg(rp_dW);
        (this.a = new rp_df(this, rp_au, this.a, rp_dW)).setName(rp_au.a("RW_Page1"));
        this.a.setName(rp_au.a("RW_Page2"));
        rp_aW2.a(this.a);
        rp_aW2.a(this.a);
        final Container contentPane = this.getContentPane();
        final rp_aW rp_aW4;
        if ((rp_aW4 = rp_aW2).a == null) {
            rp_aW4.a = new JPanel();
            (rp_aW4.a = new rp_fO("< " + rp_aW4.a)).setActionCommand("Prv");
            rp_aW4.a.addActionListener(rp_aW4);
            rp_aW4.a.setForeground(rp_aW4.a);
            rp_aW4.a.add(rp_aW4.a);
            rp_aW4.a.setVisible(false);
            (rp_aW4.b = new rp_fO(rp_aW4.b + " >")).setActionCommand("Nxt");
            rp_aW4.b.addActionListener(rp_aW4);
            rp_aW4.b.setForeground(rp_aW4.a);
            rp_aW4.a.add(rp_aW4.b);
            (rp_aW4.d = new rp_fO(rp_aW4.c)).setActionCommand("Fin");
            rp_aW4.d.addActionListener(rp_aW4);
            rp_aW4.d.setForeground(rp_aW4.a);
            rp_aW4.a.add(rp_aW4.d);
            rp_aW4.d.setVisible(false);
            (rp_aW4.c = new rp_fO(rp_aW4.d)).setActionCommand("Cnl");
            rp_aW4.c.addActionListener(rp_aW4);
            rp_aW4.c.setForeground(rp_aW4.a);
            rp_aW4.a.add(rp_aW4.c);
        }
        contentPane.add(rp_aW4.a, "South");
        rp_aW2.a(0);
        this.pack();
        this.b();
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Cnl")) {
            this.dispose();
            return;
        }
        if (actionEvent.getActionCommand().equals("Fin")) {
            if (this.a()) {
                this.dispose();
            }
        }
    }
    
    public final boolean a() {
        if (!this.a.a()) {
            return false;
        }
        super.a();
        return true;
    }
    
    protected final boolean b() {
        if (this.a()) {
            this.b = true;
            this.dispose();
            return true;
        }
        return false;
    }
    
    protected final boolean c() {
        this.b = false;
        this.dispose();
        return true;
    }
}
