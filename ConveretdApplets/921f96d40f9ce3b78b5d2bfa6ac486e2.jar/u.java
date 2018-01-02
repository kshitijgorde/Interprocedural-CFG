import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.util.Vector;
import java.awt.Label;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class u extends db implements ActionListener
{
    private String p;
    private boolean d;
    private Label a;
    private Vector n;
    private dl v;
    private dl i;
    private dl l;
    private dl b;
    private dl c;
    private Panel e;
    private Panel f;
    
    public u(final y y) {
        super(y, "Invitation");
        this.n = new Vector();
        this.e = new Panel();
        this.f = new Panel();
        this.setLayout(new BorderLayout());
        this.add(this.f, "Center");
        this.add(this.e, "South");
        this.f.setLayout(new FlowLayout(0));
        this.f.add(this.a = new Label("Invitation message"));
        this.a.setFont(dw.i);
        this.v = new dl("Cancel");
        this.i = new dl("Accept");
        this.l = new dl("Deny");
        this.b = new dl("Always Deny");
        this.c = new dl("No Invitation");
        final dl[] array = { this.v, this.i, this.l, this.b, this.c };
        for (int i = 0; i < array.length; ++i) {
            array[i].p(this);
        }
    }
    
    public final void p(final String s) {
        this.setVisible(false);
        this.d = false;
        this.p = null;
    }
    
    public final void p(final String s, final boolean b) {
        if (s == null) {
            return;
        }
        final i p2 = super.p.p();
        if (b) {
            if (this.n.contains(s.toLowerCase()) || super.p.p().b() || super.p.p().d(s)) {
                p2.d("DENY " + s);
                return;
            }
            if (this.p != null && this.isShowing()) {
                p2.d("DENY " + s);
                return;
            }
            this.setVisible(false);
            this.p = s;
            this.d = b;
            this.setTitle("Invitation from " + s);
            this.a.setText(String.valueOf(s) + " is inviting you for a game");
            this.a.invalidate();
            this.a.getParent().invalidate();
            this.invalidate();
            this.e.removeAll();
            this.e.add(this.i);
            this.e.add(this.l);
            this.e.add(this.b);
            this.e.add(this.c);
            this.l();
            this.pack();
        }
        else {
            this.setVisible(false);
            if (this.p != null) {
                if (this.d) {
                    p2.d("DENY " + this.p);
                }
                else {
                    p2.d("UNINVITE " + this.p);
                }
            }
            this.p = s;
            this.d = b;
            this.setTitle("Inviting " + s);
            this.a.setText("You are inviting " + s + "...");
            this.a.invalidate();
            this.a.getParent().invalidate();
            this.invalidate();
            this.e.removeAll();
            this.e.add(this.v);
            this.l();
            this.pack();
        }
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - this.getSize().width) / 2, (screenSize.height - this.getSize().height) / 2, this.getSize().width, this.getSize().height);
        this.show();
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        if (this.p != null) {
            super.p.p().d(String.valueOf(this.d ? "DENY " : "UNINVITE ") + this.p);
            this.p = null;
        }
        this.setVisible(false);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        final i p = super.p.p();
        if (actionCommand == "Cancel" && !this.d) {
            p.d("UNINVITE " + this.p);
            this.p = null;
            this.setVisible(false);
            return;
        }
        if (actionCommand == "Accept" && this.d) {
            p.d("ACCEPT " + this.p);
            this.p = null;
            this.setVisible(false);
            return;
        }
        if (actionCommand == "Deny" && this.d) {
            p.d("DENY " + this.p);
            this.p = null;
            this.setVisible(false);
            return;
        }
        if (actionCommand == "Always Deny" && this.d) {
            p.d("DENY " + this.p);
            this.n.addElement(this.p.toLowerCase());
            this.p = null;
            this.setVisible(false);
            return;
        }
        if (actionCommand == "No Invitation" && this.d) {
            p.d("DENY " + this.p);
            super.p.p().n();
            this.p = null;
            this.setVisible(false);
        }
    }
}
