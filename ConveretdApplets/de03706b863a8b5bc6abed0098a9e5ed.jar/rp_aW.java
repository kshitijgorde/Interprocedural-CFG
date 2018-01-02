import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_aW implements ActionListener
{
    public Color a;
    public String a;
    public String b;
    public String c;
    public String d;
    private Vector a;
    public JPanel a;
    private JPanel b;
    private int a;
    private int b;
    public rp_fO a;
    public rp_fO b;
    public rp_fO c;
    public rp_fO d;
    private rp_bW a;
    private ActionListener a;
    
    public rp_aW(final rp_bW a, final ActionListener a2) {
        this.a = Color.black;
        this.a = new Vector();
        this.a = null;
        this.b = null;
        this.a = 0;
        this.b = 0;
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.a = null;
        this.a = null;
        this.a = a;
        this.a = a2;
        this.a(0);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Cnl")) {
            this.a("Cnl");
            return;
        }
        if (actionEvent.getActionCommand().equals("Fin")) {
            this.a("Fin");
            return;
        }
        if (!actionEvent.getActionCommand().equals("Prv")) {
            if (actionEvent.getActionCommand().equals("Nxt")) {
                this.a("Nxt");
                this.a(++this.b);
                this.a.setVisible(true);
                if (this.b == this.a - 1) {
                    this.b.setVisible(false);
                    this.d.setVisible(true);
                    this.d.requestFocus();
                    return;
                }
                this.d.setVisible(false);
            }
            return;
        }
        this.a("Prv");
        this.a(--this.b);
        this.b.setVisible(true);
        this.d.setVisible(true);
        if (this.b == 0) {
            this.a.setVisible(false);
            return;
        }
        this.a.setVisible(true);
    }
    
    public final void a(final JPanel panel) {
        this.a.addElement(panel);
        ++this.a;
    }
    
    private void a(final String s) {
        if (this.a != null) {
            this.a.actionPerformed(new ActionEvent(this, 1001, s));
        }
    }
    
    public final void a(final int n) {
        if (this.b != null) {
            this.a.remove(this.b);
        }
        if (this.a.size() > 0) {
            final JPanel b = this.a.elementAt(n);
            this.a.setTitle(b.getName());
            b.setVisible(true);
            this.a.getContentPane().add(b, "Center");
            this.a.pack();
            this.a.validate();
            this.a.repaint();
            b.repaint();
            this.b = b;
        }
    }
}
