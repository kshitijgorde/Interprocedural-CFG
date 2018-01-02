// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.CheckboxGroup;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.TextArea;
import java.awt.TextField;

public class bW extends bF
{
    private TextField a;
    private TextField b;
    TextArea d;
    private bV b;
    private Checkbox J;
    private Checkbox d;
    private Choice f;
    private boolean j;
    private boolean k;
    private long r;
    private Choice A;
    private Checkbox M;
    private aX o;
    private aX p;
    private aX b;
    private Panel f;
    
    public cF a() {
        final bb bb = new bb(-999, "");
        bb.a = "";
        return bb;
    }
    
    public void a(final cF cf) {
        final aK ak = (aK)cf;
        this.a.setText(ak.f());
        this.b.a(ak.h);
        if (this.b.g() < 0) {
            this.b.b(0);
        }
        this.d.setText(ak.a);
        this.M.setState(ak.d(21));
        if (ak.d(22)) {
            this.A.select(1);
        }
        else if (ak.d(23)) {
            this.A.select(2);
        }
        else {
            this.A.select(0);
        }
        this.o.a(new Color(ak.as));
        this.p.a(new Color(ak.aN));
        this.b.a(new Color(ak.d));
    }
    
    public boolean a(final cF cf) {
        final aK ak = (aK)cf;
        final String text = this.a.getText();
        final String text2 = this.d.getText();
        if (text.length() == 0) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must provide a name for this DigiCast.  Please re-enter this information."), super.i).setVisible(true);
            return false;
        }
        if (text2.length() == 0) {
            this.d.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must provide a message for this DigiCast.  Please re-enter this information."), super.i).setVisible(true);
            return false;
        }
        ak.a = text2;
        ak.d(text);
        ak.h = this.b.a().h();
        ak.as = this.o.aA;
        ak.aN = this.p.aA;
        ak.d = this.b.aA;
        ak.a(21, this.M.getState());
        ak.a(22, this.A.getSelectedIndex() == 1);
        ak.a(23, this.A.getSelectedIndex() == 2);
        return true;
    }
    
    public void a(final bk bk) {
        bk.a(ao.e("Name:"), this.a, this.o, 0);
        bk.a(ao.e("DigiCast:"), this.d, this.f, 0);
        bk.a(ao.e("User Type:"), this.A);
        bk.a("", this.M);
        this.b.resize(200, 82);
        bk.a(this.b, 1, 1.0f, 1.0f);
    }
    
    public void c() {
        final boolean state = this.d.getState();
        final boolean b = this.f.getSelectedIndex() == 1;
        long n;
        try {
            n = Integer.parseInt(this.b.getText().trim());
            if ((n < 10L && !b) || n < 1L) {
                this.b.selectAll();
                this.b.requestFocus();
                throw new aq(am.a(ao.e("The %1 display time must be at least ten seconds long.  Please specify a larger value."), new String[] { "DigiCast" }));
            }
        }
        catch (NumberFormatException ex) {
            this.b.selectAll();
            this.b.requestFocus();
            throw new aq(am.a(ao.e("The %1 display time you entered is invalid."), new String[] { "DigiCast" }));
        }
        if (super.e || this.j != state || this.k != b || this.r != n) {
            final cD cd = new cD(67332, this.d());
            cd.j = -1;
            cd.o = -1;
            for (int i = 0; i < this.d(); ++i) {
                final aK ak = (aK)this.a(i);
                cd.a(i, ak.d());
                cd.a(i, 0, ak.h());
                if (!ak.d(63)) {
                    cd.a(i, 1, ak.i);
                    cd.a(i, 2, ak.h);
                    cd.a(i, 3, ak.as);
                    cd.a(i, 4, ak.aN);
                    cd.a(i, 5, ak.d);
                    cd.a(i, 0, ak.f());
                    cd.a(i, 1, ak.a);
                }
                cd.a(i, 21, ak.d(21));
                cd.a(i, 22, ak.d(22));
                cd.a(i, 23, ak.d(23));
            }
            cd.a(-1, n);
            cd.a(-1, 62, state);
            cd.a(-1, 32, b);
            super.i.o(cd);
            super.e = false;
        }
    }
    
    public void d() {
        super.d();
        super.i.f.a(false);
        try {
            for (int i = 0; i < super.i.f.b(); ++i) {
                this.b(new bb((aK)super.i.f.a(i)));
            }
        }
        finally {
            super.i.f.a();
        }
        this.k = cD.a(super.i.g, 32);
        if (this.k) {
            this.f.select(1);
        }
        else {
            this.f.select(0);
        }
        this.j = cD.a(super.i.g, 62);
        if (this.j) {
            this.d.setState(true);
        }
        else {
            this.J.setState(true);
        }
        this.r = (super.i.g & 0xFFFFFFFFL);
        this.b.setText(String.valueOf(this.r));
    }
    
    public void b(final cF cf) {
        super.b(cf);
        super.f.a(cf, new Color(((aK)cf).as), Color.white, cf.d);
    }
    
    public bW(final u u) {
        super(u, ao.e("DigiCast"), ao.e("DigiCast"));
        this.a = new TextField(30);
        this.b = new TextField(5);
        this.d = new TextArea("", 5, 30, 1);
        this.b = new bV();
        this.f = new Choice();
        (this.o = new aX(u, this.a, true)).a(u.k, false, true);
        this.o.a(Color.black);
        (this.p = new aX(u, this.d, true)).a(u.k, false, true);
        this.p.a(Color.black);
        (this.b = new aX(u, this.d, "setBackground")).a(u.k, false, true);
        this.b.a(bR.a);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.f = new Panel(gridBagLayout);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagLayout.setConstraints(this.p, gridBagConstraints);
        this.f.add(this.p);
        gridBagConstraints.gridx = 1;
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.f.add(this.b);
        final j j = new j("Icon");
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.J = new Checkbox(ao.e("Sequentially"), checkboxGroup, false);
        this.d = new Checkbox(ao.e("In random order"), checkboxGroup, false);
        this.M = new Checkbox(ao.e("This is a welcome message."));
        (this.A = new Choice()).addItem(ao.e("For All Users"));
        this.A.addItem(ao.e("For Super Users Only"));
        this.A.add(ao.e("For Guest Users Only"));
        this.f.addItem(ao.e("seconds"));
        this.f.addItem(ao.e("minutes"));
        this.a(ao.e("Display:"), this.J);
        this.a("", this.d);
        this.a(ao.e("Display every: "), this.b, this.f);
        this.b.a();
        u.b.a(false);
        try {
            for (int i = 0; i < u.b.b(); ++i) {
                this.b.a((as)u.b.a(i));
            }
        }
        finally {
            u.b.a();
        }
    }
}
