// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Vector;
import java.awt.Component;
import java.awt.TextField;

public class bD extends bF
{
    private TextField a;
    private TextField b;
    private TextField c;
    private TextField d;
    private TextField e;
    private int Z;
    
    public cF a() {
        if (this.d() > 0) {
            this.Z = 2;
        }
        else {
            this.Z = 1;
        }
        final bg bg = new bg(this.d(), new bz());
        bg.b = true;
        return bg;
    }
    
    public void a(final cF cf) {
        final bg bg = (bg)cf;
        if (this.Z == 1) {
            if (bg.a != null) {
                this.c.setText(bg.a.a());
                this.d.setText(new Integer(bg.a.i).toString());
                this.e.setText(bg.a.toString());
            }
            this.c.enable(bg.b);
            this.d.enable(bg.b);
            this.e.enable(bg.b);
        }
        else if (this.Z == 2) {
            this.a.setText(bg.a.d());
            this.a.enable(bg.b);
            this.b.setText(bg.a.toString());
            this.b.enable(bg.b);
        }
    }
    
    public boolean a(final cF cf) {
        final bg bg = (bg)cf;
        if (this.Z == 1) {
            if (this.e.getText().length() == 0 || this.d.getText().length() == 0 || this.c.getText().length() == 0) {
                this.c.requestFocus();
                new E(this.b(), ao.e("Note"), ao.e("You must fill all the fields.  Please re-enter this information."), super.i).setVisible(true);
                return false;
            }
            int int1;
            try {
                int1 = Integer.parseInt(this.d.getText().trim());
            }
            catch (Exception ex) {
                this.a.requestFocus();
                new E(this.b(), ao.e("Note"), ao.e("This order number does not have a valid format, please re-enter the information."), super.i).setVisible(true);
                return false;
            }
            try {
                final bx bx = new bx(this.c.getText().trim(), this.e.getText().trim(), int1);
                if (!bx.j()) {
                    this.c.requestFocus();
                    new E(this.b(), ao.e("Note"), ao.e("This serial number is not valid, please re-enter the information."), super.i).setVisible(true);
                    return false;
                }
                bg.a(bx);
            }
            catch (Exception ex2) {
                this.c.requestFocus();
                new E(this.b(), ao.e("Note"), ao.e("This serial number does not have a valid format, please re-enter the information."), super.i).setVisible(true);
                return false;
            }
            cf.a = false;
            this.b(cf);
            this.f(null);
            this.Z = 2;
            super.d.c();
            return false;
        }
        else {
            final String text = this.b.getText();
            if (text.length() == 0 || this.a.getText().length() == 0) {
                this.a.requestFocus();
                new E(this.b(), ao.e("Note"), ao.e("You must provide a key/order#.  Please re-enter this information."), super.i).setVisible(true);
                return false;
            }
            if (this.a(text, cf)) {
                this.b.requestFocus();
                new E(this.b(), ao.e("Note"), ao.e("This key already exists."), super.i).setVisible(true);
                return false;
            }
            try {
                final bz bz = new bz(this.b.getText().trim(), this.a.getText().trim());
                if (!bz.l()) {
                    this.a.requestFocus();
                    new E(this.b(), ao.e("Note"), ao.e("This key is not valid, please re-enter the information."), super.i).setVisible(true);
                    return false;
                }
                bg.a(bz);
            }
            catch (Exception ex3) {
                this.b.requestFocus();
                new E(this.b(), ao.e("Note"), ao.e("This key does not have a valid format, please re-enter the information."), super.i).setVisible(true);
                return false;
            }
            return true;
        }
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
        bk.a();
        if (this.Z == 1) {
            bk.a(ao.e("Company name:"), this.c);
            bk.a(ao.e("Order #:"), this.d);
            bk.a(ao.e("Serial #:"), this.e);
        }
        else {
            bk.a(ao.e("Order #:"), this.a);
            bk.a(ao.e("Key    :"), this.b);
            bk.a(new c(ao.e("Please enter your registration information.")), 2, 1.0f, 0.0f);
        }
        bk.pack();
    }
    
    public void c() {
        final Vector vector = new Vector<Integer>();
        for (int i = 0; i < this.d(); ++i) {
            if (!((bg)this.a(i)).d(63)) {
                vector.addElement(new Integer(i));
            }
        }
        final cD cd = new cD(65797, vector.size());
        cd.j = -999;
        cd.o = -999;
        if (vector.size() > 0) {
            final bx a = ((bg)this.a(0)).a;
            final String a2 = a.a();
            final String string = new Integer(a.i).toString();
            final String string2 = a.toString();
            cd.a(0, 0, a2);
            cd.a(0, 1, string);
            cd.a(0, 2, string2);
            ((cI)super.i).b = a;
        }
        final Vector<bz> e = new Vector<bz>();
        for (int j = 1; j < vector.size(); ++j) {
            final bz a3 = ((bg)this.a((int)vector.elementAt(j))).a;
            final String string3 = a3.toString();
            final String d = a3.d();
            cd.a(j, 0, string3);
            cd.a(j, 1, d);
            e.addElement(a3);
        }
        super.i.e = e;
        super.i.o(cd);
    }
    
    public void d() {
        super.d();
        this.Z = 1;
        if (((cI)super.i).b != null && ((cI)super.i).b.n != 0L) {
            this.b((cF)new bg(0, ((cI)super.i).b));
            this.Z = 2;
            for (int i = 0; i < super.i.e.size(); ++i) {
                this.b((cF)new bg(i + 1, (bz)super.i.e.elementAt(i)));
            }
        }
    }
    
    public void c(final cF cf) {
        if (cf == null) {
            if (this.d() > 0) {
                super.d.c();
                this.Z = 2;
            }
            else {
                this.Z = 1;
            }
            super.c.d();
            return;
        }
        if (((bg)cf).a != null) {
            this.Z = 1;
            super.d.c();
        }
        else {
            this.Z = 2;
            super.d.c();
        }
        super.c(cf);
        super.c.d();
    }
    
    public bD(final u u) {
        super(u, ao.e("Keys"), ao.e("Key"));
        this.a = new TextField(24);
        this.b = new TextField(24);
        this.c = new TextField(24);
        this.d = new TextField(24);
        this.e = new TextField(24);
        this.Z = -1;
        super.c.d();
        super.r.d();
    }
}
