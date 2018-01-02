import java.awt.Event;
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
    public Button a4;
    public b a3;
    public String a2;
    public f a1;
    public TextField l;
    
    public final String getName() {
        return this.a2;
    }
    
    public g(final int n, final int n2, final String a2, final Dimension dimension, final f a3) {
        this.a1 = a3;
        this.a2 = a2;
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
        (this.a3 = new b()).setFont(a3.az.gg);
        this.a3.setBackground(a3.az.gl.d7);
        this.a3.setForeground(a3.az.gl.d8);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.add("Center", this.a3);
        panel.add("North", canvas);
        panel.add("South", canvas2);
        panel.add("West", canvas3);
        panel.add("East", canvas4);
        final Panel panel2 = new Panel();
        final Panel panel3 = new Panel();
        panel2.setLayout(new BorderLayout(0, 0));
        this.a4 = new Button("Close");
        (this.l = new TextField(n)).setBackground(a3.az.gl.d0);
        this.l.setForeground(a3.az.gl.d1);
        this.l.setFont(a3.az.gg);
        panel2.add("North", this.l);
        panel3.setLayout(new BorderLayout());
        panel3.add("Center", panel2);
        panel3.add("East", this.a4);
        final Panel panel4 = new Panel();
        panel4.setLayout(new BorderLayout());
        panel4.add("Center", panel);
        panel4.add("South", panel3);
        this.setTitle("Chat with " + a2);
        this.setLayout(new BorderLayout());
        this.add("Center", panel4);
        this.resize(dimension);
        this.setBackground(a3.az.gl.d6);
        this.setForeground(a3.az.gl.d2);
    }
    
    public final void av() {
        for (int size = this.a1.al.size(), i = 0; i < size; ++i) {
            if (((g)this.a1.al.elementAt(i)).getName().equals(this.a2)) {
                this.a1.al.removeElementAt(i);
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.av();
            this.dispose();
        }
        else if (event.id == 1001) {
            if (event.target == this.a4) {
                this.av();
                this.dispose();
                return true;
            }
            if (event.target == this.l) {
                this.au();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void au() {
        final String text = this.l.getText();
        this.l.setText("");
        final String q = this.a1.q(text);
        if (q.startsWith("/clear")) {
            this.a3.g();
            return;
        }
        if (q.equals("")) {
            return;
        }
        this.a1.ai("PRIVMSG " + this.a2 + " :" + q);
        this.a3.h("> " + q);
    }
    
    public final void at(String string, final Color color) {
        string = "<" + this.a2 + "> " + string;
        this.a3.h(string, color);
    }
}
