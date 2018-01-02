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

public final class ao extends W
{
    private static final String q;
    private static final String w;
    private e q;
    private e w;
    private TextField q;
    private Q q;
    private TextField w;
    private bI q;
    
    public ao(final Frame frame, final bI q) {
        super(frame, cv.q(cv.q(ao.q), cv.q("...")), false);
        new SimpleDateFormat("dd MMM yy HH:mm");
        this.q = q;
        this.q = new e(80, 20);
        this.w = new e(80, 20);
        this.setBackground(be.w.q);
        final q q2 = new q();
        final Panel panel = new Panel();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setResizable(true);
        this.setLayout(layout);
        q2.setLayout(layout);
        q2.setBackground(be.w.i);
        q2.setForeground(be.w.u);
        panel.setLayout(layout);
        panel.setBackground(be.w.i);
        panel.setForeground(be.w.u);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        panel.add(new Label(cv.q(ao.w)), gridBagConstraints);
        this.q = new TextField(50);
        gridBagConstraints.gridwidth = 0;
        panel.add(this.q, gridBagConstraints);
        final Label label = new Label(cv.q(cl.q("x5CC175e")));
        gridBagConstraints.gridwidth = 1;
        panel.add(label, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        panel.add(this.q = new ap(this, this.q), gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        panel.add(this.w = new TextField(3), gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(panel, gridBagConstraints);
        q2.add(panel);
        gridBagConstraints.gridwidth = 0;
        final Image r;
        if ((r = this.q.r("postbox.gif", false)) != null) {
            final c c;
            (c = new c()).w(r);
            q2.add(c, gridBagConstraints);
        }
        this.add(q2, gridBagConstraints);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.w.q(cv.q("Close"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(cv.q("Send"));
        this.q.t();
        final d d = new d(this.q);
        layout.setConstraints(d, gridBagConstraints);
        this.add(d);
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
                    this.setTitle(cv.q(cv.q(ao.q), this.q.getText()));
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
                    new b(this.q.q(), cv.q("Error"), cv.q("Master name should be set"), this.q).setVisible(true);
                    this.q.requestFocus();
                    return true;
                }
                if (q == null || q.trim().length() == 0) {
                    new b(this.q.q(), cv.q("Error"), cv.q("Message should be entered"), this.q).setVisible(true);
                    this.q.q().requestFocus();
                    return true;
                }
                final cJ cj;
                (cj = new cJ(4202528, 1)).q = -1;
                cj.w = -1;
                cj.q(0, 0, 1);
                cj.q(0, 2, this.q.q().getForeground().getRGB());
                cj.q(0, 0, text);
                cj.q(0, 1, q);
                this.q.q(cj);
                this.dispose();
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    static TextField q(final ao ao) {
        return ao.w;
    }
    
    static {
        q = cl.q("\u001f66<9>5K=5CC175KD?KP\\");
        w = cl.q("%C5BK>1=5e");
    }
}
