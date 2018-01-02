import java.awt.Event;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShowColors extends Applet
{
    Dimension size;
    Panel display;
    Color back;
    Color fore;
    Color newcolor;
    Choice colorchoice;
    TextField r;
    TextField g;
    TextField b;
    TextField hr;
    TextField hg;
    TextField hb;
    Graphics solid;
    Button go;
    Button darker;
    Button lighter;
    Button mred;
    Button lred;
    Button mgreen;
    Button lgreen;
    Button mblue;
    Button lblue;
    String hexr;
    String hexg;
    String hexb;
    
    public void init() {
        this.size = this.size();
        if (this.back != null) {
            this.setBackground(this.back);
        }
        if (this.fore != null) {
            this.setForeground(this.fore);
        }
        this.setFont(new Font("Helvetica", 1, 14));
        this.setLayout(new BorderLayout(10, 10));
        (this.display = new Panel()).setLayout(new BorderLayout(10, 10));
        this.add("Center", this.display);
        this.solid = this.display.getGraphics();
        this.newcolor = new Color(192, 192, 192);
        this.paint(this.solid);
        final Panel panel = new Panel();
        this.add("South", panel);
        panel.add(this.darker = new Button("DARKER"));
        panel.add(this.go = new Button("GO"));
        panel.add(this.lighter = new Button("LIGHTER"));
        (this.colorchoice = new Choice()).addItem("none");
        this.colorchoice.addItem("black");
        this.colorchoice.addItem("blue");
        this.colorchoice.addItem("cyan");
        this.colorchoice.addItem("darkGray");
        this.colorchoice.addItem("gray");
        this.colorchoice.addItem("green");
        this.colorchoice.addItem("lightGray");
        this.colorchoice.addItem("magenta");
        this.colorchoice.addItem("orange");
        this.colorchoice.addItem("pink");
        this.colorchoice.addItem("red");
        this.colorchoice.addItem("white");
        this.colorchoice.addItem("yellow");
        this.colorchoice.select("none");
        panel.add(this.colorchoice);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(4, 3, 10, 10));
        this.add("North", panel2);
        panel2.add(this.mred = new Button("+ RED"));
        panel2.add(this.mgreen = new Button("+ GREEN"));
        panel2.add(this.mblue = new Button("+ BLUE"));
        final Color color = this.solid.getColor();
        (this.r = new TextField(20)).setEditable(true);
        this.r.setText(Integer.toString(color.getRed()));
        panel2.add(this.r);
        (this.g = new TextField(20)).setEditable(true);
        this.g.setText(Integer.toString(color.getGreen()));
        panel2.add(this.g);
        (this.b = new TextField(20)).setEditable(true);
        this.b.setText(Integer.toString(color.getBlue()));
        panel2.add(this.b);
        (this.hr = new TextField(20)).setEditable(false);
        this.hr.setText(Integer.toHexString(color.getRed()));
        panel2.add(this.hr);
        (this.hg = new TextField(20)).setEditable(false);
        this.hg.setText(Integer.toHexString(color.getGreen()));
        panel2.add(this.hg);
        (this.hb = new TextField(20)).setEditable(false);
        this.hb.setText(Integer.toHexString(color.getBlue()));
        panel2.add(this.hb);
        panel2.add(this.lred = new Button("- RED"));
        panel2.add(this.lgreen = new Button("- GREEN"));
        panel2.add(this.lblue = new Button("- BLUE"));
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.go) {
            try {
                final String selectedItem = this.colorchoice.getSelectedItem();
                if (selectedItem.equals("none")) {
                    final int int1 = Integer.parseInt(this.r.getText());
                    final int int2 = Integer.parseInt(this.g.getText());
                    final int int3 = Integer.parseInt(this.b.getText());
                    this.hr.setText(Integer.toHexString(int1));
                    this.hg.setText(Integer.toHexString(int2));
                    this.hb.setText(Integer.toHexString(int3));
                    this.newcolor = new Color(int1, int2, int3);
                    this.paint(this.solid);
                    return true;
                }
                if (selectedItem.equals("black")) {
                    this.newcolor = Color.black;
                }
                else if (selectedItem.equals("blue")) {
                    this.newcolor = Color.blue;
                }
                else if (selectedItem.equals("cyan")) {
                    this.newcolor = Color.cyan;
                }
                else if (selectedItem.equals("darkGray")) {
                    this.newcolor = Color.darkGray;
                }
                else if (selectedItem.equals("gray")) {
                    this.newcolor = Color.gray;
                }
                else if (selectedItem.equals("green")) {
                    this.newcolor = Color.green;
                }
                else if (selectedItem.equals("lightGray")) {
                    this.newcolor = Color.lightGray;
                }
                else if (selectedItem.equals("magenta")) {
                    this.newcolor = Color.magenta;
                }
                else if (selectedItem.equals("orange")) {
                    this.newcolor = Color.orange;
                }
                else if (selectedItem.equals("pink")) {
                    this.newcolor = Color.pink;
                }
                else if (selectedItem.equals("red")) {
                    this.newcolor = Color.red;
                }
                else if (selectedItem.equals("white")) {
                    this.newcolor = Color.white;
                }
                else if (selectedItem.equals("yellow")) {
                    this.newcolor = Color.yellow;
                }
                this.r.setText(Integer.toString(this.newcolor.getRed()));
                this.g.setText(Integer.toString(this.newcolor.getGreen()));
                this.b.setText(Integer.toString(this.newcolor.getBlue()));
                this.hr.setText(Integer.toHexString(this.newcolor.getRed()));
                this.hg.setText(Integer.toHexString(this.newcolor.getGreen()));
                this.hb.setText(Integer.toHexString(this.newcolor.getBlue()));
                this.paint(this.solid);
                return true;
            }
            catch (Exception ex) {
                return false;
            }
        }
        if (event.target == this.darker) {
            try {
                this.colorchoice.select("none");
                int int4 = Integer.parseInt(this.r.getText());
                int int5 = Integer.parseInt(this.g.getText());
                int int6 = Integer.parseInt(this.b.getText());
                this.hr.setText(Integer.toHexString(int4));
                this.hg.setText(Integer.toHexString(int5));
                this.hb.setText(Integer.toHexString(int6));
                if (int4 - 5 >= 0) {
                    int4 -= 5;
                }
                else {
                    int4 = 0;
                }
                if (int5 - 5 >= 0) {
                    int5 -= 5;
                }
                else {
                    int5 = 0;
                }
                if (int6 - 5 >= 0) {
                    int6 -= 5;
                }
                else {
                    int6 = 0;
                }
                this.newcolor = new Color(int4, int5, int6);
                this.paint(this.solid);
                this.r.setText(Integer.toString(this.newcolor.getRed()));
                this.g.setText(Integer.toString(this.newcolor.getGreen()));
                this.b.setText(Integer.toString(this.newcolor.getBlue()));
                this.hr.setText(Integer.toHexString(this.newcolor.getRed()));
                this.hg.setText(Integer.toHexString(this.newcolor.getGreen()));
                this.hb.setText(Integer.toHexString(this.newcolor.getBlue()));
                return true;
            }
            catch (Exception ex2) {
                return false;
            }
        }
        if (event.target == this.lred) {
            try {
                this.colorchoice.select("none");
                int int7 = Integer.parseInt(this.r.getText());
                final int int8 = Integer.parseInt(this.g.getText());
                final int int9 = Integer.parseInt(this.b.getText());
                this.hr.setText(Integer.toHexString(int7));
                if (int7 - 5 >= 0) {
                    int7 -= 5;
                }
                else {
                    int7 = 0;
                }
                this.newcolor = new Color(int7, int8, int9);
                this.paint(this.solid);
                this.r.setText(Integer.toString(this.newcolor.getRed()));
                this.hr.setText(Integer.toHexString(this.newcolor.getRed()));
                return true;
            }
            catch (Exception ex3) {
                return false;
            }
        }
        if (event.target == this.mred) {
            try {
                this.colorchoice.select("none");
                int int10 = Integer.parseInt(this.r.getText());
                final int int11 = Integer.parseInt(this.g.getText());
                final int int12 = Integer.parseInt(this.b.getText());
                this.hr.setText(Integer.toHexString(int10));
                if (int10 + 5 <= 255) {
                    int10 += 5;
                }
                else {
                    int10 = 255;
                }
                this.newcolor = new Color(int10, int11, int12);
                this.paint(this.solid);
                this.r.setText(Integer.toString(this.newcolor.getRed()));
                this.hr.setText(Integer.toHexString(this.newcolor.getRed()));
                return true;
            }
            catch (Exception ex4) {
                return false;
            }
        }
        if (event.target == this.lgreen) {
            try {
                this.colorchoice.select("none");
                final int int13 = Integer.parseInt(this.r.getText());
                int int14 = Integer.parseInt(this.g.getText());
                final int int15 = Integer.parseInt(this.b.getText());
                this.hg.setText(Integer.toHexString(int14));
                if (int14 - 5 >= 0) {
                    int14 -= 5;
                }
                else {
                    int14 = 0;
                }
                this.newcolor = new Color(int13, int14, int15);
                this.paint(this.solid);
                this.g.setText(Integer.toString(this.newcolor.getGreen()));
                this.hg.setText(Integer.toHexString(this.newcolor.getGreen()));
                return true;
            }
            catch (Exception ex5) {
                return false;
            }
        }
        if (event.target == this.mgreen) {
            try {
                this.colorchoice.select("none");
                final int int16 = Integer.parseInt(this.r.getText());
                int int17 = Integer.parseInt(this.g.getText());
                final int int18 = Integer.parseInt(this.b.getText());
                this.hg.setText(Integer.toHexString(int17));
                if (int17 + 5 <= 255) {
                    int17 += 5;
                }
                else {
                    int17 = 255;
                }
                this.newcolor = new Color(int16, int17, int18);
                this.paint(this.solid);
                this.g.setText(Integer.toString(this.newcolor.getGreen()));
                this.hg.setText(Integer.toHexString(this.newcolor.getGreen()));
                return true;
            }
            catch (Exception ex6) {
                return false;
            }
        }
        if (event.target == this.lblue) {
            try {
                this.colorchoice.select("none");
                final int int19 = Integer.parseInt(this.r.getText());
                final int int20 = Integer.parseInt(this.g.getText());
                int int21 = Integer.parseInt(this.b.getText());
                this.hb.setText(Integer.toHexString(int21));
                if (int21 - 5 >= 0) {
                    int21 -= 5;
                }
                else {
                    int21 = 0;
                }
                this.newcolor = new Color(int19, int20, int21);
                this.paint(this.solid);
                this.b.setText(Integer.toString(this.newcolor.getBlue()));
                this.hb.setText(Integer.toHexString(this.newcolor.getBlue()));
                return true;
            }
            catch (Exception ex7) {
                return false;
            }
        }
        if (event.target == this.mblue) {
            try {
                this.colorchoice.select("none");
                final int int22 = Integer.parseInt(this.r.getText());
                final int int23 = Integer.parseInt(this.g.getText());
                int int24 = Integer.parseInt(this.b.getText());
                this.hb.setText(Integer.toHexString(int24));
                if (int24 + 5 <= 255) {
                    int24 += 5;
                }
                else {
                    int24 = 255;
                }
                this.newcolor = new Color(int22, int23, int24);
                this.paint(this.solid);
                this.b.setText(Integer.toString(this.newcolor.getBlue()));
                this.hb.setText(Integer.toHexString(this.newcolor.getBlue()));
                return true;
            }
            catch (Exception ex8) {
                return false;
            }
        }
        if (event.target == this.lighter) {
            try {
                this.colorchoice.select("none");
                int int25 = Integer.parseInt(this.r.getText());
                int int26 = Integer.parseInt(this.g.getText());
                int int27 = Integer.parseInt(this.b.getText());
                this.hr.setText(Integer.toHexString(int25));
                this.hg.setText(Integer.toHexString(int26));
                this.hb.setText(Integer.toHexString(int27));
                if (int25 + 5 <= 255) {
                    int25 += 5;
                }
                else {
                    int25 = 255;
                }
                if (int26 + 5 <= 255) {
                    int26 += 5;
                }
                else {
                    int26 = 255;
                }
                if (int27 + 5 <= 255) {
                    int27 += 5;
                }
                else {
                    int27 = 255;
                }
                this.newcolor = new Color(int25, int26, int27);
                this.paint(this.solid);
                this.r.setText(Integer.toString(this.newcolor.getRed()));
                this.g.setText(Integer.toString(this.newcolor.getGreen()));
                this.b.setText(Integer.toString(this.newcolor.getBlue()));
                this.hr.setText(Integer.toHexString(this.newcolor.getRed()));
                this.hg.setText(Integer.toHexString(this.newcolor.getGreen()));
                this.hb.setText(Integer.toHexString(this.newcolor.getBlue()));
                return true;
            }
            catch (Exception ex9) {
                return false;
            }
        }
        return false;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.newcolor);
        graphics.fillRect(0, 0, 1000, 1000);
    }
    
    public ShowColors() {
        this.back = Color.lightGray;
        this.fore = Color.black;
    }
}
