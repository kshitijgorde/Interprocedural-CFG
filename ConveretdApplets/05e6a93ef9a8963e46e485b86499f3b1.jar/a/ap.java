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

public final class ap extends w
{
    private static final String q;
    private static final String w;
    private N q;
    private N w;
    private TextField q;
    private cg q;
    private TextField w;
    private W q;
    
    public ap(final Frame frame, final W q) {
        super(frame, t.q(al.q(ap.q), al.q("...")), false);
        new SimpleDateFormat("dd MMM yy HH:mm");
        this.q = q;
        this.q = new N(80, 20);
        this.w = new N(80, 20);
        this.setBackground(aT.w.q);
        final cv cv = new cv();
        final Panel panel = new Panel();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setResizable(true);
        this.setLayout(layout);
        cv.setLayout(layout);
        cv.setBackground(aT.w.i);
        cv.setForeground(aT.w.u);
        panel.setLayout(layout);
        panel.setBackground(aT.w.i);
        panel.setForeground(aT.w.u);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        panel.add(new Label(al.q(ap.w)), gridBagConstraints);
        this.q = new TextField(50);
        gridBagConstraints.gridwidth = 0;
        panel.add(this.q, gridBagConstraints);
        final Label label = new Label(al.q(ce.q("x5CC175e")));
        gridBagConstraints.gridwidth = 1;
        panel.add(label, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        panel.add(this.q = new by(this, this.q), gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        panel.add(this.w = new TextField(3), gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(panel, gridBagConstraints);
        cv.add(panel);
        gridBagConstraints.gridwidth = 0;
        final Image e;
        if ((e = this.q.e("postbox.gif", false)) != null) {
            final aH ah;
            (ah = new aH()).w(e);
            cv.add(ah, gridBagConstraints);
        }
        this.add(cv, gridBagConstraints);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.w.q(al.q("Close"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(al.q("Send"));
        this.q.t();
        final ab ab = new ab(this.q);
        layout.setConstraints(ab, gridBagConstraints);
        this.add(ab);
        this.pack();
        this.q();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.target == this.w) {
                    return true;
                }
                if (event.target == this.q) {
                    this.setTitle(t.q(al.q(ap.q), this.q.getText()));
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
                    new p(this.q.q(), al.q("Error"), al.q("Master name should be set"), this.q).setVisible(true);
                    this.q.requestFocus();
                    return true;
                }
                if (q == null || q.trim().length() == 0) {
                    new p(this.q.q(), al.q("Error"), al.q("Message should be entered"), this.q).setVisible(true);
                    this.q.q.requestFocus();
                    return true;
                }
                final cp cp;
                (cp = new cp(4202528, 1)).q = -1;
                cp.w = -1;
                cp.q(0, 0, 1);
                cp.q(0, 2, this.q.q.getForeground().getRGB());
                cp.q(0, 0, text);
                cp.q(0, 1, q);
                this.q.r(cp);
                this.dispose();
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    static TextField q(final ap ap) {
        return ap.w;
    }
    
    static {
        q = ce.q("\u001f66<9>5K=5CC175KD?KP\\");
        w = ce.q("%C5BK>1=5e");
    }
}
