import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Event;
import java.awt.Choice;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Label;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BarCodeTest extends Applet
{
    BarCode3of9 bc;
    Label l1;
    Label l2;
    Label l3;
    Label l4;
    Label l5;
    Label l6;
    Label l7;
    Label l8;
    Label l9;
    Panel p;
    Panel p0;
    Panel p1;
    Panel p2;
    Panel p3;
    Panel p4;
    Panel p5;
    Panel p6;
    Panel p7;
    Panel p8;
    Panel p9;
    TextField tf;
    Checkbox cb1;
    Checkbox cb2;
    Checkbox cb3;
    Checkbox cb4;
    Checkbox cb5;
    Checkbox cb6;
    Checkbox cb7;
    Checkbox cb8;
    Checkbox cb9;
    Checkbox cb10;
    Checkbox cb11;
    Checkbox cb12;
    Checkbox cb13;
    Checkbox cb14;
    Checkbox cb15;
    Checkbox cb16;
    CheckboxGroup cbg1;
    CheckboxGroup cbg2;
    CheckboxGroup cbg3;
    CheckboxGroup cbg4;
    CheckboxGroup cbg5;
    Choice ch;
    Choice ch1;
    int fontdim;
    
    public BarCodeTest() {
        this.ch = new Choice();
        this.ch1 = new Choice();
        this.fontdim = 8;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Checkbox) {
            if (((Checkbox)event.target).getLabel().equals("Text Yes")) {
                this.bc.setTextInside(true);
                this.cb9.enable();
                this.cb10.enable();
                this.cb11.enable();
                this.cb12.enable();
                this.cb13.enable();
                this.cb14.enable();
                this.cb15.enable();
                this.cb16.enable();
                this.ch1.enable();
            }
            if (((Checkbox)event.target).getLabel().equals("Text No")) {
                this.bc.setTextInside(false);
                this.cb9.disable();
                this.cb10.disable();
                this.cb11.disable();
                this.cb12.disable();
                this.cb13.disable();
                this.cb14.disable();
                this.cb15.disable();
                this.cb16.disable();
                this.ch1.disable();
            }
            if (((Checkbox)event.target).getLabel().equals("small")) {
                this.bc.setDimension(1);
            }
            if (((Checkbox)event.target).getLabel().equals("medium")) {
                this.bc.setDimension(2);
            }
            if (((Checkbox)event.target).getLabel().equals("large")) {
                this.bc.setDimension(3);
            }
            if (((Checkbox)event.target).getLabel().equals("8")) {
                this.fontdim = 8;
            }
            if (((Checkbox)event.target).getLabel().equals("10")) {
                this.fontdim = 10;
            }
            if (((Checkbox)event.target).getLabel().equals("12")) {
                this.fontdim = 12;
            }
            if (((Checkbox)event.target).getLabel().equals("14")) {
                this.fontdim = 14;
            }
            if (((Checkbox)event.target).getLabel().equals("16")) {
                this.fontdim = 16;
            }
            if (((Checkbox)event.target).getLabel().equals("Baseline")) {
                this.bc.setTextAlign(0);
            }
            if (((Checkbox)event.target).getLabel().equals("Middleline")) {
                this.bc.setTextAlign(1);
            }
            if (((Checkbox)event.target).getLabel().equals("Topline")) {
                this.bc.setTextAlign(2);
            }
        }
        if (event.target instanceof TextField) {
            try {
                this.bc.setString(this.tf.getText());
            }
            catch (Exception ex) {
                this.tf.setText(ex.getMessage());
                this.tf.selectAll();
            }
        }
        if (event.target instanceof Choice) {
            if (event.target == this.ch) {
                this.bc.setStyle(this.ch.getSelectedIndex());
            }
            if (event.target == this.ch1) {
                this.bc.setFont(new Font(this.ch1.getSelectedItem(), 0, this.fontdim));
            }
        }
        return true;
    }
    
    public void init() {
        this.p = new Panel();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        this.l1 = new Label("Insert text to encode:");
        this.tf = new TextField("HELLO", 40);
        (this.p1 = new Panel()).add(this.l1);
        this.p1.add(this.tf);
        this.p.add(this.p1);
        this.l2 = new Label("Select a variant:");
        this.ch.addItem("CODE3OF9");
        this.ch.addItem("CODE3OF9CHK");
        (this.p2 = new Panel()).add(this.l2);
        this.p2.add(this.ch);
        this.p.add(this.p2);
        this.l3 = new Label("Choose a dimension:");
        this.cbg2 = new CheckboxGroup();
        this.cb3 = new Checkbox("small", this.cbg2, true);
        this.cb4 = new Checkbox("medium", this.cbg2, false);
        this.cb5 = new Checkbox("large", this.cbg2, false);
        (this.p3 = new Panel()).add(this.l3);
        this.p3.add(this.cb3);
        this.p3.add(this.cb4);
        this.p3.add(this.cb5);
        this.p.add(this.p3);
        this.l5 = new Label("Text inside:");
        this.cbg1 = new CheckboxGroup();
        this.cb1 = new Checkbox("Text Yes", this.cbg1, true);
        this.cb2 = new Checkbox("Text No", this.cbg1, false);
        (this.p5 = new Panel()).add(this.l5);
        this.p5.add(this.cb1);
        this.p5.add(this.cb2);
        this.p.add(this.p5);
        this.l6 = new Label("Select a font:");
        final String[] fontList = this.getToolkit().getFontList();
        for (int i = 0; i < fontList.length; ++i) {
            this.ch1.addItem(fontList[i]);
        }
        (this.p6 = new Panel()).add(this.l6);
        this.p6.add(this.ch1);
        this.p.add(this.p6);
        this.l7 = new Label("Font size:");
        this.cbg4 = new CheckboxGroup();
        this.cb9 = new Checkbox("8", this.cbg4, true);
        this.cb10 = new Checkbox("10", this.cbg4, false);
        this.cb11 = new Checkbox("12", this.cbg4, false);
        this.cb12 = new Checkbox("14", this.cbg4, false);
        this.cb13 = new Checkbox("16", this.cbg4, false);
        (this.p7 = new Panel()).add(this.l7);
        this.p7.add(this.cb9);
        this.p7.add(this.cb10);
        this.p7.add(this.cb11);
        this.p7.add(this.cb12);
        this.p7.add(this.cb13);
        this.p.add(this.p7);
        this.l8 = new Label("Text alignment:");
        this.cbg5 = new CheckboxGroup();
        this.cb14 = new Checkbox("Baseline", this.cbg5, false);
        this.cb15 = new Checkbox("Middleline", this.cbg5, false);
        this.cb16 = new Checkbox("Topline", this.cbg5, true);
        (this.p8 = new Panel()).add(this.l8);
        this.p8.add(this.cb14);
        this.p8.add(this.cb15);
        this.p8.add(this.cb16);
        this.p.add(this.p8);
        this.add("Center", this.p);
        this.p0 = new Panel();
        (this.bc = new BarCode3of9()).setDimension(1);
        this.bc.setTextInside(true);
        this.bc.setStyle(0);
        this.bc.setForeground(Color.black);
        this.bc.setFont(this.getFont());
        this.bc.setTextAlign(2);
        this.bc.resize(this.bc.requestedMinimunSize("12345678"));
        try {
            this.bc.setString("HELLO");
        }
        catch (Exception ex) {}
        this.p0.add((Component)this.bc);
        this.add("East", this.p0);
    }
}
