import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class de extends db implements ActionListener
{
    private dg p;
    
    public de(final y y, final int n, final int n2) {
        super(y, "List of all tables");
        this.setLayout(new BorderLayout());
        (this.p = new dg(y.p())).setBackground(Color.white);
        this.p.p(this);
        this.add("Center", this.p);
        final Panel panel = new Panel();
        final dl dl;
        panel.add(dl = new dl("Join"));
        final dl dl2;
        panel.add(dl2 = new dl("New"));
        final dl dl3;
        panel.add(dl3 = new dl("Update"));
        final dl dl4;
        panel.add(dl4 = new dl("Close"));
        final dl[] array = { dl, dl2, dl3, dl4 };
        for (int i = 0; i < array.length; ++i) {
            array[i].p(this);
            array[i].setFont(dw.i);
        }
        this.add("South", panel);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - n) / 2, (screenSize.height - n2) / 2, n, n2);
    }
    
    public final void d() {
        this.p.d();
    }
    
    public final String p(final String s) {
        return s;
    }
    
    public final void p(final String s) {
        if (s.indexOf("#Idle") != -1 || s.indexOf("#Level") != -1) {
            return;
        }
        p(this.p, this.p(s));
    }
    
    public static final int p(final String s) {
        if (s.indexOf(".#") != -1) {
            return 100;
        }
        if (s.startsWith("O.R") || s.startsWith("O.L")) {
            return 90 + du.p(s.substring(3, s.indexOf(".", 3)), 10);
        }
        if (s.startsWith("X.R") || s.startsWith("X.L")) {
            return 80 + du.p(s.substring(3, s.indexOf(".", 3)), 10);
        }
        return 0;
    }
    
    private static final void p(final dg dg, final String s) {
        final int p2 = p(s);
        for (int i = 0; i < dg.p(); ++i) {
            final int compareTo = dg.p(i).toLowerCase().compareTo(s.toLowerCase());
            final int p3 = p(dg.p(i));
            if (compareTo == 0 && p2 == p3) {
                return;
            }
            if (p2 > p3 || (p2 == p3 && compareTo > 0)) {
                dg.d(s, i);
                return;
            }
        }
        dg.n(s);
    }
    
    private final String d(final String s) {
        final String p = du.p(s, 0, ' ');
        if (p == null) {
            return "error";
        }
        return p.substring(p.lastIndexOf(".") + 1);
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final i p = super.p.p();
        final Object source = actionEvent.getSource();
        final String actionCommand = actionEvent.getActionCommand();
        if (source instanceof dg) {
            this.setVisible(false);
            p.d("JOIN " + this.d(actionCommand));
            return;
        }
        if (actionCommand == "Close") {
            this.setVisible(false);
            return;
        }
        if (actionCommand == "New") {
            this.setVisible(false);
            p.d("OPEN");
            return;
        }
        if (actionCommand == "Update") {
            this.setVisible(false);
            p.d("TABLELIST");
            return;
        }
        if (actionCommand == "Join") {
            final String p2 = this.p.p();
            if (p2 != null) {
                p.d("JOIN " + this.d(p2));
                this.setVisible(false);
            }
        }
        else {
            System.out.println("Unhandle: " + actionEvent);
        }
    }
}
