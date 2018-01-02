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

public final class dD extends ah
{
    private g q;
    private g w;
    private g e;
    private g r;
    private g t;
    private g y;
    private Checkbox q;
    private TextArea q;
    private TextArea w;
    private cS q;
    private dz q;
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
                    final es es;
                    (es = new es(66309, 1)).w = (this.q ? -2 : -3);
                    es.q = this.q.o;
                    es.q(array);
                    this.q.q(es);
                    this.q.o = false;
                }
                this.dispose();
                return true;
            }
            case 1001: {
                if (!(event.target instanceof g)) {
                    break;
                }
                if (event.target == this.r || event.target == this.t) {
                    if (this.q.q != null) {
                        final byte[] array2;
                        (array2 = new byte[4])[0] = this.q.q[0];
                        array2[1] = this.q.q[1];
                        array2[2] = (byte)((event.target != this.r) ? 2 : 0);
                        array2[3] = (byte)(this.q ? 1 : 0);
                        final es es2;
                        (es2 = new es(66309, 1)).w = (this.q ? -2 : -3);
                        es2.q = this.q.o;
                        es2.q(array2);
                        this.q.q(es2);
                        this.q.o = false;
                    }
                    this.dispose();
                    return true;
                }
                final boolean state = this.q.getState();
                es es3 = null;
                es es4 = null;
                es es5 = null;
                byte[] array3 = null;
                if (this.q.q != null) {
                    (array3 = new byte[4])[0] = this.q.q[0];
                    array3[1] = this.q.q[1];
                    array3[2] = 1;
                    array3[3] = (byte)(this.q ? 1 : 0);
                }
                if (state) {
                    (es3 = new es(66308, 1)).q(0, 0, this.w.getText());
                    es3.q(0, 0, this.q.y);
                    es3.w = -1;
                    es3.q = this.q.o;
                    if (array3 != null) {
                        es3.q(array3);
                    }
                }
                final String trim;
                if ((trim = this.q.getText().trim()).length() > 0) {
                    (es4 = new es(66308, 1)).q(0, 0, trim);
                    es4.q(0, 0, this.q.q());
                    es4.w = -1;
                    es4.q = this.q.o;
                    if (array3 != null) {
                        es4.q(array3);
                    }
                }
                if (event.target == this.w) {
                    if (state) {
                        es3.w = this.q.y;
                    }
                    if (es4 != null) {
                        (es4 = new es(66305, 1)).q(0, 0, trim);
                        es4.q = this.q.o;
                        es4.w = this.q.y;
                        es4.q(0, 0, this.q.q());
                    }
                    (es5 = new es(66309, 1)).w = -3;
                    es5.q = this.q.o;
                    if (array3 != null) {
                        es5.q(array3);
                    }
                }
                else if (event.target == this.e) {
                    if (this.q) {
                        if (state) {
                            es3.w = -3;
                        }
                        if (es4 != null) {
                            es4.w = -3;
                        }
                    }
                    else {
                        if (state) {
                            es3.w = -2;
                        }
                        if (es4 != null) {
                            es4.w = -2;
                        }
                    }
                    (es5 = new es(66309, 1)).w = -3;
                    es5.q = this.q.o;
                    if (array3 != null) {
                        es5.q(array3);
                    }
                }
                if (state) {
                    this.q.q(es3);
                }
                if (es4 != null) {
                    this.q.q(es4);
                }
                if (es5 != null) {
                    this.q.q(es5);
                }
                this.q.o = true;
                this.dispose();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public dD(final Frame frame, final dz q, final cS q2, final dj dj) {
        super(frame, q.q(59) ? eb.q("Guest Speaker Controls") : eb.q("Moderator Controls"), true);
        this.q = new g(100, 20);
        this.w = new g(100, 20);
        this.e = new g(100, 20);
        this.r = new g(100, 20);
        this.t = new g(100, 20);
        this.q = new Checkbox(eb.q("Include original message"), true);
        this.q = new TextArea("", 5, 35, 1);
        this.w = new TextArea("", 5, 35, 1);
        this.q = false;
        this.q = q.q(59);
        this.setBackground(cf.w.q);
        this.setResizable(false);
        this.q = q;
        this.q = q2;
        final s s = new s();
        final cm cm = (dj != null) ? dj.q : null;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.w.setText(q2.q);
        this.q.q(eb.q("All Users"));
        this.q.t();
        this.e.q(eb.q("Guests"));
        if (this.q) {
            this.e.q(eb.q("Moderator"));
        }
        this.e.t();
        this.r.q(eb.q("Cancel"));
        this.r.t();
        this.w.q((dj != null) ? dj.getName() : q2.w);
        this.w.t();
        this.setLayout(gridBagLayout);
        s.setLayout(gridBagLayout);
        s.setBackground(cf.w.i);
        s.setForeground(cf.w.u);
        gridBagConstraints.insets = new Insets(1, 4, 1, 4);
        final Label label;
        (label = new Label(eb.q("From: "))).setFont(m.q);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        s.add(label);
        if (cm != null) {
            final d d = new d();
            gridBagConstraints.gridwidth = -1;
            d.w(cm.q);
            gridBagLayout.setConstraints(d, gridBagConstraints);
            s.add(d);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        final Label label2;
        (label2 = new Label((dj != null) ? dj.getName() : q2.w)).setFont(m.q);
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        s.add(label2);
        gridBagConstraints.weightx = 1.0;
        final q q3 = new q();
        gridBagLayout.setConstraints(q3, gridBagConstraints);
        s.add(q3);
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        gridBagConstraints.fill = 1;
        final t t = new t(this.w);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        s.add(t);
        final t t2 = new t(this.q);
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(t2, gridBagConstraints);
        s.add(t2);
        gridBagLayout.setConstraints(s, gridBagConstraints);
        this.add(s);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        final Label label3 = new Label(eb.q("Send to: "));
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
            final f f = new f(this.q);
            this.y = this.q;
            gridBagLayout.setConstraints(f, gridBagConstraints);
            this.add(f);
        }
        else if (q.k && this.q) {
            gridBagConstraints.gridwidth = -1;
            final f f2 = new f(this.q);
            this.y = this.q;
            gridBagLayout.setConstraints(f2, gridBagConstraints);
            this.add(f2);
        }
        else {
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(this.q, gridBagConstraints);
            this.add(this.q);
        }
        if (q.k && !this.q) {
            gridBagConstraints.gridwidth = 0;
            final f f3 = new f(this.e);
            this.y = this.e;
            gridBagLayout.setConstraints(f3, gridBagConstraints);
            this.add(f3);
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
            this.t.q(eb.q("Delete"));
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
            final es es;
            (es = new es(66309, 1)).w = (this.q ? -2 : -3);
            es.q = q.o;
            es.q(array);
            q.q(es);
            q2.o = true;
        }
        this.setVisible(true);
        this.q.requestFocus();
    }
}
