import java.awt.Event;
import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class g extends Frame
{
    public Button a8;
    public b a7;
    public String a6;
    public f a5;
    public TextField l;
    
    public final String getName() {
        return this.a6;
    }
    
    public g(final int n, final int n2, final String a6, final Dimension dimension, final f a7) {
        this.a5 = a7;
        this.a6 = a6;
        final Canvas canvas = new Canvas();
        canvas.resize(10, 1);
        canvas.setBackground(Color.black);
        final Canvas canvas2 = new Canvas();
        canvas2.resize(10, 1);
        canvas2.setBackground(Color.black);
        final Canvas canvas3 = new Canvas();
        canvas3.resize(1, 10);
        canvas3.setBackground(Color.black);
        final Canvas canvas4 = new Canvas();
        canvas4.resize(1, 10);
        canvas4.setBackground(Color.black);
        (this.a7 = new b(200, 0)).setFont(a7.a1.g7);
        this.a7.setBackground(a7.a1.hc.er);
        this.a7.setForeground(a7.a1.hc.es);
        this.a7.j(a7.a1);
        this.a7.f(a7.a1.hc.bv);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.add("Center", this.a7);
        panel.add("North", canvas);
        panel.add("South", canvas2);
        panel.add("West", canvas3);
        panel.add("East", canvas4);
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        panel2.setLayout(new BorderLayout(0, 0));
        this.a8 = new Button(a7.a1.hc.cx);
        (this.l = new TextField(n)).setBackground(a7.a1.hc.ek);
        this.l.setForeground(a7.a1.hc.el);
        this.l.setFont(a7.a1.g7);
        panel2.add("North", this.l);
        panel3.setLayout(new BorderLayout());
        panel3.add("Center", panel2);
        panel3.add("East", this.a8);
        final Panel panel4 = new Panel();
        panel4.setLayout(new BorderLayout());
        panel4.add("Center", panel);
        panel4.add("South", panel3);
        this.setTitle(String.valueOf(a7.a1.hc.cw) + "" + a6);
        this.setLayout(new BorderLayout());
        this.add("Center", panel4);
        this.resize(dimension);
        this.setBackground(a7.a1.hc.eq);
        this.setForeground(a7.a1.hc.em);
    }
    
    public final void at() {
        for (int size = this.a5.an.size(), i = 0; i < size; ++i) {
            if (((g)this.a5.an.elementAt(i)).getName().equals(this.a6)) {
                this.a5.an.removeElementAt(i);
            }
        }
    }
    
    public final void processEvent(final AWTEvent awtEvent) {
        System.out.println("Window event of " + awtEvent.toString() + " ," + awtEvent.getID());
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.at();
            this.dispose();
        }
        else if (event.id == 1001) {
            if (event.target == this.a8) {
                this.at();
                this.dispose();
                return true;
            }
            if (event.target == this.l) {
                this.as();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void as() {
        final String text = this.l.getText();
        this.l.setText("");
        final String r = this.a5.r(text);
        if (r.startsWith("/clear")) {
            this.a7.h();
            return;
        }
        if (r.equals("")) {
            return;
        }
        this.a5.ag("PRIVMSG " + this.a6 + " :" + r);
        this.a7.i("> " + r);
    }
    
    public final void ar(String string, final Color color) {
        string = "<" + this.a6 + "> " + string;
        this.a7.i(string, color);
    }
}
