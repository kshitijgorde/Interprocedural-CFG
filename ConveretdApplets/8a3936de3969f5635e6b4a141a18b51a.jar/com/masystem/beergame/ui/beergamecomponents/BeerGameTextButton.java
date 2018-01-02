// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.beergamecomponents;

import com.masystem.beergame.ui.graphics.StretchableImage;
import java.awt.Color;
import com.masystem.beergame.resource.ResourceManager;
import java.awt.Font;
import com.masystem.beergame.ui.component.StretchableButtonNode;

public final class BeerGameTextButton extends StretchableButtonNode
{
    public BeerGameTextButton() {
        this((String)null);
    }
    
    public BeerGameTextButton(final String s) {
        super(s, load("button_released.png"), load("button_pressed.png"), load("button_rollover.png"), load("button_disabled.png"));
        this.getComponent().setInsets(20, 16, 20, 18);
        this.setFont((Font)ResourceManager.getResource("DefaultFont"));
        this.setTextColor(Color.BLACK);
        this.setSize(150.0f, 55.0f);
    }
    
    private static final StretchableImage load(final String s) {
        try {
            return new StretchableImage(ResourceManager.getOptimizedImage(s));
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
