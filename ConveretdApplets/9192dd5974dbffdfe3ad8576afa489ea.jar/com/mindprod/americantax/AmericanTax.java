// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.americantax;

import javax.swing.event.ChangeEvent;
import java.awt.event.ItemEvent;
import com.mindprod.common15.FontFactory;
import com.mindprod.common13.HybridJ;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemListener;
import com.mindprod.common13.CMPAboutJBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;
import com.mindprod.fastcat.FastCat;
import javax.swing.JComponent;
import javax.swing.SpinnerModel;
import java.util.Vector;
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
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.text.DecimalFormat;
import java.awt.Color;
import javax.swing.JApplet;

public final class AmericanTax extends JApplet
{
    static final boolean DEBUGGING = false;
    private static final int APPLET_HEIGHT = 552;
    private static final int APPLET_WIDTH = 756;
    private static final String DEFAULT_VENDOR_STATE = "CA";
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 1999-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2010-12-19";
    private static final String TITLE_STRING = "American Sales Tax Calculator";
    private static final String VERSION_STRING = "3.8";
    private static final Color BACKGROUND_FOR_EDITABLE;
    private static final Color BACKGROUND_FOR_RESULT_DARK;
    private static final Color BACKGROUND_FOR_RESULT_MEDIUM;
    private static final Color BACKGROUND_FOR_RESULT_PALE;
    private static final Color BLACK;
    private static final Color DARK_GREEN;
    private static final Color FOREGROUND_FOR_ENTER;
    private static final Color FOREGROUND_FOR_LABEL;
    private static final Color FOREGROUND_FOR_TITLE;
    private static final Color WHITE;
    private static final DecimalFormat DOLLARFORMAT;
    private static final Font FONT_FOR_LABELS;
    private static final Font FONT_FOR_TITLE;
    private static final Font FONT_FOR_TITLE2;
    private JButton about;
    private JButton calcSaleAmountButton;
    private JButton calcTotalPayableButton;
    private JComboBox buyerDistrict;
    private JComboBox buyerState;
    private JEditorPane instructions;
    private JLabel buyerDistrictLabel;
    private JLabel buyerStateLabel;
    private JLabel districtSalesTaxLabel;
    private JLabel report;
    private JLabel saleAmountLabel;
    private JLabel stateSalesTaxLabel;
    private JLabel title;
    private JLabel title2;
    private JLabel totalPayableLabel;
    private JLabel totalSalesTaxLabel;
    private JSpinner.NumberEditor saleAmountNumberEditor;
    private JSpinner saleAmountSpinner;
    private JTextField districtSalesTaxText;
    private JTextField stateSalesTaxText;
    private JTextField totalPayableText;
    private JTextField totalSalesTaxText;
    private SpinnerNumberModel saleAmountSpinnerModel;
    
    public void destroy() {
        this.about = null;
        this.buyerDistrict = null;
        this.buyerDistrictLabel = null;
        this.buyerState = null;
        this.buyerStateLabel = null;
        this.calcSaleAmountButton = null;
        this.calcTotalPayableButton = null;
        this.districtSalesTaxLabel = null;
        this.districtSalesTaxText = null;
        this.instructions = null;
        this.report = null;
        this.saleAmountLabel = null;
        this.saleAmountNumberEditor = null;
        this.saleAmountSpinner = null;
        this.saleAmountSpinnerModel = null;
        this.stateSalesTaxLabel = null;
        this.stateSalesTaxText = null;
        this.title = null;
        this.title2 = null;
        this.totalPayableLabel = null;
        this.totalPayableText = null;
        this.totalSalesTaxLabel = null;
        this.totalSalesTaxText = null;
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        Common13.setLaf();
        StateItem.load();
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(AmericanTax.WHITE);
        contentPane.setLayout(new GridBagLayout());
        this.createComponents();
        this.setDistrictChoices();
        this.calcAndDisplayTaxes();
        this.layoutGridBag(contentPane);
        this.hookListeners();
        this.validate();
        this.setVisible(true);
    }
    
    private static String toDollar(final double amount, final boolean bz) {
        if (bz && amount == 0.0) {
            return "";
        }
        return AmericanTax.DOLLARFORMAT.format(amount);
    }
    
    private void calcAndDisplaySaleAmount() {
        final DistrictItem districtItem = (DistrictItem)this.buyerDistrict.getSelectedItem();
        final String totalPayableString = this.totalPayableText.getText();
        double totalPayable;
        try {
            totalPayable = (double)new DollarNumberFormatter().stringToValue(totalPayableString);
        }
        catch (ParseException event) {
            totalPayable = 0.0;
        }
        final double saleAmount = AmericanTaxTable.calcSaleAmount(districtItem, totalPayable);
        this.saleAmountSpinner.setValue(saleAmount);
    }
    
    private void calcAndDisplayTaxes() {
        final double saleAmount = this.saleAmountSpinnerModel.getNumber().doubleValue();
        final DistrictItem districtItem = (DistrictItem)this.buyerDistrict.getSelectedItem();
        final double stateSalesTaxAmount = AmericanTaxTable.calcStateSalesTax(districtItem, saleAmount);
        final double districtSalesTaxAmount = AmericanTaxTable.calcDistrictSalesTax(districtItem, saleAmount);
        final double totalSalesTaxAmount = AmericanTaxTable.calcTotalSalesTax(districtItem, saleAmount);
        final double totalPayableAmount = AmericanTaxTable.calcTotalPayable(districtItem, saleAmount);
        this.stateSalesTaxText.setText(toDollar(stateSalesTaxAmount, true));
        this.districtSalesTaxText.setText(toDollar(districtSalesTaxAmount, true));
        this.totalSalesTaxText.setText(toDollar(totalSalesTaxAmount, true));
        this.totalPayableText.setText(toDollar(totalPayableAmount, false));
    }
    
    private void createComponents() {
        (this.title = new JLabel("American Sales Tax Calculator 3.8")).setFont(AmericanTax.FONT_FOR_TITLE);
        this.title.setForeground(AmericanTax.FOREGROUND_FOR_TITLE);
        (this.title2 = new JLabel("released:2010-12-19 build:9411")).setFont(AmericanTax.FONT_FOR_TITLE2);
        this.title2.setForeground(AmericanTax.FOREGROUND_FOR_TITLE);
        (this.about = new JEButton("About")).setToolTipText("About American Sales Tax Calculator 3.8");
        (this.buyerState = new JComboBox((Vector<E>)StateItem.getStateChoices())).setSelectedItem(StateItem.findStateItem("CA"));
        (this.buyerStateLabel = new JLabel("Buyer's State", 4)).setFont(AmericanTax.FONT_FOR_LABELS);
        this.buyerStateLabel.setForeground(AmericanTax.FOREGROUND_FOR_LABEL);
        this.buyerDistrict = new JComboBox((Vector<E>)StateItem.findStateItem("CA").getDistrictItems());
        (this.buyerDistrictLabel = new JLabel("Buyer's District", 4)).setFont(AmericanTax.FONT_FOR_LABELS);
        this.buyerDistrictLabel.setForeground(AmericanTax.FOREGROUND_FOR_LABEL);
        (this.saleAmountSpinner = new JSpinner()).setFont(AmericanTax.FONT_FOR_LABELS);
        this.saleAmountSpinner.setForeground(AmericanTax.FOREGROUND_FOR_ENTER);
        this.saleAmountSpinner.setBackground(AmericanTax.BACKGROUND_FOR_EDITABLE);
        this.saleAmountSpinner.setValue(100.0);
        this.saleAmountSpinnerModel = new SpinnerNumberModel(100.0, 0.0, 999999.99, 0.01);
        this.saleAmountNumberEditor = new JSpinner.NumberEditor(this.saleAmountSpinner, "'$'###,###,##0.00");
        this.saleAmountSpinner.setModel(this.saleAmountSpinnerModel);
        this.saleAmountSpinner.setEditor(this.saleAmountNumberEditor);
        (this.saleAmountLabel = new JLabel("Amount of Sale", 4)).setFont(AmericanTax.FONT_FOR_LABELS);
        this.saleAmountLabel.setForeground(AmericanTax.FOREGROUND_FOR_LABEL);
        final boolean usingMac = System.getProperty("os.name").equals("Mac OS X");
        final char downArrow = usingMac ? '\u2193' : '\u21d3';
        final char upArrow = usingMac ? '\u2191' : '\u21d1';
        (this.calcTotalPayableButton = new JEButton("Calc " + downArrow)).setToolTipText("Calculate sales tax");
        (this.calcSaleAmountButton = new JEButton("Calc " + upArrow)).setToolTipText("Reverse calculate sale amount");
        (this.stateSalesTaxLabel = new JLabel("State Sales Tax", 4)).setFont(AmericanTax.FONT_FOR_LABELS);
        this.stateSalesTaxLabel.setForeground(AmericanTax.FOREGROUND_FOR_LABEL);
        (this.stateSalesTaxText = new JTextField("", 16)).setEditable(false);
        this.stateSalesTaxText.setFont(AmericanTax.FONT_FOR_LABELS);
        this.stateSalesTaxText.setForeground(AmericanTax.BLACK);
        this.stateSalesTaxText.setBackground(AmericanTax.BACKGROUND_FOR_RESULT_PALE);
        this.stateSalesTaxText.setHorizontalAlignment(4);
        (this.districtSalesTaxLabel = new JLabel("District Sales Tax", 4)).setFont(AmericanTax.FONT_FOR_LABELS);
        this.districtSalesTaxLabel.setForeground(AmericanTax.FOREGROUND_FOR_LABEL);
        (this.districtSalesTaxText = new JTextField("", 16)).setEditable(false);
        this.districtSalesTaxText.setFont(AmericanTax.FONT_FOR_LABELS);
        this.districtSalesTaxText.setForeground(AmericanTax.BLACK);
        this.districtSalesTaxText.setBackground(AmericanTax.BACKGROUND_FOR_RESULT_PALE);
        this.districtSalesTaxText.setHorizontalAlignment(4);
        (this.totalSalesTaxLabel = new JLabel("Total sales Tax", 4)).setFont(AmericanTax.FONT_FOR_LABELS);
        this.totalSalesTaxLabel.setForeground(AmericanTax.FOREGROUND_FOR_LABEL);
        (this.totalSalesTaxText = new JTextField("", 16)).setEditable(false);
        this.totalSalesTaxText.setFont(AmericanTax.FONT_FOR_LABELS);
        this.totalSalesTaxText.setForeground(AmericanTax.BLACK);
        this.totalSalesTaxText.setBackground(AmericanTax.BACKGROUND_FOR_RESULT_MEDIUM);
        this.totalSalesTaxText.setHorizontalAlignment(4);
        (this.totalPayableLabel = new JLabel("Total Payable", 4)).setFont(AmericanTax.FONT_FOR_LABELS);
        this.totalPayableLabel.setForeground(AmericanTax.FOREGROUND_FOR_LABEL);
        (this.totalPayableText = new JTextField("", 16)).setEditable(true);
        this.totalPayableText.setFont(AmericanTax.FONT_FOR_LABELS);
        this.totalPayableText.setForeground(AmericanTax.BLACK);
        this.totalPayableText.setBackground(AmericanTax.BACKGROUND_FOR_RESULT_DARK);
        this.totalPayableText.setHorizontalAlignment(4);
        final FastCat sb = new FastCat(15);
        sb.append("<html><body text=\"#339911\" bgcolor=\"#f8f8f8\" link=\"#ff0000\" vlink=\"#330099\" alink=\"#000800\">");
        sb.append("<font face=\"Dialog,SansSerif,sans-serif\">");
        sb.append("Select buyer's state/district, then either:");
        sb.append("<ul>");
        sb.append("<li>Click the up/down <em>Amount of Sale</em> spinner arrows.</li>");
        sb.append("<li>Fill in the <em>Amount of Sale</em> (with $, decimal and pennies), then click <em>Calc ");
        sb.append(downArrow);
        sb.append("</em> ");
        sb.append("to find the <em>Total Payable</em>.</li>");
        sb.append("<li>Fill in the <em>Total Payable</em> (with $, decimal and pennies), then click <em>Calc ");
        sb.append(upArrow);
        sb.append("</em> ");
        sb.append("to find the original <em>Amount of Sale</em>.</li>");
        sb.append("</ul>");
        sb.append("</font></body></html>");
        final String instructionsHTML = sb.toString();
        (this.instructions = new JEditorPane()).setContentType("text/html");
        this.instructions.setEditable(false);
        this.instructions.setMargin(new Insets(5, 5, 5, 5));
        try {
            this.instructions.setText(instructionsHTML);
        }
        catch (Exception e) {
            System.err.println("Because of Sun bug, unable to display instructions, please exit browser and restart");
            e.printStackTrace(System.err);
            System.err.println(instructionsHTML);
        }
        (this.report = new JLabel("Please report tax changes, errors and omissions to roedyg@mindprod.com.")).setForeground(AmericanTax.DARK_GREEN);
    }
    
    private void hookListeners() {
        this.about.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                new CMPAboutJBox("American Sales Tax Calculator", "3.8", "Calculates American state sales taxes", "and some regional sales taxes.", "freeware", "2010-12-19", 1999, "Roedy Green", "AMERICANTAX", "1.5");
            }
        });
        final TheListener theListener = new TheListener();
        this.calcTotalPayableButton.addActionListener(theListener);
        this.buyerState.addItemListener(theListener);
        this.buyerDistrict.addItemListener(theListener);
        this.saleAmountSpinnerModel.addChangeListener(theListener);
        this.saleAmountSpinner.addChangeListener(theListener);
        this.calcSaleAmountButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                AmericanTax.this.calcAndDisplaySaleAmount();
                AmericanTax.this.calcAndDisplayTaxes();
            }
        });
    }
    
    private void layoutGridBag(final Container contentPane) {
        contentPane.add(this.title, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0, 17, 0, new Insets(15, 15, 0, 5), 0, 0));
        contentPane.add(this.title2, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, 17, 0, new Insets(0, 15, 5, 5), 0, 0));
        contentPane.add(this.about, new GridBagConstraints(3, 0, 1, 2, 0.0, 0.0, 13, 0, new Insets(15, 5, 5, 15), 10, 2));
        contentPane.add(this.buyerState, new GridBagConstraints(0, 2, 1, 1, 20.0, 0.0, 17, 2, new Insets(5, 15, 5, 5), 0, 0));
        contentPane.add(this.buyerStateLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, 17, 0, new Insets(0, 15, 5, 5), 0, 0));
        contentPane.add(this.buyerDistrict, new GridBagConstraints(1, 2, 2, 1, 50.0, 0.0, 17, 2, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.buyerDistrictLabel, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, 17, 0, new Insets(0, 5, 5, 5), 0, 0));
        contentPane.add(this.saleAmountLabel, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.saleAmountSpinner, new GridBagConstraints(2, 4, 1, 1, 30.0, 0.0, 13, 2, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.calcTotalPayableButton, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, 14, 0, new Insets(5, 5, 5, 15), 0, 0));
        contentPane.add(this.stateSalesTaxLabel, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.stateSalesTaxText, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, 13, 2, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.districtSalesTaxLabel, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.districtSalesTaxText, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, 13, 2, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.totalSalesTaxLabel, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.totalSalesTaxText, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0, 13, 2, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.totalPayableLabel, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0, 13, 0, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.totalPayableText, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0, 13, 2, new Insets(5, 5, 5, 5), 0, 0));
        contentPane.add(this.calcSaleAmountButton, new GridBagConstraints(3, 8, 1, 1, 0.0, 0.0, 14, 0, new Insets(5, 5, 5, 15), 0, 0));
        contentPane.add(this.instructions, new GridBagConstraints(0, 9, 4, 1, 0.0, 0.0, 17, 2, new Insets(5, 15, 5, 15), 0, 0));
        contentPane.add(this.report, new GridBagConstraints(0, 10, 4, 1, 0.0, 0.0, 17, 2, new Insets(5, 15, 15, 15), 0, 0));
    }
    
    private void setDistrictChoices() {
        this.buyerDistrict.setModel(new DefaultComboBoxModel<DistrictItem>(((StateItem)this.buyerState.getSelectedItem()).getDistrictItems()));
    }
    
    public static void main(final String[] args) {
        HybridJ.fireup(new AmericanTax(), "American Sales Tax Calculator 3.8", 756, 552);
    }
    
    static {
        BACKGROUND_FOR_EDITABLE = Color.WHITE;
        BACKGROUND_FOR_RESULT_DARK = new Color(10485716);
        BACKGROUND_FOR_RESULT_MEDIUM = new Color(11534308);
        BACKGROUND_FOR_RESULT_PALE = new Color(16187382);
        BLACK = Color.BLACK;
        DARK_GREEN = new Color(32768);
        FOREGROUND_FOR_ENTER = Color.BLACK;
        FOREGROUND_FOR_LABEL = new Color(176);
        FOREGROUND_FOR_TITLE = new Color(14423100);
        WHITE = Color.WHITE;
        DOLLARFORMAT = new DecimalFormat("'$'###,###,###,###,##0.00");
        FONT_FOR_LABELS = FontFactory.build("Dialog", 1, 15);
        FONT_FOR_TITLE = FontFactory.build("Dialog", 1, 16);
        FONT_FOR_TITLE2 = FontFactory.build("Dialog", 0, 14);
    }
    
    private final class TheListener implements ItemListener, ActionListener, ChangeListener
    {
        public void actionPerformed(final ActionEvent e) {
            final Object object = e.getSource();
            if (object == AmericanTax.this.calcTotalPayableButton) {
                AmericanTax.this.calcAndDisplayTaxes();
            }
        }
        
        public void itemStateChanged(final ItemEvent e) {
            final Object object = e.getSource();
            if (object == AmericanTax.this.buyerState) {
                AmericanTax.this.setDistrictChoices();
                AmericanTax.this.calcAndDisplayTaxes();
            }
            else if (object == AmericanTax.this.buyerDistrict) {
                AmericanTax.this.calcAndDisplayTaxes();
            }
        }
        
        public void stateChanged(final ChangeEvent e) {
            final Object object = e.getSource();
            if (object == AmericanTax.this.saleAmountSpinnerModel) {
                AmericanTax.this.calcAndDisplayTaxes();
            }
        }
    }
}
