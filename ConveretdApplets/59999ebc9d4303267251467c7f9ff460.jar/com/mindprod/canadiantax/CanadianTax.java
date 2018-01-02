// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.canadiantax;

import com.mindprod.common15.FontFactory;
import com.mindprod.common13.HybridJ;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.SpinnerModel;
import com.mindprod.spinner.DollarNumberEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import com.mindprod.common13.JEButton;
import java.text.ParseException;
import com.mindprod.spinner.DollarNumberFormatter;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.mindprod.common13.Common13;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import com.mindprod.spinner.DateSpinner;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Color;
import com.mindprod.common11.BigDate;
import javax.swing.JApplet;

public final class CanadianTax extends JApplet
{
    private static final boolean DEBUGGING = false;
    private static final int APPLET_HEIGHT = 400;
    private static final int APPLET_WIDTH = 475;
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2011-03-25";
    private static final String TITLE_STRING = "Canadian Sales Tax Calculator";
    private static final String VERSION_STRING = "4.0";
    private static final BigDate maxDate;
    private static final BigDate minDate;
    private static final Color BACKGROUND_FOR_EDITABLE;
    private static final Color BACKGROUND_FOR_RESULT_MEDIUM;
    private static final Color BACKGROUND_FOR_RESULT_PALE;
    private static final Color DARK_BACKGROUND_FOR_RESULT;
    private static final Color FOREGROUND_FOR_ENTER;
    private static final Color FOREGROUND_FOR_EXCLUDED_FROM_PERCENT;
    private static final Color FOREGROUND_FOR_EXCLUDED_FROM_TOTAL;
    private static final Color FOREGROUND_FOR_INCLUDED_IN_TOTAL;
    private static final Color FOREGROUND_FOR_INCLUDED_PERCENT;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Color FOREGROUND_FOR_TOTAL;
    private static final DecimalFormat DOLLARFORMAT;
    private static final DecimalFormat PERCENTFORMAT3;
    private static final Font FONT_FOR_LABELS;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private static Province defaultBuyerProv;
    private final CalculateCanadianTaxes calc;
    private DateSpinner asOfDate;
    private JButton about;
    private JButton calcSaleAmountButton;
    private JButton calcTotalPayableButton;
    private JComboBox buyerProv;
    private JLabel asOfDateLabel;
    private JLabel buyerProvLabel;
    private JLabel gstLabel;
    private JLabel hstLabel;
    private JLabel pstLabel;
    private JLabel saleAmountLabel;
    private JLabel title;
    private JLabel title2;
    private JLabel totalPayableLabel;
    private JLabel totalTaxLabel;
    private JSpinner saleAmountSpinner;
    private JTextField gstAmountText;
    private JTextField gstRateText;
    private JTextField hstAmountText;
    private JTextField hstRateText;
    private JTextField pstAmountText;
    private JTextField pstRateText;
    private JTextField totalPayableAmountText;
    private JTextField totalTaxAmountText;
    private JTextField totalTaxRateText;
    private SpinnerNumberModel saleAmountSpinnerModel;
    
    public CanadianTax() {
        this.calc = new CalculateCanadianTaxes();
    }
    
    public void destroy() {
        this.about = null;
        this.asOfDate = null;
        this.asOfDateLabel = null;
        this.buyerProv = null;
        this.buyerProvLabel = null;
        this.calcSaleAmountButton = null;
        this.calcTotalPayableButton = null;
        this.gstAmountText = null;
        this.gstLabel = null;
        this.gstRateText = null;
        this.hstAmountText = null;
        this.hstLabel = null;
        this.hstRateText = null;
        this.pstAmountText = null;
        this.pstLabel = null;
        this.pstRateText = null;
        this.saleAmountLabel = null;
        this.saleAmountSpinner = null;
        this.saleAmountSpinnerModel = null;
        this.title = null;
        this.title2 = null;
        this.totalPayableAmountText = null;
        this.totalPayableLabel = null;
        this.totalTaxAmountText = null;
        this.totalTaxLabel = null;
        this.totalTaxRateText = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        Province.importTaxResource();
        if (CanadianTax.defaultBuyerProv == null) {
            validateDefaultProv(this.getParameter("prov"));
        }
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(CanadianTax.BACKGROUND_FOR_RESULT_PALE);
        contentPane.setLayout(new GridBagLayout());
        this.createComponents();
        this.calcAndDisplayTaxes();
        this.layoutGridBag(contentPane);
        this.hookListeners();
        this.validate();
        this.setVisible(true);
    }
    
    private static String toDollarString(final double amount, final boolean bz) {
        if (bz && amount == 0.0) {
            return "";
        }
        return CanadianTax.DOLLARFORMAT.format(amount);
    }
    
    private static String toPercentString(final double percent) {
        if (percent == 0.0) {
            return "";
        }
        return CanadianTax.PERCENTFORMAT3.format(percent);
    }
    
    private static void validateDefaultProv(final String defaultProv) {
        if (defaultProv != null && defaultProv.length() == 2) {
            String provAbbr2 = defaultProv.toUpperCase();
            if (provAbbr2.equals("PQ")) {
                provAbbr2 = "QC";
            }
            else if (provAbbr2.equals("YK")) {
                provAbbr2 = "YT";
            }
            else if (provAbbr2.equals("NF")) {
                provAbbr2 = "NL";
            }
            try {
                CanadianTax.defaultBuyerProv = Province.valueOf(provAbbr2);
                return;
            }
            catch (IllegalArgumentException ex) {}
        }
        System.err.println("Missing or invalid default province parameter. Assuming ON for Ontario.");
        CanadianTax.defaultBuyerProv = Province.ON;
    }
    
    private CanadianTax(final String defaultProv) {
        this.calc = new CalculateCanadianTaxes();
        validateDefaultProv(defaultProv);
    }
    
    private void calcAndDisplaySaleAmount() {
        final Province bp = (Province)this.buyerProv.getSelectedItem();
        this.pstLabel.setText((bp == Province.QC) ? "TVQ" : "PST");
        final String totalPayableString = this.totalPayableAmountText.getText();
        double totalPayable;
        try {
            totalPayable = (double)new DollarNumberFormatter().stringToValue(totalPayableString);
        }
        catch (ParseException event) {
            totalPayable = 0.0;
        }
        final BigDate date = this.asOfDate.getValue();
        final double saleAmount = this.calc.calculateSaleAmount(bp, date, totalPayable);
        this.saleAmountSpinner.setValue(saleAmount);
    }
    
    private void calcAndDisplayTaxes() {
        final Province bp = (Province)this.buyerProv.getSelectedItem();
        final BigDate date = this.asOfDate.getValue();
        this.pstLabel.setText((bp == Province.QC) ? "TVQ" : "PST");
        final double saleAmount = this.saleAmountSpinnerModel.getNumber().doubleValue();
        this.calc.calculateCanadianTaxes(bp, date, saleAmount);
        final double gstRate = this.calc.getGstRate();
        final double hstRate = this.calc.getHstRate();
        final double pstRate = this.calc.getPstRate();
        final double getTotalTaxRate = this.calc.getTotalTaxRate();
        this.gstRateText.setText(toPercentString(gstRate));
        this.hstRateText.setText(toPercentString(hstRate));
        this.pstRateText.setText(toPercentString(pstRate));
        this.totalTaxRateText.setText(toPercentString(getTotalTaxRate));
        final double gstAmount = this.calc.getGstAmount();
        final double hstAmount = this.calc.getHstAmount();
        final double pstAmount = this.calc.getPstAmount();
        final double totalTaxAmount = this.calc.getTotalTaxAmount();
        final double totalPayableAmount = this.calc.getTotalPayableAmount();
        if (hstAmount > 0.0) {
            this.gstAmountText.setForeground(CanadianTax.FOREGROUND_FOR_EXCLUDED_FROM_TOTAL);
            this.hstAmountText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_IN_TOTAL);
            this.pstAmountText.setForeground(CanadianTax.FOREGROUND_FOR_EXCLUDED_FROM_TOTAL);
            this.gstRateText.setForeground(CanadianTax.FOREGROUND_FOR_EXCLUDED_FROM_PERCENT);
            this.hstRateText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_PERCENT);
            this.pstRateText.setForeground(CanadianTax.FOREGROUND_FOR_EXCLUDED_FROM_PERCENT);
        }
        else {
            this.gstAmountText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_IN_TOTAL);
            this.hstAmountText.setForeground(CanadianTax.FOREGROUND_FOR_EXCLUDED_FROM_TOTAL);
            this.pstAmountText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_IN_TOTAL);
            this.gstRateText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_PERCENT);
            this.hstRateText.setForeground(CanadianTax.FOREGROUND_FOR_EXCLUDED_FROM_PERCENT);
            this.pstRateText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_PERCENT);
        }
        this.gstAmountText.setText(toDollarString(gstAmount, true));
        this.hstAmountText.setText(toDollarString(hstAmount, true));
        this.pstAmountText.setText(toDollarString(pstAmount, true));
        this.totalTaxAmountText.setText(toDollarString(totalTaxAmount, false));
        this.totalPayableAmountText.setText(toDollarString(totalPayableAmount, false));
    }
    
    private void createComponents() {
        (this.title = new JLabel("Canadian Sales Tax Calculator 4.0")).setFont(CanadianTax.FONT_FOR_TITLE);
        this.title.setForeground(CanadianTax.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2011-03-25 build:9411")).setFont(CanadianTax.FONT_FOR_TITLE2);
        this.title2.setForeground(CanadianTax.FOREGROUND_FOR_TITLE);
        (this.about = new JEButton("About")).setToolTipText("About Canadian Sales Tax Calculator 4.0");
        (this.asOfDateLabel = new JLabel("As-of Date", 4)).setFont(CanadianTax.FONT_FOR_LABELS);
        this.asOfDateLabel.setForeground(CanadianTax.FOREGROUND_FOR_LABEL);
        (this.asOfDate = new DateSpinner()).setMinimum(CanadianTax.minDate);
        this.asOfDate.setMaximum(CanadianTax.maxDate);
        (this.buyerProv = new JComboBox()).setEditable(false);
        this.buyerProv.setModel(new DefaultComboBoxModel<Province>(Province.values()));
        this.buyerProv.setSelectedItem(CanadianTax.defaultBuyerProv);
        (this.buyerProvLabel = new JLabel("Buyer's Province", 4)).setFont(CanadianTax.FONT_FOR_LABELS);
        this.buyerProvLabel.setForeground(CanadianTax.FOREGROUND_FOR_LABEL);
        (this.saleAmountSpinner = new JSpinner()).setFont(CanadianTax.FONT_FOR_LABELS);
        this.saleAmountSpinner.setBackground(CanadianTax.BACKGROUND_FOR_EDITABLE);
        this.saleAmountSpinner.setForeground(CanadianTax.FOREGROUND_FOR_ENTER);
        this.saleAmountSpinner.setValue(100.0);
        this.saleAmountSpinnerModel = new SpinnerNumberModel(100.0, 0.0, 999999.99, 0.01);
        final JSpinner.NumberEditor saleAmountNumberEditor = new DollarNumberEditor(this.saleAmountSpinner);
        this.saleAmountSpinner.setModel(this.saleAmountSpinnerModel);
        this.saleAmountSpinner.setEditor(saleAmountNumberEditor);
        final JFormattedTextField inner = saleAmountNumberEditor.getTextField();
        inner.setMargin(new Insets(0, 2, 0, 2));
        (this.saleAmountLabel = new JLabel("Amount of Sale", 0)).setFont(CanadianTax.FONT_FOR_LABELS);
        this.saleAmountLabel.setForeground(CanadianTax.FOREGROUND_FOR_LABEL);
        final boolean usingMac = System.getProperty("os.name").equals("Mac OS X");
        final char downArrow = usingMac ? '\u2193' : '\u21d3';
        final char upArrow = usingMac ? '\u2191' : '\u21d1';
        (this.calcTotalPayableButton = new JEButton("Calc " + downArrow)).setToolTipText("Calculate sales tax");
        (this.calcSaleAmountButton = new JEButton("Calc " + upArrow)).setToolTipText("Reverse calculate sale amount");
        (this.gstAmountText = new JTextField(16)).setEditable(false);
        this.gstAmountText.setFont(CanadianTax.FONT_FOR_LABELS);
        this.gstAmountText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_IN_TOTAL);
        this.gstAmountText.setBackground(CanadianTax.BACKGROUND_FOR_RESULT_PALE);
        this.gstAmountText.setHorizontalAlignment(4);
        this.gstAmountText.setMargin(new Insets(2, 2, 2, 2));
        (this.gstRateText = new JTextField(6)).setEditable(false);
        this.gstRateText.setFont(CanadianTax.FONT_FOR_LABELS);
        this.gstRateText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_PERCENT);
        this.gstRateText.setHorizontalAlignment(4);
        this.gstRateText.setMargin(new Insets(2, 2, 2, 2));
        (this.gstLabel = new JLabel("GST/TPS", 4)).setFont(CanadianTax.FONT_FOR_LABELS);
        this.gstLabel.setForeground(CanadianTax.FOREGROUND_FOR_LABEL);
        (this.hstAmountText = new JTextField(16)).setEditable(false);
        this.hstAmountText.setFont(CanadianTax.FONT_FOR_LABELS);
        this.hstAmountText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_IN_TOTAL);
        this.hstAmountText.setBackground(CanadianTax.BACKGROUND_FOR_RESULT_PALE);
        this.hstAmountText.setHorizontalAlignment(4);
        this.hstAmountText.setMargin(new Insets(2, 2, 2, 2));
        (this.hstRateText = new JTextField(6)).setEditable(false);
        this.hstRateText.setFont(CanadianTax.FONT_FOR_LABELS);
        this.hstRateText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_PERCENT);
        this.hstRateText.setHorizontalAlignment(4);
        this.hstRateText.setMargin(new Insets(2, 2, 2, 2));
        (this.hstLabel = new JLabel("HST", 4)).setFont(CanadianTax.FONT_FOR_LABELS);
        this.hstLabel.setForeground(CanadianTax.FOREGROUND_FOR_LABEL);
        (this.pstAmountText = new JTextField(16)).setEditable(false);
        this.pstAmountText.setFont(CanadianTax.FONT_FOR_LABELS);
        this.pstAmountText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_IN_TOTAL);
        this.pstAmountText.setBackground(CanadianTax.BACKGROUND_FOR_RESULT_PALE);
        this.pstAmountText.setHorizontalAlignment(4);
        this.pstAmountText.setMargin(new Insets(2, 2, 2, 2));
        (this.pstRateText = new JTextField(6)).setEditable(false);
        this.pstRateText.setFont(CanadianTax.FONT_FOR_LABELS);
        this.pstRateText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_PERCENT);
        this.pstRateText.setHorizontalAlignment(4);
        this.pstRateText.setMargin(new Insets(2, 2, 2, 2));
        (this.pstLabel = new JLabel("PST", 4)).setFont(CanadianTax.FONT_FOR_LABELS);
        this.pstLabel.setForeground(CanadianTax.FOREGROUND_FOR_LABEL);
        (this.totalTaxAmountText = new JTextField(16)).setEditable(false);
        this.totalTaxAmountText.setFont(CanadianTax.FONT_FOR_LABELS);
        this.totalTaxAmountText.setBackground(CanadianTax.BACKGROUND_FOR_RESULT_MEDIUM);
        this.totalTaxAmountText.setForeground(CanadianTax.FOREGROUND_FOR_TOTAL);
        this.totalTaxAmountText.setHorizontalAlignment(4);
        this.totalTaxAmountText.setMargin(new Insets(2, 2, 2, 2));
        (this.totalTaxRateText = new JTextField(6)).setEditable(false);
        this.totalTaxRateText.setFont(CanadianTax.FONT_FOR_LABELS);
        this.totalTaxRateText.setForeground(CanadianTax.FOREGROUND_FOR_INCLUDED_PERCENT);
        this.totalTaxRateText.setHorizontalAlignment(4);
        this.totalTaxRateText.setMargin(new Insets(2, 2, 2, 2));
        (this.totalTaxLabel = new JLabel("Total Tax", 4)).setFont(CanadianTax.FONT_FOR_LABELS);
        this.totalTaxLabel.setForeground(CanadianTax.FOREGROUND_FOR_LABEL);
        (this.totalPayableAmountText = new JTextField(16)).setEditable(true);
        this.totalPayableAmountText.setFont(CanadianTax.FONT_FOR_LABELS);
        this.totalPayableAmountText.setBackground(CanadianTax.DARK_BACKGROUND_FOR_RESULT);
        this.totalPayableAmountText.setForeground(CanadianTax.FOREGROUND_FOR_TOTAL);
        this.totalPayableAmountText.setHorizontalAlignment(4);
        this.totalPayableAmountText.setMargin(new Insets(2, 2, 2, 2));
        (this.totalPayableLabel = new JLabel("Total Payable", 4)).setFont(CanadianTax.FONT_FOR_LABELS);
        this.totalPayableLabel.setForeground(CanadianTax.FOREGROUND_FOR_LABEL);
    }
    
    private void hookListeners() {
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("Canadian Sales Tax Calculator", "4.0", "Calculates Canadian GST/HST/PST sales taxes", "given the buyer's province.", "freeware", "2011-03-25", 1999, "Roedy Green", "CANADIANTAX", "1.5");
            }
        });
        this.asOfDate.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                CanadianTax.this.calcAndDisplayTaxes();
            }
        });
        this.calcTotalPayableButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                CanadianTax.this.calcAndDisplayTaxes();
            }
        });
        this.calcSaleAmountButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                CanadianTax.this.calcAndDisplaySaleAmount();
                CanadianTax.this.calcAndDisplayTaxes();
            }
        });
        this.buyerProv.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                CanadianTax.this.calcAndDisplayTaxes();
            }
        });
        this.saleAmountSpinnerModel.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                CanadianTax.this.calcAndDisplayTaxes();
            }
        });
    }
    
    private void layoutGridBag(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0, 17, 0, new Insets(10, 10, 0, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, 17, 0, new Insets(0, 10, 0, 5), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(3, 0, 1, 2, 0.0, 0.0, 13, 0, new Insets(10, 5, 0, 10), 10, 2));
        contentPane.add(this.asOfDateLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 14, 0, new Insets(10, 10, 5, 5), 0, 0));
        contentPane.add(this.asOfDate, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, 17, 0, new Insets(10, 5, 5, 5), 0, 0));
        contentPane.add(this.buyerProvLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 14, 0, new Insets(5, 10, 0, 5), 0, 0));
        contentPane.add(this.buyerProv, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0, 17, 2, new Insets(5, 5, 0, 5), 0, 0));
        contentPane.add(this.saleAmountLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, 14, 0, new Insets(5, 10, 0, 5), 0, 0));
        contentPane.add(this.saleAmountSpinner, new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0, 15, 2, new Insets(5, 5, 0, 5), 0, 0));
        contentPane.add(this.calcTotalPayableButton, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, 15, 0, new Insets(5, 5, 0, 10), 0, 0));
        contentPane.add(this.gstLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, 14, 0, new Insets(5, 10, 0, 5), 0, 0));
        contentPane.add(this.gstRateText, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, 14, 2, new Insets(5, 5, 0, 5), 0, 0));
        contentPane.add(this.gstAmountText, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, 15, 2, new Insets(5, 5, 0, 25), 0, 0));
        contentPane.add(this.hstLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, 14, 0, new Insets(5, 10, 0, 5), 0, 0));
        contentPane.add(this.hstRateText, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, 14, 2, new Insets(5, 5, 0, 5), 0, 0));
        contentPane.add(this.hstAmountText, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, 15, 2, new Insets(5, 5, 0, 25), 0, 0));
        contentPane.add(this.pstLabel, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, 14, 0, new Insets(5, 10, 0, 5), 0, 0));
        contentPane.add(this.pstRateText, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, 14, 2, new Insets(5, 5, 0, 5), 0, 0));
        contentPane.add(this.pstAmountText, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0, 15, 2, new Insets(5, 5, 0, 25), 0, 0));
        contentPane.add(this.totalTaxLabel, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, 14, 0, new Insets(5, 10, 0, 5), 0, 0));
        contentPane.add(this.totalTaxRateText, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0, 14, 2, new Insets(5, 5, 0, 5), 65, 0));
        contentPane.add(this.totalTaxAmountText, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0, 15, 2, new Insets(5, 5, 0, 25), 120, 0));
        contentPane.add(this.totalPayableLabel, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, 14, 0, new Insets(5, 10, 0, 5), 0, 0));
        contentPane.add(this.totalPayableAmountText, new GridBagConstraints(1, 9, 2, 1, 0.0, 0.0, 15, 2, new Insets(5, 5, 0, 25), 0, 0));
        contentPane.add(this.calcSaleAmountButton, new GridBagConstraints(3, 9, 1, 1, 0.0, 0.0, 14, 0, new Insets(5, 5, 0, 10), 0, 0));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new CanadianTax((args.length == 1) ? args[0] : null), "Canadian Sales Tax Calculator 4.0", 475, 400);
    }
    
    static {
        maxDate = new BigDate("2012-01-01");
        minDate = new BigDate("1991-01-01");
        BACKGROUND_FOR_EDITABLE = Color.WHITE;
        BACKGROUND_FOR_RESULT_MEDIUM = new Color(11534308);
        BACKGROUND_FOR_RESULT_PALE = new Color(16187382);
        DARK_BACKGROUND_FOR_RESULT = new Color(10485716);
        FOREGROUND_FOR_ENTER = Color.BLACK;
        FOREGROUND_FOR_EXCLUDED_FROM_PERCENT = new Color(196852667);
        FOREGROUND_FOR_EXCLUDED_FROM_TOTAL = new Color(196852667);
        FOREGROUND_FOR_INCLUDED_IN_TOTAL = Color.BLACK;
        FOREGROUND_FOR_INCLUDED_PERCENT = new Color(32768);
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        FOREGROUND_FOR_TOTAL = Color.BLACK;
        DOLLARFORMAT = new DecimalFormat("'$'###,###,###,###,##0.00");
        PERCENTFORMAT3 = new DecimalFormat("##0.###'%'");
        FONT_FOR_LABELS = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
}
