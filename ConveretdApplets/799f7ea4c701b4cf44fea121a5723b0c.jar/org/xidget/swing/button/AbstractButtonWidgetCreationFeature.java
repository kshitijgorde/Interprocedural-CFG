// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.button;

import java.util.List;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import org.xmodel.Xlate;
import javax.swing.JComponent;
import org.xidget.ifeature.model.ISingleValueUpdateFeature;
import java.awt.event.ItemEvent;
import org.xmodel.xpath.expression.StatefulContext;
import org.xidget.ifeature.IWidgetContextFeature;
import org.xidget.ifeature.IScriptFeature;
import java.awt.event.ActionEvent;
import org.xidget.IXidget;
import javax.swing.ButtonGroup;
import javax.swing.AbstractButton;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import org.xidget.swing.feature.SwingWidgetCreationFeature;

public class AbstractButtonWidgetCreationFeature extends SwingWidgetCreationFeature
{
    private ActionListener actionListener;
    private ItemListener itemListener;
    private AbstractButton button;
    private ButtonGroup group;
    
    public AbstractButtonWidgetCreationFeature(final IXidget xidget) {
        super(xidget);
        this.actionListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                final IScriptFeature scriptFeature = AbstractButtonWidgetCreationFeature.this.xidget.getFeature(IScriptFeature.class);
                if (scriptFeature != null) {
                    final StatefulContext context = AbstractButtonWidgetCreationFeature.this.xidget.getFeature(IWidgetContextFeature.class).getContext(actionEvent.getSource());
                    if (context != null) {
                        scriptFeature.runScript("onPress", context);
                    }
                }
            }
        };
        this.itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                AbstractButtonWidgetCreationFeature.this.xidget.getFeature(ISingleValueUpdateFeature.class).updateModel();
            }
        };
    }
    
    @Override
    protected JComponent createSwingWidget() {
        final Type type = Enum.valueOf(Type.class, Xlate.get(this.xidget.getConfig(), "type", "push"));
        switch (type) {
            case push: {
                this.button = new JButton();
                break;
            }
            case toggle: {
                this.button = new JToggleButton();
                break;
            }
            case check: {
                this.button = new JCheckBox();
                break;
            }
            case radio: {
                this.button = this.createRadioButton();
                break;
            }
        }
        if (type == Type.check || type == Type.radio) {
            this.button.setBorder(BorderFactory.createEmptyBorder());
        }
        this.button.setHorizontalTextPosition(2);
        this.button.setHorizontalAlignment(0);
        this.button.addActionListener(this.actionListener);
        this.button.addItemListener(this.itemListener);
        return this.button;
    }
    
    private AbstractButton createRadioButton() {
        final JRadioButton radioButton = new JRadioButton();
        final List<IXidget> children = this.xidget.getParent().getChildren();
        int n = children.indexOf(this.xidget);
        if (n == -1) {
            n = children.size();
        }
        if (n > 0) {
            this.group = children.get(n - 1).getFeature(ButtonGroup.class);
        }
        if (this.group == null) {
            this.group = new ButtonGroup();
        }
        this.group.add(radioButton);
        return radioButton;
    }
    
    @Override
    public Object[] getLastWidgets() {
        return new Object[] { this.button };
    }
    
    public AbstractButton getAbstractButton() {
        return this.button;
    }
    
    public ButtonGroup getButtonGroup() {
        return this.group;
    }
    
    private enum Type
    {
        push("push", 0), 
        toggle("toggle", 1), 
        check("check", 2), 
        radio("radio", 3);
        
        private Type(final String s, final int n) {
        }
    }
}
