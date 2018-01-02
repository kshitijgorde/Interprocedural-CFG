// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class bL extends F
{
    private ad q;
    private ad w;
    private ad e;
    private ad r;
    private ad t;
    private ad y;
    private Checkbox q;
    private TextArea q;
    private TextArea w;
    private A q;
    private cT q;
    private boolean q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10) {
                    this.y.r();
                    return true;
                }
                if (event.key == 27) {
                    this.r.r();
                    return true;
                }
                break;
            }
            case 201: {
                if (this.q.q != null) {
                    final byte[] array;
                    (array = new byte[4])[0] = this.q.q[0];
                    array[1] = this.q.q[1];
                    array[2] = 0;
                    array[3] = (byte)(this.q ? 1 : 0);
                    final dI di;
                    (di = new dI(66309, 1)).w = (this.q ? -2 : -3);
                    di.q = this.q.r;
                    di.q(array);
                    this.q.o(di);
                    this.q.o = false;
                }
                this.dispose();
                return true;
            }
            case 1001: {
                if (!(event.target instanceof ad)) {
                    break;
                }
                if (event.target == this.r || event.target == this.t) {
                    if (this.q.q != null) {
                        final byte[] array2;
                        (array2 = new byte[4])[0] = this.q.q[0];
                        array2[1] = this.q.q[1];
                        array2[2] = (byte)((event.target != this.r) ? 2 : 0);
                        array2[3] = (byte)(this.q ? 1 : 0);
                        final dI di2;
                        (di2 = new dI(66309, 1)).w = (this.q ? -2 : -3);
                        di2.q = this.q.r;
                        di2.q(array2);
                        this.q.o(di2);
                        this.q.o = false;
                    }
                    this.dispose();
                    return true;
                }
                final boolean state = this.q.getState();
                dI di3 = null;
                dI di4 = null;
                dI di5 = null;
                byte[] array3 = null;
                if (this.q.q != null) {
                    (array3 = new byte[4])[0] = this.q.q[0];
                    array3[1] = this.q.q[1];
                    array3[2] = 1;
                    array3[3] = (byte)(this.q ? 1 : 0);
                }
                if (state) {
                    (di3 = new dI(66308, 1)).q(0, 0, this.w.getText());
                    di3.q(0, 0, this.q.y);
                    di3.w = -1;
                    di3.q = this.q.r;
                    if (array3 != null) {
                        di3.q(array3);
                    }
                }
                final String trim;
                if ((trim = this.q.getText().trim()).length() > 0) {
                    (di4 = new dI(66308, 1)).q(0, 0, trim);
                    di4.q(0, 0, this.q.s);
                    di4.w = -1;
                    di4.q = this.q.r;
                    if (array3 != null) {
                        di4.q(array3);
                    }
                }
                if (event.target == this.w) {
                    if (state) {
                        di3.w = this.q.y;
                    }
                    if (di4 != null) {
                        (di4 = new dI(66305, 1)).q(0, 0, trim);
                        di4.q = this.q.r;
                        di4.w = this.q.y;
                        di4.q(0, 0, this.q.s);
                    }
                    (di5 = new dI(66309, 1)).w = -3;
                    di5.q = this.q.r;
                    if (array3 != null) {
                        di5.q(array3);
                    }
                }
                else if (event.target == this.e) {
                    if (this.q) {
                        if (state) {
                            di3.w = -3;
                        }
                        if (di4 != null) {
                            di4.w = -3;
                        }
                    }
                    else {
                        if (state) {
                            di3.w = -2;
                        }
                        if (di4 != null) {
                            di4.w = -2;
                        }
                    }
                    (di5 = new dI(66309, 1)).w = -3;
                    di5.q = this.q.r;
                    if (array3 != null) {
                        di5.q(array3);
                    }
                }
                if (state) {
                    this.q.o(di3);
                }
                if (di4 != null) {
                    this.q.o(di4);
                }
                if (di5 != null) {
                    this.q.o(di5);
                }
                this.q.o = true;
                this.dispose();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public bL(final Frame frame, final cT q, final A q2, final aO ao) {
        super(frame, q.q(59) ? be.w("Guest Speaker Controls") : be.w("Moderator Controls"), true);
        this.q = new ad(100, 20);
        this.w = new ad(100, 20);
        this.e = new ad(100, 20);
        this.r = new ad(100, 20);
        this.t = new ad(100, 20);
        this.q = new Checkbox(be.w("Include original message"), true);
        this.q = new TextArea("", 5, 35, 1);
        this.w = new TextArea("", 5, 35, 1);
        this.q = false;
        this.q = q.q(59);
        this.setBackground(bC.w.q);
        this.setResizable(false);
        this.q = q;
        this.q = q2;
        final dT dt = new dT();
        final aZ az = (ao != null) ? ao.q : null;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.w.setText(q2.q);
        this.q.q(be.w("All Users"));
        this.q.t();
        this.e.q(be.w("Guests"));
        if (this.q) {
            this.e.q(be.w("Moderator"));
        }
        this.e.t();
        this.r.q(be.w("Cancel"));
        this.r.t();
        this.w.q((ao != null) ? ao.a : q2.w);
        this.w.t();
        this.setLayout(gridBagLayout);
        dt.setLayout(gridBagLayout);
        dt.setBackground(bC.w.i);
        dt.setForeground(bC.w.u);
        gridBagConstraints.insets = new Insets(1, 4, 1, 4);
        final Label label;
        (label = new Label(be.w("From: "))).setFont(cb.q);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        dt.add(label);
        if (az != null) {
            final bl bl = new bl();
            gridBagConstraints.gridwidth = -1;
            bl.w(az.q);
            gridBagLayout.setConstraints(bl, gridBagConstraints);
            dt.add(bl);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        final Label label2;
        (label2 = new Label((ao != null) ? ao.a : q2.w)).setFont(cb.q);
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        dt.add(label2);
        gridBagConstraints.weightx = 1.0;
        final q q3 = new q();
        gridBagLayout.setConstraints(q3, gridBagConstraints);
        dt.add(q3);
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        gridBagConstraints.fill = 1;
        final bZ bz = new bZ(this.w);
        gridBagLayout.setConstraints(bz, gridBagConstraints);
        dt.add(bz);
        final bZ bz2 = new bZ(this.q);
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(bz2, gridBagConstraints);
        dt.add(bz2);
        gridBagLayout.setConstraints(dt, gridBagConstraints);
        this.add(dt);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        final Label label3 = new Label(be.w("Send to: "));
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        this.add(label3);
        gridBagConstraints.weightx = 0.0;
        if (!this.q) {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        if (!q.k) {
            gridBagConstraints.gridwidth = 0;
            final as as = new as(this.q);
            this.y = this.q;
            gridBagLayout.setConstraints(as, gridBagConstraints);
            this.add(as);
        }
        else if (q.k && this.q) {
            gridBagConstraints.gridwidth = -1;
            final as as2 = new as(this.q);
            this.y = this.q;
            gridBagLayout.setConstraints(as2, gridBagConstraints);
            this.add(as2);
        }
        else {
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
        }
        if (q.k && !this.q) {
            gridBagConstraints.gridwidth = 0;
            final as as3 = new as(this.e);
            this.y = this.e;
            gridBagLayout.setConstraints(as3, gridBagConstraints);
            this.add(as3);
        }
        else if (q.k && this.q) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            this.add(this.e);
        }
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        if (!this.q) {
            gridBagConstraints.gridwidth = 2;
        }
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        if (!this.q) {
            this.t.q(be.w("Delete"));
            this.t.t();
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(this.t, gridBagConstraints);
            this.add(this.t);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.r, gridBagConstraints);
        this.add(this.r);
        this.pack();
        if (q2.q != null) {
            final byte[] array;
            (array = new byte[4])[0] = q2.q[0];
            array[1] = q2.q[1];
            array[2] = 1;
            array[3] = (byte)(this.q ? 1 : 0);
            final dI di;
            (di = new dI(66309, 1)).w = (this.q ? -2 : -3);
            di.q = q.r;
            di.q(array);
            q.o(di);
            q2.o = true;
        }
        this.setVisible(true);
        this.q.requestFocus();
    }
}
