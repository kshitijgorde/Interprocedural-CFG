// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.JLabel;
import java.util.List;
import blackmagic.swing.JUtilities;
import javax.swing.JComboBox;
import java.util.Vector;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

class ConfigPanel extends JPanel
{
    private CompoundModel vCompoundModel;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    
    public ConfigPanel(final CompoundModel vCompoundModel) {
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.setLayout(this.gbl);
        this.vCompoundModel = vCompoundModel;
        this.buildContent();
    }
    
    private void buildContent() {
        int n = -1;
        final Vector<JComboBox> vector = new Vector<JComboBox>();
        vector.add(this.addFieldPair(0, 'I', ++n));
        vector.add(this.addFieldPair(1, 'A', ++n));
        vector.add(this.addFieldPair(2, 'P', ++n));
        vector.add(this.addFieldPair(3, 'Y', ++n));
        vector.add(this.addFieldPair(4, 'R', ++n));
        vector.add(this.addFieldPair(5, 'C', ++n));
        JUtilities.equalizeComponentSizes(vector);
        vector.clear();
    }
    
    private JComboBox addFieldPair(final int n, final char displayedMnemonic, final int n2) {
        final String variableLabel = CompoundModel.getVariableLabel(n);
        final JLabel label = new JLabel(variableLabel + ":");
        label.setDisplayedMnemonic(displayedMnemonic);
        this.gbc.insets = new Insets(5, 0, 0, 0);
        this.gbc.anchor = 13;
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.gridx = 0;
        this.gbc.weightx = 1.0;
        this.gbc.gridy = n2;
        this.add(label, this.gbc);
        final JComboBox labelFor = new JComboBox();
        labelFor.setToolTipText(" Selects which value to alter when changes are made to " + variableLabel);
        this.addOptions(n, labelFor);
        labelFor.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ConfigPanel.this.vCompoundModel.setVariableToCalculator(n, (String)((JComboBox)actionEvent.getSource()).getSelectedItem());
            }
        });
        this.gbc.insets = new Insets(5, 12, 0, 0);
        this.gbc.gridwidth = 1;
        this.gbc.gridheight = 1;
        this.gbc.gridx = 1;
        this.gbc.gridy = n2;
        this.gbc.anchor = 17;
        this.add(labelFor, this.gbc);
        label.setLabelFor(labelFor);
        return labelFor;
    }
    
    private void addOptions(final int n, final JComboBox comboBox) {
        this.addOption(0, n, comboBox);
        this.addOption(1, n, comboBox);
        this.addOption(2, n, comboBox);
        this.addOption(3, n, comboBox);
        this.addOption(4, n, comboBox);
        this.addOption(5, n, comboBox);
    }
    
    private void addOption(final int n, final int n2, final JComboBox comboBox) {
        if (!CompoundModel.variableHasCalculator(n)) {
            return;
        }
        if (n2 == n) {
            return;
        }
        final String variableLabel = CompoundModel.getVariableLabel(n);
        comboBox.addItem(variableLabel);
        if (this.vCompoundModel.getVariableCalculator(n2) == n) {
            comboBox.setSelectedItem(variableLabel);
        }
    }
}
