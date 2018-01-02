import java.awt.Font;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.TextField;
import java.awt.Event;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MortgageApplet extends Applet
{
    final int YLOOKUPTBL_LIMIT = 120;
    double[][] yLookUptbl;
    double[][] graphValues;
    MortgageDialog mdb;
    MortgageFunctions mf;
    
    private TextField getTextField(final Event evt) {
        TextField tf = null;
        if (this.isEventComponent(evt, this.mdb.IDC_PRINCIPAL_TEXTBOX)) {
            tf = this.mdb.IDC_PRINCIPAL_TEXTBOX;
        }
        else if (this.isEventComponent(evt, this.mdb.IDC_INTEREST_RATE_TEXTBOX)) {
            tf = this.mdb.IDC_INTEREST_RATE_TEXTBOX;
        }
        else if (this.isEventComponent(evt, this.mdb.IDC_TERM_TEXTBOX)) {
            tf = this.mdb.IDC_TERM_TEXTBOX;
        }
        else if (this.isEventComponent(evt, this.mdb.IDC_PAYMENT_TEXTBOX)) {
            tf = this.mdb.IDC_PAYMENT_TEXTBOX;
        }
        return tf;
    }
    
    public void start() {
    }
    
    private boolean isEventComponent(final Event evt, final Component f) {
        final Rectangle r = f.bounds();
        return evt.x == r.x && evt.y == r.y;
    }
    
    public MortgageApplet() {
        this.yLookUptbl = new double[120][2];
        this.graphValues = new double[25][2];
        this.mdb = new MortgageDialog(this);
    }
    
    public void stop() {
    }
    
    public boolean keyUp(final Event evt, final int key) {
        if ('\t' == (char)key) {
            String fieldValue = "";
            if (evt.target instanceof TextField) {
                final TextField tf = this.getTextField(evt);
                fieldValue = tf.getText();
            }
            if (evt.shiftDown()) {
                this.movePrevious(evt, fieldValue);
            }
            else {
                this.moveNext(evt, fieldValue);
            }
            return true;
        }
        return false;
    }
    
    private double getDouble(final String textField, final String textFieldLabel) {
        double returnValue = -1.0;
        try {
            returnValue = Double.valueOf(textField);
        }
        catch (ArithmeticException e) {
            this.msgbox("Arithmetic Exception Error Converting Text Field (" + textFieldLabel + ") To Number " + e.toString());
        }
        catch (Exception e) {
            this.msgbox("Error Converting Text Field (" + textFieldLabel + ") To Number " + e.toString());
        }
        return returnValue;
    }
    
    private String formatCurrency(final double currency) {
        final String s = Double.toString(currency);
        final int l = s.length();
        final String result = "$" + s;
        return result;
    }
    
    private void movePrevious(final Event evt, final String fieldValue) {
        final boolean movePrevious = this.validateFieldValue(evt, fieldValue);
        if (movePrevious) {
            if (this.isEventComponent(evt, this.mdb.IDC_MONTHLY_TYPE)) {
                this.mdb.IDC_BUTTON_CALCULATE.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_SEMI_MONTHLY_TYPE)) {
                this.mdb.IDC_BUTTON_CALCULATE.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_BI_WEEKLY_TYPE)) {
                this.mdb.IDC_BUTTON_CALCULATE.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_WEEKLY_TYPE)) {
                this.mdb.IDC_BUTTON_CALCULATE.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_PRINCIPAL_TEXTBOX)) {
                this.mdb.IDC_MONTHLY_TYPE.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_INTEREST_RATE_TEXTBOX)) {
                this.mdb.IDC_PRINCIPAL_TEXTBOX.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_TERM_TEXTBOX)) {
                this.mdb.IDC_INTEREST_RATE_TEXTBOX.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_PAYMENT_TEXTBOX)) {
                this.mdb.IDC_TERM_TEXTBOX.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_BUTTON_CALCULATE)) {
                this.mdb.IDC_PAYMENT_TEXTBOX.requestFocus();
            }
        }
    }
    
    private void drawGraph(final Graphics myGraphics) {
        int oldxpos = 80;
        int oldyPpos = 0;
        int oldyIpos = 0;
        int newyPpos = 220;
        int newyIpos = 220;
        int xpos = 90;
        boolean firstTime = true;
        int i = 0;
        while (this.graphValues[i][0] != 0.0) {
            int j = 0;
            do {
                if (this.graphValues[i][0] <= this.yLookUptbl[j][0]) {
                    newyPpos = (int)Math.round(this.yLookUptbl[j][1]);
                    j = 120;
                }
            } while (++j < 120);
            j = 0;
            do {
                if (this.graphValues[i][1] <= this.yLookUptbl[j][0]) {
                    newyIpos = (int)Math.round(this.yLookUptbl[j][1]);
                    j = 120;
                }
            } while (++j < 120);
            if (firstTime) {
                oldyPpos = newyPpos;
                oldyIpos = newyIpos;
                firstTime = false;
            }
            myGraphics.setColor(Color.blue);
            myGraphics.drawLine(oldxpos, oldyPpos, xpos, newyPpos);
            myGraphics.setColor(Color.red);
            myGraphics.drawLine(oldxpos, oldyIpos, xpos, newyIpos);
            oldyPpos = newyPpos;
            oldyIpos = newyIpos;
            oldxpos = xpos;
            ++i;
            xpos += 10;
            if (i >= 25) {
                return;
            }
        }
    }
    
    public String getAppletInfo() {
        return "Name: MortgageApplet\r\n" + "Author: David Schell\r\n" + "Copyright Precedent Systems Consultants\r\n" + "Created with Microsoft Visual J++ Version 1.0";
    }
    
    private void setupMortgage() {
        int mortgageType = 1;
        final boolean CalculateUsingTerm = this.mdb.IDC_CALCULATE_USING_TERM.getState();
        if (this.mdb.IDC_MONTHLY_TYPE.getState()) {
            mortgageType = 1;
        }
        else if (this.mdb.IDC_SEMI_MONTHLY_TYPE.getState()) {
            mortgageType = 2;
        }
        else if (this.mdb.IDC_BI_WEEKLY_TYPE.getState()) {
            mortgageType = 3;
        }
        else if (this.mdb.IDC_WEEKLY_TYPE.getState()) {
            mortgageType = 4;
        }
        if (this.mf != null) {
            this.mf = null;
        }
        final double principal = this.getDouble(this.mdb.IDC_PRINCIPAL_TEXTBOX.getText(), "Principal");
        if (principal == -1.0) {
            return;
        }
        final double interest = this.getDouble(this.mdb.IDC_INTEREST_RATE_TEXTBOX.getText(), "Interest Rate");
        if (interest == -1.0) {
            return;
        }
        double term;
        double payment;
        int calculateUsing;
        if (CalculateUsingTerm) {
            term = this.getDouble(this.mdb.IDC_TERM_TEXTBOX.getText(), "Term");
            if (term == -1.0) {
                return;
            }
            payment = 0.0;
            calculateUsing = 1;
        }
        else {
            payment = this.getDouble(this.mdb.IDC_PAYMENT_TEXTBOX.getText(), "Payment");
            if (payment == -1.0) {
                return;
            }
            term = 0.0;
            calculateUsing = 2;
        }
        this.mf = new MortgageFunctions(mortgageType, principal, interest, term, payment, calculateUsing);
        if (CalculateUsingTerm) {
            this.mdb.IDC_PAYMENT_TEXTBOX.setText(Double.toString(MortgageFunctions.Round(this.mf.getPayment(), 2)));
        }
        else {
            this.mdb.IDC_TERM_TEXTBOX.setText(Double.toString(MortgageFunctions.Round(this.mf.getTerm(), 2)));
            if (!this.mf.getWillPaymentCoverInterest()) {
                this.msgbox("Payment Will Not Cover Interest ... Increase Payment");
            }
        }
        this.mdb.IDC_TOTAL_INTEREST_TEXTBOX.setText(this.formatCurrency(MortgageFunctions.Round(this.mf.getTotalInterest(), 2)));
        this.mdb.IDC_TOTAL_COST_TEXTBOX.setText(this.formatCurrency(MortgageFunctions.Round(principal + this.mf.getTotalInterest(), 2)));
        this.graphValues = this.mf.getGraphValues();
        this.repaint();
    }
    
    private void moveNext(final Event evt, final String fieldValue) {
        final boolean moveNext = this.validateFieldValue(evt, fieldValue);
        if (moveNext) {
            if (this.isEventComponent(evt, this.mdb.IDC_MONTHLY_TYPE)) {
                this.mdb.IDC_PRINCIPAL_TEXTBOX.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_SEMI_MONTHLY_TYPE)) {
                this.mdb.IDC_PRINCIPAL_TEXTBOX.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_BI_WEEKLY_TYPE)) {
                this.mdb.IDC_PRINCIPAL_TEXTBOX.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_WEEKLY_TYPE)) {
                this.mdb.IDC_PRINCIPAL_TEXTBOX.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_PRINCIPAL_TEXTBOX)) {
                this.mdb.IDC_INTEREST_RATE_TEXTBOX.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_INTEREST_RATE_TEXTBOX)) {
                this.mdb.IDC_TERM_TEXTBOX.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_TERM_TEXTBOX)) {
                this.mdb.IDC_PAYMENT_TEXTBOX.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_PAYMENT_TEXTBOX)) {
                this.mdb.IDC_BUTTON_CALCULATE.requestFocus();
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_BUTTON_CALCULATE)) {
                this.mdb.IDC_MONTHLY_TYPE.requestFocus();
            }
        }
    }
    
    private void msgbox(final String msg) {
        this.showStatus(msg);
    }
    
    public boolean action(final Event evt, final Object obj) {
        final String fieldValue = (String)obj;
        if (evt.target instanceof Button) {
            if (fieldValue.equals("Calculate")) {
                this.setupMortgage();
                return true;
            }
        }
        else if (evt.target instanceof TextField) {
            this.moveNext(evt, fieldValue);
            return true;
        }
        return false;
    }
    
    private boolean validateFieldValue(final Event evt, final String fieldValue) {
        final int fieldValueLength = fieldValue.length();
        if (fieldValueLength != 0) {
            if (this.getDouble(fieldValue, " ") == -1.0) {
                return false;
            }
        }
        else {
            if (this.isEventComponent(evt, this.mdb.IDC_PRINCIPAL_TEXTBOX)) {
                this.msgbox("Principal is Mandatory");
                return false;
            }
            if (this.isEventComponent(evt, this.mdb.IDC_INTEREST_RATE_TEXTBOX)) {
                this.msgbox("Interest Rate is Mandatory");
                return false;
            }
            if (this.isEventComponent(evt, this.mdb.IDC_TERM_TEXTBOX)) {
                if (this.mdb.IDC_CALCULATE_USING_TERM.getState()) {
                    this.msgbox("Term is Mandatory");
                }
            }
            else if (this.isEventComponent(evt, this.mdb.IDC_PAYMENT_TEXTBOX) && this.mdb.IDC_CALCULATE_USING_PAYMENT.getState()) {
                this.msgbox("Payment is Mandatory");
            }
        }
        return true;
    }
    
    public void destroy() {
    }
    
    public void init() {
        this.mdb.CreateControls();
        this.mdb.IDC_BUTTON_CALCULATE.setForeground(Color.blue);
        this.mdb.IDC_BUTTON_CALCULATE.setFont(new Font("Arial", 1, 18));
        this.mdb.IDC_MONTHLY_TYPE.setState(true);
        this.mdb.IDC_CALCULATE_USING_TERM.setState(true);
        this.mdb.IDC_PRINCIPAL_TEXTBOX.requestFocus();
        double dollarValue = 0.0;
        double yCoordinate = 370.0;
        int i = 0;
        do {
            this.yLookUptbl[i][0] = dollarValue;
            this.yLookUptbl[i][1] = yCoordinate;
            dollarValue += 18.75;
            yCoordinate -= 1.25;
        } while (++i < 120);
        this.mdb.IDC_PRINCIPAL_TEXTBOX.setText("150000");
        this.mdb.IDC_INTEREST_RATE_TEXTBOX.setText("6.75");
        this.mdb.IDC_TERM_TEXTBOX.setText("25");
    }
    
    public void paint(final Graphics g) {
        int pos = 0;
        int i = 0;
        int pb = 0;
        g.drawRect(20, 18, 110, 100);
        g.drawRect(20, 148, 110, 55);
        g.drawLine(80, 220, 80, 370);
        g.drawLine(80, 370, 330, 370);
        g.setFont(new Font("Arial", 1, 12));
        g.drawString("Years", 200, 400);
        g.setFont(new Font("Arial", 1, 12));
        g.setColor(Color.red);
        g.drawString("Interest", 20, 385);
        g.setColor(Color.blue);
        g.drawString("Principal", 20, 400);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", 0, 10));
        pos = 90;
        for (i = 1; i < 26; ++i) {
            g.drawLine(pos, 365, pos, 375);
            if (i % 5 == 0) {
                if (i <= 9) {
                    pb = 2;
                }
                else {
                    pb = 4;
                }
                g.drawString(Integer.toString(i), pos - pb, 385);
            }
            pos += 10;
        }
        g.setFont(new Font("Arial", 0, 10));
        String s = "$  ";
        pos = 360;
        for (i = 1; i < 16; ++i) {
            g.drawLine(75, pos, 85, pos);
            if (i * 150 > 999) {
                s = "$ ";
            }
            g.drawString(s + Integer.toString(i * 150), 35, pos + 5);
            pos -= 10;
        }
        this.drawGraph(g);
    }
}
