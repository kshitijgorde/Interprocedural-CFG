// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import blackmagic.finance.math.CompoundCalculators;
import blackmagic.finance.investment.calculators.foundations.DoubleCalculatorModelVariable;
import blackmagic.finance.investment.calculators.foundations.CalculatorModel;

public class CompoundModel extends CalculatorModel
{
    public static final int INITIAL_PRINCIPAL = 0;
    public static final int ANNUAL_INT_RATE = 1;
    public static final int PAYMENTS_PER_YEAR = 2;
    public static final int YEARS_TO_COMPOUND = 3;
    public static final int ANNUAL_TAX_RATE = 4;
    public static final int COMPOUNDED_AMOUNT = 5;
    public static final int VARIABLE_NUM = 6;
    private static final double[] vVariableCalculators;
    private int[] vVariableCalculatorMap;
    
    public CompoundModel() {
        super(6);
    }
    
    protected void setUpdateableVariables() {
        this.vVariables[0].setUpdatable(true);
        this.vVariables[1].setUpdatable(true);
        this.vVariables[2].setUpdatable(true);
        this.vVariables[3].setUpdatable(true);
        this.vVariables[4].setUpdatable(true);
        this.vVariables[5].setUpdatable(true);
    }
    
    protected void addPrecisionEntries() {
        this.setPrecision(2, 1);
        this.setPrecision(3, 1);
        this.setPrecision(0, 100);
        this.setPrecision(1, 10000);
        this.setPrecision(4, 10000);
    }
    
    public void reset() {
        this.stop();
        this.initialiseVariables();
        this.start();
    }
    
    public void initialise() {
        for (int i = 0; i < 6; ++i) {
            this.vVariables[i] = new DoubleCalculatorModelVariable(this, i);
        }
        this.vVariableCalculatorMap = new int[6];
        this.initialiseVariables();
        this.initialiseMap();
    }
    
    private void initialiseVariables() {
        this.setDouble(0, 0.0);
        this.setDouble(1, 0.0);
        this.setDouble(2, 1.0);
        this.setDouble(3, 1.0);
        this.setDouble(4, 0.0);
        this.setDouble(5, 0.0);
    }
    
    private void initialiseMap() {
        for (int i = 0; i < 6; ++i) {
            switch (i) {
                case 5: {
                    this.vVariableCalculatorMap[i] = 0;
                    break;
                }
                default: {
                    this.vVariableCalculatorMap[i] = 5;
                    break;
                }
            }
        }
    }
    
    protected void synchroniseWith(final CompoundModel compoundModel) {
        this.stop();
        for (int i = 0; i <= 5; ++i) {
            this.setDouble(i, (double)compoundModel.getVariable(i));
        }
        this.start();
    }
    
    protected void reCalculate(final int n) {
        final int n2 = this.vVariableCalculatorMap[n];
        double n3 = 0.0;
        switch (n2) {
            case 0: {
                n3 = CompoundCalculators.InitialPrinciple(this.getDouble(1), (int)this.getDouble(2), (int)this.getDouble(3), this.getDouble(4), this.getDouble(5));
                break;
            }
            case 3: {
                n3 = CompoundCalculators.YearsToCompound(this.getDouble(0), this.getDouble(1), (int)this.getDouble(2), this.getDouble(4), this.getDouble(5));
                break;
            }
            case 1: {
                n3 = CompoundCalculators.AnnualInterestRate(this.getDouble(0), (int)this.getDouble(2), (int)this.getDouble(3), this.getDouble(4), this.getDouble(5));
                break;
            }
            default: {
                n3 = CompoundCalculators.CompoundedAmount(this.getDouble(0), this.getDouble(1), (int)this.getDouble(2), (int)this.getDouble(3), this.getDouble(4));
                break;
            }
        }
        this.roundAndPublishVariable(n2, n3);
        this.setDouble(5, CompoundCalculators.CompoundedAmount(this.getDouble(0), this.getDouble(1), (int)this.getDouble(2), (int)this.getDouble(3), this.getDouble(4)));
        this.publish(5);
    }
    
    public String getVariableSignature() {
        String string = "";
        for (int i = 0; i < 6; ++i) {
            string += this.getDouble(i);
        }
        return string;
    }
    
    public static boolean variableHasCalculator(final int n) {
        for (int i = 0; i < CompoundModel.vVariableCalculators.length; ++i) {
            if (CompoundModel.vVariableCalculators[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    public void setVariableToCalculator(final int n, final String s) {
        int n2 = -1;
        for (int i = 0; i < 6; ++i) {
            if (s.equals(getVariableLabel(i))) {
                n2 = i;
            }
        }
        this.setVariableToCalculator(n, n2);
    }
    
    public void setVariableToCalculator(final int n, final int n2) {
        if (!variableHasCalculator(n2)) {
            return;
        }
        if (n == n2) {
            return;
        }
        this.vVariableCalculatorMap[n] = n2;
    }
    
    public int getVariableCalculator(final int n) {
        return this.vVariableCalculatorMap[n];
    }
    
    public static String getVariableLabel(final int n) {
        String s = "";
        switch (n) {
            case 0: {
                s = "Initial Principal";
                break;
            }
            case 1: {
                s = "Annual Interest Rate";
                break;
            }
            case 2: {
                s = "Payments Per Year";
                break;
            }
            case 3: {
                s = "Years To Compound";
                break;
            }
            case 4: {
                s = "Annual Tax Rate";
                break;
            }
            case 5: {
                s = "Compounded Amount";
                break;
            }
        }
        return s;
    }
    
    static {
        vVariableCalculators = new double[] { 0.0, 1.0, 3.0, 5.0 };
    }
}
