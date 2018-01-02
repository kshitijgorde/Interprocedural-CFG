// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Panel;
import java.awt.Cursor;
import java.awt.Event;

public abstract class bF extends o
{
    protected l f;
    protected aG x;
    protected aS c;
    protected aS d;
    protected aS r;
    protected aS x;
    private String ab;
    protected bk a;
    protected u i;
    protected boolean k;
    protected String[] e;
    protected cf b;
    protected j f;
    protected j g;
    
    public abstract cF a();
    
    public abstract void a(final bk p0);
    
    public abstract boolean a(final cF p0);
    
    public abstract void a(final cF p0);
    
    public String[] a() {
        return this.e;
    }
    
    public void a(final j j) {
        this.f.b(j);
    }
    
    public void a(final j j, final int n) {
        this.f.a(j, n);
    }
    
    public void c(final cF cf) {
        if (cf == null) {
            this.c.d();
            this.r.d();
        }
        else {
            if (cf.d(63)) {
                this.r.a(ao.e("Restore"));
            }
            else {
                this.r.a(ao.e("Remove"));
            }
            if (this.f.b((aU)cf)) {
                this.c.c();
            }
            else {
                this.c.d();
            }
            if (cf.a && this.f.b((aU)cf) && !cf.d(62)) {
                this.r.c();
            }
            else {
                this.r.d();
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final cF cf = (cF)this.f.a();
                if (this.a == null && ((event.target == this.f && this.c.a()) || event.target == this.c) && cf != null) {
                    super.e = true;
                    this.setCursor(Cursor.getPredefinedCursor(3));
                    (this.a = new bk(this.ab, this, cf, this.k, this.i)).setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                    this.f.requestFocus();
                }
                else if ((this.a == null || !this.a.isVisible()) && event.target == this.d) {
                    final cF a = this.a();
                    if (a != null) {
                        super.e = true;
                        this.setCursor(Cursor.getPredefinedCursor(3));
                        (this.a = new bk(this.ab, this, a, this.k, this.i)).setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                        this.f.requestFocus();
                    }
                }
                else if (event.target == this.r) {
                    if (cf.d(63)) {
                        super.e = true;
                        cf.h(63);
                        this.f.b(cf, false);
                        this.r.a(ao.e("Remove"));
                        this.e(cf);
                    }
                    else {
                        super.e = true;
                        cf.n(63);
                        this.f.b(cf, true);
                        this.r.a(ao.e("Restore"));
                        this.d(cf);
                    }
                    cf.aw = true;
                    this.f.a((aU)cf);
                    this.f.requestFocus();
                }
                else if (event.target == this.x && this.f.c() > 0) {
                    if (((cF)this.f.a(0)).d(63)) {
                        for (int i = 0; i < this.f.c(); ++i) {
                            super.e = true;
                            final cF cf2 = (cF)this.f.a(i);
                            cf2.h(63);
                            this.f.b(cf2, false);
                            this.e(cf2);
                            this.f.a((aU)cf2);
                        }
                        this.x.a(ao.e("Remove All"));
                    }
                    else {
                        for (int j = 0; j < this.f.c(); ++j) {
                            super.e = true;
                            final cF cf3 = (cF)this.f.a(j);
                            cf3.n(63);
                            this.f.b(cf3, true);
                            this.d(cf3);
                            this.f.a((aU)cf3);
                            cf3.aw = true;
                        }
                        this.x.a(ao.e("Restore All"));
                    }
                    this.f.requestFocus();
                }
                return true;
            }
            case 701: {
                this.c((cF)event.arg);
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
    
    public void d() {
        this.f.f();
        this.x.b();
    }
    
    public boolean b(final cF cf) {
        return true;
    }
    
    public void d(final cF cf) {
    }
    
    public void e(final cF cf) {
    }
    
    public void b(final cF cf) {
        final int a = this.f.a((aU)cf);
        this.x.a(cf);
        if (a == -1) {
            this.f.c(cf);
            if (cf.d(62)) {
                this.b.a(cf, true);
            }
            if (this.a != null) {
                if (!this.b(cf)) {
                    this.a.dispose();
                    this.a = null;
                }
                else {
                    final cF a2 = this.a();
                    if (a2 != null) {
                        this.a.a(a2);
                    }
                    else {
                        this.a.dispose();
                        this.a = null;
                    }
                }
            }
        }
        else {
            this.f.b(cf, a);
            if (this.a != null) {
                this.a.dispose();
                this.a = null;
            }
        }
        this.c(cf);
    }
    
    public void f(final cF cf) {
        this.a.dispose();
        this.a = null;
    }
    
    public String a(final Object o) {
        final String lowerCase = this.ab.toLowerCase();
        if (o instanceof aS) {
            if (((aS)o).a()) {
                if (o == this.d) {
                    return am.a(ao.e("Click here to add a new %1."), new String[] { lowerCase });
                }
                if (o == this.c) {
                    return am.a(ao.e("Click here to edit the selected %1."), new String[] { lowerCase });
                }
                if (o == this.r) {
                    if (this.r.c().equals(ao.e("Remove"))) {
                        return am.a(ao.e("Click here to remove the selected %1."), new String[] { lowerCase });
                    }
                    return am.a(ao.e("Click here to restore the selected %1."), new String[] { lowerCase });
                }
            }
            else {
                if (this.f.d() < 0) {
                    return am.a(ao.e("This button is disabled because no %1 is selected."), new String[] { lowerCase });
                }
                if (o == this.c) {
                    return am.a(ao.e("This button is disabled because the selected %1 cannot be edited."), new String[] { lowerCase });
                }
                return am.a(ao.e("This button is disabled because the selected %1 cannot be removed."), new String[] { lowerCase });
            }
        }
        return null;
    }
    
    public void show() {
        super.show();
        this.f.requestFocus();
    }
    
    public int j() {
        final int b = this.x.b();
        int n = 0;
        for (int i = 0; i < b; ++i) {
            if (this.a(i).aw) {
                ++n;
            }
        }
        return n;
    }
    
    public int d() {
        return this.x.b();
    }
    
    public cF a(final int n) {
        return (cF)this.x.a(n);
    }
    
    public cF b(final int n) {
        return (cF)this.x.b(n);
    }
    
    public bF(final u i, final String s, final String ab) {
        super(s, i);
        this.c = new aS(80, 20);
        this.d = new aS(80, 20);
        this.r = new aS(80, 20);
        this.x = new aS(80, 20);
        this.a = null;
        this.k = false;
        this.e = null;
        this.b = new cf(null, "default");
        this.f = new j("ID");
        this.g = new j(ao.e("Name"), "name");
        this.i = i;
        this.ab = ab;
        this.x = new aG(10, 1000, ab);
        (this.f = new l()).b(this.f);
        this.f.b(this.g);
        this.f.a(true);
        this.f.c(true);
        this.g.c(true);
        this.f.a(this.f);
        this.f.a(2);
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
        this.f.resize(250, 220);
        final aR ar = new aR(this.f);
        layout.setConstraints(ar, gridBagConstraints);
        panel.add(ar);
        final FontMetrics fontMetrics = this.c.getFontMetrics(this.c.getFont());
        int stringWidth = fontMetrics.stringWidth(ao.e("Edit"));
        final int stringWidth2 = fontMetrics.stringWidth(ao.e("Remove"));
        final int stringWidth3 = fontMetrics.stringWidth(ao.e("Add"));
        final int stringWidth4 = fontMetrics.stringWidth(ao.e("Remove All"));
        if (stringWidth2 > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (stringWidth3 > stringWidth) {
            stringWidth = stringWidth3;
        }
        if (stringWidth4 > stringWidth) {
            stringWidth = stringWidth4;
        }
        stringWidth += 20;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        layout.setConstraints(this.c, gridBagConstraints);
        this.c.a(ao.e("Edit"));
        this.c.resize(stringWidth, 20);
        panel.add(this.c);
        this.r.a(ao.e("Remove"));
        this.r.resize(stringWidth, 20);
        layout.setConstraints(this.r, gridBagConstraints);
        panel.add(this.r);
        gridBagConstraints.anchor = 11;
        gridBagConstraints.gridheight = 1;
        this.d.a(ao.e("Add"));
        this.d.resize(stringWidth, 20);
        layout.setConstraints(this.d, gridBagConstraints);
        panel.add(this.d);
        this.x.a(ao.e("Remove All"));
        this.x.resize(stringWidth, 20);
        layout.setConstraints(this.x, gridBagConstraints);
        this.x.setVisible(false);
        panel.add(this.x);
        this.a(panel, 1, 1.0f, 1.0f);
    }
}
