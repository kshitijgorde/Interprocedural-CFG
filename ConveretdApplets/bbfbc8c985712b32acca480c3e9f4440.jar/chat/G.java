// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.CheckboxGroup;
import java.awt.Component;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;

public final class G extends T
{
    private TextField a;
    private cr e;
    private boolean c;
    private Choice a;
    private Checkbox a;
    private Checkbox b;
    private Choice b;
    private I b;
    
    public final U a() {
        return new bX("");
    }
    
    public final boolean a(final cg cg, final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.a) {
                    this.a.setEnabled(true);
                    this.a.setEnabled(false);
                    return true;
                }
                if (event.target == this.b) {
                    this.a.setEnabled(true);
                    this.a.setEnabled(false);
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    public final void a(final U u) {
        final s s;
        final boolean a;
        if (a = (s = (s)u).a(2)) {
            this.a.select(ax.a(s.d));
            this.b.setState(true);
        }
        else {
            this.a.setText(s.d);
            this.a.select(0);
            this.a.setState(true);
        }
        this.a.setEnabled(!a);
        this.a.setEnabled(a);
        this.b.select(s.a);
    }
    
    public final boolean a(final U u) {
        final s s = (s)u;
        String text;
        if (this.a.getState()) {
            text = this.a.getText();
        }
        else {
            text = ax.a[this.a.getSelectedIndex()];
        }
        if (text.length() == 0) {
            this.a.requestFocus();
            new bD(this.a(), aS.a(1), aS.a(409), super.a).setVisible(true);
            return false;
        }
        s.d = text;
        s.a = this.b.getSelectedIndex();
        s.a(2, this.b.getState());
        return true;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.e) {
                    for (int a = super.a.a(), i = 0; i < a; ++i) {
                        final U u = (U)super.a.a(i);
                        if (this.c) {
                            super.a = true;
                            u.c(63);
                            u.j = 0;
                            super.a.b(u, false);
                            this.e.a(aS.a(411));
                        }
                        else {
                            super.a = true;
                            u.b(63);
                            u.j = 1;
                            super.a.b(u, true);
                            this.e.a(aS.a(412));
                        }
                    }
                    this.c = !this.c;
                }
                return super.handleEvent(event);
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public final void a(final cg cg) {
        cg.a(this.a, this.a, 0);
        if (super.a.a(69)) {
            cg.a(this.b, this.a, 0);
        }
        cg.a(aS.a(631), this.b, 0);
        cg.a(new A(aS.a(410)), 2, 1.0f, 0.0f);
    }
    
    public final void b() {
        if (super.a) {
            int n = 0;
            final r r;
            (r = new r(67339, this.b())).e = -1;
            r.d = -1;
            for (int i = 0; i < this.a(); ++i) {
                final s s;
                if ((s = (s)this.a(i)).j != 0) {
                    r.a(n, s.b);
                    r.a(n, 0, s.i);
                    r.a(n, 1, s.a);
                    if (!s.a(63)) {
                        r.a(n, 0, s.d);
                    }
                    ++n;
                }
            }
            super.a.o(r);
            super.a = false;
        }
    }
    
    public final void a() {
        super.a();
        try {
            for (int i = 0; i < super.a.j.a(); ++i) {
                this.b(new bX((s)super.a.j.a(i)));
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        this.c = false;
        this.e.a(aS.a(411));
        if (super.a.c() > 0) {
            this.e.a();
            return;
        }
        this.e.b();
    }
    
    public G(final cx cx) {
        super(cx, aS.a(109), aS.a(110));
        this.a = new TextField(30);
        (this.e = new cr(80, 20)).a(aS.a(411));
        this.a = new Choice();
        for (int i = 0; i < ax.b.length; ++i) {
            this.a.addItem(ax.b[i]);
        }
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.a = new Checkbox(aS.a("IP/Host:"), checkboxGroup, true);
        this.b = new Checkbox(aS.a(377), checkboxGroup, false);
        (this.b = new Choice()).setBackground(Color.white);
        this.b.setForeground(Color.black);
        this.b.addItem(aS.a(632));
        this.b.addItem(aS.a(609));
        this.b.addItem(aS.a(610));
        this.b.addItem(aS.a(611));
        this.b.addItem(aS.a(612));
        this.b.addItem(aS.a(613));
        this.b.addItem(aS.a(614));
        this.b.addItem(aS.a(615));
        (this.b = new I(aS.a(645), "date")).a(150);
        this.b.a = true;
        this.b.b = true;
        this.a(this.b);
        (this.b = new I(aS.a(631), "time")).a(60);
        this.b.a = true;
        this.a(this.b);
        (this.b = new I("User", "user")).a(150);
        this.b.a = true;
        this.b.b = true;
        this.a(this.b);
        super.a.a(110);
        this.b.a(120);
        this.a("", this.e);
        this.d();
    }
}
