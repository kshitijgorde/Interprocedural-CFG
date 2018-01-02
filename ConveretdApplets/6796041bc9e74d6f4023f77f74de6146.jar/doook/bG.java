// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Component;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextField;

public class bG extends bF
{
    private TextField a;
    private TextField b;
    private TextField c;
    private aS d;
    private Checkbox J;
    private Checkbox d;
    private TextField H;
    private TextField I;
    private TextField J;
    private Checkbox K;
    private Checkbox L;
    private aS y;
    private Choice z;
    
    public cF a() {
        final bj bj = new bj(-999, "");
        bj.a = true;
        bj.l(super.x.g());
        return bj;
    }
    
    public void a(final cF cf) {
        final bj bj = (bj)cf;
        this.a.setText(bj.h() + "");
        this.a.setEnabled(bj.a);
        this.b.setText(bj.f());
        this.b.setEnabled(bj.a);
        this.J.setEnabled(((cI)super.i).ay);
        this.J.setState(bj.d(61));
        this.d.setState(bj.d(56));
        this.c.setText(new Integer(bj.h).toString());
        this.J.setText(bj.U);
        this.H.setText(bj.S);
        this.I.setText(bj.T);
        this.L.setState(bj.ai);
        this.K.setState(bj.aj);
    }
    
    public boolean a(final cF cf) {
        final aF af = (aF)cf;
        final String text = this.b.getText();
        final int a = super.f.a((aU)cf);
        int n = 0;
        if (a != -1) {
            n = 1;
        }
        if (this.a(Integer.parseInt(this.a.getText()), cf)) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("A site with this ID already exists.  Please choose another ID for this site."), super.i).setVisible(true);
            return false;
        }
        if (Integer.parseInt(this.a.getText()) < 1000) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must specify a site ID greater than 1000.  Please choose another ID for this site."), super.i).setVisible(true);
            return false;
        }
        if (text.length() == 0) {
            this.b.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must provide a name for this site.  Please re-enter this information."), super.i).setVisible(true);
            return false;
        }
        if (this.J.getText().length() == 0) {
            this.J.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must provide the ChatMaster Password for this site.  Please re-enter this information."), super.i).setVisible(true);
            return false;
        }
        if (this.H.getText().length() == 0) {
            this.H.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must provide the FTP Password for this site.  Please re-enter this information."), super.i).setVisible(true);
            return false;
        }
        if (this.a(text, cf)) {
            this.b.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("A site with this name already exists.  Please choose another name for this site."), super.i).setVisible(true);
            return false;
        }
        final int k = this.k();
        final int ap = ((cI)super.i).aP;
        if (ap != 1023 && k - n >= ap) {
            new E(a(this), ao.e("Operation not allowed"), new String[] { am.a(ao.e("Your license only allows %1 sites."), new String[] { String.valueOf(ap) }) }, super.i).setVisible(true);
            return false;
        }
        for (int i = 0; i < text.length(); ++i) {
            final char char1 = text.charAt(i);
            if (char1 != '_' && !Character.isLetterOrDigit(char1)) {
                this.b.requestFocus();
                new E(this.b(), ao.e("Note"), ao.e("Site names may contain only letters, digits, and the underscore character (_).  Please choose another name for this site."), super.i).setVisible(true);
                return false;
            }
        }
        final String trim = this.c.getText().trim();
        int int1;
        try {
            int1 = Integer.parseInt(trim);
        }
        catch (NumberFormatException ex) {
            this.c.requestFocus();
            this.c.selectAll();
            new E(this.b(), ao.e("Note"), ao.e("The user count you entered is invalid. Please re-enter."), super.i).setVisible(true);
            return false;
        }
        af.d(text);
        af.a(31, this.L.getState());
        af.a(32, this.K.getState());
        af.ai = this.L.getState();
        af.aj = this.K.getState();
        af.h = int1;
        af.l(Integer.parseInt(this.a.getText()));
        af.U = this.J.getText();
        af.S = this.H.getText();
        af.T = this.I.getText();
        return true;
    }
    
    public void e(final cF cf) {
        final int k = this.k();
        final int ap = ((cI)super.i).aP;
        if (ap != 1023 && k > ap) {
            new E(a(this), ao.e("Operation not allowed"), new String[] { am.a(ao.e("Your license only allows %1 sites."), new String[] { String.valueOf(ap) }) }, super.i).setVisible(true);
            this.handleEvent(new Event(super.r, 1001, null));
        }
    }
    
    public boolean a(final int n, final cF cf) {
        for (int i = 0; i < this.d(); ++i) {
            final cF a = this.a(i);
            if (cf != a && a.h() == n) {
                return true;
            }
        }
        return false;
    }
    
    public boolean a(final String s, final cF cf) {
        for (int i = 0; i < this.d(); ++i) {
            final cF a = this.a(i);
            if (cf != a && a.f().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    public void a(final bk bk) {
        bk.a(new c(ao.e("Each site must have a unique name.  This name is not displayed to users, but is used to identify the site and store its settings and resources on the server.")), 2, 1.0f, 0.0f);
        bk.a(ao.e("Name:"), this.b);
        bk.a(ao.e("Site ID:"), this.a);
        bk.a(ao.e("ChatMaster Password:"), this.J);
        bk.a(ao.e("FTP Password:"), this.H);
        bk.a(ao.e("Service End Date:"), this.I);
        bk.a(ao.e("Max users (0 for unlimited):"), this.c);
        bk.a(ao.e("Disable Auto Login:"), this.L);
        bk.a(ao.e("Disable All Super Users:"), this.K);
    }
    
    public void c() {
        final int ar = ((cI)super.i).aR;
        final cD cd = new cD(67337, this.d());
        cd.j = -1;
        cd.o = -1;
        int n = 0;
        for (int i = 0; i < this.d(); ++i) {
            final aF af = (aF)this.a(i);
            cd.a(i, af.d());
            cd.a(i, 0, af.h());
            cd.a(i, 1, af.U);
            cd.a(i, 2, af.S);
            cd.a(i, 3, af.T);
            cd.a(i, 1, af.h);
            cd.a(i, 2, ar);
            cd.a(i, 31, af.ai);
            cd.a(i, 32, af.aj);
            if (!af.d(63)) {
                cd.a(i, 0, af.f());
                ++n;
            }
        }
        super.i.o(cd);
    }
    
    public void g(final cF cf) {
        final cD cd = new cD(33622276, 1);
        cd.a(0, 0, cf.h());
        super.i.o(cd);
    }
    
    public void h(final cF cf) {
        if (this.z.getSelectedItem() == "--- Select Backup File ---" || this.z.getSelectedItem().trim() == "") {
            new E(this.b(), ao.e("Note"), ao.e("You must provide select Backup files to restore."), super.i).setVisible(true);
            return;
        }
        new bt(null, super.i, cf, this.z.getSelectedItem()).setVisible(true);
    }
    
    public void c(final cF cf) {
        this.z.removeAll();
        this.z.add("--- Select Backup File ---");
        if (cf == null || cf.h() == 0) {
            this.d.d();
            this.y.d();
        }
        else if (cf.h() != 0) {
            this.d.c();
            if (((aF)cf).V != null && ((aF)cf).V.trim() != "") {
                int n = 0;
                for (int i = ((aF)cf).V.indexOf(44, n + 1); i != -1; i = ((aF)cf).V.indexOf(44, n + 1)) {
                    this.z.add(((aF)cf).V.substring(n, i));
                    n = i + 1;
                }
                this.z.add(((aF)cf).V.substring(n));
                this.y.c();
            }
            else {
                this.y.d();
            }
        }
        final cF cf2 = (cF)super.f.a();
        super.c(cf);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final cF cf = (cF)super.f.a();
                if (event.target == this.d) {
                    this.g(cf);
                }
                else if (event.target == this.y) {
                    this.h(cf);
                }
                return super.handleEvent(event);
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
    
    private final int k() {
        int n = 0;
        for (int i = 0; i < this.d(); ++i) {
            if (!((aF)this.a(i)).d(63)) {
                ++n;
            }
        }
        return n;
    }
    
    public void d() {
        super.d();
        super.i.m.a(false);
        try {
            for (int i = 0; i < super.i.m.b(); ++i) {
                final bj bj = new bj((aF)super.i.m.a(i));
                super.f.a(bj, bj.h() != 0);
                bj.a = false;
                bj.a = (bj.h() != 0);
                bj.U = ((aF)super.i.m.a(i)).U;
                bj.S = ((aF)super.i.m.a(i)).S;
                bj.T = ((aF)super.i.m.a(i)).T;
                bj.V = ((aF)super.i.m.a(i)).V;
                bj.ai = ((aF)super.i.m.a(i)).ai;
                bj.aj = ((aF)super.i.m.a(i)).aj;
                bj.U = ((aF)super.i.m.a(i)).U;
                this.b(bj);
            }
        }
        finally {
            super.i.m.a();
        }
    }
    
    private static final Frame a(final Component component) {
        if (component instanceof Frame) {
            return (Frame)component;
        }
        return a(component.getParent());
    }
    
    public void b(final cF cf) {
        super.x.a(cf, cf.h());
        final int a = super.f.a((aU)cf);
        if (a == -1) {
            super.f.c(cf);
            if (cf.d(62)) {
                super.b.a(cf, true);
            }
            if (super.a != null) {
                if (!this.b(cf)) {
                    super.a.dispose();
                    super.a = null;
                }
                else {
                    final cF a2 = this.a();
                    if (a2 != null) {
                        super.a.a(a2);
                    }
                    else {
                        super.a.dispose();
                        super.a = null;
                    }
                }
            }
        }
        else {
            super.f.b(cf, a);
            if (super.a != null) {
                super.a.dispose();
                super.a = null;
            }
        }
        this.c(cf);
    }
    
    public bG(final u u) {
        super(u, ao.e("Sites"), ao.e("Site"));
        this.a = new TextField(20);
        this.b = new TextField(20);
        this.c = new TextField(5);
        this.J = new TextField(20);
        this.H = new TextField(20);
        this.d = new aS(80, 20);
        this.J = new Checkbox();
        this.d = new Checkbox();
        this.y = new aS(80, 20);
        (this.z = new Choice()).setForeground(Color.black);
        this.z.add("--- Select Backup File ---");
        this.I = new TextField(20);
        (this.L = new Checkbox()).setState(true);
        this.K = new Checkbox();
        this.d.a(ao.e("Restart Site"));
        int stringWidth = this.d.getFontMetrics(this.d.getFont()).stringWidth(ao.e("Restart Site"));
        stringWidth += 20;
        this.d.resize(stringWidth, 20);
        this.y.a(ao.e("Restore"));
        int stringWidth2 = this.y.getFontMetrics(this.y.getFont()).stringWidth(ao.e("Restore"));
        stringWidth2 += 20;
        this.y.resize(stringWidth2, 20);
        this.a(null, new Component[] { this.d, this.z, this.y });
        this.d.d();
        this.y.d();
    }
}
