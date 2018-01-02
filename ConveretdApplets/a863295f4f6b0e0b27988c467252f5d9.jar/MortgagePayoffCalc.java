import KJEgui.Fmt;
import KJEcalculation.CalculationException;
import KJEcalculation.Calculation;

// 
// Decompiled by Procyon v0.5.30
// 

public class MortgagePayoffCalc extends Calculation
{
    public double MORTGAGE_YRS_LEFT;
    public double MORTGAGE_YRS_LENGTH;
    public double MORTGAGE_AMT;
    public double INCREASE_BY_AMT;
    public double RATE;
    int PAYOFF_YEARS;
    int PAYOFF_MONTHS;
    public String MSG_ERROR1;
    public int BY_YEAR;
    public double NBR_PERIODS;
    public double PERC_RESULTS_FACTOR;
    public double MAX_MORTGAGE_YRS_LENGTH;
    public double MAX_MORTGAGE_AMT;
    public double MAX_INCREASE_BY_AMT;
    public double MAX_RATE;
    public double MONTHLY_RATE;
    public double MORTGAGE_MONTH_LENGTH;
    public double PI_PAYMENT;
    public double MORTGAGE_MONTHS_LEFT;
    public double MORTGAGE_MONTHS_ELAPSED;
    public double MORTGAGE_INTEREST_TOTAL;
    public double MORTGAGE_PAYMENT_TOTAL;
    public double EARLY_PAYOFF_MONTHS;
    public double EARLY_PAYOFF_YEARS;
    public double EARLY_PAYOFF_PI_PAYMENT;
    public double EARLY_PAYOFF_INTEREST_TOTAL;
    public double EARLY_PAYOFF_PAYMENT_TOTAL;
    public double EARLY_PAYOFF_SAVINGS;
    public float[] aInterestForPeriod;
    public float[] aNewInterestForPeriod;
    public float[] aPrincipalForPeriod;
    public float[] aNewPrincipalForPeriod;
    public float[] aInterestPaid;
    public float[] aNewInterestPaid;
    public float[] aPrincipalBalance;
    public float[] aNewPrincipalBalance;
    
    public MortgagePayoffCalc() {
        this.MORTGAGE_YRS_LEFT = 0.0;
        this.MORTGAGE_YRS_LENGTH = 0.0;
        this.MORTGAGE_AMT = 0.0;
        this.INCREASE_BY_AMT = 0.0;
        this.RATE = 0.0;
        this.PAYOFF_YEARS = 0;
        this.PAYOFF_MONTHS = 0;
        this.MSG_ERROR1 = "Years remaining must be less mortgage term.";
        this.BY_YEAR = 1;
        this.NBR_PERIODS = 12.0;
        this.PERC_RESULTS_FACTOR = 100.0;
        this.MAX_MORTGAGE_YRS_LENGTH = 100.0;
        this.MAX_MORTGAGE_AMT = 1000000.0;
        this.MAX_INCREASE_BY_AMT = 100000.0;
        this.MAX_RATE = 100.0 / this.NBR_PERIODS;
        this.MONTHLY_RATE = 0.0;
        this.MORTGAGE_MONTH_LENGTH = 0.0;
        this.PI_PAYMENT = 0.0;
        this.MORTGAGE_MONTHS_LEFT = 0.0;
        this.MORTGAGE_MONTHS_ELAPSED = 0.0;
        this.MORTGAGE_INTEREST_TOTAL = 0.0;
        this.MORTGAGE_PAYMENT_TOTAL = 0.0;
        this.EARLY_PAYOFF_MONTHS = 0.0;
        this.EARLY_PAYOFF_YEARS = 0.0;
        this.EARLY_PAYOFF_PI_PAYMENT = 0.0;
        this.EARLY_PAYOFF_INTEREST_TOTAL = 0.0;
        this.EARLY_PAYOFF_PAYMENT_TOTAL = 0.0;
        this.EARLY_PAYOFF_SAVINGS = 0.0;
    }
    
    protected void calculate() throws CalculationException {
        if (this.MORTGAGE_YRS_LEFT > this.MORTGAGE_YRS_LENGTH) {
            throw new CalculationException(this.MSG_ERROR1);
        }
        this.MONTHLY_RATE = this.RATE / (this.NBR_PERIODS * 100.0);
        this.MORTGAGE_MONTH_LENGTH = this.MORTGAGE_YRS_LENGTH * this.NBR_PERIODS;
        this.PI_PAYMENT = Fmt.round(Calculation.PMT(this.MONTHLY_RATE, this.MORTGAGE_MONTH_LENGTH, this.MORTGAGE_AMT), 2);
        this.MORTGAGE_MONTHS_LEFT = this.MORTGAGE_YRS_LEFT * this.NBR_PERIODS;
        this.MORTGAGE_MONTHS_ELAPSED = (this.MORTGAGE_YRS_LENGTH - this.MORTGAGE_YRS_LEFT) * this.NBR_PERIODS;
        double mortgage_INTEREST_TOTAL = 0.0;
        double mortgage_AMT = this.MORTGAGE_AMT;
        double early_PAYOFF_INTEREST_TOTAL = 0.0;
        double mortgage_AMT2 = this.MORTGAGE_AMT;
        double n = 0.0;
        final int n2 = (int)Math.round(this.MORTGAGE_YRS_LENGTH);
        this.aInterestForPeriod = new float[n2];
        this.aNewInterestForPeriod = new float[n2];
        this.aPrincipalForPeriod = new float[n2];
        this.aNewPrincipalForPeriod = new float[n2];
        this.aInterestPaid = new float[n2];
        this.aNewInterestPaid = new float[n2];
        this.aPrincipalBalance = new float[n2];
        this.aNewPrincipalBalance = new float[n2];
        final StringBuffer sb = new StringBuffer();
        if (super.bWithSchedule) {
            if (this.BY_YEAR != 1) {
                this.addRepeat("<TD>" + super._sCell + "<B>&nbsp;</B>" + super._sCellFooter + "</TD><TD COLSPAN=3>" + super._sCell + this.sReportCol("<B><CENTER><U>Existing Payment Schedule</U></CENTER></B>", 1) + super._sCellFooter + "</TD><TD COLSPAN=3>" + super._sCell + this.sReportCol("<B><CENTER><U>Accelerated Payment Schedule</U></CENTER></B>", 2) + super._sCellFooter + "</TD>" + super._sRowFooter + super._sTopRow + "<TD>" + super._sCell + this.sReportCol("<B>#</B>", 3) + super._sCellFormat + this.sReportCol("<B>Payment</B>", 4) + super._sCellFormat + this.sReportCol("<B>Interest</B>", 5) + super._sCellFormat + this.sReportCol("<B>Balance</B>", 6) + super._sCellFormat + this.sReportCol("<B>Payment</B>", 4) + super._sCellFormat + this.sReportCol("<B>Interest</B>", 5) + super._sCellFormat + this.sReportCol("<B>Balance</B>", 6) + super._sCellFooter + "</TD>");
            }
            else {
                this.addRepeat("<TD>" + super._sCell + "<B>&nbsp;</B>" + super._sCellFooter + "</TD><TD COLSPAN=3>" + super._sCell + this.sReportCol("<B><CENTER><U>Existing Payment Schedule</CENTER></U></B>", 1) + super._sCellFooter + "</TD><TD COLSPAN=3>" + super._sCell + this.sReportCol("<B><CENTER><U>Accelerated Payment Schedule</CENTER></U></B>", 2) + super._sCellFooter + "</TD>" + super._sRowFooter + super._sTopRow + "<TD>" + super._sCell + this.sReportCol("<B><BR>Year</B>", 7) + super._sCellFormat + this.sReportCol("<B>Total<BR>Payments</B>", 8) + super._sCellFormat + this.sReportCol("<B>Total<BR>Interest</B>", 9) + super._sCellFormat + this.sReportCol("<B>Ending<BR>Balance</B>", 10) + super._sCellFormat + this.sReportCol("<B>Total<BR>Payment</B>", 8) + super._sCellFormat + this.sReportCol("<B>Total<BR>Interest</B>", 9) + super._sCellFormat + this.sReportCol("<B>Ending<BR>Balance</B>", 10) + super._sCellFooter + "</TD>");
            }
            this.addRepeat("<TD>" + super._sCell + "&nbsp;" + super._sCellFormat + "&nbsp;" + super._sCellFormat + "&nbsp;" + super._sCellFormat + Fmt.dollars(mortgage_AMT, 2) + super._sCellFooter + "</TD>" + "<TD>" + super._sCell + "&nbsp;" + super._sCellFormat + "&nbsp;" + super._sCellFormat + Fmt.dollars(mortgage_AMT2, 2) + super._sCellFooter + "</TD>");
        }
        double n3 = 0.0;
        double n4 = 0.0;
        double n5 = 0.0;
        double n6 = 0.0;
        for (int n7 = 1; n7 <= this.MORTGAGE_MONTH_LENGTH; ++n7) {
            final double round = Fmt.round(this.MONTHLY_RATE * mortgage_AMT, 2);
            final double round2 = Fmt.round(this.MONTHLY_RATE * mortgage_AMT2, 2);
            double n8;
            if (mortgage_AMT == 0.0) {
                n8 = 0.0;
            }
            else {
                n8 = this.PI_PAYMENT - round;
                if (n7 > this.MORTGAGE_MONTHS_ELAPSED) {
                    n = this.PI_PAYMENT + this.INCREASE_BY_AMT - round2;
                }
                else {
                    n = this.PI_PAYMENT - round2;
                }
                if (mortgage_AMT > 0.0) {
                    mortgage_AMT -= n8;
                }
            }
            if (mortgage_AMT2 == 0.0) {
                n = 0.0;
            }
            else {
                if (mortgage_AMT2 > 0.0) {
                    mortgage_AMT2 -= n;
                    this.EARLY_PAYOFF_MONTHS = n7;
                }
                if (mortgage_AMT < 0.0) {
                    n8 += mortgage_AMT;
                    mortgage_AMT = 0.0;
                }
                if (mortgage_AMT2 < 0.0) {
                    n += mortgage_AMT2;
                    mortgage_AMT2 = 0.0;
                }
            }
            this.aInterestForPeriod[(n7 - 1) / 12] = (float)round;
            this.aNewInterestForPeriod[(n7 - 1) / 12] = (float)round2;
            this.aPrincipalForPeriod[(n7 - 1) / 12] = (float)n8;
            this.aNewPrincipalForPeriod[(n7 - 1) / 12] = (float)n;
            mortgage_INTEREST_TOTAL += round;
            early_PAYOFF_INTEREST_TOTAL += round2;
            this.aInterestPaid[(n7 - 1) / 12] = (float)(mortgage_INTEREST_TOTAL / 1000.0);
            this.aNewInterestPaid[(n7 - 1) / 12] = (float)(early_PAYOFF_INTEREST_TOTAL / 1000.0);
            this.aPrincipalBalance[(n7 - 1) / 12] = (float)(mortgage_AMT / 1000.0);
            this.aNewPrincipalBalance[(n7 - 1) / 12] = (float)(mortgage_AMT2 / 1000.0);
            n3 += round + n8;
            n4 += round;
            final double n9 = mortgage_AMT;
            n5 += round2 + n;
            n6 += round2;
            final double n10 = mortgage_AMT2;
            if (super.bWithSchedule && this.BY_YEAR != 1) {
                this.addRepeat("<TD>" + super._sCell + n7 + super._sCellFormat + Fmt.dollars(round + n8, 2) + super._sCellFormat + Fmt.dollars(round, 2) + super._sCellFormat + Fmt.dollars(mortgage_AMT, 2) + super._sCellFormat + Fmt.dollars(round2 + n, 2) + super._sCellFormat + Fmt.dollars(round2, 2) + super._sCellFormat + Fmt.dollars(mortgage_AMT2, 2) + super._sCellFooter + "</TD>");
            }
            else if (super.bWithSchedule && n7 % 12 == 0) {
                this.addRepeat("<TD>" + super._sCell + Fmt.number(n7 / 12) + super._sCellFormat + Fmt.dollars(n3, 2) + super._sCellFormat + Fmt.dollars(n4, 2) + super._sCellFormat + Fmt.dollars(n9, 2) + super._sCellFormat + Fmt.dollars(n5, 2) + super._sCellFormat + Fmt.dollars(n6, 2) + super._sCellFormat + Fmt.dollars(n10, 2) + super._sCellFooter + "</TD>");
                n3 = 0.0;
                n4 = 0.0;
                n5 = 0.0;
                n6 = 0.0;
            }
        }
        this.MORTGAGE_INTEREST_TOTAL = mortgage_INTEREST_TOTAL;
        this.MORTGAGE_PAYMENT_TOTAL = mortgage_INTEREST_TOTAL + this.MORTGAGE_AMT;
        this.EARLY_PAYOFF_MONTHS = this.MORTGAGE_YRS_LENGTH * 12.0 - this.EARLY_PAYOFF_MONTHS;
        this.EARLY_PAYOFF_YEARS = (int)(this.EARLY_PAYOFF_MONTHS / 12.0);
        this.EARLY_PAYOFF_MONTHS -= (int)this.EARLY_PAYOFF_YEARS * 12;
        this.EARLY_PAYOFF_PI_PAYMENT = this.PI_PAYMENT + this.INCREASE_BY_AMT;
        this.EARLY_PAYOFF_INTEREST_TOTAL = early_PAYOFF_INTEREST_TOTAL;
        this.EARLY_PAYOFF_PAYMENT_TOTAL = early_PAYOFF_INTEREST_TOTAL + this.MORTGAGE_AMT;
        this.EARLY_PAYOFF_SAVINGS = this.MORTGAGE_PAYMENT_TOTAL - this.EARLY_PAYOFF_PAYMENT_TOTAL;
    }
    
    protected void clearCalculated() {
        this.MONTHLY_RATE = 0.0;
        this.MORTGAGE_MONTH_LENGTH = 0.0;
        this.PI_PAYMENT = 0.0;
        this.MORTGAGE_INTEREST_TOTAL = 0.0;
        this.MORTGAGE_PAYMENT_TOTAL = 0.0;
        this.EARLY_PAYOFF_MONTHS = 0.0;
        this.EARLY_PAYOFF_YEARS = 0.0;
        this.EARLY_PAYOFF_PI_PAYMENT = 0.0;
        this.EARLY_PAYOFF_INTEREST_TOTAL = 0.0;
        this.EARLY_PAYOFF_PAYMENT_TOTAL = 0.0;
        this.EARLY_PAYOFF_SAVINGS = 0.0;
    }
    
    public void clearInputed() {
        this.MORTGAGE_YRS_LEFT = 0.0;
        this.MORTGAGE_YRS_LENGTH = 0.0;
        this.MORTGAGE_AMT = 0.0;
        this.INCREASE_BY_AMT = 0.0;
        this.RATE = 0.0;
    }
    
    protected String formatReport(final String s) {
        return Calculation.replace("PI_PAYMENT", Fmt.dollars(this.PI_PAYMENT), Calculation.replace("EARLY_PAYOFF_SAVINGS", Fmt.dollars(this.EARLY_PAYOFF_SAVINGS), Calculation.replace("EARLY_PAYOFF_PAYMENT_TOTAL", Fmt.dollars(this.EARLY_PAYOFF_PAYMENT_TOTAL), Calculation.replace("EARLY_PAYOFF_INTEREST_TOTAL", Fmt.dollars(this.EARLY_PAYOFF_INTEREST_TOTAL), Calculation.replace("EARLY_PAYOFF_PI_PAYMENT", Fmt.dollars(this.EARLY_PAYOFF_PI_PAYMENT), Calculation.replace("EARLY_PAYOFF_YEARS", Fmt.number(this.EARLY_PAYOFF_YEARS), Calculation.replace("EARLY_PAYOFF_MONTHS", Fmt.number(this.EARLY_PAYOFF_MONTHS), Calculation.replace("MORTGAGE_PAYMENT_TOTAL", Fmt.dollars(this.MORTGAGE_PAYMENT_TOTAL), Calculation.replace("MORTGAGE_INTEREST_TOTAL", Fmt.dollars(this.MORTGAGE_INTEREST_TOTAL), Calculation.replace("MORTGAGE_MONTHS_ELAPSED", Fmt.number(this.MORTGAGE_MONTHS_ELAPSED), Calculation.replace("MORTGAGE_MONTHS_LEFT", Fmt.number(this.MORTGAGE_MONTHS_LEFT), Calculation.replace("MORTGAGE_MONTH_LENGTH", Fmt.number(this.MORTGAGE_MONTH_LENGTH), Calculation.replace("MONTHLY_RATE", Fmt.percent(this.MONTHLY_RATE), Calculation.replace("RATE", Fmt.percent(this.RATE / 100.0, 2), Calculation.replace("INCREASE_BY_AMT", Fmt.dollars(this.INCREASE_BY_AMT), Calculation.replace("MORTGAGE_AMT", Fmt.dollars(this.MORTGAGE_AMT), Calculation.replace("MORTGAGE_YRS_LENGTH", Fmt.number(this.MORTGAGE_YRS_LENGTH), Calculation.replace("MORTGAGE_YRS_LEFT", Fmt.number(this.MORTGAGE_YRS_LEFT), s))))))))))))))))));
    }
    
    public String getAmountLabel() {
        return String.valueOf(super.sScaleLabel1) + super.sCurrency;
    }
    
    public String[] getCatagories() {
        final int n = (int)Math.round(this.MORTGAGE_YRS_LENGTH);
        final String[] array = new String[n];
        for (int i = 1; i <= n; ++i) {
            array[i - 1] = new Integer(i).toString();
        }
        return array;
    }
}
