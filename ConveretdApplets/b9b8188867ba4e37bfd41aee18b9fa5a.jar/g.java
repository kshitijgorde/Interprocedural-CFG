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
    public Button a7;
    public b a6;
    public String a5;
    public f a4;
    public TextField l;
    
    public final String getName() {
        return this.a5;
    }
    
    public g(final int n, final int n2, final String a5, final Dimension dimension, final f a6) {
        this.a4 = a6;
        this.a5 = a5;
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
        (this.a6 = new b(200, 0)).setFont(a6.a0.hn);
        this.a6.setBackground(a6.a0.hs.e0);
        this.a6.setForeground(a6.a0.hs.e1);
        this.a6.j(a6.a0);
        this.a6.f(a6.a0.hs.bu);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.add("Center", this.a6);
        panel.add("North", canvas);
        panel.add("South", canvas2);
        panel.add("West", canvas3);
        panel.add("East", canvas4);
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        final Panel panel4 = new Panel();
        panel2.setLayout(new BorderLayout(0, 0));
        this.a8 = new Button(a6.a0.hs.c0);
        this.a7 = new Button(a6.a0.hs.cz);
        (this.l = new TextField(n)).setBackground(a6.a0.hs.eu);
        this.l.setForeground(a6.a0.hs.ev);
        this.l.setFont(a6.a0.hn);
        panel2.add("North", this.l);
        panel3.setLayout(new BorderLayout());
        panel4.add(this.a7);
        panel4.add(this.a8);
        panel3.add("Center", panel2);
        panel3.add("East", panel4);
        final Panel panel5 = new Panel();
        panel5.setLayout(new BorderLayout());
        panel5.setBackground(a6.a0.hs.e_);
        panel5.setForeground(a6.a0.hs.ew);
        panel5.add("Center", panel);
        panel5.add("South", panel3);
        this.setTitle(String.valueOf(a6.a0.hs.c_) + "" + a5);
        this.setLayout(new BorderLayout());
        this.add("Center", panel5);
        this.setBackground(a6.a0.hs.e_);
        this.setForeground(a6.a0.hs.ew);
        this.resize(dimension);
    }
    
    public final void aw() {
        for (int size = this.a4.am.size(), i = 0; i < size; ++i) {
            if (((g)this.a4.am.elementAt(i)).getName().equals(this.a5)) {
                this.a4.am.removeElementAt(i);
            }
        }
    }
    
    public final void processEvent(final AWTEvent awtEvent) {
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.aw();
            this.dispose();
        }
        else if (event.id == 1001) {
            if (event.target == this.a8) {
                this.aw();
                this.dispose();
                return true;
            }
            if (event.target == this.l) {
                this.av();
                return true;
            }
            if (event.target == this.a7) {
                this.a4.a0.bn(this.a5);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void av() {
        final String text = this.l.getText();
        this.l.setText("");
        final String r = this.a4.r(text);
        if (r.startsWith("/clear")) {
            this.a6.h();
            return;
        }
        if (r.startsWith("/me ")) {
            this.a4.ai("PRIVMSG " + this.a4.a0.hs.fa + " :" + this.a4.a3 + "ACTION " + r.substring(4) + this.a4.a3);
            this.a6.i("* " + r.substring(4));
            return;
        }
        if (r.equals("")) {
            return;
        }
        this.a4.ai("PRIVMSG " + this.a5 + " :" + r);
        this.a6.i("> " + r);
    }
    
    public final void au(String string, final Color color) {
        string = "<" + this.a5 + "> " + string;
        this.a6.i(string, color);
    }
    
    public final void at(final String s, final Color color) {
        this.a6.i(s, color);
    }
}
