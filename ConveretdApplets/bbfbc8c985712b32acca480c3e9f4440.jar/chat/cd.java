// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class cd extends bC
{
    private cr a;
    private cr b;
    private cr c;
    private cr d;
    private cr e;
    private cr f;
    private Checkbox a;
    private TextArea a;
    private TextArea b;
    private aU a;
    private bH a;
    private boolean a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10) {
                    this.f.c();
                    return true;
                }
                if (event.key == 27) {
                    this.d.c();
                    return true;
                }
                break;
            }
            case 201: {
                if (this.a.a != null) {
                    final byte[] a;
                    (a = new byte[4])[0] = this.a.a[0];
                    a[1] = this.a.a[1];
                    a[2] = 0;
                    a[3] = (byte)(this.a ? 1 : 0);
                    final r r;
                    (r = new r(66309, 1)).e = (this.a ? -2 : -3);
                    r.d = this.a.b;
                    r.a = a;
                    this.a.o(r);
                    this.a.e = false;
                }
                this.dispose();
                return true;
            }
            case 1001: {
                if (!(event.target instanceof cr)) {
                    break;
                }
                if (event.target == this.d || event.target == this.e) {
                    if (this.a.a != null) {
                        final byte[] a2;
                        (a2 = new byte[4])[0] = this.a.a[0];
                        a2[1] = this.a.a[1];
                        a2[2] = (byte)((event.target != this.d) ? 2 : 0);
                        a2[3] = (byte)(this.a ? 1 : 0);
                        final r r2;
                        (r2 = new r(66309, 1)).e = (this.a ? -2 : -3);
                        r2.d = this.a.b;
                        r2.a = a2;
                        this.a.o(r2);
                        this.a.e = false;
                    }
                    this.dispose();
                    return true;
                }
                final boolean state = this.a.getState();
                r r3 = null;
                r r4 = null;
                r r5 = null;
                byte[] array = null;
                if (this.a.a != null) {
                    (array = new byte[4])[0] = this.a.a[0];
                    array[1] = this.a.a[1];
                    array[2] = 1;
                    array[3] = (byte)(this.a ? 1 : 0);
                }
                if (state) {
                    (r3 = new r(66308, 1)).a(0, 0, this.b.getText());
                    r3.a(0, 0, this.a.e);
                    r3.e = -1;
                    r3.d = this.a.b;
                    if (array != null) {
                        r3.a = array;
                    }
                }
                final String trim;
                if ((trim = this.a.getText().trim()).length() > 0) {
                    (r4 = new r(66308, 1)).a(0, 0, trim);
                    r4.a(0, 0, this.a.i);
                    r4.e = -1;
                    r4.d = this.a.b;
                    if (array != null) {
                        r4.a = array;
                    }
                }
                if (event.target == this.b) {
                    if (state) {
                        r3.e = this.a.e;
                    }
                    if (r4 != null) {
                        (r4 = new r(66305, 1)).a(0, 0, trim);
                        r4.d = this.a.b;
                        r4.e = this.a.e;
                        r4.a(0, 0, this.a.i);
                    }
                    (r5 = new r(66309, 1)).e = -3;
                    r5.d = this.a.b;
                    if (array != null) {
                        r5.a = array;
                    }
                }
                else if (event.target == this.c) {
                    if (this.a) {
                        if (state) {
                            r3.e = -3;
                        }
                        if (r4 != null) {
                            r4.e = -3;
                        }
                    }
                    else {
                        if (state) {
                            r3.e = -2;
                        }
                        if (r4 != null) {
                            r4.e = -2;
                        }
                    }
                    (r5 = new r(66309, 1)).e = -3;
                    r5.d = this.a.b;
                    if (array != null) {
                        r5.a = array;
                    }
                }
                if (state) {
                    this.a.o(r3);
                }
                if (r4 != null) {
                    this.a.o(r4);
                }
                if (r5 != null) {
                    this.a.o(r5);
                }
                this.a.e = true;
                this.dispose();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public cd(final Frame frame, final bH a, final aU a2, final aZ az) {
        super(frame, a.a(59) ? aS.a(182) : aS.a(183), true);
        this.a = new cr(100, 20);
        this.b = new cr(100, 20);
        this.c = new cr(100, 20);
        this.d = new cr(100, 20);
        this.e = new cr(100, 20);
        this.a = new Checkbox(aS.a(422), true);
        this.a = new TextArea("", 5, 35, 1);
        this.b = new TextArea("", 5, 35, 1);
        this.a = false;
        this.a = a.a(59);
        this.setBackground(a.a.a);
        this.setResizable(false);
        this.a = a;
        this.a = a2;
        final bF bf = new bF();
        final b b = (az != null) ? az.a : null;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.b.setText(a2.a);
        this.a.a(aS.a(423));
        this.a.d();
        this.c.a(aS.a(424));
        if (this.a) {
            this.c.a(aS.a(120));
        }
        this.c.d();
        this.d.a(aS.a(3));
        this.d.d();
        this.b.a((az != null) ? az.d : a2.b);
        this.b.d();
        this.setLayout(gridBagLayout);
        bf.setLayout(gridBagLayout);
        bf.setBackground(a.a.h);
        bf.setForeground(a.a.g);
        gridBagConstraints.insets = new Insets(1, 4, 1, 4);
        final bi bi;
        (bi = new bi(aS.a(425), (byte)0)).setFont(bk.a);
        gridBagLayout.setConstraints(bi, gridBagConstraints);
        bf.add(bi);
        if (b != null) {
            final q q = new q();
            gridBagConstraints.gridwidth = -1;
            q.b(b.a);
            gridBagLayout.setConstraints(q, gridBagConstraints);
            bf.add(q);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        final bi bi2;
        (bi2 = new bi((az != null) ? az.d : a2.b, (byte)0)).setFont(bk.a);
        gridBagLayout.setConstraints(bi2, gridBagConstraints);
        bf.add(bi2);
        gridBagConstraints.weightx = 1.0;
        final k k = new k();
        gridBagLayout.setConstraints(k, gridBagConstraints);
        bf.add(k);
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        gridBagConstraints.fill = 1;
        final i i = new i(this.b);
        gridBagLayout.setConstraints(i, gridBagConstraints);
        bf.add(i);
        final i j = new i(this.a);
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(j, gridBagConstraints);
        bf.add(j);
        gridBagLayout.setConstraints(bf, gridBagConstraints);
        this.add(bf);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        final bi bi3 = new bi(aS.a(416), (byte)0);
        gridBagLayout.setConstraints(bi3, gridBagConstraints);
        this.add(bi3);
        gridBagConstraints.weightx = 0.0;
        if (!this.a) {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        if (!a.o) {
            gridBagConstraints.gridwidth = 0;
            final g g = new g(this.a);
            this.f = this.a;
            gridBagLayout.setConstraints(g, gridBagConstraints);
            this.add(g);
        }
        else if (a.o && this.a) {
            gridBagConstraints.gridwidth = -1;
            final g g2 = new g(this.a);
            this.f = this.a;
            gridBagLayout.setConstraints(g2, gridBagConstraints);
            this.add(g2);
        }
        else {
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        if (a.o && !this.a) {
            gridBagConstraints.gridwidth = 0;
            final g g3 = new g(this.c);
            this.f = this.c;
            gridBagLayout.setConstraints(g3, gridBagConstraints);
            this.add(g3);
        }
        else if (a.o && this.a) {
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.c, gridBagConstraints);
            this.add(this.c);
        }
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        if (!this.a) {
            gridBagConstraints.gridwidth = 2;
        }
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        if (!this.a) {
            this.e.a(aS.a(426));
            this.e.d();
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            this.add(this.e);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 13;
        gridBagLayout.setConstraints(this.d, gridBagConstraints);
        this.add(this.d);
        this.pack();
        if (a2.a != null) {
            final byte[] a3;
            (a3 = new byte[4])[0] = a2.a[0];
            a3[1] = a2.a[1];
            a3[2] = 1;
            a3[3] = (byte)(this.a ? 1 : 0);
            final r r;
            (r = new r(66309, 1)).e = (this.a ? -2 : -3);
            r.d = a.b;
            r.a = a3;
            a.o(r);
            a2.e = true;
        }
        this.setVisible(true);
        this.a.requestFocus();
    }
}
