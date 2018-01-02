// 
// Decompiled by Procyon v0.5.30
// 

public class MortgageFunctions
{
    private static final int SIGNIFICANTDIGITS = 7;
    private static final double MONTHLY = 6.0;
    private static final double SEMIMONTHLY = 12.0;
    private static final double BIWEEKLY = 13.028;
    private static final double WEEKLY = 26.053;
    private static final double SNI_MONTHLY;
    private static final double SNI_SEMIMONTHLY;
    private static final double SNI_BIWEEKLY;
    private static final double SNI_WEEKLY;
    public static final int MORTGAGETYPE_MONTHLY = 1;
    public static final int MORTGAGETYPE_SEMIMONTHLY = 2;
    public static final int MORTGAGETYPE_BIWEEKLY = 3;
    public static final int MORTGAGETYPE_WEEKLY = 4;
    public static final int MORTGAGETYPE_VARIABLE = 5;
    public static final int CALCULATEUSING_TERM = 1;
    public static final int CALCULATEUSING_PAYMENT = 2;
    public static final int GRAPHVALUES_LIMIT = 25;
    private double MortgageType;
    private double Principal;
    private double InterestRate;
    private double TermInYears;
    private double AccumulationFactorToUse;
    private double InterestConversionPeriod;
    private double Payment;
    private double OutStandingPrincipal;
    private double PrincipalAmount;
    private double InterestAmount;
    private double TotalInterest;
    private boolean PaymentWillCoverInterest;
    private double[][] GraphValues;
    
    public MortgageFunctions(final int mortgageType, final double principal, final double interestRate, final double termInYears, final double payment, final int calculateUsing) {
        this.PaymentWillCoverInterest = true;
        this.GraphValues = new double[25][2];
        this.MortgageType = mortgageType;
        this.Principal = principal;
        this.InterestRate = interestRate;
        this.TermInYears = termInYears;
        this.Payment = payment;
        this.OutStandingPrincipal = this.Principal;
        switch (mortgageType) {
            case 1: {
                this.AccumulationFactorToUse = MortgageFunctions.SNI_MONTHLY;
                this.InterestConversionPeriod = 6.0;
                break;
            }
            case 2: {
                this.AccumulationFactorToUse = MortgageFunctions.SNI_SEMIMONTHLY;
                this.InterestConversionPeriod = 12.0;
                break;
            }
            case 3: {
                this.AccumulationFactorToUse = MortgageFunctions.SNI_BIWEEKLY;
                this.InterestConversionPeriod = 13.028;
                break;
            }
            case 4: {
                this.AccumulationFactorToUse = MortgageFunctions.SNI_WEEKLY;
                this.InterestConversionPeriod = 26.053;
                break;
            }
            default: {
                this.AccumulationFactorToUse = MortgageFunctions.SNI_MONTHLY;
                this.InterestConversionPeriod = 6.0;
                break;
            }
        }
        final double interestRatePerConversionPeriod = Round(this.InterestRate / 2.0, 7);
        if (calculateUsing == 2) {
            this.TermInYears = this.CalculateAmortizationPeriodRemaining(this.Principal, this.Payment);
            if (!this.PaymentWillCoverInterest) {
                return;
            }
        }
        final double numberOfInterestConversionPeriods = Round(this.TermInYears * 2.0, 7);
        final double discountFactor = this.CalculateDiscountFactor(interestRatePerConversionPeriod, numberOfInterestConversionPeriods);
        final double accumulationFactor = this.CalculateAccumulationFactor(interestRatePerConversionPeriod, this.AccumulationFactorToUse);
        this.Payment = Round(this.Principal / discountFactor, 7) * accumulationFactor;
        this.TotalInterest = 0.0;
        final int paymentsInAYear = (int)Math.round(this.InterestConversionPeriod * 2.0);
        int payments = 0;
        int graphIndex = 0;
        while (this.OutStandingPrincipal > 0.0) {
            this.ApplyPayment();
            this.TotalInterest += this.InterestAmount;
            if (++payments == paymentsInAYear) {
                this.GraphValues[graphIndex][0] = this.PrincipalAmount;
                this.GraphValues[graphIndex][1] = this.InterestAmount;
                ++graphIndex;
                payments = 0;
            }
        }
        if (graphIndex < 24) {
            while (graphIndex < 25) {
                this.GraphValues[graphIndex][0] = 0.0;
                this.GraphValues[graphIndex][1] = 0.0;
                ++graphIndex;
            }
        }
        this.OutStandingPrincipal = this.Principal;
    }
    
    public double getTotalInterest() {
        return this.TotalInterest;
    }
    
    public double getTerm() {
        return this.TermInYears;
    }
    
    private double getInterestFactor() {
        return Round(Math.pow(1.0 + this.InterestRate / 2.0 / 100.0, this.AccumulationFactorToUse) - 1.0, 7);
    }
    
    public double[][] getGraphValues() {
        return this.GraphValues;
    }
    
    public static double Round(final double number, int significantDigits) {
        int factor = 1;
        while (significantDigits > 0) {
            factor *= 10;
            --significantDigits;
        }
        final long rounded = Math.round(number * factor);
        final double result = rounded / factor;
        return result;
    }
    
    private double CalculateAccumulationFactor(final double interestRatePerConversionPeriod, final double accumulationFactorToUse) {
        final double interestRate = Round(interestRatePerConversionPeriod / 100.0, 7);
        return Round((Math.pow(1.0 + interestRate, accumulationFactorToUse) - 1.0) / interestRate, 7);
    }
    
    private double CalculateDiscountFactor(final double interestRatePerConversionPeriod, final double numberOfInterestConversionPeriods) {
        final double interestRate = Round(interestRatePerConversionPeriod / 100.0, 7);
        return Round((1.0 - Math.pow(1.0 + interestRate, -numberOfInterestConversionPeriods)) / interestRate, 7);
    }
    
    static {
        SNI_MONTHLY = Round(0.16666666666666666, 7);
        SNI_SEMIMONTHLY = Round(0.08333333333333333, 7);
        SNI_BIWEEKLY = Round(0.07675775253300583, 7);
        SNI_WEEKLY = Round(0.038383295589759336, 7);
    }
    
    public double ApplyPayment() {
        final double interestFactor = this.getInterestFactor();
        if (this.Payment - Round(this.OutStandingPrincipal * interestFactor, 2) <= 0.01) {
            this.PaymentWillCoverInterest = false;
            return -1.0;
        }
        this.InterestAmount = Round(this.OutStandingPrincipal * interestFactor, 2);
        this.PrincipalAmount = this.Payment - this.InterestAmount;
        return this.OutStandingPrincipal -= this.PrincipalAmount;
    }
    
    public double CalculateAmortizationPeriodRemaining(double remainingMortgage, final double payment) {
        int numberOfInterestConversionPeriods = 0;
        final double interestFactor = this.getInterestFactor();
        if (payment - Round(remainingMortgage * interestFactor, 2) <= 0.01) {
            this.PaymentWillCoverInterest = false;
            return -1.0;
        }
        while (remainingMortgage > 0.0) {
            this.InterestAmount = Round(remainingMortgage * interestFactor, 2);
            this.PrincipalAmount = payment - this.InterestAmount;
            remainingMortgage -= this.PrincipalAmount;
            ++numberOfInterestConversionPeriods;
        }
        return Round(numberOfInterestConversionPeriods / (this.InterestConversionPeriod * 2.0), 2);
    }
    
    public boolean getWillPaymentCoverInterest() {
        return this.PaymentWillCoverInterest;
    }
    
    public double getPayment() {
        return this.Payment;
    }
}
