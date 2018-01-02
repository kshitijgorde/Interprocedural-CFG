// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.coal;

import com.mindprod.common15.FontFactory;
import com.mindprod.common13.HybridJ;
import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;
import javax.swing.SpinnerModel;
import com.mindprod.common13.JEButton;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Color;
import javax.swing.JApplet;

public final class Coal extends JApplet
{
    private static final int APPLET_HEIGHT = 162;
    private static final int APPLET_WIDTH = 664;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2009-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2009-11-29";
    private static final String TITLE_STRING = "Coal Extinction Calculator";
    private static final String VERSION_STRING = "1.0";
    private static final Color BACKGROUND_FOR_APP;
    private static final Color BACKGROUND_FOR_EDITABLE;
    private static final Color BACKGROUND_FOR_INSTRUCTIONS;
    private static final Color BACKGROUND_FOR_RESULT;
    private static final Color FOREGROUND_FOR_APP;
    private static final Color FOREGROUND_FOR_ENTER;
    private static final Color FOREGROUND_FOR_INSTRUCTIONS;
    private static final Color FOREGROUND_FOR_RESULT;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final DecimalFormat PERCENT_FORMAT;
    private static final DecimalFormat YEARS_FORMAT;
    private static final Font FONT_FOR_EDITABLE_FIELDS;
    private static final Font FONT_FOR_INSTRUCTIONS;
    private static final Font FONT_FOR_RESULTS;
    private static final Font FONT_FOR_TITLE;
    private JButton about;
    private JLabel annualConsumptionGrowthPercentLabel;
    private JLabel title;
    private JSpinner annualConsumptionGrowthPercent;
    private JTextArea result;
    private SpinnerNumberModel annualConsumptionGrowthPercentNumberModel;
    
    public void destroy() {
        this.about = null;
        this.annualConsumptionGrowthPercent = null;
        this.annualConsumptionGrowthPercentLabel = null;
        this.annualConsumptionGrowthPercentNumberModel = null;
        this.result = null;
        this.title = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(Coal.BACKGROUND_FOR_APP);
        contentPane.setForeground(Coal.FOREGROUND_FOR_APP);
        contentPane.setLayout(new GridBagLayout());
        this.createComponents();
        this.layoutGridBag(contentPane);
        this.hookListeners();
        this.compute();
        this.validate();
        this.setVisible(true);
    }
    
    private void compute() {
        final double percent = this.annualConsumptionGrowthPercentNumberModel.getNumber().doubleValue();
        final double rate = percent / 100.0;
        final double reserve = 2.73E11;
        final double extraction = 1.1465116279069767E9;
        final double extinctionYears = 1.0 / rate * Math.log1p(rate * 2.73E11 / 1.1465116279069767E9);
        if (rate > 0.0) {
            this.result.setText("If coal consumption grows at " + Coal.PERCENT_FORMAT.format(percent) + "% per annum, coal in the USA will run out in " + Coal.YEARS_FORMAT.format(extinctionYears) + " years.");
        }
        else if (rate < 0.0) {
            if (Double.isNaN(extinctionYears)) {
                this.result.setText("If coal consumption shrinks at " + Coal.PERCENT_FORMAT.format(-percent) + "% per annum, coal in the USA would not run out.");
            }
            else {
                this.result.setText("If coal consumption shrinks at " + Coal.PERCENT_FORMAT.format(-percent) + "% per annum, coal in the USA will run out in " + Coal.YEARS_FORMAT.format(extinctionYears) + " years.");
            }
        }
        else {
            this.result.setText("If annual coal consumption does not change, the USA will run out of coal in " + Coal.YEARS_FORMAT.format(238.1135902636917) + " years.");
        }
    }
    
    private void createComponents() {
        (this.title = new JLabel("Coal Extinction Calculator 1.0")).setFont(Coal.FONT_FOR_TITLE);
        this.title.setForeground(Coal.FOREGROUND_FOR_TITLE);
        (this.about = new JEButton("About")).setToolTipText("About Coal Extinction Calculator 1.0");
        (this.annualConsumptionGrowthPercentLabel = new JLabel("Select assumed annual growth rate percentage in coal consumption.", 4)).setFont(Coal.FONT_FOR_INSTRUCTIONS);
        this.annualConsumptionGrowthPercentLabel.setBackground(Coal.BACKGROUND_FOR_INSTRUCTIONS);
        this.annualConsumptionGrowthPercentLabel.setForeground(Coal.FOREGROUND_FOR_INSTRUCTIONS);
        (this.annualConsumptionGrowthPercent = new JSpinner()).setFont(Coal.FONT_FOR_EDITABLE_FIELDS);
        this.annualConsumptionGrowthPercent.setBackground(Coal.BACKGROUND_FOR_EDITABLE);
        this.annualConsumptionGrowthPercent.setForeground(Coal.FOREGROUND_FOR_ENTER);
        this.annualConsumptionGrowthPercent.setValue(2.9);
        this.annualConsumptionGrowthPercentNumberModel = new SpinnerNumberModel(2.9, -0.5, 25.0, 0.1);
        this.annualConsumptionGrowthPercent.setModel(this.annualConsumptionGrowthPercentNumberModel);
        (this.result = new JTextArea(2, 40)).setEditable(false);
        this.result.setMargin(new Insets(4, 4, 4, 4));
        this.result.setLineWrap(true);
        this.result.setWrapStyleWord(true);
        this.result.setFont(Coal.FONT_FOR_RESULTS);
        this.result.setBackground(Coal.BACKGROUND_FOR_RESULT);
        this.result.setForeground(Coal.FOREGROUND_FOR_RESULT);
    }
    
    private void hookListeners() {
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("Coal Extinction Calculator", "1.0", "Calculates how long coal will last presuming", "different rates of growth in demand.", "freeware", "2009-11-29", 2009, "Roedy Green", "COAL", "1.5");
            }
        });
        this.annualConsumptionGrowthPercentNumberModel.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                Coal.this.compute();
            }
        });
    }
    
    private void layoutGridBag(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 0, 5), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 13, 0, new Insets(10, 5, 5, 10), 10, 2));
        contentPane.add(this.annualConsumptionGrowthPercentLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 13, 0, new Insets(0, 10, 5, 5), 0, 0));
        contentPane.add(this.annualConsumptionGrowthPercent, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 13, 0, new Insets(0, 5, 5, 10), 20, 0));
        contentPane.add(this.result, new GridBagConstraints(0, 3, 2, 1, 1.0, 1.0, 17, 1, new Insets(5, 10, 10, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new Coal(), "Coal Extinction Calculator 1.0", 664, 162);
    }
    
    static {
        BACKGROUND_FOR_APP = new Color(15658734);
        BACKGROUND_FOR_EDITABLE = Color.WHITE;
        BACKGROUND_FOR_INSTRUCTIONS = Color.WHITE;
        BACKGROUND_FOR_RESULT = Color.WHITE;
        FOREGROUND_FOR_APP = Color.BLACK;
        FOREGROUND_FOR_ENTER = Color.BLACK;
        FOREGROUND_FOR_INSTRUCTIONS = new Color(32768);
        FOREGROUND_FOR_RESULT = Color.BLACK;
        FOREGROUND_FOR_TITLE = new Color(14423100);
        PERCENT_FORMAT = new DecimalFormat("#0.0");
        YEARS_FORMAT = new DecimalFormat("###,##0.0");
        FONT_FOR_EDITABLE_FIELDS = FontFactory.build("Monospaced", 1, 15);
        FONT_FOR_INSTRUCTIONS = FontFactory.build("Dialog", 0, 15);
        FONT_FOR_RESULTS = FontFactory.build("Dialog", 0, 15);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
    }
}
