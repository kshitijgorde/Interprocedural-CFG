import KJEgui.Fmt;
import KJEgraph.GraphDataSeries;
import KJEgraph.GraphType;
import KJEgraph.GraphLine;
import java.awt.Component;
import KJEgui.DataPanel;
import KJEcalculation.Calculation;
import java.awt.CheckboxGroup;
import java.applet.Applet;
import java.awt.Checkbox;
import KJEgraph.Graph;
import java.awt.Label;
import java.awt.Choice;
import KJEgui.Nbr;
import KJEgui.CalculatorApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MortgagePayoff extends CalculatorApplet
{
    MortgagePayoffCalc RC;
    Nbr tfMORTGAGE_YRS_LEFT;
    Nbr tfMORTGAGE_AMT;
    Nbr tfINCREASE_BY_AMT;
    Nbr tfRATE;
    Choice cmbTERM;
    Label tfMORTGAGE_PAYMENT_TOTAL;
    Label tfEARLY_PAYOFF_PAYMENT_TOTAL;
    Label tfPI_PAYMENT;
    Label tfEARLY_PAYOFF_PI_PAYMENT;
    Label tfSAVINGS;
    Graph gGraph;
    Checkbox cbYEAR;
    Checkbox cbMONTH;
    String MSG_GRAPH_TITLE;
    String MSG_GRAPH1;
    String MSG_GRAPH2;
    String MSG_GRAPH3;
    String MSG_GRAPH4;
    
    public MortgagePayoff() {
        this.RC = new MortgagePayoffCalc();
        this.tfMORTGAGE_PAYMENT_TOTAL = new Label("");
        this.tfEARLY_PAYOFF_PAYMENT_TOTAL = new Label("");
        this.tfPI_PAYMENT = new Label("");
        this.tfEARLY_PAYOFF_PI_PAYMENT = new Label("");
        this.tfSAVINGS = new Label("            ");
    }
    
    public void initCalculatorApplet() {
        super.nStack = 1;
        super.bUseNorth = true;
        super.bUseSouth = true;
        super.bUseWest = false;
        super.bUseEast = false;
        this.MSG_GRAPH1 = this.getParameter("MSG_GRAPH1", "Interest with prepayments");
        this.MSG_GRAPH2 = this.getParameter("MSG_GRAPH2", "Scheduled interest paid");
        this.MSG_GRAPH3 = this.getParameter("MSG_GRAPH3", "Balance with prepayments");
        this.MSG_GRAPH4 = this.getParameter("MSG_GRAPH4", "Scheduled principal balance");
        this.MSG_GRAPH_TITLE = this.getParameter("MSG_GRAPH_TITLE", "Mortgage repayment shortened by MSG_YEARS years and MSG_MONTHS months");
        this.RC.MSG_ERROR1 = this.getParameter("MSG_ERROR1", this.RC.MSG_ERROR1);
        this.tfRATE = new Nbr("RATE", "Annual interest rate", 1.0, 20.0, 3, 4, this);
        this.tfMORTGAGE_AMT = new Nbr("MORTGAGE_AMT", "Mortgage amount", 1.0, 1.0E8, 0, 3, this);
        this.tfMORTGAGE_YRS_LEFT = new Nbr("MORTGAGE_YRS_LEFT", "Number of years remaining", 0.0, this.getParameter("MORTGAGE_YRS_LENGTH", 30), 0, 5, this);
        this.tfINCREASE_BY_AMT = new Nbr("INCREASE_BY_AMT", "Additional monthly payment", 0.0, 10000.0, 0, 3, this);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.cbYEAR = new Checkbox(this.getParameter("MSG_LABEL1", "Amortization schedule by year"), checkboxGroup, true);
        this.cbMONTH = new Checkbox(this.getParameter("MSG_LABEL2", "Amortization schedule by Month"), checkboxGroup, false);
        this.cbYEAR.setBackground(this.getBackground());
        this.cbMONTH.setBackground(this.getBackground());
        this.setCalculation(this.RC);
        this.cmbTERM = this.getMortgageTermChoice(this.getParameter("MORTGAGE_YRS_LENGTH", 30));
    }
    
    public void initPanels() {
        final DataPanel dataPanel = new DataPanel();
        dataPanel.setBackground(this.getBackground());
        dataPanel.addRow(this.tfMORTGAGE_YRS_LEFT, String.valueOf(this.getParameter("MSG_LABEL3", "Monthly scheduled payment")) + super._COLON + " ", this.tfPI_PAYMENT, this.getPlainFont(), 1);
        dataPanel.addRow(String.valueOf(this.getParameter("MSG_LABEL4", "Mortgage length")) + super._COLON, this.cmbTERM, String.valueOf(this.getParameter("MSG_LABEL5", "Monthly accelerated payment")) + super._COLON + " ", this.tfEARLY_PAYOFF_PI_PAYMENT, this.getPlainFont(), 2);
        dataPanel.addRow(this.tfMORTGAGE_AMT, String.valueOf(this.getParameter("MSG_LABEL6", "Total scheduled payments")) + super._COLON + " ", this.tfMORTGAGE_PAYMENT_TOTAL, this.getPlainFont(), 3);
        dataPanel.addRow(this.tfINCREASE_BY_AMT, String.valueOf(this.getParameter("MSG_LABEL7", "Total accelerated payments")) + super._COLON + " ", this.tfEARLY_PAYOFF_PAYMENT_TOTAL, this.getPlainFont(), 4);
        dataPanel.addRow(this.tfRATE, String.valueOf(this.getParameter("MSG_LABEL8", "Total savings")) + super._COLON + " ", this.tfSAVINGS, this.getBoldFont(), 5);
        dataPanel.addRow(new Label(""), this.getTinyFont(), 6);
        final DataPanel dataPanel2 = new DataPanel();
        dataPanel2.setBackground(this.getBackground());
        dataPanel2.addRow("", this.cbYEAR, "", this.cbMONTH, this.getPlainFont(), 1);
        dataPanel2.addRow("", new Label(""), this.getTinyFont(), 2);
        this.gGraph = new Graph(new GraphLine(), this.imageBackground());
        this.gGraph._legend.setVisible(true);
        this.gGraph._legend.setOrientation(1);
        this.gGraph._titleXAxis.setText(this.getParameter("MSG_LABEL9", "Year of Mortgage"));
        this.gGraph._titleGraph.setText(this.getParameter("MSG_LABEL10", "Total savings"));
        this.gGraph._titleYAxis.setText(this.getParameter("MSG_LABEL11", "Dollars"));
        this.gGraph.FONT_TITLE = this.getGraphTitleFont();
        this.gGraph.FONT_BOLD = this.getBoldFont();
        this.gGraph.FONT_PLAIN = this.getPlainFont();
        this.gGraph.setForeground(this.getForeground());
        this.addDataPanel(dataPanel);
        this.addDataPanel(dataPanel2);
        this.addGraph(this.gGraph);
    }
    
    public void refresh() {
        this.gGraph.removeAll();
        this.gGraph.setBackground(this.getColor(1));
        this.gGraph.setGraphCatagories(this.RC.getCatagories());
        this.gGraph._bUseTextImages = false;
        try {
            this.gGraph.add(new GraphDataSeries(this.RC.aNewInterestPaid, this.MSG_GRAPH1, this.getGraphColor(2)));
            this.gGraph.add(new GraphDataSeries(this.RC.aInterestPaid, this.MSG_GRAPH2, this.getGraphColor(1)));
            this.gGraph.add(new GraphDataSeries(this.RC.aNewPrincipalBalance, this.MSG_GRAPH3, this.getGraphColor(4)));
            this.gGraph.add(new GraphDataSeries(this.RC.aPrincipalBalance, this.MSG_GRAPH4, this.getGraphColor(3)));
        }
        catch (Exception ex) {
            System.out.println("Huh?");
        }
        this.setTitle("Mortgage Payoff Calculator");
        this.tfMORTGAGE_PAYMENT_TOTAL.setText(Fmt.dollars(this.RC.MORTGAGE_PAYMENT_TOTAL));
        this.tfEARLY_PAYOFF_PAYMENT_TOTAL.setText(Fmt.dollars(this.RC.EARLY_PAYOFF_PAYMENT_TOTAL));
        this.tfEARLY_PAYOFF_PI_PAYMENT.setText(Fmt.dollars(this.RC.EARLY_PAYOFF_PI_PAYMENT));
        this.tfSAVINGS.setText(Fmt.dollars(this.RC.EARLY_PAYOFF_SAVINGS));
        this.tfPI_PAYMENT.setText(Fmt.dollars(this.RC.PI_PAYMENT));
        this.gGraph._titleGraph.setText(Calculation.replace("MSG_YEARS", Fmt.number(this.RC.EARLY_PAYOFF_YEARS), Calculation.replace("MSG_MONTHS", Fmt.number(this.RC.EARLY_PAYOFF_MONTHS), this.MSG_GRAPH_TITLE)));
        this.gGraph._titleYAxis.setText(this.RC.getAmountLabel());
        this.gGraph.dataChanged(true);
    }
    
    public void setValues() throws NumberFormatException {
        this.RC.MORTGAGE_YRS_LEFT = this.tfMORTGAGE_YRS_LEFT.toDouble();
        this.RC.MORTGAGE_YRS_LENGTH = this.getMortgageTerm(this.cmbTERM);
        this.RC.MORTGAGE_AMT = this.tfMORTGAGE_AMT.toDouble();
        this.RC.INCREASE_BY_AMT = this.tfINCREASE_BY_AMT.toDouble();
        this.RC.RATE = this.tfRATE.toDouble();
        if (this.cbYEAR.getState()) {
            this.RC.BY_YEAR = 1;
        }
        else {
            this.RC.BY_YEAR = 0;
        }
    }
}
