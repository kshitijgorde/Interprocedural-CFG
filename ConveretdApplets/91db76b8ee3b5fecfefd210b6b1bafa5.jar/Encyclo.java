import java.awt.event.ItemEvent;
import java.awt.Scrollbar;
import java.awt.Label;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Encyclo extends Applet implements AdjustmentListener, ActionListener, ItemListener
{
    private int max;
    private int It;
    Wave[] Wx;
    Wave[] Wy;
    Slide[] Sx;
    Slide[] Sy;
    Font font;
    EncycloCanvas canvas;
    Panel xPanel;
    Panel yPanel;
    Panel zPanel;
    Panel wPanel;
    String audioScroll;
    String audioButton;
    Button randButton;
    Button infoButton;
    Checkbox checkSpiro;
    Checkbox checkDecay;
    Checkbox checkColor;
    Checkbox checkThick;
    boolean spiro;
    boolean decay;
    boolean thick;
    boolean color;
    
    public Encyclo() {
        this.max = 8;
        this.It = 32;
        this.Wx = new Wave[2];
        this.Wy = new Wave[2];
        this.Sx = new Slide[2];
        this.Sy = new Slide[2];
        this.audioScroll = "drip.au";
        this.audioButton = "clink.au";
        this.spiro = false;
        this.decay = true;
        this.thick = false;
        this.color = false;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.infoButton) {
            try {
                this.getAppletContext().showDocument(new URL("http://EncycloZine.com/Graph/Encyclo/"));
            }
            catch (MalformedURLException ex) {
                System.err.println(ex);
            }
        }
        else if (source == this.randButton) {
            for (int i = 0; i < 2; ++i) {
                this.Wx[i].A = (int)(Math.random() * this.max + 1.0);
                this.Wx[i].F = (int)(Math.random() * this.max + 1.0);
                this.Wx[i].P = (int)(Math.random() * 360.0 + 1.0);
                if (this.spiro) {
                    this.Wy[i].A = this.Wx[i].A;
                    this.Wy[i].F = this.Wx[i].F;
                    this.Wx[0].P = 90;
                    this.Wx[1].P = 270;
                    this.Wy[0].P = 0;
                    this.Wy[1].P = 0;
                }
                else {
                    this.Wy[i].A = (int)(Math.random() * this.max + 0.5);
                    this.Wy[i].F = (int)(Math.random() * this.max + 0.5);
                    this.Wy[i].P = (int)(Math.random() * 360.0 + 0.5);
                }
                this.Sx[i].Al.setText(String.valueOf(this.Sx[i].At) + Integer.toString(this.Wx[i].A));
                this.Sx[i].Fl.setText(String.valueOf(this.Sx[i].Ft) + Integer.toString(this.Wx[i].F));
                this.Sx[i].Pl.setText(String.valueOf(this.Sx[i].Pt) + Integer.toString(this.Wx[i].P));
                this.Sx[i].SA.setValue(this.Wx[i].A);
                this.Sx[i].SF.setValue(this.Wx[i].F);
                this.Sx[i].SP.setValue(this.Wx[i].P);
                this.Sy[i].Al.setText(String.valueOf(this.Sy[i].At) + Integer.toString(this.Wy[i].A));
                this.Sy[i].Fl.setText(String.valueOf(this.Sy[i].Ft) + Integer.toString(this.Wy[i].F));
                this.Sy[i].Pl.setText(String.valueOf(this.Sy[i].Pt) + Integer.toString(this.Wy[i].P));
                this.Sy[i].SA.setValue(this.Wy[i].A);
                this.Sy[i].SF.setValue(this.Wy[i].F);
                this.Sy[i].SP.setValue(this.Wy[i].P);
            }
        }
        this.play(this.getCodeBase(), this.audioButton);
        this.canvas.setParams(this.Wx, this.Wy, this.decay, this.color, this.thick);
        this.canvas.repaint();
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final Object source = adjustmentEvent.getSource();
        for (int i = 0; i < 2; ++i) {
            if (source == this.Sx[i].SA) {
                final int value = this.Sx[i].SA.getValue();
                this.Sx[i].Al.setText(String.valueOf(this.Sx[i].At) + Integer.toString(value));
                this.Wx[i].A = value;
                if (this.spiro) {
                    this.Sy[i].SA.setValue(value);
                    this.Sy[i].Al.setText(String.valueOf(this.Sy[i].At) + Integer.toString(value));
                    this.Wy[i].A = value;
                }
            }
            else if (source == this.Sx[i].SF) {
                final int value2 = this.Sx[i].SF.getValue();
                this.Sx[i].Fl.setText(String.valueOf(this.Sx[i].Ft) + Integer.toString(value2));
                this.Wx[i].F = value2;
                if (this.spiro) {
                    this.Sy[i].SF.setValue(value2);
                    this.Sy[i].Fl.setText(String.valueOf(this.Sy[i].Ft) + Integer.toString(value2));
                    this.Wy[i].F = value2;
                }
            }
            else if (source == this.Sx[i].SP) {
                final int value3 = this.Sx[i].SP.getValue();
                this.Sx[i].Pl.setText(String.valueOf(this.Sx[i].Pt) + Integer.toString(value3));
                this.Wx[i].P = value3;
            }
            else if (source == this.Sy[i].SA) {
                final int value4 = this.Sy[i].SA.getValue();
                this.Sy[i].Al.setText(String.valueOf(this.Sy[i].At) + Integer.toString(value4));
                this.Wy[i].A = value4;
                if (this.spiro) {
                    this.Sx[i].SA.setValue(value4);
                    this.Sx[i].Al.setText(String.valueOf(this.Sx[i].At) + Integer.toString(value4));
                    this.Wx[i].A = value4;
                }
            }
            else if (source == this.Sy[i].SF) {
                final int value5 = this.Sy[i].SF.getValue();
                this.Sy[i].Fl.setText(String.valueOf(this.Sy[i].Ft) + Integer.toString(value5));
                this.Wy[i].F = value5;
                if (this.spiro) {
                    this.Sx[i].SF.setValue(value5);
                    this.Sx[i].Fl.setText(String.valueOf(this.Sx[i].Ft) + Integer.toString(value5));
                    this.Wx[i].F = value5;
                }
            }
            else if (source == this.Sy[i].SP) {
                final int value6 = this.Sy[i].SP.getValue();
                this.Sy[i].Pl.setText(String.valueOf(this.Sy[i].Pt) + Integer.toString(value6));
                this.Wy[i].P = value6;
            }
        }
        this.canvas.setParams(this.Wx, this.Wy, this.decay, this.color, this.thick);
        this.canvas.repaint();
        this.play(this.getDocumentBase(), this.audioScroll);
    }
    
    public void init() {
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.setLayout(new BorderLayout());
        this.canvas = new EncycloCanvas();
        (this.wPanel = new Panel()).setLayout(new GridLayout(1, 1));
        this.add("North", this.wPanel);
        this.wPanel.add(new Label("Encyclogram 1.0, from EncycloZine.com", 1));
        (this.xPanel = new Panel()).setLayout(new GridLayout(12, 1));
        this.add("West", this.xPanel);
        (this.yPanel = new Panel()).setLayout(new GridLayout(12, 1));
        this.add("East", this.yPanel);
        (this.zPanel = new Panel()).setLayout(new GridLayout(1, 6));
        this.add("South", this.zPanel);
        for (int i = 0; i < 2; ++i) {
            this.Wx[i] = new Wave();
            this.Wy[i] = new Wave();
            this.Sx[i] = new Slide();
            this.Sy[i] = new Slide();
            this.Wx[i].A = 7;
            this.Wy[i].A = 7;
        }
        this.Wx[0].F = 1;
        this.Wx[0].P = 90;
        this.Wx[1].F = 6;
        this.Wx[1].P = 270;
        this.Wy[0].F = 1;
        this.Wy[0].P = 0;
        this.Wy[1].F = 6;
        this.Wy[1].P = 0;
        for (int j = 0; j < 2; ++j) {
            this.Sx[j].At = "x" + Integer.toString(j + 1) + " Amplitude: ";
            this.Sx[j].Ft = "x" + Integer.toString(j + 1) + " Frequency: ";
            this.Sx[j].Pt = "x" + Integer.toString(j + 1) + " Phase    : ";
            this.Sy[j].At = "y" + Integer.toString(j + 1) + " Amplitude: ";
            this.Sy[j].Ft = "y" + Integer.toString(j + 1) + " Frequency: ";
            this.Sy[j].Pt = "y" + Integer.toString(j + 1) + " Phase    : ";
            this.Sx[j].Al = new Label(String.valueOf(this.Sx[j].At) + Integer.toString(this.Wx[j].A));
            this.xPanel.add(this.Sx[j].Al);
            (this.Sx[j].SA = new Scrollbar(0, this.Wx[j].A, 1, 0, this.max)).addAdjustmentListener(this);
            this.xPanel.add(this.Sx[j].SA);
            this.Sx[j].Fl = new Label(String.valueOf(this.Sx[j].Ft) + Integer.toString(this.Wx[j].F));
            this.xPanel.add(this.Sx[j].Fl);
            (this.Sx[j].SF = new Scrollbar(0, this.Wx[j].F, 1, 1, this.max)).addAdjustmentListener(this);
            this.xPanel.add(this.Sx[j].SF);
            this.Sx[j].Pl = new Label(String.valueOf(this.Sx[j].Pt) + Integer.toString(this.Wx[j].P));
            this.xPanel.add(this.Sx[j].Pl);
            (this.Sx[j].SP = new Scrollbar(0, this.Wx[j].P, 10, 0, 360)).setUnitIncrement(10);
            this.Sx[j].SP.setBlockIncrement(10);
            this.Sx[j].SP.addAdjustmentListener(this);
            this.xPanel.add(this.Sx[j].SP);
            this.Sy[j].Al = new Label(String.valueOf(this.Sy[j].At) + Integer.toString(this.Wy[j].A));
            this.yPanel.add(this.Sy[j].Al);
            (this.Sy[j].SA = new Scrollbar(0, this.Wy[j].A, 1, 0, this.max)).addAdjustmentListener(this);
            this.yPanel.add(this.Sy[j].SA);
            this.Sy[j].Fl = new Label(String.valueOf(this.Sy[j].Ft) + Integer.toString(this.Wy[j].F));
            this.yPanel.add(this.Sy[j].Fl);
            (this.Sy[j].SF = new Scrollbar(0, this.Wy[j].F, 1, 1, this.max)).addAdjustmentListener(this);
            this.yPanel.add(this.Sy[j].SF);
            this.Sy[j].Pl = new Label(String.valueOf(this.Sy[j].Pt) + Integer.toString(this.Wy[j].P));
            this.yPanel.add(this.Sy[j].Pl);
            (this.Sy[j].SP = new Scrollbar(0, this.Wy[j].P, 10, 0, 360)).setUnitIncrement(10);
            this.Sy[j].SP.setBlockIncrement(10);
            this.Sy[j].SP.addAdjustmentListener(this);
            this.yPanel.add(this.Sy[j].SP);
        }
        (this.randButton = new Button("Random")).addActionListener(this);
        this.zPanel.add(this.randButton);
        (this.checkSpiro = new Checkbox("Spiro", false)).addItemListener(this);
        this.zPanel.add(this.checkSpiro);
        (this.checkDecay = new Checkbox("Decay", true)).addItemListener(this);
        this.zPanel.add(this.checkDecay);
        (this.checkColor = new Checkbox("Color", false)).addItemListener(this);
        this.zPanel.add(this.checkColor);
        (this.checkThick = new Checkbox("Thick", false)).addItemListener(this);
        this.zPanel.add(this.checkThick);
        (this.infoButton = new Button("Info")).addActionListener(this);
        this.zPanel.add(this.infoButton);
        this.add(this.canvas, "Center");
        this.canvas.setParams(this.Wx, this.Wy, this.decay, this.color, this.thick);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.checkDecay) {
            this.decay = this.checkDecay.getState();
        }
        else if (itemEvent.getItemSelectable() == this.checkColor) {
            this.color = this.checkColor.getState();
        }
        else if (itemEvent.getItemSelectable() == this.checkThick) {
            this.thick = this.checkThick.getState();
        }
        else if (itemEvent.getItemSelectable() == this.checkSpiro) {
            this.spiro = this.checkSpiro.getState();
            if (this.spiro) {
                this.Sx[0].SP.setValue(90);
                this.Sx[1].SP.setValue(270);
                this.Sy[0].SP.setValue(0);
                this.Sy[1].SP.setValue(0);
                for (int i = 0; i < 2; ++i) {
                    final int value = this.Sx[i].SA.getValue();
                    this.Sy[i].SA.setValue(value);
                    this.Sy[i].Al.setText(String.valueOf(this.Sy[i].At) + Integer.toString(value));
                    this.Wy[i].A = value;
                    final int value2 = this.Sx[i].SF.getValue();
                    this.Sy[i].SF.setValue(value2);
                    this.Sy[i].Fl.setText(String.valueOf(this.Sy[i].Ft) + Integer.toString(value2));
                    this.Wy[i].F = value2;
                    final int value3 = this.Sx[i].SP.getValue();
                    this.Sx[i].Pl.setText(String.valueOf(this.Sx[i].Pt) + Integer.toString(value3));
                    this.Wx[i].P = value3;
                    final int value4 = this.Sy[i].SP.getValue();
                    this.Sy[i].Pl.setText(String.valueOf(this.Sy[i].Pt) + Integer.toString(value4));
                    this.Wy[i].P = value4;
                }
            }
        }
        this.canvas.setParams(this.Wx, this.Wy, this.decay, this.color, this.thick);
        this.canvas.repaint();
        this.play(this.getDocumentBase(), this.audioScroll);
    }
}
