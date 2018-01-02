// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Panel;
import java.applet.Applet;

public class Kinetic extends Applet implements Runnable
{
    Thread a;
    boolean try;
    Panel k;
    Panel for;
    Panel char;
    Panel o;
    Panel long;
    Panel f;
    Panel void;
    Panel l;
    Panel goto;
    Button d;
    public static Checkbox else;
    public static Checkbox new;
    public static Checkbox byte;
    public static Checkbox h;
    public static TextField g;
    public static TextField e;
    public static TextField do;
    public Scrollbar n;
    public Scrollbar j;
    public Scrollbar i;
    public static TextField int;
    public static TextField if;
    public static TextField b;
    double c;
    double p;
    double case;
    public static Checkbox q;
    ScanvasKin m;
    GraphKin null;
    
    public Kinetic() {
        this.try = false;
        this.c = 1.0;
        this.p = 0.0;
        this.case = 0.0;
    }
    
    public void init() {
        System.out.println("Magnus Chemistry");
        System.out.println("================");
        System.out.println("Kinetic calculations");
        System.out.println("created by Lars Ole Haustedt, 2000");
        System.out.println("Goodman Group");
        System.out.println("Cambridge University");
        System.out.println("All rights reserved");
        System.out.println("");
        this.null = new GraphKin(0.0, 5.0, 0.0, 1.2000000476837158);
        (this.for = new Panel()).setLayout(new GridLayout(1, 0, 0, 0));
        this.for.setBackground(new Color(0, 0, 0));
        this.m = new ScanvasKin(this.null);
        this.for.add(this.m);
        (this.f = new Panel()).setLayout(new BorderLayout());
        this.f.setBackground(Color.lightGray);
        (this.void = new Panel()).setLayout(new GridLayout(2, 1, 0, 0));
        this.void.setBackground(Color.lightGray);
        (this.l = new Panel()).setLayout(new GridLayout(4, 2, 0, 0));
        this.l.setBackground(Color.gray);
        (this.goto = new Panel()).setLayout(new GridLayout(12, 1, 0, 0));
        this.goto.setBackground(Color.lightGray);
        this.d = new Button("Calculate");
        (Kinetic.g = new TextField("1.0")).setBackground(new Color(255, 255, 255));
        (Kinetic.e = new TextField("0.0")).setBackground(new Color(255, 255, 255));
        (Kinetic.do = new TextField("0.0")).setBackground(new Color(255, 255, 255));
        final Label label = new Label();
        label.setFont(new Font("Arial", 1, 18));
        label.setText("Kinetics");
        this.void.add(label);
        this.void.add(new Label("created by Lars Ole Haustedt"));
        final Label label2 = new Label();
        label2.setText("[A](t=0)");
        label2.setFont(new Font("Arial", 1, 14));
        this.l.add(label2);
        this.l.add(Kinetic.g);
        final Label label3 = new Label();
        label3.setText("[B](t=0)");
        label3.setFont(new Font("Arial", 1, 14));
        this.l.add(label3);
        this.l.add(Kinetic.e);
        final Label label4 = new Label();
        label4.setText("[C](t=0)");
        label4.setFont(new Font("Arial", 1, 14));
        this.l.add(label4);
        this.l.add(Kinetic.do);
        this.l.add(new Label("     "));
        this.l.add(this.d);
        Kinetic.else = new Checkbox("Exact solution");
        Kinetic.new = new Checkbox("Steady state");
        Kinetic.byte = new Checkbox("Pre-equilibrium");
        Kinetic.h = new Checkbox("k-1 = 0");
        Kinetic.else.setState(true);
        Kinetic.new.setState(false);
        Kinetic.byte.setState(false);
        Kinetic.h.setState(false);
        this.goto.add(Kinetic.else);
        this.goto.add(new Label("  (Red, Green, Dark Blue)"));
        this.goto.add(Kinetic.new);
        this.goto.add(new Label("  (Yellow, Grey, Orange)"));
        this.goto.add(Kinetic.byte);
        this.goto.add(new Label("  (Light Green, Cyan, Blue)"));
        this.goto.add(Kinetic.h);
        this.goto.add(new Label("  (Pink, Magenta, Mauve)"));
        this.goto.add(new Label("     "));
        this.goto.add(new Label("Magnus Chemistry"));
        this.goto.add(new Label("Goodman Group"));
        this.goto.add(new Label("Cambridge University 2000"));
        this.f.add("North", this.void);
        this.f.add("Center", this.l);
        this.f.add("South", this.goto);
        (this.char = new Panel()).setLayout(new GridLayout(1, 3, 0, 0));
        (this.o = new Panel()).setLayout(new GridLayout(3, 1, 0, 0));
        this.o.setBackground(Color.lightGray);
        this.n = new Scrollbar(0, 110, 4, 0, 200);
        this.j = new Scrollbar(0, 100, 4, 0, 200);
        this.i = new Scrollbar(0, 10, 4, 0, 200);
        GraphKin.z = 1.1;
        GraphKin.t = 1.0;
        GraphKin.w = 0.1;
        this.o.add(this.n);
        this.o.add(this.j);
        this.o.add(this.i);
        this.char.add(this.o);
        (this.long = new Panel()).setLayout(new GridLayout(3, 4, 0, 0));
        final Label label5 = new Label();
        label5.setFont(new Font("Arial", 1, 14));
        label5.setText("    k1   ");
        this.long.add(label5);
        (Kinetic.int = new TextField("1.1")).setBackground(Color.lightGray);
        Kinetic.int.setEditable(false);
        this.long.add(Kinetic.int);
        final Label label6 = new Label();
        label6.setText("       ");
        this.long.add(label6);
        final Label label7 = new Label();
        label7.setText("       ");
        this.long.add(label7);
        final Label label8 = new Label();
        label8.setFont(new Font("Arial", 1, 14));
        label8.setText("    k-1  ");
        this.long.add(label8);
        (Kinetic.if = new TextField("1.0")).setBackground(Color.lightGray);
        Kinetic.if.setEditable(false);
        this.long.add(Kinetic.if);
        final Label label9 = new Label();
        label9.setText("       ");
        this.long.add(label9);
        final Label label10 = new Label();
        label10.setText("       ");
        this.long.add(label10);
        final Label label11 = new Label();
        label11.setFont(new Font("Arial", 1, 14));
        label11.setText("    k2   ");
        this.long.add(label11);
        (Kinetic.b = new TextField("0.1")).setBackground(Color.lightGray);
        Kinetic.b.setEditable(false);
        this.long.add(Kinetic.b);
        this.char.add(this.long);
        (Kinetic.q = new Checkbox("animate")).setState(false);
        this.char.add(Kinetic.q);
        this.char.setBackground(Color.lightGray);
        (this.k = new Panel()).setLayout(new BorderLayout());
        this.k.add("Center", this.for);
        this.k.add("South", this.char);
        this.setLayout(new BorderLayout());
        this.add("West", this.f);
        this.add("Center", this.k);
    }
    
    public void run() {
        this.a();
    }
    
    public void start() {
        if (!this.try && Kinetic.q.getState()) {
            this.try = true;
            (this.a = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.try) {
            this.a.stop();
            this.try = false;
        }
    }
    
    public void a() {
        final double n = this.n.getValue() / 100.0;
        final double n2 = this.j.getValue() / 100.0;
        final double n3 = this.i.getValue() / 100.0;
        double acos = Math.acos(n - 1.0);
        double acos2 = Math.acos(n2 - 1.0);
        double acos3 = Math.acos(n3 - 1.0);
        while (true) {
            acos += 0.002;
            acos2 += 0.003;
            acos3 += 0.005;
            if (acos > 6.3) {
                acos -= 6.283185307179586;
            }
            if (acos2 > 6.3) {
                acos2 -= 6.283185307179586;
            }
            if (acos3 > 6.3) {
                acos3 -= 6.283185307179586;
            }
            final double z = 1.0 + Math.cos(acos);
            final double t = 1.0 + Math.cos(acos2);
            final double w = 1.0 + Math.cos(acos3);
            this.n.setValue((int)(100.0 * z));
            this.j.setValue((int)(100.0 * t));
            this.i.setValue((int)(100.0 * w));
            GraphKin.z = z;
            GraphKin.t = t;
            GraphKin.w = w;
            Kinetic.int.setText(Double.toString(z));
            Kinetic.if.setText(Double.toString(t));
            Kinetic.b.setText(Double.toString(w));
            this.m.repaint();
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        this.m.paint(graphics);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.n) {
            GraphKin.z = this.n.getValue();
            GraphKin.z /= 100.0;
            Kinetic.int.setText(Double.toString(GraphKin.z));
            this.m.repaint();
            return true;
        }
        if (event.target == this.j) {
            GraphKin.t = this.j.getValue();
            GraphKin.t /= 100.0;
            Kinetic.if.setText(Double.toString(GraphKin.t));
            this.m.repaint();
            return true;
        }
        if (event.target == this.i) {
            GraphKin.w = this.i.getValue();
            GraphKin.w /= 100.0;
            Kinetic.b.setText(Double.toString(GraphKin.w));
            this.m.repaint();
            return true;
        }
        if (event.target == Kinetic.else) {
            this.m.repaint();
            return true;
        }
        if (event.target == Kinetic.new) {
            this.m.repaint();
            return true;
        }
        if (event.target == Kinetic.byte) {
            this.m.repaint();
            return true;
        }
        if (event.target == Kinetic.h) {
            this.m.repaint();
            return true;
        }
        if (event.target == Kinetic.q) {
            this.stop();
            if (Kinetic.q.getState()) {
                this.start();
            }
            this.m.repaint();
            return true;
        }
        if (event.target == this.d) {
            final String text = Kinetic.g.getText();
            final String text2 = Kinetic.e.getText();
            final String text3 = Kinetic.do.getText();
            if (text.equals("")) {
                Kinetic.g.setText("2.0");
            }
            else {
                this.c = Double.valueOf(Kinetic.g.getText());
                if (this.c > 2.0) {
                    Kinetic.g.setText("2.0");
                }
                if (this.c < 0.0) {
                    Kinetic.g.setText("0.0");
                }
                if (Double.isNaN(this.c)) {
                    Kinetic.g.setText("1.0");
                }
            }
            if (text2.equals("")) {
                Kinetic.e.setText("0.0");
            }
            else {
                this.p = Double.valueOf(Kinetic.e.getText());
                if (this.p > 2.0) {
                    Kinetic.e.setText("2.0");
                }
                if (this.p < 0.0) {
                    Kinetic.e.setText("0.0");
                }
                if (Double.isNaN(this.p)) {
                    Kinetic.e.setText("0.0");
                }
            }
            if (text3.equals("")) {
                Kinetic.do.setText("0.0");
            }
            else {
                this.case = Double.valueOf(Kinetic.do.getText());
                if (this.case > 2.0) {
                    Kinetic.do.setText("2.0");
                }
                if (this.case < 0.0) {
                    Kinetic.do.setText("0.0");
                }
                if (Double.isNaN(this.case)) {
                    Kinetic.do.setText("0.0");
                }
            }
            this.m.repaint();
            return true;
        }
        return false;
    }
}
