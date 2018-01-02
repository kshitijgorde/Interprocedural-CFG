// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.beergamecomponents;

import java.awt.Color;
import java.awt.Font;
import com.masystem.beergame.ui.graphics.StretchableImage;
import com.masystem.beergame.resource.ResourceManager;
import com.masystem.beergame.ui.component.StretchableTextFieldNode;

public final class BeerGameLoginTextField extends StretchableTextFieldNode
{
    public BeerGameLoginTextField() {
        super(new StretchableImage(ResourceManager.getOptimizedImage("textfield.png")));
        this.getComponent().setInsets(14, 14, 14, 14);
        this.getComponent().setFont((Font)ResourceManager.getResource("DefaultFont"));
        this.getComponent().setTextColor(Color.BLACK);
        this.getComponent().setSelectedTextColor(Color.WHITE);
        this.getComponent().setSelectionColor(new Color(3170448));
        this.getComponent().setCaretColor(Color.BLACK);
        this.setSize(-2.0f, -2.0f);
    }
}
