// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import blackmagic.finance.investment.calculators.foundations.NumberFieldCalculatorModelListener;
import blackmagic.finance.investment.calculators.foundations.CalculatorModelActionFocusListener;
import blackmagic.swing.JFormattedSelectField;
import blackmagic.finance.investment.calculators.foundations.CalculatorModelListener;
import javax.swing.JComponent;
import blackmagic.finance.investment.calculators.foundations.CalculatorModel;
import java.awt.Container;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import blackmagic.swing.JFormattedNumField;
import java.util.LinkedList;

public final class BasicCompoundForm extends CompoundForm
{
    public static final int INITIALISED = 0;
    public static final int DIRTY = 1;
    private String vInitialModelSignature;
    private String vRunningState;
    private int vState;
    private LinkedList[] vStateListeners;
    private JFormattedNumField vCompoundedField;
    private JPopupMenu vPopupMenu;
    private JDialog vConfigDialog;
    private JLabel vInitialPrincipleLabel;
    private JLabel vAnnualIntRateLabel;
    private JLabel vPaymentsPerYearLabel;
    private JLabel vYearsToCompoundLabel;
    private JLabel vAnnualTaxRateLabel;
    private JLabel vCompoundedAmountLabel;
    static /* synthetic */ Class class$blackmagic$finance$investment$calculators$compound$BasicCompoundForm;
    
    public BasicCompoundForm(final String s) {
        super(s);
        this.vRunningState = "Stopped";
        this.vStateListeners = new LinkedList[2];
        if (this.vInitialModelSignature == null) {
            this.vInitialModelSignature = this.getModel().getVariableSignature();
        }
        this.vPopupMenu = BasicCompounderPopupFactory.getBasicCompounderPopup(this);
        this.vConfigDialog = CompoundFormConfigFactory.getConfigDialog(this);
    }
    
    public void start() {
        if (this.vRunningState.equals("Running")) {
            return;
        }
        this.vRunningState = "Running";
        this.getModel().start();
        this.publishState();
    }
    
    public void stop() {
        this.vRunningState = "Stopped";
        this.getModel().stop();
    }
    
    public String getRunningState() {
        return this.vRunningState;
    }
    
    public void reset() {
        this.stop();
        this.getModel().reset();
        this.vCompoundedField.setLowerBound(0.0);
        this.start();
    }
    
    public void configure() {
        final Container topLevelAncestor = this.getTopLevelAncestor();
        if (this.vConfigDialog.getTitle().equals("")) {
            String title = "Configure " + this.getTitle();
            if (topLevelAncestor instanceof JFrame) {
                title = title + " - " + ((JFrame)topLevelAncestor).getTitle();
            }
            this.vConfigDialog.setTitle(title);
            this.vConfigDialog.setLocationRelativeTo(topLevelAncestor);
        }
        this.vConfigDialog.setVisible(true);
    }
    
    private void publishState() {
        if (this.getModel().getVariableSignature().equals(this.vInitialModelSignature)) {
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
    
    public void subscribeState(final BasicCompoundFormListener basicCompoundFormListener) {
        this.subscribeState(basicCompoundFormListener, 0);
        this.subscribeState(basicCompoundFormListener, 1);
    }
    
    public void subscribeState(final BasicCompoundFormListener basicCompoundFormListener, final int n) {
        assert basicCompoundFormListener != null : "pListener is null";
        if (this.vStateListeners[n] == null) {
            this.vStateListeners[n] = new LinkedList();
        }
        this.vStateListeners[n].add(basicCompoundFormListener);
    }
    
    public JPopupMenu getPopupMenu() {
        return this.vPopupMenu;
    }
    
    protected void addFields() {
        super.bindModel(new CompoundModel());
        super.addFields();
    }
    
    public void setInitialPrincipleAccelerator(final char displayedMnemonic) {
        this.vInitialPrincipleLabel.setDisplayedMnemonic(displayedMnemonic);
    }
    
    public void setAnnualIntRateAccelerator(final char displayedMnemonic) {
        this.vAnnualIntRateLabel.setDisplayedMnemonic(displayedMnemonic);
    }
    
    public void setPaymentsPerYearAccelerator(final char displayedMnemonic) {
        this.vPaymentsPerYearLabel.setDisplayedMnemonic(displayedMnemonic);
    }
    
    public void setYearsToCompoundAccelerator(final char displayedMnemonic) {
        this.vYearsToCompoundLabel.setDisplayedMnemonic(displayedMnemonic);
    }
    
    public void setAnnualTaxRateAccelerator(final char displayedMnemonic) {
        this.vAnnualTaxRateLabel.setDisplayedMnemonic(displayedMnemonic);
    }
    
    public void setCompoundedAmountAccelerator(final char displayedMnemonic) {
        this.vCompoundedAmountLabel.setDisplayedMnemonic(displayedMnemonic);
    }
    
    protected JLabel addInitialPrincipleField() {
        final JLabel addInitialPrincipleField = super.addInitialPrincipleField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addInitialPrincipleField.getLabelFor();
        formattedNumField.setToolTipText(" Enter a positive dollar amount. ");
        formattedNumField.setBounds(0.0, Double.MAX_VALUE);
        this.bindFieldToModel(0, formattedNumField);
        return this.vInitialPrincipleLabel = addInitialPrincipleField;
    }
    
    protected JLabel addAnnualIntRateField() {
        final JLabel addAnnualIntRateField = super.addAnnualIntRateField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addAnnualIntRateField.getLabelFor();
        formattedNumField.setToolTipText(" Enter a positive interest rate. ");
        formattedNumField.setBounds(0.0, Double.MAX_VALUE);
        this.bindFieldToModel(1, formattedNumField);
        return this.vAnnualIntRateLabel = addAnnualIntRateField;
    }
    
    protected JLabel addPaymentsPerYearField() {
        final JLabel addPaymentsPerYearField = super.addPaymentsPerYearField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addPaymentsPerYearField.getLabelFor();
        formattedNumField.setToolTipText(" Enter the number of payments per year (1-366). ");
        formattedNumField.setBounds(1.0, 366.0);
        this.bindFieldToModel(2, formattedNumField);
        return this.vPaymentsPerYearLabel = addPaymentsPerYearField;
    }
    
    protected JLabel addYearsToCompoundField() {
        final JLabel addYearsToCompoundField = super.addYearsToCompoundField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addYearsToCompoundField.getLabelFor();
        formattedNumField.setToolTipText(" Enter a positive number of years to compound. ");
        formattedNumField.setBounds(0.0, Double.MAX_VALUE);
        this.bindFieldToModel(3, formattedNumField);
        return this.vYearsToCompoundLabel = addYearsToCompoundField;
    }
    
    protected JLabel addAnnualTaxRateField() {
        final JLabel addAnnualTaxRateField = super.addAnnualTaxRateField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addAnnualTaxRateField.getLabelFor();
        formattedNumField.setToolTipText(" Enter a tax rate between 0 and 100%. ");
        formattedNumField.setBounds(0.0, 1.0);
        this.bindFieldToModel(4, formattedNumField);
        return this.vAnnualTaxRateLabel = addAnnualTaxRateField;
    }
    
    protected JLabel addCompoundedAmountField() {
        final JLabel addCompoundedAmountField = super.addCompoundedAmountField();
        final JFormattedNumField vCompoundedField = (JFormattedNumField)addCompoundedAmountField.getLabelFor();
        vCompoundedField.setToolTipText(" Enter a compound amount greater than the Initial Principle. ");
        vCompoundedField.setBounds(0.0, Double.MAX_VALUE);
        this.bindFieldToModel(5, vCompoundedField);
        this.vCompoundedField = vCompoundedField;
        return this.vCompoundedAmountLabel = addCompoundedAmountField;
    }
    
    protected JLabel addGrowthField() {
        final JLabel addGrowthField = super.addGrowthField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addGrowthField.getLabelFor();
        final GrowthFieldListener growthFieldListener = new GrowthFieldListener(formattedNumField);
        formattedNumField.setEnabled(false);
        this.subscribeForUpdates(0, growthFieldListener);
        this.subscribeForUpdates(5, growthFieldListener);
        return addGrowthField;
    }
    
    protected JLabel addGrowthPercentField() {
        final JLabel addGrowthPercentField = super.addGrowthPercentField();
        final JFormattedNumField formattedNumField = (JFormattedNumField)addGrowthPercentField.getLabelFor();
        final GrowthPercentFieldListener growthPercentFieldListener = new GrowthPercentFieldListener(formattedNumField);
        formattedNumField.setEnabled(false);
        this.subscribeForUpdates(0, growthPercentFieldListener);
        this.subscribeForUpdates(5, growthPercentFieldListener);
        return addGrowthPercentField;
    }
    
    private void subscribeForUpdates(final int n, final CalculatorModelListener calculatorModelListener) {
        this.getModel().subscribe(calculatorModelListener, n);
    }
    
    public CompoundModel getModel() {
        return (CompoundModel)this.vCalculatorModel;
    }
    
    public void synchroniseWith(final BasicCompoundForm basicCompoundForm) {
        this.getModel().synchroniseWith(basicCompoundForm.getModel());
        this.publishState();
    }
    
    protected void bindFieldToModel(final int n, final JFormattedNumField formattedNumField) {
        this.bindFieldToModel(n, formattedNumField, new FormModelListener(n, this.getModel()));
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
        $assertionsDisabled = !((BasicCompoundForm.class$blackmagic$finance$investment$calculators$compound$BasicCompoundForm == null) ? (BasicCompoundForm.class$blackmagic$finance$investment$calculators$compound$BasicCompoundForm = class$("blackmagic.finance.investment.calculators.compound.BasicCompoundForm")) : BasicCompoundForm.class$blackmagic$finance$investment$calculators$compound$BasicCompoundForm).desiredAssertionStatus();
    }
    
    class FormModelListener extends CalculatorModelActionFocusListener
    {
        public FormModelListener(final int n, final CalculatorModel calculatorModel) {
            super(n, calculatorModel);
        }
        
        protected void processChange(final JFormattedNumField formattedNumField) {
            super.processChange(formattedNumField);
            if (this.vModelVariable == 0) {
                BasicCompoundForm.this.vCompoundedField.setLowerBound(formattedNumField.getDouble());
            }
            ((BasicCompoundForm)formattedNumField.getParent()).publishState();
        }
    }
    
    class GrowthPercentFieldListener extends GrowthFieldListener
    {
        public GrowthPercentFieldListener(final JComponent component) {
            super(component);
        }
        
        protected void calcVariables(final CalculatorModel calculatorModel, final int n, final double n2) {
            super.calcVariables(calculatorModel, n, n2);
            this.vGrowth = ((this.vInitialPrinciple > 0.0) ? (this.vGrowth / this.vInitialPrinciple) : 0.0);
        }
    }
    
    class GrowthFieldListener extends NumberFieldCalculatorModelListener
    {
        protected double vInitialPrinciple;
        protected double vCompoundedAmount;
        protected double vGrowth;
        
        public GrowthFieldListener(final JComponent component) {
            super(component);
            this.vInitialPrinciple = 0.0;
            this.vCompoundedAmount = 0.0;
            this.vGrowth = 0.0;
        }
        
        protected void calcVariables(final CalculatorModel calculatorModel, final int n, final double n2) {
            if (n == 0) {
                this.vInitialPrinciple = n2;
            }
            else if (n == 5) {
                this.vCompoundedAmount = n2;
            }
            this.vGrowth = this.vCompoundedAmount - this.vInitialPrinciple;
        }
        
        public void updateComponent(final CalculatorModel calculatorModel, final int n, final Object o) {
            this.calcVariables(calculatorModel, n, (double)o);
            this.vField.setDouble(this.vGrowth);
        }
    }
}
