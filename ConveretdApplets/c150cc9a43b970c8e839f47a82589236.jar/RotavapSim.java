import java.awt.Event;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Choice;
import java.awt.Label;
import java.awt.TextField;
import java.awt.CardLayout;
import java.awt.Button;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RotavapSim extends Applet
{
    static Panel E;
    static Panel null;
    static Panel H;
    static Panel t;
    static Button p;
    static Button q;
    static Button C;
    static CardLayout a;
    static CardLayout u;
    static TextField else;
    static TextField e;
    static TextField n;
    static TextField void;
    static TextField v;
    static TextField d;
    static TextField f;
    static TextField A;
    static TextField w;
    static TextField s;
    static TextField char;
    static TextField z;
    static TextField c;
    static TextField goto;
    static TextField h;
    static TextField m;
    static TextField int;
    static TextField J;
    static TextField G;
    static TextField F;
    static TextField l;
    static TextField g;
    static TextField for;
    static TextField j;
    static TextField D;
    static TextField byte;
    static TextField r;
    static TextField I;
    static TextField M;
    static TextField long;
    static Label new;
    static Label i;
    static a N;
    static b O;
    static Choice b;
    Choice do;
    Choice k;
    Choice if;
    Choice o;
    String[] try;
    String[] L;
    static int K;
    static int B;
    static int case;
    
    public void a(final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight, final int n, final int n2, final int anchor) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.weightx = n;
        gridBagConstraints.weighty = n2;
        gridBagConstraints.anchor = anchor;
    }
    
    public void init() {
        System.out.println("Magnus Chemistry");
        System.out.println("================");
        System.out.println("Rotavap Simulator v1.0");
        System.out.println("(c) J M Goodman and C R Stewart, 1999-2003");
        System.out.println("Cambridge University");
        System.out.println("All rights reserved");
        System.out.println("");
        RotavapSim.E = new Panel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        RotavapSim.E.setLayout(layout);
        RotavapSim.E.setBackground(new Color(120, 210, 255));
        RotavapSim.E.setFont(new Font("Dialog", 0, 11));
        this.a(gridBagConstraints, 0, 0, 1, 1, 0, 0, 10);
        (RotavapSim.b = new Choice()).addItem("Binary mixture");
        RotavapSim.b.addItem("Ternary mixture");
        RotavapSim.b.addItem("Boiling point calc.");
        RotavapSim.b.setBackground(Color.lightGray);
        layout.setConstraints(RotavapSim.b, gridBagConstraints);
        RotavapSim.E.add(RotavapSim.b);
        RotavapSim.H = new Panel();
        RotavapSim.a = new CardLayout();
        RotavapSim.H.setLayout(RotavapSim.a);
        final String[] array = { "(enter a b.p.)", "Acetone", "Acetonitrile", "Benzene", "Chloroform", "Cyclohexane", "DCM", "DMF", "DMSO", "Ethanol", "Ether", "Ethyl Acetate", "n-Hexane", "Methanol", "THF", "Toluene", "Water" };
        this.L = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.L[i] = array[i];
        }
        final String[] array2 = { "", "56", "82", "80", "62", "81", "40", "158", "189", "78", "35", "77", "69", "65", "66", "112", "100" };
        this.try = new String[this.L.length];
        for (int j = 0; j < this.L.length; ++j) {
            this.try[j] = array2[j];
        }
        final Panel panel = new Panel();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        panel.setLayout(gridBagLayout);
        this.a(gridBagConstraints2, 0, 0, 3, 1, 0, 0, 17);
        final Label label = new Label("RotaVapSim v1.0 ");
        label.setFont(new Font("Dialog", 1, 12));
        gridBagLayout.setConstraints(label, gridBagConstraints2);
        panel.add(label);
        this.a(gridBagConstraints2, 0, 1, 3, 1, 0, 0, 17);
        final Label label2 = new Label("Solvent:");
        gridBagLayout.setConstraints(label2, gridBagConstraints2);
        panel.add(label2);
        this.a(gridBagConstraints2, 0, 2, 1, 1, 0, 0, 17);
        this.do = new Choice();
        for (int k = 0; k < this.L.length; ++k) {
            this.do.addItem(this.L[k]);
        }
        this.do.select(0);
        this.do.setBackground(Color.white);
        gridBagLayout.setConstraints(this.do, gridBagConstraints2);
        panel.add(this.do);
        this.a(gridBagConstraints2, 1, 2, 1, 1, 0, 0, 17);
        (RotavapSim.e = new TextField("", 5)).setBackground(Color.white);
        gridBagLayout.setConstraints(RotavapSim.e, gridBagConstraints2);
        panel.add(RotavapSim.e);
        this.a(gridBagConstraints2, 2, 2, 1, 1, 0, 0, 17);
        final Label label3 = new Label(" C");
        gridBagLayout.setConstraints(label3, gridBagConstraints2);
        panel.add(label3);
        this.a(gridBagConstraints2, 0, 3, 1, 1, 0, 0, 13);
        final Label label4 = new Label("Solute:");
        gridBagLayout.setConstraints(label4, gridBagConstraints2);
        panel.add(label4);
        this.a(gridBagConstraints2, 1, 3, 1, 1, 0, 0, 17);
        (RotavapSim.n = new TextField("", 5)).setBackground(Color.white);
        gridBagLayout.setConstraints(RotavapSim.n, gridBagConstraints2);
        panel.add(RotavapSim.n);
        this.a(gridBagConstraints2, 2, 3, 1, 1, 0, 0, 17);
        final Label label5 = new Label("C");
        gridBagLayout.setConstraints(label5, gridBagConstraints2);
        panel.add(label5);
        this.a(gridBagConstraints2, 0, 4, 3, 1, 0, 0, 17);
        final Label label6 = new Label("");
        gridBagLayout.setConstraints(label6, gridBagConstraints2);
        panel.add(label6);
        this.a(gridBagConstraints2, 0, 5, 3, 1, 0, 0, 17);
        final Label label7 = new Label("Quantities (moles):");
        label7.setFont(new Font("Dialog", 1, 12));
        gridBagLayout.setConstraints(label7, gridBagConstraints2);
        panel.add(label7);
        this.a(gridBagConstraints2, 0, 6, 1, 1, 0, 0, 13);
        final Label label8 = new Label("Solvent:");
        gridBagLayout.setConstraints(label8, gridBagConstraints2);
        panel.add(label8);
        this.a(gridBagConstraints2, 1, 6, 1, 1, 0, 0, 17);
        (RotavapSim.void = new TextField("", 5)).setBackground(Color.white);
        gridBagLayout.setConstraints(RotavapSim.void, gridBagConstraints2);
        panel.add(RotavapSim.void);
        this.a(gridBagConstraints2, 0, 7, 1, 1, 0, 0, 13);
        final Label label9 = new Label("Solute:");
        gridBagLayout.setConstraints(label9, gridBagConstraints2);
        panel.add(label9);
        this.a(gridBagConstraints2, 1, 7, 1, 1, 0, 0, 17);
        (RotavapSim.v = new TextField("", 5)).setBackground(Color.white);
        gridBagLayout.setConstraints(RotavapSim.v, gridBagConstraints2);
        panel.add(RotavapSim.v);
        this.a(gridBagConstraints2, 0, 8, 3, 1, 0, 0, 17);
        final Label label10 = new Label("");
        gridBagLayout.setConstraints(label10, gridBagConstraints2);
        panel.add(label10);
        this.a(gridBagConstraints2, 0, 9, 1, 1, 0, 0, 13);
        final Label label11 = new Label("Temperature:");
        gridBagLayout.setConstraints(label11, gridBagConstraints2);
        panel.add(label11);
        this.a(gridBagConstraints2, 1, 9, 1, 1, 0, 0, 17);
        (RotavapSim.else = new TextField("20", 5)).setBackground(Color.white);
        gridBagLayout.setConstraints(RotavapSim.else, gridBagConstraints2);
        panel.add(RotavapSim.else);
        this.a(gridBagConstraints2, 2, 9, 1, 1, 0, 0, 17);
        final Label label12 = new Label("C");
        gridBagLayout.setConstraints(label12, gridBagConstraints2);
        panel.add(label12);
        this.a(gridBagConstraints2, 0, 10, 3, 1, 0, 0, 17);
        final Label label13 = new Label("");
        gridBagLayout.setConstraints(label13, gridBagConstraints2);
        panel.add(label13);
        this.a(gridBagConstraints2, 0, 11, 3, 1, 0, 0, 17);
        final Label label14 = new Label("Final Evaporation");
        gridBagLayout.setConstraints(label14, gridBagConstraints2);
        panel.add(label14);
        this.a(gridBagConstraints2, 0, 12, 1, 1, 0, 0, 13);
        final Label label15 = new Label("Extent:");
        gridBagLayout.setConstraints(label15, gridBagConstraints2);
        panel.add(label15);
        this.a(gridBagConstraints2, 1, 12, 1, 1, 0, 0, 10);
        (RotavapSim.d = new TextField("100", 5)).setBackground(Color.white);
        gridBagLayout.setConstraints(RotavapSim.d, gridBagConstraints2);
        panel.add(RotavapSim.d);
        this.a(gridBagConstraints2, 2, 12, 1, 1, 0, 0, 17);
        final Label label16 = new Label("%");
        gridBagLayout.setConstraints(label16, gridBagConstraints2);
        panel.add(label16);
        this.a(gridBagConstraints2, 0, 13, 3, 1, 0, 0, 17);
        final Label label17 = new Label("");
        gridBagLayout.setConstraints(label17, gridBagConstraints2);
        panel.add(label17);
        this.a(gridBagConstraints2, 0, 14, 3, 1, 0, 0, 10);
        (RotavapSim.p = new Button("Calculate")).setBackground(Color.lightGray);
        RotavapSim.p.setFont(new Font("Dialog", 0, 12));
        gridBagLayout.setConstraints(RotavapSim.p, gridBagConstraints2);
        panel.add(RotavapSim.p);
        final Panel panel2 = new Panel();
        final GridBagLayout layout2 = new GridBagLayout();
        panel2.setLayout(layout2);
        this.a(gridBagConstraints2, 0, 0, 3, 1, 0, 0, 17);
        final Label label18 = new Label("RotaVapSim v1.0");
        label18.setFont(new Font("Dialog", 1, 12));
        layout2.setConstraints(label18, gridBagConstraints2);
        panel2.add(label18);
        this.a(gridBagConstraints2, 0, 1, 3, 1, 0, 0, 17);
        final Label label19 = new Label("SolventA:");
        layout2.setConstraints(label19, gridBagConstraints2);
        panel2.add(label19);
        this.a(gridBagConstraints2, 0, 2, 1, 1, 0, 0, 17);
        this.k = new Choice();
        for (int l = 0; l < this.L.length; ++l) {
            this.k.addItem(this.L[l]);
        }
        this.k.select(0);
        this.k.setBackground(Color.white);
        layout2.setConstraints(this.k, gridBagConstraints2);
        panel2.add(this.k);
        this.a(gridBagConstraints2, 1, 2, 1, 1, 0, 0, 10);
        (RotavapSim.w = new TextField("", 5)).setBackground(Color.white);
        layout2.setConstraints(RotavapSim.w, gridBagConstraints2);
        panel2.add(RotavapSim.w);
        this.a(gridBagConstraints2, 2, 2, 1, 1, 0, 0, 17);
        final Label label20 = new Label("C");
        layout2.setConstraints(label20, gridBagConstraints2);
        panel2.add(label20);
        this.a(gridBagConstraints2, 0, 3, 3, 1, 0, 0, 17);
        final Label label21 = new Label("SolventB:");
        layout2.setConstraints(label21, gridBagConstraints2);
        panel2.add(label21);
        this.a(gridBagConstraints2, 0, 4, 1, 1, 0, 0, 17);
        this.if = new Choice();
        for (int n = 0; n < this.L.length; ++n) {
            this.if.addItem(this.L[n]);
        }
        this.if.select(0);
        this.if.setBackground(Color.white);
        layout2.setConstraints(this.if, gridBagConstraints2);
        panel2.add(this.if);
        this.a(gridBagConstraints2, 1, 4, 1, 1, 0, 0, 10);
        (RotavapSim.s = new TextField("", 5)).setBackground(Color.white);
        layout2.setConstraints(RotavapSim.s, gridBagConstraints2);
        panel2.add(RotavapSim.s);
        this.a(gridBagConstraints2, 2, 4, 1, 1, 0, 0, 17);
        final Label label22 = new Label("C");
        layout2.setConstraints(label22, gridBagConstraints2);
        panel2.add(label22);
        this.a(gridBagConstraints2, 0, 5, 1, 1, 0, 0, 13);
        final Label label23 = new Label("Solute:");
        layout2.setConstraints(label23, gridBagConstraints2);
        panel2.add(label23);
        this.a(gridBagConstraints2, 1, 5, 1, 1, 0, 0, 10);
        (RotavapSim.char = new TextField("", 5)).setBackground(Color.white);
        layout2.setConstraints(RotavapSim.char, gridBagConstraints2);
        panel2.add(RotavapSim.char);
        this.a(gridBagConstraints2, 2, 5, 1, 1, 0, 0, 17);
        final Label label24 = new Label("C");
        layout2.setConstraints(label24, gridBagConstraints2);
        panel2.add(label24);
        this.a(gridBagConstraints2, 0, 6, 3, 1, 0, 0, 17);
        final Label label25 = new Label("Quantities (moles):");
        label25.setFont(new Font("Dialog", 1, 12));
        layout2.setConstraints(label25, gridBagConstraints2);
        panel2.add(label25);
        this.a(gridBagConstraints2, 0, 7, 1, 1, 0, 0, 13);
        final Label label26 = new Label("SolventA:");
        layout2.setConstraints(label26, gridBagConstraints2);
        panel2.add(label26);
        this.a(gridBagConstraints2, 1, 7, 1, 1, 0, 0, 10);
        (RotavapSim.z = new TextField("", 5)).setBackground(Color.white);
        layout2.setConstraints(RotavapSim.z, gridBagConstraints2);
        panel2.add(RotavapSim.z);
        this.a(gridBagConstraints2, 0, 8, 1, 1, 0, 0, 13);
        final Label label27 = new Label("SolventB:");
        layout2.setConstraints(label27, gridBagConstraints2);
        panel2.add(label27);
        this.a(gridBagConstraints2, 1, 8, 1, 1, 0, 0, 10);
        (RotavapSim.c = new TextField("", 5)).setBackground(Color.white);
        layout2.setConstraints(RotavapSim.c, gridBagConstraints2);
        panel2.add(RotavapSim.c);
        this.a(gridBagConstraints2, 0, 9, 1, 1, 0, 0, 13);
        final Label label28 = new Label("Solute:");
        layout2.setConstraints(label28, gridBagConstraints2);
        panel2.add(label28);
        this.a(gridBagConstraints2, 1, 9, 1, 1, 0, 0, 10);
        (RotavapSim.goto = new TextField("", 5)).setBackground(Color.white);
        layout2.setConstraints(RotavapSim.goto, gridBagConstraints2);
        panel2.add(RotavapSim.goto);
        this.a(gridBagConstraints2, 0, 10, 3, 1, 0, 0, 17);
        final Label label29 = new Label("");
        layout2.setConstraints(label29, gridBagConstraints2);
        panel2.add(label29);
        this.a(gridBagConstraints2, 0, 11, 1, 1, 0, 0, 13);
        final Label label30 = new Label("Temperature:");
        layout2.setConstraints(label30, gridBagConstraints2);
        panel2.add(label30);
        this.a(gridBagConstraints2, 1, 11, 1, 1, 0, 0, 10);
        (RotavapSim.h = new TextField("20", 5)).setBackground(Color.white);
        layout2.setConstraints(RotavapSim.h, gridBagConstraints2);
        panel2.add(RotavapSim.h);
        this.a(gridBagConstraints2, 2, 11, 1, 1, 0, 0, 17);
        final Label label31 = new Label("C");
        layout2.setConstraints(label31, gridBagConstraints2);
        panel2.add(label31);
        this.a(gridBagConstraints2, 0, 12, 3, 1, 0, 0, 17);
        final Label label32 = new Label("Final Evaporation");
        layout2.setConstraints(label32, gridBagConstraints2);
        panel2.add(label32);
        this.a(gridBagConstraints2, 0, 13, 1, 1, 0, 0, 13);
        final Label label33 = new Label("Extent:");
        layout2.setConstraints(label33, gridBagConstraints2);
        panel2.add(label33);
        this.a(gridBagConstraints2, 1, 13, 1, 1, 0, 0, 10);
        (RotavapSim.m = new TextField("100", 5)).setBackground(Color.white);
        layout2.setConstraints(RotavapSim.m, gridBagConstraints2);
        panel2.add(RotavapSim.m);
        this.a(gridBagConstraints2, 2, 13, 1, 1, 0, 0, 17);
        final Label label34 = new Label("%");
        layout2.setConstraints(label34, gridBagConstraints2);
        panel2.add(label34);
        this.a(gridBagConstraints2, 0, 14, 3, 1, 0, 0, 10);
        (RotavapSim.q = new Button("Calculate")).setBackground(Color.lightGray);
        RotavapSim.q.setFont(new Font("Dialog", 0, 12));
        layout2.setConstraints(RotavapSim.q, gridBagConstraints2);
        panel2.add(RotavapSim.q);
        final Panel panel3 = new Panel();
        panel3.setLayout(gridBagLayout);
        this.a(gridBagConstraints2, 0, 0, 3, 1, 0, 0, 17);
        final Label label35 = new Label("RotaVapSim v1.0");
        label35.setFont(new Font("Dialog", 1, 12));
        gridBagLayout.setConstraints(label35, gridBagConstraints2);
        panel3.add(label35);
        this.a(gridBagConstraints2, 0, 1, 3, 1, 0, 0, 17);
        final Label label36 = new Label("Solvent:");
        gridBagLayout.setConstraints(label36, gridBagConstraints2);
        panel3.add(label36);
        this.a(gridBagConstraints2, 0, 2, 1, 1, 0, 0, 17);
        this.o = new Choice();
        for (int n2 = 0; n2 < this.L.length; ++n2) {
            this.o.addItem(this.L[n2]);
        }
        this.o.select(0);
        this.o.setBackground(Color.white);
        gridBagLayout.setConstraints(this.o, gridBagConstraints2);
        panel3.add(this.o);
        this.a(gridBagConstraints2, 1, 2, 1, 1, 0, 0, 17);
        (RotavapSim.long = new TextField("", 5)).setBackground(Color.white);
        gridBagLayout.setConstraints(RotavapSim.long, gridBagConstraints2);
        panel3.add(RotavapSim.long);
        this.a(gridBagConstraints2, 2, 2, 1, 1, 0, 0, 17);
        final Label label37 = new Label("C");
        gridBagLayout.setConstraints(label37, gridBagConstraints2);
        panel3.add(label37);
        this.a(gridBagConstraints2, 0, 4, 3, 1, 0, 0, 17);
        final Label label38 = new Label("");
        gridBagLayout.setConstraints(label38, gridBagConstraints2);
        panel3.add(label38);
        this.a(gridBagConstraints2, 0, 5, 3, 1, 0, 0, 17);
        final Label label39 = new Label("Quantities (moles):");
        label39.setFont(new Font("Dialog", 1, 12));
        gridBagLayout.setConstraints(label39, gridBagConstraints2);
        panel3.add(label39);
        this.a(gridBagConstraints2, 0, 6, 1, 1, 0, 0, 13);
        final Label label40 = new Label("Solvent:");
        gridBagLayout.setConstraints(label40, gridBagConstraints2);
        panel3.add(label40);
        this.a(gridBagConstraints2, 1, 6, 1, 1, 0, 0, 17);
        (RotavapSim.j = new TextField("", 5)).setBackground(Color.white);
        gridBagLayout.setConstraints(RotavapSim.j, gridBagConstraints2);
        panel3.add(RotavapSim.j);
        this.a(gridBagConstraints2, 0, 7, 1, 1, 0, 0, 13);
        final Label label41 = new Label("Solute:");
        gridBagLayout.setConstraints(label41, gridBagConstraints2);
        panel3.add(label41);
        this.a(gridBagConstraints2, 1, 7, 1, 1, 0, 0, 17);
        (RotavapSim.D = new TextField("", 5)).setBackground(Color.white);
        gridBagLayout.setConstraints(RotavapSim.D, gridBagConstraints2);
        panel3.add(RotavapSim.D);
        this.a(gridBagConstraints2, 0, 8, 3, 1, 0, 0, 17);
        final Label label42 = new Label("");
        gridBagLayout.setConstraints(label42, gridBagConstraints2);
        panel3.add(label42);
        this.a(gridBagConstraints2, 0, 9, 1, 1, 0, 0, 13);
        final Label label43 = new Label("Temperature:");
        gridBagLayout.setConstraints(label43, gridBagConstraints2);
        panel3.add(label43);
        this.a(gridBagConstraints2, 1, 9, 1, 1, 0, 0, 17);
        (RotavapSim.for = new TextField("20", 5)).setBackground(Color.white);
        gridBagLayout.setConstraints(RotavapSim.for, gridBagConstraints2);
        panel3.add(RotavapSim.for);
        this.a(gridBagConstraints2, 2, 9, 1, 1, 0, 0, 17);
        final Label label44 = new Label("C");
        gridBagLayout.setConstraints(label44, gridBagConstraints2);
        panel3.add(label44);
        this.a(gridBagConstraints2, 0, 10, 3, 1, 0, 0, 17);
        final Label label45 = new Label("");
        gridBagLayout.setConstraints(label45, gridBagConstraints2);
        panel3.add(label45);
        this.a(gridBagConstraints2, 0, 11, 3, 1, 0, 0, 17);
        final Label label46 = new Label("Final Evaporation");
        gridBagLayout.setConstraints(label46, gridBagConstraints2);
        panel3.add(label46);
        this.a(gridBagConstraints2, 0, 12, 1, 1, 0, 0, 13);
        final Label label47 = new Label("Extent:");
        gridBagLayout.setConstraints(label47, gridBagConstraints2);
        panel3.add(label47);
        this.a(gridBagConstraints2, 1, 12, 1, 1, 0, 0, 10);
        (RotavapSim.byte = new TextField("100", 5)).setBackground(Color.white);
        gridBagLayout.setConstraints(RotavapSim.byte, gridBagConstraints2);
        panel3.add(RotavapSim.byte);
        this.a(gridBagConstraints2, 2, 12, 1, 1, 0, 0, 17);
        final Label label48 = new Label("%");
        gridBagLayout.setConstraints(label48, gridBagConstraints2);
        panel3.add(label48);
        this.a(gridBagConstraints2, 0, 13, 1, 1, 0, 0, 13);
        final Label label49 = new Label("Ratio solute/solvent:");
        gridBagLayout.setConstraints(label49, gridBagConstraints2);
        panel3.add(label49);
        this.a(gridBagConstraints2, 1, 13, 1, 1, 0, 0, 10);
        (RotavapSim.M = new TextField("", 5)).setBackground(Color.white);
        gridBagLayout.setConstraints(RotavapSim.M, gridBagConstraints2);
        panel3.add(RotavapSim.M);
        this.a(gridBagConstraints2, 0, 14, 3, 1, 0, 0, 17);
        final Label label50 = new Label("");
        gridBagLayout.setConstraints(label50, gridBagConstraints2);
        panel3.add(label50);
        this.a(gridBagConstraints2, 0, 15, 3, 1, 0, 0, 10);
        (RotavapSim.C = new Button("Calculate")).setBackground(Color.lightGray);
        RotavapSim.C.setFont(new Font("Dialog", 0, 12));
        gridBagLayout.setConstraints(RotavapSim.C, gridBagConstraints2);
        panel3.add(RotavapSim.C);
        RotavapSim.H.add("Binary", panel);
        RotavapSim.H.add("Ternary", panel2);
        RotavapSim.H.add("Calculate", panel3);
        this.a(gridBagConstraints, 0, 2, 1, 1, 0, 0, 17);
        layout.setConstraints(RotavapSim.H, gridBagConstraints);
        RotavapSim.E.add(RotavapSim.H);
        RotavapSim.a.show(RotavapSim.H, "Binary");
        (RotavapSim.null = new Panel()).setLayout(new BorderLayout());
        RotavapSim.null.setBackground(Color.white);
        RotavapSim.null.setFont(new Font("Dialog", 0, 11));
        RotavapSim.t = new Panel();
        RotavapSim.u = new CardLayout();
        RotavapSim.t.setLayout(RotavapSim.u);
        RotavapSim.t.setBackground(new Color(120, 210, 255));
        final Panel panel4 = new Panel();
        final GridBagLayout layout3 = new GridBagLayout();
        panel4.setLayout(layout3);
        this.a(gridBagConstraints2, 0, 0, 4, 1, 10, 0, 17);
        final Label label51 = new Label("New Molar Quantities after evaporation:");
        gridBagConstraints2.fill = 2;
        layout3.setConstraints(label51, gridBagConstraints2);
        panel4.add(label51);
        this.a(gridBagConstraints2, 0, 1, 1, 1, 10, 0, 13);
        final Label label52 = new Label("Solvent:");
        gridBagConstraints2.fill = 0;
        layout3.setConstraints(label52, gridBagConstraints2);
        panel4.add(label52);
        this.a(gridBagConstraints2, 1, 1, 1, 1, 10, 0, 17);
        (RotavapSim.f = new TextField("", 5)).setBackground(Color.lightGray);
        RotavapSim.f.setEditable(false);
        layout3.setConstraints(RotavapSim.f, gridBagConstraints2);
        panel4.add(RotavapSim.f);
        this.a(gridBagConstraints2, 2, 1, 1, 1, 10, 0, 13);
        final Label label53 = new Label("Solute:");
        layout3.setConstraints(label53, gridBagConstraints2);
        panel4.add(label53);
        this.a(gridBagConstraints2, 3, 1, 1, 1, 10, 0, 17);
        (RotavapSim.A = new TextField("", 5)).setBackground(Color.lightGray);
        RotavapSim.A.setEditable(false);
        layout3.setConstraints(RotavapSim.A, gridBagConstraints2);
        panel4.add(RotavapSim.A);
        final Panel panel5 = new Panel();
        final GridBagLayout layout4 = new GridBagLayout();
        panel5.setLayout(layout4);
        this.a(gridBagConstraints2, 0, 0, 6, 1, 10, 0, 17);
        final Label label54 = new Label("New Molar Quantities after Evaporation:");
        gridBagConstraints2.fill = 2;
        layout4.setConstraints(label54, gridBagConstraints2);
        panel5.add(label54);
        this.a(gridBagConstraints2, 0, 1, 1, 1, 10, 0, 13);
        final Label label55 = new Label("SolventA:");
        gridBagConstraints2.fill = 0;
        layout4.setConstraints(label55, gridBagConstraints2);
        panel5.add(label55);
        this.a(gridBagConstraints2, 1, 1, 1, 1, 10, 0, 17);
        (RotavapSim.int = new TextField("", 5)).setBackground(Color.lightGray);
        RotavapSim.int.setEditable(false);
        layout4.setConstraints(RotavapSim.int, gridBagConstraints2);
        panel5.add(RotavapSim.int);
        this.a(gridBagConstraints2, 2, 1, 1, 1, 10, 0, 13);
        final Label label56 = new Label("SolventB:");
        layout4.setConstraints(label56, gridBagConstraints2);
        panel5.add(label56);
        this.a(gridBagConstraints2, 3, 1, 1, 1, 10, 0, 17);
        (RotavapSim.J = new TextField("", 5)).setBackground(Color.lightGray);
        RotavapSim.J.setEditable(false);
        layout4.setConstraints(RotavapSim.J, gridBagConstraints2);
        panel5.add(RotavapSim.J);
        this.a(gridBagConstraints2, 4, 1, 1, 1, 10, 0, 13);
        final Label label57 = new Label("Solute:");
        layout4.setConstraints(label57, gridBagConstraints2);
        panel5.add(label57);
        this.a(gridBagConstraints2, 5, 1, 1, 1, 10, 0, 17);
        (RotavapSim.G = new TextField("", 5)).setBackground(Color.lightGray);
        RotavapSim.G.setEditable(false);
        layout4.setConstraints(RotavapSim.G, gridBagConstraints2);
        panel5.add(RotavapSim.G);
        final Panel panel6 = new Panel();
        final GridBagLayout layout5 = new GridBagLayout();
        panel6.setLayout(layout5);
        this.a(gridBagConstraints2, 0, 0, 1, 1, 10, 0, 17);
        final Label label58 = new Label("Solute boiling point");
        gridBagConstraints2.fill = 2;
        layout5.setConstraints(label58, gridBagConstraints2);
        panel6.add(label58);
        this.a(gridBagConstraints2, 1, 0, 4, 1, 10, 0, 17);
        RotavapSim.i = new Label(" - ");
        gridBagConstraints2.fill = 2;
        layout5.setConstraints(RotavapSim.i, gridBagConstraints2);
        panel6.add(RotavapSim.i);
        this.a(gridBagConstraints2, 0, 1, 1, 1, 10, 0, 13);
        final Label label59 = new Label("Approximate boiling point");
        gridBagConstraints2.fill = 0;
        layout5.setConstraints(label59, gridBagConstraints2);
        panel6.add(label59);
        this.a(gridBagConstraints2, 1, 1, 1, 1, 10, 0, 17);
        (RotavapSim.g = new TextField("", 7)).setBackground(Color.lightGray);
        RotavapSim.g.setEditable(false);
        layout5.setConstraints(RotavapSim.g, gridBagConstraints2);
        panel6.add(RotavapSim.g);
        this.a(gridBagConstraints2, 2, 1, 1, 1, 10, 0, 13);
        final Label label60 = new Label("Solvent:");
        layout5.setConstraints(label60, gridBagConstraints2);
        panel6.add(label60);
        this.a(gridBagConstraints2, 3, 1, 1, 1, 10, 0, 17);
        (RotavapSim.l = new TextField("", 5)).setBackground(Color.lightGray);
        RotavapSim.l.setEditable(false);
        layout5.setConstraints(RotavapSim.l, gridBagConstraints2);
        panel6.add(RotavapSim.l);
        this.a(gridBagConstraints2, 4, 1, 1, 1, 10, 0, 13);
        final Label label61 = new Label("Solute:");
        layout5.setConstraints(label61, gridBagConstraints2);
        panel6.add(label61);
        this.a(gridBagConstraints2, 5, 1, 1, 1, 10, 0, 17);
        (RotavapSim.F = new TextField("", 5)).setBackground(Color.lightGray);
        RotavapSim.F.setEditable(false);
        layout5.setConstraints(RotavapSim.F, gridBagConstraints2);
        panel6.add(RotavapSim.F);
        final Panel panel7 = new Panel();
        panel7.setBackground(Color.white);
        panel7.setLayout(new GridLayout(1, 1));
        panel7.add(RotavapSim.new = new Label(""));
        RotavapSim.t.add("none", panel7);
        RotavapSim.t.add("binary", panel4);
        RotavapSim.t.add("ternary", panel5);
        RotavapSim.t.add("calculate", panel6);
        RotavapSim.N = new a();
        RotavapSim.null.add("Center", RotavapSim.N);
        RotavapSim.null.add("South", RotavapSim.t);
        RotavapSim.u.show(RotavapSim.t, "none");
        this.setLayout(new BorderLayout());
        this.add("West", RotavapSim.E);
        this.add("Center", RotavapSim.null);
    }
    
    public void paint(final Graphics graphics) {
        RotavapSim.N.paint(graphics);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == RotavapSim.p) {
            return do();
        }
        if (event.target == RotavapSim.q) {
            return do();
        }
        if (event.target == RotavapSim.C) {
            return do();
        }
        if (event.target == this.do) {
            RotavapSim.e.setText("" + this.try[this.do.getSelectedIndex()]);
            if (this.do.getSelectedIndex() != 0) {
                RotavapSim.e.setEditable(false);
            }
            else {
                RotavapSim.e.setEditable(true);
            }
            return true;
        }
        if (event.target == this.k) {
            RotavapSim.w.setText("" + this.try[this.k.getSelectedIndex()]);
            if (this.k.getSelectedIndex() != 0) {
                RotavapSim.w.setEditable(false);
            }
            else {
                RotavapSim.w.setEditable(true);
            }
            return true;
        }
        if (event.target == this.if) {
            RotavapSim.s.setText("" + this.try[this.if.getSelectedIndex()]);
            if (this.if.getSelectedIndex() != 0) {
                RotavapSim.s.setEditable(false);
            }
            else {
                RotavapSim.s.setEditable(true);
            }
            return true;
        }
        if (event.target == this.o) {
            RotavapSim.long.setText("" + this.try[this.o.getSelectedIndex()]);
            if (this.o.getSelectedIndex() != 0) {
                RotavapSim.long.setEditable(false);
            }
            else {
                RotavapSim.long.setEditable(true);
            }
            return true;
        }
        if (event.target == RotavapSim.b) {
            if (RotavapSim.b.getSelectedIndex() == 0) {
                RotavapSim.a.show(RotavapSim.H, "Binary");
                if (RotavapSim.K > 0) {
                    return do();
                }
                a.a = null;
                RotavapSim.new.setText("");
                RotavapSim.u.show(RotavapSim.t, "none");
                RotavapSim.N.repaint();
            }
            else if (RotavapSim.b.getSelectedIndex() == 1) {
                RotavapSim.a.show(RotavapSim.H, "Ternary");
                if (RotavapSim.B > 0) {
                    return do();
                }
                a.a = null;
                RotavapSim.new.setText("");
                RotavapSim.u.show(RotavapSim.t, "none");
                RotavapSim.N.repaint();
            }
            else {
                RotavapSim.a.show(RotavapSim.H, "Calculate");
                if (RotavapSim.case > 0) {
                    return do();
                }
                a.a = null;
                RotavapSim.new.setText("");
                RotavapSim.u.show(RotavapSim.t, "none");
                RotavapSim.N.repaint();
            }
            return true;
        }
        return false;
    }
    
    public boolean keyDown(final Event event, final int n) {
        return event.id == 401 && n == 10 && do();
    }
    
    public static boolean do() {
        if (RotavapSim.b.getSelectedIndex() == 0) {
            ++RotavapSim.K;
            RotavapSim.f.setText("");
            RotavapSim.A.setText("");
        }
        else if (RotavapSim.b.getSelectedIndex() == 1) {
            ++RotavapSim.B;
            RotavapSim.int.setText("");
            RotavapSim.J.setText("");
            RotavapSim.G.setText("");
        }
        else {
            ++RotavapSim.case;
            RotavapSim.F.setText("");
            RotavapSim.l.setText("");
            RotavapSim.g.setText("");
        }
        RotavapSim.u.show(RotavapSim.t, "none");
        if (int()) {
            RotavapSim.new.setText("Error: at least one field is blank - all fields must be filled.");
            a.a = null;
            RotavapSim.N.repaint(100L);
        }
        else if (if()) {
            RotavapSim.new.setText("Error: at least one field contains an invalid numberC.");
            a.a = null;
            RotavapSim.N.repaint(100L);
        }
        else if (for()) {
            RotavapSim.new.setText("Error: One or more temperatures is less than absolute zero.");
            a.a = null;
            RotavapSim.N.repaint(100L);
        }
        else if (a()) {
            RotavapSim.new.setText("Error: Final Evaporation Extent must be between 0.1 and 100%.");
            a.a = null;
            RotavapSim.N.repaint(100L);
        }
        else {
            RotavapSim.O = new b();
            if (a.a != null) {
                if (RotavapSim.b.getSelectedIndex() == 0) {
                    RotavapSim.u.show(RotavapSim.t, "binary");
                }
                if (RotavapSim.b.getSelectedIndex() == 1) {
                    RotavapSim.u.show(RotavapSim.t, "ternary");
                }
                if (RotavapSim.b.getSelectedIndex() == 2) {
                    RotavapSim.u.show(RotavapSim.t, "calculate");
                }
            }
            RotavapSim.N.repaint(100L);
        }
        return true;
    }
    
    public static boolean int() {
        if (RotavapSim.b.getSelectedIndex() == 0) {
            return RotavapSim.e.getText().equals("") || RotavapSim.n.getText().equals("") || RotavapSim.void.getText().equals("") || RotavapSim.v.getText().equals("") || RotavapSim.else.getText().equals("") || RotavapSim.d.getText().equals("");
        }
        if (RotavapSim.b.getSelectedIndex() != 1) {
            return RotavapSim.long.getText().equals("") || RotavapSim.M.getText().equals("") || RotavapSim.j.getText().equals("") || RotavapSim.D.getText().equals("") || RotavapSim.for.getText().equals("") || RotavapSim.byte.getText().equals("");
        }
        System.out.println("Ternary empty field check");
        System.out.println("::" + RotavapSim.s.getText() + "::" + RotavapSim.w.getText() + "::" + RotavapSim.char.getText() + "::");
        if (RotavapSim.w.getText().equals("")) {
            return true;
        }
        if (RotavapSim.s.getText().equals("")) {
            return true;
        }
        if (RotavapSim.char.getText().equals("")) {
            return true;
        }
        if (RotavapSim.z.getText().equals("")) {
            return true;
        }
        if (RotavapSim.c.getText().equals("")) {
            return true;
        }
        if (RotavapSim.goto.getText().equals("")) {
            return true;
        }
        if (RotavapSim.h.getText().equals("")) {
            return true;
        }
        if (RotavapSim.m.getText().equals("")) {
            return true;
        }
        System.out.println("Ternary empty fields OK");
        return false;
    }
    
    public static boolean if() {
        if (RotavapSim.b.getSelectedIndex() == 0) {
            return Double.valueOf(RotavapSim.void.getText()).isNaN() || Double.valueOf(RotavapSim.v.getText()).isNaN();
        }
        if (RotavapSim.b.getSelectedIndex() == 1) {
            return Double.valueOf(RotavapSim.z.getText()).isNaN() || Double.valueOf(RotavapSim.c.getText()).isNaN() || Double.valueOf(RotavapSim.goto.getText()).isNaN();
        }
        return Double.valueOf(RotavapSim.j.getText()).isNaN() || Double.valueOf(RotavapSim.D.getText()).isNaN();
    }
    
    public static boolean for() {
        if (RotavapSim.b.getSelectedIndex() == 0) {
            return Double.valueOf(RotavapSim.e.getText()) + 273.15 <= 0.0 || Double.valueOf(RotavapSim.n.getText()) + 273.15 <= 0.0 || Double.valueOf(RotavapSim.else.getText()) + 273.15 <= 0.0;
        }
        if (RotavapSim.b.getSelectedIndex() == 1) {
            return Double.valueOf(RotavapSim.w.getText()) + 273.15 <= 0.0 || Double.valueOf(RotavapSim.s.getText()) + 273.15 <= 0.0 || Double.valueOf(RotavapSim.char.getText()) + 273.15 <= 0.0 || Double.valueOf(RotavapSim.h.getText()) + 273.15 <= 0.0;
        }
        return Double.valueOf(RotavapSim.long.getText()) + 273.15 <= 0.0 || Double.valueOf(RotavapSim.for.getText()) + 273.15 <= 0.0;
    }
    
    public static boolean a() {
        if (RotavapSim.b.getSelectedIndex() == 0) {
            return Double.valueOf(RotavapSim.d.getText()) < 0.1 || Double.valueOf(RotavapSim.d.getText()) > 100.0;
        }
        return Double.valueOf(RotavapSim.m.getText()) < 0.1 || Double.valueOf(RotavapSim.m.getText()) > 100.0;
    }
    
    static {
        RotavapSim.K = 0;
        RotavapSim.B = 0;
        RotavapSim.case = 0;
    }
}
