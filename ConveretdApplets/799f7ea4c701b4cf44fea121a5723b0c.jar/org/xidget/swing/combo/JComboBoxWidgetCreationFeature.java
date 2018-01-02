// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.combo;

import java.awt.Dimension;
import org.xmodel.IModelObject;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import org.xmodel.Xlate;
import javax.swing.ComboBoxModel;
import org.xidget.ifeature.model.ISelectionUpdateFeature;
import org.xidget.ifeature.model.ISingleValueUpdateFeature;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import org.xidget.IXidget;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import org.xidget.ifeature.ILabelFeature;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class JComboBoxWidgetCreationFeature extends SwingWidgetCreationFeature implements ILabelFeature
{
    private final ActionListener actionListener;
    private final Runnable updateRunnable;
    private JComponent component;
    private JLabel jLabel;
    private JComboBox jCombo;
    private boolean updating;
    
    public JComboBoxWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
        this.actionListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                if (JComboBoxWidgetCreationFeature.this.updating) {
                    return;
                }
                JComboBoxWidgetCreationFeature.access$1(JComboBoxWidgetCreationFeature.this, true);
                SwingUtilities.invokeLater(JComboBoxWidgetCreationFeature.this.updateRunnable);
            }
        };
        this.updateRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    JComboBoxWidgetCreationFeature.this.xidget.getFeature(ISingleValueUpdateFeature.class).updateModel();
                    JComboBoxWidgetCreationFeature.this.xidget.getFeature(ISelectionUpdateFeature.class).updateModel();
                }
                finally {
                    JComboBoxWidgetCreationFeature.access$1(JComboBoxWidgetCreationFeature.this, false);
                }
                JComboBoxWidgetCreationFeature.access$1(JComboBoxWidgetCreationFeature.this, false);
            }
        };
    }
    
    @Override
    protected JComponent createSwingWidget() {
        final IModelObject config = this.xidget.getConfig();
        final JComboBox comboBox = new JComboBox();
        this.jCombo = comboBox;
        this.component = comboBox;
        this.jCombo.setModel(new CustomComboModel(this.jCombo));
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
            gridBagLayout.setConstraints(this.jCombo, gridBagConstraints2);
            (this.component = new JPanel(gridBagLayout)).add(this.jLabel);
            this.component.add(this.jCombo);
        }
        this.jCombo.addActionListener(this.actionListener);
        return this.component;
    }
    
    @Override
    public Object[] getLastWidgets() {
        if (this.component != this.jCombo) {
            return new Object[] { this.component, this.jCombo };
        }
        return new Object[] { this.jCombo };
    }
    
    public JComponent getContainer() {
        return this.component;
    }
    
    public JComboBox getComboBox() {
        return this.jCombo;
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
    
    static /* synthetic */ void access$1(final JComboBoxWidgetCreationFeature comboBoxWidgetCreationFeature, final boolean updating) {
        comboBoxWidgetCreationFeature.updating = updating;
    }
}
