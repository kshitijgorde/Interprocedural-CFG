// 
// Decompiled by Procyon v0.5.30
// 

package de.mmkh.tams;

import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.util.Random;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Scrollbar;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

public class YieldGUI extends Panel implements AdjustmentListener, ActionListener
{
    Scrollbar xsizeSB;
    Scrollbar ysizeSB;
    Scrollbar xoffsetSB;
    Scrollbar yoffsetSB;
    Scrollbar defectsSB;
    TextField xsizeTF;
    TextField ysizeTF;
    TextField xoffsetTF;
    TextField yoffsetTF;
    TextField defectsTF;
    TextField chipsTF;
    TextField badTF;
    TextField yieldTF;
    Button applyButton;
    Button randomButton;
    Random generator;
    YieldDemo wafer;
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final Object source = adjustmentEvent.getSource();
        if (source == this.xsizeSB) {
            this.xsizeTF.setText("" + this.xsizeSB.getValue());
        }
        if (source == this.ysizeSB) {
            this.ysizeTF.setText("" + this.ysizeSB.getValue());
        }
        if (source == this.defectsSB) {
            this.defectsTF.setText("" + this.defectsSB.getValue());
        }
    }
    
    public double parse(final TextField textField) {
        final String trim = textField.getText().trim();
        try {
            return Double.valueOf(trim);
        }
        catch (Exception ex) {
            return 10.0;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        System.out.println("-#- YieldGUI.actionPerformed: " + actionEvent);
        if (source == this.applyButton) {
            this.doApplyValues();
        }
        else if (source == this.randomButton) {
            final int value = 2 + (int)(this.generator.nextDouble() * 50.0);
            final int value2 = (int)(value * (0.7 + 0.6 * this.generator.nextDouble()));
            this.xsizeSB.setValue(value);
            this.ysizeSB.setValue(value2);
            this.defectsSB.setValue((int)(this.generator.nextDouble() * 100.0));
            this.xsizeTF.setText("" + this.xsizeSB.getValue());
            this.ysizeTF.setText("" + this.ysizeSB.getValue());
            this.defectsTF.setText("" + this.defectsSB.getValue());
            this.doApplyValues();
        }
        else {
            System.err.println("-E- Unknown event source: " + actionEvent);
        }
    }
    
    public void doApplyValues() {
        if (this.wafer == null) {
            return;
        }
        this.wafer.setXSize(this.parse(this.xsizeTF));
        this.wafer.setYSize(this.parse(this.ysizeTF));
        this.wafer.setXOffset(0.0);
        this.wafer.setYOffset(0.0);
        this.wafer.setNumberOfDefects((int)this.parse(this.defectsTF));
        this.wafer.makeChips();
        this.wafer.makeDefects();
        this.wafer.repaint();
        this.chipsTF.setText("" + this.wafer.countChips() + " chips");
        this.badTF.setText("" + this.wafer.countBadChips() + " bad");
        this.yieldTF.setText("yield is " + new Format("%6.3f").form(this.wafer.getYield()));
    }
    
    public void setWafer(final YieldDemo wafer) {
        this.wafer = wafer;
        this.doApplyValues();
    }
    
    public static void main(final String[] array) {
        final YieldGUI yieldGUI = new YieldGUI();
        final YieldDemo wafer = new YieldDemo();
        yieldGUI.setWafer(wafer);
        final Frame frame = new Frame("Yield GUI");
        frame.add("North", yieldGUI);
        frame.add("South", wafer);
        frame.pack();
        frame.show();
        frame.repaint();
    }
    
    public YieldGUI() {
        this.generator = new Random();
        this.setLayout(new GridLayout(5, 3));
        this.applyButton = new Button("Apply selected values");
        this.randomButton = new Button("Apply random values");
        this.xsizeSB = new Scrollbar(0, 10, 1, 3, 50);
        this.ysizeSB = new Scrollbar(0, 14, 1, 3, 50);
        this.xoffsetSB = new Scrollbar(0, 0, 1, 0, 100);
        this.yoffsetSB = new Scrollbar(0, 0, 1, 0, 100);
        this.defectsSB = new Scrollbar(0, 40, 1, 0, 100);
        this.xsizeTF = new TextField("" + this.xsizeSB.getValue(), 8);
        this.ysizeTF = new TextField("" + this.ysizeSB.getValue(), 8);
        this.xoffsetTF = new TextField("" + this.xoffsetSB.getValue(), 8);
        this.yoffsetTF = new TextField("" + this.yoffsetSB.getValue(), 8);
        this.defectsTF = new TextField("" + this.defectsSB.getValue(), 8);
        this.chipsTF = new TextField("", 8);
        this.badTF = new TextField("", 8);
        this.yieldTF = new TextField("", 8);
        this.add(this.xsizeSB);
        this.add(this.xsizeTF);
        this.add(new Label("chip width [3..50mm]"));
        this.add(this.ysizeSB);
        this.add(this.ysizeTF);
        this.add(new Label("chip height [3..50mm]"));
        this.add(this.defectsSB);
        this.add(this.defectsTF);
        this.add(new Label("defects per wafer [0..1000]"));
        this.add(this.applyButton);
        this.add(this.randomButton);
        this.add(new Label(""));
        this.add(this.chipsTF);
        this.add(this.badTF);
        this.add(this.yieldTF);
        this.applyButton.addActionListener(this);
        this.randomButton.addActionListener(this);
        this.xsizeSB.addAdjustmentListener(this);
        this.ysizeSB.addAdjustmentListener(this);
        this.xoffsetSB.addAdjustmentListener(this);
        this.yoffsetSB.addAdjustmentListener(this);
        this.defectsSB.addAdjustmentListener(this);
    }
}
