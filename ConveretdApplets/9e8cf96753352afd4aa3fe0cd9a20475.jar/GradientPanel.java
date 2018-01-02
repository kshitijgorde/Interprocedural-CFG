import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GradientPanel extends Applet implements ActionListener, ItemListener
{
    Button Prepaint;
    TextField Ptext;
    TextField Pcyclecolor;
    TextField Pcyclespeed;
    TextField Pfontred;
    TextField Pfontblue;
    TextField Pfontgreen;
    TextField Pfontsize;
    TextField Pbgred;
    TextField Pbggreen;
    TextField Pbgblue;
    CheckboxGroup mv;
    Checkbox Pred;
    Checkbox Pgreen;
    Checkbox Pblue;
    Checkbox Pall;
    Checkbox Pupsidedown;
    Label Pistring;
    Label Pisize;
    Label Pistrength;
    Label Pirgb;
    Label Pibgrgb;
    String text;
    int a;
    int pixely;
    int fontsize;
    Color kleur;
    Color bgkleur;
    boolean upsidedown;
    int blue;
    int green;
    int red;
    int bgblue;
    String cyclecolor;
    int bggreen;
    int bgred;
    int cyclespeed;
    int fontwidth;
    
    public GradientPanel() {
        this.Prepaint = new Button("Calculate");
        this.Ptext = new TextField("JavaBoutique", 30);
        this.Pcyclecolor = new TextField("red", 10);
        this.Pcyclespeed = new TextField("10", 10);
        this.Pfontred = new TextField("0", 10);
        this.Pfontblue = new TextField("0", 10);
        this.Pfontgreen = new TextField("0", 10);
        this.Pfontsize = new TextField("30", 10);
        this.Pbgred = new TextField("100", 10);
        this.Pbggreen = new TextField("100", 10);
        this.Pbgblue = new TextField("100", 10);
        this.mv = new CheckboxGroup();
        this.Pred = new Checkbox("Red", this.mv, false);
        this.Pgreen = new Checkbox("Green", this.mv, true);
        this.Pblue = new Checkbox("Blue", this.mv, false);
        this.Pall = new Checkbox("All", this.mv, false);
        this.Pupsidedown = new Checkbox("Reverse", false);
        this.Pistring = new Label("String");
        this.Pisize = new Label("Fontsize");
        this.Pistrength = new Label("Strength");
        this.Pirgb = new Label("StartColor of Gradient (Red,Green,Blue)");
        this.Pibgrgb = new Label("Bgcolor of String (Red,Green,Blue)");
        this.a = 0;
        this.pixely = 10;
        this.upsidedown = false;
        this.fontwidth = 0;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.repaint();
    }
    
    public void init() {
        final Color color = new Color(100, 100, 100);
        this.setBackground(Color.yellow);
        this.setLayout(null);
        this.Ptext.setBounds(30, 100, 60, 20);
        this.Pfontsize.setBounds(120, 100, 60, 20);
        this.Pcyclespeed.setBounds(200, 100, 60, 20);
        this.Pbgred.setBounds(30, 150, 60, 20);
        this.Pbggreen.setBounds(120, 150, 60, 20);
        this.Pbgblue.setBounds(200, 150, 60, 20);
        this.Pfontred.setBounds(30, 200, 60, 20);
        this.Pfontgreen.setBounds(120, 200, 60, 20);
        this.Pfontblue.setBounds(200, 200, 60, 20);
        this.Pupsidedown.setBounds(30, 250, 80, 20);
        this.Prepaint.setBounds(120, 250, 140, 30);
        this.Pred.setBounds(30, 300, 60, 20);
        this.Pblue.setBounds(120, 300, 60, 20);
        this.Pgreen.setBounds(200, 300, 60, 20);
        this.Pistring.setBounds(30, 80, 60, 15);
        this.Pisize.setBounds(120, 80, 60, 15);
        this.Pistrength.setBounds(200, 80, 60, 15);
        this.Pirgb.setBounds(30, 180, 230, 15);
        this.Pibgrgb.setBounds(30, 130, 230, 15);
        this.Pblue.setBackground(color);
        this.Pred.setBackground(color);
        this.Pgreen.setBackground(color);
        this.Ptext.setBackground(Color.white);
        this.Pfontsize.setBackground(Color.white);
        this.Pcyclespeed.setBackground(Color.white);
        this.Pbgred.setBackground(Color.white);
        this.Pbggreen.setBackground(Color.white);
        this.Pbgblue.setBackground(Color.white);
        this.Pfontred.setBackground(Color.white);
        this.Pfontgreen.setBackground(Color.white);
        this.Pfontblue.setBackground(Color.white);
        this.Pupsidedown.setBackground(color);
        this.Prepaint.setBounds(120, 250, 140, 30);
        this.add(this.Pistring);
        this.add(this.Pibgrgb);
        this.add(this.Pisize);
        this.add(this.Pistrength);
        this.add(this.Pirgb);
        this.add(this.Pred);
        this.add(this.Pgreen);
        this.add(this.Pblue);
        this.add(this.Ptext);
        this.add(this.Pfontsize);
        this.add(this.Pcyclespeed);
        this.add(this.Pbgred);
        this.add(this.Pbggreen);
        this.add(this.Pbgblue);
        this.add(this.Pfontred);
        this.add(this.Pfontgreen);
        this.add(this.Pfontblue);
        this.add(this.Ptext);
        this.add(this.Pupsidedown);
        this.add(this.Prepaint);
        this.Prepaint.addActionListener(this);
        this.Pfontblue.addActionListener(this);
        this.Pfontred.addActionListener(this);
        this.Pfontgreen.addActionListener(this);
        this.Pred.addItemListener(this);
        this.Pgreen.addItemListener(this);
        this.Pblue.addItemListener(this);
        this.Pupsidedown.addItemListener(this);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.cyclecolor = this.Pcyclecolor.getText();
        if (this.Pred.getState()) {
            this.cyclecolor = "red";
        }
        if (this.Pgreen.getState()) {
            this.cyclecolor = "green";
        }
        if (this.Pblue.getState()) {
            this.cyclecolor = "blue";
        }
        this.text = this.Ptext.getText();
        this.cyclespeed = Integer.parseInt(this.Pcyclespeed.getText());
        this.fontsize = Integer.parseInt(this.Pfontsize.getText());
        this.red = Integer.parseInt(this.Pfontred.getText());
        this.green = Integer.parseInt(this.Pfontgreen.getText());
        this.blue = Integer.parseInt(this.Pfontblue.getText());
        this.kleur = new Color(this.red, this.green, this.blue);
        this.bgred = Integer.parseInt(this.Pbgred.getText());
        this.bggreen = Integer.parseInt(this.Pbggreen.getText());
        this.bgblue = Integer.parseInt(this.Pbgblue.getText());
        this.bgkleur = new Color(this.bgred, this.bggreen, this.bgblue);
        if (this.Pupsidedown.getState()) {
            this.upsidedown = true;
        }
        else {
            this.upsidedown = false;
        }
        final Image[] array = new Image[this.fontsize + this.fontsize / 2];
        final Graphics[] array2 = new Graphics[this.fontsize + this.fontsize / 2];
        final Font font = new Font("Impact", 0, this.fontsize);
        this.fontwidth = this.getFontMetrics(font).stringWidth(this.text);
        for (int i = 0; i < this.fontsize + this.fontsize / 2; ++i) {
            array[i] = this.createImage(this.fontwidth + 30, 1);
            array2[i] = array[i].getGraphics();
            if (this.cyclecolor.equals("red")) {
                this.red += this.cyclespeed;
                if (this.red > 255) {
                    this.red = 0;
                }
            }
            else if (this.cyclecolor.equals("green")) {
                this.green += this.cyclespeed;
                if (this.green > 255) {
                    this.green = 0;
                }
            }
            else if (this.cyclecolor.equals("blue")) {
                this.blue += this.cyclespeed;
                if (this.blue > 255) {
                    this.blue = 0;
                }
            }
            else if (this.cyclecolor.equals("all")) {
                this.blue += this.cyclespeed;
                this.red += this.cyclespeed;
                this.green += this.cyclespeed;
                if (this.blue > 255) {
                    this.blue = 0;
                }
                if (this.red > 255) {
                    this.red = 0;
                }
                if (this.green > 255) {
                    this.green = 0;
                }
            }
            this.kleur = new Color(this.red, this.green, this.blue);
            array2[i].setColor(this.kleur);
            this.setBackground(this.bgkleur);
            array2[i].setFont(font);
        }
        if (this.upsidedown) {
            for (int j = 0; j < this.fontsize + this.fontsize / 2 - 1; ++j) {
                array2[j].drawString(this.text, 20, this.a);
                graphics.drawImage(array[j], 20, this.pixely + 20, this);
                ++this.a;
                ++this.pixely;
            }
        }
        else {
            for (int k = 0; k < this.fontsize + this.fontsize / 2 - 1; ++k) {
                array2[k].drawString(this.text, 20, this.a);
                graphics.drawImage(array[this.fontsize + this.fontsize / 2 - 1 - k], 20, this.pixely - this.fontsize + 20, this);
                ++this.a;
                ++this.pixely;
            }
        }
        this.a = 0;
        this.pixely = 10;
    }
}
