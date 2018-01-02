// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.TextField;

public class bp extends bF
{
    private TextField a;
    
    public cF a() {
        return new aY(-999, "");
    }
    
    public void a(final cF cf) {
        this.a.setText(((aD)cf).f());
    }
    
    public boolean a(final cF cf) {
        final aD ad = (aD)cf;
        final String text = this.a.getText();
        if (text.length() == 0) {
            this.a.requestFocus();
            new E(this.b(), ao.e("Note"), ao.e("You must enter a full host name or IP address (e.g., \"123.234.56.78\" or \"www.host.com\")."), super.i).setVisible(true);
            return false;
        }
        ad.d(text);
        return true;
    }
    
    public void a(final bk bk) {
        bk.a(ao.e("IP/Host:"), this.a);
        bk.a(new c(ao.e("You may enter a full host name or IP address above.  Clients will only be allowed to connect from web pages from this host.")), 2, 1.0f, 0.0f);
    }
    
    public void c() {
        if (super.e) {
            final cD cd = new cD(16844556, this.d());
            cd.j = -1;
            cd.o = -1;
            for (int i = 0; i < this.d(); ++i) {
                final aD ad = (aD)this.a(i);
                cd.a(i, ad.d());
                cd.a(i, 0, ad.h());
                if (!ad.d(63)) {
                    cd.a(i, 0, ad.f());
                }
            }
            super.i.o(cd);
            super.e = false;
        }
    }
    
    public void d() {
        super.d();
        super.i.o.a(false);
        try {
            for (int i = 0; i < super.i.o.b(); ++i) {
                this.b((cF)new aY((aD)super.i.o.a(i)));
            }
        }
        finally {
            super.i.o.a();
        }
    }
    
    public bp(final u u) {
        super(u, ao.e("Hosts"), ao.e("Host"));
        this.a = new TextField(30);
    }
}
