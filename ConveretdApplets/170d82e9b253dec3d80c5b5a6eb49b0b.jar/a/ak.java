// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Date;
import java.awt.Container;
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

public final class ak extends ah
{
    private g q;
    private cI q;
    private Choice q;
    private Choice w;
    private Choice e;
    private TextField q;
    private dz q;
    private static g w;
    private static Canvas q;
    private Canvas w;
    private static g e;
    private int q;
    private SimpleDateFormat q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.q.r();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    this.dispose();
                    return true;
                }
                if (event.target == ak.w) {
                    final es es;
                    (es = new es(4198448, 1)).w = -1;
                    es.q = -1;
                    es.q(0, 0, this.q.getSelectedIndex());
                    es.q(0, 1, this.w.getSelectedIndex());
                    es.q(0, 2, 0);
                    es.q(0, 0, this.e.getSelectedItem());
                    this.q.e();
                    this.q.q(es);
                    ak.w.e();
                    return true;
                }
                if (event.target == this.w) {
                    this.q(--this.q);
                    if (this.q == 0) {
                        q(this.w, false);
                    }
                    return true;
                }
                if (event.target == ak.q) {
                    this.q(++this.q);
                    if (this.q > 0) {
                        q(this.w, true);
                    }
                    return true;
                }
                if (event.target == ak.e) {
                    this.q(0);
                    ak.e.e();
                    return true;
                }
                if (event.target == this.q) {
                    ak.w.q();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private void q(final int n) {
        final es es;
        (es = new es(4198448, 1)).w = -1;
        es.q = -1;
        es.q(0, 0, this.q.getSelectedIndex());
        es.q(0, 1, this.w.getSelectedIndex());
        es.q(0, 2, n);
        es.q(0, 0, this.e.getSelectedItem());
        es.q(0, 1, this.q.getText());
        this.q.e();
        this.q.q(es);
    }
    
    public ak(final Frame frame, final dz q, final int n) {
        super(frame, eb.q("Chat Server Logs"), true);
        this.q = 0;
        this.q = new SimpleDateFormat("dd MMM yy HH:mm");
        super.q = new g(80, 20);
        this.q = new g(80, 20);
        ak.w = new g(80, 20);
        ak.e = new g(80, 20);
        this.q = new cI(q, false);
        this.q = new Choice();
        this.e = new Choice();
        this.w = new Choice();
        this.q = new TextField(30);
        this.e.add("---");
        for (int i = 0; i < q.b.q(); ++i) {
            this.e.add(((bT)q.b.q(i)).getName());
        }
        this.setBackground(cf.w.q);
        final s s = new s();
        final q q2 = new q();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final Label label = new Label(eb.q("Filter:") + "    ");
        final Label label2 = new Label(eb.q("Chat Server Logs"));
        final Label label3 = new Label(eb.q("Search:"));
        ((Choice)(this.q = q)).addItem("---");
        this.q.addItem(eb.q("Today"));
        this.q.addItem(eb.q("Yesterday"));
        this.q.addItem(eb.q("2 days ago"));
        this.q.addItem(eb.q("3 days ago"));
        this.q.addItem(eb.q("4 days ago"));
        this.q.addItem(eb.q("5 days ago"));
        this.q.addItem(eb.q("6 days ago"));
        this.q.setForeground(Color.black);
        this.w.addItem("---");
        this.w.addItem(eb.q("SITESETTINGS"));
        this.w.addItem(eb.q("COLORNICK"));
        this.w.addItem(eb.q("WARN"));
        this.w.addItem(eb.q("RESTARTSITE"));
        this.w.addItem(eb.q("PRIVATEWRITE"));
        this.w.addItem(eb.q("KICKBOTS"));
        this.w.addItem(eb.q("STARTCOLORDAY"));
        this.w.addItem(eb.q("GIVEKICKTOUSER"));
        this.w.addItem(eb.q("PUBLICWRITE"));
        this.w.addItem(eb.q("ALLIP"));
        this.w.addItem(eb.q("ENDCOLORDAY"));
        this.w.addItem(eb.q("KICKINFO"));
        this.w.addItem(eb.q("CHANGEUSERNAME"));
        this.w.addItem(eb.q("MUSTBAN"));
        this.w.addItem(eb.q("REMOVE"));
        this.w.addItem(eb.q("CHANGEROOM"));
        this.w.setForeground(Color.black);
        final Container container;
        (container = new Panel()).setLayout(layout);
        final t t;
        (t = new t()).setLayout(new FlowLayout());
        this.setResizable(true);
        this.setLayout(layout);
        s.setLayout(layout);
        s.setBackground(cf.w.i);
        s.setForeground(cf.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label2.setFont(m.q);
        layout.setConstraints(label2, gridBagConstraints);
        s.add(label2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(q2, gridBagConstraints);
        s.add(q2);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        label.setFont(m.t);
        t.add(label);
        t.add(this.q, gridBagConstraints);
        t.add(this.w, gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        t.add(this.e, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        ak.w.q(eb.q("View Log"));
        ak.w.t();
        t.add(ak.w);
        layout.setConstraints(t, gridBagConstraints);
        container.add(t);
        final t t2;
        (t2 = new t()).setLayout(new FlowLayout());
        label3.setFont(m.t);
        t2.add(label3);
        t2.add(this.q);
        ak.e.q(eb.q("Search"));
        ak.e.t();
        gridBagConstraints.gridwidth = 0;
        t2.add(ak.e);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(t2, gridBagConstraints);
        container.add(t2);
        final t t3;
        (t3 = new t()).setLayout(new FlowLayout());
        (this.w = dI.q(cf.w.e(), "previous", "previousIcon.GIF", q)).setVisible(false);
        t3.add(this.w);
        (ak.q = dI.q(cf.w.e(), "next", "nextIcon.GIF", q)).setVisible(false);
        t3.add(ak.q);
        gridBagConstraints.anchor = 15;
        gridBagConstraints.gridwidth = 0;
        container.add(t3, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        s.add(container, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final t t4 = new t(this.q);
        layout.setConstraints(t4, gridBagConstraints);
        s.add(t4);
        layout.setConstraints(s, gridBagConstraints);
        this.add(s);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.q.q(eb.q("Close"));
        this.q.t();
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.q.setSize(325, 208);
        this.setModal(false);
        this.pack();
        this.w();
    }
    
    public final void q(final es es) {
        boolean b = false;
        for (int i = 0; i < es.w(); ++i) {
            if (es.q(i, 0).equalsIgnoreCase(eb.q("No data found"))) {
                q(ak.q, false);
                b = true;
            }
            final String q = es.q(i, 0);
            final String[] q2;
            String substring;
            String q3;
            String format;
            if ((q2 = dV.q(q, "\n")).length < 2) {
                substring = q;
                q3 = eb.q("System");
                format = this.q.format(new Date());
            }
            else {
                String substring2;
                final int index;
                if ((index = (substring2 = q2[1]).indexOf(":")) < 2) {
                    substring2 = substring2.substring(index + 1);
                }
                substring = q.substring(q.indexOf(substring2));
                final String[] q4;
                q3 = (q4 = dV.q(q2[0], "\t\t"))[1];
                format = q4[0];
            }
            dj dj = null;
            for (int j = 0; j < this.q.e.q(); ++j) {
                final dj dj2;
                if ((dj2 = (dj)this.q.e.q(j)).getName().equals(q3)) {
                    dj = dj2;
                    break;
                }
            }
            if (dj == null) {
                (dj = new dj(-999, q3)).e = -1;
                final bT bt;
                if ((bt = (bT)this.q.b.w(bJ.q(q3, this.q.b))) != null) {
                    dj.y(bt.y());
                    final cl cl;
                    if ((cl = (cl)this.q.x.w(bt.q)) != null) {
                        dj.p = cl.q;
                    }
                    else {
                        dj.p = -1;
                    }
                }
                else {
                    dj.y(2125055);
                    dj.p = -1;
                }
            }
            final StringBuffer sb;
            (sb = new StringBuffer()).append(substring);
            final cS cs;
            (cs = new cS(sb.toString(), dj, false, false, (cm)this.q.w.w(dj.e), null)).q(format);
            this.q.q(cs);
        }
        ak.w.q();
        ak.e.q();
        if (!b) {
            q(ak.q, true);
        }
    }
    
    private static void q(final Component component, final boolean visible) {
        component.setVisible(visible);
        component.getParent().getParent().invalidate();
        component.getParent().getParent().validate();
    }
}
