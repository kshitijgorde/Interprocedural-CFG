import KJEcalculation.CalculationException;
import KJEgui.Fmt;
import KJEcalculation.Calculation;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShouldIRefiCalculation extends Calculation
{
    public double APPRAISED_HOME_VALUE;
    public double ORIGINAL_LOAN_AMOUNT;
    public double ORIGINAL_RATE;
    public double ORIGINAL_TERM_IN_YEARS;
    public double NUMBER_OF_PAYMENTS_MADE;
    public double CURRENT_PAYMENT;
    public double CURRENT_BALANCE;
    public double NEW_BALANCE;
    public double ANNUAL_PROPERTY_TAXES;
    public double ANNUAL_HOME_INSURANCE;
    public double MONTHLY_PMI;
    public double CURRENT_PITI;
    public double NEW_RATE;
    public double NEW_TERM;
    public double CLOSING_COSTS;
    public double NEW_PAYMENT;
    public double NEWMONTHLY_PMI;
    public double PMI_PERCENT;
    public double NEW_PITI;
    public double REMAINING_INTEREST_ON_CURRENT_MORTGAGE;
    public double INTEREST_ON_NEW_MORTGAGE;
    public double INTEREST_SAVINGS;
    public double MONTHLY_SAVINGS;
    public double YOU_WILL_BREAK_EVEN_IN;
    public double NEW_LOAN_TO_VALUE;
    public String BREAK_EVEN_IN_MSG;
    public String INTEREST_SAVINGS_MSG;
    public String MSG_INCREASE1;
    public String MSG_DECREASE1;
    public String MSG_INCREASE2;
    public String MSG_DECREASE2;
    public String MSG_INCREASE3;
    public String MSG_DECREASE3;
    public String MSG_TITLE;
    public String MSG_TITLE_INCREASE;
    public String MSG_TITLE_DECREASE;
    public String MSG_BREAK_EVEN1;
    public String MSG_BREAK_EVEN2;
    public float[] DS_PAYMENTS;
    public String _sAmountLabel;
    public String[] cats;
    public String[] catlabel;
    
    public ShouldIRefiCalculation() {
        this.APPRAISED_HOME_VALUE = 0.0;
        this.ORIGINAL_LOAN_AMOUNT = 0.0;
        this.ORIGINAL_RATE = 0.0;
        this.ORIGINAL_TERM_IN_YEARS = 0.0;
        this.NUMBER_OF_PAYMENTS_MADE = 0.0;
        this.CURRENT_PAYMENT = 0.0;
        this.CURRENT_BALANCE = 0.0;
        this.NEW_BALANCE = 0.0;
        this.ANNUAL_PROPERTY_TAXES = 0.0;
        this.ANNUAL_HOME_INSURANCE = 0.0;
        this.MONTHLY_PMI = 0.0;
        this.CURRENT_PITI = 0.0;
        this.NEW_RATE = 0.0;
        this.NEW_TERM = 0.0;
        this.CLOSING_COSTS = 0.0;
        this.NEW_PAYMENT = 0.0;
        this.NEWMONTHLY_PMI = 0.0;
        this.PMI_PERCENT = 0.2;
        this.NEW_PITI = 0.0;
        this.REMAINING_INTEREST_ON_CURRENT_MORTGAGE = 0.0;
        this.INTEREST_ON_NEW_MORTGAGE = 0.0;
        this.INTEREST_SAVINGS = 0.0;
        this.MONTHLY_SAVINGS = 0.0;
        this.YOU_WILL_BREAK_EVEN_IN = 0.0;
        this.NEW_LOAN_TO_VALUE = 0.0;
        this.BREAK_EVEN_IN_MSG = "";
        this.INTEREST_SAVINGS_MSG = "";
        this.MSG_INCREASE1 = "increase INTEREST_AMT_SAVINGS";
        this.MSG_DECREASE1 = "decrease INTEREST_AMT_SAVINGS";
        this.MSG_INCREASE2 = "save INTEREST_AMT_SAVINGS in interest";
        this.MSG_DECREASE2 = "pay INTEREST_AMT_SAVINGS more in interest";
        this.MSG_INCREASE3 = "increase MONTHLY_AMT_SAVINGS";
        this.MSG_DECREASE3 = "decrease MONTHLY_AMT_SAVINGS";
        this.MSG_TITLE = "";
        this.MSG_TITLE_INCREASE = "You could save INTEREST_AMT_SAVINGS in interest";
        this.MSG_TITLE_DECREASE = "You could pay INTEREST_AMT_SAVINGS more in interest.";
        this.MSG_BREAK_EVEN1 = "With a monthly payment increase, there is no payment breakeven point.";
        this.MSG_BREAK_EVEN2 = "Refinancing will breakeven in YOU_WILL_BREAK_EVEN_IN months.";
        this.DS_PAYMENTS = new float[4];
        this._sAmountLabel = "";
        this.cats = new String[4];
        this.catlabel = new String[] { "Current PI", "New PI", "Current PITI", "New PITI" };
    }
    
    protected void calculate() throws CalculationException {
        this.CURRENT_PAYMENT = Calculation.PMT(this.ORIGINAL_RATE / 1200.0, this.ORIGINAL_TERM_IN_YEARS * 12.0, this.ORIGINAL_LOAN_AMOUNT);
        this.CURRENT_PITI = this.CURRENT_PAYMENT + this.ANNUAL_PROPERTY_TAXES / 12.0 + this.ANNUAL_HOME_INSURANCE / 12.0 + this.MONTHLY_PMI;
        final StringBuffer sb = new StringBuffer();
        double original_LOAN_AMOUNT = this.ORIGINAL_LOAN_AMOUNT;
        double n = 0.0;
        double n2 = 0.0;
        double n3 = 0.0;
        double n4 = 0.0;
        double n5 = 0.0;
        double n6 = 0.0;
        final boolean b = true;
        int n7 = 1;
        if (super.bWithSchedule) {
            this.addRepeat("<TD COLSPAN=6>" + super._sCell + this.sReportCol("<B><CENTER>Original Loan Schedule</CENTER></B>", 7) + super._sCellFooter + "</TD>" + super._sRowFooter + super._sTopRow + "<TD>" + super._sCell + this.sReportCol("<B>Year</B>", 1) + super._sCellFormat + this.sReportCol("<B>PI Payments</B>", 2) + super._sCellFormat + this.sReportCol("<B>Interest</B>", 3) + super._sCellFormat + this.sReportCol("<B>Equity</B>", 4) + super._sCellFormat + this.sReportCol("<B>Balance</B>", 5) + super._sCellFormat + this.sReportCol("<B>Interest Paid</B>", 6) + super._sCellFooter + "</TD>");
        }
        final double n8 = this.APPRAISED_HOME_VALUE * (1.0 - this.PMI_PERCENT);
        for (int n9 = 1; n9 <= this.ORIGINAL_TERM_IN_YEARS * 12.0; ++n9) {
            final double n10 = original_LOAN_AMOUNT * (this.ORIGINAL_RATE / 1200.0);
            final double n11 = this.CURRENT_PAYMENT - n10;
            original_LOAN_AMOUNT -= n11;
            n += n10;
            n2 += n11;
            n3 += n10;
            n4 += n11;
            n5 += n10;
            n6 += this.CURRENT_PAYMENT;
            if (n9 > this.NUMBER_OF_PAYMENTS_MADE) {
                this.REMAINING_INTEREST_ON_CURRENT_MORTGAGE += n10;
            }
            if (n9 == this.NUMBER_OF_PAYMENTS_MADE) {
                this.CURRENT_BALANCE = original_LOAN_AMOUNT;
            }
            if (n9 % 12 == 0) {
                if (super.bWithSchedule && b) {
                    this.addRepeat("<TD>" + super._sCell + n7 + super._sCellFormat + Fmt.dollars(n6, 2) + super._sCellFormat + Fmt.dollars(n3, 2) + super._sCellFormat + Fmt.dollars(n2, 2) + super._sCellFormat + Fmt.dollars(original_LOAN_AMOUNT, 2) + super._sCellFormat + Fmt.dollars(n, 2) + super._sCellFooter + "</TD>");
                }
                n2 = 0.0;
                n3 = 0.0;
                n6 = 0.0;
                ++n7;
            }
        }
        this.NEW_BALANCE = this.CURRENT_BALANCE;
        this.NEW_LOAN_TO_VALUE = this.NEW_BALANCE / this.APPRAISED_HOME_VALUE;
        this.NEWMONTHLY_PMI = 0.0;
        if (this.APPRAISED_HOME_VALUE * (1.0 - this.PMI_PERCENT) < this.NEW_BALANCE) {
            this.NEWMONTHLY_PMI = this.MONTHLY_PMI;
        }
        this.NEW_PAYMENT = Calculation.PMT(this.NEW_RATE / 1200.0, this.NEW_TERM * 12.0, this.NEW_BALANCE);
        this.NEW_PITI = this.NEW_PAYMENT + this.ANNUAL_PROPERTY_TAXES / 12.0 + this.ANNUAL_HOME_INSURANCE / 12.0 + this.NEWMONTHLY_PMI;
        double current_BALANCE = this.CURRENT_BALANCE;
        double n12 = 0.0;
        double n13 = 0.0;
        double n14 = 0.0;
        double n15 = 0.0;
        double interest_ON_NEW_MORTGAGE = 0.0;
        int n16 = 1;
        if (super.bWithSchedule) {
            if (this.ORIGINAL_TERM_IN_YEARS % 2.0 == 0.0) {
                this.addRepeat("<TD COLSPAN=6>" + super._sCell + "&nbsp;" + super._sCellFooter + "</TD>");
            }
            this.addRepeat("<TD COLSPAN=6>" + super._sCell + "<HR SIZE=1>" + super._sCellFooter + "</TD>" + super._sRowFooter + super._sTopRow + "<TD COLSPAN=6>" + super._sCell + this.sReportCol("<B><CENTER>New Loan Schedule</CENTER></B>", 8) + super._sCellFooter + "</TD>" + super._sRowFooter + super._sTopRow + "<TD>" + super._sCell + this.sReportCol("<B>Year</B>", 1) + super._sCellFormat + this.sReportCol("<B>Payments</B>", 2) + super._sCellFormat + this.sReportCol("<B>Interest</B>", 3) + super._sCellFormat + this.sReportCol("<B>Equity</B>", 4) + super._sCellFormat + this.sReportCol("<B>Balance</B>", 5) + super._sCellFormat + this.sReportCol("<B>Interest Paid</B>", 6) + super._sCellFooter + "</TD>");
        }
        for (int n17 = 1; n17 <= this.NEW_TERM * 12.0; ++n17) {
            final double n18 = current_BALANCE * (this.NEW_RATE / 1200.0);
            final double n19 = this.NEW_PAYMENT - n18;
            current_BALANCE -= n19;
            n12 += n18;
            n13 += n19;
            n14 += n18;
            n15 += n19;
            interest_ON_NEW_MORTGAGE += n18;
            if (n17 % 12 == 0) {
                if (super.bWithSchedule && b) {
                    this.addRepeat("<TD>" + super._sCell + n16 + super._sCellFormat + Fmt.dollars(this.NEW_PAYMENT * 12.0, 2) + super._sCellFormat + Fmt.dollars(n14, 2) + super._sCellFormat + Fmt.dollars(n13, 2) + super._sCellFormat + Fmt.dollars(current_BALANCE, 2) + super._sCellFormat + Fmt.dollars(n12, 2) + super._sCellFooter + "</TD>");
                }
                n13 = 0.0;
                n14 = 0.0;
                ++n16;
            }
        }
        this.INTEREST_ON_NEW_MORTGAGE = interest_ON_NEW_MORTGAGE;
        this.INTEREST_SAVINGS = this.REMAINING_INTEREST_ON_CURRENT_MORTGAGE - this.INTEREST_ON_NEW_MORTGAGE;
        this.MONTHLY_SAVINGS = this.CURRENT_PITI - this.NEW_PITI;
        this.YOU_WILL_BREAK_EVEN_IN = this.CLOSING_COSTS / (this.CURRENT_PITI - this.NEW_PITI);
        if (this.INTEREST_SAVINGS >= 0.0) {
            this.INTEREST_SAVINGS_MSG = this.MSG_INCREASE2;
            this.MSG_TITLE = this.MSG_TITLE_INCREASE;
        }
        else {
            this.INTEREST_SAVINGS_MSG = this.MSG_DECREASE2;
            this.MSG_TITLE = this.MSG_TITLE_DECREASE;
        }
        this.INTEREST_SAVINGS_MSG = Calculation.replace("INTEREST_AMT_SAVINGS", Fmt.dollars((this.INTEREST_SAVINGS < 0.0) ? (this.INTEREST_SAVINGS * -1.0) : this.INTEREST_SAVINGS, 2), this.INTEREST_SAVINGS_MSG);
        this.MSG_TITLE = Calculation.replace("INTEREST_AMT_SAVINGS", Fmt.dollars((this.INTEREST_SAVINGS < 0.0) ? (this.INTEREST_SAVINGS * -1.0) : this.INTEREST_SAVINGS, 2), this.MSG_TITLE);
        this.DS_PAYMENTS[0] = (float)this.CURRENT_PAYMENT;
        this.DS_PAYMENTS[1] = (float)this.NEW_PAYMENT;
        this.DS_PAYMENTS[2] = (float)this.CURRENT_PITI;
        this.DS_PAYMENTS[3] = (float)this.NEW_PITI;
        this.cats[0] = String.valueOf(this.catlabel[0]) + " " + Fmt.dollars(this.CURRENT_PAYMENT);
        this.cats[1] = String.valueOf(this.catlabel[1]) + " " + Fmt.dollars(this.NEW_PAYMENT);
        this.cats[2] = String.valueOf(this.catlabel[2]) + " " + Fmt.dollars(this.CURRENT_PITI);
        this.cats[3] = String.valueOf(this.catlabel[3]) + " " + Fmt.dollars(this.NEW_PITI);
    }
    
    protected void clearCalculated() {
        this.CURRENT_PAYMENT = 0.0;
        this.CURRENT_BALANCE = 0.0;
        this.NEW_BALANCE = 0.0;
        this.CURRENT_PITI = 0.0;
        this.NEW_PAYMENT = 0.0;
        this.NEWMONTHLY_PMI = 0.0;
        this.NEW_PITI = 0.0;
        this.REMAINING_INTEREST_ON_CURRENT_MORTGAGE = 0.0;
        this.INTEREST_ON_NEW_MORTGAGE = 0.0;
        this.INTEREST_SAVINGS = 0.0;
        this.MONTHLY_SAVINGS = 0.0;
        this.YOU_WILL_BREAK_EVEN_IN = 0.0;
        this.NEW_LOAN_TO_VALUE = 0.0;
        this.BREAK_EVEN_IN_MSG = "";
        this.INTEREST_SAVINGS_MSG = "";
    }
    
    public void clearInputed() {
        this.APPRAISED_HOME_VALUE = 0.0;
        this.ORIGINAL_LOAN_AMOUNT = 0.0;
        this.ORIGINAL_RATE = 0.0;
        this.ORIGINAL_TERM_IN_YEARS = 0.0;
        this.NUMBER_OF_PAYMENTS_MADE = 0.0;
        this.ANNUAL_PROPERTY_TAXES = 0.0;
        this.ANNUAL_HOME_INSURANCE = 0.0;
        this.MONTHLY_PMI = 0.0;
        this.NEW_RATE = 0.0;
        this.NEW_TERM = 0.0;
        this.CLOSING_COSTS = 0.0;
    }
    
    protected String formatReport(final String s) {
        final String replace = Calculation.replace("INTEREST_SAVINGS_MSG", this.INTEREST_SAVINGS_MSG, Calculation.replace("INTEREST_ON_NEW_MORTGAGE", Fmt.dollars(this.INTEREST_ON_NEW_MORTGAGE, 2), Calculation.replace("REMAINING_INTEREST_ON_CURRENT_MORTGAGE", Fmt.dollars(this.REMAINING_INTEREST_ON_CURRENT_MORTGAGE, 2), Calculation.replace("NEW_PITI", Fmt.dollars(this.NEW_PITI, 2), Calculation.replace("NEW_PAYMENT", Fmt.dollars(this.NEW_PAYMENT, 2), Calculation.replace("CLOSING_COSTS", Fmt.dollars(this.CLOSING_COSTS, 2), Calculation.replace("NEW_TERM", Fmt.number(this.NEW_TERM), Calculation.replace("NEW_RATE", Fmt.percent(this.NEW_RATE / 100.0, 3), Calculation.replace("CURRENT_PITI", Fmt.dollars(this.CURRENT_PITI, 2), Calculation.replace("MONTHLY_PMI", Fmt.dollars(this.MONTHLY_PMI, 2), Calculation.replace("ANNUAL_HOME_INSURANCE", Fmt.dollars(this.ANNUAL_HOME_INSURANCE, 2), Calculation.replace("ANNUAL_PROPERTY_TAXES", Fmt.dollars(this.ANNUAL_PROPERTY_TAXES, 2), Calculation.replace("CURRENT_BALANCE", Fmt.dollars(this.CURRENT_BALANCE, 2), Calculation.replace("CURRENT_PAYMENT", Fmt.dollars(this.CURRENT_PAYMENT, 2), Calculation.replace("NUMBER_OF_PAYMENTS_MADE", Fmt.number(this.NUMBER_OF_PAYMENTS_MADE), Calculation.replace("ORIGINAL_TERM_IN_YEARS", Fmt.number(this.ORIGINAL_TERM_IN_YEARS), Calculation.replace("ORIGINAL_RATE", Fmt.percent(this.ORIGINAL_RATE / 100.0, 3), Calculation.replace("ORIGINAL_LOAN_AMOUNT", Fmt.dollars(this.ORIGINAL_LOAN_AMOUNT, 2), Calculation.replace("APPRAISED_HOME_VALUE", Fmt.dollars(this.APPRAISED_HOME_VALUE, 2), Calculation.replace("NEWMONTHLY_PMI", Fmt.dollars(this.NEWMONTHLY_PMI, 2), Calculation.replace("MSG_TITLE", this.MSG_TITLE, Calculation.replace("REQUIRED_LTV", Fmt.percent(1.0 - this.PMI_PERCENT), Calculation.replace("PMI_PERCENT", Fmt.percent(this.PMI_PERCENT), s)))))))))))))))))))))));
        String s2;
        if (this.INTEREST_SAVINGS < 0.0) {
            s2 = Calculation.replace("INTEREST_SAVINGS", this.MSG_INCREASE1, replace);
        }
        else {
            s2 = Calculation.replace("INTEREST_SAVINGS", this.MSG_DECREASE1, replace);
        }
        final String replace2 = Calculation.replace("INTEREST_AMT_SAVINGS", Fmt.dollars((this.INTEREST_SAVINGS < 0.0) ? (this.INTEREST_SAVINGS * -1.0) : this.INTEREST_SAVINGS, 2), s2);
        String s3;
        if (this.MONTHLY_SAVINGS < 0.0) {
            s3 = Calculation.replace("MONTHLY_SAVINGS", this.MSG_INCREASE3, replace2);
            this.BREAK_EVEN_IN_MSG = this.MSG_BREAK_EVEN1;
        }
        else {
            s3 = Calculation.replace("MONTHLY_SAVINGS", this.MSG_DECREASE3, replace2);
            this.BREAK_EVEN_IN_MSG = this.MSG_BREAK_EVEN2;
        }
        return Calculation.replace("NEW_LOAN_TO_VALUE", Fmt.percent(this.NEW_LOAN_TO_VALUE, 1), Calculation.replace("YOU_WILL_BREAK_EVEN_IN", Fmt.number(this.YOU_WILL_BREAK_EVEN_IN), Calculation.replace("BREAK_EVEN_IN_MSG", this.BREAK_EVEN_IN_MSG, Calculation.replace("MONTHLY_AMT_SAVINGS", Fmt.dollars((this.MONTHLY_SAVINGS < 0.0) ? (this.MONTHLY_SAVINGS * -1.0) : this.MONTHLY_SAVINGS, 2), s3))));
    }
    
    public String getAmountLabel() {
        return String.valueOf(super._sGraphUnits) + this._sAmountLabel;
    }
    
    public String[] getCatagories() {
        return this.cats;
    }
}
