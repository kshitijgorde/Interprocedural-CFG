import KJEcalculation.CalculationException;
import KJEgui.Fmt;
import KJEcalculation.Calculation;

// 
// Decompiled by Procyon v0.5.30
// 

public class SocialSecurityCalculation extends Calculation
{
    public int iDecimal;
    public boolean ADJUST_ONLY;
    public double SOCIAL_SECURITY_CURRENT_MAX;
    public double SOCIAL_SECURITY_AP_MAX;
    public double SOCIAL_SECURITY_MAX_RATIO;
    public int SOCIAL_EARLIEST_RETIRE_AGE;
    public int SOCIAL_NORMAL_RETIRE_AGE;
    public int SOCIAL_LATEST_RETIRE_AGE;
    public double[] EARLY_DISCOUNTS;
    public int[] EARLY_AGE_CUTOFFS;
    public int[] AGE_FULL_BENEFITS;
    public int[] LATE_AGE_CUTOFFS;
    public double[] LATE_AGE_INCREASES;
    public int CURRENT_AGE;
    public double HOUSEHOLD_INCOME;
    public double SALARY_PERCENT;
    public double SOCIAL_SECURITY_INCREASE_RATE;
    public double MARRIED;
    public int AGE_OF_RETIREMENT;
    public double SOCIAL_SECURITY_MAX;
    public int SOCIAL_AT_RETIRE_AGE;
    public double FUTURE_HOUSEHOLD_INCOME;
    public double SOCIAL_AT_RETIRE_AMT;
    public double SOCIAL_AT_RETIRE_AMT_MONTHLY;
    public double SOCIAL_FULL_BENEFIT_AMT;
    public double YEARS_UNTIL_SOCIAL_FULL_BENEFITS;
    public int SOCIAL_FULL_BENEFIT_AGE;
    public double SOCIAL_FULL_BENEFIT_PERCENT;
    public double SOCIAL_AT_RETIRE_PERCENT;
    public double SOCIAL_DELAYED_RETIRE_PERCENT;
    public double HOUSEHOLD_INCOME_AT_RETIRE;
    public String _sAmountLabel;
    public String[] cats;
    public float[] DS_SOCIAL_PAYMENTS;
    public double[] DR_SOCIAL_PAYMENTS;
    String MSG_SUMMARY_TEXT;
    String MSG_SUMMARY_TEXT2;
    String SUMMARY_TEXT;
    String SUMMARY_TEXT2;
    public double[] WAGE_CUTOFFS;
    public double[] SOCIAL_FULL_BENEFIT;
    
    public SocialSecurityCalculation() {
        this.iDecimal = 0;
        this.ADJUST_ONLY = false;
        this.SOCIAL_SECURITY_CURRENT_MAX = 106800.0;
        this.SOCIAL_SECURITY_AP_MAX = 27876.0;
        this.SOCIAL_SECURITY_MAX_RATIO = 0.261;
        this.SOCIAL_EARLIEST_RETIRE_AGE = 62;
        this.SOCIAL_NORMAL_RETIRE_AGE = 67;
        this.SOCIAL_LATEST_RETIRE_AGE = 70;
        this.EARLY_DISCOUNTS = new double[] { 0.7, 0.75, 0.8, 0.86666, 0.93333, 1.0 };
        this.EARLY_AGE_CUTOFFS = new int[] { 73, 56, 0 };
        this.AGE_FULL_BENEFITS = new int[] { 65, 66, 67 };
        this.LATE_AGE_CUTOFFS = new int[] { 68, 0 };
        this.LATE_AGE_INCREASES = new double[] { 0.075, 0.08 };
        this.CURRENT_AGE = 0;
        this.HOUSEHOLD_INCOME = 0.0;
        this.SALARY_PERCENT = 0.0;
        this.SOCIAL_SECURITY_INCREASE_RATE = 3.0;
        this.MARRIED = 0.0;
        this.AGE_OF_RETIREMENT = 0;
        this.SOCIAL_SECURITY_MAX = 0.0;
        this.SOCIAL_AT_RETIRE_AGE = 0;
        this.FUTURE_HOUSEHOLD_INCOME = 0.0;
        this.SOCIAL_AT_RETIRE_AMT = 0.0;
        this.SOCIAL_AT_RETIRE_AMT_MONTHLY = 0.0;
        this.SOCIAL_FULL_BENEFIT_AMT = 0.0;
        this.YEARS_UNTIL_SOCIAL_FULL_BENEFITS = 0.0;
        this.SOCIAL_FULL_BENEFIT_AGE = 65;
        this.SOCIAL_FULL_BENEFIT_PERCENT = 0.0;
        this.SOCIAL_AT_RETIRE_PERCENT = 0.0;
        this.SOCIAL_DELAYED_RETIRE_PERCENT = 0.0;
        this.HOUSEHOLD_INCOME_AT_RETIRE = 0.0;
        this.MSG_SUMMARY_TEXT = "Social Security may provide SOCIAL_AT_RETIRE_AMT";
        this.MSG_SUMMARY_TEXT2 = "If you start collecting your benefits at age SOCIAL_AT_RETIRE_AGE you could receive approximately SOCIAL_AT_RETIRE_AMT per year or SOCIAL_AT_RETIRE_AMT_MONTHLY per month.  This is SOCIAL_AT_RETIRE_PERCENT of your final year's income of HOUSEHOLD_INCOME_AT_RETIRE. This is only an estimate. Actual benefits depend on work history and the complete compensation rules used by Social Security.";
        this.SUMMARY_TEXT = "";
        this.SUMMARY_TEXT2 = "";
        this.WAGE_CUTOFFS = new double[] { 15000.0, 20000.0, 25000.0, 30000.0, 35000.0, 40000.0, 45000.0, 50000.0, 55000.0, 60000.0, this.SOCIAL_SECURITY_CURRENT_MAX };
        this.SOCIAL_FULL_BENEFIT = new double[] { 0.58, 0.46, 0.41, 0.38, 0.36, 0.34, 0.32, 0.3, 0.28, 0.27, this.SOCIAL_SECURITY_MAX_RATIO };
    }
    
    protected void calculate() throws CalculationException {
        final double n = this.SOCIAL_SECURITY_INCREASE_RATE / 100.0;
        if (this.AGE_OF_RETIREMENT < this.SOCIAL_EARLIEST_RETIRE_AGE) {
            this.SOCIAL_AT_RETIRE_AGE = this.SOCIAL_EARLIEST_RETIRE_AGE;
        }
        else {
            this.SOCIAL_AT_RETIRE_AGE = this.AGE_OF_RETIREMENT;
        }
        for (int i = 0; i < this.EARLY_AGE_CUTOFFS.length; ++i) {
            if (this.EARLY_AGE_CUTOFFS[i] < this.CURRENT_AGE) {
                this.SOCIAL_FULL_BENEFIT_AGE = this.AGE_FULL_BENEFITS[i];
                break;
            }
        }
        for (int j = 0; j < this.LATE_AGE_CUTOFFS.length; ++j) {
            if (this.CURRENT_AGE >= this.LATE_AGE_CUTOFFS[j]) {
                this.SOCIAL_DELAYED_RETIRE_PERCENT = this.LATE_AGE_INCREASES[j];
                break;
            }
        }
        if (!this.ADJUST_ONLY) {
            this.SOCIAL_FULL_BENEFIT_AMT = 0.0;
            this.YEARS_UNTIL_SOCIAL_FULL_BENEFITS = this.SOCIAL_FULL_BENEFIT_AGE - this.CURRENT_AGE;
            this.SOCIAL_SECURITY_MAX = Fmt.round(Calculation.FV_AMT(n, this.YEARS_UNTIL_SOCIAL_FULL_BENEFITS - 1.0, this.SOCIAL_SECURITY_CURRENT_MAX), 2);
            this.WAGE_CUTOFFS[this.WAGE_CUTOFFS.length - 1] = this.SOCIAL_SECURITY_CURRENT_MAX;
            this.FUTURE_HOUSEHOLD_INCOME = Calculation.FV_AMT(this.SALARY_PERCENT / 100.0, this.YEARS_UNTIL_SOCIAL_FULL_BENEFITS - 1.0, this.HOUSEHOLD_INCOME);
            final double future_HOUSEHOLD_INCOME = this.FUTURE_HOUSEHOLD_INCOME;
            this.SOCIAL_FULL_BENEFIT_PERCENT = this.SOCIAL_FULL_BENEFIT[this.SOCIAL_FULL_BENEFIT.length - 1] * this.SOCIAL_SECURITY_MAX / future_HOUSEHOLD_INCOME;
            int k = 0;
            while (k < this.WAGE_CUTOFFS.length) {
                if (future_HOUSEHOLD_INCOME < Calculation.FV_AMT(n, this.YEARS_UNTIL_SOCIAL_FULL_BENEFITS - 1.0, this.WAGE_CUTOFFS[k])) {
                    if (k == 0) {
                        this.SOCIAL_FULL_BENEFIT_PERCENT = this.SOCIAL_FULL_BENEFIT[k];
                        break;
                    }
                    final double fv_AMT = Calculation.FV_AMT(n, this.YEARS_UNTIL_SOCIAL_FULL_BENEFITS - 1.0, this.WAGE_CUTOFFS[k]);
                    this.SOCIAL_FULL_BENEFIT_PERCENT = this.SOCIAL_FULL_BENEFIT[k] + (this.SOCIAL_FULL_BENEFIT[k - 1] - this.SOCIAL_FULL_BENEFIT[k]) * ((fv_AMT - future_HOUSEHOLD_INCOME) / (fv_AMT - Calculation.FV_AMT(n, this.YEARS_UNTIL_SOCIAL_FULL_BENEFITS - 1.0, this.WAGE_CUTOFFS[k - 1])));
                    break;
                }
                else {
                    ++k;
                }
            }
            if (this.MARRIED > 0.0) {
                this.SOCIAL_FULL_BENEFIT_PERCENT *= 1.5;
            }
            this.SOCIAL_FULL_BENEFIT_AMT = Fmt.round(this.SOCIAL_FULL_BENEFIT_PERCENT * future_HOUSEHOLD_INCOME, 0);
            if (this.MARRIED > 0.0) {
                this.SOCIAL_FULL_BENEFIT_PERCENT = this.SOCIAL_FULL_BENEFIT_AMT / this.FUTURE_HOUSEHOLD_INCOME;
            }
        }
        final int n2 = this.SOCIAL_LATEST_RETIRE_AGE - this.SOCIAL_EARLIEST_RETIRE_AGE + 1;
        this.cats = new String[n2];
        this.DS_SOCIAL_PAYMENTS = new float[n2];
        this.DR_SOCIAL_PAYMENTS = new double[n2];
        double n3 = 0.0;
        for (int l = 0; l < n2; ++l) {
            this.cats[l] = Fmt.number(this.SOCIAL_EARLIEST_RETIRE_AGE + l);
            if (this.SOCIAL_EARLIEST_RETIRE_AGE + l <= this.SOCIAL_FULL_BENEFIT_AGE) {
                this.DR_SOCIAL_PAYMENTS[l] = Fmt.round(this.SOCIAL_FULL_BENEFIT_AMT * this.EARLY_DISCOUNTS[l + this.SOCIAL_NORMAL_RETIRE_AGE - this.SOCIAL_FULL_BENEFIT_AGE], 0);
            }
            else {
                this.DR_SOCIAL_PAYMENTS[l] = Fmt.round(this.SOCIAL_FULL_BENEFIT_AMT * (1.0 + this.SOCIAL_DELAYED_RETIRE_PERCENT * (this.SOCIAL_EARLIEST_RETIRE_AGE - this.SOCIAL_FULL_BENEFIT_AGE + l)), 0);
            }
            if (this.SOCIAL_AT_RETIRE_AGE == this.SOCIAL_EARLIEST_RETIRE_AGE + l) {
                this.HOUSEHOLD_INCOME_AT_RETIRE = Calculation.FV_AMT(this.SALARY_PERCENT / 100.0, this.SOCIAL_AT_RETIRE_AGE - this.CURRENT_AGE - 1, this.HOUSEHOLD_INCOME);
                this.SOCIAL_AT_RETIRE_PERCENT = this.DR_SOCIAL_PAYMENTS[l] / this.HOUSEHOLD_INCOME_AT_RETIRE;
                this.SOCIAL_AT_RETIRE_AMT = this.DR_SOCIAL_PAYMENTS[l];
                this.SOCIAL_AT_RETIRE_AMT_MONTHLY = this.DR_SOCIAL_PAYMENTS[l] / 12.0;
            }
            if (n3 < this.DR_SOCIAL_PAYMENTS[l]) {
                n3 = this.DR_SOCIAL_PAYMENTS[l];
            }
        }
        this.SUMMARY_TEXT = this.MSG_SUMMARY_TEXT;
        this.SUMMARY_TEXT = Calculation.replace("SOCIAL_AT_RETIRE_AMT", Fmt.dollars(this.SOCIAL_AT_RETIRE_AMT), this.SUMMARY_TEXT);
        this.SUMMARY_TEXT2 = this.MSG_SUMMARY_TEXT2;
        this.SUMMARY_TEXT2 = Calculation.replace("SOCIAL_AT_RETIRE_AGE", Fmt.number(this.SOCIAL_AT_RETIRE_AGE), this.SUMMARY_TEXT2);
        this.SUMMARY_TEXT2 = Calculation.replace("SOCIAL_AT_RETIRE_AMT_MONTHLY", Fmt.dollars(this.SOCIAL_AT_RETIRE_AMT_MONTHLY), this.SUMMARY_TEXT2);
        this.SUMMARY_TEXT2 = Calculation.replace("SOCIAL_AT_RETIRE_PERCENT", Fmt.percent(this.SOCIAL_AT_RETIRE_PERCENT, 1), this.SUMMARY_TEXT2);
        this.SUMMARY_TEXT2 = Calculation.replace("HOUSEHOLD_INCOME_AT_RETIRE", Fmt.dollars(this.HOUSEHOLD_INCOME_AT_RETIRE), this.SUMMARY_TEXT2);
        this.SUMMARY_TEXT2 = Calculation.replace("SOCIAL_AT_RETIRE_AMT", Fmt.dollars(this.SOCIAL_AT_RETIRE_AMT), this.SUMMARY_TEXT2);
        int n4 = 1;
        int n5 = 0;
        if (super.bWithSchedule) {
            this.addRepeat(String.valueOf(this.getTHOpen(n4++)) + this.sReportCol("Age<BR>Benefits Begin", 1) + this.getTHCellFormat(n4++) + this.sReportCol("Amount<BR>per Month", 2) + this.getTHCellFormat(n4++) + this.sReportCol("Amount<BR>per Year", 3) + this.getTHClose());
        }
        final int graphUnits = this.getGraphUnits(n3 / 12.0);
        for (int n6 = 1; n6 <= n2; ++n6) {
            final int n7 = n6 - 1;
            this.DS_SOCIAL_PAYMENTS[n7] = (float)(this.DR_SOCIAL_PAYMENTS[n7] / 12.0 / graphUnits);
            if (super.bWithSchedule) {
                int n8 = 1;
                ++n5;
                this.addRepeat(String.valueOf(this.getTDOpen(n5, n8++)) + Fmt.number(n7 + this.SOCIAL_EARLIEST_RETIRE_AGE, 0) + this.getCellFormat(n5, n8++) + Fmt.dollars(this.DR_SOCIAL_PAYMENTS[n7] / 12.0, this.iDecimal) + this.getCellFormat(n5, n8++) + Fmt.dollars(this.DR_SOCIAL_PAYMENTS[n7], this.iDecimal) + this.getTDClose());
            }
        }
    }
    
    protected void clearCalculated() {
        this.FUTURE_HOUSEHOLD_INCOME = 0.0;
        this.SOCIAL_SECURITY_MAX = 0.0;
        this.SOCIAL_AT_RETIRE_AMT = 0.0;
        this.SOCIAL_AT_RETIRE_AGE = 0;
        this.SOCIAL_AT_RETIRE_AMT_MONTHLY = 0.0;
        this.SOCIAL_AT_RETIRE_PERCENT = 0.0;
        this.SOCIAL_FULL_BENEFIT_AGE = 65;
        this.SOCIAL_FULL_BENEFIT_PERCENT = 0.0;
        this.YEARS_UNTIL_SOCIAL_FULL_BENEFITS = 0.0;
        this.SOCIAL_DELAYED_RETIRE_PERCENT = 0.0;
        this.HOUSEHOLD_INCOME_AT_RETIRE = 0.0;
    }
    
    public void clearInputed() {
        this.CURRENT_AGE = 0;
        this.HOUSEHOLD_INCOME = 0.0;
        this.SALARY_PERCENT = 0.0;
        this.SOCIAL_SECURITY_INCREASE_RATE = 0.0;
        this.MARRIED = 0.0;
        this.AGE_OF_RETIREMENT = 0;
    }
    
    protected String formatReport(final String s) {
        return Calculation.replace("SOCIAL_DELAYED_RETIRE_PERCENT", Fmt.percent(this.SOCIAL_DELAYED_RETIRE_PERCENT, 2), Calculation.replace("SOCIAL_LATEST_RETIRE_AGE", Fmt.number(this.SOCIAL_LATEST_RETIRE_AGE), Calculation.replace("SOCIAL_EARLIEST_RETIRE_AGE", Fmt.number(this.SOCIAL_EARLIEST_RETIRE_AGE), Calculation.replace("INFLATION_RATE", Fmt.percent(this.SOCIAL_SECURITY_INCREASE_RATE / 100.0, 2), Calculation.replace("SOCIAL_SECURITY_INCREASE_RATE", Fmt.percent(this.SOCIAL_SECURITY_INCREASE_RATE / 100.0, 2), Calculation.replace("SOCIAL_SECURITY_CURRENT_MAX", Fmt.dollars(this.SOCIAL_SECURITY_CURRENT_MAX), Calculation.replace("AGE_OF_RETIREMENT", Fmt.number(this.AGE_OF_RETIREMENT), Calculation.replace("MARRIED", Fmt.yesno(this.MARRIED), Calculation.replace("SALARY_PERCENT", Fmt.percent(this.SALARY_PERCENT / 100.0, 2), Calculation.replace("HOUSEHOLD_INCOME", Fmt.dollars(this.HOUSEHOLD_INCOME), Calculation.replace("CURRENT_AGE", Fmt.number(this.CURRENT_AGE), Calculation.replace("YEARS_UNTIL_SOCIAL_FULL_BENEFITS", Fmt.number(this.YEARS_UNTIL_SOCIAL_FULL_BENEFITS), Calculation.replace("SOCIAL_FULL_BENEFIT_PERCENT", Fmt.percent(this.SOCIAL_FULL_BENEFIT_PERCENT, 2), Calculation.replace("SOCIAL_FULL_BENEFIT_AGE", Fmt.number(this.SOCIAL_FULL_BENEFIT_AGE), Calculation.replace("SOCIAL_FULL_BENEFIT_AMT", Fmt.dollars(this.SOCIAL_FULL_BENEFIT_AMT), Calculation.replace("SOCIAL_AT_RETIRE_PERCENT", Fmt.percent(this.SOCIAL_AT_RETIRE_PERCENT, 2), Calculation.replace("SOCIAL_AT_RETIRE_AMT", Fmt.dollars(this.SOCIAL_AT_RETIRE_AMT), Calculation.replace("SOCIAL_AT_RETIRE_AMT_MONTHLY", Fmt.dollars(this.SOCIAL_AT_RETIRE_AMT_MONTHLY), Calculation.replace("SOCIAL_AT_RETIRE_AGE", Fmt.number(this.SOCIAL_AT_RETIRE_AGE), Calculation.replace("SOCIAL_SECURITY_MAX", Fmt.dollars(this.SOCIAL_SECURITY_MAX), Calculation.replace("HOUSEHOLD_INCOME_AT_RETIRE", Fmt.dollars(this.HOUSEHOLD_INCOME_AT_RETIRE), Calculation.replace("FUTURE_HOUSEHOLD_INCOME", Fmt.dollars(this.FUTURE_HOUSEHOLD_INCOME), s))))))))))))))))))))));
    }
    
    public String getAmountLabel() {
        return String.valueOf(super._sGraphUnits) + this._sAmountLabel;
    }
    
    public String[] getCatagories() {
        return this.cats;
    }
    
    public String getCellFormat(final int n, final int n2) {
        return String.valueOf(this.getTDClose()) + this.getTDOpen(n, n2);
    }
    
    public String getTDClose() {
        return String.valueOf(super._sCellFooter) + "</td>";
    }
    
    public String getTDOpen(final int n, final int n2) {
        if (n2 == 1) {
            return "<td id=\"row" + n + "\" headers=\"h" + n2 + "\" scope=\"row\">" + super._sCell;
        }
        return "<td headers=\"row" + n + " h" + n2 + "\">";
    }
    
    public String getTHCellFormat(final int n) {
        return String.valueOf(this.getTHClose()) + this.getTHOpen(n);
    }
    
    public String getTHClose() {
        return String.valueOf(super._sCellFooter) + "</th>";
    }
    
    public String getTHOpen(final int n) {
        return "<th id=\"h" + n + "\" scope =\"col\">" + super._sCell;
    }
}
