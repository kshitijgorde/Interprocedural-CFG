import KJEcalculation.CalculationException;
import KJEgui.Fmt;
import KJEcalculation.Calculation;

// 
// Decompiled by Procyon v0.5.30
// 

public class MortgageLoanCalculation extends Calculation
{
    public String PREPAY_NONE;
    public String PREPAY_MONTHLY;
    public String PREPAY_YEARLY;
    public String PREPAY_ONETIME;
    public String MSG_YEAR_NUMBER;
    public String MSG_PRINCIPAL;
    public String MSG_INTEREST;
    public String MSG_PAYMENT_NUMBER;
    public String MSG_PRINCIPAL_BALANCE;
    public String MSG_PREPAYMENTS;
    public String MSG_NORMAL_PAYMENTS;
    public String MSG_PREPAY_MESSAGE;
    public String MSG_RETURN_AMOUNT;
    public String MSG_RETURN_PAYMENT;
    public String MSG_GRAPH_COL1;
    public String MSG_GRAPH_COL2;
    public String MSG_ERROR_BALLOON;
    private String sTotalCost;
    public int PAYMENT_CALC;
    public double INTEREST_RATE;
    public double TERM;
    public double TERM_BALLOON;
    public double ENDING_BALANCE;
    public double PREPAY_ENDING_BALANCE;
    public double LOAN_AMOUNT;
    public double PREPAY_AMOUNT;
    public double PREPAY_STARTS_WITH;
    public String PREPAY_TYPE;
    public double PMI_RATE;
    public double PURCHASE_PRICE;
    public double INFLATION_RATE;
    public double FEDERAL_TAX_RATE;
    public double STATE_TAX_RATE;
    public double MARGINAL_TAX_RATE;
    public double YEARLY_PROPERTY_TAXES;
    public double YEARLY_HOME_INSURANCE;
    public double YEARS_IN_HOME;
    public double SAVINGS_RATE;
    public double DISCOUNT_POINTS_PERCENT;
    public double ORIGINATION_FEES_PERCENT;
    public double OTHER_FEES;
    public double HOME_APPRECIATION;
    public double ADJUSTABLE_RATE;
    public double ADJUSTABLE_RATE_CAP;
    public double ADJUSTABLE_RATE_INCR;
    public int ADJUSTABLE_RATE_FEQ;
    public int ADJUSTABLE_RATE_FIXED;
    public double BALLOON_PAYMENT;
    public double PREPAY_BALLOON_PAYMENT;
    public boolean INTEREST_ONLY;
    public double RATE_INDEX;
    public double RATE_INDEX_MARGIN;
    public double FULLY_INDEX_RATE;
    public double FULLY_INDEXED_PAYMENT;
    public String MSG_TERM;
    public int RECAST_TO_AMORTIZE;
    public double INTEREST_PAID;
    public double TOTAL_OF_PAYMENTS;
    public double MONTHLY_PITI;
    public double MONTHLY_HOME_INSURANCE;
    public double MONTHLY_PI;
    public double FIRST_MONTH_INTEREST;
    public double FIRST_MONTH_PRINCIPAL;
    public double MONTHLY_PROPERTY_TAXES;
    public double MONTHLY_PMI;
    public double AVG_TAX_SAVINGS;
    public double TAX_ADJ_RATE;
    public double PERCENT_DOWNPAYMENT;
    public double LOAN_TO_VALUE;
    public double TOTAL_CLOSING_COSTS;
    public double DISCOUNT_POINTS_AMT;
    public double ORIGINATION_FEES_AMT;
    public double FIRST_YEAR_INTEREST;
    public double FIRST_YEAR_TAX_SAVINGS;
    public double HOME_VALUE_AT_MOVEOUT;
    public String ADJUSTABLE_PAYMENT_AMTS;
    public double ADJUSTABLE_RATE_HIGHEST;
    public double PREPAY_TOTAL_OF_PAYMENTS;
    public double PREPAY_INTEREST_PAID;
    public double PREPAY_SHORTEN_MONTHS;
    public double PREPAY_SHORTEN_YEARS;
    public double PREPAY_FIRST_YEAR_INTEREST;
    public double PREPAY_AVG_TAX_SAVINGS;
    public double PREPAY_FIRST_YEAR_TAX_SAVINGS;
    public double PREPAY_INTEREST_SAVINGS;
    public String PREPAY_MESSAGE;
    public String sReturnMessage;
    public double TOT_EQUITY_AT_MOVEOUT;
    public double PREPAY_TOT_EQUITY_AT_MOVEOUT;
    public double PREPAY_TOT_SAVED_PAYMENTS;
    public double PREPAY_TOT_SAVED_PAYMENTS_AFTX;
    public double PREPAY_TOTAL_VALUE;
    public double PREPAY_TOTAL_VALUE_AFTX;
    public double TOT_DIFFERENCE;
    public String TOT_DIFFERENCE_MSG;
    public double TOT_DIFFERENCE_AFTX;
    public String TOT_DIFFERENCE_AFTX_MSG;
    public double LOAN_APR;
    public double LOAN_APR_PAYMENT;
    public double LOAN_APR_AMOUNT;
    public double LOAN_APR_AFT;
    public int BY_YEAR;
    public int iFactor;
    public int iFactor2;
    public float[] DS_PRINCIPAL_BAL;
    public float[] DS_PREPAY_PRINCIPAL_BAL;
    public float[] DS_INTEREST;
    public float[] DS_PRINCIPAL;
    public String[] cats;
    public String[] totalpaid_cats;
    
    public MortgageLoanCalculation() {
        this.PREPAY_NONE = "None";
        this.PREPAY_MONTHLY = "Monthly";
        this.PREPAY_YEARLY = "Yearly";
        this.PREPAY_ONETIME = "One time payment";
        this.MSG_YEAR_NUMBER = "Year Number";
        this.MSG_PRINCIPAL = "Principal";
        this.MSG_INTEREST = "Interest";
        this.MSG_PAYMENT_NUMBER = "Payment Number";
        this.MSG_PRINCIPAL_BALANCE = "Principal Balance";
        this.MSG_PREPAYMENTS = "Prepayments";
        this.MSG_NORMAL_PAYMENTS = "Normal payments";
        this.MSG_PREPAY_MESSAGE = "Your planned prepayment(s) will shorten your mortgage by PREPAY_SHORTEN_YEARS year(s) and PREPAY_SHORTEN_MONTHS month(s).";
        this.MSG_RETURN_AMOUNT = "A monthly payment of MONTHLY_PI at INTEREST_RATE for TERM years will give you a mortgage amount of LOAN_AMOUNT.";
        this.MSG_RETURN_PAYMENT = "A loan amount of LOAN_AMOUNT at INTEREST_RATE for TERM years will give you a monthly payment (PI) of MONTHLY_PI.";
        this.MSG_GRAPH_COL1 = "Normal";
        this.MSG_GRAPH_COL2 = "Prepay";
        this.MSG_ERROR_BALLOON = "Loan term must be less than the amortization term.";
        this.sTotalCost = "";
        this.PAYMENT_CALC = 1;
        this.INTEREST_RATE = 0.0;
        this.TERM = 0.0;
        this.TERM_BALLOON = 0.0;
        this.ENDING_BALANCE = 0.0;
        this.PREPAY_ENDING_BALANCE = 0.0;
        this.LOAN_AMOUNT = 0.0;
        this.PREPAY_AMOUNT = 0.0;
        this.PREPAY_STARTS_WITH = 1.0;
        this.PREPAY_TYPE = this.PREPAY_NONE;
        this.PMI_RATE = 0.5;
        this.PURCHASE_PRICE = 0.0;
        this.INFLATION_RATE = 3.0;
        this.FEDERAL_TAX_RATE = 0.0;
        this.STATE_TAX_RATE = 0.0;
        this.MARGINAL_TAX_RATE = 0.0;
        this.YEARLY_PROPERTY_TAXES = 0.0;
        this.YEARLY_HOME_INSURANCE = 0.0;
        this.YEARS_IN_HOME = 0.0;
        this.SAVINGS_RATE = 0.0;
        this.DISCOUNT_POINTS_PERCENT = 0.0;
        this.ORIGINATION_FEES_PERCENT = 0.0;
        this.OTHER_FEES = 0.0;
        this.HOME_APPRECIATION = 0.0;
        this.ADJUSTABLE_RATE = 0.0;
        this.ADJUSTABLE_RATE_CAP = 0.0;
        this.ADJUSTABLE_RATE_INCR = 0.0;
        this.ADJUSTABLE_RATE_FEQ = 12;
        this.ADJUSTABLE_RATE_FIXED = 0;
        this.BALLOON_PAYMENT = 0.0;
        this.PREPAY_BALLOON_PAYMENT = 0.0;
        this.INTEREST_ONLY = false;
        this.RATE_INDEX = 0.0;
        this.RATE_INDEX_MARGIN = 0.0;
        this.FULLY_INDEX_RATE = 0.0;
        this.FULLY_INDEXED_PAYMENT = 0.0;
        this.MSG_TERM = "";
        this.RECAST_TO_AMORTIZE = 1000000;
        this.INTEREST_PAID = 0.0;
        this.TOTAL_OF_PAYMENTS = 0.0;
        this.MONTHLY_PITI = 0.0;
        this.MONTHLY_HOME_INSURANCE = 0.0;
        this.MONTHLY_PI = 0.0;
        this.FIRST_MONTH_INTEREST = 0.0;
        this.FIRST_MONTH_PRINCIPAL = 0.0;
        this.MONTHLY_PROPERTY_TAXES = 0.0;
        this.MONTHLY_PMI = 0.0;
        this.AVG_TAX_SAVINGS = 0.0;
        this.TAX_ADJ_RATE = 0.0;
        this.PERCENT_DOWNPAYMENT = 0.0;
        this.LOAN_TO_VALUE = 0.0;
        this.TOTAL_CLOSING_COSTS = 0.0;
        this.DISCOUNT_POINTS_AMT = 0.0;
        this.ORIGINATION_FEES_AMT = 0.0;
        this.FIRST_YEAR_INTEREST = 0.0;
        this.FIRST_YEAR_TAX_SAVINGS = 0.0;
        this.HOME_VALUE_AT_MOVEOUT = 0.0;
        this.ADJUSTABLE_PAYMENT_AMTS = "";
        this.ADJUSTABLE_RATE_HIGHEST = 0.0;
        this.PREPAY_TOTAL_OF_PAYMENTS = 0.0;
        this.PREPAY_INTEREST_PAID = 0.0;
        this.PREPAY_SHORTEN_MONTHS = 0.0;
        this.PREPAY_SHORTEN_YEARS = 0.0;
        this.PREPAY_FIRST_YEAR_INTEREST = 0.0;
        this.PREPAY_AVG_TAX_SAVINGS = 0.0;
        this.PREPAY_FIRST_YEAR_TAX_SAVINGS = 0.0;
        this.PREPAY_INTEREST_SAVINGS = 0.0;
        this.PREPAY_MESSAGE = "";
        this.sReturnMessage = "";
        this.TOT_EQUITY_AT_MOVEOUT = 0.0;
        this.PREPAY_TOT_EQUITY_AT_MOVEOUT = 0.0;
        this.PREPAY_TOT_SAVED_PAYMENTS = 0.0;
        this.PREPAY_TOT_SAVED_PAYMENTS_AFTX = 0.0;
        this.PREPAY_TOTAL_VALUE = 0.0;
        this.PREPAY_TOTAL_VALUE_AFTX = 0.0;
        this.TOT_DIFFERENCE = 0.0;
        this.TOT_DIFFERENCE_MSG = "";
        this.TOT_DIFFERENCE_AFTX = 0.0;
        this.TOT_DIFFERENCE_AFTX_MSG = "";
        this.LOAN_APR = 0.0;
        this.LOAN_APR_PAYMENT = 0.0;
        this.LOAN_APR_AMOUNT = 0.0;
        this.LOAN_APR_AFT = 0.0;
        this.BY_YEAR = 1;
        this.iFactor = 0;
        this.iFactor2 = 0;
    }
    
    public double APRAdjustable(final int n, final double n2, final double n3, final double n4, final int n5, final int n6, final double n7, final double n8, final double n9) {
        double n10 = n2;
        double n12;
        double n11 = n12 = n4 / 12.0;
        double n13 = Fmt.round(Calculation.PMT(n11, n, n10), 2);
        double n14 = 0.0;
        for (int i = 1; i <= n; ++i) {
            n10 -= n13 - Fmt.round(n11 * n10, 2);
            n14 += n13;
            if (((i < n5) ? 1 : ((i - n5) % n6)) == 0 && i != 1 && i != n) {
                double n15 = n7 / 12.0;
                if (n15 > n12 + n8) {
                    n15 = n12 + n8;
                }
                if (n15 > n9 / 12.0) {
                    n15 = n9 / 12.0;
                }
                if (n15 != n12) {
                    n12 = n15;
                    n11 = n15;
                    n13 = Fmt.round(Calculation.PMT(n11, n - i, n10), 2);
                    if (n15 == n7 / 12.0) {
                        n14 += (n - i) * n13;
                        break;
                    }
                }
            }
        }
        return Calculation.RATE(n, n14 / n, n2 - n3) * 12.0;
    }
    
    public void addAdjRepeat(final String s, final int n, final StringBuffer sb) {
        if (n == 0) {
            sb.append(String.valueOf(super._sTableHeader) + "<!-- top of the world-->");
            sb.append(super._sTopRow);
        }
        else if (n % 2 == 0) {
            sb.append(super._sOddRow);
        }
        else {
            sb.append(super._sEvenRow);
        }
        sb.append(s);
        sb.append(super._sRowFooter);
    }
    
    private void addPrepayRow(final int n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7) {
        this.addRepeat("<TD>" + super._sCell + Fmt.number(n) + super._sCellFormat + Fmt.dollars(n2, 2) + super._sCellFormat + Fmt.dollars(n3, 2) + super._sCellFormat + Fmt.dollars(n4, 2) + super._sCellFormat + Fmt.dollars(n5, 2) + super._sCellFormat + Fmt.dollars(n6, 2) + super._sCellFormat + Fmt.dollars(n7, 2) + super._sCellFooter + "</TD>");
    }
    
    private void addPrepayTitle(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        this.addRepeat("<TD>" + super._sCell + "&nbsp;" + super._sCellFooter + "</TD><TD COLSPAN=3>" + super._sCell + this.sReportCol("<CENTER><U><B>Regular Payment Schedule</B></U></CENTER>", 10) + super._sCellFooter + "</TD><TD COLSPAN=3>" + super._sCell + this.sReportCol("<CENTER><U><B>Prepayment Payment Schedule</B></U></CENTER>", 11) + super._sCellFooter + "</TD>" + super._sRowFooter + super._sTopRow + "<TD>" + super._sCell + "<B>" + s + "</B>" + super._sCellFormat + "<B>" + s2 + "</B>" + super._sCellFormat + "<B>" + s3 + "</B>" + super._sCellFormat + "<B>" + s4 + "</B>" + super._sCellFormat + "<B>" + s5 + "</B>" + super._sCellFormat + "<B>" + s6 + "</B>" + super._sCellFormat + "<B>" + s7 + "</B>" + super._sCellFooter + "</TD>");
    }
    
    private void addRegularRow(final int n, final double n2, final double n3, final double n4, final double n5) {
        this.addRepeat("<TD>" + super._sCell + Fmt.number(n) + super._sCellFormat + Fmt.dollars(n2, 2) + super._sCellFormat + Fmt.dollars(n3, 2) + super._sCellFormat + Fmt.dollars(n4, 2) + super._sCellFormat + Fmt.dollars(n5, 2) + super._sCellFooter + "</TD>");
    }
    
    private void addRegularTitle(final String s, final String s2, final String s3, final String s4, final String s5) {
        this.addRepeat("<TD>" + super._sCell + "<B>" + s + "</B>" + super._sCellFormat + "<B>" + s2 + "</B>" + super._sCellFormat + "<B>" + s3 + "</B>" + super._sCellFormat + "<B>" + s4 + "</B>" + super._sCellFormat + "<B>" + s5 + "</B>" + super._sCellFooter + "</TD>");
    }
    
    protected void calculate() throws CalculationException {
        if (this.MARGINAL_TAX_RATE == 0.0) {
            this.MARGINAL_TAX_RATE = this.STATE_TAX_RATE / 100.0 * (1.0 - this.FEDERAL_TAX_RATE / 100.0) * 100.0 + this.FEDERAL_TAX_RATE;
        }
        if (this.PAYMENT_CALC == 0) {
            if (this.INTEREST_ONLY) {
                this.LOAN_AMOUNT = Fmt.round(this.MONTHLY_PI / (this.INTEREST_RATE / 1200.0), 2);
            }
            else {
                this.LOAN_AMOUNT = Fmt.round(Calculation.PV(this.INTEREST_RATE / 1200.0, this.TERM * 12.0, this.MONTHLY_PI), 2);
            }
            this.sReturnMessage = this.MSG_RETURN_AMOUNT;
        }
        else {
            if (this.INTEREST_ONLY) {
                this.MONTHLY_PI = Fmt.round(this.INTEREST_RATE / 1200.0 * this.LOAN_AMOUNT, 2);
            }
            else {
                this.MONTHLY_PI = Fmt.round(Calculation.PMT(this.INTEREST_RATE / 1200.0, this.TERM * 12.0, this.LOAN_AMOUNT), 2);
            }
            this.sReturnMessage = this.MSG_RETURN_PAYMENT;
        }
        if (this.YEARS_IN_HOME == 0.0) {
            this.YEARS_IN_HOME = this.TERM;
        }
        else if (this.YEARS_IN_HOME > this.TERM) {
            this.YEARS_IN_HOME = this.TERM;
        }
        final double n = this.YEARS_IN_HOME * 12.0;
        if (this.PURCHASE_PRICE == 0.0) {
            this.PURCHASE_PRICE = this.LOAN_AMOUNT;
        }
        this.DISCOUNT_POINTS_AMT = Fmt.round(this.DISCOUNT_POINTS_PERCENT / 100.0 * this.LOAN_AMOUNT, 2);
        this.ORIGINATION_FEES_AMT = Fmt.round(this.ORIGINATION_FEES_PERCENT / 100.0 * this.LOAN_AMOUNT, 2);
        this.TOTAL_CLOSING_COSTS = this.DISCOUNT_POINTS_AMT + this.ORIGINATION_FEES_AMT + this.OTHER_FEES;
        this.PERCENT_DOWNPAYMENT = 1.0 - this.LOAN_AMOUNT / this.PURCHASE_PRICE;
        this.LOAN_TO_VALUE = this.LOAN_AMOUNT / this.PURCHASE_PRICE;
        this.MONTHLY_HOME_INSURANCE = Fmt.round(this.YEARLY_HOME_INSURANCE / 12.0, 2);
        this.MONTHLY_PROPERTY_TAXES = Fmt.round(this.YEARLY_PROPERTY_TAXES / 12.0, 2);
        if (this.PERCENT_DOWNPAYMENT >= 0.2) {
            this.MONTHLY_PMI = 0.0;
        }
        else {
            this.MONTHLY_PMI = Fmt.round(this.LOAN_AMOUNT * (this.PMI_RATE / 100.0), 2);
        }
        this.MONTHLY_PITI = this.MONTHLY_HOME_INSURANCE + this.MONTHLY_PROPERTY_TAXES + this.MONTHLY_PI;
        this.FIRST_MONTH_INTEREST = Fmt.round(this.INTEREST_RATE / 1200.0 * this.LOAN_AMOUNT, 2);
        this.FIRST_MONTH_PRINCIPAL = (this.INTEREST_ONLY ? 0.0 : (this.MONTHLY_PI - this.FIRST_MONTH_INTEREST));
        double n2 = this.INTEREST_RATE / 1200.0;
        final double n3 = this.MARGINAL_TAX_RATE / 100.0;
        final double n4 = this.SAVINGS_RATE / 1200.0;
        final double n5 = this.HOME_APPRECIATION / 100.0;
        this.FULLY_INDEX_RATE = (this.RATE_INDEX + this.RATE_INDEX_MARGIN) / 100.0;
        if (this.ADJUSTABLE_RATE == 1.0 && this.FULLY_INDEX_RATE != this.INTEREST_RATE / 100.0) {
            this.LOAN_APR = this.APRAdjustable((int)this.TERM * 12, this.LOAN_AMOUNT, this.TOTAL_CLOSING_COSTS, this.INTEREST_RATE / 100.0, this.ADJUSTABLE_RATE_FIXED, this.ADJUSTABLE_RATE_FEQ, this.FULLY_INDEX_RATE, this.ADJUSTABLE_RATE_INCR / 100.0, this.ADJUSTABLE_RATE_CAP);
        }
        else {
            this.LOAN_APR = Calculation.APR(this.TERM * 12.0, this.MONTHLY_PI, n2, this.LOAN_AMOUNT, this.TOTAL_CLOSING_COSTS) * 12.0;
        }
        this.LOAN_APR_PAYMENT = Fmt.round(Calculation.PMT(n2, this.TERM * 12.0, this.LOAN_AMOUNT + this.TOTAL_CLOSING_COSTS), 2);
        this.LOAN_APR_AMOUNT = this.LOAN_AMOUNT + this.TOTAL_CLOSING_COSTS;
        this.TAX_ADJ_RATE = this.INTEREST_RATE / 100.0 * (1.0 - n3 * ((this.LOAN_AMOUNT > 1000000.0) ? (1000000.0 / this.LOAN_AMOUNT) : 1.0));
        this.LOAN_APR_AFT = this.LOAN_APR * (1.0 - n3 * ((this.LOAN_AMOUNT > 1000000.0) ? (1000000.0 / this.LOAN_AMOUNT) : 1.0));
        this.TOTAL_OF_PAYMENTS = 0.0;
        this.INTEREST_PAID = 0.0;
        boolean b = false;
        if (this.TERM_BALLOON > 0.0) {
            if (this.TERM_BALLOON > this.TERM) {
                throw new CalculationException(this.MSG_ERROR_BALLOON);
            }
            b = true;
        }
        if (this.INTEREST_ONLY) {
            this.TERM_BALLOON = this.TERM;
            b = true;
        }
        final int n6 = (int)Math.round(b ? this.TERM_BALLOON : this.TERM) + 1;
        this.iFactor = this.getGraphUnits(this.LOAN_AMOUNT);
        this.sTotalCost = super._sGraphUnits;
        this.DS_PRINCIPAL_BAL = new float[n6];
        this.DS_PREPAY_PRINCIPAL_BAL = new float[n6];
        this.cats = new String[n6];
        final StringBuffer sb = new StringBuffer();
        boolean b2 = true;
        double yearly_PROPERTY_TAXES = this.YEARLY_PROPERTY_TAXES;
        this.PREPAY_TOTAL_OF_PAYMENTS = ((this.PREPAY_TYPE.equals(this.PREPAY_ONETIME) && this.PREPAY_STARTS_WITH == 0.0) ? this.PREPAY_AMOUNT : 0.0);
        double prepay_ENDING_BALANCE = this.LOAN_AMOUNT - this.PREPAY_TOTAL_OF_PAYMENTS;
        double prepay_FIRST_YEAR_INTEREST = 0.0;
        double n7 = 0.0;
        double n8 = 0.0;
        final double n9 = this.INTEREST_ONLY ? this.FIRST_MONTH_INTEREST : this.MONTHLY_PI;
        double n10 = 0.0;
        double n11 = 0.0;
        double loan_AMOUNT = this.LOAN_AMOUNT;
        double first_YEAR_INTEREST = 0.0;
        double n12 = 0.0;
        double n13 = 0.0;
        final double monthly_PI = this.MONTHLY_PI;
        double n14 = 0.0;
        this.PREPAY_SHORTEN_MONTHS = 0.0;
        double n15 = 0.0;
        double n16 = 0.0;
        this.TOT_EQUITY_AT_MOVEOUT = 0.0;
        this.PREPAY_TOT_EQUITY_AT_MOVEOUT = 0.0;
        this.PREPAY_TOT_SAVED_PAYMENTS = 0.0;
        this.PREPAY_TOT_SAVED_PAYMENTS_AFTX = 0.0;
        this.PREPAY_TOTAL_VALUE = 0.0;
        this.PREPAY_TOTAL_VALUE_AFTX = 0.0;
        this.TOT_DIFFERENCE = 0.0;
        this.TOT_DIFFERENCE_MSG = "";
        this.TOT_DIFFERENCE_AFTX = 0.0;
        this.TOT_DIFFERENCE_AFTX_MSG = "";
        this.HOME_VALUE_AT_MOVEOUT = this.PURCHASE_PRICE;
        if (this.PREPAY_TYPE.equals(this.PREPAY_NONE)) {
            b2 = false;
        }
        if (this.PREPAY_STARTS_WITH == 0.0 && !this.PREPAY_TYPE.equals(this.PREPAY_ONETIME)) {
            this.PREPAY_STARTS_WITH = 1.0;
        }
        int n17 = 0;
        this.cats[n17] = "0";
        this.DS_PREPAY_PRINCIPAL_BAL[n17] = (float)(prepay_ENDING_BALANCE / this.iFactor);
        this.DS_PRINCIPAL_BAL[n17] = (float)(this.LOAN_AMOUNT / this.iFactor);
        ++n17;
        if (super.bWithSchedule) {
            if (this.BY_YEAR != 1 && b2) {
                this.addPrepayTitle(this.sReportCol("<BR><BR>Nbr", 1), this.sReportCol("<BR><BR>Payment", 2), this.sReportCol("<BR><BR>Interest", 4), this.sReportCol("Ending<BR>Principal<BR>Balance", 5), this.sReportCol("<BR><BR>Payment", 2), this.sReportCol("<BR><BR>Interest", 4), this.sReportCol("Ending<BR>Principal<BR>Balance", 5));
            }
            else if (this.BY_YEAR != 1 && !b2) {
                this.addRegularTitle(this.sReportCol("<BR><BR>Nbr", 1), this.sReportCol("<BR><BR>Payment", 2), this.sReportCol("<BR><BR>Principal", 3), this.sReportCol("<BR><BR>Interest", 4), this.sReportCol("Ending<BR>Principal<BR>Balance", 5));
            }
            else if (this.BY_YEAR == 1 && b2) {
                this.addPrepayTitle(this.sReportCol("<BR><BR>Yr ", 6), this.sReportCol("<BR>Total<BR>Payments", 7), this.sReportCol("<BR>Interest<BR>Paid", 8), this.sReportCol("Ending<BR>Principal<BR>Balance", 5), this.sReportCol("<BR>Total<BR>Payments", 7), this.sReportCol("<BR>Interest<BR>Paid", 8), this.sReportCol("Ending<BR>Principal<BR>Balance", 5));
            }
            else {
                this.addRegularTitle(this.sReportCol("<BR><BR>Year", 6), this.sReportCol("<BR>Total<BR>Payments", 7), this.sReportCol("<BR>Principal<BR>Paid", 9), this.sReportCol("<BR>Interest<BR>Paid", 8), this.sReportCol("Ending<BR>Principal<BR>Balance", 5));
            }
            if (b2) {
                this.addRepeat("<TD>" + super._sCell + "&nbsp;" + super._sCellFormat + "&nbsp;" + super._sCellFormat + "&nbsp;" + super._sCellFormat + Fmt.dollars(loan_AMOUNT, 2) + super._sCellFormat + ((this.PREPAY_TYPE.equals(this.PREPAY_ONETIME) && this.PREPAY_STARTS_WITH == 0.0) ? Fmt.dollars(this.PREPAY_AMOUNT, 2) : "") + super._sCellFormat + "&nbsp;" + super._sCellFormat + Fmt.dollars(prepay_ENDING_BALANCE, 2) + super._sCellFooter + "</TD>");
            }
            else {
                this.addRepeat("<TD>" + super._sCell + "&nbsp;" + super._sCellFormat + "&nbsp;" + super._sCellFormat + "&nbsp;" + super._sCellFormat + "&nbsp;" + super._sCellFormat + Fmt.dollars(loan_AMOUNT, 2) + super._sCellFooter + "</TD>");
            }
        }
        this.ADJUSTABLE_RATE_HIGHEST = this.MONTHLY_PI;
        double n18 = this.MONTHLY_PI;
        double n19 = this.MONTHLY_PI;
        double n20 = this.INTEREST_RATE / 100.0;
        double n21 = this.INTEREST_RATE / 100.0;
        final StringBuffer sb2 = new StringBuffer();
        int n22 = 0;
        if (this.ADJUSTABLE_RATE == 1.0 && this.ADJUSTABLE_RATE_INCR != 0.0) {
            this.addAdjRepeat("<!-header of table--><TD>" + super._sCell + this.sReportCol("<B>Payment Number</B>", 12) + super._sCellFormat + this.sReportCol("<B>Interest Rate</B>", 13) + super._sCellFormat + this.sReportCol("<B>Monthly Payment</B>", 14) + super._sCellFooter + "</TD>", n22++, sb2);
            this.addAdjRepeat("<TD>" + super._sCell + "1" + super._sCellFormat + Fmt.percent(n21, 2) + super._sCellFormat + Fmt.dollars(this.MONTHLY_PI, 2) + super._sCellFooter + "</TD>", n22++, sb2);
        }
        for (int n23 = (int)(b ? this.TERM_BALLOON : this.TERM) * 12, i = 1; i <= n23; ++i) {
            double n24 = n18;
            double n25 = n19;
            double n26 = 0.0;
            double n27 = 0.0;
            if (b2 && this.PREPAY_STARTS_WITH <= i) {
                if (this.PREPAY_TYPE.equals(this.PREPAY_ONETIME) && this.PREPAY_STARTS_WITH == i) {
                    n26 = this.PREPAY_AMOUNT;
                }
                else if (this.PREPAY_TYPE.equals(this.PREPAY_YEARLY)) {
                    if ((i - this.PREPAY_STARTS_WITH) % 12.0 == 0.0) {
                        n26 = this.PREPAY_AMOUNT;
                    }
                }
                else if (this.PREPAY_TYPE.equals(this.PREPAY_MONTHLY)) {
                    n26 = this.PREPAY_AMOUNT;
                }
            }
            double round = Fmt.round(n2 * loan_AMOUNT, 2);
            if (this.INTEREST_ONLY && i < this.RECAST_TO_AMORTIZE) {
                n25 = round;
            }
            final double round2 = Fmt.round(n2 * ((loan_AMOUNT > 1000000.0) ? 1000000.0 : loan_AMOUNT), 2);
            double n28 = (this.INTEREST_ONLY && i < this.RECAST_TO_AMORTIZE) ? 0.0 : (n25 - round);
            loan_AMOUNT -= n28;
            if (loan_AMOUNT == 0.0) {
                n25 = 0.0;
                n28 = 0.0;
                round = 0.0;
            }
            else if (loan_AMOUNT < 0.0 || (loan_AMOUNT > 0.005 && n23 == i && !b)) {
                n28 += loan_AMOUNT;
                loan_AMOUNT = 0.0;
                n25 = n28 + round;
            }
            else if (n23 == i && !b) {
                loan_AMOUNT = 0.0;
            }
            double round3 = Fmt.round(n2 * prepay_ENDING_BALANCE, 2);
            final double round4 = Fmt.round(n2 * ((prepay_ENDING_BALANCE > 1000000.0) ? 1000000.0 : prepay_ENDING_BALANCE), 2);
            double n29;
            if (this.INTEREST_ONLY && i < this.RECAST_TO_AMORTIZE) {
                if (prepay_ENDING_BALANCE == 0.0) {
                    n24 = 0.0;
                    n29 = 0.0;
                    round3 = 0.0;
                    n26 = 0.0;
                }
                else {
                    n24 = round3;
                    n29 = 0.0;
                    prepay_ENDING_BALANCE -= n26;
                    if (prepay_ENDING_BALANCE < 0.0) {
                        n26 += prepay_ENDING_BALANCE;
                        prepay_ENDING_BALANCE = 0.0;
                    }
                }
            }
            else {
                n29 = n24 - round3;
                prepay_ENDING_BALANCE -= n29 + n26;
                if (prepay_ENDING_BALANCE == 0.0) {
                    n24 = 0.0;
                    n29 = 0.0;
                    round3 = 0.0;
                    n26 = 0.0;
                }
                else if (prepay_ENDING_BALANCE < 0.0) {
                    n26 += prepay_ENDING_BALANCE;
                    if (n26 < 0.0) {
                        n29 += n26;
                        n26 = 0.0;
                    }
                    prepay_ENDING_BALANCE = 0.0;
                    n24 = n29 + round3;
                }
                else if (prepay_ENDING_BALANCE > 0.005 && n23 == i && !b) {
                    n29 += prepay_ENDING_BALANCE;
                    prepay_ENDING_BALANCE = 0.0;
                    n24 = n29 + round3;
                }
                else if (n23 == i && !b) {
                    prepay_ENDING_BALANCE = 0.0;
                }
            }
            if (prepay_ENDING_BALANCE == 0.0 && this.PREPAY_SHORTEN_MONTHS == 0.0) {
                this.PREPAY_SHORTEN_MONTHS = this.TERM * 12.0 - i;
            }
            prepay_FIRST_YEAR_INTEREST += round3;
            n15 += round4;
            n7 += n29;
            n8 += n24;
            n10 += n26;
            this.PREPAY_TOTAL_OF_PAYMENTS += n24 + n26;
            this.PREPAY_INTEREST_PAID += round3;
            first_YEAR_INTEREST += round;
            n16 += round2;
            n12 += n28;
            n13 += n25;
            this.TOTAL_OF_PAYMENTS += n25;
            this.INTEREST_PAID += round;
            if (i <= n) {
                this.PREPAY_TOT_SAVED_PAYMENTS = this.PREPAY_TOT_SAVED_PAYMENTS * n4 + n26;
                this.PREPAY_TOT_SAVED_PAYMENTS_AFTX = this.PREPAY_TOT_SAVED_PAYMENTS_AFTX * n4 * (1.0 - n3) + n26;
                this.HOME_VALUE_AT_MOVEOUT += n5 * this.HOME_VALUE_AT_MOVEOUT;
                if (prepay_ENDING_BALANCE == 0.0) {
                    this.PREPAY_TOTAL_VALUE = this.PREPAY_TOTAL_VALUE * n4 + n25;
                    this.PREPAY_TOTAL_VALUE_AFTX = this.PREPAY_TOTAL_VALUE_AFTX * (n4 * (1.0 - n3)) + round * (1.0 - n3) + n28;
                }
            }
            if (i == n) {
                this.TOT_EQUITY_AT_MOVEOUT = this.HOME_VALUE_AT_MOVEOUT - loan_AMOUNT;
                this.PREPAY_TOT_EQUITY_AT_MOVEOUT = this.HOME_VALUE_AT_MOVEOUT - prepay_ENDING_BALANCE;
                this.PREPAY_TOTAL_VALUE = this.PREPAY_TOTAL_VALUE + this.PREPAY_TOT_EQUITY_AT_MOVEOUT - this.TOT_EQUITY_AT_MOVEOUT;
                this.PREPAY_TOTAL_VALUE_AFTX = this.PREPAY_TOTAL_VALUE_AFTX + this.PREPAY_TOT_EQUITY_AT_MOVEOUT - this.TOT_EQUITY_AT_MOVEOUT;
                if (this.PREPAY_TOTAL_VALUE >= this.PREPAY_TOT_SAVED_PAYMENTS) {
                    this.TOT_DIFFERENCE = this.PREPAY_TOTAL_VALUE - this.PREPAY_TOT_SAVED_PAYMENTS;
                    this.TOT_DIFFERENCE_MSG = " advantage, before taxes, created by prepaying your mortgage.";
                }
                else {
                    this.TOT_DIFFERENCE = this.PREPAY_TOT_SAVED_PAYMENTS - this.PREPAY_TOTAL_VALUE;
                    this.TOT_DIFFERENCE_MSG = " advantage, before taxes, created by investing instead of prepaying your mortgage.";
                }
                if (this.PREPAY_TOTAL_VALUE_AFTX >= this.PREPAY_TOT_SAVED_PAYMENTS_AFTX) {
                    this.TOT_DIFFERENCE_AFTX = this.PREPAY_TOTAL_VALUE_AFTX - this.PREPAY_TOT_SAVED_PAYMENTS_AFTX;
                    this.TOT_DIFFERENCE_AFTX_MSG = " advantage, after taxes, created by prepaying your mortgage.";
                }
                else {
                    this.TOT_DIFFERENCE_AFTX = this.PREPAY_TOT_SAVED_PAYMENTS_AFTX - this.PREPAY_TOTAL_VALUE_AFTX;
                    this.TOT_DIFFERENCE_AFTX_MSG = " advantage, after taxes, created by investing instead of prepaying your mortgage.";
                }
                if (i == this.TERM * 12.0) {
                    this.TOT_DIFFERENCE_AFTX_MSG = "At the end of your normal mortgage term there is a " + Fmt.dollars(this.TOT_DIFFERENCE_AFTX, 2) + this.TOT_DIFFERENCE_AFTX_MSG;
                    this.TOT_DIFFERENCE_MSG = String.valueOf(this.TOT_DIFFERENCE_MSG) + " At the end of your normal mortgage term there is a " + Fmt.dollars(this.TOT_DIFFERENCE, 2) + this.TOT_DIFFERENCE_MSG;
                }
                else {
                    this.TOT_DIFFERENCE_AFTX_MSG = String.valueOf(this.TOT_DIFFERENCE_AFTX_MSG) + " At the time you sell or refinance your home there is a " + Fmt.dollars(this.TOT_DIFFERENCE_AFTX, 2) + this.TOT_DIFFERENCE_AFTX_MSG;
                    this.TOT_DIFFERENCE_MSG = String.valueOf(this.TOT_DIFFERENCE_MSG) + " At the time you sell or refinance your home there is a " + Fmt.dollars(this.TOT_DIFFERENCE, 2) + this.TOT_DIFFERENCE_MSG;
                }
            }
            if (i % 12 == 0) {
                double prepay_FIRST_YEAR_TAX_SAVINGS;
                if (i == 12) {
                    this.FIRST_YEAR_INTEREST = first_YEAR_INTEREST;
                    this.PREPAY_FIRST_YEAR_INTEREST = prepay_FIRST_YEAR_INTEREST;
                    this.FIRST_YEAR_TAX_SAVINGS = this.MARGINAL_TAX_RATE / 100.0 * (this.DISCOUNT_POINTS_AMT + n16 + yearly_PROPERTY_TAXES);
                    final double first_YEAR_TAX_SAVINGS = this.FIRST_YEAR_TAX_SAVINGS;
                    this.PREPAY_FIRST_YEAR_TAX_SAVINGS = this.MARGINAL_TAX_RATE / 100.0 * (this.DISCOUNT_POINTS_AMT + n15 + yearly_PROPERTY_TAXES);
                    prepay_FIRST_YEAR_TAX_SAVINGS = this.PREPAY_FIRST_YEAR_TAX_SAVINGS;
                }
                else {
                    yearly_PROPERTY_TAXES *= 1.0 + this.INFLATION_RATE;
                    prepay_FIRST_YEAR_TAX_SAVINGS = this.MARGINAL_TAX_RATE / 100.0 * (n16 + yearly_PROPERTY_TAXES);
                    n27 = this.MARGINAL_TAX_RATE / 100.0 * (n15 + yearly_PROPERTY_TAXES);
                }
                n14 += prepay_FIRST_YEAR_TAX_SAVINGS;
                n11 += n27;
                n16 = 0.0;
                n15 = 0.0;
            }
            if (b && n23 == i) {
                this.BALLOON_PAYMENT = loan_AMOUNT + n25;
                loan_AMOUNT = 0.0;
                this.PREPAY_BALLOON_PAYMENT = prepay_ENDING_BALANCE + n24 + n26;
                prepay_ENDING_BALANCE = 0.0;
                this.TOTAL_OF_PAYMENTS -= n25;
                this.PREPAY_TOTAL_OF_PAYMENTS -= n26 + n24;
            }
            if (this.BY_YEAR != 1 && super.bWithSchedule) {
                if (b2) {
                    this.addPrepayRow(i, (b && n23 == i) ? this.BALLOON_PAYMENT : n25, round, loan_AMOUNT, (b && n23 == i) ? this.PREPAY_BALLOON_PAYMENT : (n26 + n24), round3, prepay_ENDING_BALANCE);
                }
                else {
                    this.addRegularRow(i, (b && n23 == i) ? this.BALLOON_PAYMENT : n25, (b && n23 == i) ? (this.BALLOON_PAYMENT - round) : n28, round, loan_AMOUNT);
                }
            }
            if (i % 12 == 0) {
                this.cats[n17] = String.valueOf(n17);
                if (b && n23 == i) {
                    this.DS_PREPAY_PRINCIPAL_BAL[n17] = (float)(this.PREPAY_BALLOON_PAYMENT / this.iFactor);
                    this.DS_PRINCIPAL_BAL[n17] = (float)(this.BALLOON_PAYMENT / this.iFactor);
                }
                else {
                    this.DS_PREPAY_PRINCIPAL_BAL[n17] = (float)(prepay_ENDING_BALANCE / this.iFactor);
                    this.DS_PRINCIPAL_BAL[n17] = (float)(loan_AMOUNT / this.iFactor);
                }
                ++n17;
                if (this.BY_YEAR == 1 && super.bWithSchedule) {
                    if (b2) {
                        this.addPrepayRow(i / 12, (b && n23 == i) ? (this.BALLOON_PAYMENT - n25 + n13) : n13, first_YEAR_INTEREST, loan_AMOUNT, (b && n23 == i) ? (this.PREPAY_BALLOON_PAYMENT - n26 - n24 + n8 + n10) : (n8 + n10), prepay_FIRST_YEAR_INTEREST, prepay_ENDING_BALANCE);
                    }
                    else {
                        this.addRegularRow(i / 12, (b && n23 == i) ? (this.BALLOON_PAYMENT - n25 + n13) : n13, (b && n23 == i) ? (this.BALLOON_PAYMENT + n12 - round - n28) : n12, first_YEAR_INTEREST, loan_AMOUNT);
                    }
                }
                first_YEAR_INTEREST = 0.0;
                n16 = 0.0;
                n12 = 0.0;
                n13 = 0.0;
                prepay_FIRST_YEAR_INTEREST = 0.0;
                n15 = 0.0;
                n7 = 0.0;
                n8 = 0.0;
                n10 = 0.0;
            }
            if (i == this.RECAST_TO_AMORTIZE || (((i < this.ADJUSTABLE_RATE_FIXED) ? 1 : ((i - this.ADJUSTABLE_RATE_FIXED) % this.ADJUSTABLE_RATE_FEQ)) == 0 && i != 1 && this.ADJUSTABLE_RATE == 1.0 && i != this.TERM * 12.0 && this.ADJUSTABLE_RATE_INCR != 0.0 && i >= this.ADJUSTABLE_RATE_FIXED)) {
                n21 += this.ADJUSTABLE_RATE_INCR / 100.0;
                if (n21 > this.ADJUSTABLE_RATE_CAP / 100.0) {
                    n21 = this.ADJUSTABLE_RATE_CAP / 100.0;
                }
                if (n21 < 0.02) {
                    n21 = 0.02;
                }
                if (n21 != n20 || i == this.RECAST_TO_AMORTIZE) {
                    n20 = n21;
                    n2 = n21 / 12.0;
                    n19 = Fmt.round(Calculation.PMT(n2, this.TERM * 12.0 - i, loan_AMOUNT), 2);
                    n18 = Fmt.round(Calculation.PMT(n2, this.TERM * 12.0 - i, prepay_ENDING_BALANCE), 2);
                    this.FULLY_INDEXED_PAYMENT = n19;
                    if (this.ADJUSTABLE_RATE_HIGHEST < n19) {
                        this.ADJUSTABLE_RATE_HIGHEST = n19;
                    }
                    this.addAdjRepeat("<TD>" + super._sCell + i + super._sCellFormat + Fmt.percent(n21, 2) + super._sCellFormat + Fmt.dollars(n19, 2) + super._sCellFooter + "</TD>", n22++, sb2);
                }
            }
        }
        if (this.ADJUSTABLE_RATE == 1.0 && this.ADJUSTABLE_RATE_INCR != 0.0) {
            this.ADJUSTABLE_PAYMENT_AMTS = sb2.append(super._sTableFooter).toString();
        }
        this.PREPAY_SHORTEN_YEARS = (int)(this.PREPAY_SHORTEN_MONTHS / 12.0);
        this.PREPAY_SHORTEN_MONTHS %= 12.0;
        this.PREPAY_MESSAGE = this.MSG_PREPAY_MESSAGE;
        this.PREPAY_AVG_TAX_SAVINGS = n11 / this.TERM;
        this.AVG_TAX_SAVINGS = n14 / this.TERM;
        this.ENDING_BALANCE = loan_AMOUNT;
        this.PREPAY_ENDING_BALANCE = prepay_ENDING_BALANCE;
        int n30 = 1;
        if (b2) {
            n30 = 2;
        }
        this.iFactor2 = this.getGraphUnits(this.TOTAL_OF_PAYMENTS + (b ? this.BALLOON_PAYMENT : 0.0));
        this.DS_INTEREST = new float[n30];
        this.DS_PRINCIPAL = new float[n30];
        this.totalpaid_cats = new String[n30];
        this.DS_INTEREST[0] = (float)(this.INTEREST_PAID / this.iFactor2);
        this.DS_PRINCIPAL[0] = (float)((this.LOAN_AMOUNT - this.ENDING_BALANCE) / this.iFactor2);
        this.totalpaid_cats[0] = this.MSG_GRAPH_COL1;
        if (b2) {
            this.DS_INTEREST[1] = (float)(this.PREPAY_INTEREST_PAID / this.iFactor2);
            this.DS_PRINCIPAL[1] = (float)((this.LOAN_AMOUNT - this.PREPAY_ENDING_BALANCE) / this.iFactor2);
            this.totalpaid_cats[1] = this.MSG_GRAPH_COL2;
            this.PREPAY_INTEREST_SAVINGS = this.INTEREST_PAID - this.PREPAY_INTEREST_PAID;
        }
    }
    
    protected void clearCalculated() {
        this.INTEREST_PAID = 0.0;
        this.TOTAL_OF_PAYMENTS = 0.0;
        this.MONTHLY_PITI = 0.0;
        this.MONTHLY_HOME_INSURANCE = 0.0;
        this.ENDING_BALANCE = 0.0;
        this.FIRST_MONTH_INTEREST = 0.0;
        this.FIRST_MONTH_PRINCIPAL = 0.0;
        this.MONTHLY_PROPERTY_TAXES = 0.0;
        this.MONTHLY_PMI = 0.0;
        this.AVG_TAX_SAVINGS = 0.0;
        this.TAX_ADJ_RATE = 0.0;
        this.PERCENT_DOWNPAYMENT = 0.0;
        this.LOAN_TO_VALUE = 0.0;
        this.TOTAL_CLOSING_COSTS = 0.0;
        this.DISCOUNT_POINTS_AMT = 0.0;
        this.ORIGINATION_FEES_AMT = 0.0;
        this.FIRST_YEAR_INTEREST = 0.0;
        this.FIRST_YEAR_TAX_SAVINGS = 0.0;
        this.HOME_VALUE_AT_MOVEOUT = 0.0;
        this.ADJUSTABLE_PAYMENT_AMTS = "";
        this.ADJUSTABLE_RATE_HIGHEST = 0.0;
        this.PREPAY_INTEREST_PAID = 0.0;
        this.PREPAY_SHORTEN_YEARS = 0.0;
        this.PREPAY_FIRST_YEAR_INTEREST = 0.0;
        this.PREPAY_AVG_TAX_SAVINGS = 0.0;
        this.PREPAY_FIRST_YEAR_TAX_SAVINGS = 0.0;
        this.PREPAY_INTEREST_SAVINGS = 0.0;
        this.PREPAY_MESSAGE = "";
        this.TOT_EQUITY_AT_MOVEOUT = 0.0;
        this.PREPAY_TOT_EQUITY_AT_MOVEOUT = 0.0;
        this.PREPAY_TOT_SAVED_PAYMENTS = 0.0;
        this.PREPAY_TOT_SAVED_PAYMENTS_AFTX = 0.0;
        this.PREPAY_TOTAL_VALUE = 0.0;
        this.PREPAY_TOTAL_VALUE_AFTX = 0.0;
        this.TOT_DIFFERENCE = 0.0;
        this.TOT_DIFFERENCE_MSG = "";
        this.TOT_DIFFERENCE_AFTX = 0.0;
        this.TOT_DIFFERENCE_AFTX_MSG = "";
        this.LOAN_APR = 0.0;
        this.LOAN_APR_PAYMENT = 0.0;
        this.LOAN_APR_AMOUNT = 0.0;
        this.LOAN_APR_AFT = 0.0;
    }
    
    public void clearInputed() {
        this.INTEREST_RATE = 0.0;
        this.TERM = 0.0;
        this.TERM_BALLOON = 0.0;
        this.LOAN_AMOUNT = 0.0;
        this.PREPAY_AMOUNT = 0.0;
        this.PREPAY_STARTS_WITH = 1.0;
        this.PREPAY_TYPE = this.PREPAY_NONE;
        this.PMI_RATE = 0.5;
        this.PURCHASE_PRICE = 0.0;
        this.INFLATION_RATE = 3.0;
        this.MARGINAL_TAX_RATE = 0.0;
        this.YEARLY_PROPERTY_TAXES = 0.0;
        this.YEARLY_HOME_INSURANCE = 0.0;
        this.YEARS_IN_HOME = 0.0;
        this.SAVINGS_RATE = 0.0;
        this.DISCOUNT_POINTS_PERCENT = 0.0;
        this.ORIGINATION_FEES_PERCENT = 0.0;
        this.OTHER_FEES = 0.0;
        this.HOME_APPRECIATION = 0.0;
        this.ADJUSTABLE_RATE_CAP = 0.0;
        this.ADJUSTABLE_RATE_INCR = 0.0;
        this.ADJUSTABLE_RATE = 0.0;
        this.ADJUSTABLE_RATE_FEQ = 12;
        this.ADJUSTABLE_RATE_FIXED = 0;
        this.BY_YEAR = 1;
        this.FEDERAL_TAX_RATE = 0.0;
        this.STATE_TAX_RATE = 0.0;
        this.MARGINAL_TAX_RATE = 0.0;
    }
    
    protected String formatReport(final String s) {
        final String replace = Calculation.replace("REGULAR_PAYMENTS", Fmt.input(this.TERM_BALLOON * 12.0 - 1.0), Calculation.replace("ADJUSTABLE_RATE", Fmt.yesno(this.ADJUSTABLE_RATE), Calculation.replace("RATE_INDEX", Fmt.percent(this.RATE_INDEX / 100.0, 3), Calculation.replace("RATE_INDEX_MARGIN", Fmt.percent(this.RATE_INDEX_MARGIN / 100.0, 3), Calculation.replace("ADJUSTABLE_RATE_FIXED", Fmt.number(this.ADJUSTABLE_RATE_FIXED), Calculation.replace("ADJUSTABLE_RATE_HIGHEST", Fmt.dollars(this.ADJUSTABLE_RATE_HIGHEST, 2), Calculation.replace("ADJUSTABLE_PAYMENT_AMTS", this.ADJUSTABLE_PAYMENT_AMTS, Calculation.replace("ADJUSTABLE_RATE_CAP", Fmt.percent(this.ADJUSTABLE_RATE_CAP / 100.0, 3), Calculation.replace("ADJUSTABLE_RATE_INCR", Fmt.percent(this.ADJUSTABLE_RATE_INCR / 100.0, 2), Calculation.replace("ADJUSTABLE_RATE_FEQ", Fmt.number(this.ADJUSTABLE_RATE_FEQ), Calculation.replace("PURCHASE_PRICE", Fmt.dollars(this.PURCHASE_PRICE, 2), Calculation.replace("SAVINGS_RATE", Fmt.percent(this.SAVINGS_RATE / 100.0, 3), Calculation.replace("TAX_ADJ_RATE", Fmt.percent(this.TAX_ADJ_RATE, 3), Calculation.replace("TERM", Fmt.number(this.TERM), Calculation.replace("TERM_BALLOON", Fmt.number(this.TERM_BALLOON), Calculation.replace("TOT_DIFFERENCE", Fmt.dollars(this.TOT_DIFFERENCE, 2), Calculation.replace("TOT_DIFFERENCE_AFTX", Fmt.dollars(this.TOT_DIFFERENCE_AFTX, 2), Calculation.replace("TOT_DIFFERENCE_AFTX_MSG", this.TOT_DIFFERENCE_AFTX_MSG, Calculation.replace("TOT_DIFFERENCE_MSG", this.TOT_DIFFERENCE_MSG, Calculation.replace("TOT_EQUITY_AT_MOVEOUT", Fmt.dollars(this.TOT_EQUITY_AT_MOVEOUT, 2), Calculation.replace("TOTAL_CLOSING_COSTS", Fmt.dollars(this.TOTAL_CLOSING_COSTS, 2), Calculation.replace("YEARLY_HOME_INSURANCE", Fmt.dollars(this.YEARLY_HOME_INSURANCE, 2), Calculation.replace("YEARLY_PROPERTY_TAXES", Fmt.dollars(this.YEARLY_PROPERTY_TAXES, 2), Calculation.replace("YEARS_IN_HOME", Fmt.number(this.YEARS_IN_HOME), Calculation.replace("RESULT_MESSAGE", this.sReturnMessage, Calculation.replace("MSG_TERM", this.MSG_TERM, Calculation.replace("ADJUSTABLE_YEARS", Fmt.number(this.TERM - this.ADJUSTABLE_RATE_FIXED / 12), Calculation.replace("FIXED_YEARS", Fmt.number(this.ADJUSTABLE_RATE_FIXED / 12), s))))))))))))))))))))))))))));
        String s2;
        if (this.PREPAY_TYPE.equals(this.PREPAY_NONE)) {
            s2 = Calculation.replace("PREPAY_BALLOON_PAYMENT", "", Calculation.replace("PREPAY_ENDING_BALANCE", "", Calculation.replace("PREPAY_AMOUNT", "", Calculation.replace("PREPAY_AVG_TAX_SAVINGS", "", Calculation.replace("PREPAY_FIRST_YEAR_INTEREST", "", Calculation.replace("PREPAY_FIRST_YEAR_TAX_SAVINGS", "", Calculation.replace("PREPAY_INTEREST_PAID", "", Calculation.replace("PREPAY_INTEREST_SAVINGS", "", Calculation.replace("PREPAY_SHORTEN_MONTHS", "", Calculation.replace("PREPAY_SHORTEN_YEARS", "", Calculation.replace("PREPAY_STARTS_WITH", "", Calculation.replace("PREPAY_TOT_EQUITY_AT_MOVEOUT", "", Calculation.replace("PREPAY_TOT_SAVED_PAYMENTS", "", Calculation.replace("PREPAY_TOT_SAVED_PAYMENTS_AFTX", "", Calculation.replace("PREPAY_TOTAL_OF_PAYMENTS", "", Calculation.replace("PREPAY_TOTAL_VALUE", "", Calculation.replace("PREPAY_TOTAL_VALUE_AFTX", "", Calculation.replace("PREPAY_TYPE", this.PREPAY_TYPE, Calculation.replace("PREPAY_MESSAGE", "", replace)))))))))))))))))));
        }
        else {
            s2 = Calculation.replace("PREPAY_BALLOON_PAYMENT", Fmt.dollars(this.PREPAY_BALLOON_PAYMENT, 2), Calculation.replace("PREPAY_ENDING_BALANCE", Fmt.dollars(this.PREPAY_ENDING_BALANCE, 2), Calculation.replace("PREPAY_AMOUNT", Fmt.dollars(this.PREPAY_AMOUNT, 2), Calculation.replace("PREPAY_AVG_TAX_SAVINGS", Fmt.dollars(this.PREPAY_AVG_TAX_SAVINGS, 2), Calculation.replace("PREPAY_FIRST_YEAR_INTEREST", Fmt.dollars(this.PREPAY_FIRST_YEAR_INTEREST, 2), Calculation.replace("PREPAY_FIRST_YEAR_TAX_SAVINGS", Fmt.dollars(this.PREPAY_FIRST_YEAR_TAX_SAVINGS, 2), Calculation.replace("PREPAY_INTEREST_PAID", Fmt.dollars(this.PREPAY_INTEREST_PAID, 2), Calculation.replace("PREPAY_INTEREST_SAVINGS", Fmt.dollars(this.PREPAY_INTEREST_SAVINGS, 2), Calculation.replace("PREPAY_SHORTEN_MONTHS", Fmt.number(this.PREPAY_SHORTEN_MONTHS), Calculation.replace("PREPAY_SHORTEN_YEARS", Fmt.number(this.PREPAY_SHORTEN_YEARS), Calculation.replace("PREPAY_STARTS_WITH", Fmt.number(this.PREPAY_STARTS_WITH), Calculation.replace("PREPAY_TOT_EQUITY_AT_MOVEOUT", Fmt.dollars(this.PREPAY_TOT_EQUITY_AT_MOVEOUT, 2), Calculation.replace("PREPAY_TOT_SAVED_PAYMENTS", Fmt.dollars(this.PREPAY_TOT_SAVED_PAYMENTS, 2), Calculation.replace("PREPAY_TOT_SAVED_PAYMENTS_AFTX", Fmt.dollars(this.PREPAY_TOT_SAVED_PAYMENTS_AFTX, 2), Calculation.replace("PREPAY_TOTAL_OF_PAYMENTS", Fmt.dollars(this.PREPAY_TOTAL_OF_PAYMENTS, 2), Calculation.replace("PREPAY_TOTAL_VALUE", Fmt.dollars(this.PREPAY_TOTAL_VALUE, 2), Calculation.replace("PREPAY_TOTAL_VALUE_AFTX", Fmt.dollars(this.PREPAY_TOTAL_VALUE_AFTX, 2), Calculation.replace("PREPAY_TYPE", this.PREPAY_TYPE, Calculation.replace("PREPAY_MESSAGE", this.PREPAY_MESSAGE, replace)))))))))))))))))));
        }
        return Calculation.replace("CHECKBOX_BY_YEAR", (this.BY_YEAR == 1) ? "CHECKED" : "", Calculation.replace("CHECKBOX_BY_MONTH", (this.BY_YEAR == 0) ? "CHECKED" : "", Calculation.replace("INTEREST_ONLY", Fmt.yesno((double)(this.INTEREST_ONLY ? 1 : 0)), Calculation.replace("FULLY_INDEXED_PAYMENT", Fmt.dollars(this.FULLY_INDEXED_PAYMENT, 2), Calculation.replace("BALLOON_PAYMENT", Fmt.dollars(this.BALLOON_PAYMENT, 2), Calculation.replace("ENDING_BALANCE", Fmt.dollars(this.ENDING_BALANCE, 2), Calculation.replace("TOTAL_OF_PAYMENTS", Fmt.dollars(this.TOTAL_OF_PAYMENTS, 2), Calculation.replace("AVG_TAX_SAVINGS", Fmt.dollars(this.AVG_TAX_SAVINGS, 2), Calculation.replace("DISCOUNT_POINTS_AMT", Fmt.dollars(this.DISCOUNT_POINTS_AMT, 2), Calculation.replace("DISCOUNT_POINTS_PERCENT", Fmt.number(this.DISCOUNT_POINTS_PERCENT, 2), Calculation.replace("FIRST_MONTH_INTEREST", Fmt.dollars(this.FIRST_MONTH_INTEREST, 2), Calculation.replace("FIRST_MONTH_PRINCIPAL", Fmt.dollars(this.FIRST_MONTH_PRINCIPAL, 2), Calculation.replace("FIRST_YEAR_INTEREST", Fmt.dollars(this.FIRST_YEAR_INTEREST, 2), Calculation.replace("FIRST_YEAR_TAX_SAVINGS", Fmt.dollars(this.FIRST_YEAR_TAX_SAVINGS, 2), Calculation.replace("HOME_APPRECIATION", Fmt.percent(this.HOME_APPRECIATION / 100.0, 2), Calculation.replace("HOME_VALUE_AT_MOVEOUT", Fmt.dollars(this.HOME_VALUE_AT_MOVEOUT, 2), Calculation.replace("INFLATION_RATE", Fmt.percent(this.INFLATION_RATE / 100.0, 2), Calculation.replace("INTEREST_PAID", Fmt.dollars(this.INTEREST_PAID, 2), Calculation.replace("INTEREST_RATE", Fmt.percent(this.INTEREST_RATE / 100.0, 3), Calculation.replace("LOAN_AMOUNT", Fmt.dollars(this.LOAN_AMOUNT, 2), Calculation.replace("LOAN_APR", Fmt.percent(this.LOAN_APR, 3), Calculation.replace("LOAN_APR_AMOUNT", Fmt.dollars(this.LOAN_APR_AMOUNT, 2), Calculation.replace("LOAN_APR_PAYMENT", Fmt.dollars(this.LOAN_APR_PAYMENT, 2), Calculation.replace("LOAN_APR_AFT", Fmt.percent(this.LOAN_APR_AFT, 3), Calculation.replace("LOAN_TO_VALUE", Fmt.percent(this.LOAN_TO_VALUE, 2), Calculation.replace("STATE_TAX_RATE", Fmt.percent(this.STATE_TAX_RATE / 100.0, 2), Calculation.replace("FEDERAL_TAX_RATE", Fmt.percent(this.FEDERAL_TAX_RATE / 100.0, 2), Calculation.replace("MARGINAL_TAX_RATE", Fmt.percent(this.MARGINAL_TAX_RATE / 100.0, 2), Calculation.replace("MONTHLY_HOME_INSURANCE", Fmt.dollars(this.MONTHLY_HOME_INSURANCE, 2), Calculation.replace("MONTHLY_PI", Fmt.dollars(this.MONTHLY_PI, 2), Calculation.replace("MONTHLY_PITI", Fmt.dollars(this.MONTHLY_PITI, 2), Calculation.replace("MONTHLY_PMI", Fmt.dollars(this.MONTHLY_PMI, 2), Calculation.replace("MONTHLY_PROPERTY_TAXES", Fmt.dollars(this.MONTHLY_PROPERTY_TAXES, 2), Calculation.replace("ORIGINATION_FEES_AMT", Fmt.dollars(this.ORIGINATION_FEES_AMT, 2), Calculation.replace("ORIGINATION_FEES_PERCENT", Fmt.percent(this.ORIGINATION_FEES_PERCENT / 100.0, 2), Calculation.replace("OTHER_FEES", Fmt.dollars(this.OTHER_FEES, 2), Calculation.replace("PERCENT_DOWNPAYMENT", Fmt.percent(this.PERCENT_DOWNPAYMENT, 2), Calculation.replace("PMI_RATE", Fmt.percent(this.PMI_RATE / 100.0, 2), s2))))))))))))))))))))))))))))))))))))));
    }
    
    public String getAmountLabel() {
        return this.sTotalCost.equals("") ? "" : (String.valueOf(this.sTotalCost) + super.sCurrency);
    }
    
    public String getAmountLabel2() {
        return super._sGraphUnits.equals("") ? "" : (String.valueOf(super._sGraphUnits) + super.sCurrency);
    }
    
    public String[] getAmountPaidCatagories() {
        return this.totalpaid_cats;
    }
    
    public String[] getCatagories() {
        return this.cats;
    }
}
