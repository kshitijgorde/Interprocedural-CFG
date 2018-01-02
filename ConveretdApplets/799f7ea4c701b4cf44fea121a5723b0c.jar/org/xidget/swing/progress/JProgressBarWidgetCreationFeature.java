// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.progress;

import java.awt.Dimension;
import org.xmodel.IModelObject;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import org.xmodel.Xlate;
import org.xidget.ifeature.model.ISingleValueUpdateFeature;
import javax.swing.event.ChangeEvent;
import org.xidget.IXidget;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.xidget.ifeature.ILabelFeature;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class JProgressBarWidgetCreationFeature extends SwingWidgetCreationFeature implements ILabelFeature
{
    private ChangeListener changeListener;
    private JComponent component;
    private JLabel jLabel;
    private JProgressBar jProgress;
    
    public JProgressBarWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
        this.changeListener = new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                JProgressBarWidgetCreationFeature.this.xidget.getFeature(ISingleValueUpdateFeature.class).updateModel();
            }
        };
    }
    
    @Override
    protected JComponent createSwingWidget() {
        final IModelObject config = this.xidget.getConfig();
        (this.jProgress = new JProgressBar()).addChangeListener(this.changeListener);
        final int equals = Xlate.get(this.xidget.getConfig(), "orientation", "horizontal").equals("vertical") ? 1 : 0;
        this.jProgress.setOrientation(equals);
        if (Xlate.get(config, "label", Xlate.childGet(config, "label", (String)null)) != null) {
            (this.jLabel = new JLabel("")).setHorizontalAlignment((equals != 0) ? 0 : 4);
            final GridBagLayout gridBagLayout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            if (equals != 0) {
                gridBagConstraints.fill = 2;
                gridBagConstraints.anchor = 11;
                gridBagConstraints.insets = new Insets(0, 0, 4, 0);
            }
            else {
                gridBagConstraints.fill = 3;
                gridBagConstraints.anchor = 17;
                gridBagConstraints.insets = new Insets(0, 0, 0, 4);
            }
            gridBagLayout.setConstraints(this.jLabel, gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.fill = 1;
            if (equals != 0) {
                gridBagConstraints2.anchor = 10;
                gridBagConstraints2.weighty = 1.0;
                gridBagConstraints2.gridy = 1;
            }
            else {
                gridBagConstraints2.anchor = 18;
                gridBagConstraints2.weightx = 1.0;
            }
            final JPanel component = new JPanel(gridBagLayout);
            gridBagLayout.setConstraints(this.jProgress, gridBagConstraints2);
            component.add(this.jLabel);
            component.add(this.jProgress);
            this.component = component;
        }
        if (this.component == null) {
            this.component = this.jProgress;
        }
        return this.component;
    }
    
    @Override
    public Object[] getLastWidgets() {
        if (this.component != this.jProgress) {
            return new Object[] { this.component, this.jProgress };
        }
        return new Object[] { this.jProgress };
    }
    
    public JComponent getContainer() {
        return this.component;
    }
    
    public JProgressBar getJProgressBar() {
        return this.jProgress;
    }
    
    public JLabel getLabelWidget() {
        return this.jLabel;
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
