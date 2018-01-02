// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component;

import java.awt.Font;
import com.masystem.beergame.ui.component.swing.ValueChangeListener;
import javax.swing.JComponent;
import com.masystem.beergame.ui.component.swing.IntegerFieldComponent;
import com.masystem.beergame.ui.scene.Node;

public class IntegerFieldNode extends Node
{
    public IntegerFieldNode() {
        super(new IntegerFieldComponent());
    }
    
    public final void setValueChangeListener(final ValueChangeListener valueChangeListener) {
        ((IntegerFieldComponent)super.getComponent()).setValueChangeListener(valueChangeListener);
    }
    
    public final void setFont(final Font font) {
        ((IntegerFieldComponent)super.getComponent()).setFont(font);
        this.updateSize();
    }
    
    public final void clearValue() {
        ((IntegerFieldComponent)super.getComponent()).clearValue();
        this.updateSize();
    }
    
    public final int getValue() {
        return ((IntegerFieldComponent)super.getComponent()).getValue();
    }
    
    public final void setMaxLength(final int n) {
        ((IntegerFieldComponent)super.getComponent()).setMaxLength(4);
    }
    
    public final void setEnabled(final boolean enabled) {
        ((IntegerFieldComponent)super.getComponent()).setEnabled(enabled);
    }
    
    private void updateSize() {
        this.setSize(-1.0f, -2.0f);
    }
    
    @Override
    public final IntegerFieldComponent getComponent() {
        return (IntegerFieldComponent)super.getComponent();
    }
}
