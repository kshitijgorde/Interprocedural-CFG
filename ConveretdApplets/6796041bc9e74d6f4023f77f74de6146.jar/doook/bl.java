// 
// Decompiled by Procyon v0.5.30
// 

package doook;

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

public class bl extends p
{
    private aS a;
    private aS b;
    private aS c;
    private aS d;
    private aS r;
    private aS s;
    private Checkbox e;
    private TextArea a;
    private TextArea c;
    private Z b;
    private cI a;
    private boolean r;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10) {
                    this.s.s();
                    return true;
                }
                if (event.key == 27) {
                    this.d.s();
                    return true;
                }
                break;
            }
            case 201: {
                if (this.b.a != null) {
                    final byte[] array = { this.b.a[0], this.b.a[1], 0, this.r };
                    final cD cd = new cD(66309, 1);
                    cd.j = (this.r ? -2 : -3);
                    cd.o = this.a.h;
                    cd.a(array);
                    this.a.o(cd);
                    this.b.ad = false;
                }
                this.dispose();
                return true;
            }
            case 1001: {
                if (!(event.target instanceof aS)) {
                    break;
                }
                if (event.target == this.d || event.target == this.r) {
                    if (this.b.a != null) {
                        final byte[] array2 = { this.b.a[0], this.b.a[1], (event.target == this.d) ? 0 : 2, this.r };
                        final cD cd2 = new cD(66309, 1);
                        cd2.j = (this.r ? -2 : -3);
                        cd2.o = this.a.h;
                        cd2.a(array2);
                        this.a.o(cd2);
                        this.b.ad = false;
                    }
                    this.dispose();
                    return true;
                }
                final boolean state = this.e.getState();
                cD cd3 = null;
                cD cd4 = null;
                cD cd5 = null;
                byte[] array3 = null;
                if (this.b.a != null) {
                    array3 = new byte[] { this.b.a[0], this.b.a[1], 1, this.r };
                }
                if (state) {
                    cd3 = new cD(66308, 1);
                    cd3.a(0, 0, this.c.getText());
                    cd3.a(0, 0, this.b.Z);
                    cd3.j = -1;
                    cd3.o = this.a.h;
                    if (array3 != null) {
                        cd3.a(array3);
                    }
                }
                final String trim = this.a.getText().trim();
                if (trim.length() > 0) {
                    cd4 = new cD(66308, 1);
                    cd4.a(0, 0, trim);
                    cd4.a(0, 0, this.a.h());
                    cd4.j = -1;
                    cd4.o = this.a.h;
                    if (array3 != null) {
                        cd4.a(array3);
                    }
                }
                if (event.target == this.b) {
                    if (state) {
                        cd3.j = this.b.Z;
                    }
                    if (cd4 != null) {
                        cd4 = new cD(66305, 1);
                        cd4.a(0, 0, trim);
                        cd4.o = this.a.h;
                        cd4.j = this.b.Z;
                        cd4.a(0, 0, this.a.h());
                    }
                    cd5 = new cD(66309, 1);
                    cd5.j = -3;
                    cd5.o = this.a.h;
                    if (array3 != null) {
                        cd5.a(array3);
                    }
                }
                else if (event.target == this.c) {
                    if (this.r) {
                        if (state) {
                            cd3.j = -3;
                        }
                        if (cd4 != null) {
                            cd4.j = -3;
                        }
                    }
                    else {
                        if (state) {
                            cd3.j = -2;
                        }
                        if (cd4 != null) {
                            cd4.j = -2;
                        }
                    }
                    cd5 = new cD(66309, 1);
                    cd5.j = -3;
                    cd5.o = this.a.h;
                    if (array3 != null) {
                        cd5.a(array3);
                    }
                }
                if (state) {
                    this.a.o(cd3);
                }
                if (cd4 != null) {
                    this.a.o(cd4);
                }
                if (cd5 != null) {
                    this.a.o(cd5);
                }
                this.b.ad = true;
                this.dispose();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public bl(final Frame frame, final cI a, final Z b, final ab ab) {
        super(frame, a.d(59) ? ao.e("Guest Speaker Controls") : ao.e("Moderator Controls"), true);
        this.a = new aS(100, 20);
        this.b = new aS(100, 20);
        this.c = new aS(100, 20);
        this.d = new aS(100, 20);
        this.r = new aS(100, 20);
        this.e = new Checkbox(ao.e("Include original message"), true);
        this.a = new TextArea("", 5, 35, 1);
        this.c = new TextArea("", 5, 35, 1);
        this.r = false;
        this.r = a.d(59);
        this.setBackground(a.a.a);
        this.setResizable(false);
        this.a = a;
        this.b = b;
        final cA ca = new cA();
        final as as = (ab == null) ? null : ab.a;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.c.setText(b.l);
        this.a.a(ao.e("All Users"));
        this.a.t();
        this.c.a(ao.e("Guests"));
        if (this.r) {
            this.c.a(ao.e("Moderator"));
        }
        this.c.t();
        this.d.a(ao.e("Cancel"));
        this.d.t();
        this.b.a((ab == null) ? b.m : ab.f());
        this.b.t();
        this.setLayout(gridBagLayout);
        ca.setLayout(gridBagLayout);
        ca.setBackground(a.a.g);
        ca.setForeground(a.a.f);
        gridBagConstraints.insets = new Insets(1, 4, 1, 4);
        final Label label = new Label(ao.e("From: "));
        label.setFont(bL.e);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        ca.add(label);
        if (as != null) {
            final B b2 = new B();
            gridBagConstraints.gridwidth = -1;
            b2.b(as.q);
            gridBagLayout.setConstraints(b2, gridBagConstraints);
            ca.add(b2);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        final Label label2 = new Label((ab == null) ? b.m : ab.f());
        label2.setFont(bL.e);
        gridBagLayout.setConstraints(label2, gridBagConstraints);
        ca.add(label2);
        gridBagConstraints.weightx = 1.0;
        final cH ch = new cH();
        gridBagLayout.setConstraints(ch, gridBagConstraints);
        ca.add(ch);
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        gridBagConstraints.fill = 1;
        final aR ar = new aR(this.c);
        gridBagLayout.setConstraints(ar, gridBagConstraints);
        ca.add(ar);
        final aR ar2 = new aR(this.a);
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(ar2, gridBagConstraints);
        ca.add(ar2);
        gridBagLayout.setConstraints(ca, gridBagConstraints);
        this.add(ca);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        final Label label3 = new Label(ao.e("Send to: "));
        gridBagLayout.setConstraints(label3, gridBagConstraints);
        this.add(label3);
        gridBagConstraints.weightx = 0.0;
        if (!this.r) {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        if (!a.z) {
            gridBagConstraints.gridwidth = 0;
            final aQ aq = new aQ(this.a);
            this.s = this.a;
            gridBagLayout.setConstraints(aq, gridBagConstraints);
            this.add(aq);
        }
        else if (a.z && this.r) {
            gridBagConstraints.gridwidth = -1;
            final aQ aq2 = new aQ(this.a);
            this.s = this.a;
            gridBagLayout.setConstraints(aq2, gridBagConstraints);
            this.add(aq2);
        }
        else {
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        if (a.z && !this.r) {
            gridBagConstraints.gridwidth = 0;
            final aQ aq3 = new aQ(this.c);
            this.s = this.c;
            gridBagLayout.setConstraints(aq3, gridBagConstraints);
            this.add(aq3);
        }
        else if (a.z && this.r) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.c, gridBagConstraints);
            this.add(this.c);
        }
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        if (!this.r) {
            gridBagConstraints.gridwidth = 2;
        }
        gridBagLayout.setConstraints(this.e, gridBagConstraints);
        this.add(this.e);
        if (!this.r) {
            this.r.a(ao.e("Delete"));
            this.r.t();
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(this.r, gridBagConstraints);
            this.add(this.r);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.d, gridBagConstraints);
        this.add(this.d);
        this.pack();
        if (b.a != null) {
            final byte[] array = { b.a[0], b.a[1], 1, this.r };
            final cD cd = new cD(66309, 1);
            cd.j = (this.r ? -2 : -3);
            cd.o = a.h;
            if (array != null) {
                cd.a(array);
            }
            if (cd != null) {
                a.o(cd);
            }
            b.ad = true;
        }
        this.setVisible(true);
        this.a.requestFocus();
    }
}
