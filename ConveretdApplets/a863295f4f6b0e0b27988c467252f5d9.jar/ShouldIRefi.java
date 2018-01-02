import KJEgui.Fmt;
import KJEgraph.GraphDataSeries;
import KJEgraph.GraphType;
import KJEgraph.GraphCatagories;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Component;
import KJEgui.DataPanel;
import KJEcalculation.Calculation;
import java.applet.Applet;
import KJEgraph.Graph;
import java.awt.Label;
import java.awt.Choice;
import KJEgui.Nbr;
import KJEgui.CalculatorApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShouldIRefi extends CalculatorApplet
{
    ShouldIRefiCalculation RC;
    Nbr tfAPPRAISED_HOME_VALUE;
    Nbr tfORIGINAL_LOAN_AMOUNT;
    Nbr tfORIGINAL_RATE;
    Nbr tfNUMBER_OF_PAYMENTS_MADE;
    Nbr tfANNUAL_PROPERTY_TAXES;
    Nbr tfANNUAL_HOME_INSURANCE;
    Nbr tfMONTHLY_PMI;
    Nbr tfNEW_RATE;
    Nbr tfCLOSING_COSTS;
    Choice cmbTERM_IN_YEARS_1;
    Choice cmbTERM_IN_YEARS_2;
    Label tfCURRENT_PAYMENT;
    Label tfCURRENT_BALANCE;
    Label tfCURRENT_PITI;
    Label tfNEW_PAYMENT;
    Label tfNEWMONTHLY_PMI;
    Label tfNEW_PITI;
    Label tfREMAINING_INTEREST_ON_CURRENT_MORTGAGE;
    Label tfINTEREST_ON_NEW_MORTGAGE;
    Label tfINTEREST_SAVINGS;
    Label tfMONTHLY_SAVINGS;
    Label tfNEW_LOAN_TO_VALUE;
    Graph gGraph;
    public float[] DS_PAYMENTS;
    public String _sAmountLabel;
    public String[] cats;
    public String[] catlabel;
    String MSG_TITLE_START;
    String MSG_TITLE_END;
    
    public ShouldIRefi() {
        this.RC = new ShouldIRefiCalculation();
        this.tfCURRENT_PAYMENT = new Label("");
        this.tfCURRENT_BALANCE = new Label("");
        this.tfCURRENT_PITI = new Label("");
        this.tfNEW_PAYMENT = new Label("");
        this.tfNEWMONTHLY_PMI = new Label("");
        this.tfNEW_PITI = new Label("");
        this.tfREMAINING_INTEREST_ON_CURRENT_MORTGAGE = new Label("");
        this.tfINTEREST_ON_NEW_MORTGAGE = new Label("");
        this.tfINTEREST_SAVINGS = new Label("");
        this.tfMONTHLY_SAVINGS = new Label("");
        this.tfNEW_LOAN_TO_VALUE = new Label("");
        this.DS_PAYMENTS = new float[4];
        this._sAmountLabel = "";
        this.cats = new String[4];
        this.catlabel = new String[] { "Current PI", "New PI", "Current PITI", "New PITI" };
    }
    
    public void initCalculatorApplet() {
        this.RC.PMI_PERCENT = this.getParameter("PMI_PERCENT", this.RC.PMI_PERCENT);
        this.RC.MSG_INCREASE1 = this.getParameter("MSG_INCREASE1", this.RC.MSG_INCREASE1);
        this.RC.MSG_DECREASE1 = this.getParameter("MSG_DECREASE1", this.RC.MSG_DECREASE1);
        this.RC.MSG_INCREASE2 = this.getParameter("MSG_INCREASE2", this.RC.MSG_INCREASE2);
        this.RC.MSG_DECREASE2 = this.getParameter("MSG_DECREASE2", this.RC.MSG_DECREASE2);
        this.RC.MSG_INCREASE3 = this.getParameter("MSG_INCREASE3", this.RC.MSG_INCREASE3);
        this.RC.MSG_DECREASE3 = this.getParameter("MSG_DECREASE3", this.RC.MSG_DECREASE3);
        this.RC.MSG_BREAK_EVEN1 = this.getParameter("MSG_BREAK_EVEN1", this.RC.MSG_BREAK_EVEN1);
        this.RC.MSG_BREAK_EVEN2 = this.getParameter("MSG_BREAK_EVEN2", this.RC.MSG_BREAK_EVEN2);
        this.RC.MSG_TITLE_DECREASE = this.getParameter("MSG_TITLE_DECREASE", this.RC.MSG_TITLE_DECREASE);
        this.RC.MSG_TITLE_INCREASE = this.getParameter("MSG_TITLE_INCREASE", this.RC.MSG_TITLE_INCREASE);
        this.RC.catlabel[0] = this.getParameter("MSG_CATLABEL1", this.RC.catlabel[0]);
        this.RC.catlabel[1] = this.getParameter("MSG_CATLABEL2", this.RC.catlabel[1]);
        this.RC.catlabel[2] = this.getParameter("MSG_CATLABEL3", this.RC.catlabel[2]);
        this.RC.catlabel[3] = this.getParameter("MSG_CATLABEL4", this.RC.catlabel[3]);
        this.tfAPPRAISED_HOME_VALUE = new Nbr("APPRAISED_HOME_VALUE", "Appraised home value", 1000.0, 5000000.0, 0, 3, this);
        this.tfORIGINAL_LOAN_AMOUNT = new Nbr("ORIGINAL_LOAN_AMOUNT", "Original mortgage", 1000.0, 5000000.0, 0, 3, this);
        this.tfORIGINAL_RATE = new Nbr("ORIGINAL_RATE", "Rate", 0.0, 20.0, 3, 4, this);
        this.tfNUMBER_OF_PAYMENTS_MADE = new Nbr("NUMBER_OF_PAYMENTS_MADE", "Number of payments made", 1.0, 360.0, 0, 5, this);
        this.tfANNUAL_PROPERTY_TAXES = new Nbr("ANNUAL_PROPERTY_TAXES", "Annual property taxes", 0.0, 50000.0, 2, 3, this);
        this.tfANNUAL_HOME_INSURANCE = new Nbr("ANNUAL_HOME_INSURANCE", "Annual home insurance", 0.0, 50000.0, 2, 3, this);
        this.tfMONTHLY_PMI = new Nbr("MONTHLY_PMI", "Monthly PMI", 0.0, 5000.0, 0, 3, this);
        this.tfNEW_RATE = new Nbr("NEW_RATE", "Rate", 0.0, 20.0, 3, 4, this);
        this.tfCLOSING_COSTS = new Nbr("CLOSING_COSTS", "Closing costs", 0.0, 50000.0, 0, 3, this);
        this.MSG_TITLE_START = this.getParameter("MSG_TITLE_START", "You could");
        this.MSG_TITLE_END = this.getParameter("MSG_TITLE_END", ".");
        super.nStack = 1;
        super.bUseNorth = false;
        super.bUseSouth = false;
        super.bUseWest = true;
        super.bUseEast = false;
        super.GRID_GAP_HOR = 0;
        super.GRID_GAP_VERT = 0;
        this.setCalculation(this.RC);
        this.cmbTERM_IN_YEARS_1 = this.getMortgageTermChoice(this.getParameter("ORIGINAL_TERM_IN_YEARS", 30));
        this.cmbTERM_IN_YEARS_2 = this.getMortgageTermChoice(this.getParameter("NEW_TERM", 30));
    }
    
    public void initPanels() {
        final DataPanel dataPanel = new DataPanel();
        dataPanel.setBackground(this.getColor(1));
        dataPanel.addRow(this.tfANNUAL_PROPERTY_TAXES, this.getPlainFont(), 1);
        dataPanel.addRow(this.tfANNUAL_HOME_INSURANCE, this.getPlainFont(), 2);
        dataPanel.addRow(this.tfAPPRAISED_HOME_VALUE, this.getPlainFont(), 3);
        dataPanel.addRow("", new Label(""), this.getTinyFont(), 4);
        dataPanel.addRow(this.tfORIGINAL_LOAN_AMOUNT, this.getBoldFont(), 5);
        dataPanel.addRow(this.tfORIGINAL_RATE, this.getPlainFont(), 6);
        dataPanel.addRow(String.valueOf(this.getParameter("MSG_LABEL1", "Term")) + super._COLON, this.cmbTERM_IN_YEARS_1, this.getPlainFont(), 7);
        dataPanel.addRow(this.tfNUMBER_OF_PAYMENTS_MADE, this.getPlainFont(), 8);
        dataPanel.addRow(this.tfMONTHLY_PMI, this.getPlainFont(), 9);
        dataPanel.addRow("", new Label(""), this.getTinyFont(), 10);
        dataPanel.addRow(String.valueOf(this.getParameter("MSG_LABEL2", "New mortgage")) + super._COLON, this.tfCURRENT_BALANCE, this.getBoldFont(), 11);
        dataPanel.addRow(this.tfNEW_RATE, this.getPlainFont(), 12);
        dataPanel.addRow(String.valueOf(this.getParameter("MSG_LABEL1", "Term")) + super._COLON, this.cmbTERM_IN_YEARS_2, this.getPlainFont(), 13);
        dataPanel.addRow(this.tfCLOSING_COSTS, this.getPlainFont(), 14);
        dataPanel.addRow(String.valueOf(this.getParameter("MSG_LABEL3", "Monthly PMI")) + super._COLON, this.tfNEWMONTHLY_PMI, this.getPlainFont(), 15);
        final Panel panel = new Panel();
        panel.setBackground(this.getColor(1));
        panel.setLayout(new BorderLayout());
        final DataPanel dataPanel2 = new DataPanel();
        dataPanel2.setBackground(this.getColor(1));
        dataPanel2.addRow("", new Label(""), this.getTinyFont(), 1);
        final DataPanel dataPanel3 = new DataPanel();
        dataPanel3.setBackground(this.getColor(1));
        dataPanel3.addRow("", new Label(""), this.getTinyFont(), 1);
        final DataPanel dataPanel4 = new DataPanel();
        dataPanel4.setBackground(this.getColor(2));
        dataPanel4.addRow(new Label("  " + this.getParameter("MSG_LABEL4", "Refinancing results")), this.getTitleFont(), 1);
        dataPanel4.addRow(String.valueOf(this.getParameter("MSG_LABEL5", "New loan to appraised value")) + super._COLON, this.tfNEW_LOAN_TO_VALUE, this.getPlainFont(), 2);
        dataPanel4.addRow(String.valueOf(this.getParameter("MSG_LABEL6", "Remaining interest on current")) + super._COLON, this.tfREMAINING_INTEREST_ON_CURRENT_MORTGAGE, this.getPlainFont(), 3);
        dataPanel4.addRow(String.valueOf(this.getParameter("MSG_LABEL7", "Interest on new mortgage")) + super._COLON, this.tfINTEREST_ON_NEW_MORTGAGE, this.getPlainFont(), 4);
        dataPanel4.addRow("", new Label(""), this.getTinyFont(), 5);
        dataPanel4.addRow(String.valueOf(this.getParameter("MSG_LABEL8", "Interest savings")) + super._COLON, this.tfINTEREST_SAVINGS, this.getBoldFont(), 6);
        dataPanel4.addRow(String.valueOf(this.getParameter("MSG_LABEL9", "Monthly savings")) + super._COLON, this.tfMONTHLY_SAVINGS, this.getBoldFont(), 7);
        panel.add("West", dataPanel2);
        panel.add("Center", dataPanel4);
        panel.add("East", dataPanel3);
        this.gGraph = new Graph(new GraphCatagories(), this.imageBackground());
        this.gGraph.FONT_TITLE = this.getGraphTitleFont();
        this.gGraph.FONT_BOLD = this.getBoldFont();
        this.gGraph.FONT_PLAIN = this.getPlainFont();
        this.gGraph.setBackground(this.getColor(1));
        this.gGraph.setForeground(this.getForeground());
        this.gGraph._legend.setVisible(true);
        this.gGraph._legend.setOrientation(1);
        this.gGraph._titleXAxis.setText("");
        this.gGraph._titleGraph.setText("");
        this.gGraph._titleYAxis.setText("");
        this.gGraph._axisX.setVisible(true);
        this.gGraph._axisY.setVisible(true);
        this.addDataPanel(dataPanel);
        this.addPanel(panel);
        this.addGraph(this.gGraph);
    }
    
    public void refresh() {
        this.gGraph.removeAll();
        this.gGraph.setGraphCatagories(this.RC.getCatagories());
        try {
            this.gGraph.add(new GraphDataSeries(this.RC.DS_PAYMENTS, ""));
            this.gGraph._axisY._bAutoMinimum = false;
            this.gGraph._axisY._axisMinimum = 0.0f;
            this.gGraph._axisY._bAutoMaximum = true;
            this.gGraph._axisX.setVisible(false);
        }
        catch (Exception ex) {
            System.out.println("Huh?");
        }
        this.gGraph._colorList = this.getGraphColors();
        this.setTitle(this.RC.MSG_TITLE);
        this.tfCURRENT_PAYMENT.setText(Fmt.dollars(this.RC.CURRENT_PAYMENT, 2));
        this.tfCURRENT_BALANCE.setText(Fmt.dollars(this.RC.CURRENT_BALANCE, 2));
        this.tfCURRENT_PITI.setText(Fmt.dollars(this.RC.CURRENT_PITI, 2));
        this.tfNEW_PAYMENT.setText(Fmt.dollars(this.RC.NEW_PAYMENT, 2));
        this.tfNEWMONTHLY_PMI.setText(Fmt.dollars(this.RC.NEWMONTHLY_PMI, 2));
        this.tfNEW_PITI.setText(Fmt.dollars(this.RC.NEW_PITI, 2));
        this.tfREMAINING_INTEREST_ON_CURRENT_MORTGAGE.setText(Fmt.dollars(this.RC.REMAINING_INTEREST_ON_CURRENT_MORTGAGE, 2));
        this.tfINTEREST_ON_NEW_MORTGAGE.setText(Fmt.dollars(this.RC.INTEREST_ON_NEW_MORTGAGE, 2));
        this.tfINTEREST_SAVINGS.setText(Fmt.dollars(this.RC.INTEREST_SAVINGS, 2));
        this.tfMONTHLY_SAVINGS.setText(Fmt.dollars(this.RC.MONTHLY_SAVINGS, 2));
        this.tfNEW_LOAN_TO_VALUE.setText(Fmt.percent(this.RC.NEW_LOAN_TO_VALUE, 2));
        this.gGraph._titleYAxis.setText(this.RC.getAmountLabel());
        this.gGraph._titleGraph.setText("");
        this.gGraph.dataChanged(true);
    }
    
    public void setValues() throws NumberFormatException {
        this.RC.ORIGINAL_TERM_IN_YEARS = this.getMortgageTerm(this.cmbTERM_IN_YEARS_1);
        this.RC.NEW_TERM = this.getMortgageTerm(this.cmbTERM_IN_YEARS_2);
        this.RC.APPRAISED_HOME_VALUE = this.tfAPPRAISED_HOME_VALUE.toDouble();
        this.RC.ORIGINAL_LOAN_AMOUNT = this.tfORIGINAL_LOAN_AMOUNT.toDouble();
        this.RC.ORIGINAL_RATE = this.tfORIGINAL_RATE.toDouble();
        this.RC.NUMBER_OF_PAYMENTS_MADE = this.tfNUMBER_OF_PAYMENTS_MADE.toDouble();
        this.RC.ANNUAL_PROPERTY_TAXES = this.tfANNUAL_PROPERTY_TAXES.toDouble();
        this.RC.ANNUAL_HOME_INSURANCE = this.tfANNUAL_HOME_INSURANCE.toDouble();
        this.RC.MONTHLY_PMI = this.tfMONTHLY_PMI.toDouble();
        this.RC.NEW_RATE = this.tfNEW_RATE.toDouble();
        this.RC.CLOSING_COSTS = this.tfCLOSING_COSTS.toDouble();
    }
}
