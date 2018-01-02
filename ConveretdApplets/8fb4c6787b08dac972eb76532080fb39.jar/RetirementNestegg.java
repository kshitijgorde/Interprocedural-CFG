import KJEgraph.GraphDataSeries;
import KJEgui.Fmt;
import KJEgui.DataPanel;
import javax.swing.JPanel;
import KJEgraph.GraphType;
import KJEgraph.GraphCatagories;
import javax.swing.JComponent;
import java.awt.Component;
import KJEcalculation.Calculation;
import java.applet.Applet;
import javax.swing.JLabel;
import KJEgui.TextWrap;
import KJEgraph.Graph;
import javax.swing.JCheckBox;
import KJEgui.Nbr;
import KJEgui.CalculatorApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RetirementNestegg extends CalculatorApplet
{
    String sGraph1;
    String sGraph2;
    String sGraph3;
    String sLABEL_5;
    String sLABEL_6;
    String sLABEL_7;
    String sLABEL_8;
    String sLABEL_9;
    String sLABEL_10;
    Nbr tfCURRENT_AGE;
    Nbr tfHOUSEHOLD_INCOME;
    Nbr tfPRE_RATE_OF_RETURN;
    Nbr tfAGE_OF_RETIREMENT;
    Nbr tfPOST_RATE_OF_RETURN;
    Nbr tfSALARY_PERCENT;
    Nbr tfYEARS_OF_RETIREMENT;
    Nbr tfINCOME_PERCENT;
    Nbr tfCURRENT_SAVINGS;
    Nbr tfINFLATION_RATE;
    Nbr tfSAVINGS_PERCENT;
    Nbr tfANNUAL_SAVINGS;
    Nbr tfCURRENT_TAX;
    Nbr tfRETIREMENT_TAX;
    JCheckBox cbMarried;
    JCheckBox cbIncludeSocialSecurity;
    JCheckBox cbTaxdeferred;
    int iInflationBox;
    Graph gGraph;
    RetirementPlanCalculation RC;
    boolean bMorespace;
    Nbr[] tfOTHER_PENSION_AMOUNT;
    Nbr[] tfOTHER_PENSION_START;
    String MSG_GRAPH_TITLE;
    String MSG_TITLE;
    JCheckBox[] cbOtherPensions;
    boolean SHOW_SAVINGS_CHOICE;
    TextWrap tfRETIREMENT_PLAN_TEXT;
    boolean bShowTax;
    JLabel tfYEARS_UNTIL_RETIREMENT;
    JLabel tfSOCIAL_SECURITY_PERCENT;
    JLabel tfENDING_BALANCE;
    
    public void initCalculatorApplet() {
        this.RC = new RetirementPlanCalculation();
        this.bMorespace = false;
        this.tfOTHER_PENSION_AMOUNT = new Nbr[3];
        this.tfOTHER_PENSION_START = new Nbr[3];
        this.MSG_GRAPH_TITLE = "You need to save ADJUST_MONTHLY_SAVINGS per month to meet your retirement needs.";
        this.MSG_TITLE = "You may need RETIRE_AMOUNT to retire at age AGE_OF_RETIREMENT";
        this.SHOW_SAVINGS_CHOICE = false;
        this.tfRETIREMENT_PLAN_TEXT = new TextWrap();
        this.bShowTax = false;
        this.tfYEARS_UNTIL_RETIREMENT = this.JOutputLabel();
        this.tfSOCIAL_SECURITY_PERCENT = this.JOutputLabel();
        this.tfENDING_BALANCE = this.JOutputLabel();
        this.cbMarried = this.JCheckBox("LABEL_2", "Check here", this.getColor(1), this.getParameter("MARRIED", false));
        this.cbIncludeSocialSecurity = this.JCheckBox("LABEL_2", "Check here", this.getColor(1), this.getParameter("INCLUDE_SOCIAL_SECURITY", true));
        this.cbTaxdeferred = this.JCheckBox("LABEL_2", "Check here", this.getColor(1), this.getParameter("TAX_DEFERRED", false));
        this.RC.SHOW_SOCIAL = this.getParameter("SHOW_SOCIAL", true);
        this.RC.OTHER_PENSION_COUNT = 0;
        this.MSG_GRAPH_TITLE = this.getParameter("MSG_GRAPH_TITLE", this.MSG_GRAPH_TITLE);
        this.MSG_TITLE = this.getParameter("MSG_TITLE", this.MSG_TITLE);
        this.tfCURRENT_AGE = new Nbr("CURRENT_AGE", "Current age", 14.0, 90.0, 0, 5, this);
        this.tfHOUSEHOLD_INCOME = new Nbr("HOUSEHOLD_INCOME", "Household income", 1.0, 5000000.0, 0, 3, this);
        this.tfPRE_RATE_OF_RETURN = new Nbr("PRE_RATE_OF_RETURN", "Rate of return before retirement", 0.0, this.getRORMaximum(20.0), 2, 4, this);
        this.tfAGE_OF_RETIREMENT = new Nbr("AGE_OF_RETIREMENT", "Age of retirement", 10.0, 90.0, 0, 5, this);
        this.tfPOST_RATE_OF_RETURN = new Nbr("POST_RATE_OF_RETURN", "Rate of return during retirement", 0.0, this.getRORMaximum(20.0), 2, 4, this);
        this.tfSALARY_PERCENT = new Nbr("SALARY_PERCENT", "Expected salary increase", 0.0, this.getParameter("MAX_SALARY_PERCENT", 20.0), 2, 4, this);
        this.tfYEARS_OF_RETIREMENT = new Nbr("YEARS_OF_RETIREMENT", "Years of retirement income", 1.0, 100.0, 0, 5, this);
        this.tfINCOME_PERCENT = new Nbr("INCOME_PERCENT", "Percent of income at retirement", 50.0, 120.0, 0, 4, this);
        this.tfCURRENT_SAVINGS = new Nbr("CURRENT_SAVINGS", "Current retirement savings", 0.0, 1.0E7, 0, 3, this);
        this.tfINFLATION_RATE = new Nbr("INFLATION_RATE", "Expected rate of inflation", 0.0, 8.0, 2, 4, this);
        this.RC.MSG_ERROR2 = this.getParameter("MSG_ERROR2", this.RC.MSG_ERROR2);
        this.RC.MSG_SOCIAL_SECURITY = this.getParameter("MSG_SOCIAL_SECURITY", this.RC.MSG_SOCIAL_SECURITY);
        this.RC.LABEL_3 = this.getParameter("LABEL_3", "Your plan is on track.");
        this.RC.LABEL_4 = this.getParameter("LABEL_4", "You may need to save more.");
        this.sLABEL_5 = this.getParameter("MSG_LABEL_5", "This assumes annual retirement expenses of");
        this.sLABEL_6 = this.getParameter("MSG_LABEL_6", "which is");
        this.sLABEL_7 = this.getParameter("MSG_LABEL_7", "of your last year's income of");
        this.sLABEL_8 = this.getParameter("LABEL_8", ".");
        this.sLABEL_9 = this.getParameter("LABEL_9", "This also includes");
        this.sLABEL_10 = this.getParameter("LABEL_10", "per year from Social Security.");
        this.RC.INFLATION_REPORT_MSG = this.getParameter("INFLATION_REPORT_MSG", this.RC.INFLATION_REPORT_MSG);
        this.RC.RESULT_START_MSG = String.valueOf(this.getParameter("RESULT_START_MSG", "Your plan provides")) + " ";
        this.RC.RESULT_START2_MSG = String.valueOf(this.getParameter("RESULT_START2_MSG", "when you retire.")) + " ";
        this.RC.RUNOUT_BEGIN_MSG = String.valueOf(this.getParameter("RUNOUT_BEGIN_MSG", "This retirement savings may run out at age")) + " ";
        this.RC.RUNOUT_END_MSG = this.getParameter("RUNOUT_END_MSG", ".");
        this.RC.SUCCESS_BEGIN_MSG = String.valueOf(this.getParameter("SUCCESS_BEGIN_MSG", "With this retirement savings you could end your retirement with")) + " ";
        this.RC.SUCCESS_END_MSG = this.getParameter("SUCCESS_END_MSG", ".");
        this.sGraph2 = this.getParameter("GRAPH_2", "Projected Need:");
        this.sGraph3 = this.getParameter("GRAPH_3", "Current plan shortfall:");
        this.sGraph1 = this.getParameter("GRAPH_1", "Projected with Social Security:");
        this.RC.CAT_LABELS[0] = this.sGraph2;
        this.RC.CAT_LABELS[1] = this.sGraph1;
        this.RC.CAT_LABELS[2] = this.sGraph3;
        super.nStack = 2;
        super.bUseNorth = true;
        super.bUseSouth = (this.iInflationBox > 0);
        super.bUseWest = false;
        super.bUseEast = false;
        this.setCalculation(this.RC);
    }
    
    public void initPanels() {
        this.setTitle("Retirement Plan");
        this.tfRETIREMENT_PLAN_TEXT.setFont(this.getPlainFont());
        this.tfRETIREMENT_PLAN_TEXT.setBackground(this.getColor(1));
        this.tfRETIREMENT_PLAN_TEXT.setForeground(this.getForeground());
        final DataPanel jDataPanel = this.JDataPanel(this.getForeground(), this.getColor(1), this.tfCURRENT_AGE);
        int n = 1;
        jDataPanel.addSpacer(n++);
        jDataPanel.addRow(this.tfCURRENT_AGE, this.tfAGE_OF_RETIREMENT, this.getPlainFont(), n++);
        jDataPanel.addRow(this.tfHOUSEHOLD_INCOME, this.tfCURRENT_SAVINGS, this.getPlainFont(), n++);
        jDataPanel.addRow(this.tfPRE_RATE_OF_RETURN, this.tfPOST_RATE_OF_RETURN, this.getPlainFont(), n++);
        jDataPanel.addRow(this.tfYEARS_OF_RETIREMENT, this.tfINCOME_PERCENT, this.getPlainFont(), n++);
        jDataPanel.addRow(this.tfSALARY_PERCENT, this.tfINFLATION_RATE, this.getPlainFont(), n++);
        if (this.RC.SHOW_SOCIAL) {
            jDataPanel.addRow(this.getParameter("MARRIED_LABEL", "If you are married"), this.cbMarried, this.getParameter("SOCIAL_SECURITY_LABEL", "To include Social Security"), this.cbIncludeSocialSecurity, this.getPlainFont(), n++);
        }
        jDataPanel.addSpacer(n++);
        super.GRID_GAP_HOR = 0;
        this.setGraphDefaults(this.gGraph = new Graph(new GraphCatagories(), this.imageBackground()), this.getForeground(), this.getColor(2));
        this.gGraph._bUseTextImages = false;
        this.gGraph._legend.setVisible(true);
        this.gGraph._titleXAxis.setText("");
        this.gGraph._axisX.setVisible(false);
        this.gGraph._axisY.setVisible(true);
        this.addPanel(jDataPanel);
        if (this.iInflationBox > 0) {
            final JPanel panel = new JPanel();
            panel.setBackground(this.getBackground());
            panel.setForeground(this.getForeground());
            if (this.getParameter("MSG_OTHER_PENSION_AMOUNT") != null) {
                final JLabel jOutputLabel = this.JOutputLabel("MSG_OTHER_PENSION_AMOUNT", 4);
                jOutputLabel.setFont(this.getPlainFont());
                jOutputLabel.setBackground(this.getBackground());
                jOutputLabel.setForeground(this.getForeground());
                panel.add(jOutputLabel);
            }
            for (int i = 0; i < this.cbOtherPensions.length; ++i) {
                if (this.cbOtherPensions[i] != null) {
                    this.cbOtherPensions[i].setFont(this.getPlainFont());
                    this.cbOtherPensions[i].setBackground(this.getColor(1));
                    this.cbOtherPensions[i].setForeground(this.getForeground());
                    panel.add(this.cbOtherPensions[i]);
                }
            }
            this.addPanel(panel);
        }
        this.addPanel(this.gGraph);
    }
    
    public void refresh() {
        this.gGraph.removeAll();
        this.gGraph.setGraphCatagories(this.RC.getCatagories2());
        this.gGraph._legend.setVisible(true);
        this.gGraph._legend.setOrientation(6);
        this.gGraph._colorList = this.getGraphColors();
        this.gGraph._showItemLabel = true;
        this.gGraph._showItemLabelOnTop = true;
        this.gGraph.setGraphUnits(this.RC.dMax2);
        this.gGraph._axisX._fSpacingPercent = 0.2f;
        this.gGraph._titleGraph.setText(Calculation.replace("ADJUST_MONTHLY_SAVINGS", Fmt.dollars(this.RC.ADJUST_MONTHLY_SAVINGS, 0), this.MSG_GRAPH_TITLE));
        try {
            this.gGraph.add(new GraphDataSeries(this.RC.DS_RETIRE1, this.sGraph2, this.getGraphColor(1)));
            this.gGraph._axisY._bAutoMinimum = false;
            this.gGraph._axisY._axisMinimum = 0.0f;
            this.gGraph._axisY._bAutoMaximum = true;
        }
        catch (Exception ex) {
            System.out.println("Huh?");
        }
        this.tfYEARS_UNTIL_RETIREMENT.setText(Fmt.number(this.RC.YEARS_UNTIL_RETIREMENT));
        this.tfSOCIAL_SECURITY_PERCENT.setText(Fmt.percent(this.RC.SOCIAL_SECURITY_PERCENT));
        this.tfENDING_BALANCE.setText(Fmt.dollars(this.RC.ENDING_BALANCE));
        this.gGraph._titleYAxis.setText(this.RC.getAmountLabel2());
        this.gGraph.dataChanged(true);
        this.setTitle(Calculation.replace("AGE_OF_RETIREMENT", Fmt.number(this.RC.AGE_OF_RETIREMENT), Calculation.replace("RETIRE_AMOUNT", Fmt.dollars(this.RC.DD_RETIRE1[0]), this.MSG_TITLE)));
        final String string = String.valueOf(this.RC.END_OF_RETIREMENT_MESSAGE) + " " + this.sLABEL_5 + " " + Fmt.dollars(this.RC.INCOME_REQUIRED_AT_RETIRE) + " " + this.sLABEL_6 + " " + Fmt.percent(this.RC.INCOME_PERCENT / 100.0) + " " + this.sLABEL_7 + " " + Fmt.dollars(this.RC.INCOME_AT_RETIRE) + this.sLABEL_8;
        String text;
        if (this.bMorespace) {
            if (this.RC.ENDING_BALANCE <= 0.0) {
                text = " // " + this.RC.LABEL_4 + " " + string;
            }
            else {
                text = " // " + this.RC.LABEL_3 + " " + string;
            }
        }
        else if (this.RC.ENDING_BALANCE <= 0.0) {
            text = " // " + this.RC.LABEL_4 + " // // " + string;
        }
        else {
            text = " // " + this.RC.LABEL_3 + " // // " + string;
        }
        if (this.RC.INCLUDE_SOCIAL_SECURITY > 0.0) {
            text = String.valueOf(text) + " " + this.sLABEL_9 + " " + Fmt.dollars(this.RC.SOCIALSECURITY_AT_RETIRE) + " " + this.sLABEL_10;
        }
        this.tfRETIREMENT_PLAN_TEXT.setText(text);
        this.tfRETIREMENT_PLAN_TEXT.repaint();
        this.tfINCOME_PERCENT.setText(Fmt.percent((this.RC.INCOME_PERCENT < 0.0) ? 0.0 : (this.RC.INCOME_PERCENT / 100.0)));
    }
    
    public void setValues() throws NumberFormatException {
        this.RC.CURRENT_AGE = this.tfCURRENT_AGE.toDouble();
        this.RC.HOUSEHOLD_INCOME = this.tfHOUSEHOLD_INCOME.toDouble();
        this.RC.PRE_RATE_OF_RETURN = this.tfPRE_RATE_OF_RETURN.toDouble();
        this.RC.AGE_OF_RETIREMENT = this.tfAGE_OF_RETIREMENT.toDouble();
        this.RC.POST_RATE_OF_RETURN = this.tfPOST_RATE_OF_RETURN.toDouble();
        this.RC.SALARY_PERCENT = this.tfSALARY_PERCENT.toDouble();
        this.RC.YEARS_OF_RETIREMENT = this.tfYEARS_OF_RETIREMENT.toDouble();
        this.RC.INCOME_PERCENT = this.tfINCOME_PERCENT.toDouble();
        this.RC.CURRENT_SAVINGS = this.tfCURRENT_SAVINGS.toDouble();
        this.RC.INFLATION_RATE = this.tfINFLATION_RATE.toDouble();
        this.RC.SAVINGS_PERCENT = 0.0;
        this.RC.INFLATION_RESULTS = false;
        this.RC.INCREASE_ANNUAL_SAVINGS = false;
        if (this.cbIncludeSocialSecurity.isSelected()) {
            this.RC.INCLUDE_SOCIAL_SECURITY = 1.0;
        }
        else {
            this.RC.INCLUDE_SOCIAL_SECURITY = 0.0;
        }
        if (this.cbMarried.isSelected()) {
            this.RC.MARRIED = 1.0;
        }
        else {
            this.RC.MARRIED = 0.0;
        }
        this.RC.DEFERRED = 0.0;
        this.RC.CURRENT_TAX = 0.0;
        this.RC.RETIREMENT_TAX = 0.0;
    }
}
