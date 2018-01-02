import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class f extends Dialog
{
    bc a;
    Frame b;
    GridBagLayout c;
    GridBagConstraints d;
    TextArea e;
    Button f;
    
    void a() {
        this.e.setText("");
        int n = 0;
        while (true) {
            Label_0071: {
                if (!bm.dX) {
                    break Label_0071;
                }
                this.e.appendText(this.a.b[n] + " " + this.a.c[n] + "\n");
                ++n;
            }
            if (n == this.a.d) {
                return;
            }
            continue;
        }
    }
    
    f(final Frame b, final boolean b2, final bc a) {
        super(b, bm.cP, b2);
        this.c = new GridBagLayout();
        this.d = new GridBagConstraints();
        this.e = new TextArea(10, 40);
        this.f = new Button(bm.cQ);
        this.b = b;
        this.a = a;
        this.setLayout(this.c);
        this.d.gridx = 0;
        this.d.gridy = 0;
        this.c.setConstraints(this.e, this.d);
        this.add(this.e);
        this.d.gridx = 0;
        this.d.gridy = 1;
        this.c.setConstraints(this.f, this.d);
        this.add(this.f);
        this.resize(600, 400);
    }
    
    void b() {
        String s = this.e.getText();
        int i = 0;
    Block_4:
        while (true) {
            final int index = s.indexOf(10);
            if (index >= 0) {
                final String s2 = new String(s.substring(0, index).trim());
                if (s2.length() > 3) {
                    this.a.c(s2);
                }
                s = s.substring(index + 1);
            }
            while (i < 0) {
                i = s.length();
                if (!bm.dX) {
                    break Block_4;
                }
            }
        }
        if (i > 3) {
            this.a.c(s.trim());
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.f) {
            this.b();
            this.hide();
        }
        return true;
    }
    
    void c() {
        this.a();
        this.show(true);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.hide();
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
}
