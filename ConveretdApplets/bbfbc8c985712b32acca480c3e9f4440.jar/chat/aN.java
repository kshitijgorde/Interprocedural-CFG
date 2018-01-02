// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.Date;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.awt.Event;
import java.util.Calendar;
import java.text.DateFormat;
import java.awt.TextField;
import java.awt.Frame;
import java.awt.Choice;

public final class aN extends bC
{
    private cr a;
    private Choice a;
    private cx a;
    private cr b;
    public static String[] a;
    private ca a;
    private Frame a;
    private cr c;
    private cr d;
    private int a;
    private Choice b;
    private TextField a;
    private TextField b;
    private DateFormat a;
    private Calendar a;
    private cr e;
    
    public final boolean handleEvent(final Event event) {
        Label_0257: {
            switch (event.id) {
                case 401: {
                    if (event.key == 27) {
                        this.a.c();
                        return true;
                    }
                    if (event.target == this.a) {
                        if (event.key > 26 && (event.key < 48 || event.key > 57)) {
                            event.key = 0;
                        }
                        return true;
                    }
                    break;
                }
                case 402: {
                    if (event.target != this.a) {
                        break Label_0257;
                    }
                    switch (event.key) {
                        case 43: {
                            this.a.setText(this.a(this.a.getText(), 1, 1));
                            break Label_0257;
                        }
                        case 45: {
                            this.a.setText(this.a(this.a.getText(), 2, 1));
                            break Label_0257;
                        }
                        case 42: {
                            this.a.setText(this.a(this.a.getText(), 3, 1));
                            break Label_0257;
                        }
                        case 47: {
                            this.a.setText(this.a(this.a.getText(), 4, 1));
                            break Label_0257;
                        }
                    }
                    break;
                }
                case 1001: {
                    if (event.target == this.c) {
                        this.d.a();
                        this.c();
                        return true;
                    }
                    if (event.target == this.d) {
                        this.c.a();
                        final aN an = this = this;
                        an.a -= 15;
                        this.a.a();
                        try {
                            for (int n = this.a - 15; this.a >= 15 && n < this.a; ++n) {
                                final bw bw = (bw)this.a.v.a(n);
                                final aU au;
                                (au = new aU(this.a.a(bw.b + "\n" + bw.d), this.a.a(bw.a), null, -999)).h = 153;
                                this.a.a(au);
                            }
                        }
                        finally {
                            throw loadexception(java.lang.Throwable.class);
                        }
                        if (this.a >= 15) {
                            this.d.a();
                        }
                        if (this.a <= 15) {
                            this.d.b();
                        }
                        return true;
                    }
                    if (event.target == this.a) {
                        this.dispose();
                        return true;
                    }
                    if (event.target == this.e) {
                        this.a(this.a.getText(), 0, 0);
                        final ap ap;
                        (ap = new ap(this.a, this.a, this.a, this, this.a)).e();
                        ap.setVisible(true);
                        return true;
                    }
                    if (event.target == this.b) {
                        this.a.a();
                        final r r;
                        (r = new r(65808, 1)).d = -1;
                        r.e = -1;
                        r.a(0, 0, this.a.i);
                        r.a(0, 1, this.b.getSelectedIndex());
                        r.a(0, 0, this.a.getSelectedItem());
                        r.a(0, 1, this.a.getText());
                        r.a(0, 2, this.b.getText());
                        this.a.o(r);
                        this.b.b();
                        this.c.b();
                        this.d.b();
                        return true;
                    }
                    break;
                }
                case 7691: {
                    this.a(this.a.getText(), 1, 0);
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    private String a(String s, final int n, final int n2) {
        final int int1 = Integer.parseInt((s = s).substring(6));
        final int n3 = Integer.parseInt(s.substring(3, 5)) - 1;
        final int int2 = Integer.parseInt(s.substring(0, 2));
        switch (n) {
            case 1: {
                this.a.set(int1, n3, int2 + n2);
                break;
            }
            case 2: {
                this.a.set(int1, n3, int2 - n2);
                break;
            }
            case 3: {
                this.a.set(int1, n3 + n2, int2);
                break;
            }
            case 4: {
                this.a.set(int1, n3 - n2, int2);
                break;
            }
        }
        return this.a.format(this.a.getTime());
    }
    
    public final void a() {
        this.b.a();
    }
    
    public final void b() {
        this.a = 0;
        this.c.b();
        this.d.b();
        this.c();
    }
    
    private void c() {
        ((ca)(this.a += 15)).a();
        try {
            for (int n = this.a - 15; n < this.a.v.a() && n < this.a; ++n) {
                final bw bw = (bw)this.a.v.a(n);
                final aU au;
                (au = new aU(this.a.a(bw.b + "\n" + bw.d), this.a.a(bw.a), null, -999)).h = 153;
                this.a.a(au);
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        if (this.a < this.a.v.a()) {
            this.c.a();
        }
        if (this.a >= this.a.v.a()) {
            this.c.b();
        }
    }
    
    public aN(final Frame a, final cx a2) {
        super(a, aS.a(179), false);
        this.setBackground(a2.a.a);
        this.a = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        this.a = Calendar.getInstance();
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        (this.e = new cr(20, 20)).a(aS.a(645));
        this.e.d();
        this.a = 0;
        this.a = a;
        this.a = new Choice();
        this.b = new Choice();
        final bF bf = new bF();
        final k k = new k();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final bi bi = new bi(aS.a(179), (byte)0);
        ((Choice)(this.a = a2)).addItem("--");
        if (aN.a != null) {
            for (int i = 0; i < aN.a.length; ++i) {
                this.a.addItem(aN.a[i]);
            }
        }
        this.b.addItem("--");
        this.b.addItem(aS.a(538));
        this.b.addItem(aS.a(537));
        this.b.addItem(aS.a(171));
        this.b.addItem(aS.a(380));
        this.b.addItem(aS.a(383));
        this.b.addItem(aS.a(379));
        this.b.addItem(aS.a(374));
        this.b.addItem(aS.a(536));
        this.b.addItem(aS.a(535));
        this.b.addItem(aS.a(145));
        this.b.addItem(aS.a(152));
        this.b.addItem(aS.a(388));
        this.b.addItem(aS.a(386));
        this.b.addItem(aS.a(140));
        this.b.addItem(aS.a(686));
        this.b.addItem(aS.a(687));
        this.b.a();
        this.a.setForeground(Color.black);
        this.b.setForeground(Color.black);
        (this.b = new TextField(30)).setForeground(Color.black);
        this.b.setBackground(Color.white);
        (this.a = new TextField(10)).setForeground(Color.black);
        this.a.setBackground(Color.white);
        this.a.setText(this.a.format(new Date()));
        this.a.setEditable(false);
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        bf.setLayout(gridBagLayout);
        bf.setBackground(a2.a.h);
        bf.setForeground(a2.a.g);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        bi.setFont(bk.a);
        gridBagLayout.setConstraints(bi, gridBagConstraints);
        bf.add(bi);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(k, gridBagConstraints);
        bf.add(k);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagLayout.setConstraints(this.e, gridBagConstraints);
        bf.add(this.e);
        final i j = new i(this.a);
        gridBagLayout.setConstraints(j, gridBagConstraints);
        bf.add(j);
        gridBagConstraints.fill = 0;
        final bi bi2;
        (bi2 = new bi(aS.a(636), (byte)0)).setFont(bk.d);
        gridBagLayout.setConstraints(bi2, gridBagConstraints);
        bf.add(bi2);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        bf.add(this.a);
        final bi bi3;
        (bi3 = new bi(aS.a(637), (byte)0)).setFont(bk.d);
        gridBagConstraints.gridwidth = 3;
        gridBagLayout.setConstraints(bi3, gridBagConstraints);
        bf.add(bi3);
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        bf.add(this.b);
        final bi bi4;
        (bi4 = new bi(aS.a(638), (byte)0)).setFont(bk.d);
        gridBagLayout.setConstraints(bi4, gridBagConstraints);
        bf.add(bi4);
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        bf.add(this.b);
        this.b.a(aS.a(534));
        this.b.d();
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        bf.add(this.b);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);
        (this.a = new aR(a2, false)).setFont(a2.a.b());
        this.a.setSize(450, 250);
        final i l = new i(this.a);
        gridBagLayout.setConstraints(l, gridBagConstraints);
        bf.add(l);
        this.c = new cr(80, 20);
        this.d = new cr(80, 20);
        final Panel panel = new Panel();
        gridBagConstraints.insets = new Insets(0, 3, 0, 3);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = -1;
        this.d.a(aS.a(533));
        this.d.d();
        this.d.b();
        gridBagLayout.setConstraints(this.d, gridBagConstraints);
        panel.add(this.d);
        gridBagConstraints.gridwidth = 0;
        this.c.a(aS.a(532));
        this.c.d();
        this.c.b();
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        panel.add(this.c);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        bf.add(panel);
        gridBagLayout.setConstraints(bf, gridBagConstraints);
        this.add(bf);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.a.a(aS.a(95));
        this.a.d();
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.pack();
        this.a.requestFocus();
    }
    
    static {
        aN.a = new String[0];
    }
}
