// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComponent;
import com.masystem.beergame.ui.component.swing.TextLabelComponent;
import com.masystem.beergame.ui.scene.Node;

public class TextLabelNode extends Node
{
    private boolean userSizeSet;
    
    public TextLabelNode() {
        this((String)null);
    }
    
    private TextLabelNode(final String s) {
        super(new TextLabelComponent());
        this.setVerticalAlignment(1);
        this.init(null);
    }
    
    protected final void init(final String text) {
        this.setText(text);
        super.setPreferredSize();
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
        ((TextLabelComponent)super.getComponent()).setFont(font);
        this.formatText();
    }
    
    public final void setText(final int n) {
        this.setText(String.valueOf(n));
    }
    
    public final void setText(final String text) {
        ((TextLabelComponent)super.getComponent()).setText(text);
        this.formatText();
    }
    
    public final String getText() {
        return ((TextLabelComponent)super.getComponent()).getText();
    }
    
    public final void setTextColor(final Color textColor) {
        ((TextLabelComponent)super.getComponent()).setTextColor(textColor);
    }
    
    public final void setHorizontalAlignment(final int horizontalAlignment) {
        ((TextLabelComponent)super.getComponent()).setHorizontalAlignment(horizontalAlignment);
    }
    
    public final void setVerticalAlignment(final int verticalAlignment) {
        ((TextLabelComponent)super.getComponent()).setVerticalAlignment(verticalAlignment);
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
