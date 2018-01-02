import KJEcalculation.CalculationException;
import KJEgui.Fmt;
import KJEcalculation.Calculation;

// 
// Decompiled by Procyon v0.5.30
// 

public class MortgagePointsCalculation extends Calculation
{
    public boolean bShort;
    public double INTEREST_RATE;
    public double TERM;
    public double LOAN_AMOUNT;
    public double MARGINAL_TAX_RATE;
    public double YEARS_IN_HOME;
    public double DISCOUNT_POINTS_PERCENT;
    public double DISCOUNT_POINTS_RATE;
    public double DISCOUNT_POINTS_AMT;
    public double POINTS_MONTHLY_PI;
    public double POINTS_INTEREST_SAVINGS;
    public double DISCOUNT_POINTS_DIFFERENCE;
    public double POINTS_TAX_REDUCTION;
    public double POINTS_COST_AFTERTAX;
    public double POINTS_HOME_EQUITY_AT_MOVEOUT;
    public double POINTS_TOTAL_OF_PAYMENTS;
    public double POINTS_INTEREST_PAID;
    public double DOWNPAYMENT_HOME_EQUITY_AT_MOVEOUT;
    public double DOWNPAYMENT_TOTAL_OF_PAYMENTS;
    public double DOWNPAYMENT_INTEREST_PAID;
    public double DOWNPAYMENT_MONTHLY_PI;
    public double DOWNPAYMENT_LOAN_AMOUNT;
    public double EQUITY_DIFFERENCE;
    public double INTEREST_DIFFERENCE;
    public double TOTAL_DIFFERENCE;
    public String TITLE_MESSAGE;
    public String RESULTS_MESSAGE;
    public double GRAPH_MAXIMUM;
    public int BY_YEAR;
    int iFactor;
    public float[] DS_PRINCIPAL_BAL;
    public float[] DS_POINTS_PRINCIPAL_BAL;
    public float[] DS_INTEREST;
    public float[] DS_PRINCIPAL;
    public String _sAmountLabel;
    public String[] cats;
    public String[] totalpaid_cats;
    public String TITLE_MESSAGE1;
    public String TITLE_MESSAGE2;
    public String RESULTS_START1;
    public String RESULTS_START2;
    public String RESULTS_MIDDLE1;
    public String RESULTS_MIDDLE2;
    public String RESULTS_END1;
    public String RESULTS_END2;
    
    public MortgagePointsCalculation() {
        this.bShort = false;
        this.INTEREST_RATE = 0.0;
        this.TERM = 0.0;
        this.LOAN_AMOUNT = 0.0;
        this.MARGINAL_TAX_RATE = 0.0;
        this.YEARS_IN_HOME = 0.0;
        this.DISCOUNT_POINTS_PERCENT = 0.0;
        this.DISCOUNT_POINTS_RATE = 0.0;
        this.DISCOUNT_POINTS_AMT = 0.0;
        this.POINTS_MONTHLY_PI = 0.0;
        this.POINTS_INTEREST_SAVINGS = 0.0;
        this.DISCOUNT_POINTS_DIFFERENCE = 0.0;
        this.POINTS_TAX_REDUCTION = 0.0;
        this.POINTS_COST_AFTERTAX = 0.0;
        this.POINTS_HOME_EQUITY_AT_MOVEOUT = 0.0;
        this.POINTS_TOTAL_OF_PAYMENTS = 0.0;
        this.POINTS_INTEREST_PAID = 0.0;
        this.DOWNPAYMENT_HOME_EQUITY_AT_MOVEOUT = 0.0;
        this.DOWNPAYMENT_TOTAL_OF_PAYMENTS = 0.0;
        this.DOWNPAYMENT_INTEREST_PAID = 0.0;
        this.DOWNPAYMENT_MONTHLY_PI = 0.0;
        this.DOWNPAYMENT_LOAN_AMOUNT = 0.0;
        this.EQUITY_DIFFERENCE = 0.0;
        this.INTEREST_DIFFERENCE = 0.0;
        this.TOTAL_DIFFERENCE = 0.0;
        this.TITLE_MESSAGE = "";
        this.RESULTS_MESSAGE = "";
        this.GRAPH_MAXIMUM = 0.0;
        this.BY_YEAR = 1;
        this.iFactor = 0;
        this._sAmountLabel = "";
        this.totalpaid_cats = new String[] { "With Points", "Without Points" };
        this.TITLE_MESSAGE1 = "Buying points can save you TOTAL_DIFF over YEARS_IN_HOME years.";
        this.TITLE_MESSAGE2 = "Buying points may cost you TOTAL_DIFF over YEARS_IN_HOME years.";
        this.RESULTS_START1 = "Buying points, in this case, can save you TOTAL_DIFF.";
        this.RESULTS_START2 = "Buying points, in this case, may cost you TOTAL_DIFF.";
        this.RESULTS_MIDDLE1 = "This includes INTEREST_DIFF less in interest paid";
        this.RESULTS_MIDDLE2 = "This includes INTEREST_DIFF more in interest paid";
        this.RESULTS_END1 = "and EQUITY_DIFF less equity in your home.";
        this.RESULTS_END2 = "and EQUITY_DIFF more equity in your home.";
    }
    
    private void addPointRow(final int n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7) {
        if (this.bShort) {
            this.addRepeat("<TD>" + super._sCell + Fmt.number(n) + super._sCellFormat + Fmt.dollars(n2) + super._sCellFormat + Fmt.dollars(n4) + super._sCellFormat + Fmt.dollars(n5) + super._sCellFormat + Fmt.dollars(n7) + super._sCellFooter + "</TD>");
        }
        else {
            this.addRepeat("<TD>" + super._sCell + Fmt.number(n) + super._sCellFormat + Fmt.dollars(n2) + super._sCellFormat + Fmt.dollars(n3) + super._sCellFormat + Fmt.dollars(n4) + super._sCellFormat + Fmt.dollars(n5) + super._sCellFormat + Fmt.dollars(n6) + super._sCellFormat + Fmt.dollars(n7) + super._sCellFooter + "</TD>");
        }
    }
    
    private void addPointsTitle(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        if (this.bShort) {
            this.addRepeat("<TD>" + super._sCell + "&nbsp;" + super._sCellFooter + "</TD><TD COLSPAN=2>" + super._sCell + this.sReportCol("<CENTER><U><B>Without Points Payment Schedule</B></U></CENTER>", 8) + super._sCellFooter + "</TD>" + "<TD COLSPAN=2>" + super._sCell + this.sReportCol("<CENTER><U><B>With Points Payment Schedule</B></U></CENTER>", 9) + super._sCellFooter + "</TD>" + super._sRowFooter + super._sTopRow + "<TD>" + super._sCell + "<B>" + s + "</B>" + super._sCellFormat + "<B>" + s2 + "</B>" + super._sCellFormat + "<B>" + s4 + "</B>" + super._sCellFormat + "<B>" + s5 + "</B>" + super._sCellFormat + "<B>" + s7 + "</B>" + super._sCellFooter + "</TD>");
        }
        else {
            this.addRepeat("<TD>" + super._sCell + "&nbsp;" + super._sCellFooter + "</TD><TD COLSPAN=3>" + super._sCell + this.sReportCol("<CENTER><U><B>Without Points Payment Schedule</B></U></CENTER>", 8) + super._sCellFooter + "</TD>" + "<TD COLSPAN=3>" + super._sCell + this.sReportCol("<CENTER><U><B>With Points Payment Schedule</B></U></CENTER>", 9) + super._sCellFooter + "</TD>" + super._sRowFooter + super._sTopRow + "<TD>" + super._sCell + "<B>" + s + "</B>" + super._sCellFormat + "<B>" + s2 + "</B>" + super._sCellFormat + "<B>" + s3 + "</B>" + super._sCellFormat + "<B>" + s4 + "</B>" + super._sCellFormat + "<B>" + s5 + "</B>" + super._sCellFormat + "<B>" + s6 + "</B>" + super._sCellFormat + "<B>" + s7 + "</B>" + super._sCellFooter + "</TD>");
        }
    }
    
    protected void calculate() throws CalculationException {
        if (this.YEARS_IN_HOME == 0.0) {
            this.YEARS_IN_HOME = this.TERM;
        }
        else if (this.YEARS_IN_HOME > this.TERM) {
            this.YEARS_IN_HOME = this.TERM;
        }
        final double n = this.YEARS_IN_HOME * 12.0;
        final double n2 = this.INTEREST_RATE / 1200.0;
        final double n3 = this.DISCOUNT_POINTS_RATE / 1200.0;
        double n4;
        if (this.MARGINAL_TAX_RATE == 0.0) {
            n4 = 0.0;
        }
        else {
            n4 = this.MARGINAL_TAX_RATE / 100.0;
        }
        this.DISCOUNT_POINTS_AMT = this.DISCOUNT_POINTS_PERCENT / 100.0 * this.LOAN_AMOUNT;
        this.POINTS_TAX_REDUCTION = this.DISCOUNT_POINTS_AMT * n4;
        this.POINTS_COST_AFTERTAX = this.DISCOUNT_POINTS_AMT - this.POINTS_TAX_REDUCTION;
        this.POINTS_MONTHLY_PI = Calculation.PMT(this.DISCOUNT_POINTS_RATE / 1200.0, this.TERM * 12.0, this.LOAN_AMOUNT);
        this.DOWNPAYMENT_LOAN_AMOUNT = this.LOAN_AMOUNT - this.POINTS_COST_AFTERTAX;
        this.DOWNPAYMENT_MONTHLY_PI = Calculation.PMT(this.INTEREST_RATE / 1200.0, this.TERM * 12.0, this.DOWNPAYMENT_LOAN_AMOUNT);
        this.DISCOUNT_POINTS_DIFFERENCE = Fmt.round(this.DOWNPAYMENT_MONTHLY_PI, 2) - Fmt.round(this.POINTS_MONTHLY_PI, 2);
        final int n5 = (int)Math.round(this.YEARS_IN_HOME) + 1;
        this.iFactor = this.getGraphUnits(this.LOAN_AMOUNT);
        this.DS_PRINCIPAL_BAL = new float[n5];
        this.DS_POINTS_PRINCIPAL_BAL = new float[n5];
        this.cats = new String[n5];
        final StringBuffer sb = new StringBuffer();
        double loan_AMOUNT = this.LOAN_AMOUNT;
        double n6 = 0.0;
        double n7 = 0.0;
        double n8 = 0.0;
        final double points_MONTHLY_PI = this.POINTS_MONTHLY_PI;
        double downpayment_LOAN_AMOUNT = this.DOWNPAYMENT_LOAN_AMOUNT;
        double n9 = 0.0;
        double n10 = 0.0;
        double n11 = 0.0;
        final double downpayment_MONTHLY_PI = this.DOWNPAYMENT_MONTHLY_PI;
        int n12 = 0;
        this.cats[n12] = "0";
        this.DS_POINTS_PRINCIPAL_BAL[n12] = (float)(this.LOAN_AMOUNT / this.iFactor);
        this.DS_PRINCIPAL_BAL[n12] = (float)(this.DOWNPAYMENT_LOAN_AMOUNT / this.iFactor);
        ++n12;
        if (super.bWithSchedule) {
            if (this.BY_YEAR != 1) {
                this.addPointsTitle(this.sReportCol("<BR><BR>Nbr", 1), this.sReportCol("<BR><BR>Payment", 2), this.sReportCol("<BR><BR>Interest", 3), this.sReportCol("Ending<BR>Balance", 4), this.sReportCol("<BR><BR>Payment", 2), this.sReportCol("<BR><BR>Interest", 3), this.sReportCol("<BR>Ending<BR>Balance", 4));
            }
            else {
                this.addPointsTitle(this.sReportCol("<BR><BR>Yr", 5), this.sReportCol("<BR>Total<BR>Payment", 6), this.sReportCol("<BR>Interest<BR>Paid", 7), this.sReportCol("<BR>Ending<BR>Balance", 4), this.sReportCol("<BR>Total<BR>Payments", 6), this.sReportCol("<BR>Interest<BR>Paid", 7), this.sReportCol("<BR>Ending<BR>Balance", 4));
            }
            if (this.bShort) {
                this.addRepeat("<TD>" + super._sCell + "&nbsp;" + super._sCellFormat + "&nbsp;" + super._sCellFormat + Fmt.dollars(downpayment_LOAN_AMOUNT) + super._sCellFormat + "&nbsp;" + super._sCellFormat + Fmt.dollars(loan_AMOUNT) + super._sCellFooter + "</TD>");
            }
            else {
                this.addRepeat("<TD>" + super._sCell + "&nbsp;" + super._sCellFormat + "&nbsp;" + super._sCellFormat + "&nbsp;" + super._sCellFormat + Fmt.dollars(downpayment_LOAN_AMOUNT) + super._sCellFormat + "&nbsp;" + super._sCellFormat + "&nbsp;" + super._sCellFormat + Fmt.dollars(loan_AMOUNT) + super._sCellFooter + "</TD>");
            }
        }
        for (int n13 = 1; n13 <= this.TERM * 12.0; ++n13) {
            double points_MONTHLY_PI2 = this.POINTS_MONTHLY_PI;
            double downpayment_MONTHLY_PI2 = this.DOWNPAYMENT_MONTHLY_PI;
            double n14;
            double n15;
            if (downpayment_LOAN_AMOUNT == 0.0) {
                downpayment_MONTHLY_PI2 = 0.0;
                n14 = 0.0;
                n15 = 0.0;
            }
            else {
                n15 = n2 * downpayment_LOAN_AMOUNT;
                n14 = downpayment_MONTHLY_PI2 - n15;
                downpayment_LOAN_AMOUNT -= n14;
                if (downpayment_LOAN_AMOUNT < 0.0) {
                    downpayment_MONTHLY_PI2 += downpayment_LOAN_AMOUNT;
                    downpayment_LOAN_AMOUNT = 0.0;
                    n14 = downpayment_MONTHLY_PI2;
                }
            }
            double n16;
            double n17;
            if (loan_AMOUNT == 0.0) {
                points_MONTHLY_PI2 = 0.0;
                n16 = 0.0;
                n17 = 0.0;
            }
            else {
                n17 = n3 * loan_AMOUNT;
                n16 = points_MONTHLY_PI2 - n17;
                loan_AMOUNT -= n16;
                if (loan_AMOUNT < 0.0) {
                    points_MONTHLY_PI2 += loan_AMOUNT;
                    loan_AMOUNT = 0.0;
                    n16 = points_MONTHLY_PI2;
                }
            }
            n6 += n17;
            n7 += n16;
            n8 += points_MONTHLY_PI2;
            n9 += n15;
            n10 += n14;
            n11 += downpayment_MONTHLY_PI2;
            if (n13 <= n) {
                this.POINTS_TOTAL_OF_PAYMENTS += points_MONTHLY_PI2;
                this.POINTS_INTEREST_PAID += n17;
                this.DOWNPAYMENT_TOTAL_OF_PAYMENTS += downpayment_MONTHLY_PI2;
                this.DOWNPAYMENT_INTEREST_PAID += n15;
            }
            if (n13 == n) {
                this.POINTS_HOME_EQUITY_AT_MOVEOUT = this.LOAN_AMOUNT - loan_AMOUNT;
                this.DOWNPAYMENT_HOME_EQUITY_AT_MOVEOUT = this.DISCOUNT_POINTS_AMT + this.DOWNPAYMENT_LOAN_AMOUNT - downpayment_LOAN_AMOUNT;
                this.EQUITY_DIFFERENCE = this.DOWNPAYMENT_HOME_EQUITY_AT_MOVEOUT - this.POINTS_HOME_EQUITY_AT_MOVEOUT;
                this.INTEREST_DIFFERENCE = this.DOWNPAYMENT_INTEREST_PAID - this.POINTS_INTEREST_PAID;
                this.TOTAL_DIFFERENCE = this.INTEREST_DIFFERENCE - this.EQUITY_DIFFERENCE;
                this.RESULTS_MESSAGE = ((this.TOTAL_DIFFERENCE >= 0.0) ? this.RESULTS_START1 : this.RESULTS_START2);
                this.RESULTS_MESSAGE = String.valueOf(this.RESULTS_MESSAGE) + " " + ((this.INTEREST_DIFFERENCE >= 0.0) ? this.RESULTS_MIDDLE1 : this.RESULTS_MIDDLE2) + " ";
                this.RESULTS_MESSAGE = String.valueOf(this.RESULTS_MESSAGE) + ((this.EQUITY_DIFFERENCE >= 0.0) ? this.RESULTS_END1 : this.RESULTS_END2);
                this.RESULTS_MESSAGE = Calculation.replace("TOTAL_DIFF", Fmt.dollars(this.TOTAL_DIFFERENCE * ((this.TOTAL_DIFFERENCE >= 0.0) ? 1 : -1)), this.RESULTS_MESSAGE);
                this.RESULTS_MESSAGE = Calculation.replace("INTEREST_DIFF", Fmt.dollars(this.INTEREST_DIFFERENCE * ((this.INTEREST_DIFFERENCE >= 0.0) ? 1 : -1)), this.RESULTS_MESSAGE);
                this.RESULTS_MESSAGE = Calculation.replace("EQUITY_DIFF", Fmt.dollars(this.EQUITY_DIFFERENCE * ((this.EQUITY_DIFFERENCE >= 0.0) ? 1 : -1)), this.RESULTS_MESSAGE);
                this.TITLE_MESSAGE = ((this.TOTAL_DIFFERENCE >= 0.0) ? this.TITLE_MESSAGE1 : this.TITLE_MESSAGE2);
                this.TITLE_MESSAGE = Calculation.replace("YEARS_IN_HOME", Fmt.number(this.YEARS_IN_HOME), this.TITLE_MESSAGE);
                this.TITLE_MESSAGE = Calculation.replace("TOTAL_DIFF", Fmt.dollars(this.TOTAL_DIFFERENCE * ((this.TOTAL_DIFFERENCE >= 0.0) ? 1 : -1)), this.TITLE_MESSAGE);
            }
            if (this.BY_YEAR != 1 && super.bWithSchedule) {
                this.addPointRow(n13, downpayment_MONTHLY_PI2, n15, downpayment_LOAN_AMOUNT, points_MONTHLY_PI2, n17, loan_AMOUNT);
            }
            if (n13 % 12 == 0) {
                if (n13 <= n) {
                    this.cats[n12] = String.valueOf(n12);
                    this.DS_POINTS_PRINCIPAL_BAL[n12] = (float)(loan_AMOUNT / this.iFactor);
                    this.DS_PRINCIPAL_BAL[n12] = (float)(downpayment_LOAN_AMOUNT / this.iFactor);
                    ++n12;
                }
                if (this.BY_YEAR == 1 && super.bWithSchedule) {
                    this.addPointRow(n13 / 12, n11, n9, downpayment_LOAN_AMOUNT, n8, n6, loan_AMOUNT);
                }
                n9 = 0.0;
                n10 = 0.0;
                n11 = 0.0;
                n6 = 0.0;
                n7 = 0.0;
                n8 = 0.0;
            }
        }
        final int n18 = 2;
        this.DS_INTEREST = new float[n18];
        this.DS_PRINCIPAL = new float[n18];
        this.DS_INTEREST[0] = (float)(this.POINTS_INTEREST_PAID / 1000.0);
        this.DS_PRINCIPAL[0] = (float)((this.POINTS_TOTAL_OF_PAYMENTS - this.POINTS_INTEREST_PAID) / 1000.0);
        this.DS_INTEREST[1] = (float)(this.DOWNPAYMENT_INTEREST_PAID / 1000.0);
        this.DS_PRINCIPAL[1] = (float)((this.DOWNPAYMENT_TOTAL_OF_PAYMENTS - this.DOWNPAYMENT_INTEREST_PAID) / 1000.0);
        this.GRAPH_MAXIMUM = this.DOWNPAYMENT_TOTAL_OF_PAYMENTS / 1000.0 + 1.0;
        if (this.DOWNPAYMENT_TOTAL_OF_PAYMENTS < this.POINTS_TOTAL_OF_PAYMENTS) {
            this.GRAPH_MAXIMUM = this.POINTS_TOTAL_OF_PAYMENTS / 1000.0 + 1.0;
        }
    }
    
    protected void clearCalculated() {
        this.DISCOUNT_POINTS_AMT = 0.0;
        this.POINTS_MONTHLY_PI = 0.0;
        this.POINTS_INTEREST_SAVINGS = 0.0;
        this.DISCOUNT_POINTS_DIFFERENCE = 0.0;
        this.POINTS_TAX_REDUCTION = 0.0;
        this.POINTS_COST_AFTERTAX = 0.0;
        this.POINTS_HOME_EQUITY_AT_MOVEOUT = 0.0;
        this.POINTS_TOTAL_OF_PAYMENTS = 0.0;
        this.POINTS_INTEREST_PAID = 0.0;
        this.DOWNPAYMENT_HOME_EQUITY_AT_MOVEOUT = 0.0;
        this.DOWNPAYMENT_TOTAL_OF_PAYMENTS = 0.0;
        this.DOWNPAYMENT_INTEREST_PAID = 0.0;
        this.EQUITY_DIFFERENCE = 0.0;
        this.INTEREST_DIFFERENCE = 0.0;
        this.TOTAL_DIFFERENCE = 0.0;
        this.DOWNPAYMENT_MONTHLY_PI = 0.0;
        this.DOWNPAYMENT_LOAN_AMOUNT = 0.0;
        this.TITLE_MESSAGE = "";
        this.RESULTS_MESSAGE = "";
    }
    
    public void clearInputed() {
        this.INTEREST_RATE = 0.0;
        this.TERM = 0.0;
        this.LOAN_AMOUNT = 0.0;
        this.DISCOUNT_POINTS_RATE = 0.0;
        this.MARGINAL_TAX_RATE = 0.0;
        this.YEARS_IN_HOME = 0.0;
        this.DISCOUNT_POINTS_PERCENT = 0.0;
        this.BY_YEAR = 1;
    }
    
    protected String formatReport(final String s) {
        return Calculation.replace("RESULTS_MESSAGE", this.RESULTS_MESSAGE, Calculation.replace("TITLE_MESSAGE", this.TITLE_MESSAGE, Calculation.replace("DISCOUNT_POINTS_RATE", Fmt.percent(this.DISCOUNT_POINTS_RATE / 100.0, 3), Calculation.replace("DISCOUNT_POINTS_AMT", Fmt.dollars(this.DISCOUNT_POINTS_AMT), Calculation.replace("DISCOUNT_POINTS_PERCENT", Fmt.number(this.DISCOUNT_POINTS_PERCENT, 3), Calculation.replace("INTEREST_RATE", Fmt.percent(this.INTEREST_RATE / 100.0, 3), Calculation.replace("LOAN_AMOUNT", Fmt.dollars(this.LOAN_AMOUNT), Calculation.replace("MARGINAL_TAX_RATE", Fmt.percent(this.MARGINAL_TAX_RATE / 100.0, 2), Calculation.replace("POINTS_INTEREST_SAVINGS", Fmt.dollars(this.POINTS_INTEREST_SAVINGS), Calculation.replace("DISCOUNT_POINTS_DIFFERENCE", Fmt.dollars(this.DISCOUNT_POINTS_DIFFERENCE, 2), Calculation.replace("POINTS_MONTHLY_PI", Fmt.dollars(this.POINTS_MONTHLY_PI, 2), Calculation.replace("TERM", Fmt.number(this.TERM), Calculation.replace("YEARS_IN_HOME", Fmt.number(this.YEARS_IN_HOME), Calculation.replace("DOWNPAYMENT_LOAN_AMOUNT", Fmt.dollars(this.DOWNPAYMENT_LOAN_AMOUNT), Calculation.replace("DOWNPAYMENT_MONTHLY_PI", Fmt.dollars(this.DOWNPAYMENT_MONTHLY_PI, 2), Calculation.replace("TOTAL_DIFFERENCE", Fmt.dollars(this.TOTAL_DIFFERENCE), Calculation.replace("INTEREST_DIFFERENCE", Fmt.dollars(this.INTEREST_DIFFERENCE), Calculation.replace("EQUITY_DIFFERENCE", Fmt.dollars(this.EQUITY_DIFFERENCE), Calculation.replace("DOWNPAYMENT_INTEREST_PAID", Fmt.dollars(this.DOWNPAYMENT_INTEREST_PAID), Calculation.replace("DOWNPAYMENT_TOTAL_OF_PAYMENTS", Fmt.dollars(this.DOWNPAYMENT_TOTAL_OF_PAYMENTS), Calculation.replace("DOWNPAYMENT_HOME_EQUITY_AT_MOVEOUT", Fmt.dollars(this.DOWNPAYMENT_HOME_EQUITY_AT_MOVEOUT), Calculation.replace("POINTS_INTEREST_PAID", Fmt.dollars(this.POINTS_INTEREST_PAID), Calculation.replace("POINTS_TOTAL_OF_PAYMENTS", Fmt.dollars(this.POINTS_TOTAL_OF_PAYMENTS), Calculation.replace("POINTS_HOME_EQUITY_AT_MOVEOUT", Fmt.dollars(this.POINTS_HOME_EQUITY_AT_MOVEOUT), Calculation.replace("POINTS_COST_AFTERTAX", Fmt.dollars(this.POINTS_COST_AFTERTAX), Calculation.replace("POINTS_TAX_REDUCTION", Fmt.dollars(this.POINTS_TAX_REDUCTION), s))))))))))))))))))))))))));
    }
    
    public String getAmountLabel() {
        return String.valueOf(super._sGraphUnits) + this._sAmountLabel;
    }
    
    public String[] getAmountPaidCatagories() {
        return this.totalpaid_cats;
    }
    
    public String[] getCatagories() {
        return this.cats;
    }
}
