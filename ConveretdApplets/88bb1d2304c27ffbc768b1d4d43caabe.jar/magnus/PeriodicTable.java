// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Event;
import java.awt.Label;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Scrollbar;
import java.awt.Button;
import java.util.Random;
import java.awt.TextArea;
import java.applet.Applet;

public class PeriodicTable extends Applet
{
    TextArea new;
    Random char;
    int byte;
    int goto;
    Button long;
    Button for;
    Button[] try;
    Button a;
    Button else;
    Scrollbar case;
    Button do;
    Button int;
    boolean if;
    
    public PeriodicTable() {
        this.char = new Random();
        this.byte = 0;
        this.goto = 12;
        this.if = false;
    }
    
    public void init() {
        System.out.println("Magnus Chemistry");
        System.out.println("================");
        System.out.println("(c) J M Goodman, 2000-1");
        System.out.println("Cambridge University");
        System.out.println("All rights reserved");
        System.out.println("");
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.fill = 1;
        (this.long = new Button("Periodic Table")).setFont(new Font("TimesRoman", 0, 18));
        layout.setConstraints(this.long, EadUtil.a(gridBagConstraints, 3, 0, 8, 1));
        this.long.setBackground(new Color(240, 240, 240));
        this.add(this.long);
        this.byte = (int)(this.goto * this.char.nextFloat());
        (this.for = new Button(this.a(this.byte))).setFont(new Font("TimesRoman", 0, 12));
        layout.setConstraints(this.for, EadUtil.a(gridBagConstraints, 3, 1, 6, 1));
        this.for.setBackground(new Color(220, 220, 220));
        this.add(this.for);
        (this.int = new Button("<--")).setFont(new Font("Courier", 0, 12));
        layout.setConstraints(this.int, EadUtil.a(gridBagConstraints, 9, 1, 1, 1));
        this.add(this.int);
        (this.do = new Button("abs")).setFont(new Font("TimesRoman", 0, 12));
        layout.setConstraints(this.do, EadUtil.a(gridBagConstraints, 10, 1, 1, 1));
        this.add(this.do);
        gridBagConstraints.fill = 2;
        layout.setConstraints(this.case = new Scrollbar(0, (int)(431.0f * this.char.nextFloat()), 4, 0, 431), EadUtil.a(gridBagConstraints, 4, 2, 6, 1));
        this.add(this.case);
        if (this.byte != 9) {
            this.case.disable();
        }
        gridBagConstraints.fill = 0;
        final Label label = new Label("(c) J M Goodman, 2001", 1);
        label.setFont(new Font("TimesRoman", 2, 12));
        layout.setConstraints(label, EadUtil.a(gridBagConstraints, 12, 0, 4, 1));
        this.add(label);
        this.try = new Button[magnus.a.if.length];
        gridBagConstraints.anchor = 17;
        for (int i = 1; i < magnus.a.if.length; ++i) {
            int n;
            int n2;
            if (i < 3) {
                n = 0;
                if (i == 1) {
                    n2 = 0;
                }
                else {
                    n2 = 17;
                }
            }
            else if (i < 11) {
                n = 1;
                if (i < 5) {
                    n2 = i - 3;
                }
                else {
                    n2 = i + 7;
                }
            }
            else if (i < 19) {
                n = 2;
                if (i < 13) {
                    n2 = i - 11;
                }
                else {
                    n2 = i - 1;
                }
            }
            else if (i < 58) {
                n = (i - 19) / 18 + 3;
                n2 = i - 18 * (n - 2) - 1;
            }
            else if (i < 72) {
                n = 7;
                n2 = i - 55;
            }
            else if (i < 90) {
                if (i < 87) {
                    n = 5;
                    n2 = i - 69;
                }
                else {
                    n = 6;
                    n2 = i - 87;
                }
            }
            else if (i < 104) {
                n = 8;
                n2 = i - 87;
            }
            else {
                n = 6;
                n2 = i - 101;
            }
            String s = magnus.a.if[i];
            if (s.length() == 1) {
                s += " ";
            }
            if (i == 57) {
                s += "*";
                (this.a = new Button("*")).setFont(new Font("Courier", 0, 16));
                this.a.setBackground(this.a(i, this.byte));
                gridBagConstraints.anchor = 13;
                layout.setConstraints(this.a, EadUtil.a(gridBagConstraints, 2, 7, 1, 1));
                this.add(this.a);
                gridBagConstraints.anchor = 17;
            }
            if (i == 89) {
                s += "#";
                (this.else = new Button("#")).setFont(new Font("Courier", 0, 16));
                this.else.setBackground(this.a(i, this.byte));
                gridBagConstraints.anchor = 13;
                layout.setConstraints(this.else, EadUtil.a(gridBagConstraints, 2, 8, 1, 1));
                this.add(this.else);
                gridBagConstraints.anchor = 17;
            }
            (this.try[i] = new Button(s)).setFont(new Font("Courier", 0, 16));
            this.try[i].setBackground(this.a(i, this.byte));
            layout.setConstraints(this.try[i], EadUtil.a(gridBagConstraints, n2, n, 1, 1));
            this.add(this.try[i]);
        }
        gridBagConstraints.anchor = 10;
        layout.setConstraints(this.new = new TextArea(5, 80), EadUtil.a(gridBagConstraints, 0, 11, 0, 0));
        this.add(this.new);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof Scrollbar && this.byte == 9) {
            this.new.setText("Temperature: " + Integer.toString((int)(10.0 * this.case.getValue() - 270.0)) + " °C");
            for (int i = 1; i < magnus.a.if.length; ++i) {
                this.try[i].setBackground(this.a(i, this.byte));
            }
            this.a.setBackground(this.a(57, this.byte));
            this.else.setBackground(this.a(89, this.byte));
        }
        else if (event.target instanceof Button && event.arg != null) {
            final String s = (String)event.arg;
            if (s.length() == 2) {
                this.a(s);
            }
            else if (s.compareTo("La*") == 0) {
                this.a("La");
            }
            else if (s.compareTo("Ac#") == 0) {
                this.a("Ac");
            }
            else if (event.target == this.long) {
                this.a();
            }
            else if (event.target == this.for) {
                this.a(false);
            }
            else if (event.target == this.int) {
                this.a(true);
            }
            else if (event.target == this.do) {
                if (this.if) {
                    this.new.setText("Absolute element colours");
                    --this.byte;
                    this.a(false);
                    this.do.setLabel("abs");
                }
                else {
                    this.new.setText("Selecting an element colours\nthe rest by their similarity to it");
                    --this.byte;
                    this.a(false);
                    this.do.setLabel("rel");
                }
                this.if = !this.if;
            }
        }
        return true;
    }
    
    void a(final boolean b) {
        if (b) {
            --this.byte;
        }
        else {
            ++this.byte;
        }
        if (this.byte >= this.goto) {
            this.byte = 0;
        }
        else if (this.byte < 0) {
            this.byte = this.goto - 1;
        }
        if (this.byte == 10 || this.byte == 8) {
            this.case.disable();
        }
        else if (this.byte == 9) {
            this.case.enable();
            this.new.setText("Temperature: " + Integer.toString((int)(10.0 * this.case.getValue() - 270.0)) + " °C");
        }
        this.for.setLabel(this.a(this.byte));
        for (int i = 1; i < magnus.a.if.length; ++i) {
            this.try[i].setBackground(this.a(i, this.byte));
        }
        this.a.setBackground(this.a(57, this.byte));
        this.else.setBackground(this.a(89, this.byte));
    }
    
    String a(final int n) {
        if (n == 0) {
            return "coloured by atomic number";
        }
        if (n == 1) {
            return "coloured by atomic weight";
        }
        if (n == 2) {
            return "coloured by number of isotopes";
        }
        if (n == 3) {
            return "coloured by melting point";
        }
        if (n == 4) {
            return "coloured by boiling point";
        }
        if (n == 5) {
            return "coloured by alphabetical order";
        }
        if (n == 6) {
            return "coloured by range of liquid phase";
        }
        if (n == 7) {
            return "Phase at 0 °C";
        }
        if (n == 8) {
            return "Phase at 100 °C";
        }
        if (n == 9) {
            return "Phase: adjustable temperature";
        }
        if (n == 10) {
            return "weight order vs atomic number";
        }
        return "uniform colours";
    }
    
    void if(final int n) {
        final Color color = new Color(0);
        final Color color2 = new Color(0);
        final Color a = this.a(n, this.byte);
        final int red = a.getRed();
        final int green = a.getGreen();
        final int blue = a.getBlue();
        for (int i = 1; i < magnus.a.if.length; ++i) {
            final Color a2 = this.a(i, this.byte);
            this.try[i].setBackground(new Color(255 - Math.abs(a2.getRed() - red), 255 - Math.abs(a2.getGreen() - green), 255 - Math.abs(a2.getBlue() - blue)));
        }
        this.a.setBackground(this.try[57].getBackground());
        this.else.setBackground(this.try[89].getBackground());
    }
    
    Color a(double n, final double n2, final int n3) {
        final int[] array = { 180, 180, 180 };
        if (n < 0.0) {
            n = 0.0;
        }
        if (n > n2) {
            n = n2;
        }
        if (n3 < 4) {
            array[n3 - 1] = 250;
            final int n4 = (int)(n / n2 * 75.0) + 180;
            final int n5 = (n3 == 1) ? 2 : (n3 - 2);
            array[n5] = n4;
            array[(n5 == 0) ? 2 : (n5 - 1)] = n4;
        }
        else if (n3 < 7) {
            final int n6 = (int)(n / n2 * 75.0);
            final int n7 = (n3 == 4) ? 2 : (n3 - 5);
            array[n7] = 255 - n6;
            array[(n7 == 0) ? 2 : (n7 - 1)] = 180 + n6;
        }
        else if (n < n2 / 2.0) {
            final int n8 = (int)(n / n2 * 150.0);
            array[2] = 250 - n8;
            array[1] = 180 + n8;
        }
        else {
            final int n9 = (int)(n / n2 * 150.0) - 75;
            array[0] = 180 + n9;
            array[1] = 250 - n9;
        }
        return new Color(array[0], array[1], array[2]);
    }
    
    Color do(final int n) {
        double a;
        if (n > 1) {
            a = magnus.a.a(n - 1, "Average");
        }
        else {
            a = 0.0;
        }
        final double a2 = magnus.a.a(n, "Average");
        final double a3 = magnus.a.a(n + 1, "Average");
        int n3;
        int n2;
        int n4;
        if (a > a2) {
            n2 = (n3 = 100 + 10 * (n - 1 - (n - 1) / 7 * 7));
            n4 = 230;
        }
        else if (a2 > a3) {
            n2 = (n3 = 100 + 10 * (n + 1 - (n + 1) / 7 * 7));
            n4 = 230;
        }
        else {
            n2 = (n3 = 160 + 15 * (n - n / 7 * 7));
            n4 = 80;
        }
        return new Color(n4, n3, n2);
    }
    
    Color a(final int n, final int n2) {
        if (n2 == 0) {
            return this.a(n, 120.0, 4);
        }
        if (n2 == 1) {
            final int n3 = (int)magnus.a.a(n, "Average");
            if (n3 < 1) {
                return new Color(150, 150, 150);
            }
            return this.a(n3, 250.0, 5);
        }
        else if (n2 == 2) {
            if (n < magnus.a.do.length) {
                return this.a(magnus.a.do[n].length, 10.0, 7);
            }
            return new Color(150, 150, 150);
        }
        else if (n2 == 3) {
            if (n >= magnus.a.new.length) {
                return new Color(150, 150, 150);
            }
            double n4 = magnus.a.new[n];
            if (n4 > -300.0) {
                if (n4 > 400.0) {
                    n4 = 400.0 + (n4 - 400.0) / 8.0;
                }
                return this.a(n4 + 300.0, 1000.0, 7);
            }
            return new Color(150, 150, 150);
        }
        else if (n2 == 4) {
            if (n >= magnus.a.byte.length) {
                return new Color(150, 150, 150);
            }
            double n5 = magnus.a.byte[n];
            if (n5 > -300.0) {
                if (n5 > 200.0) {
                    n5 = 200.0 + (n5 - 200.0) / 8.0;
                }
                return this.a(n5 + 300.0, 1000.0, 7);
            }
            return new Color(150, 150, 150);
        }
        else {
            if (n2 == 5) {
                double n6 = 1.0;
                if (n == 6) {
                    n6 = 16.5;
                }
                else if (n == 1) {
                    n6 = 38.5;
                }
                else if (n < magnus.a.for.length) {
                    for (int i = 0; i < magnus.a.for.length; ++i) {
                        if (n == magnus.a.for[i]) {
                            n6 = i;
                            break;
                        }
                    }
                }
                return this.a(n6, 120.0, 7);
            }
            if (n2 == 6) {
                if (n >= magnus.a.byte.length || n >= magnus.a.new.length) {
                    return new Color(150, 150, 150);
                }
                if (magnus.a.byte[n] > -300.0 && magnus.a.new[n] > -300.0) {
                    return this.a(magnus.a.byte[n] - magnus.a.new[n], 2000.0, 7);
                }
                return new Color(150, 150, 150);
            }
            else {
                if (n2 == 7) {
                    return this.a(n, 0.0);
                }
                if (n2 == 8) {
                    return this.a(n, 100.0);
                }
                if (n2 == 9) {
                    return this.a(n, 10.0 * this.case.getValue() - 270.0);
                }
                if (n2 == 10) {
                    return this.do(n);
                }
                return new Color(230, 250, 230);
            }
        }
    }
    
    Color a(final int n, final double n2) {
        Color color = new Color(150, 150, 150);
        if (n < magnus.a.new.length) {
            if (n2 < magnus.a.new[n]) {
                color = new Color(180, 180, 255);
            }
            else if (n < magnus.a.byte.length && magnus.a.new[n] > -300.0 && n2 < magnus.a.byte[n]) {
                color = new Color(180, 255, 180);
            }
        }
        if (n < magnus.a.byte.length && n2 > magnus.a.byte[n] && magnus.a.byte[n] > -300.0) {
            color = new Color(255, 180, 180);
        }
        return color;
    }
    
    public void a() {
        this.new.setText("Periodic Table and Properties of Elements\n(c) J M Goodman http://www.ch.cam.ac.uk/CUCL/staff/jmg.html\n\nData sources:\n (i) CRC Handbook, 78th Edition, 1997-1998, ed. D R Lide\n (ii) Pure and Applied Chemistry, 1991, 63, 975-990\n (iii) J Phys Chem Ref Data 1993, 22, 1571" + "\n\nAtomic weights and abundances: (i), (ii), (iii)\nMelting and boiling points (i)\n");
    }
    
    public void a(final String s) {
        String text = "";
        int n = 0;
        int i = 1;
        while (i < magnus.a.if.length) {
            String substring;
            if (Character.isSpace(s.charAt(1))) {
                substring = s.substring(0, 1);
            }
            else {
                substring = s;
            }
            if (magnus.a.if[i].compareTo(substring) == 0) {
                n = i;
                text = magnus.a.a[i] + ", " + magnus.a.if[i] + ",   " + "Atomic Number: " + Integer.toString(i) + "\n";
                if (i < magnus.a.do.length && magnus.a.a(i, "Average") > 0.0) {
                    text = text + "Atomic Weight: " + EadUtil.printNumber(magnus.a.a(i, "Average"), 7) + "\n";
                }
                if (magnus.a.new.length > i && magnus.a.new[i] > -500.0) {
                    if (magnus.a.byte.length > i) {
                        if (magnus.a.byte[i] < magnus.a.new[i] && magnus.a.byte[i] > -500.0) {
                            text = text + "\nMelting point: " + Double.toString(magnus.a.new[i]) + " °C (triple point)";
                        }
                        else {
                            text = text + "\nMelting point: " + Double.toString(magnus.a.new[i]) + " °C";
                        }
                    }
                    else {
                        text = text + "\nMelting point: " + Double.toString(magnus.a.new[i]) + " °C";
                    }
                }
                if (magnus.a.byte.length > i && magnus.a.byte[i] > -500.0) {
                    text = text + "\nBoiling point: " + Double.toString(magnus.a.byte[i]) + " °C";
                }
                if (i < magnus.a.do.length && magnus.a.do[i].length > 0) {
                    if (magnus.a.try[i].length == 1) {
                        text += "\nOne Isotope:\n";
                    }
                    else {
                        text = text + "\n" + Integer.toString(magnus.a.do[i].length) + " Isotopes:\n";
                    }
                    double n2 = 0.0;
                    for (int j = 0; j < magnus.a.do[i].length; ++j) {
                        text = text + EadUtil.printNumber(magnus.a.do[i][j], 7) + "   Abundance: " + EadUtil.printNumber(magnus.a.try[i][j] * 100.0, 3) + " %\n";
                        n2 += magnus.a.try[i][j] * 100.0;
                    }
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        this.new.setText(text);
        if (this.if) {
            this.if(n);
        }
    }
}
