// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.awt.Color;
import java.awt.Event;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Label;
import java.awt.Frame;

public class CalculatorWindow extends Frame
{
    public boolean bShow;
    public Label tfTitle;
    public Label tfSubTitle;
    public Button bButtonOk;
    public CalculatorApplet CA;
    String sTitle;
    private boolean bError;
    
    public CalculatorWindow(final String s, final Panel panel, final CalculatorApplet calculatorApplet) {
        this(s, panel, calculatorApplet, 550, 400);
    }
    
    public CalculatorWindow(final String s, final Panel panel, final CalculatorApplet calculatorApplet, final int n, final int n2) {
        this(s, "", panel, calculatorApplet, n, n2);
    }
    
    public CalculatorWindow(final String sTitle, String text, final Panel panel, final CalculatorApplet ca, final int n, final int n2) {
        this.bShow = false;
        this.tfTitle = new Label("", 1);
        this.tfSubTitle = new Label("", 1);
        this.bButtonOk = new Button("   OK   ");
        this.sTitle = "";
        this.bError = false;
        this.CA = ca;
        this.tfTitle.setFont(this.CA.getTitleFont());
        this.sTitle = sTitle;
        this.setTitle();
        this.tfSubTitle.setBackground(this.CA.getBackground());
        this.tfSubTitle.setFont(this.CA.getBoldFont());
        this.tfSubTitle.setText(text);
        final Panel panel2 = new Panel();
        panel2.setBackground(this.CA.getBackground());
        final Panel panel3 = new Panel();
        panel3.setBackground(this.CA.getBackground());
        panel3.setLayout(new FlowLayout());
        panel3.add(this.bButtonOk);
        panel2.setLayout(new BorderLayout());
        if (text == null) {
            text = "";
        }
        if (!text.equals("")) {
            final Panel panel4 = new Panel();
            panel4.setLayout(new BorderLayout());
            panel4.setBackground(this.CA.getBackground());
            panel4.add("North", this.tfTitle);
            panel4.add("Center", this.tfSubTitle);
            panel2.add("North", panel4);
        }
        else {
            panel2.add("North", this.tfTitle);
        }
        panel2.add("Center", panel);
        panel2.add("South", panel3);
        this.add("Center", panel2);
        this.hide();
        this.resize(n, n2);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.bButtonOk) {
            this.CA.calculate();
            if (!this.bError) {
                this.hide();
            }
            this.deliverEvent(new Event(this, 1001, this));
            return true;
        }
        this.CA.calculate();
        return super.action(event, o);
    }
    
    public boolean getShown() {
        return this.bShow;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.hide();
            return true;
        }
        if (event.target == this.bButtonOk) {
            this.CA.calculate();
        }
        if (event.id == 401) {
            return this.CA.handleEvent(event);
        }
        return super.handleEvent(event);
    }
    
    public void hide() {
        if (this.getShown()) {
            super.hide();
            this.CA.calculate();
            this.bShow = false;
        }
    }
    
    public void setTitle() {
        this.tfTitle.setBackground(this.CA.getBackground());
        this.tfTitle.setText(this.sTitle);
    }
    
    public void setTitle(final String text) {
        this.tfTitle.setBackground(this.CA.getBackground());
        this.tfTitle.setText(text);
    }
    
    public void setTitle(final String text, final Color background) {
        this.tfTitle.setBackground(background);
        this.tfTitle.setText(text);
    }
    
    public void setTitle(final String text, final Color foreground, final Color background, final boolean bError) {
        this.tfTitle.setBackground(background);
        this.tfTitle.setForeground(foreground);
        this.tfTitle.setText(text);
        this.bError = bError;
    }
    
    public void show() {
        super.show();
        this.toFront();
        this.bShow = true;
    }
}
