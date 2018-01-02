// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

public class c extends Frame
{
    private Button h;
    private Button e;
    private BorderLayout k;
    private BorderLayout j;
    private BorderLayout i;
    private Checkbox l;
    private Checkbox void;
    private Checkbox int;
    private Choice for;
    private Choice d;
    private Choice g;
    private Label case;
    private Label byte;
    private Label try;
    private Label new;
    private Label do;
    private Panel c;
    private Panel b;
    private Panel null;
    private Panel long;
    private Panel goto;
    private Panel else;
    Principale char;
    private TextField f;
    private TextField a;
    j if;
    
    public c(final Principale char1, final j if1) {
        this.f = new TextField();
        this.h = new Button();
        this.c = new Panel();
        this.case = new Label();
        this.k = new BorderLayout();
        this.b = new Panel();
        this.null = new Panel();
        this.e = new Button();
        this.j = new BorderLayout();
        this.long = new Panel();
        this.d = new Choice();
        this.byte = new Label();
        this.try = new Label();
        this.g = new Choice();
        this.goto = new Panel();
        this.l = new Checkbox();
        this.void = new Checkbox();
        this.else = new Panel();
        this.i = new BorderLayout();
        this.do = new Label();
        this.for = new Choice();
        this.new = new Label();
        this.int = new Checkbox();
        this.a = new TextField();
        this.char = char1;
        this.if = if1;
        try {
            this.a();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void if() {
        this.do.setText(this.f.getText());
        this.do.setFont(this.do());
        this.do.setForeground(this.for());
    }
    
    void a(final ActionEvent actionEvent) {
        this.char.setEnabled(true);
        this.char.requestFocus();
        this.if.a(new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false, false, false });
        this.dispose();
    }
    
    void if(final ActionEvent actionEvent) {
        final String replace = this.f.getText().replace(';', ' ');
        final Label label = new Label();
        label.setForeground(this.for());
        label.setFont(this.do());
        if (this.int.getState()) {
            label.setText(replace + "###" + this.a.getText());
        }
        else {
            label.setText(replace);
        }
        final boolean[] array = { false, false, false, false, true, false, false, false, false, false, false, false, false, false };
        if (!replace.equals("")) {
            try {
                this.char.setEnabled(true);
                this.char.requestFocus();
                this.if.a(array);
                if (this.int.getState()) {
                    this.if.a(label, true);
                }
                else {
                    this.if.a(label, false);
                }
            }
            catch (NullPointerException ex) {}
            this.dispose();
        }
    }
    
    void a(final ItemEvent itemEvent) {
        if (this.int.getState()) {
            this.a.setEnabled(true);
            this.a.setEditable(true);
            this.for.select(1);
            this.repaint();
        }
        else {
            this.a.setEnabled(false);
            this.a.setEditable(false);
            this.for.select(0);
            this.repaint();
        }
    }
    
    private void a() throws Exception {
        this.f.setColumns(20);
        this.f.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                dessin.c.this.if();
            }
        });
        this.setLayout(this.k);
        this.h.setLabel("Cancel");
        this.h.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                dessin.c.this.a(actionEvent);
            }
        });
        this.case.setText("              Tape your text     ");
        this.e.setLabel("   Ok   ");
        this.e.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                dessin.c.this.if(actionEvent);
            }
        });
        this.c.setLayout(this.j);
        this.byte.setText("Font :");
        this.try.setText("Size :");
        this.l.setLabel("Bold");
        this.l.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                dessin.c.this.if();
            }
        });
        this.void.setLabel("Italique");
        this.void.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                dessin.c.this.if();
            }
        });
        this.b.setLayout(this.i);
        this.do.setText("                                         ");
        this.new.setText("Colour :");
        this.d.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                dessin.c.this.if();
            }
        });
        this.g.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                dessin.c.this.if();
            }
        });
        this.for.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                dessin.c.this.if();
            }
        });
        this.int.setLabel("make URL link to this text");
        this.int.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                dessin.c.this.a(itemEvent);
            }
        });
        this.a.setColumns(15);
        this.a.setEnabled(false);
        this.a.setEditable(false);
        this.a.setText("http://");
        this.setVisible(true);
        this.add(this.c, "North");
        this.c.add(this.case, "North");
        this.c.add(this.f, "Center");
        this.add(this.b, "Center");
        this.b.add(this.long, "North");
        this.long.add(this.byte, null);
        this.long.add(this.d, null);
        this.b.add(this.goto, "Center");
        this.goto.add(this.void, null);
        this.goto.add(this.l, null);
        this.goto.add(this.new, null);
        this.b.add(this.else, "South");
        this.else.add(this.do, null);
        this.add(this.null, "South");
        this.null.add(this.e, null);
        this.null.add(this.h, null);
        this.goto.add(this.for, null);
        this.goto.add(this.int, null);
        this.long.add(this.try, null);
        this.long.add(this.g, null);
        this.goto.add(this.a, null);
        this.d.addItem("Helvetica");
        this.d.addItem("TimesRoman");
        this.d.addItem("Courier");
        this.d.addItem("SansSerif");
        this.d.addItem("Serif");
        this.d.addItem("Monospaced");
        this.d.select(0);
        this.g.addItem("8");
        this.g.addItem("10");
        this.g.addItem("12");
        this.g.addItem("14");
        this.g.addItem("16");
        this.g.addItem("18");
        this.g.addItem("20");
        this.g.addItem("22");
        this.g.addItem("24");
        this.g.addItem("28");
        this.g.addItem("30");
        this.g.addItem("32");
        this.g.select(2);
        this.for.addItem("Black");
        this.for.addItem("Blue");
        this.for.addItem("Cyan");
        this.for.addItem("Green");
        this.for.addItem("Magenta");
        this.for.addItem("Orange");
        this.for.addItem("Red");
        this.for.addItem("Yellow");
        this.for.select(0);
        this.do.setFont(new Font("Dialog", 0, 12));
    }
    
    private Color for() {
        Color color = Color.black;
        final int selectedIndex = this.for.getSelectedIndex();
        if (selectedIndex == 0) {
            color = Color.black;
        }
        else if (selectedIndex == 1) {
            color = Color.blue;
        }
        else if (selectedIndex == 2) {
            color = Color.cyan;
        }
        else if (selectedIndex == 3) {
            color = Color.green;
        }
        else if (selectedIndex == 4) {
            color = Color.magenta;
        }
        else if (selectedIndex == 5) {
            color = Color.orange;
        }
        else if (selectedIndex == 6) {
            color = Color.red;
        }
        else if (selectedIndex == 7) {
            color = Color.yellow;
        }
        return color;
    }
    
    private Font do() {
        final String selectedItem = this.d.getSelectedItem();
        final int n = this.g.getSelectedIndex() * 2 + 8;
        int n2 = 0;
        if (this.l.getState() && !this.void.getState()) {
            n2 = 1;
        }
        else if (!this.l.getState() && this.void.getState()) {
            n2 = 2;
        }
        else if (this.l.getState() && this.void.getState()) {
            n2 = 3;
        }
        return new Font(selectedItem, n2, n);
    }
}
