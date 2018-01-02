// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import blackmagic.swing.JFormattedPercentField;
import blackmagic.swing.JFormattedNumField;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import blackmagic.finance.investment.calculators.foundations.ModelBoundPanel;

class CompoundForm extends ModelBoundPanel
{
    private static final String GROWTH = "Growth";
    private static final String GROWTH_PERCENT = "Growth (%)";
    private GridBagLayout gbl;
    private GridBagConstraints gbc;
    private String vTitle;
    
    public CompoundForm() {
        this.initialise("");
    }
    
    public CompoundForm(final String s) {
        this.initialise(s);
    }
    
    protected void initialise(final String title) {
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.gbc.insets = new Insets(2, 1, 2, 1);
        this.setLayout(this.gbl);
        this.setTitle(title);
        this.addFields();
    }
    
    protected void addFields() {
        this.gbc.gridwidth = 0;
        this.add(Box.createVerticalStrut(0), this.gbc);
        this.addInitialPrincipleField();
        this.addAnnualIntRateField();
        this.addPaymentsPerYearField();
        this.addYearsToCompoundField();
        this.addAnnualTaxRateField();
        this.addCompoundedAmountField();
        this.gbc.gridwidth = 0;
        this.add(Box.createVerticalStrut(6), this.gbc);
        this.addGrowthField();
        this.addGrowthPercentField();
        this.gbc.gridwidth = 0;
        this.add(Box.createVerticalStrut(6), this.gbc);
    }
    
    public void setTitle(final String vTitle) {
        this.vTitle = vTitle;
        this.setBorder(BorderFactory.createTitledBorder(vTitle));
    }
    
    public String getTitle() {
        return this.vTitle;
    }
    
    protected JLabel addInitialPrincipleField() {
        return this.addFieldPair(CompoundModel.getVariableLabel(0), "#,##0.00", 0.0, 10);
    }
    
    protected JLabel addAnnualIntRateField() {
        return this.addFieldPair(CompoundModel.getVariableLabel(1) + " (%)", "#0.00%", 0.0, 6);
    }
    
    protected JLabel addPaymentsPerYearField() {
        return this.addFieldPair(CompoundModel.getVariableLabel(2), "##0", 1.0, 3);
    }
    
    protected JLabel addYearsToCompoundField() {
        return this.addFieldPair(CompoundModel.getVariableLabel(3), "##0", 1.0, 3);
    }
    
    protected JLabel addAnnualTaxRateField() {
        return this.addFieldPair(CompoundModel.getVariableLabel(4) + " (%)", "#0.00%", 0.0, 6);
    }
    
    protected JLabel addCompoundedAmountField() {
        return this.addFieldPair(CompoundModel.getVariableLabel(5), "#,##0.00", 0.0, 10);
    }
    
    protected JLabel addGrowthField() {
        return this.addFieldPair("Growth", "#,##0.00", 0.0, 10);
    }
    
    protected JLabel addGrowthPercentField() {
        return this.addFieldPair("Growth (%)", "#0.00%", 0.0, 6);
    }
    
    private JLabel addFieldPair(final String s, final String s2, final double n, final int n2) {
        final JLabel addLabel = this.addLabel(s);
        addLabel.setLabelFor(this.addField(s2, n, n2));
        return addLabel;
    }
    
    private JLabel addLabel(final String s) {
        final JLabel label = new JLabel(s + ":");
        this.gbc.anchor = 13;
        this.gbc.gridwidth = 1;
        this.add(Box.createHorizontalStrut(11));
        this.add(label, this.gbc);
        this.add(Box.createHorizontalStrut(12));
        return label;
    }
    
    private JFormattedNumField addField(final String s, final double n, final int n2) {
        final JFormattedNumField formattedNumField = JFormattedNumField.isPercentFormat(s) ? new JFormattedPercentField(s, n, n2) : new JFormattedNumField(s, n, n2);
        this.gbc.anchor = 17;
        this.gbc.gridwidth = 1;
        this.add(formattedNumField, this.gbc);
        this.gbc.gridwidth = 0;
        this.add(Box.createHorizontalStrut(10), this.gbc);
        return formattedNumField;
    }
}
