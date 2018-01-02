// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.text.DecimalFormat;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Panel;
import java.applet.Applet;

public class Eyring extends Applet
{
    Panel i;
    Panel do;
    Panel byte;
    Panel for;
    Panel m;
    Panel f;
    public static c long;
    public static TextField try;
    public static TextField if;
    public static Label k;
    public static Label new;
    Button c;
    Button b;
    Button g;
    public static TextField void;
    public static TextField e;
    public static TextField d;
    f j;
    a goto;
    static double else;
    static double l;
    static double n;
    static double a;
    static double int;
    static double null;
    static double char;
    static double h;
    static double case;
    
    public void init() {
        System.out.println("Magnus Chemistry");
        System.out.println("================");
        System.out.println("Eyring calculations");
        System.out.println("created by Paul Kirby, 1999");
        System.out.println("and by Lars Haustedt, 2000");
        System.out.println("Goodman Group");
        System.out.println("Cambridge University");
        System.out.println("All rights reserved");
        System.out.println("");
        this.goto = new a(0.0, 5.0, 0.0, 1.2000000476837158);
        (this.do = new Panel()).setLayout(new GridLayout(1, 0, 0, 0));
        this.do.setBackground(new Color(0, 0, 0));
        this.j = new f(this.goto);
        this.do.add(this.j);
        (this.f = new Panel()).setLayout(new GridLayout(12, 0, 0, 0));
        this.f.setBackground(new Color(10, 180, 230));
        Eyring.long = new c(this);
        this.c = new Button("Calculate");
        (Eyring.void = new TextField("270")).setBackground(new Color(255, 255, 255));
        (Eyring.e = new TextField()).setBackground(new Color(255, 255, 255));
        (Eyring.d = new TextField()).setBackground(new Color(255, 255, 255));
        final Label label = new Label();
        label.setFont(new Font("Arial", 1, 18));
        label.setText("Eyring");
        this.f.add(label);
        this.f.add(new Label("Temperature (K):"));
        this.f.add(Eyring.void);
        this.f.add(new Label("Energy (kJ/mol):"));
        this.f.add(Eyring.e);
        this.f.add(new Label("Rate (per second):"));
        this.f.add(Eyring.d);
        this.f.add(this.c);
        this.f.add(new Label("  "));
        final Label label2 = new Label("created by Kirby / Haustedt");
        label2.setFont(new Font("Arial", 1, 10));
        this.f.add(label2);
        this.f.add(new Label("Goodman Group"));
        final Label label3 = new Label("Cambridge University 2000");
        label3.setFont(new Font("Arial", 1, 10));
        this.f.add(label3);
        (this.byte = new Panel()).setLayout(new GridLayout(2, 0, 0, 0));
        this.byte.add(Eyring.long);
        (this.for = new Panel()).setLayout(new GridLayout(1, 8, 0, 0));
        this.for.setBackground(new Color(10, 180, 230));
        (Eyring.try = new TextField("0")).setBackground(new Color(255, 255, 255));
        (Eyring.if = new TextField("10")).setBackground(new Color(255, 255, 255));
        this.b = new Button("Set Scale");
        this.g = new Button("Switch");
        this.for.add(this.g);
        this.for.add(new Label(" "));
        Eyring.k = new Label("Min Energy:");
        this.for.add(Eyring.k);
        this.for.add(Eyring.try);
        Eyring.new = new Label("Max Energy:");
        this.for.add(Eyring.new);
        this.for.add(Eyring.if);
        this.for.add(this.b);
        this.for.add(new Label(" "));
        this.byte.add(this.for);
        (this.i = new Panel()).setLayout(new BorderLayout());
        this.i.add("Center", this.do);
        this.i.add("South", this.byte);
        this.setLayout(new BorderLayout());
        this.add("West", this.f);
        this.add("Center", this.i);
    }
    
    public void start() {
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.j.paint(graphics);
        Eyring.long.paint(graphics);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.b) {
            if (magnus.a.A == 1) {
                try {
                    Eyring.a = Double.valueOf(Eyring.try.getText());
                }
                catch (NumberFormatException ex) {
                    Eyring.a = 200.0;
                    Eyring.try.setText("200.0");
                }
                try {
                    Eyring.int = Double.valueOf(Eyring.if.getText());
                }
                catch (NumberFormatException ex2) {
                    Eyring.int = 300.0;
                    Eyring.if.setText("300.0");
                }
            }
            else {
                try {
                    Eyring.h = Double.valueOf(Eyring.try.getText());
                }
                catch (NumberFormatException ex3) {
                    Eyring.h = 0.0;
                    Eyring.try.setText("0.0");
                }
                try {
                    Eyring.case = Double.valueOf(Eyring.if.getText());
                }
                catch (NumberFormatException ex4) {
                    Eyring.case = 10.0;
                    Eyring.if.setText("10.0");
                }
            }
        }
        else if (event.target == this.g) {
            if (magnus.a.A == 0) {
                Eyring.try.setText("200");
                Eyring.if.setText("300");
                Eyring.a = 200.0;
                Eyring.int = 300.0;
                magnus.a.A = 1;
                magnus.a.p = 0;
            }
            else {
                Eyring.try.setText("0");
                Eyring.if.setText("10");
                Eyring.h = 0.0;
                Eyring.case = 10.0;
                magnus.a.A = 0;
                magnus.a.p = 0;
            }
        }
        else if (event.target == this.c) {
            int n = 0;
            if (Eyring.void.getText().equals("")) {
                ++n;
            }
            else {
                try {
                    Eyring.n = Double.valueOf(Eyring.void.getText());
                }
                catch (NumberFormatException ex5) {
                    Eyring.n = -99999.0;
                }
                if (Eyring.n > 10000.0 || Eyring.n <= 0.0) {
                    Eyring.void.setText("range 0 - 10000 K");
                    n = -99999;
                }
            }
            if (Eyring.e.getText().equals("")) {
                n += 10;
            }
            else {
                try {
                    Eyring.l = Double.valueOf(Eyring.e.getText());
                }
                catch (NumberFormatException ex6) {
                    Eyring.l = -100.0;
                }
                if (Eyring.l < 0.0) {
                    Eyring.e.setText("Enthalpy >= 0");
                    n = -99999;
                }
            }
            if (Eyring.d.getText().equals("")) {
                n += 100;
            }
            else {
                try {
                    Eyring.else = Double.valueOf(Eyring.d.getText());
                }
                catch (NumberFormatException ex7) {
                    Eyring.else = -99999.0;
                }
                if (Eyring.else <= 0.0) {
                    Eyring.d.setText("rate > 0");
                    n = -99999;
                }
            }
            switch (n) {
                case 0: {
                    Eyring.else = a(Eyring.n, Eyring.l);
                    Eyring.d.setText("" + this.a(Eyring.else));
                    break;
                }
                case 1: {
                    Eyring.void.setText("" + (float)this.if(Eyring.else, Eyring.l));
                    break;
                }
                case 10: {
                    Eyring.l = this.do(Eyring.n, Eyring.else);
                    Eyring.e.setText("" + (float)Eyring.l);
                    break;
                }
                case 100: {
                    Eyring.else = a(Eyring.n, Eyring.l);
                    Eyring.d.setText("" + this.a(Eyring.else));
                    break;
                }
            }
        }
        this.a(Eyring.l, Eyring.else, Eyring.n);
        return true;
    }
    
    public String a(final double n) {
        final int n2 = (int)Math.round(Math.log(n) / Math.log(10.0));
        return new DecimalFormat("#.########").format(n / Math.pow(10.0, n2)) + "E" + n2;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        Eyring.void.setText(String.valueOf(Eyring.n));
        Eyring.d.setText(this.a(Eyring.else));
        Eyring.e.setText(String.valueOf(Eyring.l));
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        Eyring.void.setText(String.valueOf(Eyring.n));
        Eyring.d.setText(this.a(Eyring.else));
        Eyring.e.setText(String.valueOf(Eyring.l));
        return false;
    }
    
    public double do(final double n, final double n2) {
        return -8.314 * n * Math.log(n2 * 6.6261E-34 / (1.3806E-23 * n)) / 1000.0;
    }
    
    public static double a(final double n, final double n2) {
        return 1.3806E-23 * n / 6.6261E-34 * Math.exp(-n2 * 1000.0 / (8.314 * n));
    }
    
    public double if(final double n, final double n2) {
        double n3 = 10000.0;
        double n4 = 0.0;
        while (n3 - n4 > 0.001) {
            final double n5 = (n3 + n4) / 2.0;
            if (a(n5, n2) < n) {
                n4 = n5;
            }
            else {
                n3 = n5;
            }
        }
        return (n3 + n4) / 2.0;
    }
    
    void a(final double n, final double n2, final double n3) {
        if (magnus.a.A == 0) {
            Eyring.long.if = (int)(n3 / 2.0);
            magnus.a.v = (int)((n - Eyring.h) / (Eyring.case - Eyring.h) * 620.0) + 30;
        }
        else {
            Eyring.long.if = (int)(n / 0.07999999821186066);
            magnus.a.v = (int)((n3 - Eyring.a) / (Eyring.int - Eyring.a) * 620.0) + 30;
        }
        magnus.a.p = 1;
        magnus.a.t = (int)((Eyring.char - n2) / (Eyring.char - Eyring.null) * 300.0);
        Eyring.long.repaint();
        this.repaint();
    }
    
    static {
        Eyring.else = 0.0;
        Eyring.l = 0.0;
        Eyring.n = 270.0;
        Eyring.a = 0.0;
        Eyring.int = 0.0;
        Eyring.null = 0.0;
        Eyring.char = 0.0;
        Eyring.h = 0.0;
        Eyring.case = 10.0;
    }
}
