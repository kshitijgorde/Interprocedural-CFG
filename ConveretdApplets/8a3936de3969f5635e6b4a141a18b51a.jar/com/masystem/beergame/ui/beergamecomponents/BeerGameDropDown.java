// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.beergamecomponents;

import java.awt.Color;
import java.awt.Font;
import com.masystem.beergame.ui.graphics.StretchableImage;
import com.masystem.beergame.resource.ResourceManager;
import com.masystem.beergame.ui.component.ComboBoxNode;

public final class BeerGameDropDown extends ComboBoxNode
{
    public BeerGameDropDown(final String... array) {
        super(array, new StretchableImage(ResourceManager.getOptimizedImage("textfield.png")));
        this.getComponent().setInsets(14, 14, 14, 14);
        this.getComponent().setFont((Font)ResourceManager.getResource("DefaultFont"));
        this.getComponent().setTextColor(Color.BLACK);
        this.setSize(-2.0f, -2.0f);
    }
}
