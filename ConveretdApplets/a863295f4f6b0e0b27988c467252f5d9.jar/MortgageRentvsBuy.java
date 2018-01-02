import KJEgraph.GraphCatagories;
import KJEgraph.GraphLine;
import KJEgui.Fmt;
import KJEgraph.GraphDataSeries;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import KJEgraph.GraphType;
import KJEgraph.GraphColumn;
import java.awt.Component;
import KJEgui.DataPanel;
import KJEcalculation.Calculation;
import java.applet.Applet;
import java.awt.CheckboxGroup;
import KJEgraph.Graph;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Checkbox;
import KJEgui.Nbr;
import KJEgui.CalculatorApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MortgageRentvsBuy extends CalculatorApplet
{
    MortgageRentvsBuyCalc RC;
    Nbr tfPRICE_OF_HOME;
    Nbr tfDOWNPAYMENT_CLOSING_CASH;
    Nbr tfPROPERTY_TAX_RATE;
    Nbr tfHOME_INSURANCE_RATE;
    Nbr tfINTEREST_RATE;
    Nbr tfLOAN_ORIGINATION_RATE;
    Nbr tfLOAN_ORIGINATION_AMT;
    Nbr tfPOINTS_PAID_NBR;
    Nbr tfPMI_RATE;
    Nbr tfOTHER_CLOSING_COSTS;
    Nbr tfTAX_RATE;
    Nbr tfMONTHLY_RENT;
    Nbr tfINFLATION_RATE;
    Nbr tfINVESTMENT_RETURN;
    Nbr tfHOME_APPRECIATION_RATE;
    Nbr tfHOME_COMMISION_RATE;
    Nbr tfPROPERTY_TAX_AMT;
    Checkbox cbNEW_HOME_PURCHASE;
    Checkbox cbLABEL_FLEX_DOWN;
    public String MSG_DOWNPAYMENT;
    public String MSG_REMAINING_CASH;
    public String MSG_GRAPHTITLE_START;
    public String MSG_GRAPH_TITLE_END;
    public String MSG_AXIS_TITLE;
    public String MSG_GRAPH1;
    public String MSG_GRAPH2;
    public String MSG_GRAPH3;
    public String MSG_GRAPH4;
    public String MSG_GRAPH5;
    public String MSG_GRAPH6;
    public String MSG_GRAPH7;
    public String MSG_GRAPH8;
    public String MSG_GRAPH9;
    public String MSG_GRAPH10;
    public String MSG_GRAPH11;
    public String MSG_GRAPH12;
    public String MSG_GRAPH_START1;
    public String MSG_GRAPH_START2;
    public String MSG_GRAPH_END;
    Label tfHOME_INSURANCE_AMT;
    Label lbPROPERTY_TAX_AMT;
    Label lbLOAN_ORIGINATION_AMT;
    Label tfPOINTS_PAID_AMT;
    Label tfTOTAL_CLOSING_COSTS;
    Label tfTOTAL_FOR_DOWNPAYMENT;
    Label tfLOAN_AMOUNT;
    Label tfDOWNPAYMENT_20;
    Label tfCLOSING_CLOSING_COSTS_20;
    Label tfHOME_INSURANCE_MONTHLY;
    Label tfPROPERY_TAX_MONTHLY;
    Label tfMONTHLY_PI;
    Label tfMONTHLY_PMI;
    Label tfMONTHLY_TOTAL_PMT;
    Label tfTOTAL_CMHC;
    Choice cmbTERM;
    Nbr tfMAINTENANCE;
    Graph gGraph;
    Checkbox cbYEAR;
    Checkbox cbMONTH;
    Checkbox cbMAXIMUM20;
    Checkbox cbPAYMENT;
    Checkbox cbAMTS_PAID;
    Checkbox cbINVESTMENT;
    Checkbox cbCASH;
    
    public MortgageRentvsBuy() {
        this.RC = new MortgageRentvsBuyCalc();
        this.tfHOME_INSURANCE_AMT = new Label("");
        this.lbPROPERTY_TAX_AMT = new Label("");
        this.lbLOAN_ORIGINATION_AMT = new Label("");
        this.tfPOINTS_PAID_AMT = new Label("");
        this.tfTOTAL_CLOSING_COSTS = new Label("");
        this.tfTOTAL_FOR_DOWNPAYMENT = new Label("");
        this.tfLOAN_AMOUNT = new Label("");
        this.tfDOWNPAYMENT_20 = new Label("");
        this.tfCLOSING_CLOSING_COSTS_20 = new Label("");
        this.tfHOME_INSURANCE_MONTHLY = new Label("");
        this.tfPROPERY_TAX_MONTHLY = new Label("");
        this.tfMONTHLY_PI = new Label("");
        this.tfMONTHLY_PMI = new Label("");
        this.tfMONTHLY_TOTAL_PMT = new Label("");
        this.tfTOTAL_CMHC = new Label("");
    }
    
    public void initCalculatorApplet() {
        this.cbNEW_HOME_PURCHASE = new Checkbox(this.getParameter("MSG_CHECK_FOR_GST", "Check for GST"), null, false);
        this.cbLABEL_FLEX_DOWN = new Checkbox(this.getParameter("MSG_LABEL_FLEX_DOWN1", "Check for Flex Down"), null, this.getParameter("FLEX_DOWN", false));
        this.MSG_GRAPHTITLE_START = this.getParameter("MSG_GRAPHTITLE_START", "Use of");
        this.MSG_GRAPH_TITLE_END = this.getParameter("MSG_GRAPH_TITLE_END", "Cash on Hand");
        this.MSG_AXIS_TITLE = this.getParameter("MSG_AXIS_TITLE", "Downpayment and Closing Costs");
        this.MSG_DOWNPAYMENT = this.getParameter("MSG_DOWNPAYMENT", "Downpayment");
        this.MSG_REMAINING_CASH = this.getParameter("MSG_REMAINING_CASH", "Remaining cash");
        this.MSG_GRAPH1 = this.getParameter("MSG_GRAPH1", "10 Year Projected Monthly Payments");
        this.MSG_GRAPH2 = this.getParameter("MSG_GRAPH2", "Year");
        this.MSG_GRAPH3 = this.getParameter("MSG_GRAPH3", "Net house payment");
        this.MSG_GRAPH4 = this.getParameter("MSG_GRAPH4", "Rent payment");
        this.MSG_GRAPH5 = this.getParameter("MSG_GRAPH5", "Monthly Payment Breakdown");
        this.MSG_GRAPH6 = this.getParameter("MSG_GRAPH6", "House payment");
        this.MSG_GRAPH7 = this.getParameter("MSG_GRAPH7", "Tax savings");
        this.MSG_GRAPH8 = this.getParameter("MSG_GRAPH8", "Principal payment");
        this.MSG_GRAPH9 = this.getParameter("MSG_GRAPH9", "Investment total");
        this.MSG_GRAPH10 = this.getParameter("MSG_GRAPH10", "Equity in home");
        this.MSG_GRAPH11 = this.getParameter("MSG_GRAPH11", "Net after commission");
        this.MSG_GRAPH12 = this.getParameter("MSG_GRAPH12", "Investment vs Home Equity");
        this.MSG_GRAPH_START1 = this.getParameter("MSG_GRAPH_START1", "Home beats renting after");
        this.MSG_GRAPH_START2 = this.getParameter("MSG_GRAPH_START2", "Home doesn't beat renting after");
        this.MSG_GRAPH_END = this.getParameter("MSG_GRAPH_END", "years");
        this.RC.MSG_RESULT1 = this.getParameter("MSG_RESULT1", this.RC.MSG_RESULT1);
        this.RC.MSG_DESC1 = this.getParameter("MSG_DESC1", this.RC.MSG_DESC1);
        this.RC.MSG_RESULT2 = this.getParameter("MSG_RESULT2", this.RC.MSG_RESULT2);
        this.RC.MSG_DESC2 = this.getParameter("MSG_DESC2", this.RC.MSG_DESC2);
        this.RC.MSG_ERROR1 = this.getParameter("MSG_ERROR1", this.RC.MSG_ERROR1);
        this.RC.CAMORTGAGE = this.getParameter("CAMORTGAGE", false);
        this.tfPRICE_OF_HOME = new Nbr("PRICE_OF_HOME", "Price of home", 1000.0, 1.0E7, 0, 3, this);
        this.tfDOWNPAYMENT_CLOSING_CASH = new Nbr("DOWNPAYMENT_CLOSING_CASH", "Cash on hand", 1.0, 2000000.0, 0, 3, this);
        this.tfPROPERTY_TAX_RATE = new Nbr("PROPERTY_TAX_RATE", "Property tax rate", 0.0, 10.0, 3, 4, this);
        this.tfPROPERTY_TAX_AMT = new Nbr("PROPERTY_TAX_AMT", "Property taxes", 0.0, 1000000.0, 0, 3, this);
        this.tfHOME_INSURANCE_RATE = new Nbr("HOME_INSURANCE_RATE", "Home insurance rate", 0.0, 10.0, 3, 4, this);
        this.tfINTEREST_RATE = new Nbr("INTEREST_RATE", "Interest rate", 0.0, 25.0, 3, 4, this);
        this.tfMAINTENANCE = new Nbr("MAINTENANCE", "Maintenance/Condo Fees", 0.0, 10000.0, 0, 3, this);
        if (this.getParameter("DISABLE_PROPERTY_TAX_RATE", false)) {
            this.tfPROPERTY_TAX_RATE.disable();
        }
        if (this.getParameter("DISABLE_HOME_INSURANCE_RATE", false)) {
            this.tfHOME_INSURANCE_RATE.disable();
        }
        if (this.RC.CAMORTGAGE) {
            this.tfLOAN_ORIGINATION_AMT = new Nbr("LOAN_ORIGINATION_RATE", "Mortgage fees", 0.0, 100000.0, 0, 3, this);
        }
        else {
            this.tfLOAN_ORIGINATION_RATE = new Nbr("LOAN_ORIGINATION_RATE", "Loan origination", 0.0, 5.0, 3, 4, this);
        }
        this.tfPOINTS_PAID_NBR = new Nbr("POINTS_PAID_NBR", "Points paid", 0.0, 5.0, 2, 5, this);
        this.tfPMI_RATE = new Nbr("PMI_RATE", "PMI percentage", 0.0, 3.0, 3, 4, this);
        this.tfOTHER_CLOSING_COSTS = new Nbr("OTHER_CLOSING_COSTS", "Other closing costs", 0.0, 10000.0, 0, 3, this);
        this.tfTAX_RATE = new Nbr("TAX_RATE", "Income tax rate", 0.0, 50.0, 2, 4, this);
        this.tfMONTHLY_RENT = new Nbr("MONTHLY_RENT", "Monthly rent payment", 10.0, 10000.0, 0, 3, this);
        this.tfINFLATION_RATE = new Nbr("INFLATION_RATE", "Expected inflation rate", 0.0, 20.0, 2, 4, this);
        this.tfINVESTMENT_RETURN = new Nbr("INVESTMENT_RETURN", "After-tax investment return", 0.0, this.getRORMaximum(20.0), 2, 4, this);
        this.tfHOME_APPRECIATION_RATE = new Nbr("HOME_APPRECIATION_RATE", "Home appreciates at", 0.0, 25.0, 2, 4, this);
        this.tfHOME_COMMISION_RATE = new Nbr("HOME_COMMISION_RATE", "Future sales commission", 0.0, 25.0, 2, 4, this);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.cbYEAR = new Checkbox(this.getParameter("MSG_LABEL1", "Show schedule by year"), checkboxGroup, true);
        this.cbMONTH = new Checkbox(this.getParameter("MSG_LABEL2", "Show schedule by month"), checkboxGroup, false);
        this.cbMAXIMUM20 = new Checkbox(this.getParameter("MSG_LABEL3", "Limit downpayment to 20%"), null, this.RC.CAMORTGAGE);
        final CheckboxGroup checkboxGroup2 = new CheckboxGroup();
        this.cbPAYMENT = new Checkbox(this.getParameter("MSG_LABEL4", "Monthly payment breakdown"), checkboxGroup2, true);
        this.cbAMTS_PAID = new Checkbox(this.getParameter("MSG_LABEL5", "Projected monthly payments"), checkboxGroup2, false);
        this.cbINVESTMENT = new Checkbox(this.getParameter("MSG_LABEL6", "Home equity vs. investment"), checkboxGroup2, false);
        this.cbCASH = new Checkbox(this.getParameter("MSG_LABEL7", "Use of cash on hand"), checkboxGroup2, false);
        this.cbYEAR.setBackground(this.getBackground());
        this.cbMONTH.setBackground(this.getBackground());
        this.cbMAXIMUM20.setBackground(this.getBackground());
        this.cbPAYMENT.setBackground(this.getBackground());
        this.cbAMTS_PAID.setBackground(this.getBackground());
        this.cbINVESTMENT.setBackground(this.getBackground());
        this.cbCASH.setBackground(this.getBackground());
        super.nStack = 1;
        super.bUseNorth = true;
        super.bUseSouth = false;
        super.bUseWest = false;
        super.bUseEast = false;
        if (this.getParameter("SHOW_MAINTENANCE", false)) {
            this.tfMAINTENANCE.setText("0");
        }
        this.setCalculation(this.RC);
        if (this.RC.CAMORTGAGE) {
            this.cmbTERM = this.getTermChoice(this.getParameter("LENGTH_OF_LOAN", this.getParameter("TERM", 25)), true, this.getParameter("TERM_MAXIMUM", 25), this.getParameter("SHOW_ALL_MAXIMUM", this.getParameter("TERM_MAXIMUM", 25)), this.getParameter("TERM_LONG_APPEND", ""));
        }
        else {
            this.cmbTERM = this.getMortgageTermChoice(this.getParameter("LENGTH_OF_LOAN", this.getParameter("TERM_MAXIMUM", 30)));
        }
    }
    
    public void initPanels() {
        this.cbNEW_HOME_PURCHASE.setBackground(this.getBackground());
        this.cbLABEL_FLEX_DOWN.setBackground(this.getBackground());
        int n = 1;
        final DataPanel dataPanel = new DataPanel();
        dataPanel.setBackground(this.getBackground());
        dataPanel.addRow(this.tfPRICE_OF_HOME, this.tfDOWNPAYMENT_CLOSING_CASH, this.getPlainFont(), n++);
        dataPanel.addRow(this.tfINTEREST_RATE, String.valueOf(this.getParameter("MSG_TERM", "Term")) + super._COLON, this.cmbTERM, this.getPlainFont(), n++);
        if (this.RC.CAMORTGAGE) {
            dataPanel.addRow(this.tfPROPERTY_TAX_AMT, this.tfLOAN_ORIGINATION_AMT, this.getPlainFont(), n++);
            dataPanel.addRow(this.getParameter("MSG_NEW_HOME", "Is this a new home?"), this.cbNEW_HOME_PURCHASE, String.valueOf(this.getParameter("MSG_CMHS_PREMIUM", "CMHC Premium")) + super._COLON, this.tfTOTAL_CMHC, this.getPlainFont(), n++);
            if (this.getParameter("SHOW_FLEX_DOWN", false)) {
                if (super.sLANGUAGE.equals("FRENCH")) {
                    dataPanel.addRow(this.tfOTHER_CLOSING_COSTS, "", new Label(""), this.getPlainFont(), n++);
                    final DataPanel dataPanel2 = new DataPanel();
                    dataPanel2.setBackground(this.getBackground());
                    dataPanel2.addRow(this.getParameter("MSG_LABEL_FLEX_DOWN2", "Less than 10% downpayment?"), this.cbLABEL_FLEX_DOWN, this.getPlainFont(), 1);
                    dataPanel.addRow(dataPanel2, this.getPlainFont(), n++);
                }
                else {
                    dataPanel.addRow(this.tfOTHER_CLOSING_COSTS, this.getParameter("MSG_LABEL_FLEX_DOWN2", "Less than 10% down?"), this.cbLABEL_FLEX_DOWN, this.getPlainFont(), n++);
                }
            }
            else {
                dataPanel.addRow(this.tfOTHER_CLOSING_COSTS, "", new Label(""), this.getPlainFont(), n++);
            }
        }
        else {
            dataPanel.addRow(this.tfPROPERTY_TAX_RATE, this.lbPROPERTY_TAX_AMT, this.tfHOME_INSURANCE_RATE, this.tfHOME_INSURANCE_AMT, this.getPlainFont(), n++);
            dataPanel.addRow(this.tfLOAN_ORIGINATION_RATE, this.lbLOAN_ORIGINATION_AMT, this.tfPOINTS_PAID_NBR, this.tfPOINTS_PAID_AMT, this.getPlainFont(), n++);
            if (this.getParameter("SHOW_MAINTENANCE", false)) {
                dataPanel.addRow(this.tfOTHER_CLOSING_COSTS, this.tfMAINTENANCE, new Label(this.getParameter("MSG_MONTH", "monthly")), this.getPlainFont(), n++);
            }
            else {
                dataPanel.addRow(this.tfOTHER_CLOSING_COSTS, String.valueOf(this.getParameter("MSG_CLOSING_COSTS", "Total closing costs")) + super._COLON + " ", this.tfTOTAL_CLOSING_COSTS, this.getPlainFont(), n++);
            }
        }
        if (this.RC.CAMORTGAGE) {
            dataPanel.addRow("", new Label(""), this.getTinyFont(), n++);
            if (this.getParameter("SHOW_MAINTENANCE", true)) {
                dataPanel.addRow(this.tfMAINTENANCE, new Label(this.getParameter("MSG_MONTH", "monthly")), this.tfINVESTMENT_RETURN, new Label(""), this.getPlainFont(), n++);
                dataPanel.addRow(this.tfHOME_APPRECIATION_RATE, this.tfINFLATION_RATE, this.getPlainFont(), n++);
                dataPanel.addRow(this.tfHOME_COMMISION_RATE, "", new Label(""), this.getPlainFont(), n++);
                dataPanel.addRow("", new Label(""), this.getTinyFont(), n++);
                dataPanel.addRow(String.valueOf(this.getParameter("MSG_LOAN_AMOUNT", "Mortgage amount")) + super._COLON + " ", this.tfLOAN_AMOUNT, String.valueOf(this.getParameter("MSG_CLOSING_COSTS", "Total closing costs")) + super._COLON + " ", this.tfTOTAL_CLOSING_COSTS, this.getBoldFont(), n++);
                dataPanel.addRow("", new Label(""), this.getTinyFont(), n++);
                dataPanel.addRow(this.tfMONTHLY_RENT, "", new Label(""), this.getBoldFont(), n++);
            }
            else {
                dataPanel.addRow(String.valueOf(this.getParameter("MSG_LOAN_AMOUNT", "Mortgage amount")) + super._COLON + " ", this.tfLOAN_AMOUNT, String.valueOf(this.getParameter("MSG_MONTHLY_PAYMENT", "Total monthly payment")) + super._COLON + " ", this.tfMONTHLY_TOTAL_PMT, this.getBoldFont(), n++);
                dataPanel.addRow("", new Label(""), this.getTinyFont(), n++);
                dataPanel.addRow(this.tfMONTHLY_RENT, this.tfINVESTMENT_RETURN, new Label(""), this.getPlainFont(), n++);
                dataPanel.addRow(this.tfHOME_APPRECIATION_RATE, this.tfINFLATION_RATE, this.getPlainFont(), n++);
                dataPanel.addRow(this.tfHOME_COMMISION_RATE, "", new Label(""), this.getPlainFont(), n++);
                dataPanel.addRow("", new Label(""), this.getTinyFont(), n++);
            }
        }
        else {
            dataPanel.addRow("", new Label(""), this.getTinyFont(), n++);
            dataPanel.addRow(String.valueOf(this.getParameter("MSG_LOAN_AMOUNT", "Mortgage amount")) + super._COLON + " ", this.tfLOAN_AMOUNT, String.valueOf(this.getParameter("MSG_MONTHLY_PAYMENT", "Total monthly payment")) + super._COLON + " ", this.tfMONTHLY_TOTAL_PMT, this.getBoldFont(), n++);
            dataPanel.addRow("", new Label(""), this.getTinyFont(), n++);
            dataPanel.addRow(this.tfMONTHLY_RENT, this.tfINVESTMENT_RETURN, this.getPlainFont(), n++);
            dataPanel.addRow(this.tfTAX_RATE, this.tfINFLATION_RATE, this.getPlainFont(), n++);
            dataPanel.addRow(this.tfHOME_APPRECIATION_RATE, this.tfHOME_COMMISION_RATE, this.getPlainFont(), n++);
        }
        dataPanel.addRow("", new Label(""), this.getTinyFont(), n++);
        final DataPanel dataPanel3 = new DataPanel();
        dataPanel3.setBackground(this.getBackground());
        if (super.iBrowser != 2) {
            dataPanel3.addRow(new Label(" " + this.getParameter("MSG_CHART_SELECTION", "Chart selection:")), this.getBoldFont(), 1);
        }
        dataPanel3.addRow(" ", this.cbPAYMENT, this.getPlainFont(), 2);
        dataPanel3.addRow(" ", this.cbAMTS_PAID, this.getPlainFont(), 3);
        dataPanel3.addRow(" ", this.cbINVESTMENT, this.getPlainFont(), 4);
        dataPanel3.addRow(" ", this.cbCASH, this.getPlainFont(), 5);
        if (super.iBrowser != 2) {
            dataPanel3.addRow(new Label(" " + this.getParameter("MSG_OTHER_OPTIONS", "Other options:")), this.getBoldFont(), 6);
        }
        if (!this.RC.CAMORTGAGE) {
            dataPanel3.addRow(" ", this.cbMAXIMUM20, this.getPlainFont(), 7);
        }
        dataPanel3.addRow(" ", this.cbYEAR, this.getPlainFont(), 8);
        dataPanel3.addRow(" ", this.cbMONTH, this.getPlainFont(), 9);
        this.gGraph = new Graph(new GraphColumn(), this.imageBackground());
        this.gGraph.FONT_TITLE = this.getGraphTitleFont();
        this.gGraph.FONT_BOLD = this.getBoldFont();
        this.gGraph.FONT_PLAIN = this.getPlainFont();
        this.gGraph.setForeground(this.getForeground());
        this.gGraph.setBackground(this.getColor(1));
        this.gGraph._legend.setVisible(true);
        this.gGraph._legend.setOrientation(1);
        this.gGraph._axisX.setVisible(true);
        this.gGraph._axisY.setVisible(true);
        final DataPanel dataPanel4 = new DataPanel();
        dataPanel4.setBackground(this.getBackground());
        dataPanel4.setFont(this.getTinyFont());
        dataPanel4.add(new Label(""));
        final DataPanel dataPanel5 = new DataPanel();
        dataPanel5.setBackground(this.getBackground());
        dataPanel5.setFont(this.getTinyFont());
        dataPanel5.add(new Label(""));
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 2));
        panel.add("South", dataPanel4);
        panel.add("East", dataPanel5);
        panel.add("West", dataPanel3);
        panel2.add(this.gGraph);
        panel.add("Center", panel2);
        this.addDataPanel(dataPanel);
        this.addPanel(panel);
    }
    
    public void refresh() {
        this.gGraph._bUseTextImages = false;
        if (this.cbAMTS_PAID.getState()) {
            this.gGraph.setBackground(this.getColor(1));
            this.gGraph.removeAll();
            this.gGraph._iLimit = 10;
            this.gGraph.setGraphType(new GraphColumn());
            this.gGraph.setGraphCatagories(this.RC.getCatagories());
            this.gGraph._axisY._bAutoMinimum = false;
            this.gGraph._axisY._axisMinimum = 0.0f;
            this.gGraph._axisY._bAutoMaximum = true;
            this.gGraph._legend.setVisible(true);
            this.gGraph._legend.setOrientation(1);
            this.gGraph._axisX.setVisible(true);
            this.gGraph._axisY.setVisible(true);
            this.gGraph._titleYAxis.setText("");
            this.gGraph._titleGraph.setText(this.MSG_GRAPH1);
            this.gGraph._titleXAxis.setText(this.MSG_GRAPH2);
            this.gGraph.setGraphCatagories(this.RC.getCatagories());
            try {
                this.gGraph.add(new GraphDataSeries(this.RC.DS_NET_HOME_COST, this.MSG_GRAPH3, this.getGraphColor(1)));
                this.gGraph.add(new GraphDataSeries(this.RC.DS_RENT, this.MSG_GRAPH4, this.getGraphColor(2)));
            }
            catch (Exception ex) {
                System.out.println("Huh?");
            }
        }
        else if (this.cbPAYMENT.getState()) {
            this.gGraph.setBackground(this.getColor(2));
            this.gGraph.removeAll();
            this.gGraph._iLimit = 1;
            this.gGraph.setGraphType(new GraphColumn());
            this.gGraph.setGraphCatagories(this.RC.getCatagories());
            this.gGraph._axisY._bAutoMinimum = false;
            this.gGraph._axisY._axisMinimum = 0.0f;
            this.gGraph._axisY._bAutoMaximum = true;
            this.gGraph._legend.setVisible(true);
            this.gGraph._legend.setOrientation(1);
            this.gGraph._axisX.setVisible(false);
            this.gGraph._axisY.setVisible(true);
            this.gGraph._titleYAxis.setText("");
            this.gGraph._titleGraph.setText(this.MSG_GRAPH5);
            this.gGraph._titleXAxis.setText("");
            this.gGraph.setGraphCatagories(this.RC.getCatagories());
            try {
                this.gGraph.add(new GraphDataSeries(this.RC.DS_RENT, String.valueOf(this.MSG_GRAPH4) + " " + Fmt.dollars(this.RC.DS_RENT[0]), this.getGraphColor(1)));
                this.gGraph.add(new GraphDataSeries(this.RC.DS_MONTHLY_TOTAL_PMT, String.valueOf(this.MSG_GRAPH6) + " " + Fmt.dollars(this.RC.DS_MONTHLY_TOTAL_PMT[0]), this.getGraphColor(2)));
                if (!this.RC.CAMORTGAGE) {
                    this.gGraph.add(new GraphDataSeries(this.RC.DS_TAXES, String.valueOf(this.MSG_GRAPH7) + " " + Fmt.dollars(this.RC.DS_TAXES[0]), this.getGraphColor(3)));
                }
                this.gGraph.add(new GraphDataSeries(this.RC.DS_EQUITY, String.valueOf(this.MSG_GRAPH8) + " " + Fmt.dollars(this.RC.DS_EQUITY[0]), this.getGraphColor(4)));
                this.gGraph.add(new GraphDataSeries(this.RC.DS_NET_HOME_COST, String.valueOf(this.MSG_GRAPH3) + " " + Fmt.dollars(this.RC.DS_NET_HOME_COST[0]), this.getGraphColor(5)));
            }
            catch (Exception ex2) {
                System.out.println("Huh?");
            }
        }
        else if (this.cbINVESTMENT.getState()) {
            this.gGraph.setBackground(this.getColor(3));
            this.gGraph.removeAll();
            this.gGraph._iLimit = 10;
            this.gGraph.setGraphType(new GraphLine());
            this.gGraph.setGraphCatagories(this.RC.getCatagories());
            this.gGraph._axisY._bAutoMinimum = true;
            this.gGraph._axisY._axisMinimum = 0.0f;
            this.gGraph._axisY._bAutoMaximum = true;
            this.gGraph._legend.setVisible(true);
            this.gGraph._legend.setOrientation(1);
            this.gGraph._axisX.setVisible(true);
            this.gGraph._axisY.setVisible(true);
            this.gGraph._titleYAxis.setText(this.RC.getAmountLabel());
            this.gGraph._titleGraph.setText(this.MSG_GRAPH12);
            if (this.RC.BREAKEVEN_YEARS > 0.0) {
                this.gGraph._titleXAxis.setText(String.valueOf(this.MSG_GRAPH_START1) + " " + Fmt.number(this.RC.BREAKEVEN_YEARS, 1) + ((this.MSG_GRAPH_END.length() == 1) ? "" : " ") + this.MSG_GRAPH_END);
            }
            else {
                this.gGraph._titleXAxis.setText(String.valueOf(this.MSG_GRAPH_START2) + " " + Fmt.number(this.RC.LENGTH_OF_LOAN, 1) + ((this.MSG_GRAPH_END.length() == 1) ? "" : " ") + this.MSG_GRAPH_END);
            }
            this.gGraph.setGraphCatagories(this.RC.getCatagories());
            this.gGraph.setGraphCatagories(this.RC.getCatagories());
            try {
                this.gGraph.add(new GraphDataSeries(this.RC.DS_INVESTMENT_TOTAL, this.MSG_GRAPH9, this.getGraphColor(1)));
                this.gGraph.add(new GraphDataSeries(this.RC.DS_HOME_EQUITY, this.MSG_GRAPH10, this.getGraphColor(2)));
                this.gGraph.add(new GraphDataSeries(this.RC.DS_EQUITY_AFTER_COSTS, this.MSG_GRAPH11, this.getGraphColor(3)));
            }
            catch (Exception ex3) {
                System.out.println("Huh?");
            }
        }
        else if (this.cbCASH.getState()) {
            this.gGraph.setBackground(this.getColor(4));
            this.gGraph.removeAll();
            this.gGraph.setGraphType(new GraphCatagories());
            final String[] graphCatagories = new String[this.RC.CAMORTGAGE ? 4 : 5];
            final float[] array = new float[this.RC.CAMORTGAGE ? 4 : 5];
            int n = 0;
            graphCatagories[n++] = String.valueOf(this.MSG_DOWNPAYMENT) + " " + Fmt.dollars(this.RC.TOTAL_FOR_DOWNPAYMENT);
            if (this.RC.CAMORTGAGE) {
                graphCatagories[n++] = String.valueOf(this.tfLOAN_ORIGINATION_AMT.getName()) + " " + Fmt.dollars(this.RC.LOAN_ORIGINATION_AMT);
            }
            else {
                graphCatagories[n++] = String.valueOf(this.tfLOAN_ORIGINATION_RATE.getName()) + " " + Fmt.dollars(this.RC.LOAN_ORIGINATION_AMT);
            }
            if (!this.RC.CAMORTGAGE) {
                graphCatagories[n++] = (graphCatagories[2] = String.valueOf(this.tfPOINTS_PAID_NBR.getName()) + " " + Fmt.dollars(this.RC.POINTS_PAID_AMT));
            }
            graphCatagories[n++] = String.valueOf(this.tfOTHER_CLOSING_COSTS.getName()) + " " + Fmt.dollars(this.RC.OTHER_CLOSING_COSTS);
            graphCatagories[n++] = String.valueOf(this.MSG_REMAINING_CASH) + " " + Fmt.dollars(this.RC.DOWNPAYMENT_CLOSING_CASH - this.RC.TOTAL_CLOSING_COSTS - this.RC.TOTAL_FOR_DOWNPAYMENT);
            this.gGraph.setGraphCatagories(graphCatagories);
            int n2 = 0;
            array[n2++] = (float)this.RC.TOTAL_FOR_DOWNPAYMENT;
            array[n2++] = (float)this.RC.LOAN_ORIGINATION_AMT;
            if (!this.RC.CAMORTGAGE) {
                array[n2++] = (float)this.RC.POINTS_PAID_AMT;
            }
            array[n2++] = (float)this.RC.OTHER_CLOSING_COSTS;
            array[n2++] = (float)(this.RC.DOWNPAYMENT_CLOSING_CASH - this.RC.TOTAL_CLOSING_COSTS - this.RC.TOTAL_FOR_DOWNPAYMENT);
            this.gGraph._axisY._bAutoMinimum = false;
            this.gGraph._axisY._axisMinimum = 0.0f;
            this.gGraph._axisY._bAutoMaximum = true;
            this.gGraph._legend.setVisible(true);
            this.gGraph._legend.setOrientation(1);
            this.gGraph._axisX.setVisible(false);
            this.gGraph._axisY.setVisible(true);
            this.gGraph._titleYAxis.setText("");
            this.gGraph._titleXAxis.setText("");
            this.gGraph._titleXAxis.setText(this.MSG_AXIS_TITLE);
            this.gGraph._titleGraph.setText(String.valueOf(this.MSG_GRAPHTITLE_START) + " " + Fmt.dollars(this.RC.DOWNPAYMENT_CLOSING_CASH) + " " + this.MSG_GRAPH_TITLE_END);
            try {
                this.gGraph.add(new GraphDataSeries(array, ""));
            }
            catch (Exception ex4) {
                System.out.println("Huh?");
            }
            this.gGraph._colorList = this.getGraphColors();
        }
        this.setTitle("Rent vs. Buy");
        this.tfHOME_INSURANCE_AMT.setText(" = " + Fmt.dollars(this.RC.HOME_INSURANCE_AMT, 2));
        this.lbLOAN_ORIGINATION_AMT.setText(" = " + Fmt.dollars(this.RC.LOAN_ORIGINATION_AMT, 2));
        this.lbPROPERTY_TAX_AMT.setText(" = " + Fmt.dollars(this.RC.PROPERTY_TAX_AMT, 0));
        this.tfPOINTS_PAID_AMT.setText(" = " + Fmt.dollars(this.RC.POINTS_PAID_AMT, 2));
        this.tfTOTAL_CLOSING_COSTS.setText(Fmt.dollars(this.RC.TOTAL_CLOSING_COSTS + this.RC.TOTAL_CMHC, 2));
        this.tfTOTAL_FOR_DOWNPAYMENT.setText(Fmt.dollars(this.RC.TOTAL_FOR_DOWNPAYMENT));
        this.tfLOAN_AMOUNT.setText(Fmt.dollars(this.RC.LOAN_AMOUNT));
        this.tfDOWNPAYMENT_20.setText(Fmt.dollars(this.RC.DOWNPAYMENT_20));
        this.tfCLOSING_CLOSING_COSTS_20.setText(Fmt.dollars(this.RC.CLOSING_CLOSING_COSTS_20));
        this.tfHOME_INSURANCE_MONTHLY.setText(Fmt.dollars(this.RC.HOME_INSURANCE_MONTHLY, 2));
        this.tfPROPERY_TAX_MONTHLY.setText(Fmt.dollars(this.RC.PROPERY_TAX_MONTHLY, 2));
        this.tfMONTHLY_PI.setText(Fmt.dollars(this.RC.MONTHLY_PI, 2));
        this.tfMONTHLY_PMI.setText(Fmt.dollars(this.RC.MONTHLY_PMI, 2));
        this.tfMONTHLY_TOTAL_PMT.setText(Fmt.dollars(this.RC.MONTHLY_TOTAL_PMT, 2));
        this.tfTOTAL_CMHC.setText(String.valueOf(Fmt.percent(this.RC.RATE_CMHC, 2)) + " = " + Fmt.dollars(this.RC.TOTAL_CMHC));
        this.gGraph.dataChanged(true);
    }
    
    public void setValues() throws NumberFormatException {
        this.RC.PRICE_OF_HOME = this.tfPRICE_OF_HOME.toDouble();
        this.RC.DOWNPAYMENT_CLOSING_CASH = this.tfDOWNPAYMENT_CLOSING_CASH.toDouble();
        this.RC.PROPERTY_TAX_RATE = this.tfPROPERTY_TAX_RATE.toDouble();
        this.RC.HOME_INSURANCE_RATE = this.tfHOME_INSURANCE_RATE.toDouble();
        this.RC.INTEREST_RATE = this.tfINTEREST_RATE.toDouble();
        this.RC.LENGTH_OF_LOAN = this.getMortgageTerm(this.cmbTERM);
        if (this.RC.CAMORTGAGE) {
            this.RC.LOAN_ORIGINATION_AMT = this.tfLOAN_ORIGINATION_AMT.toDouble();
            this.RC.LOAN_ORIGINATION_RATE = 0.0;
        }
        else {
            this.RC.LOAN_ORIGINATION_RATE = this.tfLOAN_ORIGINATION_RATE.toDouble();
            this.RC.LOAN_ORIGINATION_AMT = 0.0;
        }
        this.RC.POINTS_PAID_NBR = this.tfPOINTS_PAID_NBR.toDouble();
        this.RC.PMI_RATE = this.tfPMI_RATE.toDouble();
        this.RC.OTHER_CLOSING_COSTS = this.tfOTHER_CLOSING_COSTS.toDouble();
        this.RC.TAX_RATE = this.tfTAX_RATE.toDouble();
        this.RC.MONTHLY_RENT = this.tfMONTHLY_RENT.toDouble();
        this.RC.INFLATION_RATE = this.tfINFLATION_RATE.toDouble();
        this.RC.INVESTMENT_RETURN = this.tfINVESTMENT_RETURN.toDouble();
        this.RC.HOME_APPRECIATION_RATE = this.tfHOME_APPRECIATION_RATE.toDouble();
        this.RC.HOME_COMMISION_RATE = this.tfHOME_COMMISION_RATE.toDouble();
        this.RC.PROPERTY_TAX_AMT = this.tfPROPERTY_TAX_AMT.toDouble();
        if (this.cbYEAR.getState()) {
            this.RC.REPORT_PERIOD = "1";
        }
        else {
            this.RC.REPORT_PERIOD = "0";
        }
        if (this.cbMAXIMUM20.getState()) {
            this.RC.MAXIMUM_20_DOWN = 1.0;
        }
        else {
            this.RC.MAXIMUM_20_DOWN = 0.0;
        }
        this.RC.GST_TAX_USE = this.cbNEW_HOME_PURCHASE.getState();
        this.RC.bUSE_FLEX = this.cbLABEL_FLEX_DOWN.getState();
        this.RC.MAINTENANCE = this.tfMAINTENANCE.toDouble();
    }
}
