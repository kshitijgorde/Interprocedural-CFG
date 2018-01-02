import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class rp_fG extends rp_bW implements ActionListener
{
    private JPanel a;
    
    public rp_fG(final String s) {
        super(s);
    }
    
    public final void a(final String s, final String actionCommand) {
        final rp_fO rp_fO;
        (rp_fO = new rp_fO(s)).setActionCommand(actionCommand);
        rp_fO.addActionListener(this);
        if ((this = this).a == null) {
            this.a = new JPanel();
        }
        this.a.add(rp_fO);
    }
    
    public final void a(final rp_fb rp_fb) {
        this.a(rp_fb.a(0, "OK"), "O");
        this.a(rp_fb.a(0, "Cancel"), "C");
    }
    
    public final void a() {
        super.a();
        this.getContentPane().add(this.a, "South");
        final JPanel panel = new JPanel();
        this.getContentPane().add(panel, "Center");
        this.a(panel);
        this.pack();
    }
    
    public abstract void a(final JPanel p0);
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if ("O".equals(actionEvent.getActionCommand())) {
            if (this.a()) {
                this.b = true;
                this.dispose();
            }
            return;
        }
        this.dispose();
    }
    
    protected boolean b() {
        if (this.a()) {
            this.b = true;
            this.dispose();
            return true;
        }
        return false;
    }
    
    protected boolean c() {
        this.b = false;
        this.dispose();
        return true;
    }
}
