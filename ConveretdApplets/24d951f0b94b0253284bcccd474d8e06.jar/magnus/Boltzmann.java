// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Panel;
import java.applet.Applet;

public class Boltzmann extends Applet
{
    Panel case;
    Panel goto;
    Panel if;
    Panel try;
    d int;
    Button for;
    Button a;
    public static TextField char;
    public static TextField do;
    public static TextField byte;
    b new;
    e else;
    
    public void init() {
        System.out.println("Magnus Chemistry");
        System.out.println("================");
        System.out.println("Boltzmann calculations");
        System.out.println("created by Paul Kirby, 1999");
        System.out.println("Cambridge University");
        System.out.println("All rights reserved");
        System.out.println("");
        this.else = new e(0.0, 5.0, 0.0, 1.2000000476837158);
        (this.goto = new Panel()).setLayout(new GridLayout(1, 0, 5, 5));
        this.goto.setBackground(new Color(0, 0, 0));
        this.new = new b(this.else);
        this.goto.add(this.new);
        (this.try = new Panel()).setLayout(new GridLayout(9, 0, 5, 5));
        this.try.setBackground(new Color(10, 180, 230));
        this.int = new d(this);
        this.for = new Button("Logarithmic / Linear");
        this.a = new Button("Calculate");
        (Boltzmann.char = new TextField("270")).setBackground(new Color(255, 255, 255));
        (Boltzmann.do = new TextField()).setBackground(new Color(255, 255, 255));
        (Boltzmann.byte = new TextField()).setBackground(new Color(255, 255, 255));
        final Label label = new Label();
        label.setFont(new Font("Arial", 1, 18));
        label.setText("Boltzmann factors");
        this.try.add(label);
        this.try.add(new Label("Temperature (K):"));
        this.try.add(Boltzmann.char);
        this.try.add(new Label("Energy difference kJ/mol:"));
        this.try.add(Boltzmann.do);
        this.try.add(new Label("Ratio:"));
        this.try.add(Boltzmann.byte);
        this.try.add(this.a);
        final Label label2 = new Label("created by Paul Kirby");
        label2.setFont(new Font("Arial", 1, 10));
        this.try.add(label2);
        (this.if = new Panel()).setLayout(new GridLayout(2, 0));
        this.if.add(this.int);
        this.if.add(this.for);
        (this.case = new Panel()).setLayout(new BorderLayout());
        this.case.add("Center", this.goto);
        this.case.add("South", this.if);
        this.setLayout(new BorderLayout());
        this.add("West", this.try);
        this.add("Center", this.case);
    }
    
    public void start() {
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.new.paint(graphics);
        this.int.paint(graphics);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.for) {
            if (e.for == 0) {
                e.for = 1;
            }
            else {
                e.for = 0;
            }
            this.new.repaint();
            return true;
        }
        if (event.target == this.a) {
            int n = 0;
            final String text = Boltzmann.char.getText();
            final String text2 = Boltzmann.do.getText();
            final String text3 = Boltzmann.byte.getText();
            if (text.equals("")) {
                ++n;
            }
            if (text2.equals("")) {
                n += 10;
            }
            if (text3.equals("")) {
                n += 100;
            }
            double new1 = 0.0;
            double try1 = 0.0;
            double n2 = 0.0;
            switch (n) {
                case 1: {
                    new1 = Double.valueOf(Boltzmann.byte.getText());
                    try1 = Double.valueOf(Boltzmann.do.getText());
                    n2 = -1000.0 * try1 / (8.314 * Math.log(new1));
                    Boltzmann.char.setText("" + (float)n2);
                    break;
                }
                case 10: {
                    new1 = Double.valueOf(Boltzmann.byte.getText());
                    n2 = Double.valueOf(Boltzmann.char.getText());
                    try1 = -8.314 * n2 * Math.log(new1) / 1000.0;
                    Boltzmann.do.setText("" + (float)try1);
                    break;
                }
                default: {
                    try1 = Double.valueOf(Boltzmann.do.getText());
                    n2 = Double.valueOf(Boltzmann.char.getText());
                    new1 = Math.exp(-1000.0 * try1 / (8.314 * n2));
                    Boltzmann.byte.setText("" + (float)new1);
                    break;
                }
            }
            this.int.if = (int)(n2 / 2.0);
            e.if = 1;
            e.try = try1;
            e.new = new1;
            this.int.repaint();
            this.repaint();
            return true;
        }
        return false;
    }
}
