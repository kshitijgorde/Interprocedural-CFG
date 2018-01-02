// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.beergamecomponents;

import com.masystem.beergame.resource.ResourceManager;
import java.awt.Font;
import java.awt.Color;
import com.masystem.beergame.ui.component.IntegerFieldNode;

public final class BeerGameIntegerField extends IntegerFieldNode
{
    private static final Color DEFAULT_TEXT_COLOR;
    private static final Color DEFAULT_SELECTED_TEXT_COLOR;
    private static final Color DEFAULT_SELECTION_COLOR;
    private static final Color DEFAULT_CARET_COLOR;
    
    public BeerGameIntegerField() {
        this.setFont((Font)ResourceManager.getResource("DefaultFont"));
        this.getComponent().setTextColor(BeerGameIntegerField.DEFAULT_TEXT_COLOR);
        this.getComponent().setSelectedTextColor(BeerGameIntegerField.DEFAULT_SELECTED_TEXT_COLOR);
        this.getComponent().setSelectionColor(BeerGameIntegerField.DEFAULT_SELECTION_COLOR);
        this.getComponent().setCaretColor(BeerGameIntegerField.DEFAULT_CARET_COLOR);
        this.setSize(-2.0f, -2.0f);
    }
    
    public final void setFont(final String s) {
        super.setFont((Font)ResourceManager.getResource(s));
    }
    
    static {
        DEFAULT_TEXT_COLOR = Color.BLACK;
        DEFAULT_SELECTED_TEXT_COLOR = Color.WHITE;
        DEFAULT_SELECTION_COLOR = new Color(3170448);
        DEFAULT_CARET_COLOR = Color.BLACK;
    }
}
