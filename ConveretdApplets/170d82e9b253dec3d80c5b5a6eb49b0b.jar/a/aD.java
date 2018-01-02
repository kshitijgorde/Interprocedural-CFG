// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Image;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Panel;
import java.text.SimpleDateFormat;
import java.awt.Frame;
import java.awt.TextField;

public final class aD extends ah
{
    private static final String q;
    private static final String w;
    private g q;
    private g w;
    private TextField q;
    private U q;
    private TextField w;
    private cV q;
    
    public aD(final Frame frame, final cV q) {
        super(frame, ec.q(eb.q(aD.q), eb.q("...")), false);
        new SimpleDateFormat("dd MMM yy HH:mm");
        this.q = q;
        this.q = new g(80, 20);
        this.w = new g(80, 20);
        this.setBackground(cf.w.q);
        final s s = new s();
        final Panel panel = new Panel();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setResizable(true);
        this.setLayout(layout);
        s.setLayout(layout);
        s.setBackground(cf.w.i);
        s.setForeground(cf.w.u);
        panel.setLayout(layout);
        panel.setBackground(cf.w.i);
        panel.setForeground(cf.w.u);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        panel.add(new Label(eb.q(aD.w)), gridBagConstraints);
        this.q = new TextField(50);
        gridBagConstraints.gridwidth = 0;
        panel.add(this.q, gridBagConstraints);
        final Label label = new Label(eb.q(dV.q("x5CC175e")));
        gridBagConstraints.gridwidth = 1;
        panel.add(label, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        panel.add(this.q = new aE(this, this.q), gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        panel.add(this.w = new TextField(3), gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(panel, gridBagConstraints);
        s.add(panel);
        gridBagConstraints.gridwidth = 0;
        final Image r;
        if ((r = this.q.r("postbox.gif", false)) != null) {
            final d d;
            (d = new d()).w(r);
            s.add(d, gridBagConstraints);
        }
        this.add(s, gridBagConstraints);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.w.q(eb.q("Close"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(eb.q("Send"));
        this.q.t();
        final f f = new f(this.q);
        layout.setConstraints(f, gridBagConstraints);
        this.add(f);
        this.pack();
        this.w();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.target == this.w) {
                    return true;
                }
                if (event.target == this.q) {
                    this.setTitle(ec.q(eb.q(aD.q), this.q.getText()));
                }
            }
            case 1001: {
                if (event.target == this.q || event.target == this.w) {
                    this.dispose();
                    return true;
                }
                if (event.target != this.q) {
                    break;
                }
                final String text = this.q.getText();
                final String q = this.q.q(false);
                if (text == null || text.trim().length() == 0) {
                    new b(this.q.q(), eb.q("Error"), eb.q("Master name should be set"), this.q).setVisible(true);
                    this.q.requestFocus();
                    return true;
                }
                if (q == null || q.trim().length() == 0) {
                    new b(this.q.q(), eb.q("Error"), eb.q("Message should be entered"), this.q).setVisible(true);
                    this.q.q().requestFocus();
                    return true;
                }
                final es es;
                (es = new es(4202528, 1)).q = -1;
                es.w = -1;
                es.q(0, 0, 1);
                es.q(0, 2, this.q.q().getForeground().getRGB());
                es.q(0, 0, text);
                es.q(0, 1, q);
                this.q.q(es);
                this.dispose();
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    static TextField q(final aD ad) {
        return ad.w;
    }
    
    static {
        q = dV.q("\u001f66<9>5K=5CC175KD?KP\\");
        w = dV.q("%C5BK>1=5e");
    }
}
