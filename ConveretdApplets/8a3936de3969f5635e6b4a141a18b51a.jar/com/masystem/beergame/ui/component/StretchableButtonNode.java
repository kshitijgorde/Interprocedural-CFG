// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import com.masystem.beergame.ui.graphics.StretchableImage;
import javax.swing.JComponent;
import com.masystem.beergame.ui.component.swing.StretchableButtonComponent;
import com.masystem.beergame.ui.scene.Node;

public class StretchableButtonNode extends Node
{
    private boolean userSizeSet;
    
    public StretchableButtonNode() {
        super(new StretchableButtonComponent());
    }
    
    public StretchableButtonNode(final StretchableImage stretchableImage, final StretchableImage stretchableImage2, final StretchableImage stretchableImage3, final StretchableImage stretchableImage4) {
        super(new StretchableButtonComponent(stretchableImage, stretchableImage2, stretchableImage3, stretchableImage4));
    }
    
    public StretchableButtonNode(final String s, final StretchableImage stretchableImage, final StretchableImage stretchableImage2, final StretchableImage stretchableImage3, final StretchableImage stretchableImage4) {
        super(new StretchableButtonComponent(s, stretchableImage, stretchableImage2, stretchableImage3, stretchableImage4));
    }
    
    @Override
    public final void setSize(final float n, final float n2) {
        super.setSize(n, n2);
        this.userSizeSet = isUserSizeSet(n, n2);
    }
    
    @Override
    public final void setPreferredSize() {
        super.setPreferredSize();
        this.userSizeSet = false;
    }
    
    @Override
    public final void setSizeRelativeTo(final Node node, final float n, final float n2) {
        super.setSizeRelativeTo(node, n, n2);
        this.userSizeSet = isUserSizeSet(n, n2);
    }
    
    @Override
    public final void setSizeRelativeToParent(final float n, final float n2) {
        super.setSizeRelativeToParent(n, n2);
        this.userSizeSet = isUserSizeSet(n, n2);
    }
    
    public final void setFont(final Font font) {
        ((StretchableButtonComponent)super.getComponent()).setFont(font);
        this.formatText();
    }
    
    public final String getText() {
        return ((StretchableButtonComponent)super.getComponent()).getText();
    }
    
    public final void setText(final String text) {
        ((StretchableButtonComponent)super.getComponent()).setText(text);
        this.formatText();
    }
    
    public final void setTextColor(final Color textColor) {
        ((StretchableButtonComponent)super.getComponent()).setTextColor(textColor);
    }
    
    public final void setEnabled(final boolean enabled) {
        ((StretchableButtonComponent)super.getComponent()).setEnabled(enabled);
    }
    
    public final boolean isEnabled() {
        return ((StretchableButtonComponent)super.getComponent()).isEnabled();
    }
    
    public final StretchableImage getReleasedImage() {
        return ((StretchableButtonComponent)super.getComponent()).getReleasedImage();
    }
    
    public final void addActionListener(final ActionListener actionListener) {
        ((StretchableButtonComponent)super.getComponent()).addActionListener(actionListener);
    }
    
    @Override
    public final StretchableButtonComponent getComponent() {
        return (StretchableButtonComponent)super.getComponent();
    }
    
    private static boolean isUserSizeSet(final float n, final float n2) {
        return n != -2.0f || n2 != -2.0f;
    }
    
    private void formatText() {
        if (!this.userSizeSet) {
            super.setPreferredSize();
        }
    }
}
