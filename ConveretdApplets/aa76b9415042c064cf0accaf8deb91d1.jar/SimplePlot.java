import java.awt.Event;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SimplePlot extends Applet
{
    TextField fromFld;
    TextField toFld;
    String MyText;
    Label label;
    Label toLabel;
    Label fromLabel;
    Label statusLabel;
    PlotCanvas canvas;
    public int i;
    Button redb;
    Button blueb;
    Button domainChangeb;
    Color color;
    Panel panel1;
    Panel panel2;
    Panel bottomPanel;
    Panel statusPanel;
    Panel xdomainPanel;
    Choice fnChoice;
    float beginXmin;
    float beginXmax;
    
    public void init() {
        this.setLayout(new BorderLayout(5, 5));
        this.panel1 = new Panel();
        this.label = new Label("Plotting a Function");
        this.panel1.add(this.label);
        this.add("North", this.panel1);
        (this.panel2 = new Panel()).setLayout(new GridLayout(0, 1, 5, 5));
        this.panel2.add(new Label("Choose a Color"));
        (this.redb = new Button("RED")).setBackground(Color.red);
        this.panel2.add(this.redb);
        (this.blueb = new Button("BLUE")).setBackground(Color.blue);
        this.panel2.add(this.blueb);
        this.panel2.add(new Label("Choose a Function"));
        (this.fnChoice = new Choice()).addItem("x^2");
        this.fnChoice.addItem("x + 30");
        this.fnChoice.addItem("x^3");
        this.fnChoice.addItem("x^2 + x - 52");
        this.panel2.add(this.fnChoice);
        this.add("East", this.panel2);
        this.add("Center", this.canvas = new PlotCanvas(this));
        this.fromLabel = new Label("-100");
        (this.bottomPanel = new Panel()).setLayout(new GridLayout(0, 1, 5, 5));
        (this.xdomainPanel = new Panel()).add(new Label("Plot x from : "));
        this.fromFld = new TextField("-100", 7);
        this.canvas.setXmin(-100.0f);
        this.xdomainPanel.add(this.fromFld);
        this.xdomainPanel.add(new Label("to : "));
        this.toFld = new TextField("100", 10);
        this.canvas.setXmax(100.0f);
        this.xdomainPanel.add(this.toFld);
        this.domainChangeb = new Button("Change Domain");
        this.xdomainPanel.add(this.domainChangeb);
        this.bottomPanel.add(this.xdomainPanel);
        this.statusPanel = new Panel();
        (this.statusLabel = new Label("                                           ")).setForeground(Color.magenta);
        this.statusPanel.add(this.statusLabel);
        this.bottomPanel.add(this.statusPanel);
        this.add("South", this.bottomPanel);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        graphics.drawRect(0, 0, size.width - 1, size.height - 1);
    }
    
    public Insets insets() {
        return new Insets(5, 5, 5, 8);
    }
    
    public boolean handleEvent(final Event event) {
        try {
            if (event.target instanceof Button && event.id == 1001) {
                if (event.target == this.redb) {
                    this.canvas.changeColor(Color.red);
                }
                else if (event.target == this.blueb) {
                    this.canvas.changeColor(Color.blue);
                }
                else if (event.target == this.domainChangeb) {
                    this.statusLabel.setText("                           ");
                    final float floatValue = Float.valueOf(this.fromFld.getText());
                    final float floatValue2 = Float.valueOf(this.toFld.getText());
                    if (floatValue2 <= floatValue) {
                        this.statusLabel.setText("From must be less than To");
                    }
                    else {
                        this.canvas.setXmin(floatValue);
                        this.canvas.setXmax(floatValue2);
                    }
                }
                this.canvas.repaint();
            }
            else if (event.target instanceof Choice && event.id == 1001) {
                this.canvas.setFn(this.fnChoice.getSelectedIndex());
                this.canvas.repaint();
            }
        }
        catch (NumberFormatException ex) {
            this.statusLabel.setText("Domain must be a number");
            System.out.println(ex);
        }
        return false;
    }
    
    public SimplePlot() {
        this.beginXmin = -100.0f;
        this.beginXmax = 100.0f;
    }
}
