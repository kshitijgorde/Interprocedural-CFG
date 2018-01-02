// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.text;

import java.awt.Dimension;
import org.xmodel.IModelObject;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import org.xidget.layout.Size;
import org.xmodel.Xlate;
import java.awt.event.ActionEvent;
import org.xidget.ifeature.model.ISingleValueUpdateFeature;
import java.awt.event.FocusEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import org.xidget.IXidget;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import javax.swing.event.CaretListener;
import org.xidget.ifeature.ILabelFeature;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class JTextComponentWidgetCreationFeature extends SwingWidgetCreationFeature implements ILabelFeature
{
    private final CaretListener caretListener;
    private final FocusListener focusListener;
    private final Runnable updateRunnable;
    private final ActionListener actionListener;
    private JComponent component;
    private JLabel jLabel;
    private JTextComponent jText;
    
    public JTextComponentWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
        this.caretListener = new CaretListener() {
            @Override
            public void caretUpdate(final CaretEvent caretEvent) {
                SwingUtilities.invokeLater(JTextComponentWidgetCreationFeature.this.updateRunnable);
            }
        };
        this.focusListener = new FocusListener() {
            @Override
            public void focusGained(final FocusEvent focusEvent) {
            }
            
            @Override
            public void focusLost(final FocusEvent focusEvent) {
                SwingUtilities.invokeLater(JTextComponentWidgetCreationFeature.this.updateRunnable);
            }
        };
        this.updateRunnable = new Runnable() {
            @Override
            public void run() {
                JTextComponentWidgetCreationFeature.this.xidget.getFeature(ISingleValueUpdateFeature.class).updateModel();
            }
        };
        this.actionListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                SwingUtilities.invokeLater(JTextComponentWidgetCreationFeature.this.updateRunnable);
            }
        };
    }
    
    @Override
    protected JComponent createSwingWidget() {
        final IModelObject config = this.xidget.getConfig();
        final Size size = new Size(Xlate.get(config, "size", (String)null));
        if (config.isType("password")) {
            this.jText = new JPasswordField(size.width);
        }
        else if (size.height > 1) {
            this.jText = new JTextArea(size.height, size.width);
            this.component = new JScrollPane(this.jText);
        }
        else if (Xlate.get(config, "multiline", false)) {
            this.jText = new JTextArea();
            this.component = new JScrollPane(this.jText);
        }
        else {
            this.jText = new JTextField(size.width);
            ((JTextField)this.jText).addActionListener(this.actionListener);
        }
        if (Xlate.get(config, "label", Xlate.childGet(config, "label", (String)null)) != null) {
            (this.jLabel = new JLabel("")).setHorizontalAlignment(4);
            final GridBagLayout gridBagLayout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = 3;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 0, 0, 4);
            gridBagLayout.setConstraints(this.jLabel, gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            gridBagConstraints2.fill = 1;
            gridBagConstraints2.anchor = 18;
            gridBagConstraints2.weightx = 1.0;
            final JPanel component = new JPanel(gridBagLayout);
            if (this.component == null) {
                gridBagLayout.setConstraints(this.jText, gridBagConstraints2);
                component.add(this.jLabel);
                component.add(this.jText);
            }
            else {
                gridBagLayout.setConstraints(this.component, gridBagConstraints2);
                component.add(this.jLabel);
                component.add(this.component);
            }
            this.component = component;
        }
        if (this.component == null) {
            this.component = this.jText;
        }
        this.jText.addCaretListener(this.caretListener);
        this.jText.addFocusListener(this.focusListener);
        return this.component;
    }
    
    @Override
    public Object[] getLastWidgets() {
        if (this.component != this.jText) {
            return new Object[] { this.component, this.jText };
        }
        return new Object[] { this.jText };
    }
    
    public JComponent getContainer() {
        return this.component;
    }
    
    public JTextComponent getTextWidget() {
        return this.jText;
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
