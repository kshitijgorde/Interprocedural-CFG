// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.spinner;

import java.awt.Dimension;
import org.xmodel.IModelObject;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import org.xmodel.Xlate;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import org.xidget.IXidget;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.xidget.ifeature.ILabelFeature;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class JSpinnerWidgetCreationFeature extends SwingWidgetCreationFeature implements ILabelFeature
{
    private final ChangeListener changeListener;
    private final Runnable updateRunnable;
    private JComponent component;
    private JLabel jLabel;
    private JSpinner jSpinner;
    
    public JSpinnerWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
        this.changeListener = new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                SwingUtilities.invokeLater(JSpinnerWidgetCreationFeature.this.updateRunnable);
            }
        };
        this.updateRunnable = new Runnable() {
            @Override
            public void run() {
                throw new Error("Unresolved compilation problems: \n\tIValueFeature cannot be resolved to a type\n\tThe method getFeature(Class<IValueFeature>) from the type IFeatured refers to the missing type IValueFeature\n\tIValueFeature cannot be resolved to a type\n\tItem cannot be resolved to a type\n\tItem cannot be resolved to a type\n");
            }
        };
    }
    
    @Override
    protected JComponent createSwingWidget() {
        final IModelObject config = this.xidget.getConfig();
        final JSpinner spinner = new JSpinner();
        this.jSpinner = spinner;
        this.component = spinner;
        this.jSpinner.setBorder(new EmptyBorder(1, 1, 1, 1));
        addStaticChoices(this.xidget);
        if (Xlate.get(config, "label", Xlate.childGet(config, "label", (String)null)) != null) {
            (this.jLabel = new JLabel("")).setHorizontalAlignment(4);
            final GridBagLayout gridBagLayout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = 3;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 0, 0, 4);
            gridBagLayout.setConstraints(this.jLabel, gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.fill = 2;
            gridBagConstraints2.anchor = 17;
            gridBagConstraints2.weightx = 1.0;
            gridBagLayout.setConstraints(this.jSpinner, gridBagConstraints2);
            (this.component = new JPanel(gridBagLayout)).add(this.jLabel);
            this.component.add(this.jSpinner);
        }
        this.jSpinner.addChangeListener(this.changeListener);
        return this.component;
    }
    
    private static void addStaticChoices(final IXidget xidget) {
        throw new Error("Unresolved compilation problems: \n\tIChoiceListFeature cannot be resolved to a type\n\tThe method getFeature(Class<IChoiceListFeature>) from the type IFeatured refers to the missing type IChoiceListFeature\n\tIChoiceListFeature cannot be resolved to a type\n");
    }
    
    @Override
    public Object[] getLastWidgets() {
        if (this.component != this.jSpinner) {
            return new Object[] { this.component, this.jSpinner };
        }
        return new Object[] { this.jSpinner };
    }
    
    public JComponent getContainer() {
        return this.component;
    }
    
    public JSpinner getJSpinner() {
        return this.jSpinner;
    }
    
    @Override
    public void setLabelWidth(final int n) {
        if (this.jLabel == null) {
            return;
        }
        this.jLabel.setPreferredSize(new Dimension(n, this.jLabel.getPreferredSize().height));
    }
    
    @Override
    public int getLabelWidth() {
        if (this.jLabel == null) {
            return 0;
        }
        return this.jLabel.getPreferredSize().width;
    }
    
    @Override
    public void setLabelText(final String text) {
        if (this.jLabel != null) {
            this.jLabel.setText(text);
        }
    }
}
