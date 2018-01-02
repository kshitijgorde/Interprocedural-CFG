// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Panel;
import java.awt.Cursor;
import java.awt.Event;

public abstract class T extends S
{
    protected K a;
    protected av a;
    private cr e;
    protected cr a;
    protected cr b;
    protected cr c;
    protected cr d;
    private String a;
    protected cg a;
    protected cx a;
    protected boolean b;
    protected String[] a;
    protected cC a;
    private I b;
    protected I a;
    
    public abstract U a();
    
    public abstract void a(final cg p0);
    
    public abstract boolean a(final cg p0, final Event p1);
    
    public abstract boolean a(final U p0);
    
    public abstract void a(final U p0);
    
    public final String[] a() {
        return this.a;
    }
    
    public final void a(final I i) {
        this.a.b(i);
    }
    
    public final void a(final I i, final int n) {
        this.a.a(i, n);
    }
    
    public void c(final U u) {
        if (u == null || this.a.b() == -1) {
            this.e.b();
            this.b.b();
            this.c.b();
            this.d.b();
            return;
        }
        final boolean b = this.a.b((a)u);
        if (u.a(63)) {
            this.b.a(aS.a(17));
            this.a.b(u, true);
        }
        else {
            this.b.a(aS.a(15));
            this.a.b(u, false);
        }
        if (b) {
            this.e.a();
        }
        else {
            this.e.b();
        }
        if (u.a && b && !u.a(62)) {
            this.b.a();
        }
        else {
            this.b.b();
        }
        if (this.c.isVisible() && b) {
            if (u == this.a.a(0)) {
                if (this.a.a() > 1) {
                    this.d.a();
                }
                this.c.b();
            }
            else if (u == this.a.b(this.a.b() - 1)) {
                this.c.a();
                this.d.b();
            }
            else {
                this.d.a();
                if (u == this.a.a(1) && !this.a.b((a)this.a.a(0))) {
                    this.c.b();
                }
                else {
                    this.c.a();
                }
            }
        }
        if (!b) {
            this.c.b();
            this.d.b();
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final U u = (U)this.a.a();
                if (this.a == null && ((event.target == this.a && this.e.a()) || event.target == this.e) && u != null) {
                    this.setCursor(Cursor.getPredefinedCursor(3));
                    (this.a = new cg(this.a, this, u, this.b, this.a, (byte)0)).setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                    this.a.requestFocus();
                }
                else if ((this.a == null || !this.a.isVisible()) && event.target == this.a) {
                    final U a;
                    if ((a = this.a()) != null) {
                        super.a = true;
                        this.setCursor(Cursor.getPredefinedCursor(3));
                        (this.a = new cg(this.a, this, a, this.b, this.a, (byte)0)).setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                        this.a.requestFocus();
                    }
                }
                else if (event.target == this.b) {
                    if (u.a(63)) {
                        super.a = true;
                        u.c(63);
                        u.j = 1;
                        this.a.b(u, false);
                        this.b.a(aS.a(15));
                        this.c();
                    }
                    else {
                        super.a = true;
                        u.b(63);
                        u.j = 1;
                        this.a.b(u, true);
                        this.b.a(aS.a(17));
                    }
                    this.a.a((a)u);
                    this.a.requestFocus();
                }
                if (event.target == this.d) {
                    final U u2 = u;
                    int b = this.a.b((Object)u2);
                    while (this.a.b(++b) == null && this.a.b() > b) {}
                    final U u3;
                    final int i = (u3 = (U)this.a.b(b)).i;
                    u3.i = u2.i;
                    u2.i = i;
                    u3.j = 1;
                    u2.j = 1;
                    this.a.a(u3, u3.i);
                    this.a.a(u2, b);
                    this.a.c();
                    super.a = true;
                    this.c(u2);
                }
                if (event.target == this.c) {
                    final U u4 = u;
                    int b2 = this.a.b((Object)u4);
                    while (this.a.b(--b2) == null && b2 >= 0) {}
                    final U u5;
                    final int j = (u5 = (U)this.a.b(b2)).i;
                    u5.i = u4.i;
                    u4.i = j;
                    u5.j = 1;
                    u4.i = 1;
                    this.a.a(u5, u5.i);
                    this.a.a(u4, b2);
                    this.a.c();
                    super.a = true;
                    this.c(u4);
                }
                return true;
            }
            case 701: {
                this.c((U)event.arg);
                return true;
            }
            case 702: {
                this.c(null);
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void a() {
        this.a.e();
        this.a.a();
    }
    
    public void c() {
    }
    
    public void b(final U u) {
        final int a = this.a.a((a)u);
        this.a.a(u);
        if (a == -1) {
            this.a.c(u);
            if (u.a(62)) {
                this.a.a(u, true);
            }
            if (this.a != null) {
                final U a2;
                if ((a2 = this.a()) != null) {
                    this.a.a(a2);
                }
                else {
                    this.a.dispose();
                    this.a = null;
                }
            }
        }
        else {
            this.a.a(u, a);
            if (this.a != null) {
                this.a.dispose();
                this.a = null;
            }
        }
        this.c(u);
    }
    
    public void a(final U u, final cg cg) {
        this.a.dispose();
        this.a = null;
    }
    
    public final String a(final Object o) {
        final String lowerCase = this.a.toLowerCase();
        if (o instanceof cr) {
            if (((cr)o).a()) {
                if (o == this.a) {
                    return bm.a(aS.a(88), new String[] { lowerCase });
                }
                if (o == this.e) {
                    return bm.a(aS.a(89), new String[] { lowerCase });
                }
                if (o == this.b) {
                    if (this.b.a.equals(aS.a(15))) {
                        return bm.a(aS.a(94), new String[] { lowerCase });
                    }
                    return bm.a(aS.a(93), new String[] { lowerCase });
                }
            }
            else {
                if (this.a.b() < 0) {
                    return bm.a(aS.a(90), new String[] { lowerCase });
                }
                if (o == this.e) {
                    return bm.a(aS.a(92), new String[] { lowerCase });
                }
                if (o == this.b) {
                    return bm.a(aS.a(91), new String[] { lowerCase });
                }
            }
        }
        return null;
    }
    
    public void show() {
        super.show();
        this.a.requestFocus();
    }
    
    public final int a() {
        return this.a.a();
    }
    
    public final int b() {
        int n = 0;
        for (int i = 0; i < this.a.a(); ++i) {
            if (((U)this.a.a(i)).j == 1) {
                ++n;
            }
        }
        return n;
    }
    
    protected final void d() {
        this.c.hide();
        this.d.hide();
    }
    
    public final U a(final int n) {
        return (U)this.a.a(n);
    }
    
    public final U b(final int n) {
        return (U)this.a.b(n);
    }
    
    public T(final cx a, final String s, final String a2) {
        super(s, a);
        this.e = new cr(80, 20);
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.d = new cr(30, 30);
        (this.c = new cr(30, 30)).a(cs.h);
        this.d.a(cs.i);
        this.a = null;
        this.b = false;
        this.a = null;
        this.a = new cC(null, "default");
        this.b = new I("ID");
        this.a = new I(aS.a(27), "name");
        this.a = a;
        this.a = a2;
        this.a = new av(1000);
        (this.a = new K()).b(this.b);
        this.a.b(this.a);
        this.a.a();
        this.b.b = true;
        this.a.b = true;
        this.a.a(this.b);
        final I b;
        (b = this.b).c = 2;
        if (b.a != null) {
            b.a.c(b);
        }
        final Panel panel = new Panel();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        this.a.resize(250, 220);
        final i i = new i(this.a);
        layout.setConstraints(i, gridBagConstraints);
        panel.add(i);
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.e.getFontMetrics(this.e.getFont())).stringWidth(aS.a(16));
        final int stringWidth2 = fontMetrics.stringWidth(aS.a(15));
        final int stringWidth3 = fontMetrics.stringWidth(aS.a(14));
        if (stringWidth2 > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (stringWidth3 > stringWidth) {
            stringWidth = stringWidth3;
        }
        stringWidth += 20;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        layout.setConstraints(this.e, gridBagConstraints);
        this.e.a(aS.a(16));
        this.e.resize(stringWidth, 20);
        panel.add(this.e);
        this.b.a(aS.a(15));
        this.b.resize(stringWidth, 20);
        layout.setConstraints(this.b, gridBagConstraints);
        panel.add(this.b);
        gridBagConstraints.anchor = 11;
        gridBagConstraints.weighty = 1.0;
        this.a.a(aS.a(14));
        this.a.resize(stringWidth, 20);
        layout.setConstraints(this.a, gridBagConstraints);
        panel.add(this.a);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.anchor = 15;
        layout.setConstraints(this.c, gridBagConstraints);
        panel.add(this.c);
        gridBagConstraints.gridheight = 0;
        layout.setConstraints(this.d, gridBagConstraints);
        panel.add(this.d);
        gridBagConstraints.anchor = 11;
        this.a(panel, 1, 1.0f, 1.0f);
    }
}
