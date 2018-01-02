// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Panel;
import java.applet.Applet;

public class Nomo extends Applet
{
    public static double long;
    public static double else;
    public static boolean if;
    public static double byte;
    public static double n;
    Panel j;
    Panel do;
    Panel null;
    Panel g;
    Panel i;
    Panel int;
    TextField d;
    TextField b;
    TextField void;
    TextField goto;
    Button try;
    Button new;
    Button f;
    Button e;
    Button for;
    public static Label m;
    public static Label h;
    public static TextField case;
    public static TextField k;
    public static TextField c;
    public static TextField a;
    g l;
    h char;
    
    public void init() {
        this.char = new h(193.0, 413.0, 0.0, 130.0);
        (this.do = new Panel()).setLayout(new GridLayout(1, 0, 5, 5));
        this.do.setBackground(new Color(0, 0, 0));
        this.l = new g(this.char);
        this.do.add(this.l);
        (this.null = new Panel()).setLayout(new GridLayout(0, 4, 5, 5));
        this.null.setBackground(new Color(20, 74, 143));
        this.null.add(new Label("xmin"));
        this.null.add(new Label("xmax"));
        this.null.add(new Label("ymin"));
        this.null.add(new Label("ymax"));
        this.d = new TextField("0");
        this.b = new TextField("5");
        this.void = new TextField("0");
        this.goto = new TextField("1.2");
        this.null.add(this.d);
        this.null.add(this.b);
        this.null.add(this.void);
        this.null.add(this.goto);
        this.null.add(new Button("Apply"));
        (this.i = new Panel()).setLayout(new GridLayout(0, 1));
        this.i.setBackground(new Color(10, 180, 230));
        (this.int = new Panel()).setLayout(new GridLayout(11, 0, 5, 5));
        this.try = new Button("Logarithmic / Linear");
        final Label label = new Label();
        label.setFont(new Font("Arial", 1, 16));
        label.setText("Pressure/bp");
        Nomo.case = new TextField("" + (Nomo.else - 273.0));
        Nomo.k = new TextField("" + Nomo.long);
        this.for = new Button("Set initial temp. and pressure");
        Nomo.c = new TextField("");
        Nomo.a = new TextField("");
        this.e = new Button("Calculate");
        this.int.add(label);
        this.int.add(new Label("Enter literature boiling point:"));
        Nomo.m.setText("Initial Pressure (kPa)");
        this.int.add(Nomo.m);
        this.int.add(Nomo.k);
        this.int.add(new Label("Initial Temperature (C)"));
        this.int.add(Nomo.case);
        this.int.add(this.for);
        this.int.add(new Label());
        this.int.add(new Label());
        this.new = new Button("kPa / mmHg");
        final Label label2 = new Label();
        label2.setFont(new Font("Arial", 1, 12));
        label2.setText("created by Paul Kirby");
        this.int.add(label2);
        this.int.add(new Label("Goodman Group, 2000"));
        this.i.add(this.int);
        (this.g = new Panel()).setLayout(new GridLayout(0, 4));
        this.g.setBackground(new Color(10, 180, 230));
        Nomo.h.setText("New Pressure (kPa)");
        this.g.add(Nomo.h);
        this.g.add(Nomo.a);
        this.g.add(new Label("Resulting boiling point (C)"));
        this.g.add(Nomo.c);
        this.g.add(this.e);
        this.g.add(new Label());
        this.g.add(this.new);
        this.f = new Button("Pressure scale");
        this.g.add(this.f);
        (this.j = new Panel()).setLayout(new BorderLayout());
        this.j.add("Center", this.do);
        this.j.add("South", this.g);
        this.setLayout(new BorderLayout());
        this.add("Center", this.j);
        this.add("West", this.i);
    }
    
    public void start() {
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.l.paint(graphics);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.try) {
            if (magnus.h.new == 0) {
                magnus.h.new = 1;
            }
            else {
                magnus.h.new = 0;
            }
            this.l.repaint();
            return true;
        }
        if (event.target == this.new) {
            if (magnus.h.int == 0) {
                magnus.h.int = 1;
                Nomo.m.setText("Initial Pressure (mmHg)");
                Nomo.h.setText("New Pressure (mmHg)");
                final String s = new String("");
                final String text = Nomo.k.getText();
                if (!text.equals("")) {
                    Nomo.k.setText("" + Float.valueOf(text) * 7.5006f);
                }
                final String text2 = Nomo.a.getText();
                System.out.println(text2);
                if (!text2.equals("")) {
                    Nomo.a.setText("" + Float.valueOf(text2) * 7.5006f);
                }
            }
            else {
                Nomo.m.setText("Initial Pressure (kPa)");
                Nomo.h.setText("New Pressure (kPa)");
                magnus.h.int = 0;
                final String text3 = Nomo.k.getText();
                if (!text3.equals("")) {
                    Nomo.k.setText("" + Float.valueOf(text3) / 7.5006f);
                }
                final String text4 = Nomo.a.getText();
                if (!text4.equals("")) {
                    Nomo.a.setText("" + Float.valueOf(text4) / 7.5006f);
                }
            }
            this.l.repaint();
            return true;
        }
        if (event.target == this.f) {
            magnus.h.if = ((magnus.h.if == 130) ? 10 : 130);
            this.char = new h(193.0, this.char.do(), 0.0, magnus.h.if);
            this.l.a(this.char);
            this.l.repaint();
            return true;
        }
        if (event.target == this.for) {
            magnus.h.do = 0;
            final double doubleValue = Double.valueOf(Nomo.k.getText());
            final double else1 = Double.valueOf(Nomo.case.getText()) + 273.0;
            if (else1 > 202.0 && else1 < 10000.0 && doubleValue > 0.0) {
                if (magnus.h.int == 0) {
                    Nomo.long = doubleValue;
                }
                else {
                    Nomo.long = doubleValue / 7.5005998611450195;
                }
                if (Nomo.long < 100.1 && Nomo.long > 99.9) {
                    Nomo.if = true;
                    Nomo.else = else1;
                }
                else {
                    Nomo.if = false;
                    final double n = 100.0;
                    double n2 = 1.0;
                    double n3 = 10000.0;
                    if (Nomo.long > n) {
                        n3 = else1;
                    }
                    else {
                        n2 = else1;
                    }
                    double else2 = (n2 + n3) / 2.0;
                    final double log = Math.log(n / Nomo.long);
                    double n4 = n3 * (4.4 + Math.log(n3)) * (1.0 / else1 - 1.0 / n3);
                    double n5 = else2 * (4.4 + Math.log(else2)) * (1.0 / else1 - 1.0 / else2);
                    double n6 = n2 * (4.4 + Math.log(n2)) * (1.0 / else1 - 1.0 / n2);
                    if (n4 < log) {
                        Nomo.k.setText("100.0");
                        Nomo.case.setText("-70<T<3000 C and\nP>0 please.");
                        return true;
                    }
                    for (double n7 = n4 - n6; n7 > 0.001; n7 = n4 - n6) {
                        if (n5 < log) {
                            n2 = else2;
                            n6 = n5;
                        }
                        else {
                            n3 = else2;
                            n4 = n5;
                        }
                        else2 = (n2 + n3) / 2.0;
                        n5 = else2 * (4.4 + Math.log(else2)) * (1.0 / else1 - 1.0 / else2);
                    }
                    Nomo.else = else2;
                    Nomo.long = 100.0;
                }
                Nomo.a.setText("");
                Nomo.c.setText("");
                this.char.do((Nomo.else - this.char.if()) * 1.25 + this.char.if());
                this.l.repaint();
                return true;
            }
            Nomo.k.setText("100.0");
            Nomo.case.setText("-70<T<3000 C and\nP>0 please.");
            return true;
        }
        else {
            if (event.target == this.e) {
                magnus.h.do = 0;
                int n8 = 0;
                final String text5 = Nomo.c.getText();
                final String text6 = Nomo.a.getText();
                if (text5.equals("")) {
                    ++n8;
                }
                if (text6.equals("")) {
                    n8 += 10;
                }
                double n9 = 0.0;
                double a = 0.0;
                switch (n8) {
                    case 1: {
                        n9 = ((magnus.h.int == 0) ? Double.valueOf(Nomo.a.getText()) : (Double.valueOf(Nomo.a.getText()) / 7.5006));
                        a = a(n9, 4.4 + Math.log(Nomo.else + 273.15));
                        Nomo.c.setText("" + (float)(a - 273.0));
                        break;
                    }
                    default: {
                        a = Double.valueOf(Nomo.c.getText()) + 273.0;
                        final double if1 = if(a, 4.4 + Math.log(Nomo.else + 273.15));
                        n9 = ((magnus.h.int == 0) ? if1 : (if1 * 7.5006));
                        Nomo.a.setText("" + (float)n9);
                        break;
                    }
                }
                magnus.h.do = 1;
                magnus.h.case = a;
                magnus.h.byte = ((magnus.h.int == 0) ? n9 : (n9 / 7.5005998611450195));
                this.l.repaint();
                return true;
            }
            return false;
        }
    }
    
    public static double if(final double n, final double n2) {
        return Nomo.long * Math.exp(-n2 * (Nomo.else / n - 1.0));
    }
    
    public static double a(final double n, final double n2) {
        return Nomo.else / (Math.log(Nomo.long / n) / n2 + 1.0);
    }
    
    static {
        Nomo.long = 100.0;
        Nomo.else = 373.0;
        Nomo.if = true;
        Nomo.byte = 100.0;
        Nomo.n = 373.0;
        Nomo.m = new Label();
        Nomo.h = new Label();
    }
}
