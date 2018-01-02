import KJEgui.Fmt;
import KJEgraph.GraphDataSeries;
import KJEgraph.GraphStacked;
import KJEgraph.GraphType;
import KJEgraph.GraphLine;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Component;
import KJEgui.DataPanel;
import KJEcalculation.Calculation;
import java.awt.CheckboxGroup;
import java.applet.Applet;
import KJEgraph.Graph;
import java.awt.Checkbox;
import java.awt.Label;
import KJEgui.KJEChoice;
import java.awt.Choice;
import KJEgui.Nbr;
import KJEgui.CalculatorApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MortgageLoan extends CalculatorApplet
{
    MortgageLoanCalculation RC;
    Nbr tfINTEREST_RATE;
    Choice cmbTERM;
    Nbr tfTERM;
    boolean bAllTerms;
    Nbr tfLOAN_AMOUNT;
    Nbr tfPREPAY_AMOUNT;
    Nbr tfPREPAY_STARTS_WITH;
    Nbr tfPAYMENT_PI;
    Nbr tfYEARLY_PROPERTY_TAXES;
    Nbr tfYEARLY_HOME_INSURANCE;
    KJEChoice cmbPREPAY_TYPE;
    Label lbLOAN_AMOUNT;
    Label lbINTEREST_PAID;
    Label lbTOTAL_OF_PAYMENTS;
    Label lbMONTHLY_PI;
    Label lbMONTHLY_PITI;
    Label lbPREPAY_INTEREST_SAVINGS;
    Checkbox cbPRINCIPAL;
    Checkbox cbAMTS_PAID;
    Graph gGraph;
    Checkbox cbYEAR;
    Checkbox cbMONTH;
    
    public MortgageLoan() {
        this.RC = new MortgageLoanCalculation();
        this.cmbPREPAY_TYPE = new KJEChoice();
        this.lbLOAN_AMOUNT = new Label("");
        this.lbINTEREST_PAID = new Label("");
        this.lbTOTAL_OF_PAYMENTS = new Label("");
        this.lbMONTHLY_PI = new Label("");
        this.lbMONTHLY_PITI = new Label("");
        this.lbPREPAY_INTEREST_SAVINGS = new Label("");
    }
    
    public double getPayment() {
        this.calculate();
        return this.RC.MONTHLY_PI;
    }
    
    public void initCalculatorApplet() {
        this.tfINTEREST_RATE = new Nbr("INTEREST_RATE", "Interest rate", 1.0, 25.0, 3, 4, this);
        this.tfTERM = new Nbr("TERM", "Term", 0.0, 30.0, 0, 5, this);
        this.tfLOAN_AMOUNT = new Nbr("LOAN_AMOUNT", "Mortgage amount", 100.0, 1.0E8, 0, 3, this);
        this.tfPREPAY_STARTS_WITH = new Nbr("PREPAY_STARTS_WITH", "Start with payment", 0.0, 360.0, 0, 5, this);
        this.tfPREPAY_AMOUNT = new Nbr("PREPAY_AMOUNT", "Amount", 0.0, 1000000.0, 0, 3, this);
        if (this.getParameter("PAYMENT_PI") == null) {
            this.tfPAYMENT_PI = new Nbr(0.0, this.getParameter("MSG_PAYMENT_PI", "Payment"), 0.0, 10000.0, 0, 3);
        }
        else {
            this.tfPAYMENT_PI = new Nbr("PAYMENT_PI", "Payment", 0.0, 10000.0, 0, 3, this);
        }
        this.tfYEARLY_PROPERTY_TAXES = new Nbr("YEARLY_PROPERTY_TAXES", "Annual property taxes", 0.0, 100000.0, 0, 3, this);
        this.tfYEARLY_HOME_INSURANCE = new Nbr("YEARLY_HOME_INSURANCE", "Annual home insurance", 0.0, 100000.0, 0, 3, this);
        super.nStack = 1;
        super.bUseNorth = false;
        super.bUseSouth = true;
        super.bUseWest = true;
        super.bUseEast = false;
        this.RC.PREPAY_NONE = this.getParameter("MSG_PREPAY_NONE", this.RC.PREPAY_NONE);
        this.RC.PREPAY_MONTHLY = this.getParameter("MSG_PREPAY_MONTHLY", this.RC.PREPAY_MONTHLY);
        this.RC.PREPAY_YEARLY = this.getParameter("MSG_PREPAY_YEARLY", this.RC.PREPAY_YEARLY);
        this.RC.PREPAY_ONETIME = this.getParameter("MSG_PREPAY_ONETIME", this.RC.PREPAY_ONETIME);
        this.RC.MSG_YEAR_NUMBER = this.getParameter("MSG_YEAR_NUMBER", this.RC.MSG_YEAR_NUMBER);
        this.RC.MSG_PRINCIPAL = this.getParameter("MSG_PRINCIPAL", this.RC.MSG_PRINCIPAL);
        this.RC.MSG_INTEREST = this.getParameter("MSG_INTEREST", this.RC.MSG_INTEREST);
        this.RC.MSG_PAYMENT_NUMBER = this.getParameter("MSG_PAYMENT_NUMBER", this.RC.MSG_PAYMENT_NUMBER);
        this.RC.MSG_PRINCIPAL_BALANCE = this.getParameter("MSG_PRINCIPAL_BALANCE", this.RC.MSG_PRINCIPAL_BALANCE);
        this.RC.MSG_PREPAYMENTS = this.getParameter("MSG_PREPAYMENTS", this.RC.MSG_PREPAYMENTS);
        this.RC.MSG_NORMAL_PAYMENTS = this.getParameter("MSG_NORMAL_PAYMENTS", this.RC.MSG_NORMAL_PAYMENTS);
        this.RC.MSG_PREPAY_MESSAGE = this.getParameter("MSG_PREPAY_MESSAGE", this.RC.MSG_PREPAY_MESSAGE);
        this.RC.MSG_RETURN_AMOUNT = this.getParameter("MSG_RETURN_AMOUNT", this.RC.MSG_RETURN_AMOUNT);
        this.RC.MSG_RETURN_PAYMENT = this.getParameter("MSG_RETURN_PAYMENT", this.RC.MSG_RETURN_PAYMENT);
        this.RC.MSG_GRAPH_COL1 = this.getParameter("MSG_GRAPH_COL1", this.RC.MSG_GRAPH_COL1);
        this.RC.MSG_GRAPH_COL2 = this.getParameter("MSG_GRAPH_COL2", this.RC.MSG_GRAPH_COL2);
        this.cmbPREPAY_TYPE.addItem(this.RC.PREPAY_NONE);
        this.cmbPREPAY_TYPE.addItem(this.RC.PREPAY_MONTHLY);
        this.cmbPREPAY_TYPE.addItem(this.RC.PREPAY_YEARLY);
        this.cmbPREPAY_TYPE.addItem(this.RC.PREPAY_ONETIME);
        this.cmbPREPAY_TYPE.select(this.getParameter("PREPAY_TYPE", this.RC.PREPAY_NONE));
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.cbPRINCIPAL = new Checkbox(this.getParameter("MSG_CHKBOX_PRINCIPAL_BAL", "Principal balances"), checkboxGroup, true);
        this.cbAMTS_PAID = new Checkbox(this.getParameter("MSG_CHKBOX_TOTAL_PAYMENTS", "Total payments"), checkboxGroup, false);
        final CheckboxGroup checkboxGroup2 = new CheckboxGroup();
        this.cbYEAR = new Checkbox(this.getParameter("MSG_CHKBOX_BY_YEAR", "Report amortization schedule by year"), checkboxGroup2, true);
        this.cbMONTH = new Checkbox(this.getParameter("MSG_CHKBOX_BY_MONTH", "Report amortization schedule by Month"), checkboxGroup2, false);
        this.cbYEAR.setBackground(this.getBackground());
        this.cbMONTH.setBackground(this.getBackground());
        this.setCalculation(this.RC);
        this.cmbTERM = this.getMortgageTermChoice(this.getParameter("TERM", 30));
    }
    
    public void initPanels() {
        final DataPanel dataPanel = new DataPanel();
        final DataPanel dataPanel2 = new DataPanel();
        final DataPanel dataPanel3 = new DataPanel();
        int n = 1;
        dataPanel.setBackground(this.getColor(1));
        final boolean parameter = this.getParameter("SHOW_PITI", false);
        final boolean parameter2 = this.getParameter("SHOW_PREPAY", true);
        dataPanel.addRow(new Label("  " + this.getParameter("MSG_LOAN_INFORMATION", "Loan Information") + super._COLON), this.getBoldFont(), n++);
        dataPanel.addRow(this.tfLOAN_AMOUNT, this.getPlainFont(), n++);
        this.bAllTerms = this.getParameter("SHOW_ALLTERMS", false);
        if (this.bAllTerms) {
            dataPanel.addRow(this.tfTERM, this.getPlainFont(), n++);
        }
        else {
            dataPanel.addRow(String.valueOf(this.getParameter("MSG_TERM", "Term")) + super._COLON, this.cmbTERM, this.getPlainFont(), n++);
        }
        dataPanel.addRow(this.tfINTEREST_RATE, this.getPlainFont(), n++);
        if (parameter) {
            dataPanel.addRow(this.tfYEARLY_PROPERTY_TAXES, this.getPlainFont(), n++);
            dataPanel.addRow(this.tfYEARLY_HOME_INSURANCE, this.getPlainFont(), n++);
        }
        if (parameter) {
            dataPanel.addRow(String.valueOf(this.getParameter("MSG_MONTHLY_PAYMENT", "Monthly payment (PI)")) + " " + super._COLON, this.lbMONTHLY_PI, this.getPlainFont(), n++);
            dataPanel.addRow(String.valueOf(this.getParameter("MSG_PITI", "Monthly payment (PITI)")) + " " + super._COLON, this.lbMONTHLY_PITI, this.getPlainFont(), n++);
        }
        else {
            dataPanel.addRow(String.valueOf(this.getParameter("MSG_MONTHLY_PAYMENT", "Monthly payment")) + " " + super._COLON, this.lbMONTHLY_PI, this.getPlainFont(), n++);
        }
        if (!parameter || !parameter2) {
            dataPanel.addRow(String.valueOf(this.getParameter("MSG_TOTAL_PAYMENTS", "Total payments")) + " " + super._COLON, this.lbTOTAL_OF_PAYMENTS, this.getPlainFont(), n++);
            dataPanel.addRow(String.valueOf(this.getParameter("MSG_TOTAL_INTEREST", "Total interest")) + " " + super._COLON, this.lbINTEREST_PAID, this.getPlainFont(), n++);
        }
        dataPanel.addRow("", new Label(""), this.getTinyFont(), n++);
        if (parameter2) {
            dataPanel.addRow(new Label("  " + this.getParameter("MSG_PREPAYMENTS", "Prepayments") + " " + super._COLON), this.getBoldFont(), n++);
            dataPanel.addRow(String.valueOf(this.getParameter("MSG_PREPAYMENT_TYPE", "Type")) + " " + super._COLON, this.cmbPREPAY_TYPE, this.getPlainFont(), n++);
            dataPanel.addRow(this.tfPREPAY_AMOUNT, this.getPlainFont(), n++);
            dataPanel.addRow(this.tfPREPAY_STARTS_WITH, this.getPlainFont(), n++);
            dataPanel.addRow(String.valueOf(this.getParameter("MSG_PREPAYMENT_SAVINGS", "Savings")) + " " + super._COLON, this.lbPREPAY_INTEREST_SAVINGS, this.getPlainFont(), n++);
        }
        final Panel panel = new Panel();
        panel.setBackground(this.getColor(1));
        panel.setLayout(new BorderLayout());
        panel.add("Center", dataPanel);
        panel.add("East", new Label(""));
        this.cbPRINCIPAL.setBackground(this.getColor(2));
        this.cbAMTS_PAID.setBackground(this.getColor(2));
        dataPanel3.setBackground(this.getColor(2));
        dataPanel2.addRow("", this.cbYEAR, "", this.cbMONTH, this.getPlainFont(), 1);
        dataPanel3.addRow("", this.cbPRINCIPAL, "", this.cbAMTS_PAID, this.getPlainFont(), 1);
        this.gGraph = new Graph(new GraphLine(), this.imageBackground());
        this.gGraph.FONT_TITLE = this.getGraphTitleFont();
        this.gGraph.FONT_BOLD = this.getBoldFont();
        this.gGraph.FONT_PLAIN = this.getPlainFont();
        this.gGraph.setBackground(this.getColor(2));
        this.gGraph.setForeground(this.getForeground());
        final Panel panel2 = new Panel();
        panel2.setLayout(new BorderLayout());
        if (!this.getParameter("HIDE_EXTRA_GRAPHS", false)) {
            panel2.add("North", dataPanel3);
        }
        panel2.add("Center", this.gGraph);
        panel2.setBackground(this.getColor(2));
        this.addPanel(panel);
        this.addDataPanel(dataPanel2);
        this.addPanel(panel2);
    }
    
    public void refresh() {
        this.gGraph._bUseTextImages = false;
        if (this.cbAMTS_PAID.getState()) {
            this.gGraph.setBackground(this.getColor(2));
            this.gGraph.removeAll();
            this.gGraph._legend.setVisible(true);
            this.gGraph._legend.setOrientation(14);
            this.gGraph._titleXAxis.setText("");
            this.gGraph._titleGraph.setText("");
            this.gGraph._axisX.setVisible(true);
            this.gGraph._axisY.setVisible(true);
            this.gGraph.setGraphType(new GraphStacked());
            this.gGraph.setGraphCatagories(this.RC.getAmountPaidCatagories());
            try {
                this.gGraph.add(new GraphDataSeries(this.RC.DS_PRINCIPAL, this.RC.MSG_PRINCIPAL, this.getGraphColor(1)));
                this.gGraph.add(new GraphDataSeries(this.RC.DS_INTEREST, this.RC.MSG_INTEREST, this.getGraphColor(2)));
                this.gGraph._axisY._bAutoMinimum = false;
                this.gGraph._axisY._axisMinimum = 0.0f;
                this.gGraph._axisY._bAutoMaximum = false;
                this.gGraph._axisY._axisMaximum = (float)(this.RC.TOTAL_OF_PAYMENTS / this.RC.iFactor2);
            }
            catch (Exception ex) {
                System.out.println("Huh?");
            }
            this.gGraph._titleYAxis.setText("");
            this.gGraph._titleGraph.setText(this.RC.getAmountLabel2());
            this.gGraph.dataChanged(true);
        }
        else {
            this.gGraph.setBackground(this.getColor(2));
            this.gGraph.removeAll();
            this.gGraph._legend.setVisible(true);
            this.gGraph._legend.setOrientation(4);
            this.gGraph._titleXAxis.setText(this.RC.MSG_PAYMENT_NUMBER);
            this.gGraph._titleGraph.setText("");
            this.gGraph._titleYAxis.setText(this.RC.MSG_PRINCIPAL_BALANCE);
            this.gGraph._axisX.setVisible(true);
            this.gGraph._axisY.setVisible(true);
            this.gGraph.setGraphType(new GraphLine());
            this.gGraph.setGraphCatagories(this.RC.getCatagories());
            try {
                if (this.cmbPREPAY_TYPE.getSelectedItem().equals(this.RC.PREPAY_NONE)) {
                    this.gGraph.add(new GraphDataSeries(this.RC.DS_PRINCIPAL_BAL, this.RC.MSG_NORMAL_PAYMENTS, this.getGraphColor(1)));
                }
                else {
                    this.gGraph.add(new GraphDataSeries(this.RC.DS_PREPAY_PRINCIPAL_BAL, this.RC.MSG_PREPAYMENTS, this.getGraphColor(2)));
                    this.gGraph.add(new GraphDataSeries(this.RC.DS_PRINCIPAL_BAL, this.RC.MSG_NORMAL_PAYMENTS, this.getGraphColor(1)));
                }
                this.gGraph._axisY._bAutoMinimum = false;
                this.gGraph._axisY._bAutoMaximum = true;
                this.gGraph._axisY._axisMinimum = 0.0f;
            }
            catch (Exception ex2) {
                System.out.println("Huh?");
            }
            this.gGraph._titleYAxis.setText(this.RC.getAmountLabel());
            this.gGraph._titleGraph.setText("");
            this.gGraph.dataChanged(true);
            this.gGraph._titleXAxis.setText(this.RC.MSG_YEAR_NUMBER);
        }
        this.lbMONTHLY_PITI.setText(Fmt.dollars(this.RC.MONTHLY_PITI, 2));
        this.lbMONTHLY_PI.setText(Fmt.dollars(this.RC.MONTHLY_PI, 2));
        this.lbLOAN_AMOUNT.setText(Fmt.dollars(this.RC.LOAN_AMOUNT));
        this.lbTOTAL_OF_PAYMENTS.setText(Fmt.dollars(this.RC.PREPAY_TOTAL_OF_PAYMENTS, 2));
        this.lbINTEREST_PAID.setText(Fmt.dollars(this.RC.PREPAY_INTEREST_PAID, 2));
        this.lbPREPAY_INTEREST_SAVINGS.setText(Fmt.dollars(this.RC.PREPAY_INTEREST_SAVINGS, 2));
        this.setTitle("Fixed Mortgage Loan Calculator");
    }
    
    public void setValues() throws NumberFormatException {
        this.RC.PREPAY_TYPE = this.cmbPREPAY_TYPE.getSelectedItem();
        this.RC.PREPAY_AMOUNT = this.tfPREPAY_AMOUNT.toDouble();
        this.RC.PREPAY_STARTS_WITH = this.tfPREPAY_STARTS_WITH.toDouble();
        this.RC.INTEREST_RATE = this.tfINTEREST_RATE.toDouble();
        this.RC.YEARLY_PROPERTY_TAXES = this.tfYEARLY_PROPERTY_TAXES.toDouble();
        this.RC.YEARLY_HOME_INSURANCE = this.tfYEARLY_HOME_INSURANCE.toDouble();
        if (this.bAllTerms) {
            this.RC.TERM = (int)this.tfTERM.toDouble();
        }
        else {
            this.RC.TERM = this.getMortgageTerm(this.cmbTERM);
        }
        this.RC.LOAN_AMOUNT = this.tfLOAN_AMOUNT.toDouble();
        if (this.cbYEAR.getState()) {
            this.RC.BY_YEAR = 1;
        }
        else {
            this.RC.BY_YEAR = 0;
        }
        this.RC.MONTHLY_PI = this.tfPAYMENT_PI.toDouble();
        if (this.RC.MONTHLY_PI > 0.0) {
            this.RC.PAYMENT_CALC = 0;
        }
        else {
            this.RC.PAYMENT_CALC = 1;
        }
    }
}
