// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Event;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.Button;
import java.awt.List;
import java.awt.Label;
import java.awt.Dialog;

public class maw extends Dialog
{
    private maa a;
    private md b;
    private mav[] c;
    private int d;
    private Label e;
    private List f;
    private Button g;
    private Button h;
    private mav i;
    
    public maw(final maa a, final String s, final md b, final mav[] c, final int d) {
        super(a, s, true);
        this.a = a;
        this.b = b;
        this.c = c;
        switch (this.d = d) {
            case 1: {
                this.e = new Label(b.fr);
                break;
            }
            case 2: {
                this.e = new Label(b.fs);
                break;
            }
            case 3: {
                this.e = new Label(b.ft);
                break;
            }
        }
        this.f = new List(10, false);
        for (int i = 0; i < c.length; ++i) {
            this.f.addItem(c[i].toString());
        }
        this.g = new Button(b.fu);
        this.h = new Button(b.fv);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(2, 2, 2));
        panel.add(this.g);
        panel.add(this.h);
        this.setLayout(new BorderLayout(2, 2));
        this.add("North", this.e);
        this.add("Center", this.f);
        this.add("South", panel);
        maj.a(this, a.d);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.f && event.id == 701) {
            this.i = this.c[(int)event.arg];
            return true;
        }
        if (event.id == 201) {
            this.b();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.g) {
            this.a();
            return true;
        }
        if (event.target == this.h) {
            this.b();
            return true;
        }
        return false;
    }
    
    private void a() {
        if (this.i != null) {
            this.a.a(this.i.a, this.i.b, this.d);
        }
        this.hide();
        this.dispose();
    }
    
    private void b() {
        this.hide();
        this.dispose();
    }
}
