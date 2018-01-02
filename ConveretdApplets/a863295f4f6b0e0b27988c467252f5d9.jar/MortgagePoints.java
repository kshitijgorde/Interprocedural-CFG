import KJEgui.Fmt;
import KJEgraph.GraphDataSeries;
import KJEgraph.GraphStacked;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import KJEgraph.GraphType;
import KJEgraph.GraphLine;
import java.awt.Component;
import KJEgui.DataPanel;
import KJEcalculation.Calculation;
import java.awt.CheckboxGroup;
import java.applet.Applet;
import KJEgraph.Graph;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Choice;
import KJEgui.Nbr;
import KJEgui.CalculatorApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MortgagePoints extends CalculatorApplet
{
    MortgagePointsCalculation RC;
    Nbr tfINTEREST_RATE;
    Choice cmbTERM;
    Nbr tfLOAN_AMOUNT;
    Nbr tfDISCOUNT_POINTS_PERCENT;
    Nbr tfDISCOUNT_POINTS_RATE;
    Nbr tfYEARS_IN_HOME;
    Label lbLOAN_AMOUNT;
    Label lbINTEREST_PAID;
    Label lbTOTAL_OF_PAYMENTS;
    Label lbMONTHLY_PI;
    Label lbPOINTS_MONTHLY_PI;
    Label lbPOINTS_INTEREST_SAVINGS;
    Label lbDISCOUNT_POINTS_DIFFERENCE;
    Label lbDOWNPAYMENT_LOAN_AMOUNT;
    Label lbDISCOUNT_POINTS_AMT;
    Checkbox cbPRINCIPAL;
    Checkbox cbAMTS_PAID;
    Graph gGraph;
    Checkbox cbYEAR;
    Checkbox cbMONTH;
    String MSG_GRAPH1;
    String MSG_GRAPH2;
    String MSG_GRAPH3;
    String MSG_GRAPH4;
    String MSG_GRAPH5;
    String MSG_GRAPH_TITLE1;
    
    public MortgagePoints() {
        this.RC = new MortgagePointsCalculation();
        this.lbLOAN_AMOUNT = new Label("");
        this.lbINTEREST_PAID = new Label("");
        this.lbTOTAL_OF_PAYMENTS = new Label("");
        this.lbMONTHLY_PI = new Label("");
        this.lbPOINTS_MONTHLY_PI = new Label("");
        this.lbPOINTS_INTEREST_SAVINGS = new Label("");
        this.lbDISCOUNT_POINTS_DIFFERENCE = new Label("");
        this.lbDOWNPAYMENT_LOAN_AMOUNT = new Label("");
        this.lbDISCOUNT_POINTS_AMT = new Label("");
    }
    
    public void initCalculatorApplet() {
        this.RC.totalpaid_cats[0] = this.getParameter("MSG_CAT_LBL1", this.RC.totalpaid_cats[0]);
        this.RC.totalpaid_cats[1] = this.getParameter("MSG_CAT_LBL2", this.RC.totalpaid_cats[1]);
        this.RC.TITLE_MESSAGE1 = this.getParameter("TITLE_MESSAGE1", this.RC.TITLE_MESSAGE1);
        this.RC.TITLE_MESSAGE2 = this.getParameter("TITLE_MESSAGE2", this.RC.TITLE_MESSAGE2);
        this.RC.RESULTS_START1 = this.getParameter("RESULTS_START1", this.RC.RESULTS_START1);
        this.RC.RESULTS_START2 = this.getParameter("RESULTS_START2", this.RC.RESULTS_START2);
        this.RC.RESULTS_MIDDLE1 = this.getParameter("RESULTS_MIDDLE1", this.RC.RESULTS_MIDDLE1);
        this.RC.RESULTS_MIDDLE2 = this.getParameter("RESULTS_MIDDLE2", this.RC.RESULTS_MIDDLE2);
        this.RC.RESULTS_END1 = this.getParameter("RESULTS_END1", this.RC.RESULTS_END1);
        this.RC.RESULTS_END2 = this.getParameter("RESULTS_END2", this.RC.RESULTS_END2);
        this.MSG_GRAPH_TITLE1 = this.getParameter("MSG_GRAPH_TITLE1", "Totals for YEARS_IN_HOME years, in thousands");
        this.MSG_GRAPH1 = this.getParameter("MSG_GRAPH1", "Principal");
        this.MSG_GRAPH2 = this.getParameter("MSG_GRAPH2", "Interest");
        this.MSG_GRAPH3 = this.getParameter("MSG_GRAPH3", "Payment Number");
        this.MSG_GRAPH4 = this.getParameter("MSG_GRAPH4", "Principal Balance");
        this.MSG_GRAPH5 = this.getParameter("MSG_GRAPH5", "Year Number");
        this.tfINTEREST_RATE = new Nbr("INTEREST_RATE", "Interest rate", 1.0, 25.0, 3, 4, this);
        this.tfLOAN_AMOUNT = new Nbr("LOAN_AMOUNT", "Amount", 100.0, 1.0E8, 0, 3, this);
        this.tfDISCOUNT_POINTS_PERCENT = new Nbr("DISCOUNT_POINTS_PERCENT", "Discount points", -25.0, 25.0, 3, 5, this);
        this.tfDISCOUNT_POINTS_RATE = new Nbr("DISCOUNT_POINTS_RATE", "Points rate", 0.0, 25.0, 3, 4, this);
        this.tfYEARS_IN_HOME = new Nbr("YEARS_IN_HOME", "Years in home", 1.0, 30.0, 0, 5, this);
        super.nStack = 1;
        super.bUseNorth = false;
        super.bUseSouth = true;
        super.bUseWest = true;
        super.bUseEast = false;
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.cbPRINCIPAL = new Checkbox(this.getParameter("MSG_LABEL1", "Principal balances"), checkboxGroup, false);
        this.cbAMTS_PAID = new Checkbox(this.getParameter("MSG_LABEL2", "Total payments"), checkboxGroup, true);
        final CheckboxGroup checkboxGroup2 = new CheckboxGroup();
        this.cbYEAR = new Checkbox(this.getParameter("MSG_LABEL3", "Report amortization schedule by year"), checkboxGroup2, true);
        this.cbMONTH = new Checkbox(this.getParameter("MSG_LABEL4", "Report amortization schedule by Month"), checkboxGroup2, false);
        this.cbYEAR.setBackground(this.getBackground());
        this.cbMONTH.setBackground(this.getBackground());
        this.setCalculation(this.RC);
        this.cmbTERM = this.getMortgageTermChoice(this.getParameter("TERM", 30));
    }
    
    public void initPanels() {
        final DataPanel dataPanel = new DataPanel();
        final DataPanel dataPanel2 = new DataPanel();
        final DataPanel dataPanel3 = new DataPanel();
        dataPanel.setBackground(this.getColor(1));
        dataPanel.addRow(new Label("  " + this.getParameter("MSG_LABEL5", "Loan and points information") + super._COLON), this.getBoldFont(), 1);
        dataPanel.addRow(this.tfLOAN_AMOUNT, this.getPlainFont(), 2);
        dataPanel.addRow(String.valueOf(this.getParameter("MSG_TERM", "Term")) + super._COLON, this.cmbTERM, this.getPlainFont(), 3);
        dataPanel.addRow(this.tfDISCOUNT_POINTS_RATE, this.getPlainFont(), 4);
        dataPanel.addRow(this.tfDISCOUNT_POINTS_PERCENT, this.getPlainFont(), 5);
        dataPanel.addRow(this.tfYEARS_IN_HOME, this.getPlainFont(), 6);
        dataPanel.addRow(String.valueOf(this.getParameter("MSG_MONTHLY_PAYMENT", "Monthly payment")) + super._COLON, this.lbPOINTS_MONTHLY_PI, this.getPlainFont(), 7);
        dataPanel.addRow(String.valueOf(this.getParameter("MSG_DISCOUNT_POINTS_AMT", "Fee paid for points")) + super._COLON, this.lbDISCOUNT_POINTS_AMT, this.getPlainFont(), 8);
        dataPanel.addRow("", new Label((super.iBrowser == 2) ? "          " : "                                                                                                               "), this.getTinyFont(), 9);
        dataPanel.addRow(new Label("  " + this.getParameter("MSG_LABEL6", "Loan without points") + super._COLON), this.getBoldFont(), 10);
        dataPanel.addRow(String.valueOf(this.getParameter("MSG_LABEL7", "Amount")) + super._COLON, this.lbDOWNPAYMENT_LOAN_AMOUNT, this.getPlainFont(), 11);
        dataPanel.addRow(this.tfINTEREST_RATE, this.getPlainFont(), 12);
        dataPanel.addRow(String.valueOf(this.getParameter("MSG_MONTHLY_PAYMENT", "Monthly payment")) + super._COLON, this.lbMONTHLY_PI, this.getPlainFont(), 13);
        dataPanel.addRow(String.valueOf(this.getParameter("MSG_LABEL7", "Difference")) + super._COLON, this.lbDISCOUNT_POINTS_DIFFERENCE, this.getPlainFont(), 14);
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
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add("North", dataPanel3);
        panel.add("Center", this.gGraph);
        panel.setBackground(this.getColor(2));
        final Panel panel2 = new Panel();
        panel2.setBackground(this.getColor(1));
        panel2.setLayout(new BorderLayout());
        panel2.add("Center", dataPanel);
        panel2.add("East", new Label(""));
        this.addPanel(panel2);
        this.addDataPanel(dataPanel2);
        this.addPanel(panel);
    }
    
    public void refresh() {
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
                this.gGraph.add(new GraphDataSeries(this.RC.DS_PRINCIPAL, this.MSG_GRAPH1, this.getGraphColor(1)));
                this.gGraph.add(new GraphDataSeries(this.RC.DS_INTEREST, this.MSG_GRAPH2, this.getGraphColor(2)));
                this.gGraph._axisY._bAutoMinimum = false;
                this.gGraph._axisY._axisMinimum = 0.0f;
                this.gGraph._axisY._bAutoMaximum = false;
                this.gGraph._axisY._axisMaximum = (float)this.RC.GRAPH_MAXIMUM;
            }
            catch (Exception ex) {
                System.out.println("Huh?");
            }
            this.gGraph._titleYAxis.setText("");
            this.gGraph._titleGraph.setText(Calculation.replace("YEARS_IN_HOME", Fmt.number(this.RC.YEARS_IN_HOME), this.MSG_GRAPH_TITLE1));
            this.gGraph.dataChanged(true);
        }
        else {
            this.gGraph.setBackground(this.getColor(2));
            this.gGraph.removeAll();
            this.gGraph._legend.setVisible(true);
            this.gGraph._legend.setOrientation(12);
            this.gGraph._titleXAxis.setText(this.MSG_GRAPH3);
            this.gGraph._titleGraph.setText("");
            this.gGraph._titleYAxis.setText(this.MSG_GRAPH4);
            this.gGraph._axisX.setVisible(true);
            this.gGraph._axisY.setVisible(true);
            this.gGraph.setGraphType(new GraphLine());
            this.gGraph._bUseTextImages = false;
            this.gGraph.setGraphCatagories(this.RC.getCatagories());
            try {
                this.gGraph.add(new GraphDataSeries(this.RC.DS_POINTS_PRINCIPAL_BAL, this.RC.totalpaid_cats[0], this.getGraphColor(2)));
                this.gGraph.add(new GraphDataSeries(this.RC.DS_PRINCIPAL_BAL, this.RC.totalpaid_cats[1], this.getGraphColor(1)));
                this.gGraph._axisY._bAutoMinimum = true;
                this.gGraph._axisY._bAutoMaximum = true;
                this.gGraph._axisY._axisMinimum = 0.0f;
            }
            catch (Exception ex2) {
                System.out.println("Huh?");
            }
            this.gGraph._titleYAxis.setText(this.RC.getAmountLabel());
            this.gGraph._titleGraph.setText("");
            this.gGraph.dataChanged(true);
            this.gGraph._titleXAxis.setText(this.MSG_GRAPH5);
        }
        this.lbDISCOUNT_POINTS_AMT.setText(Fmt.dollars(this.RC.DISCOUNT_POINTS_AMT));
        this.lbDOWNPAYMENT_LOAN_AMOUNT.setText(Fmt.dollars(this.RC.DOWNPAYMENT_LOAN_AMOUNT));
        this.lbMONTHLY_PI.setText(Fmt.dollars(this.RC.DOWNPAYMENT_MONTHLY_PI, 2));
        this.lbLOAN_AMOUNT.setText(Fmt.dollars(this.RC.LOAN_AMOUNT));
        this.lbPOINTS_MONTHLY_PI.setText(Fmt.dollars(this.RC.POINTS_MONTHLY_PI, 2));
        this.lbDISCOUNT_POINTS_DIFFERENCE.setText(Fmt.dollars(this.RC.DISCOUNT_POINTS_DIFFERENCE, 2));
        this.setTitle(this.RC.TITLE_MESSAGE);
    }
    
    public void setValues() throws NumberFormatException {
        this.RC.INTEREST_RATE = this.tfINTEREST_RATE.toDouble();
        this.RC.TERM = this.getMortgageTerm(this.cmbTERM);
        this.RC.LOAN_AMOUNT = this.tfLOAN_AMOUNT.toDouble();
        this.RC.DISCOUNT_POINTS_RATE = this.tfDISCOUNT_POINTS_RATE.toDouble();
        this.RC.DISCOUNT_POINTS_PERCENT = this.tfDISCOUNT_POINTS_PERCENT.toDouble();
        this.RC.YEARS_IN_HOME = this.tfYEARS_IN_HOME.toDouble();
        if (this.cbYEAR.getState()) {
            this.RC.BY_YEAR = 1;
        }
        else {
            this.RC.BY_YEAR = 0;
        }
    }
}
