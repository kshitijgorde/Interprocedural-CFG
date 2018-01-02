import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class da extends db implements ActionListener
{
    private String p;
    private dl d;
    private dl a;
    private dl n;
    private dl v;
    private dl i;
    private dl l;
    private dl b;
    private dl c;
    private Container e;
    
    public da(final y y) {
        super(y, "Action");
        this.setBackground(Color.white);
        this.p(this.d = new dl("Mute"));
        this.p(new di(0, 2, Color.blue));
        this.p(this.a = new dl("Query"));
        this.p(this.n = new dl("Private chat"));
        this.p(this.v = new dl("Invite"));
        this.p(new di(0, 2, Color.blue));
        this.p(this.i = new dl("Eject"));
        this.p(this.l = new dl("Block"));
        this.p(new di(0, 2, Color.blue));
        this.p(this.b = new dl("Unmute"));
        this.p(this.c = new dl("List all mutes"));
        this.d.p(this);
        this.a.p(this);
        this.n.p(this);
        this.v.p(this);
        this.i.p(this);
        this.l.p(this);
        this.b.p(this);
        this.c.p(this);
        this.pack();
    }
    
    public final void addNotify() {
        super.addNotify();
        this.d.setFont(new Font(this.getFont().getName(), 1, this.getFont().getSize()));
    }
    
    private final void p(final Component component) {
        if (this.e == null) {
            this.e = this;
        }
        this.e.setLayout(new BorderLayout());
        final Panel e = new Panel();
        this.e.add(component, "North");
        this.e.add(e, "Center");
        this.e = e;
    }
    
    public final void setName(final String p) {
        this.setTitle(this.p = p);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final i p = super.p.p();
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand == "Query") {
            this.setVisible(false);
            p.d("WHO " + this.p);
            return;
        }
        if (actionCommand == "Private chat") {
            this.setVisible(false);
            super.p.a(this.p);
            return;
        }
        if (actionCommand == "Invite") {
            this.setVisible(false);
            p.d("INVITE " + this.p);
            return;
        }
        if (actionCommand == "Eject") {
            this.setVisible(false);
            p.d("EJECT " + this.p);
            return;
        }
        if (actionCommand == "Block") {
            this.setVisible(false);
            p.d("BLOCK " + this.p);
            return;
        }
        if (actionCommand == "Mute") {
            this.setVisible(false);
            super.p.p(this.p);
            return;
        }
        if (actionCommand == "Unmute") {
            this.setVisible(false);
            super.p.d(this.p);
            return;
        }
        if (actionCommand == "List all mutes") {
            this.setVisible(false);
            super.p.n();
        }
    }
}
