// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import java.awt.Color;
import blackmagic.finance.investment.calculators.foundations.CalculatorModel;
import blackmagic.finance.investment.calculators.foundations.CalculatorModelListener;
import javax.swing.JComponent;
import blackmagic.swing.JFormattedNumField;
import javax.swing.JLabel;
import java.util.LinkedList;

public final class DiffCompoundForm extends CompoundForm
{
    private CompoundModel vBaseModel;
    private CompoundModel vAlternateModel;
    public static final int SYNCHRONISED = 0;
    public static final int UNSYNCHRONISED = 1;
    private LinkedList[] vStateListeners;
    private int vState;
    private String vRunningState;
    static /* synthetic */ Class class$blackmagic$finance$investment$calculators$compound$DiffCompoundForm;
    
    public DiffCompoundForm(final String s, final CompoundModel vBaseModel, final CompoundModel vAlternateModel) {
        super(s);
        this.vStateListeners = new LinkedList[2];
        this.vRunningState = "Stopped";
        assert vBaseModel != null && vAlternateModel != null : "null CompoundForm passed to DiffCompoundForm constructor";
        this.vBaseModel = vBaseModel;
        this.vAlternateModel = vAlternateModel;
        super.addFields();
        this.setEnabled(false);
    }
    
    public CompoundModel getBaseModel() {
        return this.vBaseModel;
    }
    
    public CompoundModel getAlternateModel() {
        return this.vAlternateModel;
    }
    
    public void start() {
        if (this.vRunningState.equals("Running")) {
            return;
        }
        this.vRunningState = "Running";
        this.publishState();
    }
    
    public void stop() {
        this.vRunningState = "Stopped";
    }
    
    private void publishState() {
        if (this.getBaseModel().getVariableSignature().equals(this.getAlternateModel().getVariableSignature())) {
            this.publishState(0);
        }
        else {
            this.publishState(1);
        }
    }
    
    private void publishState(final int vState) {
        if (this.vRunningState.equals("Stopped")) {
            return;
        }
        if (vState == 1 && this.vState == 1) {
            return;
        }
        this.vState = vState;
        final LinkedList list = this.vStateListeners[vState];
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); ++i) {
            list.get(i).updateComponent(this, vState);
        }
    }
    
    public void subscribeState(final DiffCompoundFormListener diffCompoundFormListener) {
        this.subscribeState(diffCompoundFormListener, 0);
        this.subscribeState(diffCompoundFormListener, 1);
    }
    
    public void subscribeState(final DiffCompoundFormListener diffCompoundFormListener, final int n) {
        assert diffCompoundFormListener != null : "pListener is null";
        if (this.vStateListeners[n] == null) {
            this.vStateListeners[n] = new LinkedList();
        }
        this.vStateListeners[n].add(diffCompoundFormListener);
    }
    
    protected void addFields() {
    }
    
    protected JLabel addInitialPrincipleField() {
        final JLabel addInitialPrincipleField = super.addInitialPrincipleField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addInitialPrincipleField.getLabelFor();
        formattedNumField.setEnabled(false);
        this.setListener(formattedNumField, 0);
        return addInitialPrincipleField;
    }
    
    protected JLabel addAnnualIntRateField() {
        final JLabel addAnnualIntRateField = super.addAnnualIntRateField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addAnnualIntRateField.getLabelFor();
        formattedNumField.setEnabled(false);
        this.setListener(formattedNumField, 1);
        return addAnnualIntRateField;
    }
    
    protected JLabel addPaymentsPerYearField() {
        final JLabel addPaymentsPerYearField = super.addPaymentsPerYearField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addPaymentsPerYearField.getLabelFor();
        formattedNumField.setEnabled(false);
        this.setListener(formattedNumField, 2);
        return addPaymentsPerYearField;
    }
    
    protected JLabel addYearsToCompoundField() {
        final JLabel addYearsToCompoundField = super.addYearsToCompoundField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addYearsToCompoundField.getLabelFor();
        formattedNumField.setEnabled(false);
        this.setListener(formattedNumField, 3);
        return addYearsToCompoundField;
    }
    
    protected JLabel addAnnualTaxRateField() {
        final JLabel addAnnualTaxRateField = super.addAnnualTaxRateField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addAnnualTaxRateField.getLabelFor();
        formattedNumField.setEnabled(false);
        this.setListener(formattedNumField, 4);
        return addAnnualTaxRateField;
    }
    
    protected JLabel addCompoundedAmountField() {
        final JLabel addCompoundedAmountField = super.addCompoundedAmountField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addCompoundedAmountField.getLabelFor();
        formattedNumField.setEnabled(false);
        this.setListener(formattedNumField, 5);
        return addCompoundedAmountField;
    }
    
    protected JLabel addGrowthField() {
        final JLabel addGrowthField = super.addGrowthField();
        final JFormattedNumField growthListener = (JFormattedNumField)addGrowthField.getLabelFor();
        growthListener.setEnabled(false);
        this.setGrowthListener(growthListener);
        return addGrowthField;
    }
    
    protected JLabel addGrowthPercentField() {
        final JLabel addGrowthPercentField = super.addGrowthPercentField();
        final JFormattedNumField growthPercentListener = (JFormattedNumField)addGrowthPercentField.getLabelFor();
        growthPercentListener.setEnabled(false);
        this.setGrowthPercentListener(growthPercentListener);
        return addGrowthPercentField;
    }
    
    private void setListener(final JFormattedNumField formattedNumField, final int n) {
        final DiffListener diffListener = new DiffListener(formattedNumField);
        this.vBaseModel.subscribe(diffListener, n);
        this.vAlternateModel.subscribe(diffListener, n);
    }
    
    private void setGrowthListener(final JFormattedNumField formattedNumField) {
        final GrowthDiffListener growthDiffListener = new GrowthDiffListener(formattedNumField);
        this.vBaseModel.subscribe(growthDiffListener, 0);
        this.vBaseModel.subscribe(growthDiffListener, 5);
        this.vAlternateModel.subscribe(growthDiffListener, 0);
        this.vAlternateModel.subscribe(growthDiffListener, 5);
    }
    
    private void setGrowthPercentListener(final JFormattedNumField formattedNumField) {
        final GrowthPercentDiffListener growthPercentDiffListener = new GrowthPercentDiffListener(formattedNumField);
        this.vBaseModel.subscribe(growthPercentDiffListener, 0);
        this.vBaseModel.subscribe(growthPercentDiffListener, 5);
        this.vAlternateModel.subscribe(growthPercentDiffListener, 0);
        this.vAlternateModel.subscribe(growthPercentDiffListener, 5);
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
        $assertionsDisabled = !((DiffCompoundForm.class$blackmagic$finance$investment$calculators$compound$DiffCompoundForm == null) ? (DiffCompoundForm.class$blackmagic$finance$investment$calculators$compound$DiffCompoundForm = class$("blackmagic.finance.investment.calculators.compound.DiffCompoundForm")) : DiffCompoundForm.class$blackmagic$finance$investment$calculators$compound$DiffCompoundForm).desiredAssertionStatus();
    }
    
    class GrowthPercentDiffListener extends GrowthDiffListener
    {
        public GrowthPercentDiffListener(final JComponent component) {
            super(component);
        }
        
        protected void calculateVariables(final CalculatorModel calculatorModel, final int n, final double n2) {
            super.calculateVariables(calculatorModel, n, n2);
            this.vBaseModelValue = ((this.vBaseModelInitialPrinciple > 0.0) ? (this.vBaseModelValue / this.vBaseModelInitialPrinciple) : 0.0);
            this.vAlternateModelValue = ((this.vAlternateModelInitialPrinciple > 0.0) ? (this.vAlternateModelValue / this.vAlternateModelInitialPrinciple) : 0.0);
        }
    }
    
    class GrowthDiffListener extends DiffListener
    {
        protected double vBaseModelInitialPrinciple;
        protected double vAlternateModelInitialPrinciple;
        protected double vBaseModelCompoundedAmount;
        protected double vAlternateModelCompoundedAmount;
        
        public GrowthDiffListener(final JComponent component) {
            super(component);
        }
        
        protected void calculateVariables(final CalculatorModel calculatorModel, final int n, final double n2) {
            if (calculatorModel == DiffCompoundForm.this.vBaseModel && n == 0) {
                this.vBaseModelInitialPrinciple = n2;
            }
            if (calculatorModel == DiffCompoundForm.this.vBaseModel && n == 5) {
                this.vBaseModelCompoundedAmount = n2;
            }
            if (calculatorModel == DiffCompoundForm.this.vAlternateModel && n == 0) {
                this.vAlternateModelInitialPrinciple = n2;
            }
            if (calculatorModel == DiffCompoundForm.this.vAlternateModel && n == 5) {
                this.vAlternateModelCompoundedAmount = n2;
            }
            this.vBaseModelValue = this.vBaseModelCompoundedAmount - this.vBaseModelInitialPrinciple;
            this.vAlternateModelValue = this.vAlternateModelCompoundedAmount - this.vAlternateModelInitialPrinciple;
        }
        
        public void updateComponent(final CalculatorModel calculatorModel, final int n, final Object o) {
            this.calculateVariables(calculatorModel, n, (double)o);
            this.setValue();
        }
    }
    
    class DiffListener implements CalculatorModelListener
    {
        protected JFormattedNumField vField;
        protected double vBaseModelValue;
        protected double vAlternateModelValue;
        
        public DiffListener(final JComponent component) {
            this.setComponent(component);
        }
        
        public void setComponent(final JComponent component) {
            assert component instanceof JFormattedNumField;
            this.vField = (JFormattedNumField)component;
        }
        
        public void updateComponent(final CalculatorModel calculatorModel, final int n, final Object o) {
            final Double n2 = (Double)o;
            if (calculatorModel == DiffCompoundForm.this.vBaseModel) {
                this.vBaseModelValue = n2;
            }
            else {
                this.vAlternateModelValue = n2;
            }
            this.setValue();
            ((DiffCompoundForm)this.vField.getParent()).publishState();
        }
        
        protected void setValue() {
            if (this.vAlternateModelValue < this.vBaseModelValue || this.vAlternateModelValue > this.vBaseModelValue) {
                this.vField.setDisabledTextColor(Color.BLACK);
            }
            else {
                this.vField.setDisabledTextColor(this.vField.getBackground());
            }
            this.vField.setDouble(Math.abs(this.vAlternateModelValue - this.vBaseModelValue));
        }
        
        static {
            $assertionsDisabled = !((DiffCompoundForm.class$blackmagic$finance$investment$calculators$compound$DiffCompoundForm == null) ? (DiffCompoundForm.class$blackmagic$finance$investment$calculators$compound$DiffCompoundForm = DiffCompoundForm.class$("blackmagic.finance.investment.calculators.compound.DiffCompoundForm")) : DiffCompoundForm.class$blackmagic$finance$investment$calculators$compound$DiffCompoundForm).desiredAssertionStatus();
        }
    }
}
