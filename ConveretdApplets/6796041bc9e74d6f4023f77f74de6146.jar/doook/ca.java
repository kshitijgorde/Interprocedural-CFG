// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;

public class ca extends bF
{
    private TextField a;
    private TextField b;
    private TextField c;
    private TextField d;
    private int b;
    private Choice y;
    private Checkbox n;
    private aX e;
    private aX b;
    
    public void a(final bk bk) {
        bk.a(ao.e("Name:"), this.a);
        bk.a(ao.e("Topic:"), this.b);
        bk.a(ao.e("Password (Optional):"), this.c);
        bk.a(ao.e("Confirm Password:"), this.d);
        bk.a(ao.e("Maximum users:"), this.y);
        if (super.i.d(0)) {
            bk.a(ao.e("Room Color:"), this.e);
            bk.a(ao.e("Back Color:"), this.b);
        }
        final aF af = (aF)((cI)super.i).m.b(((cI)super.i).u);
        bk.a(this.n, 0, 0.0f, 0.0f);
        this.c.setEchoCharacter('*');
        this.d.setEchoCharacter('*');
    }
    
    public void a(final cF cf) {
        final bf bf = (bf)cf;
        this.a.setText(super.i.d(bf.f()));
        if (bf.a != null) {
            this.b.setText(bf.a);
        }
        else {
            this.b.setText("");
        }
        if (bf.a != null) {
            this.c.setText(bf.a.toString());
            this.d.setText(bf.a.toString());
        }
        else {
            this.c.setText("");
            this.d.setText("");
        }
        if (bf.Z > 0) {
            this.y.select(String.valueOf(bf.Z));
        }
        else {
            this.y.select(0);
        }
        this.e.a(new Color(cf.aN));
        this.b.a(new Color(cf.d));
        this.n.setState(bf.d(57));
    }
    
    public boolean a(final cF cf) {
        final String trim = this.a.getText().trim();
        String text = this.b.getText();
        final String text2 = this.c.getText();
        final String text3 = this.d.getText();
        final bf bf = (bf)cf;
        if (super.f.a((aU)cf) != -1) {}
        if (trim.length() == 0) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must provide a name for the Chat Room.  Please re-enter this information."), super.i).setVisible(true);
            return false;
        }
        this.l();
        final int ar = ((cI)super.i).aR;
        if (text2.length() > 0 && !text2.equals(text3)) {
            this.d.requestFocus();
            new E(this.b(), ao.e("Note"), new String[] { ao.e("Your confirmation password does not match the new password.  Please re-enter this information."), ao.e("Room passwords are optional; you may leave the password fields blank if you do not want to password protect the room.") }, super.i).setVisible(true);
            return false;
        }
        if (text.length() == 0) {
            text = null;
        }
        bf.a = text;
        bf.d(trim);
        if (text2.length() > 0) {
            bf.a = new a(text2);
        }
        else {
            bf.a = null;
        }
        if (this.y.getSelectedIndex() == 0) {
            bf.Z = 0;
        }
        else {
            bf.Z = Integer.parseInt(this.y.getSelectedItem());
        }
        bf.aN = this.e.aA;
        bf.d = this.b.aA;
        bf.a(57, this.n.getState());
        return true;
    }
    
    public cF a() {
        return new bf(-999, "");
    }
    
    public boolean k() {
        for (int i = 0; i < this.d(); ++i) {
            final av av = (av)this.a(i);
            if (av.d(62)) {
                return av.h() != this.b;
            }
        }
        return true;
    }
    
    public void c() {
        if (super.e || this.k()) {
            final cD cd = new cD(67330, this.j());
            cd.j = -1;
            cd.o = -1;
            cd.k = super.i.h();
            int i = 0;
            int n = 0;
            while (i < this.d()) {
                final av av = (av)this.a(i);
                if (av.aw) {
                    if (av.a != -999) {
                        final av av2 = (av)this.b(av.a);
                        if (av2 != null && av2.d(63)) {
                            av.n(63);
                        }
                    }
                    cd.a(n, av.d());
                    cd.a(n, 0, av.h());
                    if (!av.d(63)) {
                        cd.a(n, 1, av.a);
                        cd.a(n, 2, av.b);
                        cd.a(n, 3, av.Z);
                        cd.a(n, 5, av.aN);
                        cd.a(n, 6, av.d);
                        cd.a(n, 0, av.f());
                        cd.a(n, 1, av.a);
                        cd.a(n, 2, av.R);
                        cd.a(n, 0, av.a);
                        cd.a(n, av.d());
                    }
                    ++n;
                }
                ++i;
            }
            super.i.o(cd);
            super.e = false;
        }
    }
    
    public void b(final cF cf) {
        super.b(cf);
        super.f.a(cf, new Color(cf.aN), Color.white, cf.d);
    }
    
    public void d() {
        super.d();
        super.i.d.a(false);
        try {
            for (int i = 0; i < super.i.d.b(); ++i) {
                final av av = (av)super.i.d.a(i);
                if (av.d(62)) {
                    this.b = av.h();
                }
                this.b(new bf(av));
            }
        }
        finally {
            super.i.d.a();
        }
    }
    
    private final int l() {
        int n = 0;
        for (int i = 0; i < this.d(); ++i) {
            if (!this.a(i).d(63)) {
                ++n;
            }
        }
        return n;
    }
    
    public void e(final cF cf) {
        this.l();
        final int ar = ((cI)super.i).aR;
    }
    
    public ca(final u u) {
        super(u, ao.e("Rooms"), ao.e("Room"));
        this.a = new TextField(20);
        this.b = new TextField(30);
        this.c = new TextField(15);
        this.d = new TextField(15);
        this.y = new Choice();
        this.n = new Checkbox(ao.e("Suppress enter/exit messages"));
        (this.e = new aX(u)).a(u.k, false, true);
        (this.b = new aX(u)).a(u.k, false, true);
        final j j = new j(t.a, "lock");
        j.b(16);
        this.a(super.b, 0);
        this.a(j, 2);
        j.c(0);
        this.y.addItem(ao.e("No Maximum"));
        this.y.addItem("2");
        this.y.addItem("3");
        this.y.addItem("4");
        this.y.addItem("5");
        this.y.addItem("10");
        this.y.addItem("15");
        this.y.addItem("20");
        this.y.addItem("25");
        this.y.addItem("30");
        this.y.addItem("35");
        this.y.addItem("40");
        this.y.addItem("45");
        this.y.addItem("50");
        this.y.addItem("75");
        this.y.addItem("100");
    }
}
