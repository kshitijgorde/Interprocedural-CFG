// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.beergamecomponents;

import java.awt.Color;
import com.masystem.beergame.resource.ResourceManager;
import java.awt.Font;
import com.masystem.beergame.ui.component.TextLabelNode;

public class BeerGameTextLabel extends TextLabelNode
{
    public BeerGameTextLabel() {
        this(" ");
    }
    
    public BeerGameTextLabel(final String s) {
        this.setFont((Font)ResourceManager.getResource("DefaultFont"));
        this.setTextColor(Color.BLACK);
        this.init(s);
    }
    
    public final void setFont(final String s) {
        super.setFont((Font)ResourceManager.getResource(s));
    }
}
