// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.NoSuchElementException;
import java.awt.Event;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Point;
import java.util.Vector;
import java.awt.Checkbox;
import java.awt.Dialog;

public final class S extends Dialog
{
    private j q;
    public static boolean q;
    private static Checkbox q;
    private bH q;
    private l q;
    private Vector q;
    private e q;
    private e w;
    private Point q;
    
    public S(final j q, final Point q2) {
        super(new Frame(), cv.q("Select emotions"), false);
        S.q = false;
        this.q = q;
        this.q = q2;
        this.q = (bH)this.q.q();
        this.q = this.q.q.q();
    }
    
    public final synchronized void hide() {
        S.q = false;
        super.hide();
    }
    
    private void e() {
        this.q.q();
        for (int i = 0; i < this.q.size(); ++i) {
            final bg bg;
            if ((bg = this.q.elementAt(i)).q(36)) {
                this.q.q(bg);
            }
        }
    }
    
    public final void q() {
        this.setBackground(be.w.q);
        final q q = new q();
        final o o = new o();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(cv.q("Select emotion"));
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        q.setLayout(gridBagLayout);
        q.setBackground(be.w.i);
        q.setForeground(be.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label.setFont(k.q);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        q.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(o, gridBagConstraints);
        q.add(o);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        (this.q = new l(this)).setSize(350, 200);
        this.e();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        q.add(this.q);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(q, gridBagConstraints);
        this.add(q);
        if (S.q == null) {
            (S.q = new Checkbox(cv.q("Close after selection"))).setForeground(be.w.u);
            S.q.setState(true);
        }
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(S.q, gridBagConstraints);
        this.add(S.q);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        (this.q = new e(cv.q("Close"))).t();
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        (this.w = new e(cv.q("Insert"))).t();
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        this.pack();
    }
    
    public final synchronized void w() {
        S.q = true;
        this.setLocation(this.q);
        this.doLayout();
        this.show(true);
        this.setVisible(true);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.q.q();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    this.q.q();
                    return true;
                }
                if (event.target == this.w) {
                    a.q(" " + ((bg)this.q.q()).getName(), this.q.q());
                    this.requestFocus();
                    return true;
                }
                if (event.target instanceof m) {
                    try {
                        a.q(" " + ((bg)this.q.q()).getName(), this.q.q());
                    }
                    catch (NoSuchElementException ex) {}
                    if (S.q.getState()) {
                        this.q.q();
                    }
                }
                return true;
            }
            case 201: {
                this.q.q();
                break;
            }
        }
        return super.handleEvent(event);
    }
}
