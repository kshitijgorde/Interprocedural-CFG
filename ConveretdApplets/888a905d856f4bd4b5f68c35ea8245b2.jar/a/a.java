// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Date;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Event;
import java.text.SimpleDateFormat;
import java.awt.Canvas;
import java.awt.TextField;
import java.awt.Choice;

public final class a extends F
{
    private ad e;
    public dl q;
    private Choice q;
    private Choice w;
    private Choice e;
    private TextField q;
    public cT q;
    public static ad q;
    public static Canvas q;
    private Canvas w;
    public static ad w;
    private int q;
    public SimpleDateFormat q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.e.r();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.e) {
                    this.dispose();
                    return true;
                }
                if (event.target == a.q) {
                    final dI di;
                    (di = new dI(4198448, 1)).w = -1;
                    di.q = -1;
                    di.q(0, 0, this.q.getSelectedIndex());
                    di.q(0, 1, this.w.getSelectedIndex());
                    di.q(0, 2, 0);
                    di.q(0, 0, this.e.getSelectedItem());
                    this.q.e();
                    this.q.o(di);
                    a.q.e();
                    return true;
                }
                if (event.target == this.w) {
                    this.q(--this.q);
                    if (this.q == 0) {
                        q(this.w, false);
                    }
                    return true;
                }
                if (event.target == a.q) {
                    this.q(++this.q);
                    if (this.q > 0) {
                        q(this.w, true);
                    }
                    return true;
                }
                if (event.target == a.w) {
                    this.q(0);
                    a.w.e();
                    return true;
                }
                if (event.target == this.q) {
                    a.q.q();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private void q(final int n) {
        final dI di;
        (di = new dI(4198448, 1)).w = -1;
        di.q = -1;
        di.q(0, 0, this.q.getSelectedIndex());
        di.q(0, 1, this.w.getSelectedIndex());
        di.q(0, 2, n);
        di.q(0, 0, this.e.getSelectedItem());
        di.q(0, 1, this.q.getText());
        this.q.e();
        this.q.o(di);
    }
    
    public a(final Frame frame, final cT q, final int n) {
        super(frame, be.w("Chat Server Logs"), true);
        this.q = 0;
        this.q = new SimpleDateFormat("dd MMM yy HH:mm");
        super.q = new ad(80, 20);
        this.e = new ad(80, 20);
        a.q = new ad(80, 20);
        a.w = new ad(80, 20);
        this.q = new dl(q, false);
        this.q = new Choice();
        this.e = new Choice();
        this.w = new Choice();
        this.q = new TextField(30);
        this.e.add("---");
        for (int i = 0; i < q.e.q; ++i) {
            this.e.add(((cu)q.e.q(i)).a);
        }
        this.setBackground(bC.w.q);
        final dT dt = new dT();
        final q q2 = new q();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final Label label = new Label(be.w("Filter:") + "    ");
        final Label label2 = new Label(be.w("Chat Server Logs"));
        final Label label3 = new Label(be.w("Search:"));
        ((Choice)(this.q = q)).addItem("---");
        this.q.addItem(be.w("Today"));
        this.q.addItem(be.w("Yesterday"));
        this.q.addItem(be.w("2 days ago"));
        this.q.addItem(be.w("3 days ago"));
        this.q.addItem(be.w("4 days ago"));
        this.q.addItem(be.w("5 days ago"));
        this.q.addItem(be.w("6 days ago"));
        this.q.setForeground(Color.black);
        this.w.addItem("---");
        this.w.addItem(be.w("SITESETTINGS"));
        this.w.addItem(be.w("COLORNICK"));
        this.w.addItem(be.w("WARN"));
        this.w.addItem(be.w("RESTARTSITE"));
        this.w.addItem(be.w("PRIVATEWRITE"));
        this.w.addItem(be.w("KICKBOTS"));
        this.w.addItem(be.w("STARTCOLORDAY"));
        this.w.addItem(be.w("GIVEKICKTOUSER"));
        this.w.addItem(be.w("PUBLICWRITE"));
        this.w.addItem(be.w("ALLIP"));
        this.w.addItem(be.w("ENDCOLORDAY"));
        this.w.addItem(be.w("KICKINFO"));
        this.w.addItem(be.w("CHANGEUSERNAME"));
        this.w.addItem(be.w("MUSTBAN"));
        this.w.addItem(be.w("REMOVE"));
        this.w.addItem(be.w("CHANGEROOM"));
        this.w.setForeground(Color.black);
        final Panel panel;
        (panel = new Panel()).setLayout(layout);
        final bZ bz;
        (bz = new bZ()).setLayout(new FlowLayout());
        this.setResizable(true);
        this.setLayout(layout);
        dt.setLayout(layout);
        dt.setBackground(bC.w.i);
        dt.setForeground(bC.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label2.setFont(cb.q);
        layout.setConstraints(label2, gridBagConstraints);
        dt.add(label2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(q2, gridBagConstraints);
        dt.add(q2);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        label.setFont(cb.t);
        bz.add(label);
        bz.add(this.q, gridBagConstraints);
        bz.add(this.w, gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        bz.add(this.e, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        a.q.q(be.w("View Log"));
        a.q.t();
        bz.add(a.q);
        layout.setConstraints(bz, gridBagConstraints);
        panel.add(bz);
        final bZ bz2;
        (bz2 = new bZ()).setLayout(new FlowLayout());
        label3.setFont(cb.t);
        bz2.add(label3);
        bz2.add(this.q);
        a.w.q(be.w("Search"));
        a.w.t();
        gridBagConstraints.gridwidth = 0;
        bz2.add(a.w);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(bz2, gridBagConstraints);
        panel.add(bz2);
        final bZ bz3;
        (bz3 = new bZ()).setLayout(new FlowLayout());
        (this.w = bi.q(bC.w.r(), "previous", "previousIcon.GIF", q)).setVisible(false);
        bz3.add(this.w);
        (a.q = bi.q(bC.w.r(), "next", "nextIcon.GIF", q)).setVisible(false);
        bz3.add(a.q);
        gridBagConstraints.anchor = 15;
        gridBagConstraints.gridwidth = 0;
        panel.add(bz3, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        dt.add(panel, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final bZ bz4 = new bZ(this.q);
        layout.setConstraints(bz4, gridBagConstraints);
        dt.add(bz4);
        layout.setConstraints(dt, gridBagConstraints);
        this.add(dt);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.e.q(be.w("Close"));
        this.e.t();
        layout.setConstraints(this.e, gridBagConstraints);
        this.add(this.e);
        this.q.setSize(325, 208);
        this.setModal(false);
        this.pack();
        this.w();
    }
    
    public final void q(final dI di) {
        boolean b = false;
        for (int i = 0; i < di.w(); ++i) {
            if (di.q(i, 0).equalsIgnoreCase(be.w("No data found"))) {
                q(a.q, false);
                b = true;
            }
            final String q = di.q(i, 0);
            final String[] q2;
            String substring;
            String w;
            String format;
            if ((q2 = ds.q(q, "\n")).length < 2) {
                substring = q;
                w = be.w("System");
                format = this.q.format(new Date());
            }
            else {
                String substring2;
                final int index;
                if ((index = (substring2 = q2[1]).indexOf(":")) < 2) {
                    substring2 = substring2.substring(index + 1);
                }
                substring = q.substring(q.indexOf(substring2));
                final String[] q3;
                w = (q3 = ds.q(q2[0], "\t\t"))[1];
                format = q3[0];
            }
            aO ao = null;
            for (int j = 0; j < this.q.a.q; ++j) {
                final aO ao2;
                if ((ao2 = (aO)this.q.a.q(j)).a.equals(w)) {
                    ao = ao2;
                    break;
                }
            }
            if (ao == null) {
                (ao = new aO(-999, w)).e = -1;
                final cu cu;
                if ((cu = (cu)this.q.e.w(aY.q(w, this.q.e))) != null) {
                    ao.p(cu.w());
                    final de de;
                    if ((de = (de)this.q.y.w(cu.q)) != null) {
                        ao.t = de.q;
                    }
                    else {
                        ao.t = -1;
                    }
                }
                else {
                    ao.p(2125055);
                    ao.t = -1;
                }
            }
            final StringBuffer sb;
            (sb = new StringBuffer()).append(substring);
            final A a;
            (a = new A(sb.toString(), ao, false, false, (aZ)this.q.p.w(ao.e), null)).q(format);
            this.q.q(a);
        }
        a.q.q();
        a.w.q();
        if (!b) {
            q(a.q, true);
        }
    }
    
    public static void q(final Component component, final boolean visible) {
        component.setVisible(visible);
        component.getParent().getParent().invalidate();
        component.getParent().getParent().validate();
    }
}
