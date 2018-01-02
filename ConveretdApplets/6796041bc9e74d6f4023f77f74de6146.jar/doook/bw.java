// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Panel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Event;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.TextArea;

public final class bw extends p
{
    private aS b;
    private static TextArea d;
    private Choice d;
    private Choice w;
    private Choice t;
    private TextField A;
    private u e;
    public static cD c;
    public static aS u;
    public static aS v;
    public static aS w;
    public static int aD;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.b.s();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.b) {
                    this.dispose();
                    return true;
                }
                if (event.target == bw.u) {
                    final cD cd = new cD(262656, 1);
                    cd.o = -1;
                    cd.j = -1;
                    cd.a(0, 0, this.d.getSelectedIndex());
                    cd.a(0, 1, this.w.getSelectedIndex());
                    cd.a(0, 0, this.t.getSelectedItem());
                    cd.a(0, 1, this.A.getText());
                    bw.u.d();
                    bw.v.d();
                    bw.w.d();
                    bw.aD = 0;
                    this.e.o(cd);
                    return true;
                }
                if (event.target == bw.v) {
                    ++bw.aD;
                    w();
                    return true;
                }
                if (event.target == bw.w) {
                    --bw.aD;
                    w();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public static void w() {
        bw.u.c();
        bw.d.setText("");
        if (bw.aD == 0) {
            bw.w.d();
        }
        else {
            bw.w.c();
        }
        int n = bw.aD * 10;
        for (int n2 = 0; n < bw.c.g() && n2 < 10; ++n, ++n2) {
            bw.d.append(bw.c.a(n, 0));
            bw.d.append("\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        }
        if (n >= bw.c.g()) {
            bw.v.d();
        }
        else {
            bw.v.c();
        }
    }
    
    public bw(final Frame frame, final u e) {
        super(frame, ao.e("Chat Server Logs"), true);
        this.e = e;
        super.a = new aS(80, 20);
        this.b = new aS(80, 20);
        this.setBackground(e.a.a);
        final cA ca = new cA();
        final cH ch = new cH();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setResizable(true);
        this.setLayout(gridBagLayout);
        ca.setLayout(gridBagLayout);
        ca.setBackground(e.a.g);
        ca.setForeground(e.a.f);
        final Label label = new Label(ao.e("Chat Server Logs"));
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label.setFont(bL.e);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        ca.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(ch, gridBagConstraints);
        ca.add(ch);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        final Label label2 = new Label(ao.e("Day:"));
        label2.setFont(bL.g);
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        ca.add(label2);
        (this.d = new Choice()).addItem(ao.e("Today"));
        this.d.addItem(ao.e("Yesterday"));
        this.d.addItem(ao.e("2 days ago"));
        this.d.addItem(ao.e("3 days ago"));
        this.d.addItem(ao.e("4 days ago"));
        this.d.addItem(ao.e("5 days ago"));
        this.d.addItem(ao.e("6 days ago"));
        this.d.setForeground(Color.black);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.d, gridBagConstraints);
        ca.add(this.d);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        final Label label3 = new Label(ao.e("Type:"));
        label3.setFont(bL.g);
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        ca.add(label3);
        (this.w = new Choice()).addItem(ao.e(" "));
        this.w.addItem(ao.e("Change colors, Display names, and Rooms"));
        this.w.addItem(ao.e("Edit bands, Kick  users, and view all on IP"));
        this.w.addItem(ao.e("Enable and disable writing in public"));
        this.w.addItem(ao.e("Login and Logout"));
        this.w.addItem(ao.e("Edit site options"));
        this.w.addItem(ao.e("Create and edit rooms"));
        this.w.addItem(ao.e("Edit accounts"));
        this.w.addItem(ao.e("Send broadcasts and warnings"));
        this.w.addItem(ao.e("Oncekick feature"));
        this.w.addItem(ao.e("Prohibited Nicknames"));
        this.w.setForeground(Color.black);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        ca.add(this.w);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        final Label label4 = new Label(ao.e("User:"));
        label4.setFont(bL.g);
        gridBagLayout.setConstraints(label4, gridBagConstraints);
        ca.add(label4);
        (this.t = new Choice()).addItem(ao.e(" "));
        this.t.addItem(ao.e("System"));
        this.t.addItem(ao.e("Admin"));
        this.e.g.a(false);
        try {
            for (int i = 0; i < this.e.g.b(); ++i) {
                this.t.addItem(((cF)this.e.g.a(i)).f());
            }
        }
        finally {
            this.e.g.a();
        }
        this.t.setForeground(Color.black);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.t, gridBagConstraints);
        ca.add(this.t);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        final Label label5 = new Label(ao.e("Filter:"));
        label5.setFont(bL.g);
        gridBagLayout.setConstraints(label5, gridBagConstraints);
        ca.add(label5);
        (this.A = new TextField("", 40)).setForeground(Color.black);
        this.A.setBackground(Color.white);
        gridBagLayout.setConstraints(this.A, gridBagConstraints);
        ca.add(this.A);
        bw.u = new aS(80, 20);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        bw.u.a(ao.e("View Log"));
        bw.u.t();
        gridBagLayout.setConstraints(bw.u, gridBagConstraints);
        ca.add(bw.u);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        bw.d = new TextArea("", 20, 150, 1);
        final aR ar = new aR(bw.d);
        gridBagLayout.setConstraints(ar, gridBagConstraints);
        ca.add(ar);
        final Panel panel = new Panel(new GridBagLayout());
        (bw.w = new aS(100, 20)).a(ao.e("Previous"));
        bw.w.d();
        panel.add(bw.w);
        (bw.v = new aS(100, 20)).a(ao.e("Next"));
        bw.v.d();
        panel.add(bw.v);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        ca.add(panel);
        gridBagLayout.setConstraints(ca, gridBagConstraints);
        this.add(ca);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.b.a(ao.e("Close"));
        this.b.t();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        this.pack();
        bw.d.requestFocus();
    }
}
