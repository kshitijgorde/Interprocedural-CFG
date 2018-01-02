// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

public abstract class CalculatorModel
{
    private int vStatus;
    private static final int RUNNING = -1;
    private static final int STOPPED = -2;
    protected static final int PING_VARIABLE = 0;
    public static int VARIABLE_NUM;
    protected CalculatorModelVariable[] vVariables;
    static /* synthetic */ Class class$blackmagic$finance$investment$calculators$foundations$CalculatorModel;
    
    public CalculatorModel(final int variable_NUM) {
        this.stop();
        CalculatorModel.VARIABLE_NUM = variable_NUM;
        this.vVariables = new CalculatorModelVariable[CalculatorModel.VARIABLE_NUM];
        this.initialise();
        this.addPrecisionEntries();
        this.setUpdateableVariables();
    }
    
    protected abstract void setUpdateableVariables();
    
    protected abstract void addPrecisionEntries();
    
    public void initialise() {
        for (int i = 0; i < CalculatorModel.VARIABLE_NUM; ++i) {
            (this.vVariables[i] = new CalculatorModelVariable(this, i)).setValue(new Double(0.0));
        }
    }
    
    public void start() {
        this.vStatus = -1;
        for (int i = 0; i < CalculatorModel.VARIABLE_NUM; ++i) {
            this.publish(i);
        }
    }
    
    public void stop() {
        this.vStatus = -2;
    }
    
    public void reset() {
        this.stop();
        this.initialise();
        this.start();
    }
    
    public Object getVariable(final int n) {
        return this.vVariables[n].getValue();
    }
    
    private boolean variableIsUpdateable(final int n) {
        return this.vVariables[n].isUpdatable();
    }
    
    public void updateVariable(final int n, final Object o) {
        if (!this.variableIsUpdateable(n)) {
            return;
        }
        this.publish(n, o);
        this.reCalculate(n);
    }
    
    public void updateVariable(final int n, final double n2) {
        if (!this.variableIsUpdateable(n)) {
            return;
        }
        this.roundAndPublishVariable(n, n2);
        this.reCalculate(n);
    }
    
    protected void roundAndPublishVariable(final int n, final double n2) {
        this.setDouble(n, n2);
        this.publish(n);
    }
    
    public void subscribe(final CalculatorModelListener calculatorModelListener, final int n) {
        assert calculatorModelListener != null : "pListener is null";
        this.vVariables[n].addListener(calculatorModelListener);
    }
    
    public void publish(final int n, final Object value) {
        this.vVariables[n].setValue(value);
        this.publish(n);
    }
    
    public void publish(final int n) {
        if (this.vStatus == -2) {
            return;
        }
        this.vVariables[n].publish();
    }
    
    protected abstract void reCalculate(final int p0);
    
    public void setPrecision(final int n, final int precisionValue) {
        if (!(this.vVariables[n] instanceof DoubleCalculatorModelVariable)) {
            return;
        }
        ((DoubleCalculatorModelVariable)this.vVariables[n]).setPrecisionValue(precisionValue);
    }
    
    public void setDouble(final int n, final double double1) {
        if (!(this.vVariables[n] instanceof DoubleCalculatorModelVariable)) {
            return;
        }
        ((DoubleCalculatorModelVariable)this.vVariables[n]).setDouble(double1);
    }
    
    public double getDouble(final int n) {
        assert this.vVariables[n] instanceof DoubleCalculatorModelVariable : "Variable is not a Double";
        return ((DoubleCalculatorModelVariable)this.vVariables[n]).getDouble();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        $assertionsDisabled = !((CalculatorModel.class$blackmagic$finance$investment$calculators$foundations$CalculatorModel == null) ? (CalculatorModel.class$blackmagic$finance$investment$calculators$foundations$CalculatorModel = class$("blackmagic.finance.investment.calculators.foundations.CalculatorModel")) : CalculatorModel.class$blackmagic$finance$investment$calculators$foundations$CalculatorModel).desiredAssertionStatus();
        CalculatorModel.VARIABLE_NUM = 1;
    }
}
