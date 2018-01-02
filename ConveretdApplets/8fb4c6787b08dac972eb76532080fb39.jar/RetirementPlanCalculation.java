import KJEcalculation.CalculationException;
import KJEgui.Fmt;
import KJEcalculation.Calculation;

// 
// Decompiled by Procyon v0.5.30
// 

public class RetirementPlanCalculation extends Calculation
{
    public boolean bATRETIREMENT;
    public int iDecimals;
    public String RESULT_START_MSG;
    public String RESULT_START2_MSG;
    public String RUNOUT_BEGIN_MSG;
    public String RUNOUT_END_MSG;
    public String SUCCESS_BEGIN_MSG;
    public String SUCCESS_END_MSG;
    public String INFLATION_REPORT_MSG;
    public String LABEL_3;
    public String LABEL_4;
    public String MSG_ERROR1;
    public String MSG_ERROR2;
    public String RESPONSE;
    public String RESPONSE1;
    public String RESPONSE2;
    public String _sGraphUnits2;
    public String MSG_START_OF_MESSAGE;
    public String START_OF_MESSAGE;
    public String MSG_SOCIAL_SECURITY;
    public String MSG_DOESNT_RUNOUT;
    public String MSG_RUNOUT;
    public boolean INCREASE_ANNUAL_SAVINGS;
    public boolean SHOW_SOCIAL;
    public double dAdjustedSavingsRate;
    public double dAdjustedRorRate;
    public double dAdjustedSalaryPercent;
    public double dAdjusteYearsToRetirement;
    public double dNeedAtRetire;
    public double ADJUST_ANNUAL_SAVINGS;
    public double ADJUST_MONTHLY_SAVINGS;
    public String RESULTS_MSG;
    public double CURRENT_AGE;
    public double HOUSEHOLD_INCOME;
    public double PRE_RATE_OF_RETURN;
    public double AGE_OF_RETIREMENT;
    public double POST_RATE_OF_RETURN;
    public double SALARY_PERCENT;
    public double YEARS_UNTIL_RETIREMENT;
    public double YEARS_OF_RETIREMENT;
    public double INCOME_PERCENT;
    public double CURRENT_SAVINGS;
    public double INFLATION_RATE;
    public double INCLUDE_SOCIAL_SECURITY;
    public double MARRIED;
    public double SOCIAL_SECURITY_PERCENT;
    public double SOCIAL_SECURITY_SALARY;
    public double ENDING_BALANCE;
    public double SAVINGS_PERCENT;
    public double ANNUAL_SAVINGS;
    public double MONTHLY_SAVINGS;
    public double AGE_RUN_OUT;
    public String END_OF_RETIREMENT_MESSAGE;
    public double SOCIAL_SECURITY_INCREASE_RATE;
    public double INCOME_AT_RETIRE;
    public double INCOME_REQUIRED_AT_RETIRE;
    public double SOCIALSECURITY_AT_RETIRE;
    public double SOCIALSECURITY_START_AGE;
    public double CURRENT_TAX;
    public double RETIREMENT_TAX;
    public double DEFERRED;
    public boolean INFLATION_RESULTS;
    public boolean SAVEMORE_RESULTS;
    public boolean SHORT_RESULTS;
    public double oldSAVINGS_PERCENT;
    public double BALANCE_AT_RETIRE;
    public int[] OTHER_PENSION_START;
    public double[] OTHER_PENSION_AMOUNT;
    public boolean[] OTHER_PENSION_INFLATION;
    public boolean[] OTHER_PENSION_CPP;
    public int OTHER_PENSION_COUNT;
    double[] OTHER_PENSION_AMOUNT_ADJUSTED;
    public int CPP_MINIMUM_AGE;
    public int CPP_MAXIMUM_AGE;
    public int CPP_NOMINAL_AGE;
    public double CPP_ADJUSTMENT;
    public float[] DS_SAVINGS;
    public float[] DS_WITHDRAWAL;
    public double[] DD_RETIRE1;
    public double[] DD_RETIRE2;
    public float[] DS_RETIRE1;
    public double dMax2;
    public double[] DR_SALARY;
    public double[] DR_BEGINING_BALANCE;
    public double[] DR_ENDING_BALANCE;
    public double[] DR_INTEREST;
    public double[] DR_SAVINGS;
    public double[] DR_RETIREMENT_INCOME;
    public double[] DR_SOCIAL_SECURITY_INCOME;
    public double[] DR_RETIREMENT_WITHDRAWAL;
    public double[] DS_INFLATION_INCREASE;
    public float[] DS_INFLATION_RUNOUT;
    public double[] DS_SAVEMORE_INCREASE;
    public float[] DS_SAVEMORE_RUNOUT;
    public String[] CS_INFLATION_RUNOUT;
    public String[] CS_SAVEMORE_RUNOUT;
    public String[] cats;
    public String[] cats2;
    public String[] CAT_LABELS;
    
    public RetirementPlanCalculation() {
        this.bATRETIREMENT = false;
        this.iDecimals = 0;
        this.RESULT_START_MSG = "Your plan could provide ";
        this.RESULT_START2_MSG = " when you retire.";
        this.RUNOUT_BEGIN_MSG = "With your current plan your retirement savings may run out at age ";
        this.RUNOUT_END_MSG = ".";
        this.SUCCESS_BEGIN_MSG = "You could end your retirement with ";
        this.SUCCESS_END_MSG = ".";
        this.INFLATION_REPORT_MSG = "This amount is increased INFLATION_RATE each year until you retire and through your retirement to account for inflation.";
        this.LABEL_3 = "Congratulations your plan is on track.";
        this.LABEL_4 = "You may need to save more.";
        this.MSG_ERROR1 = "Both current savings and monthly income cannot be zero.";
        this.MSG_ERROR2 = "Current age must be less than retirement age.";
        this.RESPONSE = "";
        this.RESPONSE1 = "";
        this.RESPONSE2 = "";
        this._sGraphUnits2 = "";
        this.MSG_START_OF_MESSAGE = "";
        this.START_OF_MESSAGE = "";
        this.MSG_SOCIAL_SECURITY = "This plan includes SOCIALSECURITY_AT_RETIRE per year from social security.";
        this.MSG_DOESNT_RUNOUT = "Does not runout of funds";
        this.MSG_RUNOUT = "Retirement funds runout at age";
        this.INCREASE_ANNUAL_SAVINGS = true;
        this.SHOW_SOCIAL = true;
        this.dAdjustedSavingsRate = 0.0;
        this.dAdjustedRorRate = 0.0;
        this.dAdjustedSalaryPercent = 0.0;
        this.dAdjusteYearsToRetirement = 0.0;
        this.dNeedAtRetire = 0.0;
        this.ADJUST_ANNUAL_SAVINGS = 0.0;
        this.ADJUST_MONTHLY_SAVINGS = 0.0;
        this.RESULTS_MSG = "";
        this.CURRENT_AGE = 0.0;
        this.HOUSEHOLD_INCOME = 0.0;
        this.PRE_RATE_OF_RETURN = 0.0;
        this.AGE_OF_RETIREMENT = 0.0;
        this.POST_RATE_OF_RETURN = 0.0;
        this.SALARY_PERCENT = 0.0;
        this.YEARS_UNTIL_RETIREMENT = 0.0;
        this.YEARS_OF_RETIREMENT = 0.0;
        this.INCOME_PERCENT = 0.0;
        this.CURRENT_SAVINGS = 0.0;
        this.INFLATION_RATE = 3.0;
        this.INCLUDE_SOCIAL_SECURITY = 0.0;
        this.MARRIED = 0.0;
        this.SOCIAL_SECURITY_PERCENT = 0.0;
        this.SOCIAL_SECURITY_SALARY = 0.0;
        this.ENDING_BALANCE = 0.0;
        this.SAVINGS_PERCENT = 0.0;
        this.ANNUAL_SAVINGS = 0.0;
        this.MONTHLY_SAVINGS = 0.0;
        this.AGE_RUN_OUT = 0.0;
        this.END_OF_RETIREMENT_MESSAGE = "";
        this.SOCIAL_SECURITY_INCREASE_RATE = 0.024;
        this.INCOME_AT_RETIRE = 0.0;
        this.INCOME_REQUIRED_AT_RETIRE = 0.0;
        this.SOCIALSECURITY_AT_RETIRE = 0.0;
        this.SOCIALSECURITY_START_AGE = 0.0;
        this.CURRENT_TAX = 0.0;
        this.RETIREMENT_TAX = 0.0;
        this.DEFERRED = 1.0;
        this.INFLATION_RESULTS = false;
        this.SAVEMORE_RESULTS = false;
        this.SHORT_RESULTS = false;
        this.oldSAVINGS_PERCENT = 0.0;
        this.BALANCE_AT_RETIRE = 0.0;
        this.OTHER_PENSION_START = new int[3];
        this.OTHER_PENSION_AMOUNT = new double[3];
        this.OTHER_PENSION_INFLATION = new boolean[3];
        this.OTHER_PENSION_CPP = new boolean[3];
        this.OTHER_PENSION_COUNT = 0;
        this.OTHER_PENSION_AMOUNT_ADJUSTED = new double[3];
        this.CPP_MINIMUM_AGE = 60;
        this.CPP_MAXIMUM_AGE = 71;
        this.CPP_NOMINAL_AGE = 65;
        this.CPP_ADJUSTMENT = 0.06;
        this.DD_RETIRE1 = new double[3];
        this.DD_RETIRE2 = new double[3];
        this.DS_RETIRE1 = new float[3];
        this.dMax2 = 0.0;
        this.DS_INFLATION_INCREASE = new double[4];
        this.DS_INFLATION_RUNOUT = new float[4];
        this.DS_SAVEMORE_INCREASE = new double[5];
        this.DS_SAVEMORE_RUNOUT = new float[5];
        this.CS_INFLATION_RUNOUT = new String[4];
        this.CS_SAVEMORE_RUNOUT = new String[5];
        this.cats2 = new String[3];
        this.CAT_LABELS = new String[] { "Projected Need:", "Projected with Social Security:", "Projected with Current Savings:" };
    }
    
    protected void calculate() throws CalculationException {
        for (int i = 0; i < this.DD_RETIRE1.length; ++i) {
            this.DD_RETIRE1[i] = 0.0;
            this.DD_RETIRE2[i] = 0.0;
        }
        double n = this.PRE_RATE_OF_RETURN / 100.0;
        double n2 = this.POST_RATE_OF_RETURN / 100.0;
        this.BALANCE_AT_RETIRE = this.CURRENT_SAVINGS;
        if (this.DEFERRED == 0.0) {
            n *= 1.0 - this.CURRENT_TAX / 100.0;
            n2 *= 1.0 - this.RETIREMENT_TAX / 100.0;
        }
        this.YEARS_UNTIL_RETIREMENT = this.AGE_OF_RETIREMENT - this.CURRENT_AGE;
        this.AGE_RUN_OUT = 0.0;
        if (this.SHOW_SOCIAL) {
            final SocialSecurityCalculation socialSecurityCalculation = new SocialSecurityCalculation();
            socialSecurityCalculation.clearInputed();
            socialSecurityCalculation.CURRENT_AGE = (int)((this.CURRENT_AGE > 69.0) ? 69.0 : this.CURRENT_AGE);
            socialSecurityCalculation.HOUSEHOLD_INCOME = this.HOUSEHOLD_INCOME;
            socialSecurityCalculation.AGE_OF_RETIREMENT = (int)((this.AGE_OF_RETIREMENT > 70.0) ? 70.0 : this.AGE_OF_RETIREMENT);
            socialSecurityCalculation.SALARY_PERCENT = this.SALARY_PERCENT;
            socialSecurityCalculation.SOCIAL_SECURITY_INCREASE_RATE = this.INFLATION_RATE;
            socialSecurityCalculation.MARRIED = this.MARRIED;
            socialSecurityCalculation.clearCalculated();
            socialSecurityCalculation.calculate();
            this.SOCIALSECURITY_AT_RETIRE = socialSecurityCalculation.SOCIAL_AT_RETIRE_AMT;
            this.SOCIALSECURITY_START_AGE = socialSecurityCalculation.SOCIAL_AT_RETIRE_AGE;
        }
        else {
            this.SOCIALSECURITY_AT_RETIRE = 0.0;
            this.SOCIALSECURITY_START_AGE = 65.0;
            this.INCLUDE_SOCIAL_SECURITY = 0.0;
        }
        this.ANNUAL_SAVINGS = Fmt.round(this.HOUSEHOLD_INCOME * (this.SAVINGS_PERCENT / 100.0), 0);
        this.MONTHLY_SAVINGS = this.ANNUAL_SAVINGS / 12.0;
        if (this.CURRENT_AGE > this.AGE_OF_RETIREMENT) {
            throw new CalculationException(this.MSG_ERROR2);
        }
        this.bATRETIREMENT = false;
        if (this.CURRENT_AGE == this.AGE_OF_RETIREMENT) {
            --this.CURRENT_AGE;
            this.bATRETIREMENT = true;
        }
        final int n3 = (int)Math.round(this.YEARS_UNTIL_RETIREMENT + this.YEARS_OF_RETIREMENT);
        int n4 = 0;
        double n5 = 0.0;
        this.DS_SAVINGS = new float[n3];
        this.DS_WITHDRAWAL = new float[n3];
        this.DR_SALARY = new double[n3];
        this.DR_BEGINING_BALANCE = new double[n3];
        this.DR_ENDING_BALANCE = new double[n3];
        this.DR_INTEREST = new double[n3];
        this.DR_SAVINGS = new double[n3];
        this.DR_RETIREMENT_INCOME = new double[n3];
        this.DR_SOCIAL_SECURITY_INCOME = new double[n3];
        this.DR_RETIREMENT_WITHDRAWAL = new double[n3];
        this.cats = new String[n3];
        final StringBuffer sb = new StringBuffer();
        boolean b = false;
        for (int j = 0; j < this.OTHER_PENSION_AMOUNT.length; ++j) {
            if (this.OTHER_PENSION_CPP[j]) {
                b = this.OTHER_PENSION_CPP[j];
            }
        }
        for (int k = 0; k < this.OTHER_PENSION_AMOUNT.length; ++k) {
            if (!this.OTHER_PENSION_CPP[k] || this.AGE_OF_RETIREMENT == this.CPP_NOMINAL_AGE) {
                this.OTHER_PENSION_AMOUNT_ADJUSTED[k] = this.OTHER_PENSION_AMOUNT[k];
            }
            else if (this.OTHER_PENSION_START[k] > this.CPP_NOMINAL_AGE) {
                this.OTHER_PENSION_AMOUNT_ADJUSTED[k] = this.OTHER_PENSION_AMOUNT[k] + (this.OTHER_PENSION_START[k] - this.CPP_NOMINAL_AGE) * this.CPP_ADJUSTMENT * this.OTHER_PENSION_AMOUNT[k];
            }
            else if (this.OTHER_PENSION_START[k] < this.CPP_NOMINAL_AGE) {
                this.OTHER_PENSION_AMOUNT_ADJUSTED[k] = this.OTHER_PENSION_AMOUNT[k] - (this.CPP_NOMINAL_AGE - this.OTHER_PENSION_START[k]) * this.CPP_ADJUSTMENT * this.OTHER_PENSION_AMOUNT[k];
            }
            else {
                this.OTHER_PENSION_AMOUNT_ADJUSTED[k] = this.OTHER_PENSION_AMOUNT[k];
            }
        }
        for (int l = 1; l <= n3; ++l) {
            n4 = l - 1;
            if (n4 == 0) {
                this.DR_BEGINING_BALANCE[n4] = this.CURRENT_SAVINGS;
                this.INCOME_AT_RETIRE = this.HOUSEHOLD_INCOME;
                this.INCOME_REQUIRED_AT_RETIRE = this.INCOME_AT_RETIRE * (this.INCOME_PERCENT / 100.0);
            }
            else {
                this.DR_BEGINING_BALANCE[n4] = this.DR_ENDING_BALANCE[n4 - 1];
            }
            if (this.YEARS_UNTIL_RETIREMENT - l > 0.0) {
                this.DR_SAVINGS[n4] = (this.INCREASE_ANNUAL_SAVINGS ? (((n4 == 0) ? this.HOUSEHOLD_INCOME : this.DR_SALARY[n4 - 1]) * (this.SAVINGS_PERCENT / 100.0)) : this.ANNUAL_SAVINGS);
                this.DR_SALARY[n4] = Calculation.FV_AMT(this.SALARY_PERCENT / 100.0, l, this.HOUSEHOLD_INCOME);
                this.DR_INTEREST[n4] = this.DR_BEGINING_BALANCE[n4] * n;
                this.DR_RETIREMENT_INCOME[n4] = 0.0;
                this.DR_SOCIAL_SECURITY_INCOME[n4] = 0.0;
                this.DR_RETIREMENT_WITHDRAWAL[n4] = 0.0;
                this.INCOME_AT_RETIRE = this.DR_SALARY[n4];
                this.INCOME_REQUIRED_AT_RETIRE = this.DR_SALARY[n4] * (this.INCOME_PERCENT / 100.0);
            }
            else {
                this.DR_SALARY[n4] = Calculation.FV_AMT(this.INFLATION_RATE / 100.0, l - this.YEARS_UNTIL_RETIREMENT - (double)(this.bATRETIREMENT ? 1 : 0), this.INCOME_AT_RETIRE);
                this.DR_INTEREST[n4] = this.DR_BEGINING_BALANCE[n4] * n2;
                this.DR_SAVINGS[n4] = 0.0;
                this.DR_RETIREMENT_INCOME[n4] = this.DR_SALARY[n4] * (this.INCOME_PERCENT / 100.0);
                if (l + this.CURRENT_AGE - this.SOCIALSECURITY_START_AGE >= 0.0 && this.INCLUDE_SOCIAL_SECURITY > 0.0) {
                    this.DR_SOCIAL_SECURITY_INCOME[n4] = Calculation.FV_AMT(this.INFLATION_RATE / 100.0, l + this.CURRENT_AGE - (this.bATRETIREMENT ? 1 : 0) - this.SOCIALSECURITY_START_AGE, this.SOCIALSECURITY_AT_RETIRE);
                }
                else {
                    this.DR_SOCIAL_SECURITY_INCOME[n4] = 0.0;
                }
                for (int n6 = 0; n6 < this.OTHER_PENSION_AMOUNT.length; ++n6) {
                    if (this.OTHER_PENSION_AMOUNT[n6] > 0.0 && l + this.CURRENT_AGE - ((this.OTHER_PENSION_START[n6] == 0) ? this.AGE_OF_RETIREMENT : this.OTHER_PENSION_START[n6]) >= 0.0) {
                        if (this.OTHER_PENSION_INFLATION[n6]) {
                            final double[] dr_SOCIAL_SECURITY_INCOME = this.DR_SOCIAL_SECURITY_INCOME;
                            final int n7 = n4;
                            dr_SOCIAL_SECURITY_INCOME[n7] += Calculation.FV_AMT(this.INFLATION_RATE / 100.0, l - (this.bATRETIREMENT ? 1 : 0), this.OTHER_PENSION_AMOUNT_ADJUSTED[n6] * 12.0);
                        }
                        else {
                            final double[] dr_SOCIAL_SECURITY_INCOME2 = this.DR_SOCIAL_SECURITY_INCOME;
                            final int n8 = n4;
                            dr_SOCIAL_SECURITY_INCOME2[n8] += this.OTHER_PENSION_AMOUNT_ADJUSTED[n6] * 12.0;
                        }
                    }
                }
                this.DR_RETIREMENT_WITHDRAWAL[n4] = this.DR_RETIREMENT_INCOME[n4] - this.DR_SOCIAL_SECURITY_INCOME[n4];
                double n9 = 1.0;
                if (this.DEFERRED == 1.0 && this.DR_RETIREMENT_WITHDRAWAL[n4] > 0.0) {
                    n9 = 1.0 - this.RETIREMENT_TAX / 100.0;
                }
                this.DR_RETIREMENT_WITHDRAWAL[n4] /= n9;
                final double npv_AMT = Calculation.NPV_AMT(n2, l - this.YEARS_UNTIL_RETIREMENT + 1.0, this.DR_RETIREMENT_WITHDRAWAL[n4]);
                final double[] dd_RETIRE1 = this.DD_RETIRE1;
                final int n10 = 1;
                dd_RETIRE1[n10] += npv_AMT;
                final double[] dd_RETIRE2 = this.DD_RETIRE2;
                final int n11 = 1;
                dd_RETIRE2[n11] += this.DR_RETIREMENT_WITHDRAWAL[n4];
                final double npv_AMT2 = Calculation.NPV_AMT(n2, l - this.YEARS_UNTIL_RETIREMENT + 1.0, this.DR_RETIREMENT_INCOME[n4] / n9);
                final double[] dd_RETIRE3 = this.DD_RETIRE1;
                final int n12 = 0;
                dd_RETIRE3[n12] += npv_AMT2;
                final double[] dd_RETIRE4 = this.DD_RETIRE2;
                final int n13 = 0;
                dd_RETIRE4[n13] += this.DR_RETIREMENT_INCOME[n4] / n9;
            }
            this.DR_ENDING_BALANCE[n4] = this.DR_BEGINING_BALANCE[n4] + this.DR_INTEREST[n4] + this.DR_SAVINGS[n4] - this.DR_RETIREMENT_WITHDRAWAL[n4];
            if (this.YEARS_UNTIL_RETIREMENT - l == 1.0) {
                this.BALANCE_AT_RETIRE = this.DR_ENDING_BALANCE[n4];
            }
            if (this.DR_ENDING_BALANCE[n4] <= 0.0) {
                final double n14 = (this.AGE_RUN_OUT != 0.0) ? this.DR_RETIREMENT_WITHDRAWAL[n4] : (-1.0 * this.DR_ENDING_BALANCE[n4]);
                final double npv_AMT3 = Calculation.NPV_AMT(n2, l - this.YEARS_UNTIL_RETIREMENT + 1.0, n14);
                final double[] dd_RETIRE5 = this.DD_RETIRE1;
                final int n15 = 2;
                dd_RETIRE5[n15] += npv_AMT3;
                final double[] dd_RETIRE6 = this.DD_RETIRE2;
                final int n16 = 2;
                dd_RETIRE6[n16] += n14;
                if (this.AGE_RUN_OUT == 0.0) {
                    this.AGE_RUN_OUT = l + this.CURRENT_AGE;
                }
                this.DR_RETIREMENT_WITHDRAWAL[n4] = this.DR_BEGINING_BALANCE[n4] + this.DR_INTEREST[n4] + this.DR_SAVINGS[n4];
                this.DR_ENDING_BALANCE[n4] = 0.0;
            }
            if (this.DR_ENDING_BALANCE[n4] > n5) {
                n5 = this.DR_ENDING_BALANCE[n4];
            }
        }
        this.ENDING_BALANCE = this.DR_ENDING_BALANCE[n4];
        this.END_OF_RETIREMENT_MESSAGE = null;
        if (this.ENDING_BALANCE == 0.0) {
            this.END_OF_RETIREMENT_MESSAGE = String.valueOf(this.RESULT_START_MSG) + Fmt.dollars(this.BALANCE_AT_RETIRE, this.iDecimals) + " " + this.RESULT_START2_MSG + " " + this.RUNOUT_BEGIN_MSG + Fmt.number(this.AGE_RUN_OUT) + ((this.RUNOUT_END_MSG.length() == 1) ? this.RUNOUT_END_MSG : (" " + this.RUNOUT_END_MSG));
            this.RESULTS_MSG = this.LABEL_4;
            this.RESPONSE = this.RESPONSE2;
        }
        else {
            this.END_OF_RETIREMENT_MESSAGE = String.valueOf(this.RESULT_START_MSG) + Fmt.dollars(this.BALANCE_AT_RETIRE, this.iDecimals) + " " + this.RESULT_START2_MSG + " " + this.SUCCESS_BEGIN_MSG + Fmt.dollars(this.ENDING_BALANCE, this.iDecimals) + ((this.SUCCESS_END_MSG.length() == 1) ? this.SUCCESS_END_MSG : (" " + this.SUCCESS_END_MSG));
            this.RESULTS_MSG = this.LABEL_3;
            this.RESPONSE = this.RESPONSE1;
        }
        if (this.ENDING_BALANCE == 0.0) {
            this.dAdjustedSavingsRate = this.getAdjustedSavingsRate((int)(this.CURRENT_AGE + n3), n, n2);
            this.dAdjustedRorRate = this.getAdjustedRorRate((int)(this.CURRENT_AGE + n3), n, n2);
            this.dAdjustedSalaryPercent = this.getAdjustedSalaryPercent((int)(this.CURRENT_AGE + n3), n, n2);
            this.dAdjusteYearsToRetirement = this.getAdjusteYearsToRetirement((int)(this.CURRENT_AGE + n3), n, n2) + this.CURRENT_AGE;
            if (this.YEARS_UNTIL_RETIREMENT <= 1.0) {
                this.ADJUST_ANNUAL_SAVINGS = this.DD_RETIRE1[2];
            }
            else {
                this.ADJUST_ANNUAL_SAVINGS = Fmt.round(this.dAdjustedSavingsRate * this.HOUSEHOLD_INCOME, 2);
            }
            this.ADJUST_MONTHLY_SAVINGS = Fmt.round(this.ADJUST_ANNUAL_SAVINGS / 12.0, 2);
        }
        if (this.INFLATION_RESULTS) {
            for (int n17 = 0; n17 < 4; ++n17) {
                this.DS_INFLATION_INCREASE[n17] = n17 * 0.03 + this.INFLATION_RATE / 100.0;
                if (n17 == 3) {
                    this.DS_INFLATION_INCREASE[n17] = (n17 + 1) * 0.03 + this.INFLATION_RATE / 100.0;
                }
                this.DS_INFLATION_RUNOUT[n17] = (float)this.getRunoutAge(this.CURRENT_AGE, this.CURRENT_SAVINGS, this.SALARY_PERCENT / 100.0, this.HOUSEHOLD_INCOME, n, this.SAVINGS_PERCENT / 100.0, this.DS_INFLATION_INCREASE[n17], n2, this.INCOME_PERCENT / 100.0, this.INCLUDE_SOCIAL_SECURITY, this.INFLATION_RATE / 100.0, this.YEARS_UNTIL_RETIREMENT, this.SOCIALSECURITY_START_AGE, this.SOCIALSECURITY_AT_RETIRE, 110, true);
                this.CS_INFLATION_RUNOUT[n17] = Fmt.percent(this.DS_INFLATION_INCREASE[n17]);
            }
        }
        if (this.SAVEMORE_RESULTS) {
            for (int n18 = 0; n18 < 5; ++n18) {
                this.DS_SAVEMORE_INCREASE[n18] = n18 * 0.04 + this.SAVINGS_PERCENT / 100.0;
                this.DS_SAVEMORE_RUNOUT[n18] = (float)this.getRunoutAge(this.CURRENT_AGE, this.CURRENT_SAVINGS, this.SALARY_PERCENT / 100.0, this.HOUSEHOLD_INCOME, n, this.DS_SAVEMORE_INCREASE[n18], this.INFLATION_RATE / 100.0, n2, this.INCOME_PERCENT / 100.0, this.INCLUDE_SOCIAL_SECURITY, this.INFLATION_RATE / 100.0, this.YEARS_UNTIL_RETIREMENT, this.SOCIALSECURITY_START_AGE, this.SOCIALSECURITY_AT_RETIRE, 110, true);
                this.CS_SAVEMORE_RUNOUT[n18] = Fmt.percent(this.DS_SAVEMORE_INCREASE[n18]);
            }
        }
        if (super.bWithSchedule) {
            if (this.SHORT_RESULTS) {
                this.addRepeat("<TD WIDTH=5%>" + super._sCell + this.sReportCol("<B><BR><BR>CENTER>Age</CENTER></B>", 9) + super._sCellFooter + "</TD><TD WIDTH=14%>" + super._sCell + this.sReportCol("<B><CENTER>Interest<BR> and<BR> Savings</CENTER></B>", 8) + super._sCellFooter + "</TD><TD WIDTH=13%>" + super._sCell + this.sReportCol("<B><CENTER>Retirement<BR>Account<BR>Withdrawals</CENTER></B>", 6) + super._sCellFooter + "</TD><TD WIDTH=13%>" + super._sCell + this.sReportCol("<B><CENTER>Ending<BR>Retirement<BR>Balance</CENTER></B>", 7) + super._sCellFooter + "</TD>");
            }
            else {
                this.addRepeat("<TD WIDTH=5%>" + super._sCell + this.sReportCol("<B><BR><BR><CENTER>Age</CENTER></B>", 9) + super._sCellFooter + "</TD><TD WIDTH=14%>" + super._sCell + this.sReportCol("<B><CENTER>Beginning<BR>Retirement<BR>Balance</CENTER></B>", 1) + super._sCellFooter + "</TD><TD WIDTH=14%>" + super._sCell + this.sReportCol("<B><CENTER> <BR>Investment<BR>Growth</CENTER></B>", 2) + ((this.ANNUAL_SAVINGS == 0.0) ? "" : (this.INCREASE_ANNUAL_SAVINGS ? (String.valueOf(super._sCellFooter) + "</TD><TD WIDTH=14%>" + super._sCell + Calculation.replace("CALC_RESULT", Fmt.percent(this.SAVINGS_PERCENT / 100.0, 2), this.sReportCol("<B><CENTER>Contributions<BR>at CALC_RESULT<BR>of Income</CENTER></B>", 3))) : (String.valueOf(super._sCellFooter) + "</TD><TD WIDTH=14%>" + super._sCell + Calculation.replace("CALC_RESULT", Fmt.percent(this.SAVINGS_PERCENT / 100.0, 2), this.sReportCol("<B><CENTER><BR><BR>Contributions</CENTER></B>", 10))))) + super._sCellFooter + "</TD><TD WIDTH=14%>" + super._sCell + Calculation.replace("CALC_RESULT", Fmt.percent(this.INCOME_PERCENT / 100.0, 0), this.sReportCol("<B><CENTER>Retire<BR>with CALC_RESULT<BR>of Income</CENTER></B>", 4)) + ((this.INCLUDE_SOCIAL_SECURITY > 0.0 || this.OTHER_PENSION_COUNT > 0) ? (String.valueOf(super._sCellFooter) + "</TD><TD WIDTH=13%>" + super._sCell + this.sReportCol(b ? "<B><CENTER>Pension Income<BR>OAS/CPP<BR>or QPP</CENTER></B>" : "<B><CENTER>Social<BR>Security<BR>Income</CENTER></B>", 5)) : "") + super._sCellFooter + "</TD><TD WIDTH=13%>" + super._sCell + this.sReportCol("<B><CENTER>Retirement<BR>Account<BR>Withdrawals</CENTER></B>", 6) + super._sCellFooter + "</TD><TD WIDTH=13%>" + super._sCell + this.sReportCol("<B><CENTER>Ending<BR>Retirement<BR>Balance</CENTER></B>", 7) + super._sCellFooter + "</TD>");
            }
        }
        for (int n19 = 0; n19 < this.DD_RETIRE1.length; ++n19) {
            this.dMax2 = ((this.DD_RETIRE1[n19] > this.dMax2) ? this.DD_RETIRE1[n19] : this.dMax2);
        }
        final int graphUnits = this.getGraphUnits(this.dMax2);
        this._sGraphUnits2 = super._sGraphUnits;
        if (this.INCLUDE_SOCIAL_SECURITY < 1.0) {
            this.DS_RETIRE1 = new float[2];
            (this.cats2 = new String[2])[0] = String.valueOf(this.CAT_LABELS[0]) + " " + Fmt.dollars(this.DD_RETIRE1[0]);
            this.cats2[1] = String.valueOf(this.CAT_LABELS[2]) + " " + Fmt.dollars(this.DD_RETIRE1[2]);
            for (int n20 = 0; n20 < this.DS_RETIRE1.length; ++n20) {
                this.DS_RETIRE1[n20] = (float)(this.DD_RETIRE1[n20 + 1] / graphUnits);
            }
        }
        else {
            this.DS_RETIRE1 = new float[3];
            this.cats2 = new String[3];
            for (int n21 = 0; n21 < this.DS_RETIRE1.length; ++n21) {
                this.DS_RETIRE1[n21] = (float)(this.DD_RETIRE1[n21] / graphUnits);
                this.cats2[n21] = String.valueOf(this.CAT_LABELS[n21]) + " " + Fmt.dollars(this.DD_RETIRE1[n21]);
            }
        }
        final int graphUnits2 = this.getGraphUnits(n5);
        for (int n22 = 1; n22 <= n3; ++n22) {
            final int n23 = n22 - 1;
            this.cats[n23] = Fmt.number(n23 + this.CURRENT_AGE);
            if (this.DR_ENDING_BALANCE[n23] < 0.0) {
                this.DS_SAVINGS[n23] = 0.0f;
                this.DS_WITHDRAWAL[n23] = 0.0f;
                this.DR_ENDING_BALANCE[n23] = 0.0;
                this.DR_RETIREMENT_WITHDRAWAL[n23] = 0.0;
            }
            else {
                this.DS_SAVINGS[n23] = (float)(this.DR_ENDING_BALANCE[n23] / graphUnits2);
                this.DS_WITHDRAWAL[n23] = (float)(this.DR_RETIREMENT_WITHDRAWAL[n23] / graphUnits2);
            }
            if (super.bWithSchedule) {
                if (this.SHORT_RESULTS) {
                    this.addRepeat("<TD>" + super._sCell + Fmt.number(n22 + this.CURRENT_AGE, 0) + super._sCellFormat + Fmt.dollars(this.DR_INTEREST[n23] + this.DR_SAVINGS[n23], this.iDecimals) + super._sCellFormat + Fmt.dollars(this.DR_RETIREMENT_WITHDRAWAL[n23], this.iDecimals) + super._sCellFormat + Fmt.dollars(this.DR_ENDING_BALANCE[n23], this.iDecimals) + super._sCellFooter + "</TD>");
                }
                else {
                    this.addRepeat("<TD WIDTH=5% ALIGN=RIGHT>" + super._sCell + Fmt.number(n22 + this.CURRENT_AGE, 0) + super._sCellFooter + "</TD><TD WIDTH=14% ALIGN=RIGHT>" + super._sCell + Fmt.dollars(this.DR_BEGINING_BALANCE[n23], this.iDecimals) + super._sCellFooter + "</TD><TD WIDTH=14% ALIGN=RIGHT>" + super._sCell + Fmt.dollars(this.DR_INTEREST[n23], this.iDecimals) + ((this.ANNUAL_SAVINGS == 0.0) ? "" : (String.valueOf(super._sCellFooter) + "</TD><TD WIDTH=14% ALIGN=RIGHT>" + super._sCell + Fmt.dollars(this.DR_SAVINGS[n23], this.iDecimals))) + super._sCellFooter + "</TD><TD WIDTH=14% ALIGN=RIGHT>" + super._sCell + Fmt.dollars(this.DR_RETIREMENT_INCOME[n23], this.iDecimals) + ((this.INCLUDE_SOCIAL_SECURITY > 0.0 || this.OTHER_PENSION_COUNT > 0) ? (String.valueOf(super._sCellFooter) + "</TD><TD WIDTH=13% ALIGN=RIGHT>" + super._sCell + Fmt.dollars(this.DR_SOCIAL_SECURITY_INCOME[n23], this.iDecimals)) : "") + super._sCellFooter + "</TD><TD WIDTH=13% ALIGN=RIGHT>" + super._sCell + Fmt.dollars(this.DR_RETIREMENT_WITHDRAWAL[n23], this.iDecimals) + super._sCellFooter + "</TD><TD WIDTH=13% ALIGN=RIGHT>" + super._sCell + Fmt.dollars(this.DR_ENDING_BALANCE[n23], this.iDecimals) + super._sCellFooter + "</TD>");
                }
            }
        }
        if (this.MSG_START_OF_MESSAGE.equals("")) {
            this.START_OF_MESSAGE = "";
        }
        else {
            this.START_OF_MESSAGE = Calculation.replace("NEED_AT_RETIRE_AFSS", Fmt.dollars(this.DD_RETIRE1[1], 0), this.MSG_START_OF_MESSAGE);
            this.START_OF_MESSAGE = String.valueOf(Calculation.replace("NEED_THRU_RETIRE_AFSS", Fmt.dollars(this.DD_RETIRE2[1], 0), this.START_OF_MESSAGE)) + " ";
        }
    }
    
    protected void clearCalculated() {
        this.YEARS_UNTIL_RETIREMENT = 0.0;
        this.SOCIAL_SECURITY_PERCENT = 0.0;
        this.SOCIAL_SECURITY_SALARY = 0.0;
        this.ENDING_BALANCE = 0.0;
        this.ANNUAL_SAVINGS = 0.0;
        this.MONTHLY_SAVINGS = 0.0;
        this.INCOME_AT_RETIRE = 0.0;
        this.INCOME_REQUIRED_AT_RETIRE = 0.0;
        this.SOCIALSECURITY_AT_RETIRE = 0.0;
        this.SOCIALSECURITY_START_AGE = 0.0;
        this.BALANCE_AT_RETIRE = 0.0;
        this.ADJUST_ANNUAL_SAVINGS = 0.0;
        this.ADJUST_MONTHLY_SAVINGS = 0.0;
    }
    
    public void clearInputed() {
        this.CURRENT_AGE = 0.0;
        this.HOUSEHOLD_INCOME = 0.0;
        this.PRE_RATE_OF_RETURN = 0.0;
        this.AGE_OF_RETIREMENT = 0.0;
        this.POST_RATE_OF_RETURN = 0.0;
        this.SALARY_PERCENT = 0.0;
        this.YEARS_OF_RETIREMENT = 0.0;
        this.CURRENT_SAVINGS = 0.0;
        this.INFLATION_RATE = 0.0;
        this.INCLUDE_SOCIAL_SECURITY = 0.0;
        this.MARRIED = 0.0;
        this.SAVINGS_PERCENT = 0.0;
        this.INFLATION_RESULTS = false;
        this.SAVEMORE_RESULTS = false;
    }
    
    protected String formatReport(final String s) {
        String s2 = s;
        for (int i = 0; i < this.OTHER_PENSION_START.length; ++i) {
            s2 = Calculation.replace("OTHER_PENSION_INFLATION" + (i + 1), this.OTHER_PENSION_INFLATION[i] ? this.INFLATION_REPORT_MSG : "", Calculation.replace("OTHER_PENSION_AMOUNT" + (i + 1), Fmt.dollars(this.OTHER_PENSION_AMOUNT_ADJUSTED[i], this.iDecimals), Calculation.replace("OTHER_PENSION_START" + (i + 1), Fmt.number((this.OTHER_PENSION_START[i] > this.AGE_OF_RETIREMENT) ? ((double)this.OTHER_PENSION_START[i]) : this.AGE_OF_RETIREMENT), s2)));
        }
        if (this.INFLATION_RESULTS) {
            for (int j = 0; j < 4; ++j) {
                final String replace = Calculation.replace("INFLATION_INCREASE" + j, Fmt.percent(this.DS_INFLATION_INCREASE[j], 2), s2);
                if (this.DS_INFLATION_RUNOUT[j] < 110.0f) {
                    s2 = Calculation.replace("INFLATION_MSG" + j, String.valueOf(this.MSG_RUNOUT) + " " + Fmt.number(this.DS_INFLATION_RUNOUT[j]), replace);
                }
                else {
                    s2 = Calculation.replace("INFLATION_MSG" + j, this.MSG_DOESNT_RUNOUT, replace);
                }
            }
        }
        if (this.SAVEMORE_RESULTS) {
            for (int k = 0; k < 5; ++k) {
                final String replace2 = Calculation.replace("SAVEMORE_INCREASE" + k, Fmt.percent(this.DS_SAVEMORE_INCREASE[k], 2), s2);
                if (this.DS_SAVEMORE_RUNOUT[k] < 110.0f) {
                    s2 = Calculation.replace("SAVEMORE_MSG" + k, String.valueOf(this.MSG_RUNOUT) + " " + Fmt.number(this.DS_SAVEMORE_RUNOUT[k]), replace2);
                }
                else {
                    s2 = Calculation.replace("SAVEMORE_MSG" + k, this.MSG_DOESNT_RUNOUT, replace2);
                }
            }
        }
        String s3;
        if (this.ENDING_BALANCE == 0.0 && !this.bATRETIREMENT && this.CURRENT_AGE != this.AGE_OF_RETIREMENT - 1.0) {
            s3 = Calculation.replace("ADJUST_YEARS", Fmt.number(this.dAdjusteYearsToRetirement), Calculation.replace("ADJUST_INCOME_REQUIRED", Fmt.percent(this.dAdjustedSalaryPercent), Calculation.replace("ADJUST_ROR", Fmt.percent(this.dAdjustedRorRate, 2), Calculation.replace("ADJUST_SAVINGS_RATE", Fmt.percent(this.dAdjustedSavingsRate, 2), Calculation.replace("</ADJUST_ADVICE>", "", Calculation.replace("<ADJUST_ADVICE>", "", Calculation.replace("<!--/ADJUST_ADVICE-->", "", Calculation.replace("<!--ADJUST_ADVICE-->", "", s2))))))));
        }
        else {
            s3 = Calculation.replace("</ADJUST_ADVICE>", "-->", Calculation.replace("<ADJUST_ADVICE>", "<!--", Calculation.replace("<!--/ADJUST_ADVICE-->", "-->", Calculation.replace("<!--ADJUST_ADVICE-->", "<!--", s2))));
        }
        final String replace3 = Calculation.replace("INFLATION_RATE", Fmt.percent(this.INFLATION_RATE / 100.0, 1), Calculation.replace("CURRENT_SAVINGS", Fmt.dollars(this.CURRENT_SAVINGS, this.iDecimals), Calculation.replace("INCOME_PERCENT", Fmt.percent(this.INCOME_PERCENT / 100.0, 2), Calculation.replace("YEARS_OF_RETIREMENT", Fmt.number(this.YEARS_OF_RETIREMENT), Calculation.replace("YEARS_UNTIL_RETIREMENT", Fmt.number(this.YEARS_UNTIL_RETIREMENT), Calculation.replace("SALARY_PERCENT", Fmt.percent(this.SALARY_PERCENT / 100.0, 2), Calculation.replace("POST_RATE_OF_RETURN", Fmt.percent(this.POST_RATE_OF_RETURN / 100.0, 2), Calculation.replace("AGE_OF_RETIREMENT", Fmt.number(this.AGE_OF_RETIREMENT), Calculation.replace("PRE_RATE_OF_RETURN", Fmt.percent(this.PRE_RATE_OF_RETURN / 100.0, 2), Calculation.replace("HOUSEHOLD_INCOME", Fmt.dollars(this.HOUSEHOLD_INCOME, this.iDecimals), Calculation.replace("CURRENT_AGE", Fmt.number(this.CURRENT_AGE + (double)(this.bATRETIREMENT ? 1 : 0)), Calculation.replace("INCREASE_ANNUAL_SAVINGS", this.INCREASE_ANNUAL_SAVINGS ? "will" : "does not", Calculation.replace("NEED_THRU_RETIRE", Fmt.dollars(this.DD_RETIRE2[0]), Calculation.replace("NEED_THRU_RETIRE_PLAN", Fmt.dollars(this.DD_RETIRE2[2]), Calculation.replace("NEED_THRU_RETIRE_AFSS", Fmt.dollars(this.DD_RETIRE2[1]), Calculation.replace("NEED_AT_RETIRE", Fmt.dollars(this.DD_RETIRE1[0]), Calculation.replace("NEED_AT_RETIRE_PLAN", Fmt.dollars(this.DD_RETIRE1[2]), Calculation.replace("NEED_AT_RETIRE_AFSS", Fmt.dollars(this.DD_RETIRE1[1]), Calculation.replace("ADJUST_MONTHLY_SAVINGS", Fmt.dollars(this.ADJUST_MONTHLY_SAVINGS, 2), Calculation.replace("ADJUST_ANNUAL_SAVINGS", Fmt.dollars(this.ADJUST_ANNUAL_SAVINGS, 2), s3))))))))))))))))))));
        String s4;
        if (this.INCLUDE_SOCIAL_SECURITY > 0.0) {
            s4 = Calculation.replace("SOCIALSECURITY_AT_RETIRE", Fmt.dollars(this.SOCIALSECURITY_AT_RETIRE, this.iDecimals), Calculation.replace("SOCIAL_SECURITY_MSG", this.MSG_SOCIAL_SECURITY, replace3));
        }
        else {
            s4 = Calculation.replace("SOCIALSECURITY_AT_RETIRE", Fmt.dollars(0.0, this.iDecimals), Calculation.replace("SOCIAL_SECURITY_MSG", "", replace3));
        }
        return Calculation.replace("RESPONSE", this.RESPONSE, Calculation.replace("RESULTS_MSG", this.RESULTS_MSG, Calculation.replace("BALANCE_AT_RETIRE", Fmt.dollars(this.BALANCE_AT_RETIRE, this.iDecimals), Calculation.replace("SOCIALSECURITY_START_AGE", Fmt.number(this.SOCIALSECURITY_START_AGE), Calculation.replace("INCOME_REQUIRED_AT_RETIRE", Fmt.dollars(this.INCOME_REQUIRED_AT_RETIRE, this.iDecimals), Calculation.replace("INCOME_AT_RETIRE", Fmt.dollars(this.INCOME_AT_RETIRE, this.iDecimals), Calculation.replace("END_OF_RETIREMENT_MESSAGE", this.END_OF_RETIREMENT_MESSAGE, Calculation.replace("MONTHLY_SAVINGS", Fmt.dollars(this.MONTHLY_SAVINGS, this.iDecimals), Calculation.replace("ANNUAL_SAVINGS", Fmt.dollars(this.ANNUAL_SAVINGS, this.iDecimals), Calculation.replace("SAVINGS_PERCENT", Fmt.percent(this.SAVINGS_PERCENT / 100.0, 2), Calculation.replace("ENDING_BALANCE", Fmt.dollars(this.ENDING_BALANCE, this.iDecimals), Calculation.replace("PV_ENDING_BALANCE", Fmt.dollars(Calculation.NPV_AMT(this.INFLATION_RATE / 100.0, (int)Math.round(this.YEARS_UNTIL_RETIREMENT + this.YEARS_OF_RETIREMENT), this.ENDING_BALANCE), this.iDecimals), Calculation.replace("SOCIAL_SECURITY_SALARY", Fmt.dollars(this.SOCIAL_SECURITY_SALARY, this.iDecimals), Calculation.replace("SOCIAL_SECURITY_PERCENT", Fmt.percent(this.SOCIAL_SECURITY_PERCENT), Calculation.replace("MARRIED", Fmt.yesno(this.MARRIED), Calculation.replace("CHECK_MARRIED", (this.MARRIED != 0.0) ? "CHECKED" : "", Calculation.replace("RETIREMENT_TAX", Fmt.percent(this.RETIREMENT_TAX / 100.0), Calculation.replace("CURRENT_TAX", Fmt.percent(this.CURRENT_TAX / 100.0), Calculation.replace("DEFERRED", Fmt.yesno(this.DEFERRED), Calculation.replace("INCLUDE_SOCIAL_SECURITY", Fmt.yesno(this.INCLUDE_SOCIAL_SECURITY), Calculation.replace("CHECK_SOCIAL_SECURITY", (this.INCLUDE_SOCIAL_SECURITY != 0.0) ? "CHECKED" : "", s4)))))))))))))))))))));
    }
    
    public int getAdjusteYearsToRetirement(final int n, final double n2, final double n3) {
        final SocialSecurityCalculation socialSecurityCalculation = new SocialSecurityCalculation();
        for (int i = 1; i < 50; ++i) {
            double n4 = 0.0;
            double social_AT_RETIRE_AMT = 0.0;
            if (this.INCLUDE_SOCIAL_SECURITY > 0.0) {
                socialSecurityCalculation.clearInputed();
                socialSecurityCalculation.CURRENT_AGE = (int)((this.CURRENT_AGE > 69.0) ? 69.0 : this.CURRENT_AGE);
                socialSecurityCalculation.HOUSEHOLD_INCOME = this.HOUSEHOLD_INCOME;
                socialSecurityCalculation.AGE_OF_RETIREMENT = (int)((this.CURRENT_AGE + this.YEARS_UNTIL_RETIREMENT + i > 70.0) ? 70.0 : (this.CURRENT_AGE + this.YEARS_UNTIL_RETIREMENT + i));
                socialSecurityCalculation.SALARY_PERCENT = this.SALARY_PERCENT;
                socialSecurityCalculation.SOCIAL_SECURITY_INCREASE_RATE = this.INFLATION_RATE;
                socialSecurityCalculation.MARRIED = this.MARRIED;
                try {
                    socialSecurityCalculation.clearCalculated();
                    socialSecurityCalculation.calculate();
                }
                catch (CalculationException ex) {}
                social_AT_RETIRE_AMT = socialSecurityCalculation.SOCIAL_AT_RETIRE_AMT;
                n4 = socialSecurityCalculation.SOCIAL_AT_RETIRE_AGE;
            }
            if (this.getRunoutAge(this.CURRENT_AGE, this.CURRENT_SAVINGS, this.SALARY_PERCENT / 100.0, this.HOUSEHOLD_INCOME, n2, this.SAVINGS_PERCENT / 100.0, this.INFLATION_RATE / 100.0, n3, this.INCOME_PERCENT / 100.0, this.INCLUDE_SOCIAL_SECURITY, this.INFLATION_RATE / 100.0, (int)(this.YEARS_UNTIL_RETIREMENT + i), n4, social_AT_RETIRE_AMT, n + i, false) > 0.0) {
                return (int)(this.YEARS_UNTIL_RETIREMENT + i);
            }
        }
        return (int)(this.YEARS_UNTIL_RETIREMENT + 50.0);
    }
    
    public double getAdjustedRorRate(final int n, final double n2, final double n3) {
        double n4 = n2;
        double n5 = 0.5;
        for (int i = 1; i < 50; ++i) {
            final double runoutAge = this.getRunoutAge(this.CURRENT_AGE, this.CURRENT_SAVINGS, this.SALARY_PERCENT / 100.0, this.HOUSEHOLD_INCOME, n4, this.SAVINGS_PERCENT / 100.0, this.INFLATION_RATE / 100.0, n3, this.INCOME_PERCENT / 100.0, this.INCLUDE_SOCIAL_SECURITY, this.INFLATION_RATE / 100.0, this.YEARS_UNTIL_RETIREMENT, this.SOCIALSECURITY_START_AGE, this.SOCIALSECURITY_AT_RETIRE, n, false);
            if (runoutAge > 0.0 && runoutAge < 1.0) {
                return Fmt.round(n4 + 5.0E-5, 4);
            }
            if (runoutAge <= 0.0) {
                n4 += n5;
            }
            else {
                n4 -= n5;
            }
            n5 /= 2.0;
        }
        return Fmt.round(n4 + 5.0E-5, 4);
    }
    
    public double getAdjustedSalaryPercent(final int n, final double n2, final double n3) {
        double n4 = this.INCOME_PERCENT / 100.0;
        double n5 = 0.5;
        for (int i = 1; i < 50; ++i) {
            final double runoutAge = this.getRunoutAge(this.CURRENT_AGE, this.CURRENT_SAVINGS, this.SALARY_PERCENT / 100.0, this.HOUSEHOLD_INCOME, n2, this.SAVINGS_PERCENT / 100.0, this.INFLATION_RATE / 100.0, n3, n4, this.INCLUDE_SOCIAL_SECURITY, this.INFLATION_RATE / 100.0, this.YEARS_UNTIL_RETIREMENT, this.SOCIALSECURITY_START_AGE, this.SOCIALSECURITY_AT_RETIRE, n, false);
            if (runoutAge > 0.0 && runoutAge < 1.0) {
                return Fmt.round(n4 - 0.005, 2);
            }
            if (runoutAge <= 0.0) {
                n4 -= n5;
            }
            else {
                n4 += n5;
            }
            n5 /= 2.0;
        }
        return Fmt.round(n4 - 0.005, 4);
    }
    
    public double getAdjustedSavingsRate(final int n, final double n2, final double n3) {
        double n4 = 200.0;
        double n5 = n4 / 2.0;
        for (int i = 1; i < 50; ++i) {
            final double runoutAge = this.getRunoutAge(this.CURRENT_AGE, this.CURRENT_SAVINGS, this.SALARY_PERCENT / 100.0, this.HOUSEHOLD_INCOME, n2, n4, this.INFLATION_RATE / 100.0, n3, this.INCOME_PERCENT / 100.0, this.INCLUDE_SOCIAL_SECURITY, this.INFLATION_RATE / 100.0, this.YEARS_UNTIL_RETIREMENT, this.SOCIALSECURITY_START_AGE, this.SOCIALSECURITY_AT_RETIRE, n, false);
            if (runoutAge > 0.0 && runoutAge < 1.0) {
                return Fmt.round(n4 + 5.0E-8, 7);
            }
            if (runoutAge <= 0.0) {
                n4 += n5;
            }
            else {
                n4 -= n5;
            }
            n5 /= 2.0;
        }
        return Fmt.round(n4 + 5.0E-8, 7);
    }
    
    public String getAmountLabel() {
        return String.valueOf(super._sGraphUnits) + super.sCurrency;
    }
    
    public String getAmountLabel2() {
        return String.valueOf(this._sGraphUnits2) + super.sCurrency;
    }
    
    public String[] getCatagories() {
        return this.cats;
    }
    
    public String[] getCatagories2() {
        return this.cats2;
    }
    
    public double getRunoutAge(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10, final double n11, final double n12, final double n13, final double n14, int n15, final boolean b) {
        double n16 = n2;
        double n17 = 0.0;
        double fv_AMT = 0.0;
        final double n18 = n4 * n6;
        for (int n19 = 1; n19 <= n15 - this.CURRENT_AGE; ++n19) {
            double n20;
            double n21;
            double n22;
            if (n12 - n19 > 0.0) {
                final double fv_AMT2 = Calculation.FV_AMT(n3, n19, n4);
                n20 = n16 * n5;
                n21 = (this.INCREASE_ANNUAL_SAVINGS ? (fv_AMT2 * n6) : n18);
                n22 = 0.0;
                fv_AMT = 0.0;
                n17 = fv_AMT2;
            }
            else {
                final double fv_AMT3 = Calculation.FV_AMT(n7, n19 - n12, n17);
                n20 = n16 * n8;
                n21 = 0.0;
                final double n23 = fv_AMT3 * n9;
                double fv_AMT4;
                if (n19 + n - n13 >= 0.0 && n10 > 0.0) {
                    fv_AMT4 = Calculation.FV_AMT(n11, n19 + n - n13, n14);
                }
                else {
                    fv_AMT4 = 0.0;
                }
                for (int i = 0; i < this.OTHER_PENSION_AMOUNT.length; ++i) {
                    if (this.OTHER_PENSION_AMOUNT_ADJUSTED[i] > 0.0 && n19 + n - this.OTHER_PENSION_START[i] >= 0.0) {
                        if (this.OTHER_PENSION_INFLATION[i]) {
                            fv_AMT = Calculation.FV_AMT(n7, n19, this.OTHER_PENSION_AMOUNT_ADJUSTED[i] * 12.0);
                        }
                        else {
                            fv_AMT = this.OTHER_PENSION_AMOUNT_ADJUSTED[i] * 12.0;
                        }
                    }
                }
                n22 = n23 - fv_AMT4 - fv_AMT;
                if (this.DEFERRED == 1.0 && n22 > 0.0) {
                    n22 /= 1.0 - this.RETIREMENT_TAX / 100.0;
                }
            }
            n16 = n16 + n20 + n21 - n22;
            if (n16 < 0.0) {
                n15 = n19 + (int)this.CURRENT_AGE;
                break;
            }
        }
        if (b) {
            return n15;
        }
        return n16;
    }
}
