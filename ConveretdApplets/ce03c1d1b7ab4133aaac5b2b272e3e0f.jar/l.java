import java.awt.Label;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class l extends Panel implements Runnable
{
    int f;
    int i;
    TextField e;
    TextField a;
    TextField d;
    a b;
    g j;
    Thread g;
    int c;
    Font h;
    
    public void a(final int c) {
        this.c = c;
        (this.g = new Thread(this)).start();
    }
    
    public void run() {
        while (this.c >= 0) {
            this.e.setText(new StringBuffer().append(this.c).toString());
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
            --this.c;
        }
    }
    
    public void paint(final Graphics graphics) {
    }
    
    private final void a() {
        this.h = new Font("Dialog", 0, 18);
    }
    
    public l(final int f, final int i) {
        this.a();
        this.f = f;
        this.i = i;
        this.setLayout(null);
        this.setBackground(new Color(33, 33, 33));
        this.setFont(this.h);
        int n = 5;
        (this.b = new a(true, 16, 60, false)).reshape(this.f - 26, n, this.b.p, this.b.w);
        this.add(this.b);
        (this.j = new g(this.f - 36, 60, this.b)).reshape(10, n, this.j.g, this.j.k);
        this.j.setBackground(Color.white);
        this.add(this.j);
        n += 65;
        final int n2 = 60;
        final int n3 = 100;
        (this.a = new TextField("000")).reshape(10, n, n2, 24);
        this.a.setEditable(false);
        this.a.setForeground(Color.black);
        this.a.setBackground(Color.white);
        this.add(this.a);
        final Label label = new Label("Human");
        label.reshape(n2 + 20, n, n3, 24);
        label.setForeground(k.k);
        this.add(label);
        (this.e = new TextField("00")).setEditable(false);
        this.e.reshape(this.f / 2 - 20, n, 40, 24);
        this.e.setForeground(Color.red);
        this.add(this.e);
        (this.d = new TextField("000")).setEditable(false);
        this.d.setForeground(Color.black);
        this.d.setBackground(Color.white);
        this.d.reshape(this.f - n2 - 10, n, n2, 24);
        this.add(this.d);
        final Label label2 = new Label("Computer");
        label2.setAlignment(2);
        label2.setForeground(e.k);
        label2.reshape(this.f - n2 - 10 - n3 - 10, n, n3, 24);
        this.add(label2);
    }
}
