import KJEgui.Fmt;
import KJEcalculation.CalculationException;
import KJEcalculation.Calculation;

// 
// Decompiled by Procyon v0.5.30
// 

public class MortgageRentvsBuyCalc extends Calculation
{
    public double MAINTENANCE;
    public boolean bUSE_FLEX;
    public boolean CAMORTGAGE;
    public int nPeriod;
    public double BREAKEVEN_YEARS;
    public double PRICE_OF_HOME;
    public double DOWNPAYMENT_CLOSING_CASH;
    public double PROPERTY_TAX_RATE;
    public double HOME_INSURANCE_RATE;
    public double INTEREST_RATE;
    public double LENGTH_OF_LOAN;
    public double LOAN_ORIGINATION_RATE;
    public double POINTS_PAID_NBR;
    public double PMI_RATE;
    public double OTHER_CLOSING_COSTS;
    public double HOME_INSURANCE_AMT;
    public double LOAN_ORIGINATION_AMT;
    public double PROPERTY_TAX_AMT;
    public double MAXIMUM_20_DOWN;
    public String REPORT_PERIOD;
    public double POINTS_PAID_AMT;
    public double TOTAL_CLOSING_COSTS;
    public double TOTAL_FOR_DOWNPAYMENT;
    public double LOAN_AMOUNT;
    public double DOWNPAYMENT_20;
    public double CLOSING_CLOSING_COSTS_20;
    public double HOME_INSURANCE_MONTHLY;
    public double PROPERY_TAX_MONTHLY;
    public double MONTHLY_PI;
    public double MONTHLY_PMI;
    public double MONTHLY_TOTAL_PMT;
    public double TAX_RATE;
    public double MONTHLY_RENT;
    public double INFLATION_RATE;
    public double INVESTMENT_RETURN;
    public double HOME_APPRECIATION_RATE;
    public double HOME_COMMISION_RATE;
    public double GST_TAX;
    public boolean GST_TAX_USE;
    public double TOTAL_CMHC;
    public double RATE_CMHC;
    public double LOAN_TO_VALUE;
    public double ENTERED_INTEREST_RATE;
    public String MSG_RESULT1;
    public String MSG_DESC1;
    public String MSG_RESULT2;
    public String MSG_DESC2;
    public String MSG_ERROR1;
    public float[] DS_RENT;
    public float[] DS_INVESTMENT_TOTAL;
    public float[] DS_PMI;
    public float[] DS_EQUITY;
    public float[] DS_EQUITY_AFTER_COSTS;
    public float[] DS_INTEREST;
    public float[] DS_INSURANCE;
    public float[] DS_TAXES;
    public float[] DS_PROPERTY_TAXES;
    public float[] DS_MONTHLY_TOTAL_PMT;
    public float[] DS_NET_HOME_COST;
    public float[] DS_HOME_VALUE;
    public float[] DS_HOME_EQUITY;
    double dDS_RENT;
    double dDS_INVESTMENT_TOTAL;
    double dDS_PMI;
    double dDS_EQUITY;
    double dDS_EQUITY_AFTER_COSTS;
    double dDS_INTEREST;
    double dDS_INSURANCE;
    double dDS_TAXES;
    double dDS_PROPERTY_TAXES;
    double dDS_MONTHLY_TOTAL_PMT;
    double dDS_NET_HOME_COST;
    double dDS_HOME_VALUE;
    double dDS_HOME_EQUITY;
    double dDS_MAINTENANCE;
    public String[] cats;
    
    public MortgageRentvsBuyCalc() {
        this.MAINTENANCE = 0.0;
        this.bUSE_FLEX = false;
        this.CAMORTGAGE = false;
        this.nPeriod = 0;
        this.BREAKEVEN_YEARS = 0.0;
        this.PRICE_OF_HOME = 0.0;
        this.DOWNPAYMENT_CLOSING_CASH = 0.0;
        this.PROPERTY_TAX_RATE = 0.0;
        this.HOME_INSURANCE_RATE = 0.0;
        this.INTEREST_RATE = 0.0;
        this.LENGTH_OF_LOAN = 0.0;
        this.LOAN_ORIGINATION_RATE = 0.0;
        this.POINTS_PAID_NBR = 0.0;
        this.PMI_RATE = 0.0;
        this.OTHER_CLOSING_COSTS = 0.0;
        this.HOME_INSURANCE_AMT = 0.0;
        this.LOAN_ORIGINATION_AMT = 0.0;
        this.PROPERTY_TAX_AMT = 0.0;
        this.MAXIMUM_20_DOWN = 0.0;
        this.POINTS_PAID_AMT = 0.0;
        this.TOTAL_CLOSING_COSTS = 0.0;
        this.TOTAL_FOR_DOWNPAYMENT = 0.0;
        this.LOAN_AMOUNT = 0.0;
        this.DOWNPAYMENT_20 = 0.0;
        this.CLOSING_CLOSING_COSTS_20 = 0.0;
        this.HOME_INSURANCE_MONTHLY = 0.0;
        this.PROPERY_TAX_MONTHLY = 0.0;
        this.MONTHLY_PI = 0.0;
        this.MONTHLY_PMI = 0.0;
        this.MONTHLY_TOTAL_PMT = 0.0;
        this.TAX_RATE = 0.0;
        this.MONTHLY_RENT = 0.0;
        this.INFLATION_RATE = 0.0;
        this.INVESTMENT_RETURN = 0.0;
        this.HOME_APPRECIATION_RATE = 0.0;
        this.HOME_COMMISION_RATE = 0.0;
        this.GST_TAX = 0.0;
        this.GST_TAX_USE = false;
        this.TOTAL_CMHC = 0.0;
        this.RATE_CMHC = 0.0;
        this.LOAN_TO_VALUE = 0.0;
        this.ENTERED_INTEREST_RATE = 0.0;
        this.MSG_RESULT1 = "Your home purchase breaks even after BREAKEVEN_YEARS years.";
        this.MSG_DESC1 = "If you cannot remain in your home for at least BREAKEVEN_YEARS years you should consider continuing to rent.";
        this.MSG_RESULT2 = "Your home purchase does not breakeven after LENGTH_OF_LOAN years.";
        this.MSG_DESC2 = "Your home purchase does not breakeven after LENGTH_OF_LOAN years.  You should consider continuing to rent.";
        this.MSG_ERROR1 = "You do not have enough cash on hand to cover your closing costs.";
    }
    
    protected void calculate() throws CalculationException {
        boolean b = false;
        double n = this.PRICE_OF_HOME / 2.0;
        this.LOAN_AMOUNT = this.PRICE_OF_HOME;
        int n2 = 0;
        this.DOWNPAYMENT_20 = this.PRICE_OF_HOME * 0.2;
        if (this.CAMORTGAGE) {
            this.ENTERED_INTEREST_RATE = this.INTEREST_RATE;
            this.INTEREST_RATE = (Math.pow(1.0 + this.INTEREST_RATE / 200.0, 0.16666666666666666) - 1.0) * 1200.0;
        }
        this.GST_TAX = (this.CAMORTGAGE ? this.getGST(this.GST_TAX_USE ^ true, this.PRICE_OF_HOME) : 0.0);
        while (!b) {
            this.LOAN_ORIGINATION_AMT = (this.CAMORTGAGE ? this.LOAN_ORIGINATION_AMT : (this.LOAN_AMOUNT * (this.LOAN_ORIGINATION_RATE / 100.0)));
            this.POINTS_PAID_AMT = this.LOAN_AMOUNT * (this.POINTS_PAID_NBR / 100.0);
            this.TOTAL_CLOSING_COSTS = this.GST_TAX + this.POINTS_PAID_AMT + this.LOAN_ORIGINATION_AMT + this.OTHER_CLOSING_COSTS;
            this.TOTAL_FOR_DOWNPAYMENT = this.DOWNPAYMENT_CLOSING_CASH - this.TOTAL_CLOSING_COSTS;
            if (this.TOTAL_FOR_DOWNPAYMENT > this.DOWNPAYMENT_20 && this.MAXIMUM_20_DOWN == 1.0 && !this.CAMORTGAGE) {
                this.TOTAL_FOR_DOWNPAYMENT = this.DOWNPAYMENT_20;
            }
            if (this.CAMORTGAGE) {
                this.LOAN_AMOUNT = this.PRICE_OF_HOME - this.TOTAL_FOR_DOWNPAYMENT;
                this.RATE_CMHC = this.getCMHCRate(this.LOAN_AMOUNT, this.TOTAL_FOR_DOWNPAYMENT, this.PRICE_OF_HOME, this.bUSE_FLEX);
                this.TOTAL_CMHC = this.LOAN_AMOUNT * this.RATE_CMHC;
                this.LOAN_AMOUNT += this.TOTAL_CMHC;
            }
            else {
                this.RATE_CMHC = 0.0;
                this.TOTAL_CMHC = 0.0;
            }
            final double n3 = this.LOAN_AMOUNT + this.TOTAL_FOR_DOWNPAYMENT - this.TOTAL_CMHC;
            if (Math.round(this.PRICE_OF_HOME * 1000.0) == Math.round(n3 * 1000.0)) {
                b = true;
            }
            else if (this.PRICE_OF_HOME > n3) {
                this.LOAN_AMOUNT += n;
            }
            else {
                this.LOAN_AMOUNT -= n;
            }
            n /= 2.0;
            if (++n2 > 50) {
                b = true;
            }
        }
        if (this.TOTAL_FOR_DOWNPAYMENT < 0.0) {
            throw new CalculationException(this.MSG_ERROR1);
        }
        if (this.DOWNPAYMENT_20 > this.TOTAL_FOR_DOWNPAYMENT && !this.CAMORTGAGE) {
            this.MONTHLY_PMI = this.LOAN_AMOUNT * (this.PMI_RATE / 1200.0);
        }
        else {
            this.MONTHLY_PMI = 0.0;
        }
        this.HOME_INSURANCE_AMT = this.PRICE_OF_HOME * (this.HOME_INSURANCE_RATE / 100.0);
        if (!this.CAMORTGAGE) {
            this.PROPERTY_TAX_AMT = this.PRICE_OF_HOME * (this.PROPERTY_TAX_RATE / 100.0);
        }
        this.HOME_INSURANCE_MONTHLY = this.HOME_INSURANCE_AMT / 12.0;
        this.PROPERY_TAX_MONTHLY = this.PROPERTY_TAX_AMT / 12.0;
        this.CLOSING_CLOSING_COSTS_20 = this.TOTAL_CLOSING_COSTS + this.DOWNPAYMENT_20;
        this.MONTHLY_PI = Fmt.round(Calculation.PMT(this.INTEREST_RATE / 1200.0, this.LENGTH_OF_LOAN * 12.0, this.LOAN_AMOUNT), 2);
        this.MONTHLY_TOTAL_PMT = this.PROPERY_TAX_MONTHLY + this.HOME_INSURANCE_MONTHLY + this.MONTHLY_PI + this.MONTHLY_PMI + this.MAINTENANCE;
        final int n4 = (int)Math.round(this.LENGTH_OF_LOAN);
        final int graphUnits = this.getGraphUnits(this.PRICE_OF_HOME * Math.pow(1.0 + this.INFLATION_RATE / 100.0, 5.0));
        this.DS_RENT = new float[n4];
        this.DS_INVESTMENT_TOTAL = new float[n4];
        this.DS_PMI = new float[n4];
        this.DS_EQUITY = new float[n4];
        this.DS_INTEREST = new float[n4];
        this.DS_INSURANCE = new float[n4];
        this.DS_TAXES = new float[n4];
        this.DS_PROPERTY_TAXES = new float[n4];
        this.DS_MONTHLY_TOTAL_PMT = new float[n4];
        this.DS_NET_HOME_COST = new float[n4];
        this.DS_HOME_VALUE = new float[n4];
        this.DS_HOME_EQUITY = new float[n4];
        this.DS_EQUITY_AFTER_COSTS = new float[n4];
        this.cats = new String[n4];
        final StringBuffer sb = new StringBuffer();
        String s;
        if (this.REPORT_PERIOD.equals("0")) {
            s = this.sReportCol("<BR><B>Nbr</B>", 1);
        }
        else {
            s = this.sReportCol("<BR><B>Year</B>", 7);
        }
        if (super.bWithSchedule) {
            this.addRepeat("<TD>" + super._sCell + s + super._sCellFormat + this.sReportCol("<B>House<BR>Payment (PITI)</B>", 2) + (this.CAMORTGAGE ? "" : (String.valueOf(super._sCellFormat) + this.sReportCol("<B>Payment<BR>After Tax Savings</B>", 3))) + super._sCellFormat + this.sReportCol("<B>Rent<BR>Payment</B>", 4) + super._sCellFormat + this.sReportCol("<B>Value of<BR>Investment</B>", 5) + super._sCellFormat + this.sReportCol("<B>Home<BR>Equity</B>", 6) + super._sCellFooter + "</TD>");
        }
        this.dDS_RENT = this.MONTHLY_RENT;
        this.dDS_INVESTMENT_TOTAL = this.TOTAL_FOR_DOWNPAYMENT + this.TOTAL_CLOSING_COSTS;
        this.dDS_INSURANCE = this.HOME_INSURANCE_MONTHLY;
        this.dDS_PROPERTY_TAXES = this.PROPERY_TAX_MONTHLY;
        this.dDS_HOME_EQUITY = this.PRICE_OF_HOME - this.LOAN_AMOUNT;
        this.dDS_HOME_VALUE = this.PRICE_OF_HOME;
        this.dDS_MAINTENANCE = this.MAINTENANCE;
        final double ror_MONTH = Calculation.ROR_MONTH(this.INVESTMENT_RETURN / 100.0);
        double loan_AMOUNT = this.LOAN_AMOUNT;
        this.dDS_EQUITY_AFTER_COSTS = this.dDS_HOME_VALUE - loan_AMOUNT - this.dDS_HOME_VALUE * this.HOME_COMMISION_RATE / 100.0;
        double n5 = this.dDS_HOME_VALUE * (this.HOME_APPRECIATION_RATE / 1200.0);
        for (int i = 1; i <= n4 * 12; ++i) {
            final int n6 = (i - 1) / 12;
            this.cats[n6] = String.valueOf(n6 + 1);
            if (n6 > 0 && (i - 1) % 12 == 0) {
                this.dDS_RENT *= 1.0 + this.INFLATION_RATE / 100.0;
                this.dDS_INSURANCE = this.dDS_HOME_VALUE * (this.HOME_INSURANCE_RATE / 1200.0);
                this.dDS_MAINTENANCE *= 1.0 + this.INFLATION_RATE / 100.0;
                if (this.CAMORTGAGE) {
                    this.dDS_PROPERTY_TAXES *= 1.0 + this.INFLATION_RATE / 100.0;
                }
                else {
                    this.dDS_PROPERTY_TAXES = this.dDS_HOME_VALUE * (this.PROPERTY_TAX_RATE / 1200.0);
                }
                n5 = this.dDS_HOME_VALUE * (this.HOME_APPRECIATION_RATE / 1200.0);
            }
            this.dDS_INTEREST = Fmt.round(this.INTEREST_RATE / 1200.0 * loan_AMOUNT, 2);
            this.dDS_EQUITY = this.MONTHLY_PI - this.dDS_INTEREST;
            loan_AMOUNT -= this.dDS_EQUITY;
            if (loan_AMOUNT < 0.0) {
                loan_AMOUNT = 0.0;
                this.dDS_EQUITY = this.MONTHLY_PI - loan_AMOUNT - this.dDS_INTEREST;
            }
            if (n4 * 12 == i) {
                if (loan_AMOUNT > 0.005) {
                    loan_AMOUNT = 0.0;
                    this.dDS_EQUITY = this.MONTHLY_PI + loan_AMOUNT - this.dDS_INTEREST;
                }
                else {
                    loan_AMOUNT = 0.0;
                }
            }
            if (this.DOWNPAYMENT_20 > this.PRICE_OF_HOME - loan_AMOUNT) {
                this.dDS_PMI = this.LOAN_AMOUNT * (this.PMI_RATE / 1200.0);
            }
            else {
                this.dDS_PMI = 0.0;
            }
            this.dDS_MONTHLY_TOTAL_PMT = this.dDS_INSURANCE + this.dDS_PROPERTY_TAXES + this.dDS_INTEREST + this.dDS_EQUITY + this.dDS_PMI + this.dDS_MAINTENANCE;
            this.dDS_TAXES = (this.dDS_INTEREST + this.dDS_PROPERTY_TAXES) * (this.TAX_RATE / 100.0);
            this.dDS_INVESTMENT_TOTAL = this.dDS_INVESTMENT_TOTAL * (1.0 + ror_MONTH) + (this.dDS_MONTHLY_TOTAL_PMT - this.dDS_RENT - this.dDS_TAXES);
            this.dDS_NET_HOME_COST = this.dDS_MONTHLY_TOTAL_PMT - this.dDS_TAXES;
            this.dDS_HOME_VALUE += n5;
            this.dDS_HOME_EQUITY = this.dDS_HOME_VALUE - loan_AMOUNT;
            this.dDS_EQUITY_AFTER_COSTS = this.dDS_HOME_EQUITY - this.dDS_HOME_VALUE * this.HOME_COMMISION_RATE / 100.0;
            if (this.BREAKEVEN_YEARS == 0.0 && this.dDS_EQUITY_AFTER_COSTS > this.dDS_INVESTMENT_TOTAL) {
                this.BREAKEVEN_YEARS = i / 12.0;
            }
            this.DS_RENT[n6] = (float)this.dDS_RENT;
            this.DS_PMI[n6] = (float)this.dDS_PMI;
            this.DS_EQUITY[n6] = (float)this.dDS_EQUITY;
            this.DS_INTEREST[n6] = (float)this.dDS_INTEREST;
            this.DS_INSURANCE[n6] = (float)this.dDS_INSURANCE;
            this.DS_TAXES[n6] = (float)this.dDS_TAXES;
            this.DS_PROPERTY_TAXES[n6] = (float)this.dDS_PROPERTY_TAXES;
            this.DS_MONTHLY_TOTAL_PMT[n6] = (float)this.dDS_MONTHLY_TOTAL_PMT;
            this.DS_NET_HOME_COST[n6] = (float)this.dDS_NET_HOME_COST;
            this.DS_HOME_VALUE[n6] = (float)(this.dDS_HOME_VALUE / graphUnits);
            this.DS_HOME_EQUITY[n6] = (float)(this.dDS_HOME_EQUITY / graphUnits);
            this.DS_EQUITY_AFTER_COSTS[n6] = (float)(this.dDS_EQUITY_AFTER_COSTS / graphUnits);
            this.DS_INVESTMENT_TOTAL[n6] = (float)(this.dDS_INVESTMENT_TOTAL / graphUnits);
            if (this.REPORT_PERIOD.equals("0")) {
                this.nPeriod = i;
            }
            else {
                this.nPeriod = n6 + 1;
            }
            if ((i % 12 == 0 || this.REPORT_PERIOD.equals("0")) && super.bWithSchedule) {
                this.addRepeat("<TD>" + super._sCell + this.nPeriod + super._sCellFormat + Fmt.dollars(this.dDS_MONTHLY_TOTAL_PMT, 2) + (this.CAMORTGAGE ? "" : (String.valueOf(super._sCellFormat) + Fmt.dollars(this.dDS_NET_HOME_COST, 2))) + super._sCellFormat + Fmt.dollars(this.dDS_RENT, 2) + super._sCellFormat + Fmt.dollars(this.dDS_INVESTMENT_TOTAL) + super._sCellFormat + Fmt.dollars(this.dDS_EQUITY_AFTER_COSTS) + super._sCellFooter + "</TD>");
            }
        }
    }
    
    protected void clearCalculated() {
        this.HOME_INSURANCE_AMT = 0.0;
        this.POINTS_PAID_AMT = 0.0;
        this.TOTAL_CLOSING_COSTS = 0.0;
        this.TOTAL_FOR_DOWNPAYMENT = 0.0;
        this.LOAN_AMOUNT = 0.0;
        this.DOWNPAYMENT_20 = 0.0;
        this.CLOSING_CLOSING_COSTS_20 = 0.0;
        this.HOME_INSURANCE_MONTHLY = 0.0;
        this.PROPERY_TAX_MONTHLY = 0.0;
        this.MONTHLY_PI = 0.0;
        this.MONTHLY_PMI = 0.0;
        this.MONTHLY_TOTAL_PMT = 0.0;
        this.BREAKEVEN_YEARS = 0.0;
        this.ENTERED_INTEREST_RATE = 0.0;
    }
    
    public void clearInputed() {
        this.PRICE_OF_HOME = 0.0;
        this.MAXIMUM_20_DOWN = 0.0;
        this.DOWNPAYMENT_CLOSING_CASH = 0.0;
        this.PROPERTY_TAX_RATE = 0.0;
        this.HOME_INSURANCE_RATE = 0.0;
        this.INTEREST_RATE = 0.0;
        this.LENGTH_OF_LOAN = 0.0;
        this.LOAN_ORIGINATION_RATE = 0.0;
        this.POINTS_PAID_NBR = 0.0;
        this.PMI_RATE = 0.0;
        this.OTHER_CLOSING_COSTS = 0.0;
        this.TAX_RATE = 0.0;
        this.MONTHLY_RENT = 0.0;
        this.INFLATION_RATE = 0.0;
        this.INVESTMENT_RETURN = 0.0;
        this.HOME_APPRECIATION_RATE = 0.0;
        this.HOME_COMMISION_RATE = 0.0;
    }
    
    protected String formatReport(final String s) {
        String s2;
        if (this.BREAKEVEN_YEARS > 0.0 || this.BREAKEVEN_YEARS >= 30.0) {
            s2 = Calculation.replace("RESULT_MESSAGE_ADVICE", this.MSG_DESC1, Calculation.replace("RESULT_MESSAGE_MAIN", this.MSG_RESULT1, s));
        }
        else {
            s2 = Calculation.replace("RESULT_MESSAGE_ADVICE", this.MSG_DESC2, Calculation.replace("RESULT_MESSAGE_MAIN", this.MSG_RESULT2, s));
        }
        return Calculation.replace("BREAKEVEN_YEARS", Fmt.number(this.BREAKEVEN_YEARS, 1), Calculation.replace("HOME_APPRECIATION_RATE", Fmt.percent(this.HOME_APPRECIATION_RATE / 100.0, 2), Calculation.replace("HOME_COMMISION_RATE", Fmt.percent(this.HOME_COMMISION_RATE / 100.0, 2), Calculation.replace("INVESTMENT_RETURN", Fmt.percent(this.INVESTMENT_RETURN / 100.0, 2), Calculation.replace("INFLATION_RATE", Fmt.percent(this.INFLATION_RATE / 100.0, 2), Calculation.replace("MONTHLY_RENT", Fmt.dollars(this.MONTHLY_RENT), Calculation.replace("MAINTENANCE", Fmt.dollars(this.MAINTENANCE, 2), Calculation.replace("MONTHLY_TOTAL_PMT", Fmt.dollars(this.MONTHLY_TOTAL_PMT, 2), Calculation.replace("MONTHLY_TOTAL_PMT", Fmt.dollars(this.MONTHLY_TOTAL_PMT, 2), Calculation.replace("GST_TAX", Fmt.dollars(this.GST_TAX), Calculation.replace("RATE_CMHC", Fmt.percent(this.RATE_CMHC, 2), Calculation.replace("TOTAL_CMHC", Fmt.dollars(this.TOTAL_CMHC), Calculation.replace("MONTHLY_PMI", Fmt.dollars(this.MONTHLY_PMI, 2), Calculation.replace("MONTHLY_PI", Fmt.dollars(this.MONTHLY_PI, 2), Calculation.replace("PROPERY_TAX_MONTHLY", Fmt.dollars(this.PROPERY_TAX_MONTHLY, 2), Calculation.replace("HOME_INSURANCE_MONTHLY", Fmt.dollars(this.HOME_INSURANCE_MONTHLY, 2), Calculation.replace("CLOSING_CLOSING_COSTS_20", Fmt.dollars(this.CLOSING_CLOSING_COSTS_20), Calculation.replace("DOWNPAYMENT_20", Fmt.dollars(this.DOWNPAYMENT_20), Calculation.replace("LOAN_AMOUNT", Fmt.dollars(this.LOAN_AMOUNT), Calculation.replace("TOTAL_FOR_DOWNPAYMENT", Fmt.dollars(this.TOTAL_FOR_DOWNPAYMENT), Calculation.replace("TOTAL_CLOSING_COSTS", Fmt.dollars(this.TOTAL_CLOSING_COSTS + this.TOTAL_CMHC, 2), Calculation.replace("POINTS_PAID_AMT", Fmt.dollars(this.POINTS_PAID_AMT, 2), Calculation.replace("PROPERTY_TAX_AMT", Fmt.dollars(this.PROPERTY_TAX_AMT, 2), Calculation.replace("LOAN_ORIGINATION_AMT", Fmt.dollars(this.LOAN_ORIGINATION_AMT, 0), Calculation.replace("HOME_INSURANCE_AMT", Fmt.dollars(this.HOME_INSURANCE_AMT, 2), Calculation.replace("OTHER_CLOSING_COSTS", Fmt.dollars(this.OTHER_CLOSING_COSTS), Calculation.replace("PMI_RATE", Fmt.percent(this.PMI_RATE / 100.0, 3), Calculation.replace("POINTS_PAID_NBR", Fmt.number(this.POINTS_PAID_NBR), Calculation.replace("LOAN_ORIGINATION_RATE", Fmt.percent(this.LOAN_ORIGINATION_RATE / 100.0, 3), Calculation.replace("LENGTH_OF_LOAN", Fmt.number(this.LENGTH_OF_LOAN), Calculation.replace("INTEREST_RATE", Fmt.percent((this.CAMORTGAGE ? this.ENTERED_INTEREST_RATE : this.INTEREST_RATE) / 100.0, 3), Calculation.replace("HOME_INSURANCE_RATE", Fmt.percent(this.HOME_INSURANCE_RATE / 100.0, 3), Calculation.replace("TAX_RATE", Fmt.percent(this.TAX_RATE / 100.0, 2), Calculation.replace("PROPERTY_TAX_RATE", Fmt.percent(this.PROPERTY_TAX_RATE / 100.0, 3), Calculation.replace("DOWNPAYMENT_CLOSING_CASH", Fmt.dollars(this.DOWNPAYMENT_CLOSING_CASH), Calculation.replace("MAXIMUM_20_DOWN", Fmt.yesno(this.MAXIMUM_20_DOWN), Calculation.replace("PRICE_OF_HOME", Fmt.dollars(this.PRICE_OF_HOME), s2)))))))))))))))))))))))))))))))))))));
    }
    
    public String getAmountLabel() {
        return String.valueOf(super._sGraphUnits) + super.sCurrency;
    }
    
    public double getCMHCRate(final double n, final double n2, final double n3, final boolean b) {
        double n4 = 0.0;
        if (n3 * 0.25 > n2) {
            final double round = Fmt.round(n / n3, 6);
            if (round > 0.9) {
                n4 = (b ? 2.9 : 2.75) / 100.0;
            }
            else if (round > 0.85) {
                n4 = 0.02;
            }
            else if (round > 0.8) {
                n4 = 0.0175;
            }
            else if (round > 0.75) {
                n4 = 0.01;
            }
            else if (round > 0.65) {
                n4 = 0.006500000000000001;
            }
            else {
                n4 = 0.0;
            }
        }
        return n4;
    }
    
    public String[] getCatagories() {
        return this.cats;
    }
    
    public double getGST(final boolean b, final double n) {
        if (b) {
            return 0.0;
        }
        double n2 = 0.0;
        if (n <= 350000.0) {
            n2 = n * 0.07 * 0.36;
            if (n2 > 8750.0) {
                n2 = 8750.0;
            }
        }
        else if (n <= 450000.0) {
            n2 = 8750.0 * (450000.0 - n) / 100000.0;
        }
        return Fmt.round(n * 0.07 - n2, 2);
    }
}
