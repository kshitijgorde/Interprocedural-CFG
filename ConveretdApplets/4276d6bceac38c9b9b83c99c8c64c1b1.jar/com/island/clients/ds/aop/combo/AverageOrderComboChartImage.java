// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.aop.combo;

import java.awt.Image;

public class AverageOrderComboChartImage extends AverageOrderComboChartImageProducer
{
    Image image;
    
    public AverageOrderComboChartImage(final String symbol) {
        super(symbol);
    }
    
    public void setImage(final Image image) {
        this.image = image;
    }
    
    protected Image getImageTemplate(final int width, final int height) {
        return this.image;
    }
}
