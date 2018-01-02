// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.FontMetrics;
import java.awt.Container;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;
import java.awt.TextArea;

public class bo extends aC implements aB
{
    protected TextArea a;
    private Canvas a;
    private Canvas b;
    private cr a;
    private cr b;
    cs a;
    ca a;
    private int a;
    private ai a;
    v a;
    private bz a;
    private bz b;
    
    private final Canvas a(final String s, final String s2, final String s3, final String a) {
        aB a2 = null;
        if (this.a.a.d() && s != null) {
            final Image a3 = this.a.a(s + s2 + "_button_up.gif", true);
            final Image a4 = this.a.a(s + s2 + "_button_dn.gif", true);
            if (a3 != null && a4 != null) {
                ((bG)(a2 = bG.a(a3, a4, null))).a = a;
            }
        }
        if (a2 == null) {
            if (s3 == null) {
                a2 = new cr(70, 20);
            }
            else {
                a2 = new cr(s3, 70, 20);
            }
            ((cr)a2).a(a, null);
        }
        return (cr)a2;
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return aS.a(438);
        }
        return null;
    }
    
    public final void a(final aU au) {
        this.a.a(au);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.a.appendText("\n");
                    break;
                }
                if (event.key == 10 || event.key == ce.a) {
                    if (this.a instanceof cr) {
                        ((cr)this.a).c();
                    }
                    else {
                        ((bG)this.a).a();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    final String trim = this.a.getText().trim();
                    this.a.setText("");
                    if (trim.length() > 0) {
                        if (this.a.a(14)) {
                            this.a.a(trim, this.a, -1, this.a.a, this.a());
                        }
                        else {
                            this.a.a(trim, this.a, -1, 0, 0);
                        }
                        this.a.d();
                    }
                    if (ce.a) {
                        this.a.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.a) {
                    final aj aj;
                    (aj = new aj(null, this.a, this.a)).e();
                    aj.setVisible(true);
                    return true;
                }
                if (event.target == this.b) {
                    final ah ah;
                    (ah = new ah(null, this.a, this.a)).e();
                    ah.setVisible(true);
                    return true;
                }
                if (event.target == this.a) {
                    if (this.a.a) {
                        this.a.c();
                    }
                    else {
                        this.a.b();
                    }
                    this.a.setFont(new Font(this.a.a.a, ((this.a() - 1 & 0xFFFFFFFC) == 0x0) ? (this.a() - 1) : 0, this.a.a.a));
                    return true;
                }
                if (event.target == this.b) {
                    if (this.b.a) {
                        this.b.c();
                    }
                    else {
                        this.b.b();
                    }
                    this.a.setFont(new Font(this.a.a.a, ((this.a() - 1 & 0xFFFFFFFC) == 0x0) ? (this.a() - 1) : 0, this.a.a.a));
                    return true;
                }
                if (event.target == this.b) {
                    final r r;
                    (r = new r(67074, 1)).a(0, 0, this.a.i);
                    r.e = this.a;
                    this.a.o(r);
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.a.a((URL)event.arg, "_blank");
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private int a() {
        int n = 1;
        if (!this.a.a) {
            ++n;
        }
        if (!this.b.a) {
            n += 2;
        }
        return n;
    }
    
    public void dispose() {
        super.dispose();
        this.a.a.b(this.a);
    }
    
    public bo(final cs a, int layout, final ca a3) {
        this.setBackground(a.a.a);
        this.a = a;
        this.a = a2;
        this.a = (ai)a.c.b(a2);
        this.setTitle(bm.a(aS.a(439), new String[] { this.a.d }));
        layout = (int)new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final bF bf = new bF();
        this.setLayout((LayoutManager)layout);
        bf.setLayout((LayoutManager)layout);
        try {
            this.a = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.a = new TextArea(2, 10);
        }
        this.a = a3;
        a.a.c();
        (this.a = new cr(25, 25)).a(cs.d);
        (this.b = new cr(25, 25)).a(cs.c);
        a3.setFont(a.a.b());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 1, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final i i = new i(a3);
        ((GridBagLayout)layout).setConstraints(i, gridBagConstraints);
        bf.add(i);
        this.a.setFont(bk.c);
        if (a.a(14)) {
            final Panel panel;
            (panel = new Panel()).setLayout((LayoutManager)layout);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.insets = new Insets(0, 6, 0, 6);
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            (this.a = new bz(20)).a(cs.e);
            (this.b = new bz(20)).a(cs.f);
            this.a.setBackground(a.a.h);
            this.b.setBackground(a.a.h);
            final Color h;
            (h = a.a.h).getRed();
            h.getGreen();
            h.getBlue();
            final int rgb = h.getRGB();
            final int[] array = new int[41];
            final int n = rgb >> 16 & 0xFF;
            final int n2 = rgb >> 8 & 0xFF;
            final int n3 = rgb & 0xFF;
            final int n4 = n / 3 / 20;
            final int n5 = n2 / 3 / 20;
            final int n6 = n3 / 3 / 20;
            final int n7 = ((255 - n) / 3 << 1) / 20;
            final int n8 = ((255 - n2) / 3 << 1) / 20;
            final int n9 = ((255 - n3) / 3 << 1) / 20;
            for (int j = 0; j < 21; ++j) {
                array[20 - j] = (chat.y.a(n - n4 * j) << 16) + (chat.y.a(n2 - n5 * j) << 8) + chat.y.a(n3 - n6 * j);
            }
            for (int k = 0; k < 21; ++k) {
                array[k + 20] = (chat.y.a(n + n7 * k) << 16) + (chat.y.a(n2 + n8 * k) << 8) + chat.y.a(n3 + n9 * k);
            }
            final int n10 = array[36];
            this.a.a(n10);
            this.b.a(n10);
            this.b.setFont(new Font(bk.a.getFamily(), 3, bk.c.getSize()));
            (this.a = new v(this.a, this.a)).a(2);
            this.a.a = this.a.a(4);
            this.a.a(a.a.j);
            this.a.resize(20, 20);
            ((GridBagLayout)layout).setConstraints(this.a, gridBagConstraints);
            panel.add(this.a);
            ((GridBagLayout)layout).setConstraints(this.b, gridBagConstraints);
            panel.add(this.b);
            ((GridBagLayout)layout).setConstraints(this.a, gridBagConstraints);
            panel.add(this.a);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.insets = new Insets(0, 1, 1, 1);
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            ((GridBagLayout)layout).setConstraints(panel, gridBagConstraints);
            bf.add(panel);
        }
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final Container container;
        (container = new Panel()).setLayout(gridBagLayout);
        final Container container2;
        (container2 = new Panel()).setLayout(gridBagLayout);
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.insets = new Insets(0, 1, 1, 1);
        gridBagConstraints2.anchor = 17;
        gridBagLayout.setConstraints(this.a, gridBagConstraints2);
        container.add(this.a);
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.insets = new Insets(0, 1, 0, 1);
        gridBagConstraints2.anchor = 17;
        gridBagLayout.setConstraints(this.b, gridBagConstraints2);
        container.add(this.b);
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.gridheight = 0;
        gridBagConstraints2.gridwidth = -1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 1.0;
        final i l = new i(this.a);
        gridBagLayout.setConstraints(l, gridBagConstraints2);
        container2.add(l);
        gridBagConstraints2.fill = 0;
        gridBagConstraints2.gridheight = 0;
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagLayout.setConstraints(container, gridBagConstraints2);
        container2.add(container);
        gridBagConstraints.insets = new Insets(1, 3, 2, 3);
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        ((GridBagLayout)layout).setConstraints(container2, gridBagConstraints);
        bf.add(container2);
        bf.setBackground(a.a.h);
        bf.setForeground(a.a.g);
        if ((!ce.b || ce.b != 1) && a.a(20)) {
            this.a.a(21);
        }
        final String c = a.a.c();
        final String s = "_2";
        this.b = this.a(c, "profile_request" + s, aS.a(413), bm.a(aS.a(440), new String[] { this.a.d }));
        this.a = this.a(c, "send_small" + s, aS.a(13), aS.a(441));
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.b.getFontMetrics(bk.b)).stringWidth(aS.a(13));
        final int stringWidth2;
        if ((stringWidth2 = fontMetrics.stringWidth(aS.a(413))) > stringWidth) {
            stringWidth = stringWidth2;
        }
        gridBagConstraints.insets = ((this.b instanceof bG || this.a instanceof bG) ? new Insets(0, 0, 0, 0) : new Insets(2, 3, 2, 3));
        gridBagConstraints.weightx = ((this.a instanceof bG) ? 0.0 : 0.2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = 0;
        if (this.a instanceof cr) {
            final g g = new g(this.a);
            ((GridBagLayout)layout).setConstraints(g, gridBagConstraints);
            bf.add(g);
        }
        else {
            ((GridBagLayout)layout).setConstraints(this.a, gridBagConstraints);
            bf.add(this.a);
        }
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.weightx = ((this.b instanceof bG) ? 0.0 : 0.2);
        gridBagConstraints.gridwidth = 0;
        ((GridBagLayout)layout).setConstraints(this.b, gridBagConstraints);
        if (this.b instanceof cr) {
            this.b.resize(stringWidth2, 20);
        }
        bf.add(this.b);
        if (this.a instanceof cr) {
            this.a.resize(stringWidth, 20);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ((GridBagLayout)layout).setConstraints(bf, gridBagConstraints);
        this.add(bf);
    }
}
