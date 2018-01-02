// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component;

import java.awt.event.ActionListener;
import com.masystem.beergame.ui.graphics.StretchableImage;
import javax.swing.JComponent;
import com.masystem.beergame.ui.component.swing.StretchableTextFieldComponent;
import com.masystem.beergame.ui.scene.Node;

public class StretchableTextFieldNode extends Node
{
    public StretchableTextFieldNode() {
        super(new StretchableTextFieldComponent());
    }
    
    public StretchableTextFieldNode(final StretchableImage stretchableImage) {
        super(new StretchableTextFieldComponent(stretchableImage));
    }
    
    public final String getText() {
        return ((StretchableTextFieldComponent)super.getComponent()).getText();
    }
    
    public final void addActionListener(final ActionListener actionListener) {
        ((StretchableTextFieldComponent)super.getComponent()).addActionListener(actionListener);
    }
    
    @Override
    public final StretchableTextFieldComponent getComponent() {
        return (StretchableTextFieldComponent)super.getComponent();
    }
}
