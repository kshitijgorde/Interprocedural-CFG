import java.awt.Event;
import java.awt.Component;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Point;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class b extends Frame
{
    static Point d;
    static TextField byte;
    static Button l;
    static Button b;
    static Button void;
    static Button char;
    static Button long;
    static Button j;
    static Button int;
    static Button m;
    static Button g;
    static Button case;
    static Button try;
    static Button h;
    static Button e;
    static Button null;
    static Button do;
    static Button if;
    static Button new;
    static Button i;
    static Button f;
    static Button c;
    static Button for;
    static Choice n;
    static Choice k;
    Color a;
    static boolean else;
    JME goto;
    
    b(final JME goto1) {
        super("Atom/Bond Query");
        this.a = JME.M;
        this.goto = goto1;
        this.setLayout(new GridLayout(0, 1));
        this.setFont(goto1.bl);
        this.setBackground(this.a);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(0, 3, 1));
        panel.add(new Label("Atom type :"));
        final boolean b = b.case == null;
        if (b) {
            b.case = new Button("Any");
            b.try = new Button("Any except C");
            b.h = new Button("Halogen");
        }
        panel.add(b.case);
        panel.add(b.try);
        panel.add(b.h);
        this.add(panel);
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout(0, 3, 1));
        panel2.add(new Label("Or select one or more from the list :", 0));
        this.add(panel2);
        final Panel panel3 = new Panel();
        panel3.setLayout(new FlowLayout(0, 3, 1));
        if (b) {
            b.l = new Button("C");
            b.b = new Button("N");
            b.void = new Button("O");
            b.char = new Button("S");
            b.long = new Button("P");
            b.j = new Button("F");
            b.int = new Button("Cl");
            b.m = new Button("Br");
            b.g = new Button("I");
        }
        panel3.add(b.l);
        panel3.add(b.b);
        panel3.add(b.void);
        panel3.add(b.char);
        panel3.add(b.long);
        panel3.add(b.j);
        panel3.add(b.int);
        panel3.add(b.m);
        panel3.add(b.g);
        this.add(panel3);
        final Panel panel4 = new Panel();
        panel4.setLayout(new FlowLayout(0, 3, 1));
        if (b) {
            (b.k = new Choice()).addItem("Any");
            b.k.addItem("0");
            b.k.addItem("1");
            b.k.addItem("2");
            b.k.addItem("3");
        }
        panel4.add(new Label("Number of hydrogens :  "));
        panel4.add(b.k);
        this.add(panel4);
        final Panel panel5 = new Panel();
        panel5.setLayout(new FlowLayout(0, 3, 1));
        if (b) {
            (b.n = new Choice()).addItem("Any");
            b.n.addItem("0");
            b.n.addItem("1");
            b.n.addItem("2");
            b.n.addItem("3");
            b.n.addItem("4");
            b.n.addItem("5");
            b.n.addItem("6");
        }
        panel5.add(new Label("Number of connections :", 0));
        panel5.add(b.n);
        panel5.add(new Label(" (H's don't count.)", 0));
        this.add(panel5);
        final Panel panel6 = new Panel();
        panel6.setLayout(new FlowLayout(0, 3, 1));
        panel6.add(new Label("Atom is :"));
        if (b) {
            b.e = new Button("Aromatic");
        }
        panel6.add(b.e);
        if (b) {
            b.null = new Button("Nonaromatic");
        }
        panel6.add(b.null);
        if (b) {
            b.do = new Button("Ring");
        }
        panel6.add(b.do);
        if (b) {
            b.if = new Button("Nonring");
        }
        panel6.add(b.if);
        this.add(panel6);
        final Panel panel7 = new Panel();
        panel7.setBackground(this.getBackground().darker());
        panel7.setLayout(new FlowLayout(0, 3, 1));
        panel7.add(new Label("Bond is :"));
        if (b) {
            b.new = new Button("Any");
        }
        panel7.add(b.new);
        if (b) {
            b.i = new Button("Aromatic");
        }
        panel7.add(b.i);
        if (b) {
            b.f = new Button("Ring");
        }
        panel7.add(b.f);
        if (b) {
            b.c = new Button("Nonring");
        }
        panel7.add(b.c);
        this.add(panel7);
        final Panel panel8 = new Panel();
        panel8.setLayout(new FlowLayout(1, 3, 1));
        if (b) {
            b.byte = new TextField("*", 20);
        }
        panel8.add(b.byte);
        panel8.add(new Button("Reset"));
        panel8.add(new Button("Close"));
        this.add(panel8);
        this.setResizable(false);
        if (b) {
            this.for();
            this.a();
            this.do();
            b.e.setBackground(this.a);
            b.null.setBackground(this.a);
            b.do.setBackground(this.a);
            b.if.setBackground(this.a);
            b.k.setBackground(this.a);
            b.n.setBackground(this.a);
            this.a(b.case);
        }
        this.pack();
        this.setLocation(b.d);
        this.show();
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("Close")) {
            b.d = this.getLocationOnScreen();
            this.hide();
        }
        else if (o.equals("Reset")) {
            this.int();
            this.a(b.case);
            this.if();
        }
        else if (event.target instanceof Button) {
            this.do();
            if (event.target == b.case) {
                this.for();
                this.a();
            }
            else if (event.target == b.try) {
                this.for();
                this.a();
            }
            else if (event.target == b.h) {
                this.for();
                this.a();
            }
            else if (event.target == b.do) {
                b.if.setBackground(this.a);
            }
            else if (event.target == b.if) {
                b.do.setBackground(this.a);
                b.e.setBackground(this.a);
            }
            else if (event.target == b.e) {
                b.null.setBackground(this.a);
                b.if.setBackground(this.a);
            }
            else if (event.target == b.null) {
                b.e.setBackground(this.a);
            }
            else if (event.target == b.new || event.target == b.i || event.target == b.f || event.target == b.c) {
                this.int();
                b.else = true;
            }
            else {
                this.a();
            }
            this.a((Button)event.target);
            this.if();
        }
        else if (event.target instanceof Choice) {
            this.do();
            final Choice choice = (Choice)event.target;
            if (choice.getSelectedIndex() == 0) {
                choice.setBackground(this.a);
            }
            else {
                choice.setBackground(Color.orange);
            }
            this.if();
        }
        if (this.goto.al != 107) {
            this.goto.al = 107;
            this.goto.repaint();
        }
        return true;
    }
    
    private void int() {
        this.for();
        this.a();
        b.k.select(0);
        b.n.select(0);
        b.e.setBackground(this.a);
        b.null.setBackground(this.a);
        b.do.setBackground(this.a);
        b.if.setBackground(this.a);
        b.k.setBackground(this.a);
        b.n.setBackground(this.a);
        this.do();
    }
    
    private void for() {
        b.l.setBackground(this.a);
        b.b.setBackground(this.a);
        b.void.setBackground(this.a);
        b.char.setBackground(this.a);
        b.long.setBackground(this.a);
        b.j.setBackground(this.a);
        b.int.setBackground(this.a);
        b.m.setBackground(this.a);
        b.g.setBackground(this.a);
    }
    
    private void a() {
        b.case.setBackground(this.a);
        b.try.setBackground(this.a);
        b.h.setBackground(this.a);
    }
    
    private void do() {
        b.new.setBackground(this.a);
        b.i.setBackground(this.a);
        b.f.setBackground(this.a);
        b.c.setBackground(this.a);
        b.else = false;
    }
    
    private void a(final Button button) {
        if (button.getBackground() == this.a) {
            button.setBackground(Color.orange);
        }
        else {
            button.setBackground(this.a);
        }
    }
    
    private void if() {
        String s = "";
        boolean b = false;
        if (b.case.getBackground() != this.a) {
            s = "*";
            b = true;
        }
        else if (b.try.getBackground() != this.a) {
            s = "!#6";
            b = true;
        }
        else if (b.h.getBackground() != this.a) {
            b.j.setBackground(Color.orange);
            b.int.setBackground(Color.orange);
            b.m.setBackground(Color.orange);
            b.g.setBackground(Color.orange);
            s = "F,Cl,Br,I";
        }
        else {
            final boolean b2 = b.e.getBackground() != this.a;
            final boolean b3 = b.null.getBackground() != this.a;
            if (b.l.getBackground() != this.a) {
                if (b2) {
                    s += "c,";
                }
                else if (b3) {
                    s += "C,";
                }
                else {
                    s += "#6,";
                }
            }
            if (b.b.getBackground() != this.a) {
                if (b2) {
                    s += "n,";
                }
                else if (b3) {
                    s += "N,";
                }
                else {
                    s += "#7,";
                }
            }
            if (b.void.getBackground() != this.a) {
                if (b2) {
                    s += "o,";
                }
                else if (b3) {
                    s += "O,";
                }
                else {
                    s += "#8,";
                }
            }
            if (b.char.getBackground() != this.a) {
                if (b2) {
                    s += "s,";
                }
                else if (b3) {
                    s += "S,";
                }
                else {
                    s += "#16,";
                }
            }
            if (b.long.getBackground() != this.a) {
                if (b2) {
                    s += "p,";
                }
                else if (b3) {
                    s += "P,";
                }
                else {
                    s += "#15,";
                }
            }
            if (b.j.getBackground() != this.a) {
                s += "F,";
            }
            if (b.int.getBackground() != this.a) {
                s += "Cl,";
            }
            if (b.m.getBackground() != this.a) {
                s += "Br,";
            }
            if (b.g.getBackground() != this.a) {
                s += "I,";
            }
            if (s.endsWith(",")) {
                s = s.substring(0, s.length() - 1);
            }
            if (s.length() < 1 && !b.else) {
                if (b2) {
                    s = "a";
                }
                else if (b3) {
                    s = "A";
                }
                else {
                    b.case.setBackground(Color.orange);
                    s = "*";
                }
            }
        }
        String s2 = "";
        if (b && b.e.getBackground() != this.a) {
            s2 += ";a";
        }
        if (b && b.null.getBackground() != this.a) {
            s2 += ";A";
        }
        if (b.do.getBackground() != this.a) {
            s2 += ";R";
        }
        if (b.if.getBackground() != this.a) {
            s2 += ";!R";
        }
        String text;
        if (b.case.getBackground() != this.a && s2.length() > 0) {
            text = s2.substring(1, s2.length());
        }
        else {
            text = s + s2;
        }
        int selectedIndex = b.k.getSelectedIndex();
        if (selectedIndex > 0) {
            --selectedIndex;
            text = text + ";H" + selectedIndex;
        }
        int selectedIndex2 = b.n.getSelectedIndex();
        if (selectedIndex2 > 0) {
            --selectedIndex2;
            text = text + ";D" + selectedIndex2;
        }
        if (b.new.getBackground() != this.a) {
            text = "~";
        }
        if (b.i.getBackground() != this.a) {
            text = ":";
        }
        if (b.f.getBackground() != this.a) {
            text = "@";
        }
        if (b.c.getBackground() != this.a) {
            text = "!@";
        }
        b.byte.setText(text);
    }
    
    static {
        b.d = new Point(20, 200);
        b.else = false;
    }
}
