// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.net.URL;
import java.awt.Event;

public final class am extends C
{
    private aw a;
    private i a;
    private i b;
    private t a;
    private aj a;
    private bl a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == 27) {
                    this.a.c();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.a) {
                    this.a.b(this.a.b);
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.a.a((URL)event.arg, "_blank");
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                    this.a.a(null, this.a);
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public am(Frame frame, final bl a, aj a2, final m m, final int n) {
        super(frame, false);
        this.a = new i(70, 20);
        this.b = new i(115, 20);
        this.setBackground(a.a.a);
        this.a = a2;
        this.a = a;
        (frame = (Frame)new aM()).setBackground(a.a.h);
        frame.setForeground(a.a.g);
        final bb bb = new bb();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final String a3 = a.a(a2.c);
        final String a4 = a.a(m.a(n, 0));
        final String a5 = m.a(n, 1);
        String a6;
        if ((a6 = m.a(n, 2)) == null) {
            a6 = a4;
        }
        final String a7 = m.a(n, 3);
        String value = null;
        String s = null;
        String a8 = null;
        m.a(n, 4);
        boolean b = false;
        final O o;
        if ((o = (O)a.d.b(a2.b)) != null) {
            a8 = a.a(o.c);
        }
        final int a9;
        if ((a9 = m.a(n, 1)) != -999) {
            value = String.valueOf(a9);
        }
        if (m.a(n, 1)) {
            s = ak.a(367);
        }
        else if (m.a(n, 0)) {
            s = ak.a(368);
        }
        a.a(41);
        final b b2 = (b)a.b.b(a2.a);
        this.setResizable(false);
        this.setTitle(ak.a(ak.a(369), new String[] { a3 }));
        this.setLayout(gridBagLayout);
        frame.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridwidth = 1;
        if (b2 != null) {
            final Component component;
            ((l)(component = new l())).b(b2.a);
            gridBagLayout.setConstraints(component, gridBagConstraints);
            frame.add(component);
        }
        gridBagConstraints.gridwidth = 0;
        final aw aw;
        (aw = new aw(a3, (byte)0)).setFont(ay.c);
        gridBagLayout.setConstraints(aw, gridBagConstraints);
        frame.add(aw);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(bb, gridBagConstraints);
        frame.add(bb);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        if (a6 != null) {
            this.a = new aw(a6, (byte)0);
            final aw aw2;
            (aw2 = new aw(ak.a(370), (byte)0)).setFont(ay.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(aw2, gridBagConstraints);
            frame.add(aw2);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            frame.add(this.a);
            b = true;
        }
        if (a4 != null) {
            this.a = new aw(a4, (byte)0);
            final aw aw3;
            (aw3 = new aw(ak.a(371), (byte)0)).setFont(ay.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(aw3, gridBagConstraints);
            frame.add(aw3);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            frame.add(this.a);
            b = true;
        }
        if (value != null) {
            final aw aw4 = new aw(value, (byte)0);
            final aw aw5;
            (aw5 = new aw(ak.a(375), (byte)0)).setFont(ay.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(aw5, gridBagConstraints);
            frame.add(aw5);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(aw4, gridBagConstraints);
            frame.add(aw4);
            b = true;
        }
        if (s != null) {
            final aw aw6 = new aw(s, (byte)0);
            final aw aw7;
            (aw7 = new aw(ak.a(376), (byte)0)).setFont(ay.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(aw7, gridBagConstraints);
            frame.add(aw7);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(aw6, gridBagConstraints);
            frame.add(aw6);
            b = true;
        }
        if (a7 != null) {
            a2 = (aj)new t(a7);
            final aw aw8;
            (aw8 = new aw(ak.a(377), (byte)0)).setFont(ay.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(aw8, gridBagConstraints);
            frame.add(aw8);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints((Component)a2, gridBagConstraints);
            try {
                ((t)a2).a(new URL(a7));
            }
            catch (MalformedURLException ex2) {}
            frame.add((Component)a2);
            b = true;
        }
        if (a5 != null) {
            final t t = new t(a5);
            final aw aw9;
            (aw9 = new aw(ak.a(378), (byte)0)).setFont(ay.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(aw9, gridBagConstraints);
            frame.add(aw9);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(t, gridBagConstraints);
            frame.add(t);
            b = true;
        }
        if (a8 != null) {
            this.a = new t(a8);
            a2 = (aj)new aw(ak.a(84), (byte)0);
            try {
                this.a.a(new URL("file:room"));
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            ((aw)a2).setFont(ay.d);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints((Component)a2, gridBagConstraints);
            frame.add((Component)a2);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            frame.add(this.a);
            b = true;
        }
        if (!b) {
            final aw aw10;
            (aw10 = new aw(ak.a(389), (byte)0)).setFont(ay.d);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(aw10, gridBagConstraints);
            frame.add(aw10);
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagLayout.setConstraints(frame, gridBagConstraints);
        this.add(frame);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        if (a.a(43)) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            this.b.a(ak.a(390));
            this.b.resize(this.b.getFontMetrics(this.b.getFont()).stringWidth(ak.a(390)) + 20, 20);
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
            gridBagConstraints.weightx = 0.0;
        }
        gridBagConstraints.gridwidth = 0;
        this.a.a(ak.a(2));
        this.a.resize(this.a.getFontMetrics(this.a.getFont()).stringWidth(ak.a(2)) + 20, 20);
        final f f = new f(this.a);
        gridBagLayout.setConstraints(f, gridBagConstraints);
        this.add(f);
        this.pack();
        this.setVisible(true);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = this.size();
        if (screenSize.width < size.width + 50) {
            this.resize(screenSize.width - 50, size.height);
        }
    }
}
