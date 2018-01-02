import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.text.NumberFormat;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.TextField;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RetirementCalculator extends Applet
{
    private static final int WEALTH = 0;
    private static final int INTEREST = 1;
    private static final int EXPENSES = 2;
    private static final int DEATH = 3;
    private static final int INIT_WEALTH = 100000;
    private static final int INIT_INTEREST = 5;
    private static final int INIT_DEATH = 25;
    private static final int INIT_EXPENSES;
    private int dependantVariable;
    
    public RetirementCalculator() {
        this.dependantVariable = 2;
        final TextField textField = new TextField("$100000", 10);
        final TextField textField2 = new TextField("5.00%", 10);
        final TextField textField3 = new TextField("$" + RetirementCalculator.INIT_EXPENSES / 12, 10);
        final TextField textField4 = new TextField("25", 10);
        final FloatSlider floatSlider = new FloatSlider(0, 100000.0f, 10000.0f, 5000000.0f, true);
        final FloatSlider floatSlider2 = new FloatSlider(0, 5.0f, 0.0f, 50.0f, false);
        final FloatSlider floatSlider3 = new FloatSlider(0, RetirementCalculator.INIT_EXPENSES, 10.0f, 240000.0f, true);
        final FloatSlider floatSlider4 = new FloatSlider(0, 25.0f, 0.0f, 100.0f, false);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        final Checkbox checkbox = new Checkbox("Wealth", false, checkboxGroup);
        final Checkbox checkbox2 = new Checkbox("Interest", false, checkboxGroup);
        final Checkbox checkbox3 = new Checkbox("Expenses", true, checkboxGroup);
        final Checkbox checkbox4 = new Checkbox("Death In", false, checkboxGroup);
        textField.setEnabled(false);
        textField2.setEnabled(false);
        textField3.setEnabled(false);
        textField4.setEnabled(false);
        final ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(final ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == 2) {
                    return;
                }
                final Checkbox selectedCheckbox = checkboxGroup.getSelectedCheckbox();
                if (selectedCheckbox == checkbox) {
                    RetirementCalculator.this.dependantVariable = 0;
                }
                else if (selectedCheckbox == checkbox2) {
                    RetirementCalculator.this.dependantVariable = 1;
                }
                else if (selectedCheckbox == checkbox3) {
                    RetirementCalculator.this.dependantVariable = 2;
                }
                else if (selectedCheckbox == checkbox4) {
                    RetirementCalculator.this.dependantVariable = 3;
                }
            }
        };
        checkbox.addItemListener(itemListener);
        checkbox2.addItemListener(itemListener);
        checkbox3.addItemListener(itemListener);
        checkbox4.addItemListener(itemListener);
        final NumberFormat instance = NumberFormat.getInstance();
        final NumberFormat instance2 = NumberFormat.getInstance();
        final NumberFormat instance3 = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(0);
        instance2.setMaximumFractionDigits(1);
        instance2.setMaximumFractionDigits(1);
        instance3.setMaximumFractionDigits(2);
        instance3.setMinimumFractionDigits(2);
        final AdjustmentListener adjustmentListener = new AdjustmentListener() {
            public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
                this.calc();
            }
            
            public void calc() {
                final double n = floatSlider.getFloatValue();
                final double n2 = floatSlider2.getFloatValue();
                final double n3 = floatSlider3.getFloatValue();
                final double n4 = floatSlider4.getFloatValue();
                if (RetirementCalculator.this.dependantVariable == 0) {
                    floatSlider.setFloatValue(solveForWealth(n2, n4, n3));
                }
                else if (RetirementCalculator.this.dependantVariable == 1) {
                    floatSlider2.setFloatValue(solveForInterest(n, n4, n3));
                }
                else if (RetirementCalculator.this.dependantVariable == 2) {
                    floatSlider3.setFloatValue(solveForExpenses(n, n2, n4));
                }
                else if (RetirementCalculator.this.dependantVariable == 3) {
                    floatSlider4.setFloatValue(solveForDeath(n, n2, n3));
                }
                textField.setText("$" + instance.format(floatSlider.getFloatValue()));
                textField2.setText(instance3.format(floatSlider2.getFloatValue()) + "%");
                textField3.setText("$" + instance.format(floatSlider3.getFloatValue() / 12.0f));
                textField4.setText(instance2.format(floatSlider4.getFloatValue()));
            }
        };
        floatSlider.addAdjustmentListener(adjustmentListener);
        floatSlider2.addAdjustmentListener(adjustmentListener);
        floatSlider3.addAdjustmentListener(adjustmentListener);
        floatSlider4.addAdjustmentListener(adjustmentListener);
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 100.0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        this.addField(this, checkbox2, gridBagConstraints, 0, 1, 1, 1);
        this.addField(this, checkbox, gridBagConstraints, 0, 0, 1, 1);
        this.addField(this, checkbox3, gridBagConstraints, 0, 2, 1, 1);
        this.addField(this, checkbox4, gridBagConstraints, 0, 3, 1, 1);
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        this.addField(this, textField, gridBagConstraints, 1, 0, 1, 1);
        this.addField(this, textField2, gridBagConstraints, 1, 1, 1, 1);
        this.addField(this, textField3, gridBagConstraints, 1, 2, 1, 1);
        this.addField(this, textField4, gridBagConstraints, 1, 3, 1, 1);
        gridBagConstraints.weightx = 80.0;
        this.addField(this, floatSlider, gridBagConstraints, 2, 0, 1, 1);
        this.addField(this, floatSlider2, gridBagConstraints, 2, 1, 1, 1);
        this.addField(this, floatSlider3, gridBagConstraints, 2, 2, 1, 1);
        this.addField(this, floatSlider4, gridBagConstraints, 2, 3, 1, 1);
        this.setBackground(Color.white);
    }
    
    private static float solveForWealth(final double n, final double n2, final double n3) {
        return (float)(n3 * (1.0 - Math.exp(-n2 * n / 100.0)) / (n / 100.0));
    }
    
    private static float solveForExpenses(final double n, final double n2, final double n3) {
        return (float)(n * n2 / 100.0 / (1.0 - Math.exp(-n3 * n2 / 100.0)));
    }
    
    private static float solveForDeath(final double n, final double n2, final double n3) {
        return (float)(-Math.log(1.0 - n * (n2 / 100.0) / n3) / (n2 / 100.0));
    }
    
    private static float solveForInterest(final double n, final double n2, final double n3) {
        float n4 = 5.0f;
        float n5 = 1.0f;
        float solveForWealth = solveForWealth(n4, n2, n3);
        while (Math.abs(n5) > 1.0E-5f) {
            final float solveForWealth2 = solveForWealth(n4 + n5, n2, n3);
            if (Math.abs(n - solveForWealth2) < Math.abs(n - solveForWealth)) {
                n4 += n5;
                solveForWealth = solveForWealth2;
            }
            else {
                n5 /= -2.0f;
            }
        }
        return n4;
    }
    
    private void addField(final Container container, final Component component, final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight) {
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        this.add(component, gridBagConstraints);
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("Retirement Calculator");
        frame.add(new RetirementCalculator());
        frame.pack();
        frame.setSize(600, frame.getHeight());
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(1);
            }
        });
    }
    
    static {
        INIT_EXPENSES = (int)solveForExpenses(100000.0, 5.0, 25.0);
    }
}
