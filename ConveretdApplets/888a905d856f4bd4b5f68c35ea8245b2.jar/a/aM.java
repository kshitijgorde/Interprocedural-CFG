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

public final class aM extends F
{
    private static final String q;
    private static final String w;
    private ad q;
    private ad w;
    private TextField q;
    private dw q;
    private TextField w;
    private ap q;
    
    public aM(final Frame frame, final ap q) {
        super(frame, B.q(be.w(aM.q), be.w("...")), false);
        new SimpleDateFormat("dd MMM yy HH:mm");
        this.q = q;
        this.q = new ad(80, 20);
        this.w = new ad(80, 20);
        this.setBackground(bC.w.q);
        final dT dt = new dT();
        final Panel panel = new Panel();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setResizable(true);
        this.setLayout(layout);
        dt.setLayout(layout);
        dt.setBackground(bC.w.i);
        dt.setForeground(bC.w.u);
        panel.setLayout(layout);
        panel.setBackground(bC.w.i);
        panel.setForeground(bC.w.u);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        panel.add(new Label(be.w(aM.w)), gridBagConstraints);
        this.q = new TextField(50);
        gridBagConstraints.gridwidth = 0;
        panel.add(this.q, gridBagConstraints);
        final Label label = new Label(be.w(ds.q("x5CC175e")));
        gridBagConstraints.gridwidth = 1;
        panel.add(label, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        panel.add(this.q = new cC(this, this.q), gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        panel.add(this.w = new TextField(3), gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(panel, gridBagConstraints);
        dt.add(panel);
        gridBagConstraints.gridwidth = 0;
        final Image e;
        if ((e = this.q.e("postbox.gif", false)) != null) {
            final bl bl;
            (bl = new bl()).w(e);
            dt.add(bl, gridBagConstraints);
        }
        this.add(dt, gridBagConstraints);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.w.q(be.w("Close"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(be.w("Send"));
        this.q.t();
        final as as = new as(this.q);
        layout.setConstraints(as, gridBagConstraints);
        this.add(as);
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
                    this.setTitle(B.q(be.w(aM.q), this.q.getText()));
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
                    new dd(this.q.q(), be.w("Error"), be.w("Master name should be set"), this.q).setVisible(true);
                    this.q.requestFocus();
                    return true;
                }
                if (q == null || q.trim().length() == 0) {
                    new dd(this.q.q(), be.w("Error"), be.w("Message should be entered"), this.q).setVisible(true);
                    this.q.q.requestFocus();
                    return true;
                }
                final dI di;
                (di = new dI(4202528, 1)).q = -1;
                di.w = -1;
                di.q(0, 0, 1);
                di.q(0, 2, this.q.q.getForeground().getRGB());
                di.q(0, 0, text);
                di.q(0, 1, q);
                this.q.o(di);
                this.dispose();
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    static TextField q(final aM am) {
        return am.w;
    }
    
    static {
        q = ds.q("\u001f66<9>5K=5CC175KD?KP\\");
        w = ds.q("%C5BK>1=5e");
    }
}
