// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.foundations;

import blackmagic.swing.ComponentLabelPair;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JComponent;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import blackmagic.swing.JFormattedDateField;
import blackmagic.swing.JFormattedSelectField;
import blackmagic.swing.JFormattedNumField;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class ModelBoundPanel extends JPanel
{
    protected CalculatorModel vCalculatorModel;
    protected GridBagLayout gbl;
    protected GridBagConstraints gbc;
    protected Vector vLabels;
    
    public ModelBoundPanel() {
        this.initialise();
    }
    
    protected void initialise() {
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.setLayout(this.gbl);
    }
    
    public void bindModel(final CalculatorModel vCalculatorModel) {
        this.vCalculatorModel = vCalculatorModel;
    }
    
    protected void bindFieldToModel(final int n, final JFormattedNumField formattedNumField) {
        this.bindFieldToModel(n, formattedNumField, new CalculatorModelActionFocusListener(n, this.vCalculatorModel));
    }
    
    protected void bindFieldToModel(final int n, final JFormattedDateField formattedDateField) {
        this.bindFieldToModel(n, formattedDateField, new CalculatorModelDateActionFocusListener(n, this.vCalculatorModel));
    }
    
    protected void bindFieldToModel(final int n, final JFormattedSelectField formattedSelectField, final CalculatorModelActionFocusListener calculatorModelActionFocusListener) {
        formattedSelectField.addActionListener(calculatorModelActionFocusListener);
        formattedSelectField.addFocusListener(calculatorModelActionFocusListener);
        this.subscribeForUpdates(n, formattedSelectField);
    }
    
    protected void subscribeForUpdates(final int n, final JFormattedSelectField formattedSelectField) {
        if (formattedSelectField instanceof JFormattedNumField) {
            this.vCalculatorModel.subscribe(new NumberFieldCalculatorModelListener(formattedSelectField), n);
        }
        if (formattedSelectField instanceof JFormattedDateField) {
            this.vCalculatorModel.subscribe(new DateFieldCalculatorModelListener(formattedSelectField), n);
        }
    }
    
    protected void addFieldPair(final JLabel label, final JFormattedSelectField formattedSelectField, final int gridx, final int gridy) {
        this.gbc.anchor = 13;
        this.gbc.gridx = gridx;
        this.gbc.gridy = gridy;
        this.add(label, this.gbc);
        this.gbc.anchor = 17;
        this.gbc.gridx = gridx + 1;
        this.add(formattedSelectField, this.gbc);
    }
    
    protected JComponent addDisabledFieldPair(final int n, final String s, final int n2, final int n3) {
        final ComponentLabelPair pair = FieldLabelFactory.getPair(n, s);
        this.vLabels.add(pair.getLabel());
        pair.getComponent().setEnabled(false);
        this.addFieldPair(pair.getLabel(), (JFormattedSelectField)pair.getComponent(), n2, n3);
        return pair.getComponent();
    }
    
    protected JComponent addEnabledFieldPair(final int n, final char c, final String s, final String toolTipText, final int n2, final int n3) {
        final ComponentLabelPair pair = FieldLabelFactory.getPair(n, c, s);
        this.vLabels.add(pair.getLabel());
        pair.getComponent().setToolTipText(toolTipText);
        this.addFieldPair(pair.getLabel(), (JFormattedSelectField)pair.getComponent(), n2, n3);
        return pair.getComponent();
    }
    
    public void start() {
        this.vCalculatorModel.start();
    }
}
