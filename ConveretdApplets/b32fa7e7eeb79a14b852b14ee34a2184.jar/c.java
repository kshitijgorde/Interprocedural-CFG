import java.awt.Event;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Point;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class c extends Frame
{
    static Point for;
    static Point new;
    static Point if;
    static TextField int;
    TextField do;
    JME a;
    
    c(final int n, final JME a) {
        this.a = a;
        this.setFont(a.bl);
        this.setBackground(JME.M);
        this.setResizable(false);
        if (n == 1) {
            this.if(a.if());
        }
        else if (n == 2) {
            this.a();
        }
        else {
            this.if();
        }
        this.pack();
        this.show();
    }
    
    void if() {
        this.setTitle("about JME");
        this.setLayout(new GridLayout(0, 1, 0, 0));
        this.setFont(this.a.bl);
        this.setBackground(JME.M);
        if (!this.a.bx) {
            this.add(new Label("JME Molecular Editor© v2004.10", 1));
            this.add(new Label("Peter Ertl, peter.ertl@pharma.novartis.com", 1));
            this.add(new Label("www.molinspiration.com/jme", 1));
        }
        else {
            this.add(new Label("JME Editor© v2004.10", 1));
            this.add(new Label("www.molinspiration.com", 1));
        }
        final Panel panel = new Panel();
        panel.add(new Button(" Close "));
        this.add(panel);
        this.setLocation(c.for);
    }
    
    void if(final String s) {
        this.setTitle("SMILES");
        this.setLayout(new BorderLayout(2, 0));
        this.do = new TextField(s + "     ");
        if (!this.a.aN) {
            this.do.setEditable(false);
        }
        this.add("Center", this.do);
        final Panel panel = new Panel();
        panel.add(new Button("Close"));
        if (this.a.aN) {
            panel.add(new Button("Submit"));
        }
        this.add("South", panel);
        this.do.setText(this.do.getText().trim());
        this.setResizable(true);
        this.setLocation(c.new);
    }
    
    void a(final String text) {
        final Dimension size = this.size();
        int n = this.a.aF.stringWidth(text) + 30;
        if (n < 150) {
            n = 150;
        }
        this.resize(n, size.height);
        this.validate();
        this.do.setText(text);
    }
    
    void a() {
        this.setTitle("nonstandard atom");
        this.setLayout(new BorderLayout(2, 0));
        final Panel panel = new Panel();
        panel.add(new Label("atomic SMILES", 1));
        this.add("North", panel);
        if (c.int == null) {
            c.int = new TextField("H", 8);
        }
        this.add("Center", c.int);
        final Panel panel2 = new Panel();
        panel2.add(new Button("Close "));
        this.add("South", panel2);
        this.setLocation(c.if);
    }
    
    public boolean action(final Event event, final Object o) {
        if (" Close ".equals(o)) {
            c.for = this.a.bp.getLocationOnScreen();
            this.hide();
        }
        else if ("Close".equals(o)) {
            c.new = this.a.L.getLocationOnScreen();
            this.hide();
        }
        else if ("Close ".equals(o)) {
            c.if = this.a.au.getLocationOnScreen();
            this.hide();
        }
        else if ("Submit".equals(o)) {
            this.a.readSmiles(this.do.getText());
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (c.int == null) {
            return false;
        }
        if (this.a.al != 1201) {
            this.a.al = 1201;
            this.a.bw = 18;
        }
        return false;
    }
    
    static {
        c.for = new Point(500, 10);
        c.new = new Point(200, 50);
        c.if = new Point(150, 420);
    }
}
