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

public class aA extends k implements ab
{
    protected TextArea a;
    private Canvas a;
    private Canvas b;
    private i a;
    private i b;
    bh a;
    aX a;
    private int a;
    private aj a;
    o a;
    private aI a;
    private aI b;
    
    private final Canvas a(final String s, final String s2, final String s3, final String a) {
        ab a2 = null;
        if (this.a.a.d() && s != null) {
            final Image a3 = this.a.a(s + s2 + "_button_up.gif", true);
            final Image a4 = this.a.a(s + s2 + "_button_dn.gif", true);
            if (a3 != null && a4 != null) {
                ((aN)(a2 = aN.a(a3, a4, null))).a = a;
            }
        }
        if (a2 == null) {
            if (s3 == null) {
                a2 = new i(70, 20);
            }
            else {
                a2 = new i(s3, 70, 20);
            }
            ((i)a2).a(a, null);
        }
        return (i)a2;
    }
    
    public String a(final Object o) {
        if (o == this.a) {
            return ak.a(438);
        }
        return null;
    }
    
    public final void a(final ao ao) {
        this.a.a(ao);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.a.appendText("\n");
                    break;
                }
                if (event.key == 10 || event.key == aZ.a) {
                    if (this.a instanceof i) {
                        ((i)this.a).c();
                    }
                    else {
                        ((aN)this.a).a();
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
                        this.a.c();
                    }
                    if (aZ.a) {
                        this.a.requestFocus();
                    }
                    return true;
                }
                if (event.target == this.a) {
                    final N n;
                    (n = new N(null, this.a, this.a)).a();
                    n.setVisible(true);
                    return true;
                }
                if (event.target == this.b) {
                    final M m;
                    (m = new M(null, this.a, this.a)).a();
                    m.setVisible(true);
                    return true;
                }
                if (event.target == this.a) {
                    if (this.a.a) {
                        this.a.b();
                    }
                    else {
                        this.a.a();
                    }
                    this.a.setFont(new Font(this.a.a.a, ((this.a() - 1 & 0xFFFFFFFC) == 0x0) ? (this.a() - 1) : 0, this.a.a.a));
                    return true;
                }
                if (event.target == this.b) {
                    if (this.b.a) {
                        this.b.b();
                    }
                    else {
                        this.b.a();
                    }
                    this.a.setFont(new Font(this.a.a.a, ((this.a() - 1 & 0xFFFFFFFC) == 0x0) ? (this.a() - 1) : 0, this.a.a.a));
                    return true;
                }
                if (event.target == this.b) {
                    final m i;
                    (i = new m(67074, 1)).a(0, 0, this.a.g);
                    i.e = this.a;
                    this.a.m(i);
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
    
    public aA(final bh a, int layout, final aX a3) {
        this.setBackground(a.a.a);
        this.a = a;
        this.a = a2;
        this.a = (aj)a.c.b(a2);
        this.setTitle(ak.a(ak.a(439), new String[] { this.a.c }));
        layout = (int)new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final aM am = new aM();
        this.setLayout((LayoutManager)layout);
        am.setLayout((LayoutManager)layout);
        try {
            this.a = new TextArea("", 2, 10, 3);
        }
        catch (Throwable t) {
            this.a = new TextArea(2, 10);
        }
        this.a = a3;
        a.a.b();
        (this.a = new i(25, 25)).a(bh.d);
        (this.b = new i(25, 25)).a(bh.c);
        a3.setFont(a.a.b());
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.insets = new Insets(2, 3, 1, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final g g = new g(a3);
        ((GridBagLayout)layout).setConstraints(g, gridBagConstraints);
        am.add(g);
        this.a.setFont(ay.c);
        if (a.a(14)) {
            final Panel panel;
            (panel = new Panel()).setLayout((LayoutManager)layout);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.insets = new Insets(0, 6, 0, 6);
            gridBagConstraints.fill = 0;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            (this.a = new aI(20)).a(bh.e);
            (this.b = new aI(20)).a(bh.f);
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
            for (int i = 0; i < 21; ++i) {
                array[20 - i] = (chat.h.a(n - n4 * i) << 16) + (chat.h.a(n2 - n5 * i) << 8) + chat.h.a(n3 - n6 * i);
            }
            for (int j = 0; j < 21; ++j) {
                array[j + 20] = (chat.h.a(n + n7 * j) << 16) + (chat.h.a(n2 + n8 * j) << 8) + chat.h.a(n3 + n9 * j);
            }
            final int n10 = array[36];
            this.a.b(n10);
            this.b.b(n10);
            this.b.setFont(new Font(ay.a.getFamily(), 3, ay.c.getSize()));
            (this.a = new o(this.a, this.a)).a(1);
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
            am.add(panel);
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
        final g g2 = new g(this.a);
        gridBagLayout.setConstraints(g2, gridBagConstraints2);
        container2.add(g2);
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
        am.add(container2);
        am.setBackground(a.a.h);
        am.setForeground(a.a.g);
        if ((!aZ.b || aZ.b != 1) && a.a(20)) {
            this.a.a(21);
        }
        final String b = a.a.b();
        final String s = "_2";
        this.b = this.a(b, "profile_request" + s, ak.a(413), ak.a(ak.a(440), new String[] { this.a.c }));
        this.a = this.a(b, "send_small" + s, ak.a(13), ak.a(441));
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.b.getFontMetrics(ay.b)).stringWidth(ak.a(13));
        final int stringWidth2;
        if ((stringWidth2 = fontMetrics.stringWidth(ak.a(413))) > stringWidth) {
            stringWidth = stringWidth2;
        }
        gridBagConstraints.insets = ((this.b instanceof aN || this.a instanceof aN) ? new Insets(0, 0, 0, 0) : new Insets(2, 3, 2, 3));
        gridBagConstraints.weightx = ((this.a instanceof aN) ? 0.0 : 0.2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.gridwidth = 0;
        if (this.a instanceof i) {
            final f f = new f(this.a);
            ((GridBagLayout)layout).setConstraints(f, gridBagConstraints);
            am.add(f);
        }
        else {
            ((GridBagLayout)layout).setConstraints(this.a, gridBagConstraints);
            am.add(this.a);
        }
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.weightx = ((this.b instanceof aN) ? 0.0 : 0.2);
        gridBagConstraints.gridwidth = 0;
        ((GridBagLayout)layout).setConstraints(this.b, gridBagConstraints);
        if (this.b instanceof i) {
            this.b.resize(stringWidth2, 20);
        }
        am.add(this.b);
        if (this.a instanceof i) {
            this.a.resize(stringWidth, 20);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        ((GridBagLayout)layout).setConstraints(am, gridBagConstraints);
        this.add(am);
    }
}
