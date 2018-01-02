// 
// Decompiled by Procyon v0.5.30
// 

package MortCalcApplet;

import java.awt.event.FocusEvent;
import java.util.Date;
import java.awt.event.AdjustmentEvent;
import java.awt.event.TextEvent;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.io.OutputStream;
import com.ms.io.clientstorage.ClientStorageManager;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import com.ms.io.clientstorage.ClientStore;
import java.math.BigDecimal;
import java.awt.Rectangle;
import java.awt.Choice;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Scrollbar;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Panel;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import java.awt.event.FocusListener;
import java.awt.event.TextListener;
import java.awt.event.AdjustmentListener;
import java.applet.Applet;

public class MortCalcApplet extends Applet implements AdjustmentListener, TextListener, FocusListener, ItemListener, KeyListener
{
    Panel pnlPurchasePrice;
    Panel pnlInterestRate;
    Panel pnlLoanTerm;
    Panel pnlDownPayment;
    Panel pnlMonthlyPayment;
    Panel pnlTotalCost;
    Panel pnlVersionDateTime;
    CheckboxGroup grpLock;
    Checkbox chkLockPurchasePrice;
    Checkbox chkLockMonthlyPayment;
    Scrollbar scrollPurchasePrice;
    Scrollbar scrollInterestRate;
    Scrollbar scrollLoanTermYears;
    Scrollbar scrollMonthlyPayment;
    TextField fldPurchasePrice;
    TextField fldDownPayment;
    Label lblPurchasePrice;
    Label lblInterestRate;
    Label lblInterestRateValue;
    Label lblLoanTerm;
    Label lblLoanTermValue;
    Label lblDownPayment;
    Label lblMonthlyPayment;
    Label lblMonthlyPaymentValue;
    Label lblTotalCost;
    Label lblVersionDateTime;
    Choice choiceDownPayment;
    private static final Rectangle RECT_LABEL;
    private static final Rectangle RECT_SCROLL_BAR;
    private static final int VALUE_LEFT;
    private static final int PANEL_WIDTH = 380;
    private static final int PANEL_HEIGHT = 60;
    private static final int PANEL_LEFT = 5;
    private static final BigDecimal ZERO;
    private static final BigDecimal ONE;
    private static final BigDecimal FIVE;
    private static final BigDecimal TWELVE;
    private boolean mbReadyToListen;
    private boolean mbListenersAdded;
    private boolean mbKeyTypedPurchasePrice;
    private boolean mbKeyTypedDownPayment;
    private int miPurchasePrice;
    private BigDecimal mnInterestRate;
    private int miLoanTermMonths;
    private String msDownPaymentPct;
    private BigDecimal mnDownPayment;
    private BigDecimal mnMonthlyPayment;
    private boolean mbLockPurchasePrice;
    private ClientStore mobjClientStore;
    private static final String CLIENT_STORE_FILE = "MortCalcParms";
    private static final String PARAM_BACKGROUND = "background";
    private static final String PARAM_FOREGROUND = "foreground";
    private static final String PARAM_PURCHASE_PRICE = "purchaseprice";
    private static final String PARAM_INTEREST_RATE = "interestrate";
    private static final String PARAM_LOAN_TERM = "loanterm";
    private static final String PARAM_DOWN_PAYMENT = "downpayment";
    private static final String PARAM_DOWN_PAYMENT_PCT = "downpaymentpct";
    private static final String PARAM_MONTHLY_PAYMENT = "monthlypayment";
    private static final String PARAM_LOCK_PURCHASE_PRICE = "lockpurchaseprice";
    private static final String PARAM_CLIENT_STORE = "clientstore";
    private static final String TERMINATOR = ";";
    
    public void stop() {
        try {
            System.out.println("starting stop()");
            if (Class.forName("com.ms.security.PolicyEngine") != null) {
                System.out.println("asserting permission");
                PolicyEngine.assertPermission(PermissionID.CLIENTSTORE);
            }
            System.out.println("opening writable client store stream");
            final OutputStream openWritable = ClientStorageManager.openWritable("MortCalcParms", false);
            System.out.println("marking file as expendable");
            this.mobjClientStore.markFileAsExpendable("MortCalcParms", true);
            System.out.println("writing parameters");
            this.write(openWritable, "interestrate" + "=" + this.mnInterestRate.toString());
            this.write(openWritable, "loanterm" + "=" + Integer.toString(this.miLoanTermMonths));
            this.write(openWritable, "downpayment" + "=" + this.mnDownPayment.toString());
            this.write(openWritable, "downpaymentpct" + "=" + this.msDownPaymentPct);
            this.write(openWritable, "monthlypayment" + "=" + this.mnMonthlyPayment.toString());
            this.write(openWritable, "lockpurchaseprice" + "=" + this.chkLockPurchasePrice.getState());
            System.out.println("closing output stream");
            openWritable.close();
        }
        catch (Exception ex) {}
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.choiceDownPayment) {
            this.msDownPaymentPct = this.choiceDownPayment.getSelectedItem();
            this.populate();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    private void useClientStoreParams() {
        if (this.mobjClientStore == null) {
            return;
        }
        try {
            if (Class.forName("com.ms.security.PolicyEngine") != null) {
                PolicyEngine.assertPermission(PermissionID.CLIENTSTORE);
            }
            final InputStream openReadable = ClientStorageManager.openReadable("MortCalcParms");
            final byte[] array = new byte[255];
            openReadable.read(array);
            final StringTokenizer stringTokenizer = new StringTokenizer(new String(array), ";");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final int index = nextToken.indexOf("=");
                if (index > -1) {
                    final String substring = nextToken.substring(0, index);
                    final String substring2 = nextToken.substring(index + 1);
                    if (substring.equals("interestrate")) {
                        this.mnInterestRate = new BigDecimal(substring2);
                    }
                    else if (substring.equals("loanterm")) {
                        this.miLoanTermMonths = Integer.decode(substring2);
                    }
                    else if (substring.equals("downpayment")) {
                        this.mnDownPayment = new BigDecimal(substring2);
                    }
                    else if (substring.equals("downpaymentpct")) {
                        this.msDownPaymentPct = substring2;
                    }
                    else if (substring.equals("monthlypayment")) {
                        this.mnMonthlyPayment = new BigDecimal(substring2);
                    }
                    else {
                        if (!substring.equals("lockpurchaseprice")) {
                            continue;
                        }
                        this.mbLockPurchasePrice = substring2.equals("true");
                    }
                }
            }
            openReadable.close();
        }
        catch (Exception ex) {
            System.out.println("Problem in useClientStoreParams: " + ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }
    
    void initForm() {
        this.setBackground(Color.lightGray);
        this.setForeground(Color.black);
        this.setLayout(null);
        this.lblPurchasePrice.setBounds(MortCalcApplet.RECT_LABEL);
        this.scrollPurchasePrice.setBounds(MortCalcApplet.RECT_SCROLL_BAR);
        this.scrollPurchasePrice.setBlockIncrement(1000);
        this.scrollPurchasePrice.setUnitIncrement(500);
        this.fldPurchasePrice.setBounds(MortCalcApplet.VALUE_LEFT, 30, 85, 20);
        this.chkLockPurchasePrice.setBounds(110, 5, 200, 25);
        this.pnlPurchasePrice.setBounds(5, 5, 380, 60);
        this.pnlPurchasePrice.setLayout(null);
        this.pnlPurchasePrice.add(this.lblPurchasePrice);
        this.pnlPurchasePrice.add(this.chkLockPurchasePrice);
        this.pnlPurchasePrice.add(this.scrollPurchasePrice);
        this.pnlPurchasePrice.add(this.fldPurchasePrice);
        this.lblInterestRate.setBounds(MortCalcApplet.RECT_LABEL);
        this.scrollInterestRate.setBounds(MortCalcApplet.RECT_SCROLL_BAR);
        this.scrollInterestRate.setBlockIncrement(2);
        this.scrollInterestRate.setUnitIncrement(1);
        this.lblInterestRateValue.setBounds(MortCalcApplet.VALUE_LEFT, 28, 60, 25);
        this.pnlInterestRate.setBounds(5, 65, 380, 60);
        this.pnlInterestRate.setLayout(null);
        this.pnlInterestRate.add(this.lblInterestRate);
        this.pnlInterestRate.add(this.scrollInterestRate);
        this.pnlInterestRate.add(this.lblInterestRateValue);
        this.lblLoanTerm.setBounds(MortCalcApplet.RECT_LABEL);
        this.scrollLoanTermYears.setBounds(MortCalcApplet.RECT_SCROLL_BAR);
        this.scrollLoanTermYears.setBlockIncrement(10);
        this.scrollLoanTermYears.setUnitIncrement(1);
        this.lblLoanTermValue.setBounds(MortCalcApplet.VALUE_LEFT, 28, 160, 25);
        this.pnlLoanTerm.setBounds(5, 125, 380, 60);
        this.pnlLoanTerm.setLayout(null);
        this.pnlLoanTerm.add(this.lblLoanTerm);
        this.pnlLoanTerm.add(this.scrollLoanTermYears);
        this.pnlLoanTerm.add(this.lblLoanTermValue);
        this.lblDownPayment.setBounds(MortCalcApplet.RECT_LABEL);
        this.choiceDownPayment.setBounds(5, 30, 50, 20);
        this.fldDownPayment.setBounds(60, 30, 90, 20);
        this.choiceDownPayment.addItem("");
        this.choiceDownPayment.addItem("0%");
        this.choiceDownPayment.addItem("5%");
        this.choiceDownPayment.addItem("10%");
        this.choiceDownPayment.addItem("15%");
        this.choiceDownPayment.addItem("20%");
        this.choiceDownPayment.addItem("25%");
        this.choiceDownPayment.addItem("30%");
        this.pnlDownPayment.setBounds(5, 185, 380, 60);
        this.pnlDownPayment.setLayout(null);
        this.pnlDownPayment.add(this.lblDownPayment);
        this.pnlDownPayment.add(this.choiceDownPayment);
        this.pnlDownPayment.add(this.fldDownPayment);
        this.lblMonthlyPayment.setBounds(MortCalcApplet.RECT_LABEL);
        this.scrollMonthlyPayment.setBounds(MortCalcApplet.RECT_SCROLL_BAR);
        this.scrollMonthlyPayment.setBlockIncrement(10000);
        this.scrollMonthlyPayment.setUnitIncrement(100);
        this.lblMonthlyPaymentValue.setBounds(MortCalcApplet.VALUE_LEFT, 28, 100, 25);
        this.chkLockMonthlyPayment.setBounds(110, 5, 160, 25);
        this.pnlMonthlyPayment.setBounds(5, 245, 380, 60);
        this.pnlMonthlyPayment.setLayout(null);
        this.pnlMonthlyPayment.add(this.lblMonthlyPayment);
        this.pnlMonthlyPayment.add(this.chkLockMonthlyPayment);
        this.pnlMonthlyPayment.add(this.scrollMonthlyPayment);
        this.pnlMonthlyPayment.add(this.lblMonthlyPaymentValue);
        this.lblTotalCost.setBounds(0, 0, 200, 25);
        this.pnlTotalCost.setBounds(192, 310, 380, 60);
        this.pnlTotalCost.setLayout(null);
        this.pnlTotalCost.add(this.lblTotalCost);
        this.lblVersionDateTime.setBounds(5, 5, 150, 25);
        this.pnlVersionDateTime.setBounds(0, 320, 380, 60);
        this.pnlVersionDateTime.setLayout(null);
        this.pnlVersionDateTime.setFont(new Font("Arial", 0, 10));
        this.pnlVersionDateTime.add(this.lblVersionDateTime);
        this.add(this.pnlPurchasePrice);
        this.add(this.pnlInterestRate);
        this.add(this.pnlLoanTerm);
        this.add(this.pnlDownPayment);
        this.add(this.pnlMonthlyPayment);
        this.add(this.pnlTotalCost);
    }
    
    private void scrollPurchasePrice() {
        this.miPurchasePrice = this.scrollPurchasePrice.getValue();
        this.populate();
    }
    
    private void scrollMonthlyPayment() {
        this.mnMonthlyPayment = this.intToBigDecimal(this.scrollMonthlyPayment.getValue()).movePointLeft(2);
        this.populate();
    }
    
    private void write(final OutputStream outputStream, String string) {
        string += ";";
        try {
            outputStream.write(string.getBytes());
        }
        catch (Exception ex) {}
    }
    
    public void textValueChanged(final TextEvent textEvent) {
        final Object source = textEvent.getSource();
        if (source == this.fldPurchasePrice && this.mbKeyTypedPurchasePrice) {
            if (!this.chkLockPurchasePrice.getState()) {
                this.chkLockPurchasePrice.setState(true);
            }
            this.miPurchasePrice = this.currencyToInt(this.fldPurchasePrice.getText(), 0);
            this.populate();
            this.mbKeyTypedPurchasePrice = false;
            return;
        }
        if (source == this.fldDownPayment && this.mbKeyTypedDownPayment) {
            this.msDownPaymentPct = "";
            this.mnDownPayment = this.currencyToBigDecimal(this.fldDownPayment.getText());
            this.populate();
            this.mbKeyTypedDownPayment = false;
        }
    }
    
    private BigDecimal intToBigDecimal(final int n) {
        return new BigDecimal((double)new Integer(n));
    }
    
    private void addListeners() {
        if (!this.mbReadyToListen || this.mbListenersAdded) {
            return;
        }
        this.scrollInterestRate.addAdjustmentListener(this);
        this.scrollLoanTermYears.addAdjustmentListener(this);
        this.scrollPurchasePrice.addAdjustmentListener(this);
        this.scrollMonthlyPayment.addAdjustmentListener(this);
        this.fldPurchasePrice.addTextListener(this);
        this.fldPurchasePrice.addFocusListener(this);
        this.fldPurchasePrice.addKeyListener(this);
        this.choiceDownPayment.addItemListener(this);
        this.fldDownPayment.addTextListener(this);
        this.fldDownPayment.addFocusListener(this);
        this.fldDownPayment.addKeyListener(this);
        this.mbListenersAdded = true;
    }
    
    private void scrollLoanTerm() {
        this.miLoanTermMonths = this.scrollLoanTermYears.getValue() * 12;
        this.populate();
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final Object source = adjustmentEvent.getSource();
        if (source == this.scrollInterestRate) {
            this.scrollInterestRate();
            return;
        }
        if (source == this.scrollPurchasePrice) {
            if (!this.chkLockPurchasePrice.getState()) {
                this.chkLockPurchasePrice.setState(true);
            }
            this.scrollPurchasePrice();
            return;
        }
        if (source == this.scrollLoanTermYears) {
            this.scrollLoanTerm();
            return;
        }
        if (source == this.scrollMonthlyPayment) {
            if (!this.chkLockMonthlyPayment.getState()) {
                this.chkLockMonthlyPayment.setState(true);
            }
            this.scrollMonthlyPayment();
        }
    }
    
    private BigDecimal currencyToBigDecimal(final String s) {
        if (s == null) {
            return MortCalcApplet.ZERO;
        }
        if (s.equals("")) {
            return MortCalcApplet.ZERO;
        }
        return new BigDecimal(this.strip(s, "0123456789."));
    }
    
    private void populate() {
        this.removeListeners();
        this.recompute();
        final int value = this.scrollPurchasePrice.getValue();
        if (value != this.miPurchasePrice) {
            final int minimum = this.scrollPurchasePrice.getMinimum();
            final int maximum = this.scrollPurchasePrice.getMaximum();
            if (this.miPurchasePrice < minimum && value != minimum) {
                this.scrollPurchasePrice.setValue(minimum);
            }
            else if (this.miPurchasePrice > maximum && value != maximum) {
                this.scrollPurchasePrice.setValue(maximum);
            }
            else {
                this.scrollPurchasePrice.setValue(this.miPurchasePrice);
            }
        }
        if (this.currencyToInt(this.fldPurchasePrice.getText(), 0) != this.miPurchasePrice) {
            this.fldPurchasePrice.setText(this.intToCurrency(this.miPurchasePrice, 0));
        }
        if (!this.intToBigDecimal(this.scrollInterestRate.getValue() * 5).movePointLeft(2).equals(this.mnInterestRate)) {
            this.scrollInterestRate.setValue(this.mnInterestRate.movePointRight(2).divide(MortCalcApplet.FIVE, 0, 4).intValue());
        }
        final String text = this.lblInterestRateValue.getText();
        if (!text.substring(0, text.length() - 1).equals(this.mnInterestRate.toString())) {
            this.lblInterestRateValue.setText(this.mnInterestRate.toString() + "%");
        }
        if (this.scrollLoanTermYears.getValue() * 12 != this.miLoanTermMonths) {
            this.scrollLoanTermYears.setValue(this.miLoanTermMonths / 12);
        }
        final String text2 = this.lblLoanTermValue.getText();
        final int index = text2.indexOf(" ");
        int intValue = 0;
        if (index > -1) {
            intValue = new Integer(text2.substring(0, index));
        }
        if (intValue != this.miLoanTermMonths) {
            final int n = this.miLoanTermMonths / 12;
            String string = " yr";
            if (n > 1) {
                string += "s";
            }
            this.lblLoanTermValue.setText(new Integer(this.miLoanTermMonths).toString() + " mos (" + new Integer(n).toString() + string + ")");
        }
        if (!this.msDownPaymentPct.equals(this.choiceDownPayment.getSelectedItem())) {
            this.choiceDownPayment.select(this.msDownPaymentPct);
        }
        if (!this.currencyToBigDecimal(this.fldDownPayment.getText()).equals(this.mnDownPayment)) {
            this.fldDownPayment.setText(this.bigDecimalToCurrency(this.mnDownPayment));
        }
        final int value2 = this.scrollMonthlyPayment.getValue();
        final BigDecimal intToBigDecimal = this.intToBigDecimal(value2);
        final long longValue = this.mnMonthlyPayment.movePointRight(2).longValue();
        if (!intToBigDecimal.equals(this.mnMonthlyPayment)) {
            final int minimum2 = this.scrollMonthlyPayment.getMinimum();
            final int maximum2 = this.scrollMonthlyPayment.getMaximum();
            if (longValue < minimum2 && value2 != minimum2) {
                this.scrollMonthlyPayment.setValue(minimum2);
            }
            else if (longValue > maximum2 && value2 != maximum2) {
                this.scrollMonthlyPayment.setValue(maximum2);
            }
            else {
                this.scrollMonthlyPayment.setValue((int)(Object)new Long(longValue));
            }
        }
        if (!this.currencyToBigDecimal(this.lblMonthlyPaymentValue.getText()).equals(this.mnMonthlyPayment)) {
            this.lblMonthlyPaymentValue.setText(this.bigDecimalToCurrency(this.mnMonthlyPayment));
        }
        this.addListeners();
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final Object source = keyEvent.getSource();
        if (source == this.fldPurchasePrice) {
            this.mbKeyTypedPurchasePrice = true;
            return;
        }
        if (source == this.fldDownPayment) {
            this.mbKeyTypedDownPayment = true;
        }
    }
    
    public MortCalcApplet() {
        this.pnlPurchasePrice = new Panel();
        this.pnlInterestRate = new Panel();
        this.pnlLoanTerm = new Panel();
        this.pnlDownPayment = new Panel();
        this.pnlMonthlyPayment = new Panel();
        this.pnlTotalCost = new Panel();
        this.pnlVersionDateTime = new Panel();
        this.grpLock = new CheckboxGroup();
        this.chkLockPurchasePrice = new Checkbox("Calculate my monthly payment", true, this.grpLock);
        this.chkLockMonthlyPayment = new Checkbox("Tell me what I can afford", false, this.grpLock);
        this.scrollPurchasePrice = new Scrollbar(0, 20000, 500, 0, 20000000);
        this.scrollInterestRate = new Scrollbar(0, 180, 5, 0, 405);
        this.scrollLoanTermYears = new Scrollbar(0, 4, 0, 1, 41);
        this.scrollMonthlyPayment = new Scrollbar(0, 5000, 1, 0, 2000000);
        this.fldPurchasePrice = new TextField(15);
        this.fldDownPayment = new TextField(15);
        this.lblPurchasePrice = new Label("Purchase Price");
        this.lblInterestRate = new Label("Interest Rate");
        this.lblInterestRateValue = new Label("9.00%");
        this.lblLoanTerm = new Label("Loan Term");
        this.lblLoanTermValue = new Label("48 mos (4 yrs)");
        this.lblDownPayment = new Label("Down Payment");
        this.lblMonthlyPayment = new Label("Monthly Payment");
        this.lblMonthlyPaymentValue = new Label("$200,000");
        this.lblTotalCost = new Label("Total Cost will be");
        this.lblVersionDateTime = new Label("v1.2 " + new Date().toLocaleString());
        this.choiceDownPayment = new Choice();
        this.mbReadyToListen = false;
        this.mbListenersAdded = false;
        this.mbKeyTypedPurchasePrice = false;
        this.mbKeyTypedDownPayment = false;
        this.mbLockPurchasePrice = true;
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "background", "String", "Background color, format \"rrggbb\"" }, { "foreground", "String", "Foreground color, format \"rrggbb\"" } };
    }
    
    private String strip(final String s, final String s2) {
        if (s == null) {
            return null;
        }
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            final String substring = s.substring(i, i + 1);
            if (s2.indexOf(substring) > -1) {
                string += substring;
            }
        }
        return string;
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        final Object source = focusEvent.getSource();
        if (source == this.fldPurchasePrice) {
            final int currencyToInt = this.currencyToInt(this.fldPurchasePrice.getText(), 0);
            this.removeListeners();
            this.fldPurchasePrice.setText(this.intToCurrency(currencyToInt, 0));
            this.addListeners();
            return;
        }
        if (source == this.fldDownPayment) {
            final int currencyToInt2 = this.currencyToInt(this.fldDownPayment.getText(), 2);
            this.removeListeners();
            this.fldDownPayment.setText(this.intToCurrency(currencyToInt2, 2));
            this.addListeners();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    private void usePageParams() {
        String parameter = this.getParameter("background");
        String parameter2 = this.getParameter("foreground");
        String parameter3 = this.getParameter("purchaseprice");
        String parameter4 = this.getParameter("interestrate");
        String parameter5 = this.getParameter("loanterm");
        if (parameter == null) {
            parameter = "C0C0C0";
        }
        if (parameter2 == null) {
            parameter2 = "000000";
        }
        if (parameter3 == null) {
            parameter3 = "200000";
        }
        if (parameter4 == null) {
            parameter4 = "7.25";
        }
        if (parameter5 == null) {
            parameter5 = "48";
        }
        final Color stringToColor = this.stringToColor(parameter);
        final Color stringToColor2 = this.stringToColor(parameter2);
        this.setBackground(stringToColor);
        this.setForeground(stringToColor2);
        this.pnlDownPayment.setBackground(stringToColor);
        this.pnlInterestRate.setBackground(stringToColor);
        this.pnlLoanTerm.setBackground(stringToColor);
        this.pnlMonthlyPayment.setBackground(stringToColor);
        this.pnlPurchasePrice.setBackground(stringToColor);
        this.pnlTotalCost.setBackground(stringToColor);
        this.pnlVersionDateTime.setBackground(stringToColor);
        this.lblDownPayment.setBackground(stringToColor);
        this.lblInterestRate.setBackground(stringToColor);
        this.lblInterestRateValue.setBackground(stringToColor);
        this.lblLoanTerm.setBackground(stringToColor);
        this.lblLoanTermValue.setBackground(stringToColor);
        this.lblMonthlyPayment.setBackground(stringToColor);
        this.lblMonthlyPaymentValue.setBackground(stringToColor);
        this.lblPurchasePrice.setBackground(stringToColor);
        this.lblTotalCost.setBackground(stringToColor);
        this.lblVersionDateTime.setBackground(stringToColor);
        this.chkLockMonthlyPayment.setBackground(stringToColor);
        this.chkLockPurchasePrice.setBackground(stringToColor);
        this.miPurchasePrice = new Integer(parameter3);
        this.mnInterestRate = new BigDecimal(parameter4);
        this.miLoanTermMonths = new Integer(parameter5);
    }
    
    static {
        RECT_LABEL = new Rectangle(5, 5, 95, 25);
        RECT_SCROLL_BAR = new Rectangle(5, 30, 270, 20);
        VALUE_LEFT = MortCalcApplet.RECT_SCROLL_BAR.y + MortCalcApplet.RECT_SCROLL_BAR.width - 20;
        ZERO = new BigDecimal("0");
        ONE = new BigDecimal("1");
        FIVE = new BigDecimal("5");
        TWELVE = new BigDecimal("12");
    }
    
    private String bigDecimalToCurrency(final BigDecimal bigDecimal) {
        return this.longToCurrency(bigDecimal.movePointRight(bigDecimal.scale()).longValue(), bigDecimal.scale());
    }
    
    private String longToCurrency(final long n, final int n2) {
        int n3 = 0;
        int n4 = 3;
        if (n2 > 0) {
            n4 = 6;
        }
        String s = new BigDecimal((double)new Long(n)).movePointLeft(n2).toString();
        for (int i = s.length() - n4; i > 0; i = s.length() - n4 - n3) {
            s = s.substring(0, i) + "," + s.substring(i);
            n3 += 4;
        }
        return "$" + s;
    }
    
    private void getClientStore() {
        try {
            if (Class.forName("com.ms.security.PolicyEngine") != null) {
                PolicyEngine.assertPermission(PermissionID.CLIENTSTORE);
            }
            this.mobjClientStore = ClientStorageManager.getStore();
        }
        catch (Exception ex) {
            System.out.println("Problem in getClientStore: " + ex.toString());
        }
    }
    
    private void removeListeners() {
        if (!this.mbListenersAdded) {
            return;
        }
        this.scrollInterestRate.removeAdjustmentListener(this);
        this.scrollLoanTermYears.removeAdjustmentListener(this);
        this.scrollPurchasePrice.removeAdjustmentListener(this);
        this.scrollMonthlyPayment.removeAdjustmentListener(this);
        this.fldPurchasePrice.removeTextListener(this);
        this.fldPurchasePrice.removeFocusListener(this);
        this.fldPurchasePrice.removeKeyListener(this);
        this.choiceDownPayment.removeItemListener(this);
        this.fldDownPayment.removeTextListener(this);
        this.fldDownPayment.removeFocusListener(this);
        this.fldDownPayment.removeKeyListener(this);
        this.mbListenersAdded = false;
    }
    
    private int currencyToInt(String s, final int n) {
        final int index = s.indexOf(".");
        if (n > 0 && index == -1) {
            for (int i = 0; i < n; ++i) {
                s += "0";
            }
        }
        else if (index != s.length() - n - 1) {
            for (int n2 = n - s.length() + index + 1, j = 0; j < n2; ++j) {
                s += "0";
            }
        }
        final String strip = this.strip(s, "0123456789");
        if (strip == null) {
            return 0;
        }
        if (strip.equals("")) {
            return 0;
        }
        return new Integer(strip);
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        final Object source = focusEvent.getSource();
        if (!(source instanceof TextField)) {
            return;
        }
        ((TextField)source).selectAll();
    }
    
    public void init() {
        this.initForm();
        this.usePageParams();
        this.getClientStore();
        this.useClientStoreParams();
        if (this.mbLockPurchasePrice) {
            this.chkLockPurchasePrice.setState(true);
        }
        else {
            this.chkLockMonthlyPayment.setState(true);
        }
        if (this.msDownPaymentPct == null) {
            this.msDownPaymentPct = "20%";
        }
        this.populate();
        this.mbReadyToListen = true;
        this.addListeners();
    }
    
    private void recompute() {
        BigDecimal movePointLeft = null;
        final BigDecimal movePointLeft2 = this.mnInterestRate.movePointLeft(2);
        final BigDecimal intToBigDecimal = this.intToBigDecimal(this.miLoanTermMonths);
        final int intValue = this.mnInterestRate.movePointRight(2).intValue();
        BigDecimal bigDecimal;
        if (intValue == 0) {
            bigDecimal = this.intToBigDecimal(this.miLoanTermMonths);
        }
        else {
            final BigDecimal divide = movePointLeft2.divide(MortCalcApplet.TWELVE, 10, 4);
            BigDecimal bigDecimal2 = MortCalcApplet.ONE.divide(divide.add(MortCalcApplet.ONE), 10, 4);
            final BigDecimal bigDecimal3 = new BigDecimal(bigDecimal2.doubleValue());
            for (int i = 2; i <= this.miLoanTermMonths; ++i) {
                bigDecimal2 = bigDecimal2.multiply(bigDecimal3).setScale(10, 4);
            }
            bigDecimal = MortCalcApplet.ONE.subtract(bigDecimal2).divide(divide, 10, 4);
        }
        if (!this.msDownPaymentPct.equals("")) {
            movePointLeft = new BigDecimal(this.msDownPaymentPct.substring(0, this.msDownPaymentPct.length() - 1)).movePointLeft(2);
        }
        if (this.chkLockMonthlyPayment.getState()) {
            this.miPurchasePrice = this.mnMonthlyPayment.multiply(bigDecimal).intValue();
            final BigDecimal intToBigDecimal2 = this.intToBigDecimal(this.miPurchasePrice);
            if (this.msDownPaymentPct.equals("")) {
                this.miPurchasePrice += this.mnDownPayment.intValue();
            }
            else {
                final BigDecimal divide2 = intToBigDecimal2.divide(MortCalcApplet.ONE.subtract(movePointLeft), 2, 4);
                this.miPurchasePrice = divide2.intValue();
                this.mnDownPayment = divide2.multiply(movePointLeft);
                this.mnDownPayment = this.mnDownPayment.setScale(2, 4);
            }
        }
        else {
            final BigDecimal intToBigDecimal3 = this.intToBigDecimal(this.miPurchasePrice);
            if (!this.msDownPaymentPct.equals("")) {
                this.mnDownPayment = intToBigDecimal3.multiply(movePointLeft);
            }
            this.mnMonthlyPayment = intToBigDecimal3.subtract(this.mnDownPayment).divide(bigDecimal, 2, 4);
        }
        BigDecimal bigDecimal4;
        if (intValue == 0) {
            bigDecimal4 = this.intToBigDecimal(this.miPurchasePrice).movePointRight(2);
        }
        else {
            bigDecimal4 = this.mnMonthlyPayment.multiply(intToBigDecimal).movePointRight(2).add(this.mnDownPayment.movePointRight(2));
        }
        this.lblTotalCost.setText("Total Cost will be " + this.longToCurrency(bigDecimal4.longValue(), 2));
    }
    
    private void scrollInterestRate() {
        this.mnInterestRate = this.intToBigDecimal(this.scrollInterestRate.getValue() * 5).movePointLeft(2);
        this.populate();
    }
    
    private Color stringToColor(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
    
    private String intToCurrency(final int n, final int n2) {
        return this.longToCurrency(new Integer(n), n2);
    }
}
